package com.pxt.loja.gui;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pxt.framework.business.PersistenceService;
import pxt.framework.faces.controller.SearchController;
import pxt.framework.faces.controller.SearchFieldController;
import pxt.framework.persistence.PersistenceException;
import pxt.framework.validation.ValidationException;

import com.pxt.loja.business.impl.MovimentacaoBO;
import com.pxt.loja.domain.Movimentacao;
import com.pxt.loja.domain.Operacao;
import com.pxt.loja.domain.Produto;

@ManagedBean
@ViewScoped
public class RelatorioMovimentacaoBean extends SearchController<Movimentacao>{
	private static final long serialVersionUID = 1L;

	@EJB
	private PersistenceService persistenceService;
	@EJB
	private MovimentacaoBO movimentacaoBO;
	
	private Movimentacao domain;
	private Date dataInicial;
	private Date dataFinal;
	private SearchFieldController<Produto> searchProduto;
	
	
	public Movimentacao getDomain() {
		if (domain == null) {
			domain = new Movimentacao();
		}
		return domain;
	}
	
	public void setDomain(Movimentacao domain) {
		this.domain = domain;
	}
	
	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public PersistenceService getPersistenceService() {
		return persistenceService;
	}
	
	@Override
	protected void limpar() {
		setDataInicial(null);
		setDataFinal(null);
		searchProduto.limpar();
		getDomain().setOperacao(null);
		getDomain().setDescricao(null);
		
		super.limpar();
	}
	
	public List<Operacao> getOperacoesMovimentacao() {
		return Operacao.getOperacoesMovimentacao();
	}
	
	public void validarCampos() throws ValidationException {
		if (getDataInicial() == null || getDataFinal() == null) {
			throw new ValidationException("A data inicial e final são obrigatórias!");
		}
		
		Date dataAtual = new Date();
		if(getDataInicial().after(dataAtual) || getDataFinal().after(dataAtual)) {
			throw new ValidationException("As datas não podem ser futuras!");
		}
		if (getDataInicial().after(getDataFinal())) {
			throw new ValidationException("A data inicial não pode ser maior que a final!");
		}
		
		Long diferencaHoras = (getDataFinal().getTime() - getDataInicial().getTime()) / 86400000;
		if (diferencaHoras > 30) {
			throw new ValidationException("O período não pode ser maior que 30 dias!");
		}
	}
	
	@Override
	protected void busca() {
		try {
			validarCampos();
			List<Movimentacao> listaMovimentacao = movimentacaoBO.buscarMovimentacao(getDomain(), getDataInicial(), getDataFinal());
			if (listaMovimentacao.isEmpty()) {
				this.msgWarn("Nenhuma movimentação encontrada!");
			}
			this.setListagem(listaMovimentacao);
			
		} catch (ValidationException e) {
			e.printStackTrace();
			this.msgWarn(e.getMessage());
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			this.msgWarn(e.getMessage());
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
