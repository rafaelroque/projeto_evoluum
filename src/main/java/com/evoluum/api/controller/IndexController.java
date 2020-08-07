package com.evoluum.api.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
@RequestMapping("/index")
public class IndexController {

	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport() throws IOException {


        ByteArrayInputStream bis = gerarRelatorio();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	 public static ByteArrayInputStream gerarRelatorio() {

	        Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();

	        try {

	            PdfPTable table = new PdfPTable(3);
	            table.setWidthPercentage(60);
	            table.setWidths(new int[]{1, 3, 3});

	            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

	            PdfPCell hcell;
	            hcell = new PdfPCell(new Phrase("Id", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            hcell = new PdfPCell(new Phrase("Name", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            hcell = new PdfPCell(new Phrase("Population", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);
	            
	            PdfWriter.getInstance(document, out);
	            document.open();
	            document.add(table);
	            
	            document.close();
	
	
}catch(Exception e) {
	System.out.println(e);
}
	        
	        return new ByteArrayInputStream(out.toByteArray());
	 }
}
