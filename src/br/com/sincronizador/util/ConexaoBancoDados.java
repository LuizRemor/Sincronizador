package br.com.sincronizador.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConexaoBancoDados {

	private static ConexaoBancoDados conexaoBancoDados;

	private ConexaoBancoDados() {

	}

	public static ConexaoBancoDados getInstance() {

		if (conexaoBancoDados == null) {
			conexaoBancoDados = new ConexaoBancoDados();
		}
		return conexaoBancoDados;
	}
	
	public Connection getConexao(String banco) {
		
		Connection conexao = null;
		
		if(banco.equals("G4")) {
		
			conexao = this.conexaoBaseG4();
			
		}else if(banco.equals("DIFERPAN")) {
			conexao = conexaoDiferpan();
		}else if(banco.equals("LOPES")) {
			conexao = conexaoLopes();
		}
		
		return conexao;
		
	}
	

	private Connection conexaoDiferpan() {

		Connection conexao = null;

		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexao = DriverManager.getConnection("jdbc:oracle:thin:@10.0.4.4:1521:DIFERPAN", 
					                              "INTRANET", 
					                              "INTR9N3TB9S3");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conexao;

	}
	
	private Connection conexaoLopes() {

		Connection conexao = null;

		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexao = DriverManager.getConnection("jdbc:oracle:thin:@lopesdist-dataunique.ddns.com.br:1521:WINT", 
					                              "G4", 
					                              "bmub2106$");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conexao;

	}
	
	
	private Connection conexaoBaseG4() {

		Connection conexao = null;

		try {
			
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection("jdbc:postgresql://10.0.4.59:5432/postgres", 
					                              "postgres", 
					                              "adfec#855@Postgre");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conexao;
		
	}

}
