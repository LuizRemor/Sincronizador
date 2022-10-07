package br.com.sincronizador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sincronizador.entidade.Script;
import br.com.sincronizador.util.ConexaoBancoDados;

public class ExecutaCargaDAO {

	public Script script;

	public ExecutaCargaDAO() {

		this.script = new Script();

	}
	
	private String converteDataParaTextoSemHora(Date data) {
		
		SimpleDateFormat stringToDate = new SimpleDateFormat("ddMMyyyy");
		
		return stringToDate.format(data);
		
	}
	
	private String converteDataParaTextoComHora(Date data) {
		
		
		SimpleDateFormat stringToDate = new SimpleDateFormat("ddMMyyyy HHmmss");
		
		return stringToDate.format(data);
		
	}

	public void executa(String chave, String banco) {

		String modo = script.retornaTipoAtualizacao(chave);

		if (modo.equals("SEMPAGINAR")) {

			this.executaSemPaginar(chave, banco);

		} else {

			this.executaPaginando(chave, banco);

		}

	}

	private void executaSemPaginar(String chave, String banco) {

		String consulta = script.retornaScriptSelectSemPaginar(chave);
		String comando = null;

		List<String> listaComandos = new ArrayList<String>();

		Connection conexao = ConexaoBancoDados.getInstance().getConexao(banco);

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(consulta);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				comando = script.retornaScriptInsertUpdate(chave);

				if (chave.equals("FILIAL")) {

					// '[ID]','[EMPRESA]','[CODIGO]',[CIDADEIBGE],'[RAZAOSOCIAL]'
					comando = comando.replace("[ID]", resultSet.getString(1));
					comando = comando.replace("[EMPRESA]", resultSet.getString(2));
					comando = comando.replace("[CODIGO]", resultSet.getString(3));
					comando = comando.replace("[CIDADEIBGE]", String.valueOf(resultSet.getLong(4)));
					comando = comando.replace("[RAZAOSOCIAL]", resultSet.getString(5));


				} else if (chave.equals("CLIENTE")) {
					
					// '[ID]','[EMPRESA]','[CODIGO]','[BAIRRO]',[CODIGOIBGE],'[RAMO]','[CATEGORIA]',TO_DATE('[DTCADASTRO]','ddmmyyyy'),TO_TIMESTAMP('[DTULTIMAALTERACAO]','ddmmyyyy hh24miss'),[ATIVO]) 
					comando = comando.replace("[ID]", resultSet.getString(1));
					comando = comando.replace("[EMPRESA]", resultSet.getString(2));
					comando = comando.replace("[CODIGO]", resultSet.getString(3));
					comando = comando.replace("[BAIRRO]", resultSet.getString(4));
					comando = comando.replace("[CIDADEIBGE]", String.valueOf(resultSet.getLong(5)));
					comando = comando.replace("[RAMO]", resultSet.getString(6));
					comando = comando.replace("[CATEGORIA]", resultSet.getString(7));
					comando = comando.replace("[DTCADASTRO]", this.converteDataParaTextoSemHora(resultSet.getDate(8)));
					comando = comando.replace("[DTULTIMAALTERACAO]", this.converteDataParaTextoComHora(resultSet.getDate(9)));
					comando = comando.replace("[ATIVO]", String.valueOf(resultSet.getLong(10)));
					
				}  else if (chave.equals("PRODUTO")) {
					
					// '[ID]','[EMPRESA]','[CODIGO]',TO_DATE('[DTCADASTRO]','ddmmyyyy'),[CODIGOBARRA],'[FORNECEDOR]','[CODIGOFABRICA]','[DEPARTAMENTO]','[SECAO]','[MARCA]','[TIPO]',[ATIVO],TO_TIMESTAMP('[DTULTIMAALTERACAO]','ddmmyyyy hh24miss')
					comando = comando.replace("[ID]", resultSet.getString(1));
					comando = comando.replace("[EMPRESA]", resultSet.getString(2));
					comando = comando.replace("[CODIGO]", resultSet.getString(3));
					comando = comando.replace("[DESCRICAO]", resultSet.getString(4));
					comando = comando.replace("[DTCADASTRO]", this.converteDataParaTextoSemHora(resultSet.getDate(5)));
					comando = comando.replace("[CODIGOBARRA]", String.valueOf(resultSet.getLong(6)));
					comando = comando.replace("[FORNECEDOR]", resultSet.getString(7));
					comando = comando.replace("[CODIGOFABRICA]", resultSet.getString(8));
					comando = comando.replace("[DEPARTAMENTO]", resultSet.getString(9));
					comando = comando.replace("[SECAO]", resultSet.getString(10));
					comando = comando.replace("[MARCA]", resultSet.getString(11));
					comando = comando.replace("[TIPO]", resultSet.getString(12));
					comando = comando.replace("[ATIVO]", String.valueOf(resultSet.getLong(13)));
					comando = comando.replace("[DTULTIMAALTERACAO]", this.converteDataParaTextoComHora(resultSet.getDate(14)));
					
				} else if (chave.equals("LOCALIZACAO")) {
					
					comando = comando.replace("[ID]", resultSet.getString(1));
					comando = comando.replace("[UF]", resultSet.getString(2));
					comando = comando.replace("[NOME]", resultSet.getString(3));
					comando = comando.replace("[CAPITAL]", resultSet.getString(4));
					comando = comando.replace("[LONGITUDE]", resultSet.getString(5));
					comando = comando.replace("[LATITUDE]", resultSet.getString(6));
					comando = comando.replace("[MICROREGIAO]", resultSet.getString(7));
					comando = comando.replace("[MESOREGIAO]", resultSet.getString(8));
					comando = comando.replace("[REGIAO]", resultSet.getString(9));
					comando = comando.replace("[ESTADO]", resultSet.getString(10));
					comando = comando.replace("[POPULACAO]", resultSet.getString(11));					
					
				}
				
				listaComandos.add(comando);

			}

			resultSet.close();
			preparedStatement.close();
			
			conexao = ConexaoBancoDados.getInstance().getConexao("G4");
			
			conexao.setAutoCommit(false);
			
			Statement statement = conexao.createStatement();

			this.gravaAlteracoes(listaComandos, statement);
			
			conexao.commit();
			statement.close();
			conexao.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void executaPaginando(String chave, String banco) {

	}

	private void gravaAlteracoes(List<String> listaComandos, Statement statement) {

		
		try {

			for (String linha : listaComandos) {
				
				statement.addBatch(linha);
				
			}

			statement.executeBatch();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

}
