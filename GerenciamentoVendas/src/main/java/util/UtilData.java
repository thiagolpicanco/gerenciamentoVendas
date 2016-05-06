package util;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import enums.EnumTipoData;


/**
 * 
 * @author jonatha.chaves
 * @version 1.0.0
 *
 *          <p>
 *          UtilData
 *          </p>
 *          <p>
 *          Descrição: Classe responsável por fornecer tratamento, conversões e formatações para datas
 *          </p>
 * <br>
 *          <b>Empresa:</b> Cef - Caixa Econômica Federal
 *
 */
public class UtilData {
    /**
     * 
     */
    private static UtilMensagem UTIL_MENSAGEM = new UtilMensagem("propriedades/nucleo");

    /**
     * Formato: aaaa-MM-dd
     */
    public static final SimpleDateFormat SF_AAAA_MM_DD = new SimpleDateFormat(
	    EnumTipoData.AAAA_MM_dd.getTipoFormatoData());

    /**
     * Formato: dd/MM/aaaa
     */
    public static final SimpleDateFormat SF_dd_MM_AAAA = new SimpleDateFormat(
	    EnumTipoData.DD_MM_AAAA.getTipoFormatoData());

    /**
     * Formato: dd/MM/aaaa HH:mm:ss
     */
    public static final SimpleDateFormat SF_DD_MM_AAAA_HH_MM_SS = new SimpleDateFormat(
	    EnumTipoData.DD_MM_AAAA_HH_MM_SS.getTipoFormatoData());

    /**
     * 
     * @author jonatha.chaves
     * @verison 1.0.0
     * 
     *          <p>
     *          Método responsável por somar/subtrair a quantiade de dias {@link quantidadeDeDias} da data informada.
     *          <p>
     *
     * @param data
     * @param quantidadeDeDias
     * @return {@link Date}
     */
    public static Date somarSubtrairDias(final Date data, final int quantidadeDeDias) {
	final Calendar calendario = Calendar.getInstance();
	calendario.setTime(data);
	calendario.add(Calendar.DATE, quantidadeDeDias);
	return calendario.getTime();
    }

    
    public static void somenteData(final Calendar date) {
	date.set(Calendar.HOUR_OF_DAY, 0);
	date.set(Calendar.MINUTE, 0);
	date.set(Calendar.SECOND, 0);
	date.set(Calendar.MILLISECOND, 0);
    }

   
    public static Date somarSubtrairMinutos(final Date data, final int minutos) {
	final Calendar calendario = Calendar.getInstance();
	calendario.setTime(data);
	calendario.add(Calendar.MINUTE, minutos);
	return calendario.getTime();
    }

    
    public static int diferencaEmDiasEntreDatas(final Date dataInicial, final Date dataFinal) {
	int resultado = -1;
	if (dataInicial != null && dataFinal != null) {
	    final long tempo1 = dataInicial.getTime();
	    final long tempo2 = dataFinal.getTime();
	    final long difTempo = tempo2 - tempo1;

	    resultado = (int) ((difTempo + 60L * 60 * 1000) / (24L * 60 * 60 * 1000));
	}
	return resultado;
    }

    
    public static Integer calcularIdade(final Date dataNascimento) {
	final Calendar aux = new GregorianCalendar();
	aux.setTime(dataNascimento);
	// Cria um objeto calendar com a data atual
	final Calendar hoje = Calendar.getInstance();
	// Obtém a idade baseado no ano
	int idade = hoje.get(Calendar.YEAR) - aux.get(Calendar.YEAR);
	aux.add(Calendar.YEAR, idade);
	// se a data de hoje é antes da data de Nascimento, então diminui 1(um)
	if (hoje.before(dataNascimento)) {
	    idade--;
	}
	return idade;
    }

   
    public static Date primeiroHorarioDaDate(final Date date) {
	final Calendar aux = Calendar.getInstance();
	aux.setTime(date);
	somenteData(aux); // zera os parametros de hour,min,sec,milisec
	return aux.getTime();
    }

   
    public static Date ultimoHorarioDaData(final Date date) {
	final Calendar aux = Calendar.getInstance();
	aux.setTime(date);
	somenteData(aux); // zera os parametros de hour,min,sec,milisec
	aux.roll(Calendar.DATE, true); // vai para o dia seguinte
	aux.roll(Calendar.MILLISECOND, false); // reduz 1 milisegundo
	return aux.getTime();
    }

    
    public static boolean verificarSeDataEhMenor(final Date primeiraData, final Date segundaData) {
	boolean retorno = false;
	if (primeiraData != null && segundaData != null) {
	    retorno = primeiraData.before(segundaData);
	}
	return retorno;
    }

   
    public static boolean verificarSeDataEhMaior(final Date primeiraData, final Date segundaData) {
	boolean retorno = false;
	if (primeiraData != null && segundaData != null) {
	    retorno = primeiraData.after(segundaData);
	}
	return retorno;
    }

  

    /**
     * 
     * @author jonatha.chaves
     * @verison 1.0.0
     *
     *          <p>
     *          Método responsável por verificar se a primeira data é igual que a segunda data. Leva em consideração
     *          dd/MM/aaaa HH:mm:ss.
     *          <p>
     *
     * @param primeiraData
     * @param segundaData
     * @return {@link boolean}
     */
    public static boolean verificarSeDataEhIgual(final Date primeiraData, final Date segundaData) {
	boolean retorno = false;
	if (primeiraData != null && segundaData != null) {
	    retorno = primeiraData.equals(segundaData);
	}
	return retorno;
    }

    /**
     * @author guilherme.santos
     * @verison 1.0.0
     *
     *          <p>
     *          Método responsável por validar se o dia informado é um dia util, de segunda a sexta.
     *          <p>
     *
     * @param data
     * @return {@link boolean}
     */
    public static boolean verificarDiaUtilSemana(final Date data) {
	boolean retorno = true;
	final Calendar calendar = new GregorianCalendar();
	calendar.setTime(data);
	if ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
		|| (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
	    retorno = false;
	}
	return retorno;
    }

   
    public static boolean verificarDataContidaPeriodo(final Date dataVerificacao, final Date inicioPeriodo,
	    final Date fimPeriodo) {
	boolean resultado = false;

	if (verificarSeDataEhIgual(dataVerificacao, inicioPeriodo)
		|| verificarSeDataEhIgual(dataVerificacao, fimPeriodo)
		|| (verificarSeDataEhMaior(dataVerificacao, inicioPeriodo) && verificarSeDataEhMenor(dataVerificacao,
			fimPeriodo))) {
	    resultado = true;
	}

	return resultado;
    }

   
    public static Date converterDataSemHoras(final Date data) {
	if (data == null) {
	    return null;
	}
	final Calendar dataConvertida = Calendar.getInstance();
	dataConvertida.setTime(data);
	dataConvertida.set(Calendar.HOUR_OF_DAY, 0);
	dataConvertida.set(Calendar.MINUTE, 0);
	dataConvertida.set(Calendar.SECOND, 0);
	return dataConvertida.getTime();
    }

 
    public static String converterDataEmString(final Date data, EnumTipoData enumTipoData) {
	final SimpleDateFormat sf = new SimpleDateFormat(enumTipoData.getTipoFormatoData());
	return data == null ? null : sf.format(data);
    }

    public static String converterDataEmString(final Date data, String formato) {
	final SimpleDateFormat sf = new SimpleDateFormat(formato);
	return data == null ? null : sf.format(data);
    }

   
    public static String converteMilisegundoParaHHmmss(final Long mili) {
	return String.format(
		"%02d:%02d:%02d",
		TimeUnit.MILLISECONDS.toHours(mili),
		TimeUnit.MILLISECONDS.toMinutes(mili) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(mili)),
		TimeUnit.MILLISECONDS.toSeconds(mili)
			- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mili)));
    }

    public static int converterDataParaDia(Long dataLong) {
	dataLong = (((dataLong / 24) / 60) / 60) / 1000;
	Integer resultado = new Integer(dataLong.toString());
	return resultado;
    }

   
    public static Integer converterAnosEmDias(final Integer anos) {
	Integer dias = 0;
	if (anos != null && anos > 0) {
	    dias = anos * 365;
	}
	return dias;
    }

   
    public static Integer converterMesesEmDias(final Integer meses) {
	Integer dias = 0;
	if (meses != null && meses > 0) {
	    dias = meses * 30;
	}
	return dias;
    }

    public static int obterUltimoDiaDoMesDaData(final Date date) {
	final Calendar aux = Calendar.getInstance();
	aux.setTime(date);
	return aux.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    
    public static int obterDiaDoMes(final Date data) {
	int retorno = 0;
	if (data != null) {
	    final Calendar calendario = Calendar.getInstance();

	    calendario.setTime(data);

	    retorno = calendario.get(Calendar.DAY_OF_MONTH);
	}
	return retorno;
    }

    
    public static int obterDiasUteis(final Date initialDate, final Date finalDate) {
	int retorno = 0;
	final int totalDays = diferencaEmDiasEntreDatas(initialDate, finalDate);

	final Calendar calendar = new GregorianCalendar();

	// Setando o calendar com a Data Inicial
	calendar.setTime(initialDate);

	for (int i = 0; i <= totalDays; i++) {

	    if (!(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
		    && !(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
		retorno++;
	    }
	    calendar.add(Calendar.DATE, 1);

	}
	return retorno;
    }

    
    public static int obterMes(final Date data) {
	int retorno = 0;
	if (data != null) {
	    final Calendar calendario = Calendar.getInstance();

	    calendario.setTime(data);

	    retorno = calendario.get(Calendar.MONTH) + 1;
	}
	return retorno;
    }

    public static int obterAno(final Date data) {
	int retorno = 0;
	if (data != null) {
	    final Calendar calendario = Calendar.getInstance();

	    calendario.setTime(data);

	    retorno = calendario.get(Calendar.YEAR);
	}
	return retorno;
    }

  
    public static int obterAnoCorrente() {
	Calendar cal = Calendar.getInstance();
	return cal.get(Calendar.YEAR);
    }
}
