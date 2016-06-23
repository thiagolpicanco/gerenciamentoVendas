package controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import persistence.FuncionarioDao;
import persistence.TipoFuncionarioDao;
import servicos.FuncionarioService;
import servicos.LoginService;
import util.MensagensUtil;
import entidades.Funcionario;
import entidades.Login;
import entidades.TipoFuncionario;
import enums.TipoFuncionarioEnum;
import exceptions.PersistenciaException;

@ManagedBean
@ViewScoped
public class ManterUsuarioMB {

	final String MSG_CADASTRO_SUCESSO = "Usuário Cadastrado com Sucesso.";
	final String MSG_CADASTRO_ERRO = "Erro ao cadastrar usuário: ";

	@EJB
	private LoginService loginService;

	@EJB
	private FuncionarioService funcionarioService;

	/**
	 * VARIAVEIS
	 * 
	 * 
	 */

	private Login login;
	private List<Funcionario> listaFuncionarios;

	@PostConstruct
	public void init() {
		try {
			login = new Login();
			this.listaFuncionariosSemLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void listaFuncionariosSemLogin() {
		listaFuncionarios = funcionarioService.listarTodos();
		List<Login> listaLogin = loginService.listarLogins();
		List<Funcionario> listaAuxFunconario = new ArrayList<>();

		for (Login login : listaLogin) {
			listaAuxFunconario.add(login.getFuncionario());
		}
		listaFuncionarios.removeAll(listaAuxFunconario);

	}

	public void limpaCampos() {
		login = new Login();
	}

	public void cadastrarUsuario() {

		try {
			loginService.cadastrarUsuario(login);
			MensagensUtil.adicionaMensagemSucesso(MSG_CADASTRO_SUCESSO);
			this.listaFuncionariosSemLogin();
			this.limpaCampos();
		} catch (PersistenciaException e) {
			if (e.getCause().getCause().getCause().toString().contains("duplicate")) {
				MensagensUtil.adicionaMensagemErro("ERRO: Já existe um usuario com esse login");
			} else {
				MensagensUtil.adicionaMensagemErro(MSG_CADASTRO_ERRO + e.getMessage());
			}
		}

	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}
