package com.monkeys.cobranca.model;

public enum StatusUsuario {

	ATIVO("Ativo"), 
	INATIVO("Inativo");

	private String descricao;

	StatusUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDesricao() {
		return descricao;

	}

}