package com.pxt.loja.persistence.dao;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pxt.loja.domain.Produto;

@Stateless
public class ProdutoDAO extends LOJAHibernateDAO<Produto, Long>{

	
	public Boolean verificarExisteProduto(String descricao, Long codigoFornecedor) {
		Criteria criteria = getSession().createCriteria(Produto.class);
		
		if (descricao != null && !descricao.isEmpty() && codigoFornecedor != null && !descricao.isEmpty()) {
			criteria.add(Restrictions.like("descricao", descricao));
			criteria.add(Restrictions.eq("fornecedor.codigo", codigoFornecedor));
		}
		
		if (criteria.list().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
}
