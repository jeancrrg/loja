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
	
	@Column(name = "DESNOM")
	private String nome;
	
	@Column(name = "NUMCPFCNPJ")
	private String cpfCnpj;
	
	@Column(name = "DESEMA")
	private String email;
	
	@Column(name = "NUMTEL")
	private String telefone;
	
	@Column(name = "DATNASC")
	private Date dataNascimento;
	
	public Cliente() {
	}
	
	public Cliente(Long codigo, String nome, String cpf, String email,
			String telefone, Date dataNascimento) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cpfCnpj = cpf;
		this.email = email;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if (nome != null) {
			nome = nome.replaceAll("[~´.()'/-]", "").trim();
		}
		this.nome = nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		if (cpfCnpj != null) {
			cpfCnpj = cpfCnpj.replaceAll("[.()'/-]", "").replace(" ", "");
		}
		this.cpfCnpj = cpfCnpj;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if (email != null) {
			email = email.replaceAll("[()'/-]", "").replace(" ", "");
		}
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		if (telefone != null) {
			telefone = telefone.replaceAll("[.()'/-]", "").replace(" ", "");
		}
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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
		return nome;
	}
	
}
