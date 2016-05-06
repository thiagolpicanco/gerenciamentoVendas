package servicos;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import entidades.Funcionario;
import exceptions.PersistenciaException;
import persistence.FuncionarioDao;

/**
 * 
 * @author thiago.picanco
 *
 */
@Stateless
public class FuncionarioService {

	
	
	@EJB
	FuncionarioDao funcionarioDao;

	public void cadastraFuncionario(Funcionario funcionario) {
		try {
			funcionarioDao.gravarOuAtualizar(funcionario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletaFuncionario(Funcionario funcionario) {
		try {
			funcionarioDao.excluir(funcionario);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizaFuncionario(Funcionario funcionario) {
		try {
			funcionarioDao.gravarOuAtualizar(funcionario);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Funcionario> listarTodos() {
		return funcionarioDao.listarTudo();
	}
	
	
	
}
