package com.pxt.loja.gui;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pxt.framework.business.PersistenceService;
import pxt.framework.faces.controller.CrudController;
import pxt.framework.faces.controller.SearchFieldController;
import pxt.framework.faces.exception.CrudException;

import com.pxt.loja.domain.Fornecedor;
import com.pxt.loja.domain.Marca;
import com.pxt.loja.domain.Produto;

@ManagedBean
@ViewScoped
public class ProdutoBean extends CrudController<Produto> {
	private static final long serialVersionUID = 1L;

	@EJB
	private PersistenceService persistenceService;
	private Produto domain;
	private SearchFieldController<Marca> searchMarca;
	private SearchFieldController<Fornecedor> searchFornecedor;
	
	
	@Override
	public Produto getDomain() {
		if (domain == null) {
			domain = new Produto();
		}
		return domain;
	}

	@Override
	public void setDomain(Produto domain) {
		this.domain = domain;
	}

	@Override
	public PersistenceService getPersistenceService() {
		return persistenceService;
	}

	@SuppressWarnings("serial")
	public SearchFieldController<Marca> getSearchMarca() {
		if (this.searchMarca == null) {
			this.searchMarca = new SearchFieldController<Marca>(this.persistenceService, Marca.class) {

				@Override
				public Marca getObject() {
					return getDomain().getMarcaNaoNulo();
				}

				@Override
				public void setObject(Marca marca) {
					getDomain().setMarca(marca);;
				}
				
				@Override
				public void buscar() throws Exception {
					setResultList((List<Marca>) persistenceService.findByExample(((Marca) getSearchObject())));
					super.buscar();
				}
				
				@Override
				public void limpar() {
					super.limpar();
				}
				
			};
		}
		return this.searchMarca;
	}
	
	
	@SuppressWarnings("serial")
	public SearchFieldController<Fornecedor> getSearchFornecedor() {
		if (this.searchFornecedor == null) {
			this.searchFornecedor = new SearchFieldController<Fornecedor>(this.persistenceService, Fornecedor.class) {
	
				@Override
				public Fornecedor getObject() {
					return getDomain().getFornecedorNaoNulo();
				}
				
				@Override
				public void setObject(Fornecedor fornecedor) {
					getDomain().setFornecedor(fornecedor);
				}

				@Override
				public void buscar() throws Exception {
					setResultList((List<Fornecedor>) persistenceService.findByExample(((Fornecedor) getSearchObject())));
				}

				@Override
				public void limpar() {
					super.limpar();
				}
			};
		}
		return this.searchFornecedor; 
	}
	
	
	@Override
	protected void antesSalvar() throws CrudException {
		if (getDomain().getDescricao() == null || getDomain().getDescricao().isEmpty()) {
			throw new CrudException("A descrição do produto é um campo obrigatório!");
		}
		super.antesSalvar();
	}
	
}
