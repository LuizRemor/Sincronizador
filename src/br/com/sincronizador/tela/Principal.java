package br.com.sincronizador.tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.com.sincronizador.controle.AtualizacaoControle;

public class Principal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextArea textArea;
	private JLabel lblSituacao;
	private JPanel painel;

	public Principal() {

		super("Sincronizador");

		this.setSize(450, 500);
		
		this.montaPainel();
		
		this.add(painel);
		
	//	this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	

	}
	
	private void montaPainel() {
		
		painel = new JPanel();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    lblSituacao = new JLabel("Situação: " + formato.format(Calendar.getInstance().getTime()));
		
		painel.add(lblSituacao);
		
		textArea = new JTextArea(20, 30);
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);
		textArea.setText("Serviços inciados com sucesso!");
		
		painel.add(scrollPane);
	
		
		JButton finalizar = new JButton("Fechar");
		finalizar.setActionCommand("BOTAO-FINALIZAR");
		finalizar.addActionListener(this);
		
		JButton carga = new JButton("Carga Inicial");
		carga.setActionCommand("BOTAO-CARGA");
		carga.addActionListener(this);
		
		JButton atualizacao = new JButton("Iniciar atualizações");
		atualizacao.setActionCommand("BOTAO-ATUALIZACAO");
		atualizacao.addActionListener(this);
		
		painel.add(finalizar);
		painel.add(carga);
		painel.add(atualizacao);
		
	}

	public static void main(String strings[]) {

		String modo = System.getProperty("mode");

		if ((modo != null) && (modo.toUpperCase().equals("SERVER"))) {

			System.out.println("### Modo Server ###");

		} else {

			new Principal(); 

		}

	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		if(actionEvent.getActionCommand().equals("BOTAO-FINALIZAR")) {
			
			System.exit(0);
			
		} else if (actionEvent.getActionCommand().equals("BOTAO-CARGA")) {
			
			AtualizacaoControle atualizacaoControle = new AtualizacaoControle();
			
			atualizacaoControle.atualizaCargas();
			
		} else if (actionEvent.getActionCommand().equals("BOTAO-ATUALIZACAO")) {
			
			
			
		}

	
	}

}
