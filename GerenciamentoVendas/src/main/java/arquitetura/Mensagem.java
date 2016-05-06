package arquitetura;



public class Mensagem {
    
    private String chaveMensagem;
    private Object[] parametro;
  
    public String getChaveMensagem() {
        return chaveMensagem;
    }
 
    public void setChaveMensagem(String chaveMensagem) {
        this.chaveMensagem = chaveMensagem;
    }
    
    public Object[] getParametro() {
        return parametro;
    }
    
    public void setParametro(Object[] parametro) {
        this.parametro = parametro;
    }
   
    public Mensagem(String chaveMensagem, Object[] parametro) {
	super();
	this.chaveMensagem = chaveMensagem;
	this.parametro = parametro;
    }
    
    public Mensagem(String chaveMensagem) {
	super();
	this.chaveMensagem = chaveMensagem;
    }
    
    

}
