package com.pxt.loja.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "JEANCRG.TLJESTOQUE")
public class Estoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LJESTOQUE")
	@SequenceGenerator(sequenceName = "SEQ_LJESTOQUE", allocationSize = 1, name = "SEQ_LJESTOQUE")
	@Column(name = "CODEST")
	private Long codigo;
	
	@OneToOne
	@JoinColumn(name = "CODPROD", referencedColumnName = "CODPROD")
	private Produto produto;
	
	@Column(name = "QNTPROD")
	private Integer quantidade;
	
	@Column(name = "QNTRSV")
	private Integer quantidadeReservado;
	
	@Column(name = "QNTRCB")
	private Integer quantidadeRecebimento;
	
	
	public Estoque(){
	}


	public Estoque(Long codigo, Produto produto, Integer quantidade,
			Integer quantidadeReservado, Integer quantidadeRecebimento) {
		super();
		this.codigo = codigo;
		this.produto = produto;
		this.quantidade = quantidade;
		this.quantidadeReservado = quantidadeReservado;
		this.quantidadeRecebimento = quantidadeRecebimento;
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
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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
		Estoque other = (Estoque) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estoque [codigo=" + codigo + ", produto=" + produto
				+ ", quantidade=" + quantidade + ", quantidadeReservado="
				+ quantidadeReservado + ", quantidadeRecebimento="
				+ quantidadeRecebimento + "]";
	}
}
