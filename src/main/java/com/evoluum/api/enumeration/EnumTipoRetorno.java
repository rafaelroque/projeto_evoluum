package com.evoluum.api.enumeration;

public enum EnumTipoRetorno {
	
	JSON,
	XML,
	PDF;
	
	public static EnumTipoRetorno findTipoRetorno(String tipoRetorno) {
		for(EnumTipoRetorno enumeration : EnumTipoRetorno.values()) {
			if(tipoRetorno.trim().toUpperCase().equals(enumeration.name()) ){
				return enumeration;
			}
		}
		return null;
	}
	
}
