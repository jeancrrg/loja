package com.pxt.loja.gui;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pxt.framework.business.PersistenceService;
import pxt.framework.faces.controller.CrudController;
import pxt.framework.faces.exception.CrudException;

import com.pxt.loja.domain.Cliente;

@ManagedBean
@ViewScoped
public class ClienteBean extends CrudController<Cliente> {
	private static final long serialVersionUID = 1L;

	@EJB
	private PersistenceService persistenceService;
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
		if (getDomain().getRazaoSocial() == null || getDomain().getRazaoSocial().isEmpty()) {
			throw new CrudException("A razão social é um campo obrigatório!");
		}
		if (getDomain().getNomeFantasia() == null || getDomain().getNomeFantasia().isEmpty()) {
			throw new CrudException("O nome fantasia é um campo obrigatório!");
		}
		super.antesSalvar();
	}
	
}
