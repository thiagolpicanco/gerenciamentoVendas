package servicos;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import entidades.Cliente;
import exceptions.PersistenciaException;
import persistence.ClienteDao;

/**
 * 
 * @author thiago.picanco
 *
 */
@Stateless
public class ClienteService {

	@EJB
	ClienteDao clienteDao;

	public void cadastraCliente(Cliente cliente) {
		try {
			clienteDao.gravarOuAtualizar(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletaCliente(Cliente cliente) {
		try {
			clienteDao.excluir(cliente);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizaCliente(Cliente cliente) {
		try {
			clienteDao.gravarOuAtualizar(cliente);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Cliente> listarTodos() {
		return clienteDao.listarTudo();
	}

	public List<Cliente> listaPorFiltro(Cliente cliente) {
		return clienteDao.filtrarClientees(cliente);
	}

}
