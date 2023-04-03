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
			throw new ValidationException("O nome é um campo obrigatório!");
		}
		if (cliente.getCpfCnpj() == null || cliente.getCpfCnpj().isEmpty()) {
			throw new ValidationException("O CPF é um campo obrigatório!");
		}
		if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
			throw new ValidationException("O email é um campo obrigatório!");
		}
		if (cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
			throw new ValidationException("O telefone é um campo obrigatório!");
		}
		if (cliente.getDataNascimento() == null) {
			throw new ValidationException("A data de nascimento é um campo obrigatório!");
		}
		
		Date dataAtual = new Date();
		if(cliente.getDataNascimento().after(dataAtual)) {
			throw new ValidationException("A data de nascimento não pode ser futura!");
		}
		
		if (cliente.getCpfCnpj().length() != 11 && cliente.getCpfCnpj().length() != 14) {
			throw new ValidationException("CPF/CNPJ inválido! Verifique o CPF/CNPJ e se possui apenas números!");
		} else if (cliente.getTelefone().length() != 11) {
			throw new ValidationException("Telefone inválido! Verifique o telefone, se possui o 9 na frente e apenas números!");
		}
	}
	
}
