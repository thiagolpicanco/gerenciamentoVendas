package entidades;

import javax.persistence.Column;
import javax.persistence.Id;

public class Parametro {
	@Id
	private Integer id;

	@Column
	private String nomeSistema;

	public Parametro() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeSistema() {
		return nomeSistema;
	}

	public void setNomeSistema(String nomeSistema) {
		this.nomeSistema = nomeSistema;
	}

}
