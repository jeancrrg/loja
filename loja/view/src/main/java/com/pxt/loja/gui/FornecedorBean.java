package com.pxt.loja.gui;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pxt.framework.business.PersistenceService;
import pxt.framework.faces.controller.CrudController;
import pxt.framework.faces.controller.CrudState;
import pxt.framework.faces.exception.CrudException;
import pxt.framework.validation.ValidationException;

import com.pxt.loja.business.impl.FornecedorBO;
import com.pxt.loja.domain.Fornecedor;


@ManagedBean
@ViewScoped
public class FornecedorBean extends CrudController<Fornecedor>{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PersistenceService persistenceService;
	@EJB
	private FornecedorBO fornecedorBO;
	
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
		try {
			fornecedorBO.validarCampos(getDomain());
		
			if (this.getEstadoCrud() == CrudState.ST_INSERT && fornecedorBO.verificarExisteCnpj(getDomain().getCnpj())) {
				throw new CrudException("Já possui esse fornecedor cadastrado!");
			}
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new CrudException(e.getMessage());
		}
	}
	
}
