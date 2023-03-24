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

import com.pxt.loja.business.impl.EstoqueBO;
import com.pxt.loja.business.impl.MovimentacaoBO;
import com.pxt.loja.domain.Estoque;
import com.pxt.loja.domain.Operacao;
import com.pxt.loja.domain.Produto;

@ManagedBean
@ViewScoped
public class ReceberMercadoriaBean extends CrudController<Estoque> {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PersistenceService persistenceService;
	@EJB
	private EstoqueBO estoqueBO;
	@EJB
	private MovimentacaoBO movimentacaoBO;
	private Estoque domain;
	private String descricaoMovimentacao;
	private SearchFieldController<Produto> searchProduto;
	
	
	@Override
	public Estoque getDomain() {
		if (domain == null) {
			domain = new Estoque();
		}
		return domain;
	}

	@Override
	public void setDomain(Estoque domain) {
		this.domain = domain;
	}

	@Override
	public PersistenceService getPersistenceService() {
		return persistenceService;
	}

	public String getDescricaoMovimentacao() {
		return descricaoMovimentacao;
	}
	
	public void setDescricaoMovimentacao(String descricaoMovimentacao) {
		this.descricaoMovimentacao = descricaoMovimentacao;
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
	protected void buscar() throws TransactionException {
		setListagem(estoqueBO.buscarEstoque(getDomain()));
	}
	
	@Override
	protected void antesSalvar() throws CrudException {
		if (getDomain().getQuantidadeRecebimento() <= 0) {
			throw new CrudException("A quantidade de recebimento não pode ser 0 ou negativo!");
			
		} if (getDescricaoMovimentacao() == null || getDescricaoMovimentacao().isEmpty()) {
			throw new CrudException("É obrigatório preencher o campo de descrição!");
		}
		super.antesSalvar();
	}
	
	@Override
	protected void salvar() throws CrudException, TransactionException {
		try {
			estoqueBO.receberMercadoria(getDomain());
		} catch (PersistenceException e) {
			throw new CrudException("ERRO ao receber mercadoria:" + e.getMessage() + e.getStackTrace());
		}
		
		try {
			movimentacaoBO.salvarMovimentacao(getDomain(), descricaoMovimentacao, Operacao.RECEBIMENTO);
		} catch (PersistenceException e) {
			throw new CrudException("ERRO ao salvar movimentação de recebimento:" + e.getStackTrace());
		}
		
	}
	
}
