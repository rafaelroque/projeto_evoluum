package com.evoluum.api.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.evoluum.api.entity.Cidade;
import com.evoluum.api.entity.Estado;
import com.evoluum.api.exception.ListagemException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CidadeService {
	
	private final static String API_CIDADES ="https://servicodados.ibge.gov.br/api/v1/localidades/estados/%UF%/municipios";
	private final static String PLACEHOLDER ="%UF%";
	
	private static final Logger LOGGER  = LogManager.getLogger(CidadeService.class);
	
	@Cacheable("cidades")
	@HystrixCommand(fallbackMethod = "getFallbackCidades")
	public Set<Cidade> getCidades(Estado estado) throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		String urlCidade = API_CIDADES.replace(PLACEHOLDER, estado.getSigla());
		Set<Cidade> cidades = objectMapper.readValue(new URL(urlCidade), new TypeReference<Set<Cidade>>(){});
		return cidades;
	}
	
	public Set<Cidade> getFallbackCidades(Estado estado , Throwable e) throws ListagemException {
		LOGGER.info("Executando fallback");
		LOGGER.error("Erro ao acessar endpoint de Cidades", e);
		throw new ListagemException();
	}

}
