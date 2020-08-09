package com.evoluum.api.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Microrregiao implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty
	private String id;
	@JsonProperty
	private String nome;
	@JsonProperty
	private Mesorregiao mesorregiao;



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



	public Mesorregiao getMesorregiao() {
		return mesorregiao;
	}

	public void setMesorregiao(Mesorregiao mesorregiao) {
		this.mesorregiao = mesorregiao;
	}

	public Microrregiao() {

	}

	public Microrregiao(String id, String nome , Mesorregiao mesorregiao) {
		super();
		this.id = id;
		this.nome = nome;
		this.mesorregiao = mesorregiao;
	}



}
