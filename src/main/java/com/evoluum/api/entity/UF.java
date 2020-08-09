package com.evoluum.api.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UF implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty
	private String id;
	@JsonProperty
	private String sigla;
	@JsonProperty
	private String nome;
	@JsonIgnore
	private Regiao regiao;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Regiao getRegiao() {
		return regiao;
	}
	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}
	public UF(String id, String sigla, String nome, Regiao regiao) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
		this.regiao = regiao;
	}
	
	public UF() {
		
	}
	
	
}
