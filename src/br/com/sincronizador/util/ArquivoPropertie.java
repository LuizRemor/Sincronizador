package br.com.sincronizador.util;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ArquivoPropertie {

	private static ArquivoPropertie arquivoPropertie;
	private static Properties properties;

	private ArquivoPropertie() {

		properties = new Properties();

		FileInputStream file;

		try {

			file = new FileInputStream("./configuracao.properties");

			properties.load(file);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public static ArquivoPropertie getInstance() {

		if (arquivoPropertie == null) {

			arquivoPropertie = new ArquivoPropertie();

		}

		return arquivoPropertie;

	}

	public String retornaDiretorioEmail() {

		return properties.getProperty("e-mail.diretorio");

	}

	public String retornaSeparador() {

		return properties.getProperty("sinc.separador");

	}

	public String retornaServidor() {

		String servidor = properties.getProperty("sinc.servidor");

		return servidor.trim();

	}

	public Map<String, Object> retornaParametroBDTI() {

		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("url", properties.getProperty("bdti.url"));
		parametros.put("usuario", properties.getProperty("bdti.usuario"));
		parametros.put("senha", properties.getProperty("bdti.senha"));

		return parametros;

	}

	public Map<String, Object> retornaParametroMHub() {

		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("url", properties.getProperty("mhub.url"));
		parametros.put("usuario", properties.getProperty("mhub.usuario"));
		parametros.put("senha", properties.getProperty("mhub.senha"));

		return parametros;

	}

	public Map<String, Object> retornaParametroSiger() {

		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("url", properties.getProperty("siger.url"));
		parametros.put("usuario", properties.getProperty("siger.usuario"));
		parametros.put("senha", properties.getProperty("siger.senha"));

		return parametros;

	}

	public Map<String, Object> retornaParametroEmail() {

		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("usuario", properties.getProperty("e-mail.usuario"));
		parametros.put("senha", properties.getProperty("e-mail.senha"));
		parametros.put("e-mail", properties.getProperty("e-mail.email"));
		parametros.put("nome", properties.getProperty("e-mail.nome"));
		parametros.put("porta", properties.getProperty("e-mail.porta"));
		parametros.put("servidor", properties.getProperty("e-mail.servidor"));

		return parametros;

	}

	public Map<String, Object> retornaParametroCentralTelefonica() {

		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("url", properties.getProperty("ct.url"));
		parametros.put("usuario", properties.getProperty("ct.usuario"));
		parametros.put("senha", properties.getProperty("ct.senha"));

		return parametros;

	}

	public Integer retornaTempoVerificacaoNovaTarefa() {

		String tempo = properties.getProperty("sinc.tempo.verificanovatarefa");

		return Integer.valueOf(tempo);

	}

	public String retornaIdRobo() {

		String id = properties.getProperty("sinc.id");

		return id;

	}

	public String retornaDiretorioIntegracaoPedido() {

		String diretorio = properties.getProperty("sinc.diretorio");

		return diretorio.trim();

	}

}
