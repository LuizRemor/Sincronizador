package br.com.sincronizador.entidade;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Script {

	public Script() {

	}

	public String retornaTipoAtualizacao(String chave) {

		if (chave.equals("FILIAL")) {
			return "SEMPAGINAR";
		}
		else if (chave.equals("CLIENTE")) {
			return "SEMPAGINAR";
		} 
		else if (chave.equals("PRODUTO")) {
			return "SEMPAGINAR";
		} 
		else if (chave.equals("LOCALIZACAO")) {
			return "SEMPAGINAR";
		}

		return "PAGINANDO";

	}

	public String retornaScriptInsertUpdate(String chave) {

		if (chave.equals("FILIAL")) {

			return " insert into g4.tb_dim_filialg4 (id, empresa, codigo, cidadecodibge, razaosocial) "
					+ " values ('[ID]','[EMPRESA]','[CODIGO]',[CIDADEIBGE],'[RAZAOSOCIAL]') " + " on conflict (id) "
					+ " do " + " update " + "    set empresa = '[EMPRESA]',    " + "         codigo = '[CODIGO]',     "
					+ "  cidadecodibge =  [CIDADEIBGE],  " + "  razaosocial   = '[RAZAOSOCIAL]' ";

		} else if (chave.equals("CLIENTE")) {

			return " insert into g4.tb_dim_clienteg4(id,empresa,codigo,bairro,cidadecodibge,ramo,categoria,dtcadastro,dtultimaalteracao,ativo) "
					+ " values('[ID]','[EMPRESA]','[CODIGO]','[BAIRRO]',[CIDADEIBGE],'[RAMO]','[CATEGORIA]',TO_DATE('[DTCADASTRO]','ddmmyyyy'),TO_TIMESTAMP('[DTULTIMAALTERACAO]','ddmmyyyy hh24miss'),[ATIVO]) "
					+ " on conflict (id) " + " do " + " update " + " set empresa       = '[EMPRESA]', "
					+ " codigo            = '[CODIGO]', " + " bairro            = '[BAIRRO]', "
					+ " cidadecodibge     =  [CIDADEIBGE], " + " ramo              = '[RAMO]', "
					+ " categoria         = '[CATEGORIA]', "
					+ " dtcadastro        = TO_DATE('[DTCADASTRO]','ddmmyyyy'), "
					+ " dtultimaalteracao = TO_TIMESTAMP('[DTULTIMAALTERACAO]','ddmmyyyy hh24miss'), "
					+ " ativo             = [ATIVO] ";

		} else if (chave.equals("PRODUTO")) {

			return " insert into g4.tb_dim_produtog4(id,empresa,filial_id,codigo,descricao,dtcadastro,codigobarraunitario,fornecedor,codigofabrica,departamento,secao,marca,tipo,ativo,dtultimaalteracao) "
					+ " values('[ID]','[EMPRESA]','[FILIAL_ID]','[CODIGO]','[DESCRICAO]',TO_DATE('[DTCADASTRO]','ddmmyyyy'),[CODIGOBARRA],'[FORNECEDOR]','[CODIGOFABRICA]','[DEPARTAMENTO]','[SECAO]','[MARCA]','[TIPO]',[ATIVO],TO_TIMESTAMP('[DTULTIMAALTERACAO]','ddmmyyyy hh24miss')) "
					+ "  on conflict (id) " + "  do " + " update " + "     set empresa             = '[EMPRESA]', "
					+ " 		codigo              = '[CODIGO]'     ,"
					+ "         filial_id           = '[FILIAL_ID]'  ,"
					+ "         descricao           = '[DESCRICAO]'  ,"
					+ " 		dtcadastro          =  TO_DATE('[DTCADASTRO]','ddmmyyyy'), "
					+ " 		codigobarraunitario =  [CODIGOBARRA]   ,"
					+ "         fornecedor          = '[FORNECEDOR]'   , "
					+ " 	    codigofabrica       = '[CODIGOFABRICA]', "
					+ " 		departamento        = '[DEPARTAMENTO] ', "
					+ "         secao               = '[SECAO]'        , "
					+ " 		marca               = '[MARCA]'        , "
					+ "         tipo                = '[TIPO]'         , "
					+ " 		ativo               =  [ATIVO]         , "
					+ " 		dtultimaalteracao   = TO_TIMESTAMP('[DTULTIMAALTERACAO]','ddmmyyyy hh24miss')";

		} else if (chave.equals("LOCALIZACAO")) {

			return " insert into g4.tb_dim_localizacaog4(id,uf,nome,capital,longitude,latitude,microregiao,mesoregiao,regiao,estado,populacao) "
					+ " values('[ID]','[UF]','[NOME]','[CAPITAL]','[LONGITUDE]','[LATITUDE]','[MICROREGIAO]','[MESOREGIAO]','[REGIAO]','[ESTADO]','[POPULACAO]') "
					+ "  on conflict (id) " + "  do " + " update " + "     set id          = '[ID]'            , "
					+ " 		uf          = '[UF]'            , " + "         nome        = '[NOME]'          , "
					+ " 		capital     = '[CAPITAL]'       , " + " 		longitude   = '[LONGITUDE]'     , "
					+ "         latitude    = '[LATITUDE]'      , " + " 	    microregiao = '[MICROREGIAO]'   , "
					+ " 		mesoregiao  = '[MESOREGIAO] '   , " + "         regiao      = '[REGIAO]'        , "
					+ " 		estado      = '[ESTADO]'        , " + "         populacao   = '[POPULACAO]'       ";

		}

		else if (chave.equals("VENDA")) {

			return "insert into g4.tb_fato_vendag4 (id,empresa,filial_id,data,nrnota,prazomedio,cliente_id,produto_id,quantidade,custo,preco,tipo,dtcancel,dtultimaalteracao)"
					+ " values ('[ID]','[EMPRESA]','[FILIAL_ID]',TO_DATE('[DATA]','ddmmyyyy'),[NRONOTA],[PRAZOMEDIO],[CLIENTE_ID],[PRODUTO_ID],"
					+ "	        [QUANTIDADE],[CUSTO],[PRECO],'[TIPO]',TO_DATE([DTCANCEL],'ddmmyyyy'),TO_TIMESTAMP('[DTULTIMAALTERACAO]','ddmmyyyy hh24miss'))"
					+ "  on conflict (id) " + "  do " + "update " + "		set id 			       = '[ID]',"
					+ "    		empresa            = '[EMPRESA]'," + "	   		filial_id  	       = '[FILIAL_ID]',"
					+ "    		data               =  TO_DATE('[DATA]','ddmmyyyy'),"
					+ "    		nrnota             = [NRONOTA]," + "    		prazomedio         = [PRAZOMEDIO],"
					+ "    		cliente_id         = [CLIENTE_ID]," + "    		produto_id         = [PRODUTO_ID],"
					+ "    		quantidade         = [QUANTIDADE]," + "    		custo              = [CUSTO],"
					+ "    		preco              = [PRECO]," + "    		tipo               = '[TIPO]',"
					+ "    		dtcancel           = TO_DATE([DTCANCEL],'ddmmyyyy'),"
					+ "    		dtultimaalteracao  = TO_TIMESTAMP('[DTULTIMAALTERACAO]','ddmmyyyy hh24miss')";

		}

		else if (chave.equals("ENTRADA")) {

			return "insert into g4.tb_fato_entradag4 (  id," + "empresa," + "filial_id," + "dtemissao," + "nronota,"
					+ "parcelamento," + "produto_id," + "quantidade," + "precocompra," + "vlcredicms," + "tipo,"
					+ "dtcancel," + "dtultimaalteracao)"
					+ "values ('[ID]','[EMPRESA]','[FILIAL_ID]',TO_DATE('[DTEMISSAO]','ddmmyyyy'),[NRONOTA],'[PARCELAMENTO]',[PRODUTO_ID],"
					+ "[QUANTIDADE],[PRECOCOMPRA],[VLCREDICMS],'[TIPO]',TO_DATE([DTCANCEL],'ddmmyyyy'),TO_TIMESTAMP('[DTULTIMAALTERACAO]','ddmmyyyy hh24miss'))"
					+ "on conflict (id) " + "do " + "update " + "set id 			      = '[ID]',"
					+ "empresa            = '[EMPRESA]'," + "filial_id  	      = '[FILIAL_ID]',"
					+ "dtemissao          =  TO_DATE('[DTEMISSAO]','ddmmyyyy')," + "nronota            = [NRONOTA],"
					+ "parcelamento       = '[PARCELAMENTO]'," + "produto_id         = [PRODUTO_ID],"
					+ "quantidade         = [QUANTIDADE]," + "precocompra        = [PRECOCOMPRA],"
					+ "vlcredicms         = [VLCREDICMS]," + "tipo               = '[TIPO]',"
					+ "dtcancel           = TO_DATE([DTCANCEL],'ddmmyyyy'),"
					+ "dtultimaalteracao  = TO_TIMESTAMP('[DTULTIMAALTERACAO]','ddmmyyyy hh24miss')";

		}

		return chave;

	}

	public String retornaScriptSelectPaginando(String chave) {

		return null;
	}

	public String retornaScript(String chave, String tipoCarga) {

		if (tipoCarga.equals("INICIAL")) {

			if (chave.equals("FILIAL")) {
				return "SELECT * FROM V_DIMG4_FILIAL";
			} 
			else if (chave.equals("CLIENTE")) {
				return "SELECT * FROM V_DIMG4_CLIENTE";
			} 
			else if (chave.equals("PRODUTO")) {
				return "SELECT * FROM V_DIMG4_PRODUTO";
			}
			else if (chave.equals("LOCALIZACAO")) {
				return "SELECT * FROM V_DIMG4_LOCALIZACAO";
			}
			else if (chave.equals("VENDA")) {
				return "SELECT * FROM V_FATOG4_VENDA WHERE 1 = 1 ";
			} 
			else if (chave.equals("ENTRADA")) {
				return "SELECT * FROM V_FATOG4_ENTRADA WHERE 1 = 1 ";
			}

		} else {
			
			if (chave.equals("FILIAL")) {
				return "SELECT * FROM V_DIMG4_FILIAL";
			} 
			else if (chave.equals("CLIENTE")) {
				return "SELECT * FROM V_DIMG4_CLIENTE";
			} 
			else if (chave.equals("PRODUTO")) {
				return "SELECT * FROM V_DIMG4_PRODUTO";
			} 
			else if (chave.equals("LOCALIZACAO")) {
				return "SELECT * FROM V_DIMG4_LOCALIZACAO";
			} 
			else if (chave.equals("VENDA")) {
				return "SELECT * FROM V_FATOG4_VENDA WHERE 1 = 1 AND V_FATOG4_VENDA.DATA >= TRUNC(SYSDATE-4)";
			} 
			else if (chave.equals("ENTRADA")) {
				return "SELECT * FROM V_FATOG4_ENTRADA WHERE 1 = 1 AND V_FATOG4_ENTRADA.DTEMISSAO >= TRUNC(SYSDATE-4)";
			}
			
		}

		return chave;

	}

	public String retornaScriptSelectMenorData(String chave) {

		if (chave.equals("VENDA")) {
			return "SELECT MIN(V_FATOG4_VENDA.DATA) AS DATA AS QTDE FROM V_FATOG4_VENDA";
		} 
		else if (chave.equals("ENTRADA")) {
			return "SELECT MIN(V_FATOG4_ENTRADA.DTEMISSAO) AS DATA AS QTDE FROM V_FATOG4_ENTRADA";
		}

		return chave;

	}

	public String retornaScriptComData(String chave, String tipoCarga, Date data) {
		
		SimpleDateFormat stringToDate = new SimpleDateFormat("ddMMyyyy");
		String consulta = this.retornaScript(chave, tipoCarga);

		if (chave.equals("VENDA")) {
			return consulta + " AND DATA = TO_DATE('"+ stringToDate.format(data) +"','DDMMYYYY')";
		} 
		else if (chave.equals("ENTRADA")) {
			return consulta + " AND DTEMISSAO = TO_DATE('"+ stringToDate.format(data) +"','DDMMYYYY')";
		}

		return chave;

	}

}
