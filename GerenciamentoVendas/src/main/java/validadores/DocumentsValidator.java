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

/**
 * Esta classe possui metodos para fazer a validacao de documentos, tais como
 * CPF, CNPJ, etc.
 * 
 * @author GS Tecnologia
 * @version 1.0
 */
public class DocumentsValidator {

    /** Atributo cpf length. */
    private static int CPF_LENGTH = 11;

    /** Atributo cnpj length. */
    private static int CNPJ_LENGTH = 14;

    /**
     * Dado um objeto String, retorna todos os digitos encontrados nele.
     * 
     * @param sNumero
     *            O CPF a ser avaliado
     * @return Os digitos do CPF
     */
    private static String retiraDigitos(String sNumero) {
	if (sNumero == null) {
	    return "";
	}

	String result = "";
	for (int nCount = 0; nCount < sNumero.length(); nCount++) {
	    try {
		int digitoCPF = Integer.valueOf(sNumero.substring(nCount, nCount + 1)).intValue();
		result += String.valueOf(digitoCPF);
	    } catch (Exception e) {
		// Significa que o caracter sendo avaliado nao e' um digito.
	    }
	}
	return result;
    }

    /**
     * Faz a validacao de um CPF.
     * 
     * @param numeroCPF
     *            O CPF a ser validado
     * @return true, se for um CPF valido
     */
    public static boolean validaCPF(String numeroCPF) {
	String sDigitos = retiraDigitos(numeroCPF);
	try {
	    if (sDigitos.length() != CPF_LENGTH) {
		return false;
	    }

	    if (Long.valueOf(sDigitos).longValue() == 0) {
		return false;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	int d1, d2;
	int digito1, digito2, resto;
	int digitoCPF;
	String nDigResult;
	d1 = d2 = 0;
	digito1 = digito2 = resto = 0;
	for (int n_Count = 1; n_Count < sDigitos.length() - 1; n_Count++) {
	    digitoCPF = Integer.valueOf(sDigitos.substring(n_Count - 1, n_Count)).intValue();
	    // --------- Multiplique a ultima casa por 2 a seguinte por 3 a
	    // seguinte por 4 e assim por diante.
	    d1 = d1 + (11 - n_Count) * digitoCPF;
	    // --------- Para o segundo digito repita o procedimento incluindo o
	    // primeiro digito calculado no passo
	    // anterior.
	    d2 = d2 + (12 - n_Count) * digitoCPF;
	}
	;
	// --------- Primeiro resto da divisão por 11.
	resto = (d1 % 11);
	// --------- Se o resultado for 0 ou 1 o digito é 0 caso contrário o
	// digito é 11 menos o resultado anterior.
	if (resto < 2)
	    digito1 = 0;
	else
	    digito1 = 11 - resto;
	d2 += 2 * digito1;
	// --------- Segundo resto da divisão por 11.
	resto = (d2 % 11);
	// --------- Se o resultado for 0 ou 1 o digito é 0 caso contrário o
	// digito é 11 menos o resultado anterior.
	if (resto < 2)
	    digito2 = 0;
	else
	    digito2 = 11 - resto;
	// --------- Digito verificador do CPF que está sendo validado.
	String nDigVerific = sDigitos.substring(sDigitos.length() - 2, sDigitos.length());
	// --------- Concatenando o primeiro resto com o segundo.
	nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
	// --------- Comparar o digito verificador do cpf com o primeiro resto +
	// o segundo resto.

	return nDigVerific.equals(nDigResult);
    }

    /**
     * Faz a validacao de um CNPJ.
     * 
     * @param numeroCNPJ
     *            O CNPJ a ser validado
     * @return true, se for um CNPJ valido
     */
    public static boolean validaCNPJ(String numeroCNPJ) {
	String sDigitos = retiraDigitos(numeroCNPJ);
	try {
	    if (sDigitos.length() != CNPJ_LENGTH) {
		return false;
	    }
	    if (Integer.valueOf(sDigitos).intValue() == 0) {
		return false;
	    }
	} catch (Exception e) {
	}

	int soma = 0, dig;
	String cnpj_calc = sDigitos.substring(0, 12);
	char[] chr_cnpj = sDigitos.toCharArray();
	// --------- Primeira parte
	for (int i = 0; i < 4; i++)
	    if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
		soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
	for (int i = 0; i < 8; i++)
	    if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
		soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
	dig = 11 - (soma % 11);
	cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
	// --------- Segunda parte
	soma = 0;
	for (int i = 0; i < 5; i++)
	    if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
		soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
	for (int i = 0; i < 8; i++)
	    if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
		soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
	dig = 11 - (soma % 11);
	cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
	return sDigitos.equals(cnpj_calc);
    }
}