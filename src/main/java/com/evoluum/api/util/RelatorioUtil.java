package com.evoluum.api.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.evoluum.api.to.CidadeEstadoTO;
import com.evoluum.api.to.MontagemRelatorioTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class RelatorioUtil {
	
	public static ByteArrayInputStream generatePdfReport(MontagemRelatorioTO to , List<CidadeEstadoTO> lista) throws DocumentException {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

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

		return new ByteArrayInputStream(out.toByteArray());
	}

}
