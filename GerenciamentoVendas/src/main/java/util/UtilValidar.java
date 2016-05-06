/**
 * Copyright (c) 2015 Caixa Econômica Federal. Todos os direitos
 * reservados.
 *
 * Caixa Econômica Federal - caixa-nucleo
 *
 * Este software foi desenvolvido sob demanda da CAIXA e está
 * protegido por leis de direitos autorais e tratados internacionais. As
 * condições de cópia e utilização do todo ou partes dependem de autorização da
 * empresa. Cópias não são permitidas sem expressa autorização. Não pode ser
 * comercializado ou utilizado para propósitos particulares.
 *
 * Uso exclusivo da Caixa Econômica Federal. A reprodução ou distribuição não
 * autorizada deste programa ou de parte dele, resultará em punições civis e
 * criminais e os infratores incorrem em sanções previstas na legislação em
 * vigor.
 *
 * Histórico do Subversion:
 *
 * LastChangedRevision: $Revision$
 * LastChangedBy: $Author$
 * LastChangedDate: $Date$
 *
 * HeadURL: $HeadURL$
 *
 */
package util;

import java.text.DecimalFormat;



public class UtilValidar {
    
    private static final DecimalFormat DECIMAL_FORMAT_CNPJ = new DecimalFormat(
		"00000000000000");

    public static boolean validarCnpj(final Long cnpj) {
	final String strCnpj = DECIMAL_FORMAT_CNPJ.format(cnpj);
	return !cnpjPadrao(strCnpj) && cnpjValido(strCnpj);
    }

    private static boolean cnpjPadrao(final String strCnpj) {
	return "00000000000000".equals(strCnpj) || "11111111111111".equals(strCnpj) || "22222222222222".equals(strCnpj)
		|| "33333333333333".equals(strCnpj) || "44444444444444".equals(strCnpj)
		|| "55555555555555".equals(strCnpj) || "66666666666666".equals(strCnpj)
		|| "77777777777777".equals(strCnpj) || "88888888888888".equals(strCnpj)
		|| "99999999999999".equals(strCnpj);
    }

    private static boolean cnpjValido(final String strCnpj) {
	int soma = 0, dig;
	String cnpjCalc = strCnpj.substring(0, 12);
	if (strCnpj.length() != 14) {
	    return false;
	}
	final char[] chrCnpj = strCnpj.toCharArray();
	for (int i = 0; i < 4; i++) {
	    if (chrCnpj[i] - 48 >= 0 && chrCnpj[i] - 48 <= 9) {
		soma += (chrCnpj[i] - 48) * (6 - (i + 1));
	    }
	}
	for (int i = 0; i < 8; i++) {
	    if (chrCnpj[i + 4] - 48 >= 0 && chrCnpj[i + 4] - 48 <= 9) {
		soma += (chrCnpj[i + 4] - 48) * (10 - (i + 1));
	    }
	}
	dig = 11 - (soma % 11);
	cnpjCalc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
	soma = 0;
	for (int i = 0; i < 5; i++) {
	    if (chrCnpj[i] - 48 >= 0 && chrCnpj[i] - 48 <= 9) {
		soma += (chrCnpj[i] - 48) * (7 - (i + 1));
	    }
	}
	for (int i = 0; i < 8; i++) {
	    if (chrCnpj[i + 5] - 48 >= 0 && chrCnpj[i + 5] - 48 <= 9) {
		soma += (chrCnpj[i + 5] - 48) * (10 - (i + 1));
	    }
	}
	dig = 11 - (soma % 11);
	cnpjCalc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
	return strCnpj.equals(cnpjCalc);
    }

}
