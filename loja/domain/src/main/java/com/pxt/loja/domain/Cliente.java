package com.pxt.loja.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "JEANCRG.TLJCLIENTE")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LJCLIENTE")
	@SequenceGenerator(sequenceName = "SEQ_LJCLIENTE", allocationSize = 1, name = "SEQ_LJCLIENTE")
	@Column(name = "CODCLI")
	private Long codigo;
	@Column(name = "DESRAZ")
	private String razaoSocial;
	@Column(name = "DESFAN")
	private String nomeFantasia;
	@Column(name = "NUMCNPJ")
	private String cnpj;
	@Column(name = "DATNASC")
	private Date dataNascimento;
	@Column(name = "INDATV")
	private boolean ativo = true;
	
	
	public Cliente() {
	}

	
	public Cliente(Long codigo, String razaoSocial, String nomeFantasia,
			String cnpj, Date dataNascimento) {
		super();
		this.codigo = codigo;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.dataNascimento = dataNascimento;
		this.ativo = true;
	}


	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", razaoSocial=" + razaoSocial
				+ ", nomeFantasia=" + nomeFantasia + ", cnpj=" + cnpj
				+ ", dataNascimento=" + dataNascimento + ", ativo=" + ativo
				+ "]";
	}
}
