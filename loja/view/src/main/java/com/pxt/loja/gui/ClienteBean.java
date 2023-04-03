package com.pxt.loja.gui;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pxt.framework.business.PersistenceService;
import pxt.framework.faces.controller.CrudController;
import pxt.framework.faces.controller.CrudState;
import pxt.framework.faces.exception.CrudException;
import pxt.framework.validation.ValidationException;

import com.pxt.loja.business.impl.ClienteBO;
import com.pxt.loja.domain.Cliente;

@ManagedBean
@ViewScoped
public class ClienteBean extends CrudController<Cliente> {
	private static final long serialVersionUID = 1L;

	@EJB
	private PersistenceService persistenceService;
	@EJB
	private ClienteBO clienteBO;
	
	private Cliente domain;
	
	
	@Override
	public Cliente getDomain() {
		if (domain == null) {
			domain = new Cliente();
		}
		return domain;
	}

	@Override
	public void setDomain(Cliente domain) {
		this.domain = domain;
	}

	@Override
	public PersistenceService getPersistenceService() {
		return persistenceService;
	}

	@Override
	protected void antesSalvar() throws CrudException {
		try {
			clienteBO.validarCadastro(getDomain());
			
			if (this.getEstadoCrud() == CrudState.ST_INSERT && clienteBO.verificarCpfCnpj(getDomain().getCpfCnpj())) {
				throw new CrudException("Já possui esse cliente cadastrado!");
			}
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new CrudException(e.getMessage());
		}
	}
	
}
