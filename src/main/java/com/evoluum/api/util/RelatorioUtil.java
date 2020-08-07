package com.evoluum.api.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.evoluum.api.to.MontagemRelatorioTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class RelatorioUtil {
	
	 public static ByteArrayInputStream gerarRelatorio(MontagemRelatorioTO to) {

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
