package com.pxt.loja.business.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.pxt.loja.persistence.dao.ProdutoDAO;

@Stateless
public class ProdutoBO {

	@EJB
	private ProdutoDAO produtoDAO;
	
	
	public Boolean verificarExisteProduto(String descricao, Long codigoFornecedor) {
		return produtoDAO.verificarExisteProduto(descricao, codigoFornecedor);
	}
	
}
