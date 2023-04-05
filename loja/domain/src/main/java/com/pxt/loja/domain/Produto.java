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
	
	@Column(name = "NUMTAM")
	private String tamanho;
	
	@Column(name = "DESMOD")
	private String modelo;
	
	@ManyToOne
	@JoinColumn(name = "CODMRC", referencedColumnName = "CODMRC")
	private Marca marca;
	
	@ManyToOne
	@JoinColumn(name = "CODFRN", referencedColumnName = "CODFRN")
	private Fornecedor fornecedor;
	
	@Column(name = "VLRPROD")
	private BigDecimal preco;
	
	@Column(name = "INDATV")
	private boolean ativo = true;
	
	
	public Produto() {
	}


	public Produto(Long codigo, String descricao, String tamanho,
			String modelo, Marca marca, Fornecedor fornecedor,
			BigDecimal preco, boolean ativo) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.tamanho = tamanho;
		this.modelo = modelo;
		this.marca = marca;
		this.fornecedor = fornecedor;
		this.preco = preco;
		this.ativo = ativo;
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
			descricao = descricao.replaceAll("[~,{}=╢@#$%&*!<>?()+.()'/-]", "").trim();
			descricao = descricao.replaceAll("[аюцб]", "A");
			descricao = descricao.replaceAll("[ихй]", "E");
			descricao = descricao.replaceAll("[млн]", "I");
			descricao = descricao.replaceAll("[срту]", "O");
			descricao = descricao.replaceAll("[зыш]", "U");
			descricao = descricao.replaceAll("[г]", "C");
		}
		this.descricao = descricao;
	}

	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		if (modelo != null) {
			modelo = modelo.replaceAll("[~,{}=╢@#$%&*!<>?()+.()'/-]", "").trim();
			modelo = modelo.replaceAll("[аюцб]", "A");
			modelo = modelo.replaceAll("[ихй]", "E");
			modelo = modelo.replaceAll("[млн]", "I");
			modelo = modelo.replaceAll("[срту]", "O");
			modelo = modelo.replaceAll("[зыш]", "U");
			modelo = modelo.replaceAll("[г]", "C");
		}
		this.modelo = modelo;
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

	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Marca getMarcaNaoNulo() {
		if (marca == null) {
			return new Marca();
		}
		return this.marca;
	}
	
	public Fornecedor getFornecedorNaoNulo() {
		if (fornecedor == null) {
			return new Fornecedor();
		}
		return this.fornecedor;
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
		return descricao;
	}
	
}
