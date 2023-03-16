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
@Table(name = "JEANCRG.TLJMARCA")
public class Marca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LJMARCA")
	@SequenceGenerator(sequenceName = "SEQ_LJMARCA", allocationSize = 1, name = "SEQ_LJMARCA")
	@Column(name = "CODMRC")
	private Long codigo;
	@Column(name = "DESMRC")
	private String descricao;
	
	
	public Marca() {
	}

	
	public Marca(Long codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
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
		Marca other = (Marca) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Marca [codigo=" + codigo + ", descricao=" + descricao + "]";
	}
}
