package controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.CategoriaProduto;
import entidades.Cliente;
import entidades.Compra;
import entidades.EntradaProduto;
import entidades.Fornecedor;
import entidades.Funcionario;
import entidades.Produto;
import entidades.SaidaProduto;
import persistence.CategoriaProdutoDao;
import servicos.ClienteService;
import servicos.CompraService;
import servicos.FornecedorService;
import servicos.FuncionarioService;
import servicos.ProdutoService;
import util.MensagensUtil;

@ManagedBean
@ViewScoped
public class ManterCompraMB {

	@EJB
	CompraService CompraService;
	@EJB
	ClienteService clienteService;
	@EJB
	ProdutoService produtoService;
	@EJB
	FuncionarioService funcionarioService;

	@EJB
	FornecedorService fornecedorService;

	@EJB
	CompraService compraService;

	@EJB
	CategoriaProdutoDao categoriaProdutoDao;

	// -------VARIAVEIS------------//

	private Compra compra;

	private List<Compra> listaCompras;

	private List<Cliente> listaClientes;

	private Funcionario funcionarioResponsavel;

	private Fornecedor fornecedorCompra;

	private List<Funcionario> listaFuncionarios;

	private Produto filtroProduto;

	private List<Produto> listaProdutos;

	private Produto produto;
	private EntradaProduto entradaProduto;
	private List<CategoriaProduto> listaCategorias;
	private Integer qtdEntrada;

	private List<Fornecedor> listaFornecedores;

	@PostConstruct
	public void init() {
		compra = new Compra();
		produto = new Produto();
		filtroProduto = new Produto();
		entradaProduto = new EntradaProduto();
		this.listaCompras = compraService.listarTodos();
		inicializaCombos();
	}

	public List<String> completaNomeProduto(String query) {
		filtroProduto.setNome(query);
		List<String> listaNomes = new ArrayList<>();

		listaProdutos = produtoService.listarProdutoLike(filtroProduto);
		for (Produto produto : listaProdutos) {
			listaNomes.add(produto.getNome());
		}

		return listaNomes;
	}

	public void buscarProduto() {
		listaProdutos = produtoService.listaPorFiltro(filtroProduto);
	}

	public void inicializaCombos() {
		this.listarClientes();
		listaFornecedores = fornecedorService.listarTodos();
		listaCategorias = categoriaProdutoDao.listarTudo();
	}

	public void adicionaProdutoCompra() {
		if (entradaProduto.getProduto() == null || entradaProduto.getQuantidade() == null) {
			MensagensUtil.adicionaMensagemErro("Erro ao adicionar produto: Preencha os campos obrigatórios");
		} else {
			entradaProduto.setCompra(compra);
			entradaProduto.setData(new Date());
			entradaProduto.setObservacao("Compra");
			compra.getListaProdutos().add(entradaProduto);
			entradaProduto = new EntradaProduto();
			alteraValorTotal();
		}
	}

	public void removerProdutoCompra(EntradaProduto entrada) {
		compra.getListaProdutos().remove(entrada);
		this.alteraValorTotal();
	}

	public void registrarVenda() {

	}

	public void alteraValorTotal() {
		Double valorTotal = 0.0;
		for (EntradaProduto entradaProduto : compra.getListaProdutos()) {
			valorTotal += entradaProduto.getValorUnitario() * entradaProduto.getQuantidade();
		}
		this.compra.setValorTotal(valorTotal);
	}

	public void listarProdutos() {
		listaProdutos = produtoService.listarTodos();
	}

	public void listarClientes() {
		listaClientes = clienteService.listarTodos();
	}

	public void efetuarCompra() {
		try {
			for (EntradaProduto entradaProd : compra.getListaProdutos()) {
				Produto prod = produtoService.buscaProduto(entradaProd.getProduto());
				Integer qtdFinal = prod.getQtdAtual() + entradaProd.getQuantidade();
				prod.setQtdAtual(qtdFinal);

				produtoService.atualizaProduto(prod);
			}
			compra.setDataCompra(new Date());
			compra.setFuncionarioResponsavel(UsuarioSessaoMB.getUsuarioLogin().getFuncionario());

			compraService.cadastraCompra(compra);
			MensagensUtil.adicionaMensagemSucesso("Compra registrada com sucesso");
			init();
		} catch (Exception e) {
			MensagensUtil.adicionaMensagemErro("Erro ao registrar compra: " + e.getMessage());
		}

	}

	public void listaFuncionarios() {
		listaFuncionarios = funcionarioService.listarTodos();
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

	public CompraService getCompraService() {
		return CompraService;
	}

	public void setCompraService(CompraService compraService) {
		CompraService = compraService;
	}

	public FornecedorService getFornecedorService() {
		return fornecedorService;
	}

	public void setFornecedorService(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	public CategoriaProdutoDao getCategoriaProdutoDao() {
		return categoriaProdutoDao;
	}

	public void setCategoriaProdutoDao(CategoriaProdutoDao categoriaProdutoDao) {
		this.categoriaProdutoDao = categoriaProdutoDao;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public EntradaProduto getEntradaProduto() {
		return entradaProduto;
	}

	public void setEntradaProduto(EntradaProduto entradaProduto) {
		this.entradaProduto = entradaProduto;
	}

	public List<Compra> getListaCompras() {
		return listaCompras;
	}

	public void setListaCompras(List<Compra> listaCompras) {
		this.listaCompras = listaCompras;
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Produto getFiltroProduto() {
		return filtroProduto;
	}

	public void setFiltroProduto(Produto filtroProduto) {
		this.filtroProduto = filtroProduto;
	}

	public List<CategoriaProduto> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<CategoriaProduto> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public List<Fornecedor> getListaFornecedores() {
		return listaFornecedores;
	}

	public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
		this.listaFornecedores = listaFornecedores;
	}

	public Integer getQtdEntrada() {
		return qtdEntrada;
	}

	public void setQtdEntrada(Integer qtdEntrada) {
		this.qtdEntrada = qtdEntrada;
	}

	public Fornecedor getFornecedorCompra() {
		return fornecedorCompra;
	}

	public void setFornecedorCompra(Fornecedor fornecedorCompra) {
		this.fornecedorCompra = fornecedorCompra;
	}

}
