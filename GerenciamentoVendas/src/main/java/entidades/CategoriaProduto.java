package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import arquitetura.Entidade;

@Entity
@Table(name = "categoria_produto")
public class CategoriaProduto extends Entidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6261897825792516039L;

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "no_categoria")
	private String noCategoria;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNoCategoria() {
		return noCategoria;
	}

	public void setNoCategoria(String noCategoria) {
		this.noCategoria = noCategoria;
	}

}
