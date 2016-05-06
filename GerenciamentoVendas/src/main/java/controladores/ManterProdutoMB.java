package controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import entidades.Produto;
import servicos.ProdutoService;

@ManagedBean
@SessionScoped
public class ManterProdutoMB {

	
	@EJB
	ProdutoService produtoService;
	
	
	private Produto produto;
	
	
	
	

	@PostConstruct
	public void init() {

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
	

}
