package com.evoluum.api.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.evoluum.api.entity.Cidade;
import com.evoluum.api.to.CidadeEstadoTO;
import com.evoluum.api.entity.Estado;
import com.evoluum.api.retorno.TipoRetornoFactory;
import com.evoluum.api.service.CidadeService;
import com.evoluum.api.service.EstadoService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
	
	@Autowired
	private TipoRetornoFactory tipoRetornoFactory;

	

	
	@GetMapping("/exportar")
	public ResponseEntity<?> gerarRetorno(@RequestParam(name = "type") String returnType) throws Exception{
	    return   tipoRetornoFactory
	    		.getStrategyTipoRetorno(returnType)
	    		.processar(montarRetorno());
	}
	
	private List<CidadeEstadoTO> montarRetorno() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		
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

		return listagem;
	}

	



}
