package br.com.sincronizador.controle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sincronizador.dao.ExecutaCargaDAO;

public class AtualizacaoControle {
	
	
	private ExecutaCargaDAO executaCargaDAO;
	private List<String> bancos;
	private List<String> visoes;
	
	public AtualizacaoControle() {
		
		executaCargaDAO = new ExecutaCargaDAO();
		bancos = new ArrayList<>();
		visoes = new ArrayList<>();
		
		bancos.add("DIFERPAN");
		
	//	visoes.add("FILIAL");
	//	visoes.add("CLIENTE");
	//	visoes.add("PRODUTO");
		visoes.add("LOCALIZACAO");
		
	}
	
	public void atualizaCargas() {
		
		SimpleDateFormat stringToDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		for (String banco : bancos) {
			
			for (String visao : visoes) {
				
				System.out.println("Iniciou: " + visao + " no banco: " + banco + " as " + stringToDate.format(new Date()));
				
				executaCargaDAO.executa(visao, banco);
				
				System.out.println("Finalizou: " + visao + " no banco: " + banco + " as " + stringToDate.format(new Date()));
				
			}
		}
	
	}

}
 