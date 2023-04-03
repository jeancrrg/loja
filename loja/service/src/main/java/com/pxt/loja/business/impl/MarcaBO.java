package com.pxt.loja.business.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pxt.framework.validation.ValidationException;

import com.pxt.loja.persistence.dao.MarcaDAO;

@Stateless
public class MarcaBO {

	@EJB
	private MarcaDAO marcaDAO;
	
	
	public Boolean verificarExisteNome(String nome) {
		return marcaDAO.verificarExisteNome(nome);
	}	
	
	public void validarCampos(String nome) throws ValidationException {
		if (nome == null || nome.isEmpty()) {
			throw new ValidationException("O nome é um campo obrigatório!");
		}
	}
}
