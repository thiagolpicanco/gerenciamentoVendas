package enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author thiago.picanco
 *
 */
public enum TamanhoRoupasNumeralEnum {

	TAMANHO_NUM_ROUPAS_34("34", "Tamanho 34 ROUPAS"), TAMANHO_NUM_ROUPAS_36("36",
			"Tamanho 36 ROUPAS"), TAMANHO_NUM_ROUPAS_38("38", "Tamanho 38 ROUPAS"), TAMANHO_NUM_ROUPAS_40("40",
					"Tamanho 40 ROUPAS"), TAMANHO_NUM_ROUPAS_42("42", "Tamanho 42 ROUPAS"), TAMANHO_NUM_ROUPAS_46("46",
							"Tamanho 46 ROUPAS"), TAMANHO_NUM_ROUPAS_48("48",
									"Tamanho 48 ROUPAS"), TAMANHO_NUM_ROUPAS_50("50",
											"Tamanho 50 ROUPAS"), TAMANHO_NUM_ROUPAS_52("52", "Tamanho 52 ROUPAS");

	String letra;
	private String descricao;

	static Map<String, TamanhoRoupasNumeralEnum> map = new HashMap<String, TamanhoRoupasNumeralEnum>();

	static {
		for (TamanhoRoupasNumeralEnum tamanho : TamanhoRoupasNumeralEnum.values()) {
			map.put(tamanho.letra, tamanho);
		}
	}

	public TamanhoRoupasNumeralEnum getByCod(String letra) {
		return map.get(letra);

	}

	private TamanhoRoupasNumeralEnum(String letra, String descricao) {
		this.letra = letra;
		this.descricao = descricao;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
