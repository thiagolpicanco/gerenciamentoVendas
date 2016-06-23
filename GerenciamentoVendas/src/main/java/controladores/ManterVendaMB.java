package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import entidades.CategoriaProduto;
import entidades.Cliente;
import entidades.EntradaProduto;
import entidades.Fornecedor;
import entidades.Funcionario;
import entidades.NotaFiscal;
import entidades.Produto;
import entidades.SaidaProduto;
import entidades.TipoPagamento;
import entidades.Venda;
import persistence.CategoriaProdutoDao;
import persistence.TipoPagamentoDao;
import servicos.ClienteService;
import servicos.FornecedorService;
import servicos.FuncionarioService;
import servicos.ProdutoService;
import servicos.VendaService;
import util.MensagensUtil;

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

	@EJB
	FornecedorService fornecedorService;

	@EJB
	CategoriaProdutoDao categoriaProdutoDao;

	@EJB
	TipoPagamentoDao tipoPagamentoDao;

	public static final String RELATORIO_PROTOCOLO = "/WEB-INF/notafiscal/RelatorioProtocolo.jasper";

	// -------VARIAVEIS------------//

	private Venda venda;
	private List<Venda> listaVendas;
	private List<Cliente> listaClientes;
	private Funcionario funcionarioResponsavel;
	private List<Funcionario> listaFuncionarios;
	private Produto filtroProduto;
	private Cliente filtroCliente;
	private List<Produto> listaProdutos;
	private Cliente cliente;
	private Produto produto;
	private SaidaProduto saidaProduto;
	private List<SaidaProduto> listaProdutosCarrinho;
	private List<CategoriaProduto> listaCategorias;
	private Integer qtdSaida;
	private List<TipoPagamento> tiposPagamento;
	private List<Fornecedor> listaFornecedores;
	private Venda vendaSelecionada;
	private Boolean isVisualizar;
	private String tituloMB = "Lista de Vendas Efetuadas";

	@PostConstruct
	public void init() {

		venda = new Venda();
		venda.setNotaFiscal(new NotaFiscal());
		listaProdutosCarrinho = new ArrayList<>();
		produto = new Produto();
		filtroProduto = new Produto();
		filtroCliente = new Cliente();
		saidaProduto = new SaidaProduto();
		inicializaCombos();
		try {
			listaFuncionarios = funcionarioService.listarTodos();
			listaVendas = vendaService.listarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Flash getFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	public void visualizaVenda(Venda vendaParametro) {
		isVisualizar = Boolean.TRUE;
		vendaSelecionada = vendaParametro;
		tituloMB = "Detalhamento de venda efetuada";
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
		tiposPagamento = tipoPagamentoDao.listarTudo();
	}

	public void adicionaProdutoVenda() {
		saidaProduto = new SaidaProduto();
		saidaProduto.setQuantidade(qtdSaida);
		saidaProduto.setProduto(produto);
		saidaProduto.setVenda(venda);
		saidaProduto.setDataSaida(new Date());
		venda.getListaProdutos().add(saidaProduto);
		this.alteraValorTotal();
	}

	public void filtrarVendas() {
		listaVendas = vendaService.filtraVenda(venda);
	}

	public void alteraValorTotal() {
		Double valorTotal = 0.0;
		for (SaidaProduto saidaProduto : venda.getListaProdutos()) {
			valorTotal += saidaProduto.getProduto().getValorVenda() * saidaProduto.getQuantidade();
		}
		venda.setValorTotal(valorTotal);
	}

	public void listarProdutos() {
		listaProdutos = produtoService.listarTodos();
	}

	public void listarClientes() {
		listaClientes = clienteService.listarTodos();
	}

	public void efetuarVenda() {

		try {
			Produto produtoFinal = new Produto();
			for (SaidaProduto saidaProd : venda.getListaProdutos()) {
				produtoFinal = produtoService.buscaProduto(saidaProd.getProduto());
				Integer qtdFinal = produtoFinal.getQtdAtual() - saidaProd.getQuantidade();
				produtoFinal.setQtdAtual(qtdFinal);
				produtoService.atualizaProduto(produtoFinal);
				saidaProd.setObservacao("Venda");
			}

			if (produtoFinal.getQtdAtual() < 0) {
				throw new Exception("Não existe essa quantidade de produto estoque");
			}

			venda.setDataVenda(new Date());
			venda.setCliente(cliente);
			venda.setFuncionarioResponsavel(UsuarioSessaoMB.getUsuarioLogin().getFuncionario());
			NotaFiscal notaFiscal = new NotaFiscal();
			notaFiscal.setFuncionarioResponsavel(venda.getFuncionarioResponsavel());
			notaFiscal.setCliente(venda.getCliente());
			venda.setNotaFiscal(notaFiscal);
			vendaService.cadastraVenda(venda);
			MensagensUtil.adicionaMensagemSucesso("Venda registrada com sucesso");
			init();
		} catch (Exception e) {
			MensagensUtil.adicionaMensagemErro("Erro ao registrar Venda: " + e.getMessage());
		}

	}

//	public void imprimirProtocolo() {
//		String reportUrlReal = ConstantesRelatorio.RELATORIO_PROTOCOLO;
//		Map<String, Object> params = new HashMap<String, Object>();
//		try {
//			GerarRelatorioBean.gerarRelatorio(protocolosSelecionados, reportUrlReal, params, TipoRelEnum.PDF);
//		} catch (Exception e) {
//			MENSAGEM_FACES.adicionarMensagemErro("Erro gerar Relatório");
//		}
//	}

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

	public List<TipoPagamento> getTiposPagamento() {
		return tiposPagamento;
	}

	public void setTiposPagamento(List<TipoPagamento> tiposPagamento) {
		this.tiposPagamento = tiposPagamento;
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

	public List<SaidaProduto> getListaProdutosCarrinho() {
		return listaProdutosCarrinho;
	}

	public void setListaProdutosCarrinho(List<SaidaProduto> listaProdutosCarrinho) {
		this.listaProdutosCarrinho = listaProdutosCarrinho;
	}

	public Produto getFiltroProduto() {
		return filtroProduto;
	}

	public void setFiltroProduto(Produto filtroProduto) {
		this.filtroProduto = filtroProduto;
	}

	public Cliente getFiltroCliente() {
		return filtroCliente;
	}

	public void setFiltroCliente(Cliente filtroCliente) {
		this.filtroCliente = filtroCliente;
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

	public Integer getQtdSaida() {
		return qtdSaida;
	}

	public void setQtdSaida(Integer qtdSaida) {
		this.qtdSaida = qtdSaida;
	}

	public Venda getVendaSelecionada() {
		return vendaSelecionada;
	}

	public void setVendaSelecionada(Venda vendaSelecionada) {
		this.vendaSelecionada = vendaSelecionada;
	}

	public Boolean getIsVisualizar() {
		return isVisualizar;
	}

	public void setIsVisualizar(Boolean isVisualizar) {
		this.isVisualizar = isVisualizar;
	}

	public String getTituloMB() {
		return tituloMB;
	}

	public void setTituloMB(String tituloMB) {
		this.tituloMB = tituloMB;
	}

}
