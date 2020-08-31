package com.evoluum.api.retorno;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.evoluum.api.to.CidadeEstadoTO;

public class ProcessaRetornoJson implements StrategyTipoRetorno {

	@Override
	public ResponseEntity<?> processar(List<CidadeEstadoTO> lista) throws Exception {
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(lista);

	}

}
