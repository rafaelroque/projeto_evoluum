package com.evoluum.api.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.evoluum.api.entity.Estado;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EstadoService {
	
	private final static String API_ESTADOS = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";

	@Cacheable("estados")
	public Set<Estado> getEstados() throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
	    Set<Estado> estados = objectMapper.readValue(new URL(API_ESTADOS), new TypeReference<Set<Estado>>(){});
	    return estados;
	}
	
}
