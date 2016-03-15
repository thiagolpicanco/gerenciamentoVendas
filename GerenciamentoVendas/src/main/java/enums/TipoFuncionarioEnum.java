package enums;

public enum TipoFuncionarioEnum {
	DIRETOR(1, "Diretor"), GERENTE(2, "Gerente"), VENDEDOR(3, "Vendedor"), ESTOQUISTA(
			4, "Estoquista");

	private Integer id;

	private String noTipoFuncionario;

	private TipoFuncionarioEnum(Integer id, String noTipoFuncionario) {
		this.id = id;
		this.noTipoFuncionario = noTipoFuncionario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNoTipoFuncionario() {
		return noTipoFuncionario;
	}

	public void setNoTipoFuncionario(String noTipoFuncionario) {
		this.noTipoFuncionario = noTipoFuncionario;
	}

}
