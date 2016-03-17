package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Venda implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3601125323849678360L;
	private List<Produto> listaProdutos;
	private Funcionario funcionarioResponsavel;
	private String notaFiscal;
	private Date dataVenda;
	private String status;
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
	
	
	
	
	
	
	
	
	
	
	
	
}
