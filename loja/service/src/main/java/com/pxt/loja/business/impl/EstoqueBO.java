package com.pxt.loja.business.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pxt.framework.persistence.PersistenceException;

import com.pxt.loja.domain.Estoque;
import com.pxt.loja.domain.Pedido;
import com.pxt.loja.persistence.dao.EstoqueDAO;

@Stateless
public class EstoqueBO {

	@EJB
	EstoqueDAO estoqueDAO;

	
	public List<Estoque> buscarEstoque(Estoque estoque) {
		return estoqueDAO.buscarEstoque(estoque);
	}
	
	
	public Estoque buscarEstoquePorCodigo(Long codigo) throws PersistenceException {
		return estoqueDAO.buscarEstoquePorCodigo(codigo);
	}
	
	
	public void receberMercadoria(Estoque estoque) throws PersistenceException {
		List<Estoque> estoqueAtual = estoqueDAO.buscarEstoque(estoque);
		Estoque estoqueSalvar;

		if (estoqueAtual.isEmpty()) {
			estoqueSalvar = new Estoque();

			estoqueSalvar.setProduto(estoque.getProduto());
			estoqueSalvar.setQuantidadeDisponivel(0);
			estoqueSalvar.setQuantidadeReservado(0);
			estoqueSalvar.setQuantidadeRecebimento(estoque.getQuantidadeRecebimento());
			estoqueSalvar.setFilial(estoque.getFilial());

		} else {
			estoqueSalvar = estoqueAtual.get(0);

			if (estoque.getFilial() != estoqueSalvar.getFilial() && estoqueSalvar.getQuantidadeRecebimento() > 0) {
				throw new PersistenceException("N�o � poss�vel realizar o recebimento para outra filial pois ainda existe estoque de recebimento na filial: " + estoqueSalvar.getFilial());
			}
			estoqueSalvar.setQuantidadeRecebimento(estoqueSalvar.getQuantidadeRecebimento() + estoque.getQuantidadeRecebimento());
		}
		try {
			estoqueDAO.saveOrUpdate(estoqueSalvar);
		} catch (PersistenceException e) {
			throw new PersistenceException("ERRO: N�o foi poss�vel salvar o estoque de recebimento!", e);
		}
	}
	
	
	public void liberarMercadoria(Estoque estoque) throws PersistenceException {
		List<Estoque> listaEstoque = estoqueDAO.buscarEstoque(estoque);
		Estoque estoqueAtual = new Estoque();
		
		if (listaEstoque.isEmpty()) {
			throw new PersistenceException("Produto n�o encontrado no estoque!");
		} else {
			estoqueAtual = listaEstoque.get(0);
		}
		if (estoque.getQuantidadeRecebimento() > estoqueAtual.getQuantidadeRecebimento()) {
			throw new PersistenceException("N�o � poss�vel realizar a libera��o dessa quantidade! Quantidade em recebimento: " + estoqueAtual.getQuantidadeRecebimento());
		}
		if (estoque.getFilial() != estoqueAtual.getFilial() && estoqueAtual.getQuantidadeRecebimento() > 0) {
			throw new PersistenceException("N�o � poss�vel realizar a libera��o para outra filial pois ainda existe estoque de recebimento na filial: " + estoqueAtual.getFilial());
		}
		estoqueAtual.setQuantidadeDisponivel(estoqueAtual.getQuantidadeDisponivel() + estoque.getQuantidadeRecebimento());
		estoqueAtual.setQuantidadeRecebimento(estoqueAtual.getQuantidadeRecebimento() - estoque.getQuantidadeRecebimento());
			
		try {
			estoqueDAO.saveOrUpdate(estoqueAtual);
		} catch (PersistenceException e) {
			throw new PersistenceException("N�o foi poss�vel salvar estoque de libera��o!", e);
		}
	}
	
	
	public Estoque reservarMercadoria(Pedido pedido) throws PersistenceException {
		Estoque estoqueAtual = estoqueDAO.buscarEstoquePorCodigo(pedido.getProduto().getCodigo());
		
		if (estoqueAtual == null) {
			throw new PersistenceException("N�o possui o produto no estoque!");
		}
		if (estoqueAtual.getQuantidadeDisponivel() < pedido.getQuantidade()) {
			throw new PersistenceException("Quantidade indispon�vel no estoque! Quantidade dispon�vel: " + estoqueAtual.getQuantidadeDisponivel());
		}
		estoqueAtual.setQuantidadeDisponivel(estoqueAtual.getQuantidadeDisponivel() - pedido.getQuantidade());
		estoqueAtual.setQuantidadeReservado(estoqueAtual.getQuantidadeReservado() + pedido.getQuantidade());
		
		try {
			return estoqueDAO.saveOrUpdate(estoqueAtual);
		} catch (PersistenceException e) {
			throw new PersistenceException("ERRO: N�o foi poss�vel salvar estoque de reserva!", e);
		}
	}
	
}
