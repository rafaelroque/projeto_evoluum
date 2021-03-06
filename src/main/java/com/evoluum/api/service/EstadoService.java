
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
import com.evoluum.api.util.Constantes;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class EstadoService {
	
	
	private static final Logger LOGGER  = LogManager.getLogger(EstadoService.class);

	@Cacheable("estados")
	@HystrixCommand(fallbackMethod = "getFallbackEstados", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")
			})
	public Set<Estado> getEstados() throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
	    Set<Estado> estados = objectMapper.readValue(new URL(Constantes.API_ESTADOS), new TypeReference<Set<Estado>>(){});
	    return estados;
	}
	
	public Set<Estado> getFallbackEstados(Throwable e) throws ListagemException {
		LOGGER.info("Executando fallback");
		LOGGER.error("Erro ao acessar endpoint de Estados", e);
		throw new ListagemException();
	}
	
}
