package exceptions;




public class PersistenciaException extends Exception {

    /**
     * identificador da classe para serialização
     */
    private static final long serialVersionUID = 6208667762699491388L;

   
    public PersistenciaException() {
	// Construtor.
    }

    
    public PersistenciaException(String mensagem) {
	super(mensagem);
    }

    
    public PersistenciaException(String mensagem, Throwable causa) {
	super(mensagem, causa);
    }

  
    public PersistenciaException(Throwable causa) {
	super(causa);
    }
}