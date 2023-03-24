package com.pxt.loja.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import pxt.framework.persistence.PersistenceException;

import com.pxt.loja.domain.Estoque;

@Stateless
public class EstoqueDAO extends LOJAHibernateDAO<Estoque, Long> {

	public List<Estoque> buscarEstoque(Estoque estoque) {
		Criteria criteria = getSession().createCriteria(Estoque.class);

		if (estoque.getProduto() != null
				&& estoque.getProduto().getCodigo() != null) {
			criteria.add(Restrictions.eq("produto.codigo", estoque.getProduto()
					.getCodigo()));
		}
		return criteria.list();
	}

	public Estoque buscarEstoquePorCodigo(Long codigo) throws PersistenceException {
		try {
			Criteria criteria = getSession().createCriteria(Estoque.class);

			if (codigo != null) {
				criteria.add(Restrictions.eq("produto.codigo", codigo));
			}
			return (Estoque) criteria.uniqueResult();
		} catch (Exception e) {
			throw new PersistenceException("Não foi possível buscar o estoque por código!", e);
		}
	}

}
