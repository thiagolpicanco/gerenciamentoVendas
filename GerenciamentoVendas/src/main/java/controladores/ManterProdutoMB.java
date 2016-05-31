package controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import entidades.CategoriaProduto;
import entidades.EntradaProduto;
import entidades.Fornecedor;
import entidades.Produto;
import entidades.SaidaProduto;
import enums.EnumMotivoEntrada;
import enums.EnumMotivoSaida;
import enums.TamanhoCalcadosEnum;
import enums.TamanhoRoupasLetrasEnum;
import persistence.CategoriaProdutoDao;
import servicos.FornecedorService;
import servicos.ProdutoService;
import util.MensagensUtil;

@ManagedBean
@ViewScoped
public class ManterProdutoMB {

	final String MSG_PRODUTO_CADASTRADO = "Produto cadastrado com sucesso.";
	final String MSG_PRODUTO_ERRO = "Erro ao cadastrar produto: ";
	final String MSG_PRODUTO_REMOVIDO = "Produto removido com sucesso";

	@EJB
	ProdutoService produtoService;

	@EJB
	FornecedorService fornecedorService;

	@EJB
	CategoriaProdutoDao categoriaProdutoDao;

	private List<Produto> listaProdutos;

	private List<CategoriaProduto> listaCategorias;

	private List<Fornecedor> listaFornecedores;
	private Produto produto;
	private List<String> listaTamanhos;
	private Produto produtoSelecionado;
	private Integer qtdEntrada;
	private String render;
	private Integer qtdSaida;
	private List<String> listaMotivos;
	private SaidaProduto saidaProduto;
	private EntradaProduto entradaProduto;
	private List<Produto> listaProdutosPendentes;

	@PostConstruct
	public void init() {
		produto = new Produto();
		saidaProduto = new SaidaProduto();
		entradaProduto = new EntradaProduto();
		this.inicializaCombos();
		produtoSelecionado = new Produto();
		this.listarProdutos();
	}

	public void limpaCampos() {
		produto = new Produto();
		saidaProduto = new SaidaProduto();
		entradaProduto = new EntradaProduto();
		qtdEntrada = 0;
		qtdSaida = 0;
		RequestContext.getCurrentInstance().update("principal");
	}

	public void renderizaConsulta() {
		this.listarProdutos();
		render = "c";
		this.limpaCampos();
	}

	public void renderizaEntrada() {
		render = "e";
		this.limpaCampos();
		this.listaMotivosEntrada();
	}

	public void renderizaSaida() {
		render = "s";
		this.limpaCampos();
		this.listaMotivosSaida();
	}

	public void renderizaRelatorio() {
		render = "r";
		this.limpaCampos();
	}

	public void buscar() {
		listaProdutos = produtoService.listaPorFiltro(produto);
		produto = new Produto();
	}

	public void inicializaCombos() {
		listaFornecedores = fornecedorService.listarTodos();
		listaCategorias = categoriaProdutoDao.listarTudo();
	}

	public void listarProdutos() {
		listaProdutos = produtoService.listarTodos();
	}

	public void visualizarProduto(Produto produto) {

		this.produtoSelecionado = produto;
	}

	public void cadastraProduto() {
		try {
			this.produto.setQtdAtual(0);
			produtoService.cadastraProduto(this.produto);
			MensagensUtil.adicionaMensagemSucesso(MSG_PRODUTO_CADASTRADO);
			produto = new Produto();

		} catch (Exception e) {
			MensagensUtil.adicionaMensagemErro(MSG_PRODUTO_ERRO + e.getMessage());
		}

	}

	public void listaMotivosEntrada() {
		listaMotivos = new ArrayList<String>();

		for (EnumMotivoEntrada motivo : EnumMotivoEntrada.values()) {
			listaMotivos.add(motivo.getDescricao());
		}

	}

	public void listaMotivosSaida() {
		listaMotivos = new ArrayList<String>();

		for (EnumMotivoSaida motivo : EnumMotivoSaida.values()) {
			listaMotivos.add(motivo.getDescricao());
		}

	}

	public List<Produto> listaProdutosPendentes() {

		listaProdutosPendentes = produtoService.listaProdutosPendentes();
		for (Produto p : listaProdutosPendentes) {
			p.setQtdPendente(retornaQtdPendente(p));
		}
		return listaProdutosPendentes;

	}

	public int retornaQtdPendente(Produto produto) {
		return produto.getQtdMinima() - produto.getQtdAtual();
	}

	public void listaTamanhosPorCategoria() {
		if (produto.getCategoria() != null) {
			listaTamanhos = new ArrayList<>();
			if (produto.getCategoria().getNoCategoria().equalsIgnoreCase("Calçados")) {
				for (TamanhoCalcadosEnum enumCalcado : TamanhoCalcadosEnum.values()) {
					listaTamanhos.add(enumCalcado.getLetra());
				}
			} else {
				for (TamanhoRoupasLetrasEnum enumRoupa : TamanhoRoupasLetrasEnum.values()) {
					listaTamanhos.add(enumRoupa.getLetra());
				}
			}
		}
	}

	public void efetuarEntradaEstoque() {
		entradaProduto.setData(new Date());
		entradaProduto.setProduto(produto);
		entradaProduto.setQuantidade(qtdEntrada);
		Integer qtdFinal = produto.getQtdAtual() + this.qtdEntrada;
		produto.setQtdAtual(qtdFinal);
		try {
			produtoService.entradaProduto(entradaProduto);
			MensagensUtil.adicionaMensagemSucesso("Entrada de produto efetuada com sucesso");

		} catch (Exception e) {
			MensagensUtil.adicionaMensagemErro("Erro ao dar entrada de produto" + e.getMessage());
		}
		this.limpaCampos();
	}

	public void efetuarSaidaEstoque() {
		Integer qtdAtual = produto.getQtdAtual();
		Integer qtdFinal = qtdAtual - this.qtdSaida;
		produto.setQtdAtual(qtdFinal);
		saidaProduto.setProduto(produto);
		saidaProduto.setDataSaida(new Date());
		saidaProduto.setQuantidade(this.qtdSaida);

		try {
			produtoService.saidaProduto(saidaProduto);
			MensagensUtil.adicionaMensagemSucesso("Remoção de produto efetuada com sucesso");

		} catch (Exception e) {
			MensagensUtil.adicionaMensagemErro("Erro ao dar remover de produto" + e.getMessage());
		}
		this.limpaCampos();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public FornecedorService getFornecedorService() {
		return fornecedorService;
	}

	public void setFornecedorService(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	public List<Fornecedor> getListaFornecedores() {
		return listaFornecedores;
	}

	public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
		this.listaFornecedores = listaFornecedores;
	}

	public CategoriaProdutoDao getCategoriaProdutoDao() {
		return categoriaProdutoDao;
	}

	public void setCategoriaProdutoDao(CategoriaProdutoDao categoriaProdutoDao) {
		this.categoriaProdutoDao = categoriaProdutoDao;
	}

	public List<CategoriaProduto> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<CategoriaProduto> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public List<String> getListaTamanhos() {
		return listaTamanhos;
	}

	public void setListaTamanhos(List<String> listaTamanhos) {
		this.listaTamanhos = listaTamanhos;
	}

	public String getRender() {
		return render;
	}

	public void setRender(String render) {
		this.render = render;
	}

	public Integer getQtdEntrada() {
		return qtdEntrada;
	}

	public void setQtdEntrada(Integer qtdEntrada) {
		this.qtdEntrada = qtdEntrada;
	}

	public Integer getQtdSaida() {
		return qtdSaida;
	}

	public void setQtdSaida(Integer qtdSaida) {
		this.qtdSaida = qtdSaida;
	}

	public List<String> getListaMotivos() {
		return listaMotivos;
	}

	public void setListaMotivos(List<String> listaMotivos) {
		this.listaMotivos = listaMotivos;
	}

	public SaidaProduto getSaidaProduto() {
		return saidaProduto;
	}

	public void setSaidaProduto(SaidaProduto saidaProduto) {
		this.saidaProduto = saidaProduto;
	}

	public EntradaProduto getEntradaProduto() {
		return entradaProduto;
	}

	public void setEntradaProduto(EntradaProduto entradaProduto) {
		this.entradaProduto = entradaProduto;
	}

	public List<Produto> getListaProdutosPendentes() {
		return listaProdutosPendentes;
	}

	public void setListaProdutosPendentes(List<Produto> listaProdutosPendentes) {
		this.listaProdutosPendentes = listaProdutosPendentes;
	}

}
