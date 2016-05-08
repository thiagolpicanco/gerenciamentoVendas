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
