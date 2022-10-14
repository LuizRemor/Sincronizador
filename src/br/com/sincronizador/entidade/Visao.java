package br.com.sincronizador.entidade;

public class Visao {

	private String banco;
	private String visao;

	public Visao(String banco, String visao) {
		super();
		this.banco = banco;
		this.visao = visao;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getVisao() {
		return visao;
	}

	public void setVisao(String visao) {
		this.visao = visao;
	}

}