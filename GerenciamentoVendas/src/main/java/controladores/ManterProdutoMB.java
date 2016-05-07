package controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import entidades.CategoriaProduto;
import entidades.Fornecedor;
import entidades.Produto;
import enums.TamanhoCalcadosEnum;
import enums.TamanhoRoupasLetrasEnum;
import enums.TamanhoRoupasNumeralEnum;
import persistence.CategoriaProdutoDao;
import servicos.FornecedorService;
import servicos.ProdutoService;
import util.MensagensUtil;

@ManagedBean
@SessionScoped
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

	private Produto produtoSelecionado;

	@PostConstruct
	public void init() {
		produto = new Produto();
		
		produtoSelecionado = new Produto();
		this.inicializaCombos();
		listarProdutos();
	}

	public void limpaCampos() {
		produto = new Produto();

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
			produtoService.cadastraProduto(this.produto);
			MensagensUtil.adicionaMensagemSucesso(MSG_PRODUTO_CADASTRADO);
			produto = new Produto();
		} catch (Exception e) {
			MensagensUtil.adicionaMensagemErro(MSG_PRODUTO_ERRO + e.getMessage());
		}

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

}
