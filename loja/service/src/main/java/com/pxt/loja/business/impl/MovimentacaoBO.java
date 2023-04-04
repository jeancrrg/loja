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
	
	
	public void validarCamposMovimentacao(Movimentacao movimentacao) throws ValidationException {
		if (movimentacao.getDescricao() == null || movimentacao.getDescricao().isEmpty()) {
			throw new ValidationException("A descri��o � um campo obrigat�rio!");
		}
		if (movimentacao.getProduto() == null) {
			throw new ValidationException("O produto � um campo obrigat�rio!");
		}
		if (movimentacao.getOperacao() == null) {
			throw new ValidationException("A opera��o � um campo obrigat�rio!");
		}
		if (movimentacao.getQuantidade() == null || movimentacao.getQuantidade() <= 0) {
			throw new ValidationException("A quantidade n�o pode ser 0, negativo ou vazio!");
		}
	}
	
	public List<Movimentacao> buscarMovimentacao(Movimentacao movimentacao, Date dataInicial, Date dataFinal) throws PersistenceException{
		return movimentacaoDAO.buscarMovimentacao(movimentacao, dataInicial, dataFinal);
	}
	
	public void salvar(Movimentacao movimentacao) throws PersistenceException, ValidationException {
		try {
			estoqueBO.salvar(movimentacao.getProduto(), movimentacao.getQuantidade(), movimentacao.getOperacao());
			movimentacao.setData(new Date());
			movimentacaoDAO.save(movimentacao);
		} catch (PersistenceException e) {
			throw new PersistenceException("ERRO: N�o foi poss�vel salvar a movimenta��o! ", e);
		}
	}
	
}
