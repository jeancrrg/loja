package com.pxt.loja.business.impl;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pxt.framework.validation.ValidationException;

import com.pxt.loja.domain.Produto;
import com.pxt.loja.persistence.dao.ProdutoDAO;

@Stateless
public class ProdutoBO {

	@EJB
	private ProdutoDAO produtoDAO;
	
	
	public Boolean verificarExisteProduto(String descricao, Long codigoFornecedor) {
		return produtoDAO.verificarExisteProduto(descricao, codigoFornecedor);
	}
	
	public void validarCadastro(Produto produto) throws ValidationException{
		if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
			throw new ValidationException("A descrição é um campo obrigatório!");
		}
		if (produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValidationException("O preço não pode ser 0, negativo ou vazio!");
		}
		if (produto.getTamanho() == null) {
			throw new ValidationException("O tamanho é um campo obrigatório!");
		}
		if (produto.getModelo() == null) {
			throw new ValidationException("O modelo é um campo obrigatório!");
		}
		if (produto.getMarca() == null) {
			throw new ValidationException("A marca é um campo obrigatório!");
		}
		if (produto.getFornecedor() == null) {
			throw new ValidationException("O fornecedor é um campo obrigatório!");
		}
	}
	
}
