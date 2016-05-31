package controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.Cliente;
import entidades.Funcionario;
import entidades.Produto;
import entidades.SaidaProduto;
import entidades.Venda;
import servicos.ClienteService;
import servicos.FuncionarioService;
import servicos.ProdutoService;
import servicos.VendaService;

@ManagedBean
@ViewScoped
public class ManterVendaMB {

	@EJB
	VendaService vendaService;
	@EJB
	ClienteService clienteService;
	@EJB
	ProdutoService produtoService;
	@EJB
	FuncionarioService funcionarioService;
	// -------VARIAVEIS------------//

	private Venda venda;

	private List<Venda> listaVendas;

	private List<Cliente> listaClientes;

	private Funcionario funcionarioResponsavel;

	private List<Funcionario> listaFuncionarios;

	private List<Produto> listaProdutos;

	private Cliente cliente;
	private Produto produto;
	private SaidaProduto saidaProduto;

	@PostConstruct
	public void init() {
		venda = new Venda();
		produto = new Produto();
		saidaProduto = new SaidaProduto();
		inicializaCombos();
	}

	public void inicializaCombos() {
		this.listarProdutos();
		this.listarClientes();
	}

	public void adicionaProdutoVenda() {
		saidaProduto.setProduto(produto);
		saidaProduto.setVenda(venda);
		saidaProduto.setDataSaida(new Date());
		venda.getListaProdutos().add(saidaProduto);
	}

	public void listarProdutos() {
		listaProdutos = produtoService.listarTodos();
	}

	public void listarClientes() {
		listaClientes = clienteService.listarTodos();
	}

	public void efetuarVenda() {

	}

	public void listaFuncionarios() {
		listaFuncionarios = funcionarioService.listarTodos();
	}

	public VendaService getVendaService() {
		return vendaService;
	}

	public void setVendaService(VendaService vendaService) {
		this.vendaService = vendaService;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}

	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Venda> getListaVendas() {
		return listaVendas;
	}

	public void setListaVendas(List<Venda> listaVendas) {
		this.listaVendas = listaVendas;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Funcionario getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}

	public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
		this.funcionarioResponsavel = funcionarioResponsavel;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public SaidaProduto getSaidaProduto() {
		return saidaProduto;
	}

	public void setSaidaProduto(SaidaProduto saidaProduto) {
		this.saidaProduto = saidaProduto;
	}

}
