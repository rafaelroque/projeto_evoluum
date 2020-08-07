package com.evoluum.api.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CidadeEstadoTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idEstado;
	private String siglaEstado;
	private String regiaoNome;
	private String nomeCidade;
	private String nomeMesorregiao;
	private String nomeFormatado;
	public String getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}
	public String getSiglaEstado() {
		return siglaEstado;
	}
	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}
	public String getRegiaoNome() {
		return regiaoNome;
	}
	public void setRegiaoNome(String regiaoNome) {
		this.regiaoNome = regiaoNome;
	}
	public String getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	public String getNomeMesorregiao() {
		return nomeMesorregiao;
	}
	public void setNomeMesorregiao(String nomeMesorregiao) {
		this.nomeMesorregiao = nomeMesorregiao;
	}
	public String getNomeFormatado() {
		return nomeFormatado;
	}
	public void setNomeFormatado(String nomeFormatado) {
		this.nomeFormatado = nomeFormatado;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public CidadeEstadoTO(String idEstado, String siglaEstado, String regiaoNome, String nomeCidade,
			String nomeMesorregiao, String nomeFormatado) {
		super();
		this.idEstado = idEstado;
		this.siglaEstado = siglaEstado;
		this.regiaoNome = regiaoNome;
		this.nomeCidade = nomeCidade;
		this.nomeMesorregiao = nomeMesorregiao;
		this.nomeFormatado = nomeFormatado;
	}
	
	private static List<CidadeEstadoTO> gerarListagemTeste(){
		List<CidadeEstadoTO> lista = new ArrayList<>();
		lista.add(new CidadeEstadoTO("1", "CE", "Nordeste", "Fortaleza", "Regiao 1", "Fortaleza/CE"));
		
		return lista;
	}

}
