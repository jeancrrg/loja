package com.pxt.loja.gui;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pxt.framework.business.PersistenceService;
import pxt.framework.faces.controller.CrudController;
import pxt.framework.faces.controller.CrudState;
import pxt.framework.faces.exception.CrudException;

import com.pxt.loja.business.impl.MarcaBO;
import com.pxt.loja.domain.Marca;

@ManagedBean
@ViewScoped
public class MarcaBean extends CrudController<Marca>{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PersistenceService persistenceService;
	@EJB
	private MarcaBO marcaBO; 
	
	private Marca domain;
	
	
	@Override
	public Marca getDomain() {
		if (domain == null) {
			domain = new Marca();
		}
		return domain;
	}

	@Override
	public void setDomain(Marca domain) {
		this.domain = domain;
	}

	@Override
	public PersistenceService getPersistenceService() {
		return persistenceService;
	}

	@Override
	protected void antesSalvar() throws CrudException {
		if (getDomain().getNome() == null || getDomain().getNome().isEmpty()) {
			throw new CrudException("O nome é um campo obrigatório!");
		}
			
		if (this.getEstadoCrud() == CrudState.ST_INSERT && marcaBO.verificarExisteNome(getDomain().getNome())) {
			throw new CrudException("Já possui essa marca cadastrada!");
		}
	}
	
}
