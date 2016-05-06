package servicos;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import entidades.Produto;
import exceptions.PersistenciaException;
import persistence.GerencialDao;
import persistence.ProdutoDao;

/**
 * 
 * @author thiago.picanco
 *
 */
@Stateless
public class ProdutoService {

	@EJB
	ProdutoDao produtoDao;

	public void cadastraProduto(Produto produto) {
		try {
			produtoDao.gravarOuAtualizar(produto);
		} catch (PersistenciaException e) {
			e.printStackTrace();
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

	public void atualizaProduto(Produto produto) {
		try {
			produtoDao.gravarOuAtualizar(produto);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Produto> listarTodos() {
		return produtoDao.listarTudo();
	}

}
