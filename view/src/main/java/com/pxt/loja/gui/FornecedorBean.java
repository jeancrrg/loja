package com.pxt.loja.gui;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pxt.framework.business.PersistenceService;
import pxt.framework.faces.controller.CrudController;
import pxt.framework.faces.exception.CrudException;

import com.pxt.loja.domain.Fornecedor;


@ManagedBean
@ViewScoped
public class FornecedorBean extends CrudController<Fornecedor>{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PersistenceService persistenceService;
	private Fornecedor domain;
	
	
	@Override
	public Fornecedor getDomain() {
		if (domain == null) {
			domain = new Fornecedor();
		}
		return domain;
	}
	
	@Override
	public void setDomain(Fornecedor domain) {
		this.domain = domain;
	}
	
	@Override
	public PersistenceService getPersistenceService() {
		return persistenceService;
	}
	
	@Override
	protected void antesSalvar() throws CrudException {
		if (getDomain().getDescricao() == null || getDomain().getDescricao().isEmpty()) {
			throw new CrudException("A descri��o do fornecedor � um campo obrigat�rio!");
		}
		super.antesSalvar();
	}
	
}
