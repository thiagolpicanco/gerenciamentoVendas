package enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author thiago.picanco
 *
 */
public enum EnumMotivoSaida {
	TROCA(1, "TROCA"), VENDA(2, "Venda");

	private Integer id;

	private String descricao;

	private EnumMotivoSaida(Integer id, String noTipoFuncionario) {
		this.id = id;
		this.descricao = noTipoFuncionario;
	}

	static Map<String, EnumMotivoSaida> map = new HashMap<String, EnumMotivoSaida>();

	static {
		for (EnumMotivoSaida descricao : EnumMotivoSaida.values()) {
			map.put(descricao.descricao, descricao);
		}
	}

	public EnumMotivoSaida getByCod(String cod) {
		return map.get(cod);

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static Map<String, EnumMotivoSaida> getMap() {
		return map;
	}

	public static void setMap(Map<String, EnumMotivoSaida> map) {
		EnumMotivoSaida.map = map;
	}

}
