package util;

import java.text.SimpleDateFormat;
import java.util.Date;

import enums.FormatoDataEnum;
/**
 * 
 * @author Thiago
 *
 */
public class DataUtil {

	public static String formataData(FormatoDataEnum padrao, Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat(padrao.getFormato());
		return sdf.format(data);
	}

}
