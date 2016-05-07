package controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import entidades.Estoque;
import entidades.Produto;
import enums.TamanhoCalcadosEnum;
import enums.TamanhoRoupasLetrasEnum;
import servicos.EstoqueService;
import servicos.ProdutoService;
import util.MensagensUtil;

@ManagedBean
@ViewScoped
public class ManterEstoqueMB {

	final String MSG_PRODUTO_ESTOQUE_VINCULADO = "Produto vinculado com sucesso.";
	final String MSG_PRODUTO_ESTOQUE_ERRO = "Erro ao vincular produto: ";

	@EJB
	ProdutoService produtoService;

	@EJB
	EstoqueService estoqueService;

	private List<String> listaTamanhos;
	private List<Produto> listaProdutos;
	private Estoque estoque;

	@PostConstruct
	public void init() {
		estoque = new Estoque();
		this.inicializaCombos();
	}

	public void inicializaCombos() {
		listaProdutos = produtoService.listarTodos();
	}

	public void listaTamanhosPorCategoria() {
		if (estoque.getProduto().getCategoria() != null) {
			listaTamanhos = new ArrayList<>();
			if (estoque.getProduto().getCategoria().getNoCategoria().equalsIgnoreCase("Calçados")) {
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

	public void vincularProduto() {
		try {
			estoqueService.vinculaProdutoEstoque(estoque);
			MensagensUtil.adicionaMensagemSucesso(MSG_PRODUTO_ESTOQUE_VINCULADO);
			this.limparCampos();
		} catch (Exception e) {
			MensagensUtil.adicionaMensagemErro(MSG_PRODUTO_ESTOQUE_ERRO + e.getMessage());
		}
	}

	public void limparCampos() {
		this.inicializaCombos();
		estoque = new Estoque();
		RequestContext.getCurrentInstance().update("formVincular");
	}

	public List<String> getListaTamanhos() {
		return listaTamanhos;
	}

	public void setListaTamanhos(List<String> listaTamanhos) {
		this.listaTamanhos = listaTamanhos;
	}

	

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

}
