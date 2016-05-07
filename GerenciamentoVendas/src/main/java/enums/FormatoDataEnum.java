package enums;

public enum FormatoDataEnum {

	DIA_MES_ANO("Dia, Mes e Ano", "dd/MM/YYYY");

	private String desc;
	private String formato;

	private FormatoDataEnum(String desc, String formato) {
		this.desc = desc;
		this.formato = formato;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

}
