package br.com.sincronizador.controle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JTextArea;

import br.com.sincronizador.dao.ExecutaCargaDAO;
import br.com.sincronizador.entidade.Visao;

public class AtualizacaoControle {

	private ExecutaCargaDAO executaCargaDAO;
	private List<String> bancos;
	private List<Visao> visoes;
	private JTextArea   textArea;
	
	public AtualizacaoControle(List<String> bancos, List<Visao> visoes, JTextArea textArea) {

		executaCargaDAO = new ExecutaCargaDAO();
		this.bancos = bancos;
		this.visoes = visoes;
		this.textArea = textArea;

	}

	public void atualizaCargas(String tipoCarga, JTextArea textArea) {

		SimpleDateFormat stringToDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		this.textArea.setText("");
		
		this.textArea.setText("MODO DE EXCUÇÂO - MANUAL \n\n");
		
		for (String banco : bancos) {
			
			for (Visao visao : visoes) {
				
				if(visao.getBanco().equals(banco)) {
				
					this.textArea.append("Iniciou a " + tipoCarga + ": " + visao.getVisao().toString() + " no banco: " + banco + " as "
						+ stringToDate.format(new Date()) + "\n");

					executaCargaDAO.executa(visao.getVisao(), banco, tipoCarga, textArea);

					this.textArea.append("Finalizou a " + tipoCarga + ": " + visao.getVisao().toString() + " no banco: " + banco + " as "
						+ stringToDate.format(new Date()) + "\n");
				}
			}
		}

	}

}
