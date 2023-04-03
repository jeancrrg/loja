package com.pxt.loja.persistence.dao;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pxt.loja.domain.Cliente;

@Stateless
public class ClienteDAO extends LOJAHibernateDAO<Cliente, Long>{

	
	public Boolean verificarCpfCnpj(String cpfCnpj) {
		Criteria criteria = getSession().createCriteria(Cliente.class);
		
		if (cpfCnpj != null && !cpfCnpj.isEmpty()) {
			criteria.add(Restrictions.eq("cpfCnpj", cpfCnpj));
		}
		
		if (criteria.list().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	
}
