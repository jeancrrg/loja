package com.pxt.loja.business.impl;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import pxt.framework.persistence.PersistenceException;

import com.pxt.loja.domain.Estoque;
import com.pxt.loja.domain.Movimentacao;
import com.pxt.loja.domain.Operacao;
import com.pxt.loja.persistence.dao.MovimentacaoDAO;

@Stateless
public class MovimentacaoBO {

	@EJB
	private MovimentacaoDAO movimentacaoDAO;
	
	
	public void salvarMovimentacao(Estoque estoque, String descricaoMovimentacao, Operacao operacao) throws PersistenceException {
		try {
			Movimentacao movimentacao = new Movimentacao();
			Date data = new Date();
			
			movimentacao.setData(data);
			movimentacao.setQuantidade(estoque.getQuantidadeRecebimento());
			movimentacao.setProduto(estoque.getProduto());
			movimentacao.setOperacao(operacao);
			movimentacao.setDescricao(descricaoMovimentacao);
		
			movimentacaoDAO.save(movimentacao);
		} catch (PersistenceException e) {
			throw new PersistenceException("ERRO: N�o foi poss�vel salvar a movimenta��o! ", e);
		}
		
	}
	
}
