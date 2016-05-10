package controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.Produto;
import entidades.ProdutoEstoque;
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
	private ProdutoEstoque estoque;
	private List<ProdutoEstoque> produtosVinculados;
	private Integer qtdEntrada;
	private Integer qtdSaida;

	@PostConstruct
	public void init() {
		estoque = new ProdutoEstoque();
		this.inicializaCombos();
		this.listaProdutosVinculados();
	}

	public void listaProdutosVinculados() {
		produtosVinculados = (List<ProdutoEstoque>) estoqueService.listarTodosProdutosVinculados();
	}

	public void obtemListaTamanhosVinculados() {
		listaTamanhos = estoqueService.listaTamanhosPorProduto(estoque.getProduto());
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

	public void efetuarEntradaEstoque() {
		Integer qtdAtual = estoque.getQtdAtual();
		Integer qtdFinal = estoque.getQtdAtual() + this.qtdEntrada;
		estoque.setQtdAtual(qtdFinal);
		try {
			estoqueService.atualizarProdutoEstoque(estoque);
			MensagensUtil.adicionaMensagemSucesso("Entrada de produto efetuada com sucesso");

		} catch (Exception e) {
			MensagensUtil.adicionaMensagemErro("Erro ao dar entrada de produto" + e.getMessage());
		}

	}

	public void efetuarSaidaEstoque() {
		Integer qtdAtual = estoque.getQtdAtual();
		Integer qtdFinal = qtdAtual - this.qtdSaida;
		estoque.setQtdAtual(qtdFinal);
		try {
			estoqueService.atualizarProdutoEstoque(estoque);
			MensagensUtil.adicionaMensagemSucesso("Remoção de produto efetuada com sucesso");

		} catch (Exception e) {
			MensagensUtil.adicionaMensagemErro("Erro ao dar remover de produto" + e.getMessage());
		}

	}

	public void vincularProduto() {
		try {
			estoque.setQtdAtual(0);
			estoqueService.vinculaProdutoEstoque(estoque);
			MensagensUtil.adicionaMensagemSucesso(MSG_PRODUTO_ESTOQUE_VINCULADO);
			this.limparCampos();
		} catch (Exception e) {
			MensagensUtil.adicionaMensagemErro(MSG_PRODUTO_ESTOQUE_ERRO + e.getMessage());
		}
	}

	public void removerVinculoProduto() {

		try {

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void limparCampos() {
		this.inicializaCombos();
		estoque = new ProdutoEstoque();
		this.qtdEntrada = null;
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

	public ProdutoEstoque getEstoque() {
		return estoque;
	}

	public void setEstoque(ProdutoEstoque estoque) {
		this.estoque = estoque;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public EstoqueService getEstoqueService() {
		return estoqueService;
	}

	public void setEstoqueService(EstoqueService estoqueService) {
		this.estoqueService = estoqueService;
	}

	public List<ProdutoEstoque> getProdutosVinculados() {
		return produtosVinculados;
	}

	public void setProdutosVinculados(List<ProdutoEstoque> produtosVinculados) {
		this.produtosVinculados = produtosVinculados;
	}

	public String getMSG_PRODUTO_ESTOQUE_VINCULADO() {
		return MSG_PRODUTO_ESTOQUE_VINCULADO;
	}

	public String getMSG_PRODUTO_ESTOQUE_ERRO() {
		return MSG_PRODUTO_ESTOQUE_ERRO;
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

	public Integer getQtdPendente(ProdutoEstoque estoque) {
		if (estoque.getQtdAtual() > estoque.getQtdMinima()) {
			return 0;
		} else {
			return estoque.getQtdAtual() - estoque.getQtdMinima() *-1;
		}
	}
}
