package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Venda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3601125323849678360L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;
	@OneToMany
	private List<Produto> listaProdutos;
	@ManyToOne
	private Funcionario funcionarioResponsavel;
	@Column
	private String notaFiscal;
	@Column
	private Date dataVenda;
	@Column
	private String status;
	@Column
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
