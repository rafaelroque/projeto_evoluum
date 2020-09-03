package com.evoluum.api.enumeration;

import java.util.Arrays;
import java.util.Optional;

public enum EnumTipoRetorno {
	
	JSON,
	XML,
	PDF;
	
	public static EnumTipoRetorno findTipoRetorno(String tipoRetorno) {
		
		return Arrays.stream(EnumTipoRetorno.values())
				.filter(enumTipo-> enumTipo.name().equals(tipoRetorno.toUpperCase()))
				.findFirst()
				.get();
	
}
	
}
