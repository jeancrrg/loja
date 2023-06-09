package com.pxt.loja.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import pxt.framework.persistence.PersistenceException;

import com.pxt.loja.domain.Estoque;

@Stateless
public class EstoqueDAO extends LOJAHibernateDAO<Estoque, Long> {

	
	public List<Estoque> buscarEstoque(Estoque estoque) throws PersistenceException {
		try {
			Criteria criteria = getSession().createCriteria(Estoque.class);
	
			if (estoque.getProdutoNaoNulo().getMarca() != null) {
				criteria.add(Restrictions.eq("produto.marca.codigo", estoque.getProduto().getMarca().getCodigo()));
			}
			else if (estoque.getProduto() != null && estoque.getProduto().getCodigo() != null) {
				criteria.add(Restrictions.eq("produto.codigo", estoque.getProduto().getCodigo()));
			}
			
			return criteria.list();
		} catch (Exception e) {
			throw new PersistenceException("N�o foi poss�vel buscar o estoque!", e);
		}
	}

	
	public Estoque buscarEstoquePorCodigoProduto(Long codigo) throws PersistenceException {
		try {
			Criteria criteria = getSession().createCriteria(Estoque.class);

			if (codigo != null) {
				criteria.add(Restrictions.eq("produto.codigo", codigo));
			}
			return (Estoque) criteria.uniqueResult();
			
		} catch (Exception e) {
			throw new PersistenceException("N�o foi poss�vel buscar o estoque pelo c�digo do produto!", e);
		}
	}

}
