package com.evoluum.api.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.evoluum.api.entity.Cidade;
import com.evoluum.api.entity.Estado;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CidadeService {
	
	private final static String API_CIDADES ="https://servicodados.ibge.gov.br/api/v1/localidades/estados/%UF%/municipios";
	private final static String PLACEHOLDER ="%UF%";
	
	@Cacheable("cidades")
	public Set<Cidade> getCidades(Estado estado) throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		String urlCidade =API_CIDADES.replace(PLACEHOLDER, estado.getSigla());
		Set<Cidade> cidades = objectMapper.readValue(new URL(urlCidade), new TypeReference<Set<Cidade>>(){});
		return cidades;
	}

}
