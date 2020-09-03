package com.evoluum.api.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.evoluum.api.entity.Cidade;
import com.evoluum.api.entity.Estado;
import com.evoluum.api.exception.ListagemException;
import com.evoluum.api.util.Constantes;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class CidadeService {

	private static final Logger LOGGER  = LogManager.getLogger(CidadeService.class);

	@Cacheable("cidades")
	@HystrixCommand(fallbackMethod = "getFallbackCidades", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")
	})
	public Set<Cidade> getCidades(Estado estado) throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		String urlCidade = Constantes.API_CIDADES_POR_UF.replace(Constantes.PLACEHOLDER_UF, estado.getSigla());
		Set<Cidade> cidades = objectMapper.readValue(new URL(urlCidade), new TypeReference<Set<Cidade>>(){});
		return cidades;
	}

	public Set<Cidade> getFallbackCidades(Estado estado , Throwable e) throws ListagemException {
		LOGGER.info("Executando fallback");
		LOGGER.error("Erro ao acessar endpoint de Cidades", e);
		throw new ListagemException();
	}

	@Cacheable("idCidade")
	@HystrixCommand(fallbackMethod = "getFallBackreturnCityId", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")
	})
	public String returnCityId(String nomeCidade) throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String urlCidade = Constantes.API_CIDADES;
		Set<Cidade> cidades = objectMapper.readValue(new URL(urlCidade), new TypeReference<Set<Cidade>>(){});

		return cidades.stream()
				.filter(c-> c.getNome().equals(nomeCidade))
				.collect(Collectors.toList())
				.get(0)
				.getId();
	}

	public String getFallBackreturnCityId(String nomeCidade) {
		return "0";
	}

}
