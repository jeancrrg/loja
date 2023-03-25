package com.pxt.loja.domain;

import java.util.ArrayList;
import java.util.List;

public enum Filial {

	CATALAO_GO,
	UBERLANDIA_MG,
	IGARAPAVA_SP,
	SAOGONCALO_BA,
	CRATO_CE;
	
	
	public static List<Filial> getTodasFiliais() {
		List<Filial> listaFiliais = new ArrayList<>();
		for (Filial filial : Filial.values()) {
			listaFiliais.add(filial);
		}
		return listaFiliais;
	}
	
}
