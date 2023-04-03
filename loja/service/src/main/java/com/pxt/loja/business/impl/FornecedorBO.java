package com.pxt.loja.business.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pxt.framework.validation.ValidationException;

import com.pxt.loja.domain.Fornecedor;
import com.pxt.loja.persistence.dao.FornecedorDAO;

@Stateless
public class FornecedorBO {

	@EJB
	private FornecedorDAO fornecedorDAO;
	
	
	public Boolean verificarExisteCnpj(String cnpj) {
		return fornecedorDAO.verificarExisteCnpj(cnpj);
	}
	
	public void validarCampos(Fornecedor fornecedor) throws ValidationException {
		if (fornecedor.getNome() == null || fornecedor.getNome().isEmpty()) {
			throw new ValidationException("O nome � um campo obrigat�rio!");
		}
		if (fornecedor.getCnpj() == null || fornecedor.getCnpj().isEmpty()) {
			throw new ValidationException("O CNPJ � um campo obrigat�rio!");
		}
		if (fornecedor.getTelefone() == null || fornecedor.getTelefone().isEmpty()) {
			throw new ValidationException("O telefone � um campo obrigat�rio!");
		}
		if (fornecedor.getEmail() == null || fornecedor.getEmail().isEmpty()) {
			throw new ValidationException("O email � um campo obrigat�rio!");
		}
		
		if (fornecedor.getCnpj().length() != 14) {
			throw new ValidationException("CNPJ inv�lido! Verifique o CNPJ e se possui apenas n�meros!");
		} else if (fornecedor.getTelefone().length() != 11) {
			throw new ValidationException("Telefone inv�lido! Verifique o telefone, se possui o 9 na frente e apenas n�meros!");
		} 
		
	}
}
