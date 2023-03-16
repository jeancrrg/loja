package com.pxt.loja.domain;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "JEANCRG.TLJPRODUTO")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LJPRODUTO")
	@SequenceGenerator(sequenceName = "SEQ_LJPRODUTO", allocationSize = 1, name = "SEQ_LJPRODUTO")
	@Column(name = "CODPROD")
	private Long codigo;
	@Column(name = "DESPROD")
	private String descricao;
	@Column(name = "PREPROD")
	private BigDecimal preco;
	
	@ManyToOne
	@JoinColumn(name = "CODMRC", referencedColumnName = "CODMRC")
	private Marca marca;
	
	@ManyToOne
	@JoinColumn(name = "CODFRN", referencedColumnName = "CODFRN")
	private Fornecedor fornecedor;
	
	@Column(name = "INDATV")
	private boolean ativo = true;
	
	
	public Produto() {
	}


	public Produto(Long codigo, String descricao, BigDecimal preco,
			Marca marca, Fornecedor fornecedor) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.marca = marca;
		this.fornecedor = fornecedor;
		this.ativo = true;
	}


	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao
				+ ", preco=" + preco + ", marca=" + marca + ", fornecedor="
				+ fornecedor + ", ativo=" + ativo + "]";
	}
}
