package com.evoluum.api.retorno;

import java.util.Map;
import com.evoluum.api.enumeration.EnumTipoRetorno;

public class TipoRetornoFactory {

	private Map<EnumTipoRetorno, StrategyTipoRetorno> tiposRetorno;
	
	public TipoRetornoFactory(Map<EnumTipoRetorno, StrategyTipoRetorno> tiposRetorno) {
		this.tiposRetorno = tiposRetorno;
	}
	
	public StrategyTipoRetorno getStrategyTipoRetorno(String tipoRetorno) {
		return tiposRetorno.get(EnumTipoRetorno.findTipoRetorno(tipoRetorno));
	}
	
	
	
	
}
