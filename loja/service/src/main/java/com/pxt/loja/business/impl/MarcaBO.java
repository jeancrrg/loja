package com.pxt.loja.business.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.pxt.loja.persistence.dao.MarcaDAO;

@Stateless
public class MarcaBO {

	@EJB
	private MarcaDAO marcaDAO;
	
	
	public Boolean verificarExisteNome(String nome) {
		return marcaDAO.verificarExisteNome(nome);
	}	

}
