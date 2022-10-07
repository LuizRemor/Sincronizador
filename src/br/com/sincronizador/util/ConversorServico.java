package br.com.sincronizador.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class ConversorServico {

	public Date converteParaData(String paramentro) {

		Date retorno = null;

		try {

			SimpleDateFormat stringToDate = new SimpleDateFormat("yyyy-MM-dd");
			retorno = new Date(stringToDate.parse(paramentro).getTime());

		} catch (Exception e) {

			retorno = null;

		}

		return retorno;

	}

	public Long converteParaLong(String paramentro) {
		
		Long retorno = null;
		
		try {

			retorno = Long.valueOf(paramentro);

		} catch (Exception e) {

			retorno = null;

		}
		
		return retorno;

	}
	
	
	public BigDecimal converteParaBigdecimal(String paramentro) {
		
		BigDecimal retorno = null;
		
		try {

			retorno = new BigDecimal(paramentro);

		} catch (Exception e) {

			retorno = null;

		}
		
		return retorno;

	}

}
