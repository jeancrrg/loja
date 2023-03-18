package com.pxt.loja.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "JEANCRG.TLJMOVIMENTACAO")
public class Movimentacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LJMOVIMENTACAO")
	@SequenceGenerator(sequenceName = "SEQ_LJMOVIMENTACAO", allocationSize = 1, name = "SEQ_LJMOVIMENTACAO")
	@Column(name = "CODMOV")
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "CODPROD", referencedColumnName = "CODPROD")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "CODOPR", referencedColumnName = "CODOPR")
	private Operacao operacao;
	
	@Column(name = "DATINI")
	private Date dataInicial;
	
	@Column(name = "DATFIN")
	private Date dataFinal;
	
	@Column(name = "QNTMVT")
	private Integer quantidadeMovimentada;
	
	
	public Movimentacao() {
	}


	public Movimentacao(Long codigo, Produto produto, Operacao operacao,
			Date dataInicial, Date dataFinal, Integer quantidadeMovimentada) {
		super();
		this.codigo = codigo;
		this.produto = produto;
		this.operacao = operacao;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.quantidadeMovimentada = quantidadeMovimentada;
	}


	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Operacao getOperacao() {
		return operacao;
	}
	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
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

	public Integer getQuantidadeMovimentada() {
		return quantidadeMovimentada;
	}
	public void setQuantidadeMovimentada(Integer quantidadeMovimentada) {
		this.quantidadeMovimentada = quantidadeMovimentada;
	}

	
	//M�todo para pegar o Produto n�o nulo para usar no xhtml
	public Produto getProdutoNaoNulo() {
		if (produto == null) {
			return new Produto();
		}
		return this.produto;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimentacao other = (Movimentacao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Movimentacao [codigo=" + codigo + ", produto=" + produto
				+ ", operacao=" + operacao + ", dataInicial=" + dataInicial
				+ ", dataFinal=" + dataFinal + ", quantidadeMovimentada="
				+ quantidadeMovimentada + "]";
	}
}
