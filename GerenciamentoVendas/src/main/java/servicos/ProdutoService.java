package servicos;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import entidades.EntradaProduto;
import entidades.Produto;
import entidades.SaidaProduto;
import exceptions.PersistenciaException;
import persistence.EntradaProdutoDao;
import persistence.GerencialDao;
import persistence.ProdutoDao;
import persistence.SaidaProdutoDao;
import util.MensagensUtil;

/**
 * 
 * @author thiago.picanco
 *
 */
@Stateless
public class ProdutoService {

	@EJB
	ProdutoDao produtoDao;

	@EJB
	EntradaProdutoDao entradaProdutoDao;

	@EJB
	SaidaProdutoDao saidaProdutoDao;

	public void cadastraSaidaProduto(SaidaProduto saidaProduto) throws Exception {
		saidaProdutoDao.gravar(saidaProduto);
	}

	public Produto buscaProduto(Produto produto) throws Exception {
		return produtoDao.buscaProduto(produto);
	}

	public List<Produto> listaProdutosPendentes() {
		return produtoDao.listaProdutosPendentes();
	}

	public void entradaProduto(EntradaProduto entradaProduto) throws PersistenciaException {

		entradaProdutoDao.gravar(entradaProduto);
		this.atualizaProduto(entradaProduto.getProduto());

	}

	public void saidaProduto(SaidaProduto saidaProduto) throws PersistenciaException {

		saidaProdutoDao.gravar(saidaProduto);
		this.atualizaProduto(saidaProduto.getProduto());

	}

	public void cadastraProduto(Produto produto) throws PersistenciaException {

		Produto produtoExistente = produtoDao.buscaPorTamanhoEProduto(produto);
		if (null != produtoExistente) {
			throw new PersistenciaException("Produto ja cadastrado.");
		} else {
			produtoDao.gravar(produto);
		}

	}

	public void deletaProduto(Produto produto) {
		try {
			produtoDao.excluir(produto);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizaProduto(Produto produto) throws PersistenciaException {

		produtoDao.gravarOuAtualizar(produto);

	}

	public void atualizarProduto(Produto produto) {
		try {
			produtoDao.atualizar(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Produto> listarProdutoLike(Produto produto) {
		return produtoDao.listaProdutoLike(produto);
	}

	public List<Produto> listarTodos() {
		return produtoDao.listarTudo();
	}

	public List<Produto> listaPorFiltro(Produto filtro) {
		return produtoDao.filtrarProdutos(filtro);
	}

	public List<String> listaTamanhosPorProduto(Produto produto) {
		return produtoDao.listaTamanhosPorProduto(produto);
	}
}
