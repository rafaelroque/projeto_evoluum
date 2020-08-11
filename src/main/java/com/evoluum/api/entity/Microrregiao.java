package com.evoluum.api.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Microrregiao extends BaseEntity implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty
	private Mesorregiao mesorregiao;

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
