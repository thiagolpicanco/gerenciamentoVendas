package exceptions;

import arquitetura.Mensagem;

public class ImportacaoException extends Exception {

    /**
     * identificador da classe para serialização
     */
    private static final long serialVersionUID = 6208667762699491388L;

    private Mensagem mensagem;

    public ImportacaoException() {
	// Construtor.
    }

    public ImportacaoException(String mensagem) {
	super(mensagem);
	this.mensagem = new Mensagem(mensagem);
    }

    public ImportacaoException(String mensagem, Object[] parametro) {
	super(mensagem);
	this.mensagem = new Mensagem(mensagem, parametro);
    }

    public ImportacaoException(String mensagem, Throwable causa) {
	super(mensagem, causa);
	this.mensagem = new Mensagem(mensagem);
    }

    public ImportacaoException(Throwable causa) {
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