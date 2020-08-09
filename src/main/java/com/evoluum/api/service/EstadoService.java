package com.evoluum.api.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.evoluum.api.entity.Estado;
import com.evoluum.api.exception.ListagemException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class EstadoService {
	
	private final static String API_ESTADOS = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
	
	private static final Logger LOGGER  = LogManager.getLogger(EstadoService.class);

	@Cacheable("estados")
	@HystrixCommand(fallbackMethod = "getFallbackEstados")
	public Set<Estado> getEstados() throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
	    Set<Estado> estados = objectMapper.readValue(new URL(API_ESTADOS), new TypeReference<Set<Estado>>(){});
	    return estados;
	}
	
	public Set<Estado> getFallbackEstados(Throwable e) throws ListagemException {
		LOGGER.info("Executando fallback");
		LOGGER.error("Erro ao acessar endpoint de Estados", e);
		throw new ListagemException();
	}
	
}
