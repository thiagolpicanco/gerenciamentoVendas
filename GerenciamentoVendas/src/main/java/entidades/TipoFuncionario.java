package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import arquitetura.Entidade;

@Entity
@Table(name = "tipo_funcionario")
public class TipoFuncionario extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8000777842665330989L;

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "no_tipo")
	private String noTipoFuncionario;

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
