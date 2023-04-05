package com.pxt.loja.business.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pxt.framework.persistence.PersistenceException;
import pxt.framework.validation.ValidationException;

import com.pxt.loja.domain.Movimentacao;
import com.pxt.loja.persistence.dao.MovimentacaoDAO;

@Stateless
public class MovimentacaoBO {

	@EJB
	private MovimentacaoDAO movimentacaoDAO;
	@EJB
	private EstoqueBO estoqueBO;
	
	
	public List<Movimentacao> buscarMovimentacao(Movimentacao movimentacao, Date dataInicial, Date dataFinal) throws PersistenceException{
		return movimentacaoDAO.buscarMovimentacao(movimentacao, dataInicial, dataFinal);
	}
	
	public void salvar(Movimentacao movimentacao) throws PersistenceException, ValidationException {
		try {
			estoqueBO.salvar(movimentacao.getProduto(), movimentacao.getQuantidade(), movimentacao.getOperacao());
			movimentacao.setData(new Date());
			movimentacaoDAO.save(movimentacao);
			
		} catch (PersistenceException e) {
			throw new PersistenceException("ERRO: Não foi possível salvar a movimentação! ", e);
		}
	}
	
}
