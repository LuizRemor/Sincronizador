package br.com.sincronizador.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ArquivoServico { 
	
	public List<String> lerArquivo(String path) throws IOException {

		List<String> linhas = new ArrayList<String>();
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "ISO-8859-1"));
		
		while (bufferedReader.ready()) {

			linhas.add(bufferedReader.readLine());

		}

		bufferedReader.close();

		return linhas;

	}
	
	
	public void geraArquivo(String diretorio, String nomeArquivo, List<String> linhas) throws IOException {
		
		
		    this.criaDiretorio(diretorio);
		
			File arquivo = new File(diretorio + nomeArquivo);

			FileWriter fileWriter = new FileWriter(arquivo, false);

			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (String linha : linhas) {

				bufferedWriter.write(linha);
				bufferedWriter.newLine();

			} 

			bufferedWriter.close();
			fileWriter.close();
		
	}
	
	
	public boolean criaDiretorio(String diretorio) {

		boolean retorno = true;

		File file = new File(diretorio);

		if (!file.exists()) {

			retorno = file.mkdir();

		}

		return retorno;

	}


}
