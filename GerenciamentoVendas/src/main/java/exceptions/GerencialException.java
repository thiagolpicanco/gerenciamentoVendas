package exceptions;

import arquitetura.Mensagem;

public class GerencialException extends Exception {
	/**
	 * identificador da classe para serialização
	 */
	private static final long serialVersionUID = 6208667762699491388L;

	private Mensagem mensagem;

	public GerencialException() {
		// TODO Auto-generated constructor stub
	}

	public GerencialException(String mensagem) {
		super(mensagem);
		this.mensagem = new Mensagem(mensagem);
	}

	public GerencialException(String mensagem, Object[] parametro) {
		super(mensagem);
		this.mensagem = new Mensagem(mensagem, parametro);
	}

	public GerencialException(String mensagem, Throwable causa) {
		super(mensagem, causa);
		this.mensagem = new Mensagem(mensagem);
	}

	public GerencialException(Throwable causa) {
		super(causa);
	}

	/**
	 * Retorna o valor do atributo mensagem.
	 *
	 * @return mensagem
	 */
	public Mensagem getMensagem() {
		return mensagem;
	}

	/**
	 * Define o valor do atributo mensagem.
	 *
	 * @param mensagem
	 *            valor a ser atribuído
	 */
	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
}
