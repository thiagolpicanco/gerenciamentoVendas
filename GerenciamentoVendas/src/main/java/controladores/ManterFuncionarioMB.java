package controladores;

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
import util.MensagensUtil;
import entidades.Funcionario;
import entidades.TipoFuncionario;
import enums.TipoFuncionarioEnum;

@ManagedBean
@ViewScoped
public class ManterFuncionarioMB {

	final String MSG_CADASTRO_SUCESSO = "Funcionário Cadastrado com Sucesso.";
	final String MSG_CADASTRO_ERRO = "Erro ao cadastrar usuario: ";

	@EJB
	private FuncionarioService funcionarioService;

	@EJB
	private TipoFuncionarioDao tipoFuncionarioDao;

	/**
	 * VARIAVEIS
	 * 
	 * 
	 */

	public String teste;

	private Funcionario funcionario;

	private List<Funcionario> listaFuncionarios;

	private List<String> listaCargos;

	@PostConstruct
	public void init() {
		try {
			funcionario = new Funcionario();
			listaFuncionarios = funcionarioService.listarTodos();
			this.inicializaCombos();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void limpaCampos() {
		funcionario = new Funcionario();
		inicializaCombos();
	}

	public void inicializaCombos() {
		listaCargos = Arrays.asList("Gerente", "Vendedor", "Estoquista", "Administrador");

	}

	public void filtrarFuncionarios() {
		listaFuncionarios = funcionarioService.filtaFuncionarios(funcionario);
	}

	public void salvarOuAtualizar() {

	}

	public void cadastrarFuncionario() {

		try {
			funcionarioService.cadastraFuncionario(funcionario);
			MensagensUtil.adicionaMensagemSucesso(MSG_CADASTRO_SUCESSO);
			this.limpaCampos();
		} catch (Exception e) {
			MensagensUtil.adicionaMensagemErro(MSG_CADASTRO_ERRO + e.getMessage());
		}

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

	public List<String> getListaCargos() {
		return listaCargos;
	}

	public void setListaCargos(List<String> listaCargos) {
		this.listaCargos = listaCargos;
	}

}
