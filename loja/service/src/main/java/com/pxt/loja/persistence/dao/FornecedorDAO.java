package com.pxt.loja.persistence.dao;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pxt.loja.domain.Fornecedor;

@Stateless
public class FornecedorDAO extends LOJAHibernateDAO<Fornecedor, Long>{

	
	public Boolean verificarExisteCnpj(String cnpj) {
		Criteria criteria = getSession().createCriteria(Fornecedor.class);
		
		if (cnpj != null && !cnpj.isEmpty()) {
			criteria.add(Restrictions.eq("cnpj", cnpj));
		}
		
		if (criteria.list().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	
}
