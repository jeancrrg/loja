package com.pxt.loja.business.impl;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;



import pxt.framework.validation.ValidationException;

import com.pxt.loja.domain.Cliente;
import com.pxt.loja.persistence.dao.ClienteDAO;

@Stateless
public class ClienteBO {

	@EJB
	private ClienteDAO clienteDAO;
	
	
	public Boolean verificarCpfCnpj(String cpfCnpj) {
		return clienteDAO.verificarCpfCnpj(cpfCnpj);
	}
	
	public void validarCadastro(Cliente cliente) throws ValidationException{
		if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
			throw new ValidationException("O nome � um campo obrigat�rio!");
		}
		if (cliente.getCpfCnpj() == null || cliente.getCpfCnpj().isEmpty()) {
			throw new ValidationException("O CPF � um campo obrigat�rio!");
		}
		if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
			throw new ValidationException("O email � um campo obrigat�rio!");
		}
		if (cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
			throw new ValidationException("O telefone � um campo obrigat�rio!");
		}
		if (cliente.getDataNascimento() == null) {
			throw new ValidationException("A data de nascimento � um campo obrigat�rio!");
		}
		
		Date dataAtual = new Date();
		if(cliente.getDataNascimento().after(dataAtual)) {
			throw new ValidationException("A data de nascimento n�o pode ser futura!");
		}
		
		if (cliente.getCpfCnpj().length() != 11 && cliente.getCpfCnpj().length() != 14) {
			throw new ValidationException("CPF/CNPJ inv�lido! Verifique o CPF/CNPJ e se possui apenas n�meros!");
		} else if (cliente.getTelefone().length() != 11) {
			throw new ValidationException("Telefone inv�lido! Verifique o telefone, se possui o 9 na frente e apenas n�meros!");
		}
	}
	
}
