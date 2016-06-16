package servicos;

import java.util.List;

/**
 * 
 * @author thiago.picanco
 *
 */
import javax.ejb.Stateless;

import entidades.Compra;
import entidades.Venda;
import exceptions.PersistenciaException;
import persistence.CompraDao;

@Stateless
public class CompraService {

	private CompraDao compraDao;

	public void cadastraCompra(Compra compra) {
		try {
			compraDao.gravarOuAtualizar(compra);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletaVenda(Compra compra) {
		try {
			compraDao.excluir(compra);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizaCompra(Compra compra) {
		try {
			compraDao.gravarOuAtualizar(compra);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Compra> listarTodos() {
		return compraDao.listarTudo();
	}

}
