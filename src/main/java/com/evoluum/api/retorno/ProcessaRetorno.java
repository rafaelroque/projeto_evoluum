package com.evoluum.api.retorno;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.evoluum.api.to.CidadeEstadoTO;
import com.itextpdf.text.DocumentException;

public interface ProcessaRetorno {
	
	public ResponseEntity<?> processar(List<CidadeEstadoTO> lista) throws DocumentException;

}
