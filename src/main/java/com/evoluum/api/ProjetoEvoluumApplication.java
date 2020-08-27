package com.evoluum.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;

import com.evoluum.api.retorno.ProcessaRetorno;
import com.evoluum.api.retorno.ProcessaRetornoPdf;

@SpringBootApplication
@EnableCaching
@EnableCircuitBreaker
public class ProjetoEvoluumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoEvoluumApplication.class, args);
	}
	
	@Bean
	public ProcessaRetorno retorno() {
		return new ProcessaRetornoPdf();
	}

}
