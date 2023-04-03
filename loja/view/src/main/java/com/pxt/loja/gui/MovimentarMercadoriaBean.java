package com.pxt.loja.gui;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pxt.framework.business.PersistenceService;
import pxt.framework.business.TransactionException;
import pxt.framework.faces.controller.CrudController;
import pxt.framework.faces.controller.SearchFieldController;
import pxt.framework.faces.exception.CrudException;
import pxt.framework.persistence.PersistenceException;
import pxt.framework.validation.ValidationException;

import com.pxt.loja.business.impl.EstoqueBO;
import com.pxt.loja.business.impl.MovimentacaoBO;
import com.pxt.loja.domain.Movimentacao;
import com.pxt.loja.domain.Operacao;
import com.pxt.loja.domain.Produto;

@ManagedBean
@ViewScoped
public class MovimentarMercadoriaBean extends CrudController<Movimentacao>{
	private static final long serialVersionUID = 1L;

	@EJB
	private PersistenceService persistenceService;
	@EJB
	private MovimentacaoBO movimentacaoBO;
	@EJB
	private EstoqueBO estoqueBO;
	
	private Movimentacao domain;
	private SearchFieldController<Produto> searchProduto;
	
	
	@Override
	public Movimentacao getDomain() {
		if (domain == null) {
			domain = new Movimentacao();
		}
		return domain;
	}

	@Override
	public void setDomain(Movimentacao domain) {
		this.domain = domain;
	}

	@Override
	public PersistenceService getPersistenceService() {
		return persistenceService;
	}

	public List<Operacao> getOperacoesMovimentacao() {
		return Operacao.getOperacoesMovimentacao();
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
					super.buscar();
				}
				
				@Override
				public void limpar() {
					super.limpar();
				}
				
			};
		}
		return this.searchProduto;
	}
	
	@Override
	protected void antesSalvar() throws CrudException {
		try {
			movimentacaoBO.validarCampos(getDomain());
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new CrudException(e.getMessage());
		}
	}
	
	@Override
	protected void salvar() throws CrudException, TransactionException {
		try {
			movimentacaoBO.salvar(getDomain());
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new CrudException(e.getMessage());
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new CrudException(e.getMessage());
		}
	}
}
