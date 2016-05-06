package entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import arquitetura.Entidade;

@Entity
@Table(name = "ESTOQUE")
public class Estoque extends Entidade implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4326679135747328007L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	@Column(name="no_estoque")
	private String noEstoque;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "estoque")
	private List<EstoqueProduto> listaProdutos;
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionarioResponsavel;

	public Estoque() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNoEstoque() {
		return noEstoque;
	}

	public void setNoEstoque(String noEstoque) {
		this.noEstoque = noEstoque;
	}

	public List<EstoqueProduto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<EstoqueProduto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Funcionario getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}

	public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
		this.funcionarioResponsavel = funcionarioResponsavel;
	}

}
