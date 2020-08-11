package com.evoluum.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class BaseEntity {
	
	@JsonProperty
	protected String id;
	@JsonProperty
	protected String nome;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
