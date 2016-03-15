package controladores;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import persistence.FuncionarioDao;
import util.MensagensUtil;
import entidades.Funcionario;
import enums.TipoFuncionarioEnum;

@ManagedBean
@SessionScoped
public class ManterFuncionarioMB {

	final String MSG_CADASTRO_SUCESSO = "Funcionário Cadastrado com Sucesso.";

	private Funcionario funcionario;

	private FuncionarioDao funcionarioDao;

	private List<Funcionario> listaFuncionarios;

	public void init() {
		

	}

	public void salvarOuAtualizar() {
	}
	
	
	
	
	

	
	
}
