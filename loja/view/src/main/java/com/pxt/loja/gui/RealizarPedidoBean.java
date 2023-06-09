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
import com.pxt.loja.business.impl.PedidoBO;
import com.pxt.loja.domain.Cliente;
import com.pxt.loja.domain.Operacao;
import com.pxt.loja.domain.Pedido;
import com.pxt.loja.domain.Produto;

@ManagedBean
@ViewScoped
public class RealizarPedidoBean extends CrudController<Pedido>{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PersistenceService persistenceService;
	@EJB
	private EstoqueBO estoqueBO;
	@EJB
	private PedidoBO pedidoBO;
	@EJB
	private MovimentacaoBO movimentacaoBO;
	
	private Pedido domain;
	private String descricaoMovimentacao;
	private SearchFieldController<Cliente> searchCliente;
	private SearchFieldController<Produto> searchProduto;
	
	
	@Override
	public Pedido getDomain() {
		if (domain == null) {
			domain = new Pedido();
		}
		return domain;
	}

	@Override
	public void setDomain(Pedido domain) {
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
	public SearchFieldController<Cliente> getSearchCliente() {
		if (this.searchCliente == null) {
			this.searchCliente = new SearchFieldController<Cliente>(this.persistenceService, Cliente.class) {

				@Override
				public Cliente getObject() {
					//ajustar para cliente nao nulo
					return getDomain().getCliente();
				}

				@Override
				public void setObject(Cliente cliente) {
					getDomain().setCliente(cliente);
				}
				
				@Override
				public void buscar() throws Exception {
					setResultList((List<Cliente>) persistenceService.findByExample(((Cliente) getSearchObject())));
					super.buscar();
				}
				
				@Override
				public void limpar() {
					super.limpar();
				}
				
			};
		}
		return this.searchCliente;
	}
	
	/*/@SuppressWarnings("serial")
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
	}/*/
	
	@Override
	protected void antesSalvar() throws CrudException {
		if (getDomain().getCliente() == null) {
			throw new CrudException("� obrigat�rio preencher o cliente!");
		}
		/*/if (getDomain().getProduto() == null) {
			throw new CrudException("� obrigat�rio preencher o produto!");
		}
		if (getDomain().getQuantidade() == null || getDomain().getQuantidade() <= 0) {
			throw new CrudException("N�o � poss�vel inserir quantidade 0, negativo ou vazio!");
		}/*/
		super.antesSalvar();
	}
	
	@Override
	protected void salvar() throws CrudException, TransactionException {
		try {
			//estoqueBO.reservarMercadoria(getDomain());
			Pedido pedido = pedidoBO.salvarPedido(getDomain());
			//movimentacaoBO.salvarMovimentacao(null, pedido, descricaoMovimentacao, Operacao.RESERVA);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new CrudException(e.getMessage());
		}
	}
		
}
