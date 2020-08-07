package com.evoluum.api.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
import com.evoluum.api.to.MontagemRelatorioTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
@RequestMapping("/listagem")
public class ListagemController {

private final static String API_ESTADOS = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
private final static String API_CIDADES ="https://servicodados.ibge.gov.br/api/v1/localidades/estados/%UF%/municipios";
private final static String PLACEHOLDER ="%UF%";

@GetMapping("/exportar")
public ResponseEntity<InputStreamResource>  processLista() throws IOException {
	
	List<CidadeEstadoTO> listagem = new ArrayList<>();
	ObjectMapper objectMapper = new ObjectMapper();
    Set<Estado> estados = objectMapper.readValue(new URL(API_ESTADOS), new TypeReference<Set<Estado>>(){});
    for(Estado estado : estados) {
    	
	String urlCidade =API_CIDADES.replace(PLACEHOLDER, estado.getSigla());     
	Set<Cidade> cidades = objectMapper.readValue(new URL(urlCidade), new TypeReference<Set<Cidade>>(){});
    
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
    
    ByteArrayInputStream bis =  gerarRelatorio(new MontagemRelatorioTO(6, 
 			80, new int[]{2, 4, 4,6,6,6}, FontFactory.HELVETICA_BOLD, Element.ALIGN_CENTER, 
 			Arrays.asList("Id Estado","Sigla Estado","Regiao","Cidade","MesoRregiao","Nome Formatado")), listagem);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=listagem.pdf");
	
	
	
    
    
    return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));

    
    
	
    
    
}

public static ByteArrayInputStream gerarRelatorio(MontagemRelatorioTO to , List<CidadeEstadoTO> lista) {

    Document document = new Document();
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try {

        PdfPTable table = new PdfPTable(to.getNumColumns());
        table.setWidthPercentage(to.getWidthPercentage());
        table.setWidths(to.getWidths());

        Font headFont = FontFactory.getFont(to.getFont());

        
        for(String cabecalho  : to.getLabelColunas()) {
        	PdfPCell hcell =new PdfPCell(new Phrase(cabecalho, headFont));
            hcell.setHorizontalAlignment(to.getHorizontalAlignment());
            table.addCell(hcell);
        }
        
        for(CidadeEstadoTO  obj : lista) {
        	 PdfPCell cell;

             cell = new PdfPCell(new Phrase(obj.getIdEstado().toString()));
             cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
             table.addCell(cell);

             cell = new PdfPCell(new Phrase(obj.getSiglaEstado()));
             cell.setPaddingLeft(5);
             cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell.setHorizontalAlignment(Element.ALIGN_LEFT);
             table.addCell(cell);

             cell = new PdfPCell(new Phrase(obj.getRegiaoNome()));
             cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             cell.setPaddingRight(5);
             table.addCell(cell);
             
             cell = new PdfPCell(new Phrase(obj.getNomeCidade()));
             cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             cell.setPaddingRight(5);
             table.addCell(cell);
             
            
             
             cell = new PdfPCell(new Phrase(obj.getNomeMesorregiao()));
             cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             cell.setPaddingRight(5);
             table.addCell(cell);
             
             cell = new PdfPCell(new Phrase(obj.getNomeFormatado()));
             cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             cell.setPaddingRight(5);
             table.addCell(cell);
        	
        }
       
        	            
        PdfWriter.getInstance(document, out);
        document.open();
        document.add(table);
        
        document.close();


}catch(Exception e) {
//TODO melhorar tratamento de log
System.out.println(e);
}
    
    return new ByteArrayInputStream(out.toByteArray());
}


	
}
