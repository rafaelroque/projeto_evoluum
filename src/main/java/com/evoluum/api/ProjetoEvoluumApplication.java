package com.evoluum.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;

import com.evoluum.api.retorno.ProcessaRetorno;
import com.evoluum.api.retorno.ProcessaRetornoJson;
import com.evoluum.api.retorno.ProcessaRetornoPdf;
import com.evoluum.api.retorno.ProcessaRetornoXml;
import com.evoluum.api.util.Constantes;

@SpringBootApplication
@EnableCaching
@EnableCircuitBreaker
public class ProjetoEvoluumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoEvoluumApplication.class, args);
	}
	
	@Bean
	public Map<String , ? super ProcessaRetorno> mapaRetorno(){
		Map<String , ? super ProcessaRetorno> mapa = new HashMap<String ,  ProcessaRetorno>();
		mapa.put(Constantes.ReturnType.PDF, new ProcessaRetornoPdf() );
		mapa.put(Constantes.ReturnType.JSON, new ProcessaRetornoJson() );
		mapa.put(Constantes.ReturnType.XML, new ProcessaRetornoXml() );
		return mapa;
	}
	
}
