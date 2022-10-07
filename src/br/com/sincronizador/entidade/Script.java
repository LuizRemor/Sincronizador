package br.com.sincronizador.entidade;

public class Script {

	public Script() {

	}

	public String retornaTipoAtualizacao(String chave) {

		if (chave.equals("FILIAL")) {
			return "SEMPAGINAR";
		} else if (chave.equals("CLIENTE")) {
			return "SEMPAGINAR";
		} else if (chave.equals("PRODUTO")) {
			return "SEMPAGINAR";
		} else if (chave.equals("LOCALIZACAO")) {
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

			return " insert into g4.tb_dim_produtog4(id,empresa,codigo,descricao,dtcadastro,codigobarraunitario,fornecedor,codigofabrica,departamento,secao,marca,tipo,ativo,dtultimaalteracao) "
					+ " values('[ID]','[EMPRESA]','[CODIGO]','[DESCRICAO]',TO_DATE('[DTCADASTRO]','ddmmyyyy'),[CODIGOBARRA],'[FORNECEDOR]','[CODIGOFABRICA]','[DEPARTAMENTO]','[SECAO]','[MARCA]','[TIPO]',[ATIVO],TO_TIMESTAMP('[DTULTIMAALTERACAO]','ddmmyyyy hh24miss')) "
					+ "  on conflict (id) " 
					+ "  do " 
					+ " update " 
					+ "     set empresa             = '[EMPRESA]', "
					+ " 		codigo              = '[CODIGO]',     "
					+ "         descricao           = '[DESCRICAO]',  "
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
					+ "  on conflict (id) " 
					+ "  do " 
					+ " update " 
					+ "     set id          = '[ID]'            , "
					+ " 		uf          = '[UF]'            , "
					+ "         nome        = '[NOME]'          , "
					+ " 		capital     = '[CAPITAL]'       , "
					+ " 		longitude   = '[LONGITUDE]'     , " 		
					+ "         latitude    = '[LATITUDE]'      , "
					+ " 	    microregiao = '[MICROREGIAO]'   , "
					+ " 		mesoregiao  = '[MESOREGIAO] '   , "  
					+ "         regiao      = '[REGIAO]'        , " 
					+ " 		estado      = '[ESTADO]'        , " 
					+ "         populacao   = '[POPULACAO]'       ";
			
		}

		return chave;

	}

	public String retornaScriptSelectSemPaginar(String chave) {

		if (chave.equals("FILIAL")) {
			return "SELECT * FROM V_DIM_FILIALG4";
		} else if (chave.equals("CLIENTE")) {
			return "SELECT * FROM V_DIM_CLIENTEG4";
		} else if (chave.equals("PRODUTO")) {
			return "SELECT * FROM V_DIM_PRODUTOG4";
		} else if (chave.equals("LOCALIZACAO")) {
			return "SELECT * FROM V_DIM_LOCALIZACAOG4";
		}

		return chave;

	}

	public String retornaScriptSelectCount(String chave) {

		if (chave.equals("FILIAL")) {
			return "SELECT COUNT(1) AS QTDE FROM V_DIM_FILIALG4";
		} else if (chave.equals("CLIENTE")) {
			return "SELECT COUNT(1) AS QTDE FROM V_DIM_CLIENTEG4";
		} else if (chave.equals("PRODUTO")) {
			return "SELECT COUNT(1) AS QTDE FROM V_DIM_PRODUTOG4";
		} else if (chave.equals("LOCALIZACAO")) {
			return "SELECT COUNT(1) AS QTDE FROM V_DIM_LOCALIZACAOG4";
		}

		return chave;

	}

	public String retornaScriptSelectCount(String chave, Integer registroInicial, Integer registroFinal) {

		if (chave.equals("FILIAL")) {
			return "SELECT COUNT(1) AS QTDE FROM V_DIM_FILIALG4";
		}

		return chave;

	}

}
