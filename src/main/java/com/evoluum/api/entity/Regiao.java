package com.evoluum.api.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Regiao extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty
	private String sigla;
	
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public Regiao() {
		super();
	}
	public Regiao(String id, String sigla, String nome) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
	}
	
	
	
	
	
	

}
