package br.com.sincronizador.controle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JTextArea;

import br.com.sincronizador.dao.ExecutaCargaDAO;
import br.com.sincronizador.entidade.Visao;

public class ThreadAtualizacao extends Thread {
	
	private ExecutaCargaDAO executaCargaDAO;
	private List<String>    bancos;
	private List<Visao>    visoes;
	private JTextArea       textArea;
	private String          tipoCarga;
	private Long            intervalo;
	private Boolean         executa;
	
	public ThreadAtualizacao(List<String> bancos, List<Visao> visoes, JTextArea textArea) {
		
		executaCargaDAO = new ExecutaCargaDAO();
		this.bancos     = bancos;
		this.visoes     = visoes;
		this.textArea   = textArea;
		this.tipoCarga  = "ATUALIZACAO";
		this.executa    = true;
		// 30 minutos
		this.intervalo  = (long)30*60*1000;
		
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		
		while(this.executa) {
			
			try {
				
				SimpleDateFormat dateToString = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

				textArea.setText("MODO DE EXCUÇÂO - THREAD \n\n");

				for (String banco : bancos) {

					for (Visao visao : visoes) {
						
						if(visao.getBanco().equals(banco)) {

						textArea.append("Iniciou a " + tipoCarga + ": " + visao.getVisao().toString() + " no banco: " + banco + " as "
								+ dateToString.format(new Date()) + "\n");

						executaCargaDAO.executa(visao.getVisao(), banco, tipoCarga, textArea);

						textArea.append("Finalizou a " + tipoCarga + ": " + visao.getVisao().toString() + " no banco: " + banco + " as "
								+ dateToString.format(new Date()) + "\n");
						}
					}
				}
				
				Calendar calendario = Calendar.getInstance();
				
				calendario.add(Calendar.MILLISECOND, this.intervalo.intValue());
				
				textArea.append("\nPRÓXIMA EXECUÇÃO: " + dateToString.format(calendario.getTime()) + "\n\n");
				
				this.sleep(this.intervalo);
				
			} catch (InterruptedException e) {
			
				this.executa = false;
				textArea.setText(e.getMessage());
			
			}
			
		}
		
	}

}
