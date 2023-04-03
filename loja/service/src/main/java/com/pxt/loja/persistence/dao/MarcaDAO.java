package com.pxt.loja.persistence.dao;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.pxt.loja.domain.Marca;

@Stateless
public class MarcaDAO extends LOJAHibernateDAO<Marca, Long>{

	
	public Boolean verificarExisteNome(String nome) {
		Criteria criteria = getSession().createCriteria(Marca.class);
		
		if (nome != null && !nome.isEmpty()) {
			criteria.add(Restrictions.eq("nome", nome));
		}
		
		if (criteria.list().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
}
