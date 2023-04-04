package com.pxt.loja.gui;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pxt.framework.business.PersistenceService;
import pxt.framework.faces.controller.SearchController;
import pxt.framework.faces.controller.SearchFieldController;
import pxt.framework.persistence.PersistenceException;
import pxt.framework.validation.ValidationException;

import com.pxt.loja.business.impl.EstoqueBO;
import com.pxt.loja.domain.Estoque;
import com.pxt.loja.domain.Produto;

@ManagedBean
@ViewScoped
public class EstoqueBean extends SearchController<Estoque>{
	private static final long serialVersionUID = 1L;

	@EJB
	private PersistenceService persistenceService;
	@EJB
	private EstoqueBO estoqueBO;
	
	private Estoque domain;
	private SearchFieldController<Produto> searchProduto;
	
	public Estoque getDomain() {
		if (domain == null) {
			domain = new Estoque();
		}
		return domain;
	}

	public void setDomain(Estoque domain) {
		this.domain = domain;
	}

	public PersistenceService getPersistenceService() {
		return persistenceService;
	}
	
	
	@Override
	protected void busca() {
		try {
			estoqueBO.validarCampos(getDomain());
			List<Estoque> listaEstoque = estoqueBO.buscarEstoque(getDomain());
			if (listaEstoque.isEmpty()) {
				this.msgWarn("Nenhum produto encontrado no estoque!");
			}
			this.setListagem(listaEstoque);
			
		} catch (ValidationException e) {
			e.printStackTrace();
			this.msgWarn(e.getMessage());
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			this.msgError(e, e.getMessage());
		}
	}
	
	@SuppressWarnings("serial")
	public SearchFieldController<Produto> getSearchProduto() {
		if (this.searchProduto == null) {
			this.searchProduto = new SearchFieldController<Produto>(this.persistenceService, Produto.class) {

				@Override
				public Produto getObject() {
					return getDomain().getProdutoNaoNulo();
				}

				@Override
				public void setObject(Produto produto) {
					getDomain().setProduto(produto);
				}
				
				@Override
				public void buscar() throws Exception {
					setResultList((List<Produto>) persistenceService.findByExample(((Produto) getSearchObject())));
				}
				
				@Override
				public void limpar() {
					super.limpar();
				}
				
			};
		}
		return this.searchProduto;
	}
	
}
