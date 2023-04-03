package com.pxt.loja.domain;

import java.util.ArrayList;
import java.util.List;

public enum Operacao {

	RECEBIMENTO,
	LIBERACAO,
	RESERVA,
	DEVOLUCAO,
	DESCARTE,
	AJUSTE_RECEBIMENTO,
	AJUSTE_LIBERACAO,
	AJUSTE_RESERVA;
	
	public static List<Operacao> getOperacoesMovimentacao() {
		List<Operacao> listaOperacoes = new ArrayList<>();
		for (Operacao operacao : Operacao.values()) {
			if (operacao != Operacao.RESERVA) {
				listaOperacoes.add(operacao);
			}
		}
		return listaOperacoes;
	}
}
