package controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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

	@EJB
	private FuncionarioDao funcionarioDao;

	/**
	 * VARIAVEIS
	 * 
	 * 
	 */

	public String teste;

	private Funcionario funcionario;

	private List<Funcionario> listaFuncionarios;

	@PostConstruct
	public void init() {
		try {
			listaFuncionarios = funcionarioDao.listarTudo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

	public void salvarOuAtualizar() {
	}

	public String getTeste() {
		return teste;
	}

	public void setTeste(String teste) {
		this.teste = teste;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
