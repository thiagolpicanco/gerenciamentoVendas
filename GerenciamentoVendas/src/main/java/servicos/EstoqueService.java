package servicos;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import entidades.Estoque;
import persistence.EstoqueDao;

/**
 * 
 * @author thiago.picanco
 *
 */

@Stateless
public class EstoqueService {

	@EJB
	EstoqueDao estoqueDao;

	public void vinculaProdutoEstoque(Estoque estoque) throws Exception {
		if (!isDuplicado(estoque)) {
			estoqueDao.cadastrarProdutoEstoque(estoque);
		} else {
			throw new Exception("Produto ja está vinculado ao estoque");
		}

	}

	public void entradaEstoque(Estoque estoque) {

	}

	public void saidaEstoque(Estoque estoque) {

	}

	public Boolean isDuplicado(Estoque estoque) {
		if (null != estoqueDao.buscaPorTamanhoEProduto(estoque)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

}
