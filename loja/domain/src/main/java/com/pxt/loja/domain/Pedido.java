package com.pxt.loja.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
@Table(name = "JEANCRG.TLJPEDIDO")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LJPEDIDO")
	@SequenceGenerator(sequenceName = "SEQ_LJPEDIDO", allocationSize = 1, name = "SEQ_LJPEDIDO")
	@Column(name = "CODPED")
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "CODCLI", referencedColumnName = "CODCLI")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "CODPROD", referencedColumnName = "CODPROD")
	private Produto produto;
	@Column(name = "QNTPROD")
	private Integer quantidade;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "DESFIL")
	private Filial filial;
	
	@Column(name = "DATPED")
	private Date data;
	@Column(name = "TOTPED")
	private BigDecimal total;
	
	
	public Pedido() {
		
	}


	public Pedido(Long codigo, Cliente cliente, Produto produto,
			Integer quantidade, Filial filial, Date data, BigDecimal total) {
		super();
		this.codigo = codigo;
		this.cliente = cliente;
		this.produto = produto;
		this.quantidade = quantidade;
		this.filial = filial;
		this.data = data;
		this.total = total;
	}
	

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public List<Filial> getTodasFiliais() {
		return Filial.getTodasFiliais();
	}
	
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Cliente getClienteNaoNulo() {
		if (cliente == null) {
			return new Cliente();
		}
		return this.cliente;
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
		Pedido other = (Pedido) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
