package com.pxt.loja.business.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.pxt.loja.persistence.dao.ClienteDAO;

@Stateless
public class ClienteBO {

	@EJB
	private ClienteDAO clienteDAO;
	
	
	public Boolean verificarExisteCpfCnpj(String cpfCnpj) {
		return clienteDAO.verificarExisteCpfCnpj(cpfCnpj);
	}
	
}
