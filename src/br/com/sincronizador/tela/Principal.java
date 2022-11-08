package br.com.sincronizador.tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import br.com.sincronizador.controle.AtualizacaoControle;
import br.com.sincronizador.controle.ThreadAtualizacao;
import br.com.sincronizador.entidade.Visao;

// Herança (extends) e Interface (implements)
public class Principal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JTextArea textArea;
    private JPanel painel;
    private JButton carga;
    private JButton atualizacao;

    public Principal() {

        // contrutor da classe pai super
        super("Sincronizador");

        this.setSize(590, 500);

        this.montaPainel();

        this.add(painel);

        this.setResizable(false);

        this.setVisible(true);

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        //iniciaThreadAtualizacao();

    }

    private void montaPainel() {

        painel = new JPanel();
        painel.setLayout(null);

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane jScrollPane = new JScrollPane(textArea);
        jScrollPane.setBounds(50, 50, 470, 300);

        painel.add(jScrollPane);

        JButton finalizar = new JButton("Fechar");
        finalizar.setActionCommand("BOTAO-FINALIZAR");
        finalizar.setBounds(50, 380, 150, 30);
        finalizar.addActionListener(this);

        carga = new JButton("Carga Inicial");
        carga.setActionCommand("BOTAO-CARGA");
        carga.setBounds(210, 380, 150, 30);
        carga.addActionListener(this);

        atualizacao = new JButton("Iniciar atualizações");
        atualizacao.setBounds(370, 380, 150, 30);
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

        List<String> bancos = new ArrayList<>();
        bancos.add("DIFERPAN");
        bancos.add("LOPES");

        List<Visao> visoes = new ArrayList<>();

        for (String banco : bancos) {
            if (banco.equals("DIFERPAN")) {

//                visoes.add(new Visao("DIFERPAN", "FILIAL"));
//                visoes.add(new Visao("DIFERPAN", "CLIENTE"));
//                visoes.add(new Visao("DIFERPAN", "PRODUTO"));
                visoes.add(new Visao("DIFERPAN", "LOCALIZACAO"));
//                visoes.add(new Visao("DIFERPAN", "VENDA"));
//                visoes.add(new Visao("DIFERPAN", "ENTRADA"));

            } else if (banco.equals("LOPES")) {
//                visoes.add(new Visao("LOPES", "FILIAL"));
//                visoes.add(new Visao("LOPES", "CLIENTE"));
//                visoes.add(new Visao("LOPES", "PRODUTO"));
//                visoes.add(new Visao("LOPES", "VENDA"));
//                visoes.add(new Visao("LOPES", "ENTRADA"));
            }
        }

        if (actionEvent.getActionCommand().equals("BOTAO-FINALIZAR")) {

            System.exit(0);

        } else if (actionEvent.getActionCommand().equals("BOTAO-CARGA")) {

            AtualizacaoControle atualizacaoControle = new AtualizacaoControle(bancos, visoes, this.textArea);

            atualizacaoControle.atualizaCargas("INICIAL", this.textArea);

        } else if (actionEvent.getActionCommand().equals("BOTAO-ATUALIZACAO")) {

            this.carga.setEnabled(false);
            this.atualizacao.setEnabled(false);
            ThreadAtualizacao thread = new ThreadAtualizacao(bancos, visoes, this.textArea);
            thread.start();

        }

    }

    public void iniciaThreadAtualizacao() {

        List<String> bancos = new ArrayList<>();
        bancos.add("DIFERPAN");
        bancos.add("LOPES");

        List<Visao> visoes = new ArrayList<>();

        for (String banco : bancos) {
            if (banco.equals("DIFERPAN")) {

//                visoes.add(new Visao("DIFERPAN", "FILIAL"));
//                visoes.add(new Visao("DIFERPAN", "CLIENTE"));
//                visoes.add(new Visao("DIFERPAN", "PRODUTO"));
//                visoes.add(new Visao("DIFERPAN", "LOCALIZACAO"));
//                visoes.add(new Visao("DIFERPAN", "VENDA"));
//                visoes.add(new Visao("DIFERPAN", "ENTRADA"));

            } else if (banco.equals("LOPES")) {
//                visoes.add(new Visao("LOPES", "FILIAL"));
//                visoes.add(new Visao("LOPES", "CLIENTE"));
//                visoes.add(new Visao("LOPES", "PRODUTO"));
//                visoes.add(new Visao("LOPES", "VENDA"));
//                visoes.add(new Visao("LOPES", "ENTRADA"));
            }
        }

        this.carga.setEnabled(false);
        this.atualizacao.setEnabled(false);
        ThreadAtualizacao thread = new ThreadAtualizacao(bancos, visoes, this.textArea);
        thread.start();

    }
}