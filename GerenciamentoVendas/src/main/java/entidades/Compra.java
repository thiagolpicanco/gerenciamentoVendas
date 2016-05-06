package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import arquitetura.Entidade;

@Entity
@Table(name = "COMPRA")
@SequenceGenerator(name = "sq_compra", sequenceName = "sq_compra", allocationSize = 1, initialValue = 1)

public class Compra extends Entidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2597489331441641825L;
	@Id
	@GeneratedValue(generator="sq_compra")
	@Column
	private Integer id;
	@OneToMany
	@JoinTable(name = "compra_produto", joinColumns = {
			@JoinColumn(name = "compra_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "produto_id", referencedColumnName = "id") })
	private List<Produto> listaProdutos;
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private Fornecedor fornecedor;
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionarioResponsavel;
	@Column(name = "nota_fiscal")
	private String notaFiscal;
	@Column(name = "data_compra")
	private Date dataCompra;
	@Column(name = "status")
	private String status;
	@Column(name = "valor_total")
	private Double valorTotal;

	public Compra() {
		// TODO Auto-generated constructor stub
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Funcionario getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}

	public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
		this.funcionarioResponsavel = funcionarioResponsavel;
	}

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
