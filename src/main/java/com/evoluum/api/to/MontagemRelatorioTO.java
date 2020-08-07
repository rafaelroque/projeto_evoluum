package com.evoluum.api.to;

import java.io.Serializable;
import java.util.List;

public class MontagemRelatorioTO implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numColumns;
	private int widthPercentage;
	private int[] widths;
	private String font;
	private int horizontalAlignment;
	private List<String> labelColunas;
	public int getNumColumns() {
		return numColumns;
	}
	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}
	public int getWidthPercentage() {
		return widthPercentage;
	}
	public void setWidthPercentage(int widthPercentage) {
		this.widthPercentage = widthPercentage;
	}
	public int[] getWidths() {
		return widths;
	}
	public void setWidths(int[] widths) {
		this.widths = widths;
	}
	public String getFont() {
		return font;
	}
	public void setFont(String font) {
		this.font = font;
	}
	public int getHorizontalAlignment() {
		return horizontalAlignment;
	}
	public void setHorizontalAlignment(int horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}
	public List<String> getLabelColunas() {
		return labelColunas;
	}
	public void setLabelColunas(List<String> labelColunas) {
		this.labelColunas = labelColunas;
	}
	public MontagemRelatorioTO(int numColumns, int widthPercentage, int[] widths, String font, int horizontalAlignment,
			List<String> labelColunas) {
		super();
		this.numColumns = numColumns;
		this.widthPercentage = widthPercentage;
		this.widths = widths;
		this.font = font;
		this.horizontalAlignment = horizontalAlignment;
		this.labelColunas = labelColunas;
	}
	
	

}
