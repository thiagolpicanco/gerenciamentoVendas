package enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author thiago.picanco
 *
 */
public enum TamanhoRoupasLetrasEnum {
	PP("PP", "Muito Pequeno"), P("P", "Pequeno"), M("M", "Medio"), G("G", "Grande"), GG("GG", "Muito Grande"), EG("EG",
			"Extra grande");



	String letra;
	private String descricao;

	static Map<String, TamanhoRoupasLetrasEnum> map = new HashMap<String, TamanhoRoupasLetrasEnum>();

	static {
		for (TamanhoRoupasLetrasEnum tamanho : TamanhoRoupasLetrasEnum.values()) {
			map.put(tamanho.letra, tamanho);
		}
	}

	public TamanhoRoupasLetrasEnum getByCod(String letra) {
		return map.get(letra);

	}

	private TamanhoRoupasLetrasEnum(String letra, String descricao) {
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
