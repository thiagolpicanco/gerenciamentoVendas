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
 * - Acrescentado o metodo patternValidate - Alterado o metodo validate, para utilizar o metodo patternValidate
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
@FacesValidator(value = "cnpjValidator")
public class CNPJValidator implements Validator {

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

		String enteredCNPJ = "";
		try {
			enteredCNPJ = (String) object;
		} catch (Exception e) {

		}
		if (!patternValidate(enteredCNPJ)) {
			FacesMessage message = new FacesMessage();
			message.setDetail("CNPJ incorreto: " + enteredCNPJ);
			message.setSummary("CNPJ incorreto: " + enteredCNPJ);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

	/**
	 * Pattern validate.
	 * 
	 * @param cnpjText
	 *            the CNPJ text
	 * @return true, if successful
	 */
	public boolean patternValidate(String cnpjText) {
		if (cnpjText == null) {
			return false;
		}
		return DocumentsValidator.validaCNPJ(cnpjText);
	}
}