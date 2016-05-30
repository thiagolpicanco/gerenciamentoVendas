package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class ProdutoPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3878470632175721897L;
	@Column(name = "cod_produto")
	private Integer codigo;
	@Column(name = "tamanho")
	private String tamanho;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

}
