package util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * 
 * @author thiago.picanco
 *
 */
public class MensagensUtil {

	
	public static void adicionaMensagemSucesso(String texto){
		FacesMessage msg = new FacesMessage(texto);
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, msg);
		
	}
	
	public static void adicionaMensagemErro(String texto){
		FacesMessage msg = new FacesMessage(texto);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, msg);
		
	}
	
	public static void adicionaMensagemAviso(String texto){
		FacesMessage msg = new FacesMessage(texto);
		msg.setSeverity(FacesMessage.SEVERITY_WARN);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, msg);
		
	}
	
	
	
	
	
	
	
	
	
	
}
