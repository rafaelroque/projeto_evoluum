package com.evoluum.api.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evoluum.api.entity.Cidade;
import com.evoluum.api.to.CidadeEstadoTO;
import com.evoluum.api.entity.Estado;
import com.evoluum.api.service.CidadeService;
import com.evoluum.api.service.EstadoService;
import com.evoluum.api.to.MontagemRelatorioTO;
import com.evoluum.api.util.RelatorioUtil;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
/**
 * 
 * @author Rafael Roque
 *
 */
@RestController
@RequestMapping("/listagem")
public class ListagemController {

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private CidadeService cidadeService; 

	private static final Logger LOGGER  = LogManager.getLogger(ListagemController.class);	

	@GetMapping("/exportar")
	public ResponseEntity<InputStreamResource> export() throws IOException, DocumentException {

		LOGGER.info("[EXPORTAR]iniciando processamento");
		long start = System.currentTimeMillis();
		List<CidadeEstadoTO> listagem = new ArrayList<>();
		Set<Estado> estados = estadoService.getEstados();

		for(Estado estado : estados) {

			Set<Cidade> cidades = cidadeService.getCidades(estado);

			for(Cidade cidade  : cidades) {
				CidadeEstadoTO to = new CidadeEstadoTO();
				to.setIdEstado(estado.getId());
				to.setSiglaEstado(estado.getSigla());
				to.setRegiaoNome(estado.getRegiao().getNome());
				to.setNomeCidade(cidade.getNome());
				to.setNomeMesorregiao(cidade.getMicrorregiao().getMesorregiao().getNome());
				to.setNomeFormatado(cidade.getNome()+"/"+estado.getSigla());
				listagem.add(to);
			}
		}

		ByteArrayInputStream bis =  RelatorioUtil.generatePdfReport(new MontagemRelatorioTO(6, 
				80, new int[]{2, 4, 4,6,6,6}, FontFactory.HELVETICA_BOLD, Element.ALIGN_CENTER, 
				Arrays.asList("Id Estado","Sigla Estado","Regiao","Cidade","Mesorregiao","Nome Formatado")), listagem);


		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listagem.pdf");

		LOGGER.info("[EXPORTAR]finalizando processamento");
		long end = System.currentTimeMillis();
		LOGGER.info(String.format("Tempo de processamento:%s segundos", (end - start) / 1000F));

		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}

	



}
