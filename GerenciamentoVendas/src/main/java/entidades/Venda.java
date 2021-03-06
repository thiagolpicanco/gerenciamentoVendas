package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	@Column(name = "nu_venda")
	private Integer nuVenda;
	@OneToMany(mappedBy = "venda", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<SaidaProduto> listaProdutos;
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionarioResponsavel;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nota_fiscal")
	private NotaFiscal notaFiscal;
	@Column(name = "data_venda")
	private Date dataVenda;
	@Column(name = "valor_total")
	private Double valorTotal;

	@ManyToOne
	@JoinColumn(name = "tipo_pagamento")
	private TipoPagamento tipoPagamento;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Venda() {
		this.listaProdutos = new ArrayList<>();
		this.nuVenda = null;
	}

	public Integer getNuVenda() {
		return nuVenda;
	}

	public void setNuVenda(Integer nuVenda) {
		this.nuVenda = nuVenda;
	}

	public List<SaidaProduto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<SaidaProduto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public Funcionario getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}

	public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
		this.funcionarioResponsavel = funcionarioResponsavel;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

}
