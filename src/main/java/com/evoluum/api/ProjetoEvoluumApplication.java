package com.evoluum.api;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import com.evoluum.api.enumeration.EnumTipoRetorno;
import com.evoluum.api.retorno.ProcessaRetornoJson;
import com.evoluum.api.retorno.ProcessaRetornoPdf;
import com.evoluum.api.retorno.StrategyTipoRetorno;
import com.evoluum.api.retorno.TipoRetornoFactory;


@SpringBootApplication
@EnableCaching
@EnableCircuitBreaker
public class ProjetoEvoluumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoEvoluumApplication.class, args);
	}
	
	@Bean
	public TipoRetornoFactory tipoRetornoFactory() {
		Map<EnumTipoRetorno, StrategyTipoRetorno> tiposRetorno = new HashMap<>();
		tiposRetorno.put(EnumTipoRetorno.PDF, new ProcessaRetornoPdf());
		tiposRetorno.put(EnumTipoRetorno.JSON, new ProcessaRetornoJson());

		TipoRetornoFactory factory = new TipoRetornoFactory(tiposRetorno);
		return factory;
		
	}
	
}
