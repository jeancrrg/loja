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
@Table(name = "JEANCRG.TLJITEMPEDIDO")
public class ItemPedido implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LJITEMPEDIDO")
	@SequenceGenerator(sequenceName = "SEQ_LJITEMPEDIDO", allocationSize = 1, name = "SEQ_LJITEMPEDIDO")
	@Column(name = "INDITE")
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "CODPROD", referencedColumnName = "CODPROD")
	private Produto item;
	
	@Column(name = "QNTITE")
	private Integer quantidade;
	
	@Column(name = "VLRITE")
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name = "NUMPED", referencedColumnName = "NUMPED")
	private Pedido pedido;
	
	
	public ItemPedido() {
	}


	public ItemPedido(Long codigo, Produto item, Integer quantidade,
			BigDecimal valor, Pedido pedido) {
		super();
		this.codigo = codigo;
		this.item = item;
		this.quantidade = quantidade;
		this.valor = valor;
		this.pedido = pedido;
	}


	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Produto getItem() {
		return item;
	}
	public void setItem(Produto item) {
		this.item = item;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
}
