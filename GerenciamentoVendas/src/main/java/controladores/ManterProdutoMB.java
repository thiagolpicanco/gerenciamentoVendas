package controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import entidades.Fornecedor;
import entidades.Produto;
import servicos.FornecedorService;
import servicos.ProdutoService;

@ManagedBean
@SessionScoped
public class ManterProdutoMB {

	@EJB
	ProdutoService produtoService;

	@EJB
	FornecedorService fornecedorService;

	private List<SelectItem> listaFornecedoresSelect;

	private List<Fornecedor> listaFornecedores;
	private Produto produto;
	

	@PostConstruct
	public void init() {
		listaFornecedores = fornecedorService.listarTodos();
		produto = new Produto();
		listaFornecedoresSelect = new ArrayList<>();
		for (Fornecedor f : listaFornecedores) {
			SelectItem si = new SelectItem(f, f.getNome());
			listaFornecedoresSelect.add(si);
		}

	}

	public void cadastraProduto() {
		produtoService.cadastraProduto(this.produto);
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

	public List<SelectItem> getListaFornecedoresSelect() {
		return listaFornecedoresSelect;
	}

	public void setListaFornecedoresSelect(List<SelectItem> listaFornecedoresSelect) {
		this.listaFornecedoresSelect = listaFornecedoresSelect;
	}

}
