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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@GeneratedValue(generator = "sq_compra")
	@Column
	private Integer nu_compra;
	@OneToMany(mappedBy = "compra")
	private List<EntradaProduto> listaProdutos;
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private Fornecedor fornecedor;
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionarioResponsavel;
	@Column(name = "nota_fiscal")
	private String notaFiscal;
	@Column(name = "data_compra")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCompra;
	@Column(name = "valor_total")
	private Double valorTotal;

	public Compra() {
		// TODO Auto-generated constructor stub
	}

	public Integer getNu_compra() {
		return nu_compra;
	}

	public void setNu_compra(Integer nu_compra) {
		this.nu_compra = nu_compra;
	}

	public List<EntradaProduto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<EntradaProduto> listaProdutos) {
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

	

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
