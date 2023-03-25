package com.pxt.loja.business.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import pxt.framework.persistence.PersistenceException;

import com.pxt.loja.domain.Pedido;
import com.pxt.loja.persistence.dao.PedidoDAO;

@Stateless
public class PedidoBO {

	@EJB
	private PedidoDAO pedidoDAO;
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvarPedido(Pedido pedido) throws PersistenceException {
		try {
			Pedido pedidoSalvar = new Pedido();
			Date data = new Date();
			
			pedidoSalvar.setCliente(pedido.getCliente());
			pedidoSalvar.setProduto(pedido.getProduto());
			pedidoSalvar.setQuantidade(pedido.getQuantidade());
			pedidoSalvar.setFilial(pedido.getFilial());
			pedidoSalvar.setData(data);
			
			BigDecimal precoProduto  = pedido.getProduto().getPreco();
			Integer quantidadeProduto = pedido.getQuantidade();
			pedidoSalvar.setTotal(precoProduto.multiply(BigDecimal.valueOf(quantidadeProduto)));
			
			pedidoDAO.save(pedidoSalvar);
		} catch (PersistenceException e) {
			throw new PersistenceException("ERRO: Não foi possível salvar o pedido " + pedido.getCodigo());
		}
	}
	
	
	
}
