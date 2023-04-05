package com.pxt.loja.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "JEANCRG.TLJFORNECEDOR")
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LJMARCA")
	@SequenceGenerator(sequenceName = "SEQ_LJMARCA", allocationSize = 1, name = "SEQ_LJMARCA")
	@Column(name = "CODFRN")
	private Long codigo;
	
	@Column(name = "DESFRN")
	private String nome;
	
	@Column(name = "NUMCNPJ")
	private String cnpj;
	
	@Column(name = "NUMTEL")
	private String telefone;
	
	@Column(name = "DESEMA")
	private String email;
	
	
	public Fornecedor() {
	}

	
	public Fornecedor(Long codigo, String nome, String cnpj, String telefone,
			String email) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.email = email;
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
			nome = nome.replaceAll("[~,{}=╢@#$%&*!<>?()+.()'/-]", "").trim();
			nome = nome.replaceAll("[аюцб]", "A");
			nome = nome.replaceAll("[ихй]", "E");
			nome = nome.replaceAll("[млн]", "I");
			nome = nome.replaceAll("[срту]", "O");
			nome = nome.replaceAll("[зыш]", "U");
			nome = nome.replaceAll("[г]", "C");
		}
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		if (cnpj != null) {
			cnpj = cnpj.replaceAll("[.()'/-]", "").replace(" ", "");
		}
		this.cnpj = cnpj;
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

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if (email != null) {
			email = email.replaceAll("[~,{}=╢#$%&*!<>?()+()'/-]", "").replace(" ", "");;
			email = email.replaceAll("[аюцб]", "A");
			email = email.replaceAll("[ихй]", "E");
			email = email.replaceAll("[млн]", "I");
			email = email.replaceAll("[срту]", "O");
			email = email.replaceAll("[зыш]", "U");
			email = email.replaceAll("[г]", "C");
		}
		this.email = email;
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
		Fornecedor other = (Fornecedor) obj;
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
