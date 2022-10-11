package br.com.sincronizador.controle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JTextArea;

import br.com.sincronizador.dao.ExecutaCargaDAO;

public class AtualizacaoControle {

	private ExecutaCargaDAO executaCargaDAO;
	private List<String> bancos;
	private List<String> visoes;

	public AtualizacaoControle(List<String> bancos, List<String> visoes) {

		executaCargaDAO = new ExecutaCargaDAO();
		this.bancos = bancos;
		this.visoes = visoes;

		bancos.add("DIFERPAN");

	}

	public void atualizaCargas(String tipoCarga, JTextArea textArea) {

		SimpleDateFormat stringToDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		textArea.setText("");
		
		textArea.setText("MODO DE EXCUÇÂO - MANUAL \n\n");

		for (String banco : bancos) {

			for (String visao : visoes) {

				textArea.append("Iniciou a " + tipoCarga + ": " + visao + " no banco: " + banco + " as "
						+ stringToDate.format(new Date()) + "\n");

				executaCargaDAO.executa(visao, banco, tipoCarga, textArea);

				textArea.append("Finalizou a " + tipoCarga + ": " + visao + " no banco: " + banco + " as "
						+ stringToDate.format(new Date()) + "\n");

			}
		}

	}

}
