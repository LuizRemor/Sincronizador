package br.com.sincronizador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JTextArea;

import br.com.sincronizador.entidade.Script;
import br.com.sincronizador.util.ConexaoBancoDados;

public class ExecutaCargaDAO {

	public Script script;

	public ExecutaCargaDAO() {

		this.script = new Script();

	}

	private String converteDataParaTextoSemHora(Date data) {

		if (data == null) {

			return "";

		} else {

			SimpleDateFormat stringToDate = new SimpleDateFormat("ddMMyyyy");

			return stringToDate.format(data);
		}

	}

	private String converteDataParaTextoComHora(Date data) {

		if (data == null) {

			return "";

		} else {
			SimpleDateFormat stringToDate = new SimpleDateFormat("ddMMyyyy HHmmss");

			return stringToDate.format(data);
		}
	}

	public void executa(String chave, String banco, String tipoCarga, JTextArea textArea) {

		String modo = script.retornaTipoAtualizacao(chave);

		if (modo.equals("SEMPAGINAR")) {

			this.executaSemPaginar(chave, banco, tipoCarga);

		} else {

			this.executaPaginando(chave, banco, tipoCarga, textArea);

		}

	}

	private void executaSemPaginar(String chave, String banco, String tipoCarga) {

		String consulta = script.retornaScript(chave, tipoCarga);
		String comando = null;

		List<String> listaComandos = new ArrayList<String>();

		Connection conexao = ConexaoBancoDados.getInstance().getConexao(banco);

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(consulta);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				comando = script.retornaScriptInsertUpdate(chave);

				if (chave.equals("FILIAL")) {

					comando = comando.replace("[ID]", resultSet.getString(1));
					comando = comando.replace("[EMPRESA]", resultSet.getString(2));
					comando = comando.replace("[CODIGO]", resultSet.getString(3));
					comando = comando.replace("[CIDADEIBGE]", String.valueOf(resultSet.getLong(4)));
					comando = comando.replace("[RAZAOSOCIAL]", resultSet.getString(5));

				} else if (chave.equals("CLIENTE")) {

					comando = comando.replace("[ID]", resultSet.getString(1));
					comando = comando.replace("[EMPRESA]", resultSet.getString(2));
					comando = comando.replace("[CODIGO]", resultSet.getString(3));
					comando = comando.replace("[BAIRRO]", resultSet.getString(4));
					comando = comando.replace("[CIDADEIBGE]", String.valueOf(resultSet.getLong(5)));
					comando = comando.replace("[RAMO]", resultSet.getString(6));
					comando = comando.replace("[CATEGORIA]", resultSet.getString(7));
					comando = comando.replace("[DTCADASTRO]", this.converteDataParaTextoSemHora(resultSet.getDate(8)));
					comando = comando.replace("[DTULTIMAALTERACAO]",
							this.converteDataParaTextoComHora(resultSet.getDate(9)));
					comando = comando.replace("[ATIVO]", String.valueOf(resultSet.getLong(10)));

				} else if (chave.equals("PRODUTO")) {

					comando = comando.replace("[ID]", resultSet.getString(1));
					comando = comando.replace("[EMPRESA]", resultSet.getString(2));
					comando = comando.replace("[FILIAL_ID]", resultSet.getString(3));
					comando = comando.replace("[CODIGO]", String.valueOf(resultSet.getLong(4)));
					comando = comando.replace("[DESCRICAO]", resultSet.getString(5));
					comando = comando.replace("[DTCADASTRO]", this.converteDataParaTextoSemHora(resultSet.getDate(6)));
					comando = comando.replace("[CODIGOBARRA]", String.valueOf(resultSet.getLong(7)));
					comando = comando.replace("[FORNECEDOR]", resultSet.getString(8));
					comando = comando.replace("[CODIGOFABRICA]", resultSet.getString(9));
					comando = comando.replace("[DEPARTAMENTO]", resultSet.getString(10));
					comando = comando.replace("[SECAO]", resultSet.getString(11));
					comando = comando.replace("[MARCA]", resultSet.getString(12));
					comando = comando.replace("[TIPO]", resultSet.getString(13));
					comando = comando.replace("[ATIVO]", String.valueOf(resultSet.getLong(14)));
					comando = comando.replace("[DTULTIMAALTERACAO]",
							this.converteDataParaTextoComHora(resultSet.getDate(15)));

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
					comando = comando.replace("[PAIS]", resultSet.getString(12));

				} else if (chave.equals("VENDA")) {

					comando = comando.replace("[ID]", resultSet.getString(1));
					comando = comando.replace("[EMPRESA]", resultSet.getString(2));
					comando = comando.replace("[FILIAL_ID]", resultSet.getString(3));
					comando = comando.replace("[DATA]", this.converteDataParaTextoSemHora(resultSet.getDate(4)));
					comando = comando.replace("[NRONOTA]", String.valueOf(resultSet.getLong(5)));
					comando = comando.replace("[PRAZOMEDIO]", String.valueOf(resultSet.getLong(6)));
					comando = comando.replace("[CLIENTE_ID]", String.valueOf(resultSet.getLong(7)));
					comando = comando.replace("[PRODUTO_ID]", String.valueOf(resultSet.getLong(8)));
					comando = comando.replace("[QUANTIDADE]", String.valueOf(resultSet.getDouble(9)));
					comando = comando.replace("[CUSTO]", String.valueOf(resultSet.getDouble(10)));
					comando = comando.replace("[PRECO]", String.valueOf(resultSet.getDouble(11)));
					comando = comando.replace("[TIPO]", resultSet.getString(12));
					Date dtcancel = resultSet.getDate(13);

					String dataCancelFormatada;

					if (dtcancel == null) {

						dataCancelFormatada = "null";
					}

					else {
						dataCancelFormatada = this.converteDataParaTextoSemHora(resultSet.getDate(13));
						dataCancelFormatada = "'" + dataCancelFormatada + "'";
					}

					comando = comando.replace("[DTCANCEL]", dataCancelFormatada);
					comando = comando.replace("[DTULTIMAALTERACAO]",
							this.converteDataParaTextoSemHora(resultSet.getDate(14)));

				}

				else if (chave.equals("ENTRADA")) {

					comando = comando.replace("[ID]", resultSet.getString(1));
					comando = comando.replace("[EMPRESA]", resultSet.getString(2));
					comando = comando.replace("[FILIAL_ID]", resultSet.getString(3));
					comando = comando.replace("[DTEMISSAO]", this.converteDataParaTextoSemHora(resultSet.getDate(4)));
					comando = comando.replace("[NRONOTA]", String.valueOf(resultSet.getLong(5)));
					comando = comando.replace("[PARCELAMENTO]", resultSet.getString(6));
					comando = comando.replace("[PRODUTO_ID]", String.valueOf(resultSet.getLong(7)));
					comando = comando.replace("[QUANTIDADE]", String.valueOf(resultSet.getDouble(8)));
					comando = comando.replace("[PRECOCOMPRA]", String.valueOf(resultSet.getDouble(9)));
					comando = comando.replace("[VLCREDICMS]", String.valueOf(resultSet.getDouble(10)));
					comando = comando.replace("[TIPO]", resultSet.getString(11));
					Date dtcancel = resultSet.getDate(12);

					String dataCancelFormatada;

					if (dtcancel == null) {
						dataCancelFormatada = "null";
					} else {
						dataCancelFormatada = this.converteDataParaTextoSemHora(resultSet.getDate(12));
						dataCancelFormatada = "'" + dataCancelFormatada + "'";
					}

					comando = comando.replace("[DTCANCEL]", dataCancelFormatada);
					comando = comando.replace("[DTULTIMAALTERACAO]",
							this.converteDataParaTextoSemHora(resultSet.getDate(13)));

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

	private void executaPaginando(String chave, String banco, String tipoCarga, JTextArea textArea) {

		SimpleDateFormat dateToString = new SimpleDateFormat("dd/MM/yyyy");

		Calendar dataIncremental = Calendar.getInstance();

		if (tipoCarga.equals("INICIAL")) {
			dataIncremental.set(2020, 0, 1);
		}else {
			dataIncremental.add(Calendar.DAY_OF_YEAR, -5);
		}

		Calendar dataAtual = Calendar.getInstance();

		List<String> listaComandos = null;

		try {

			Connection conexaoLeitura = ConexaoBancoDados.getInstance().getConexao(banco);

			Connection conexaoGravacao = ConexaoBancoDados.getInstance().getConexao("G4");

			Statement statement = conexaoGravacao.createStatement();

			Statement statementLeitura = conexaoLeitura.createStatement();

			conexaoGravacao.setAutoCommit(false);

			String consulta = null;
			ResultSet resultSet = null;
			String comando = null;

			while (dataAtual.compareTo(dataIncremental) >= 0) {

				listaComandos = new ArrayList<>();

				consulta = script.retornaScriptComData(chave, tipoCarga, dataIncremental.getTime());

				resultSet = statementLeitura.executeQuery(consulta);

				textArea.append(
						"Executando: " + chave + " data: " + dateToString.format(dataIncremental.getTime()) + "\n");

				while (resultSet.next()) {

					comando = script.retornaScriptInsertUpdate(chave);

					if (chave.equals("VENDA")) {

						comando = comando.replace("[ID]", resultSet.getString(1));
						comando = comando.replace("[EMPRESA]", resultSet.getString(2));
						comando = comando.replace("[FILIAL_ID]", resultSet.getString(3));
						comando = comando.replace("[DATA]", this.converteDataParaTextoSemHora(resultSet.getDate(4)));
						comando = comando.replace("[NRONOTA]", String.valueOf(resultSet.getLong(5)));
						comando = comando.replace("[PRAZOMEDIO]", String.valueOf(resultSet.getLong(6)));
						comando = comando.replace("[CLIENTE_ID]", String.valueOf(resultSet.getLong(7)));
						comando = comando.replace("[PRODUTO_ID]", String.valueOf(resultSet.getLong(8)));
						comando = comando.replace("[QUANTIDADE]", String.valueOf(resultSet.getLong(9)));
						comando = comando.replace("[CUSTO]", String.valueOf(resultSet.getDouble(10)));
						comando = comando.replace("[PRECO]", String.valueOf(resultSet.getDouble(11)));
						comando = comando.replace("[TIPO]", resultSet.getString(12));
						Date dtcancel = resultSet.getDate(13);
						String dataCancelFormatada;

						if (dtcancel == null) {

							dataCancelFormatada = "null";
						}

						else {
							dataCancelFormatada = this.converteDataParaTextoSemHora(resultSet.getDate(13));
							dataCancelFormatada = "'" + dataCancelFormatada + "'";
						}

						comando = comando.replace("[DTCANCEL]", dataCancelFormatada);
						comando = comando.replace("[DTULTIMAALTERACAO]",
								this.converteDataParaTextoSemHora(resultSet.getDate(14)));

					}

					else if (chave.equals("ENTRADA")) {

						comando = comando.replace("[ID]", resultSet.getString(1));
						comando = comando.replace("[EMPRESA]", resultSet.getString(2));
						comando = comando.replace("[FILIAL_ID]", resultSet.getString(3));
						comando = comando.replace("[DTEMISSAO]",
								this.converteDataParaTextoSemHora(resultSet.getDate(4)));
						comando = comando.replace("[NRONOTA]", String.valueOf(resultSet.getLong(5)));
						comando = comando.replace("[PARCELAMENTO]", resultSet.getString(6));
						comando = comando.replace("[PRODUTO_ID]", String.valueOf(resultSet.getLong(7)));
						comando = comando.replace("[QUANTIDADE]", String.valueOf(resultSet.getLong(8)));
						comando = comando.replace("[PRECOCOMPRA]", String.valueOf(resultSet.getDouble(9)));
						comando = comando.replace("[VLCREDICMS]", String.valueOf(resultSet.getDouble(10)));
						comando = comando.replace("[TIPO]", resultSet.getString(11));

						Date dtcancel = resultSet.getDate(12);
						String dataCancelFormatada;
						if (dtcancel == null) {
							dataCancelFormatada = "null";
						} else {
							dataCancelFormatada = this.converteDataParaTextoSemHora(resultSet.getDate(12));
							dataCancelFormatada = "'" + dataCancelFormatada + "'";
						}

						comando = comando.replace("[DTCANCEL]", dataCancelFormatada);
						comando = comando.replace("[DTULTIMAALTERACAO]",
								this.converteDataParaTextoSemHora(resultSet.getDate(13)));

					}

					listaComandos.add(comando);

				}

				this.gravaAlteracoes(listaComandos, statement);

				conexaoGravacao.commit();

				dataIncremental.add(Calendar.DAY_OF_YEAR, 1);

			}

			statement.close();

			conexaoLeitura.close();

			conexaoGravacao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

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
