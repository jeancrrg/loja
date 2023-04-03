package com.pxt.loja.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	
	@Column(name = "DESMOV")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "CODPROD", referencedColumnName = "CODPROD")
	private Produto produto;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "DESOPR")
	private Operacao operacao;
	
	@Column(name = "DATMOV")
	private Date data;
	
	@Column(name = "QNTMOV")
	private Integer quantidade;
	
	public Movimentacao() {
	}
	
	public Movimentacao(Long codigo, String descricao, Produto produto,
			Operacao operacao, Date data, Integer quantidade) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.produto = produto;
		this.operacao = operacao;
		this.data = data;
		this.quantidade = quantidade;
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
		if (descricao != null) {
			descricao = descricao.trim();
		}
		this.descricao = descricao;
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

	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

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
		return descricao;
	}
	
}
