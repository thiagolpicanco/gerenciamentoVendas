package enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author thiago.picanco
 *
 */
public enum TamanhoCalcadosEnum {

	CALCADO_34("34", "Tamanho 34 Calçados"), CALCADO_35("35", "Tamanho 35 Calçados"), CALCADO_36("36",
			"Tamanho 36 Calçados"), CALCADO_37("37", "Tamanho 37 Calçados"), CALCADO_38("38",
					"Tamanho 38 Calçados"), CALCADO_39("39", "Tamanho 39 Calçados"), CALCADO_40("40",
							"Tamanho 40 Calçados"), CALCADO_41("41", "Tamanho 41 Calçados"), CALCADO_42("42",
									"Tamanho 42 Calçados"), CALCADO_44("44", "Tamanho 44 Calçados");

	private String letra;
	private String descricao;

	static Map<String, TamanhoCalcadosEnum> map = new HashMap<String, TamanhoCalcadosEnum>();

	static {
		for (TamanhoCalcadosEnum tamanho : TamanhoCalcadosEnum.values()) {
			map.put(tamanho.letra, tamanho);
		}
	}

	public TamanhoCalcadosEnum getByCod(String letra) {
		return map.get(letra);

	}

	private TamanhoCalcadosEnum(String letra, String descricao) {
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
