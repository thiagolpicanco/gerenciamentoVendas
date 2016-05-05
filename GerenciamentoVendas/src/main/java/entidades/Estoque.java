package entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="ESTOQUE")
public class Estoque extends Entidade implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4326679135747328007L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;
	@Column
	private String noEstoque;
	@OneToMany
	private List<Produto> listaProdutos;
	@Column
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

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public Funcionario getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}

	public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
		this.funcionarioResponsavel = funcionarioResponsavel;
	}

}
