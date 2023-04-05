package com.pxt.loja.business.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pxt.framework.persistence.PersistenceException;
import pxt.framework.validation.ValidationException;

import com.pxt.loja.domain.Estoque;
import com.pxt.loja.domain.Operacao;
import com.pxt.loja.domain.Produto;
import com.pxt.loja.persistence.dao.EstoqueDAO;

@Stateless
public class EstoqueBO {

	@EJB
	private EstoqueDAO estoqueDAO;

	
	public List<Estoque> buscarEstoque(Estoque estoque) throws PersistenceException {
		return estoqueDAO.buscarEstoque(estoque);
	}
	
	public void validarEstoque(Estoque estoque, Integer quantidade, String tipoEstoque) throws ValidationException {
		if (tipoEstoque.equals("RECEBIMENTO")) {
			if (quantidade > estoque.getQuantidadeRecebimento()) {
				throw new ValidationException("Não é possível realizar a operação dessa quantidade! Quantidade em recebimento: " + estoque.getQuantidadeRecebimento());
			}
		} else if (tipoEstoque.equals("DISPONIVEL")) {
			if (quantidade > estoque.getQuantidadeDisponivel()) {
				throw new ValidationException("Não é possível realizar a operação dessa quantidade! Quantidade disponível: " + estoque.getQuantidadeDisponivel());
			}
		} else if (tipoEstoque.equals("RESERVADO")) {
			if (quantidade > estoque.getQuantidadeReservado()) {
				throw new ValidationException("Não é possível realizar a operação dessa quantidade! Quantidade reservado: " + estoque.getQuantidadeReservado());
			}
		}
	}
	
	
	public void salvar(Produto produto, Integer quantidade, Operacao operacao) throws PersistenceException, ValidationException {
		try {
			Estoque estoqueAtual = estoqueDAO.buscarEstoquePorCodigoProduto(produto.getCodigo());
			Estoque estoqueSalvar = new Estoque();
			
			if (operacao.equals(Operacao.RECEBIMENTO)) {
				if (estoqueAtual == null) {
					estoqueSalvar.setProduto(produto);
					estoqueSalvar.setQuantidadeDisponivel(0);
					estoqueSalvar.setQuantidadeReservado(0);
					estoqueSalvar.setQuantidadeRecebimento(quantidade);
				} else {
					estoqueSalvar = estoqueAtual;
					estoqueSalvar.setQuantidadeRecebimento(estoqueSalvar.getQuantidadeRecebimento() + quantidade);
				}
			} else {
				if (estoqueAtual == null) {
					throw new ValidationException("Produto não encontrado no estoque!");
				} 
				estoqueSalvar = estoqueAtual;
				
				if (operacao.equals(Operacao.LIBERACAO)) {
					validarEstoque(estoqueSalvar, quantidade, "RECEBIMENTO");
					estoqueSalvar.setQuantidadeDisponivel(estoqueSalvar.getQuantidadeDisponivel() + quantidade);
					estoqueSalvar.setQuantidadeRecebimento(estoqueSalvar.getQuantidadeRecebimento() - quantidade);
				}
				if (operacao.equals(Operacao.DEVOLUCAO)) {
					estoqueSalvar.setQuantidadeDisponivel(estoqueSalvar.getQuantidadeDisponivel() + quantidade);
				}   
				if (operacao.equals(Operacao.DESCARTE)) {
					validarEstoque(estoqueSalvar, quantidade, "DISPONIVEL");
					estoqueSalvar.setQuantidadeDisponivel(estoqueSalvar.getQuantidadeDisponivel() - quantidade);
				}
				if (operacao.equals(Operacao.AJUSTE_RECEBIMENTO)) {
					estoqueSalvar.setQuantidadeRecebimento(estoqueSalvar.getQuantidadeRecebimento() - quantidade);
				}
				if (operacao.equals(Operacao.AJUSTE_LIBERACAO)) {
					estoqueSalvar.setQuantidadeDisponivel(estoqueSalvar.getQuantidadeDisponivel() - quantidade);
					estoqueSalvar.setQuantidadeRecebimento(estoqueSalvar.getQuantidadeRecebimento() + quantidade);
				}
				if (operacao.equals(Operacao.AJUSTE_RESERVA)) {
					estoqueSalvar.setQuantidadeReservado(estoqueSalvar.getQuantidadeReservado() - quantidade);
					estoqueSalvar.setQuantidadeDisponivel(estoqueSalvar.getQuantidadeDisponivel() + quantidade);
				}
			}
			estoqueDAO.saveOrUpdate(estoqueSalvar);
				
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new PersistenceException("Não foi possível salvar o estoque!");
		}
	}
	
	
	/*/public void reservarMercadoria(Pedido pedido) throws PersistenceException {
		Estoque estoqueAtual = estoqueDAO.buscarEstoquePorCodigo(pedido.getProduto().getCodigo());
		
		if (estoqueAtual == null) {
			throw new PersistenceException("Não possui o produto no estoque!");
		}
		if (estoqueAtual.getQuantidadeDisponivel() < pedido.getQuantidade()) {
			throw new PersistenceException("Quantidade indisponível no estoque! Quantidade disponível: " + estoqueAtual.getQuantidadeDisponivel());
		}
		estoqueAtual.setQuantidadeDisponivel(estoqueAtual.getQuantidadeDisponivel() - pedido.getQuantidade());
		estoqueAtual.setQuantidadeReservado(estoqueAtual.getQuantidadeReservado() + pedido.getQuantidade());
		
		try {
			estoqueDAO.saveOrUpdate(estoqueAtual);
		} catch (PersistenceException e) {
			throw new PersistenceException("ERRO: Não foi possível salvar estoque de reserva!", e);
		}
	}/*/
	
}
