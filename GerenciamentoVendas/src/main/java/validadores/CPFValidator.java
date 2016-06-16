/**
 * Copyright (c) 2009-2011 Caixa Econômica Federal. Todos os direitos reservados.
 * 
 * Caixa Econômica Federal - SIETI - Sistema de Registro de Ocorrências Éticas - Módulo Registro de Ocorrências Éticas
 * 
 * Este programa de computador foi desenvolvido sob demanda da CAIXA e está
 * protegido por leis de direitos autorais e tratados internacionais.
 * As condições de cópia e utilização do todo ou partes dependem de autorização da
 * empresa. Cópias não são permitidas sem expressa autorização. Não pode ser 
 * comercializado ou utilizado para propósitos particulares.
 * 
 * Uso exclusivo da Caixa Econômica Federal. A reprodução ou distribuição não autorizada
 * deste programa ou de parte dele, resultará em punições civis e criminais e os
 * infratores incorrem em sanções previstas na legislação em vigor.
 * 
 * Histórico do Subversion:
 *
 * LastChangedRevision: $
 * LastChangedBy: $
 * LastChangedDate: $
 * 
 * HeadURL: $
 * 
 */

/*
 * 		- Acrescentado o metodo patternValidate
 * 		- Alterado o metodo validate, para utilizar o metodo patternValidate
 * 
 */

package validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * The Class EmailValidator.
 * 
 * @author GS Tecnologia
 * @version 1.0
 */
@FacesValidator(value="cpfValidator")
public class CPFValidator implements Validator {

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.faces.validator.Validator#validate(javax.faces.context.FacesContext
     * , javax.faces.component.UIComponent, java.lang.Object)
     */
    /**
     * Método Validate.
     * 
     * @param facesContext
     *            the faces context
     * @param uIComponent
     *            the u i component
     * @param object
     *            the object
     * @throws ValidatorException
     *             the validator exception
     */
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException {

	String enteredCPF = "";
	try {
	    enteredCPF = (String) object;

	} catch (Exception e) {
	    e.printStackTrace();
	}

	enteredCPF = enteredCPF.replace(".", "").replace("-", "");
	if (!patternValidate(enteredCPF)) {
	    FacesMessage message = new FacesMessage();
	    message.setDetail("CPF incorreto: " + enteredCPF);
	    message.setSummary("CPF incorreto: " + enteredCPF);
	    message.setSeverity(FacesMessage.SEVERITY_ERROR);
	    throw new ValidatorException(message);
	}
    }

    /**
     * Pattern validate.
     * 
     * @param CPFText
     *            the CPF text
     * @return true, if successful
     */
    public boolean patternValidate(String CPFText) {
	if (CPFText == null) {
	    return false;
	}

	if (CPFText.equals("00000000000"))
	    return false;
	if (CPFText.equals("11111111111"))
	    return false;
	if (CPFText.equals("22222222222"))
	    return false;
	if (CPFText.equals("33333333333"))
	    return false;
	if (CPFText.equals("44444444444"))
	    return false;
	if (CPFText.equals("55555555555"))
	    return false;
	if (CPFText.equals("66666666666"))
	    return false;
	if (CPFText.equals("77777777777"))
	    return false;
	if (CPFText.equals("88888888888"))
	    return false;
	if (CPFText.equals("99999999999"))
	    return false;

	return DocumentsValidator.validaCPF(CPFText);
    }
}