package servicos;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import entidades.Estoque;
import exceptions.GerencialException;
import persistence.EstoqueDao;
import util.MensagensUtil;

/**
 * 
 * @author thiago.picanco
 *
 */

@Stateless
public class EstoqueService {

	@EJB
	EstoqueDao estoqueDao;

	public void vinculaProdutoEstoque(Estoque estoque) throws GerencialException, Exception {
		if (!isDuplicado(estoque)) {
			try {
				estoqueDao.cadastrarProdutoEstoque(estoque);
			} catch (Exception e) {
				throw e;
			}
		} else {

		}
	}

	public void removerVinculoProduto(Estoque estoque) throws Exception {
		if (estoque.getQtdAtual() > 0) {
			throw new GerencialException("Produto não pode ser removido pois contem quantidade em estoque");
		} else {
			estoqueDao.excluir(estoque);
		}

	}

	public void entradaEstoque(Estoque estoque) {
		try {
			estoqueDao.atualizar(estoque);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saidaEstoque(Estoque estoque) {
		try {

		} catch (Exception e) {
			
		}
	}

	public Boolean isDuplicado(Estoque estoque) {
		if (null != estoqueDao.buscaPorTamanhoEProduto(estoque)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
}
