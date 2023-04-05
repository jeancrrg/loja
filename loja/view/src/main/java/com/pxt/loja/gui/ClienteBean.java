package com.pxt.loja.gui;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pxt.framework.business.PersistenceService;
import pxt.framework.faces.controller.CrudController;
import pxt.framework.faces.controller.CrudState;
import pxt.framework.faces.exception.CrudException;

import com.pxt.loja.business.impl.ClienteBO;
import com.pxt.loja.domain.Cliente;

@ManagedBean
@ViewScoped
public class ClienteBean extends CrudController<Cliente> {
	private static final long serialVersionUID = 1L;

	@EJB
	private PersistenceService persistenceService;
	@EJB
	private ClienteBO clienteBO;
	
	private Cliente domain;
	
	
	@Override
	public Cliente getDomain() {
		if (domain == null) {
			domain = new Cliente();
		}
		return domain;
	}

	@Override
	public void setDomain(Cliente domain) {
		this.domain = domain;
	}

	@Override
	public PersistenceService getPersistenceService() {
		return persistenceService;
	}
	
	@Override
	protected void antesSalvar() throws CrudException {
		if (getDomain().getNome() == null || getDomain().getNome().isEmpty()) {
			this.msgWarn("O nome � um campo obrigat�rio!");
		}
		if (getDomain().getCpfCnpj() == null || getDomain().getCpfCnpj().isEmpty()) {
			throw new CrudException("O CPF � um campo obrigat�rio!");
		}
		if (getDomain().getEmail() == null || getDomain().getEmail().isEmpty()) {
			throw new CrudException("O email � um campo obrigat�rio!");
		}
		if (getDomain().getTelefone() == null || getDomain().getTelefone().isEmpty()) {
			throw new CrudException("O telefone � um campo obrigat�rio!");
		}
		if (getDomain().getDataNascimento() == null) {
			throw new CrudException("A data de nascimento � um campo obrigat�rio!");
		}
		
		Date dataAtual = new Date();
		if(getDomain().getDataNascimento().after(dataAtual)) {
			throw new CrudException("A data de nascimento n�o pode ser futura!");
		}
		if (getDomain().getCpfCnpj().length() != 11 && getDomain().getCpfCnpj().length() != 14) {
			throw new CrudException("CPF/CNPJ inv�lido! Verifique o CPF/CNPJ e se possui apenas n�meros!");
		} else if (getDomain().getTelefone().length() != 11) {
			throw new CrudException("Telefone inv�lido! Verifique o telefone, se possui o 9 na frente e apenas n�meros!");
		}
		
		if (this.getEstadoCrud() == CrudState.ST_INSERT && clienteBO.verificarExisteCpfCnpj(getDomain().getCpfCnpj())) {
			throw new CrudException("J� possui esse cliente cadastrado!");
		}
	}
	
}
