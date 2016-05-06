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
@Table(name = "VENDA")
@SequenceGenerator(name = "sq_venda", sequenceName = "sq_venda", allocationSize = 1, initialValue = 1)
public class Venda extends Entidade implements Serializable {

	/**
	 * 
	 * @author thiago.picanco
	 *
	 */
	private static final long serialVersionUID = 3601125323849678360L;
	@Id
	@GeneratedValue(generator = "sq_venda")
	@Column(name = "id")
	private Integer id;
	@OneToMany
	@JoinTable(name = "venda_produto", joinColumns = {
			@JoinColumn(name = "venda_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "produto_id", referencedColumnName = "id") })
	private List<Produto> listaProdutos;
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionarioResponsavel;
	@Column(name = "nota_fiscal")
	private String notaFiscal;
	@Column(name = "data_venda")
	private Date dataVenda;
	@Column(name = "status")
	private String status;
	@Column(name = "valor_total")
	private Double valorTotal;

	public Venda() {
		// TODO Auto-generated constructor stub
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

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Date getDataCompra() {
		return dataVenda;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataVenda = dataCompra;
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

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
