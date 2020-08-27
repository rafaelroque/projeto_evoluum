package com.evoluum.api.retorno;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.evoluum.api.to.CidadeEstadoTO;
import com.evoluum.api.to.MontagemRelatorioTO;
import com.evoluum.api.util.RelatorioUtil;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;

public class ProcessaRetornoPdf implements ProcessaRetorno {

	@Override
	public ResponseEntity<?> processar(List<CidadeEstadoTO> lista) throws DocumentException {
		ByteArrayInputStream bis =  RelatorioUtil.generatePdfReport(new MontagemRelatorioTO(6, 
				80, new int[]{2, 4, 4,6,6,6}, FontFactory.HELVETICA_BOLD, Element.ALIGN_CENTER, 
				Arrays.asList("Id Estado","Sigla Estado","Regiao","Cidade","Mesorregiao","Nome Formatado")), lista);


		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listagem.pdf");
		
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));


	}

}
