package enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author thiago.picanco
 *
 */
public enum EnumMotivoEntrada {
	DEVOLUCAO(1, "Devolução"), COMPRA(2, "Compra");

	private Integer id;

	private String descricao;

	private EnumMotivoEntrada(Integer id, String noTipoFuncionario) {
		this.id = id;
		this.descricao = noTipoFuncionario;
	}

	static Map<String, EnumMotivoEntrada> map = new HashMap<String, EnumMotivoEntrada>();

	static {
		for (EnumMotivoEntrada descricao : EnumMotivoEntrada.values()) {
			map.put(descricao.descricao, descricao);
		}
	}

	public EnumMotivoEntrada getByCod(String cod) {
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

	public static Map<String, EnumMotivoEntrada> getMap() {
		return map;
	}

	public static void setMap(Map<String, EnumMotivoEntrada> map) {
		EnumMotivoEntrada.map = map;
	}

}
