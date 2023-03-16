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
@Table(name = "JEANCRG.TLJOPERACAO")
public class Operacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LJOPERACAO")
	@SequenceGenerator(sequenceName = "SEQ_LJOPERACAO", allocationSize = 1, name = "SEQ_LJOPERACAO")
	@Column(name = "CODOPR")
	private Long codigo;
	@Column(name = "DESOPR")
	private String descricao;
	
	
	public Operacao() {
	}


	public Operacao(Long codigo, String descricao) {
		super();
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
		Operacao other = (Operacao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Operacao [codigo=" + codigo + ", descricao=" + descricao + "]";
	}
}
