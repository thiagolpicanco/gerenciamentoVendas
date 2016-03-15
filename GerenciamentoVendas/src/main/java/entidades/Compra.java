package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Compra implements Serializable{

	private List<Produto> listaProdutos;
	private Fornecedor fornecedor;
	private Funcionario funcionarioResponsavel;
	private String notaFiscal;
	private Date dataCompra;
	private String status;
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
	
	
	
	
}
