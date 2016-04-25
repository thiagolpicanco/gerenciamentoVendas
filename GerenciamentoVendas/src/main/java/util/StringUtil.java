package br.gov.caixa.siorf.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import br.gov.caixa.siorf.helper.UtilData;

public class StringUtil {

    private static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###.###");

    public static String removeZerosEsquerda(String valor) {
	Integer valorRemovido = Integer.parseInt(valor);
	valor = valorRemovido.toString();

	return valor;
    }

    public static String adicionaZeroEsquerda(String campo, Integer tamanhoCampo) {
	StringBuilder retorno = new StringBuilder();
	Integer qtdZeros = 0;

	if (null != campo) {
	    qtdZeros = tamanhoCampo - campo.length();
	}

	if (qtdZeros > 0) {
	    for (Integer i = 1; i <= qtdZeros; i++) {
		retorno.append("0");
	    }
	    retorno.append(campo);
	    return retorno.toString();

	} else {
	    return campo;
	}

    }

    public static String formataDecimal(String value) {
	String valueFormat = null;
	value = value.toString().replace(",", ".");
	try {
	    BigDecimal valor = new BigDecimal(value);
	    valor.setScale(2, RoundingMode.HALF_UP);
	    valueFormat = DECIMAL_FORMAT.format(valor);
	    if (!valueFormat.contains(",")) {
		valueFormat = valueFormat + ",00";
	    }
	} catch (NumberFormatException e) {
	    e.printStackTrace();
	}

	return valueFormat;
    }

    public static String formatarMonetario(String valor) {
	return null != valor ? NumberFormat.getCurrencyInstance().format(Double.valueOf(valor)).replace("R$ ", "") : "";
    }

    public static String formatarCnpj(String string) {
	if (!isBlankOrNull(string)) {
	    String parte1 = string.substring(0, 2);
	    String parte2 = string.substring(2, 5);
	    String parte3 = string.substring(5, 8);
	    String parte4 = string.substring(8, 12);
	    String parte5 = string.substring(12, 14);

	    String cnpjFormatado = parte1 + "." + parte2 + "." + parte3 + "/" + parte4 + "-" + parte5;

	    return cnpjFormatado;

	} else {
	    return "";
	}
    }

    public static Boolean isBlankOrNull(String value) {
	return "".equals(value) || null == value;
    }

    

    public static StringBuilder trataNullExportaArquivo(StringBuilder linhaNaoTratada, StringBuilder linhaTratada) {
	return linhaTratada.append(linhaNaoTratada.toString().replace("null", ""));
    }

    public static String formataNumeroProposta(String nuProposta) {
	String partenuPropostaAux = "";
	if (!nuProposta.trim().equals("")) {
	    if (nuProposta.length() < 10) {
		for (int i = 0; i < (10 - nuProposta.length()); i++) {
		    partenuPropostaAux = partenuPropostaAux + "0";
		}
		for (int i = 0; i < nuProposta.length(); i++) {
		    partenuPropostaAux = partenuPropostaAux + nuProposta.charAt(i);
		}

		nuProposta = partenuPropostaAux;
	    }
	}

	return nuProposta;
    }

    public static boolean somentoNumeros(String s) {
	char[] c = s.toCharArray();
	boolean d = true;
	for (int i = 0; i < c.length; i++) {
	    if (!Character.isDigit(c[i])) {
		d = false;
		break;
	    }
	}
	return d;
    }

    public static String formataData(String data) {
	if (null != data && !data.equals("")) {
	    String ano = data.substring(0, 4);
	    String mes = data.substring(5, 7);
	    String dia = data.substring(8, 10);
	    return dia + "/" + mes + "/" + ano;
	}
	return "";
    }

    public static String formataMoeda(String valor) {
	String valorFinal = "";
	if (null != valor && !valor.equals("")) {
	    Double numeroDouble = new Double(valor);

	    DecimalFormat formatoDois = new DecimalFormat("##,###,###,##0.00",
		    new DecimalFormatSymbols(new Locale("pt", "BR")));
	    formatoDois.setMinimumFractionDigits(2);
	    formatoDois.setParseBigDecimal(true);
	    valorFinal = formatoDois.format(numeroDouble);
	}

	return valorFinal;
    }

    public static String formataMoedaRelatorio(String valor) {
	return valor.replace(".", ",");
    }

    /**
     * @author thiago.picanco
     * @param campo
     * @param qtd
     * @return
     */
    public static String retornaUltimosValoresPorIndiceInicial(String campo, int indice) {
	Integer indiceInicial = campo.length() - indice;
	String retorno = campo.substring(indiceInicial);
	return retorno;
    }

    /**
     * @author thiago.picanco
     * @param campo
     * @param tamanho
     * @return
     */

    public static String preencheComZerosEsquerda(String campo, int tamanho) {
	StringBuilder sb = new StringBuilder();

	int qtdZerosAdicionar = tamanho - campo.length();

	for (int i = 0; i < qtdZerosAdicionar; i++) {
	    sb.append("0");
	}
	sb.append(campo);
	return sb.toString();

    }

    public static String insereQuebraLinha(String linha) {
	linha += ";EOL";
	return linha;
    }

}
