package com.evoluum.api.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mesorregiao implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty
	private String id;
	@JsonProperty
	private String nome;
	@JsonProperty
	private UF UF;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	
	



	public UF getUF() {
		return UF;
	}

	public void setUF(UF uF) {
		UF = uF;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Mesorregiao() {
		
	}

	public Mesorregiao(String id, String nome, UF UF) {
		super();
		this.id = id;
		this.nome = nome;
		this.UF = UF;
	}
	
	

}
