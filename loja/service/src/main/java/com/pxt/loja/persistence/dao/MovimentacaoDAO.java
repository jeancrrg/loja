package com.pxt.loja.persistence.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import pxt.framework.persistence.PersistenceException;

import com.pxt.loja.domain.Movimentacao;

@Stateless
public class MovimentacaoDAO extends LOJAHibernateDAO<Movimentacao, Long> {

	
	public List<Movimentacao> buscarMovimentacao(Movimentacao movimentacao, Date dataInicial, Date dataFinal) throws PersistenceException {
		try {
			Criteria criteria = getSession().createCriteria(Movimentacao.class);
			
			if (movimentacao.getProduto() != null && movimentacao.getProduto().getCodigo() != null) {
				criteria.add(Restrictions.eq("produto.codigo", movimentacao.getProduto().getCodigo()));
			}
			if (movimentacao.getOperacao() != null) {
				criteria.add(Restrictions.eq("operacao", movimentacao.getOperacao()));
			}
			if (dataInicial != null && dataFinal != null) {
				criteria.add(Restrictions.between("data", dataInicial, dataFinal));
			}
			if (movimentacao.getDescricao() != null && !movimentacao.getDescricao().isEmpty()) {
				criteria.add(Restrictions.like("descricao", movimentacao.getDescricao(), MatchMode.ANYWHERE));
			}
			
			return criteria.list();
		} catch (Exception e) {
			throw new PersistenceException("Não possível buscar a movimentação!", e);
		}
	}
	
}
