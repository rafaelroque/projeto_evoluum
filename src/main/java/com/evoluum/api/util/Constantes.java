package com.evoluum.api.util;

public class Constantes {
	
	public final static String API_ESTADOS = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
	public final static String API_CIDADES ="https://servicodados.ibge.gov.br/api/v1/localidades/estados/%UF%/municipios";
	public final static String PLACEHOLDER_UF ="%UF%";
	public final static Integer QUANTIDADE_ESTADOS = 27;
	
	public final class ReturnType{
		public final static String PDF = "pdf";
		public final static String CSV = "csv";
		public final static String JSON = "json";
		public final static String XML = "xml";
	}

}
