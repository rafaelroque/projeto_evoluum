package com.evoluum.api.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mesorregiao extends BaseEntity implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty
	private UF UF;


	public UF getUF() {
		return UF;
	}

	public void setUF(UF uF) {
		UF = uF;
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
