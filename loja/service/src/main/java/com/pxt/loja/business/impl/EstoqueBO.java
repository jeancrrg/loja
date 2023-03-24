package com.pxt.loja.business.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import pxt.framework.persistence.PersistenceException;

import com.pxt.loja.domain.Estoque;
import com.pxt.loja.domain.Pedido;
import com.pxt.loja.persistence.dao.EstoqueDAO;

@Stateless
public class EstoqueBO {

	@EJB
	EstoqueDAO estoqueDAO;
	@EJB
	PedidoBO pedidoBO;
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Estoque> buscarEstoque(Estoque estoque) {
		return estoqueDAO.buscarEstoque(estoque);
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Estoque buscarEstoquePorCodigo(Long codigo) {
		return estoqueDAO.buscarEstoquePorCodigo(codigo);
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void receberMercadoria(Estoque estoque) throws PersistenceException {
		List<Estoque> listaEstoque = estoqueDAO.buscarEstoque(estoque);
		Estoque estoqueSalvar;
		
		if (listaEstoque.isEmpty()) {
			estoqueSalvar = new Estoque();
				
			estoqueSalvar.setProduto(estoque.getProduto());
			estoqueSalvar.setQuantidadeDisponivel(0);
			estoqueSalvar.setQuantidadeReservado(0);
			estoqueSalvar.setQuantidadeRecebimento(estoque.getQuantidadeRecebimento());
		
		} else {
			estoqueSalvar = listaEstoque.get(0);
			estoqueSalvar.setQuantidadeRecebimento(estoqueSalvar.getQuantidadeRecebimento() + estoque.getQuantidadeRecebimento());
		}
		estoqueDAO.saveOrUpdate(estoqueSalvar);
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void liberarMercadoria(Estoque estoque) throws PersistenceException {
		List<Estoque> listaEstoque = estoqueDAO.buscarEstoque(estoque);
		Estoque estoqueAtual = new Estoque();
		
		if (listaEstoque.isEmpty()) {
			throw new PersistenceException("Produto não encontrado no estoque!");
		} else {
			estoqueAtual = listaEstoque.get(0);
		}
		
		if (estoque.getQuantidadeRecebimento() > estoqueAtual.getQuantidadeRecebimento()) {
			throw new PersistenceException("Não é possível realizar a movimentação dessa quantidade! Quantidade em recebimento: " + estoqueAtual.getQuantidadeRecebimento());
		}
		
		estoqueAtual.setQuantidadeDisponivel(estoqueAtual.getQuantidadeDisponivel() + estoque.getQuantidadeRecebimento());
		estoqueAtual.setQuantidadeRecebimento(estoqueAtual.getQuantidadeRecebimento() - estoque.getQuantidadeRecebimento());
		
		estoqueDAO.saveOrUpdate(estoqueAtual);
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void reservarMercadoria(Pedido pedido) throws PersistenceException {
		Estoque estoqueAtual = estoqueDAO.buscarEstoquePorCodigo(pedido.getProduto().getCodigo());
		
		if (estoqueAtual == null) {
			throw new PersistenceException("Não possui o produto no estoque!");
		}
		if (estoqueAtual.getQuantidadeDisponivel() < pedido.getQuantidade()) {
			throw new PersistenceException("Quantidade indisponível no estoque! Quantidade disponível: " + estoqueAtual.getQuantidadeDisponivel());
		}
		
		estoqueAtual.setQuantidadeDisponivel(estoqueAtual.getQuantidadeDisponivel() - pedido.getQuantidade());
		estoqueAtual.setQuantidadeReservado(estoqueAtual.getQuantidadeReservado() + pedido.getQuantidade());
		
		estoqueDAO.saveOrUpdate(estoqueAtual);
	}
	
}
