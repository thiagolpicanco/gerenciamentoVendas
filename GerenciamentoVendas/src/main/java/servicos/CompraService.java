package servicos;

import java.util.List;

import javax.ejb.EJB;
/**
 * 
 * @author thiago.picanco
 *
 */
import javax.ejb.Stateless;

import entidades.Compra;
import exceptions.PersistenciaException;
import persistence.CompraDao;

@Stateless
public class CompraService {
	@EJB
	private CompraDao compraDao;

	public void cadastraCompra(Compra compra) throws PersistenciaException {
		compraDao.gravarOuAtualizar(compra);
	}

	public void deletaVenda(Compra compra) throws PersistenciaException {
		compraDao.excluir(compra);

	}

	public void atualizaCompra(Compra compra) throws PersistenciaException {
		compraDao.gravarOuAtualizar(compra);
	}

	public List<Compra> listarTodos() {
		return compraDao.listarTudo();
	}

}
