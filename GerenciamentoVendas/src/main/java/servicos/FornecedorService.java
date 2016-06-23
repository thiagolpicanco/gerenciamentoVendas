package servicos;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import entidades.Cliente;
import entidades.Fornecedor;
import entidades.Produto;
import exceptions.PersistenciaException;
import persistence.FornecedorDao;

/**
 * 
 * @author thiago.picanco
 *
 */
@Stateless
public class FornecedorService {

	@EJB
	FornecedorDao fornecedorDao;

	public void cadastraFornecedor(Fornecedor fornecedor) throws Exception {
		try {
			fornecedorDao.gravarOuAtualizar(fornecedor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Fornecedor> findByCNPJ(String cnpj) {
		return fornecedorDao.findByCNPJ(cnpj);

	}

	public void deletaFornecedor(Fornecedor fornecedor) {
		try {
			fornecedorDao.excluir(fornecedor);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizaFornecedor(Fornecedor fornecedor) {
		try {
			fornecedorDao.gravarOuAtualizar(fornecedor);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Fornecedor> listarTodos() {
		return fornecedorDao.listarTudo();
	}

	public List<Fornecedor> listaPorFiltro(Fornecedor filtro) {
		return fornecedorDao.filtrarFornecedores(filtro);
	}

}
