package com.pxt.loja.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "JEANCRG.TLJESTOQUE")
public class Estoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne
	@JoinColumn(name = "CODPROD", referencedColumnName = "CODPROD")
	private Produto produto;
	
	@Column(name = "QNTDSP")
	private Integer quantidadeDisponivel;
	
	@Column(name = "QNTRSV")
	private Integer quantidadeReservado;
	
	@Column(name = "QNTRCB")
	private Integer quantidadeRecebimento;
	
	
	public Estoque(){
	}


	public Estoque(Produto produto, Integer quantidadeDisponivel,
			Integer quantidadeReservado, Integer quantidadeRecebimento) {
		super();
		this.produto = produto;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.quantidadeReservado = quantidadeReservado;
		this.quantidadeRecebimento = quantidadeRecebimento;
	}


	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}
	public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}

	public Integer getQuantidadeReservado() {
		return quantidadeReservado;
	}
	public void setQuantidadeReservado(Integer quantidadeReservado) {
		this.quantidadeReservado = quantidadeReservado;
	}
	
	public Integer getQuantidadeRecebimento() {
		return quantidadeRecebimento;
	}
	public void setQuantidadeRecebimento(Integer quantidadeRecebimento) {
		this.quantidadeRecebimento = quantidadeRecebimento;
	}

	public Produto getProdutoNaoNulo() {
		if (produto == null) {
			return new Produto();
		}
		return this.produto;
	}

}
