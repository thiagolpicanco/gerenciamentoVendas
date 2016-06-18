package controladores;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import entidades.Funcionario;
import persistence.TipoFuncionarioDao;
import servicos.FuncionarioService;
import util.MensagensUtil;

@ManagedBean
@ViewScoped
public class ManterFuncionarioMB {

	final String MSG_CADASTRO_SUCESSO = "Funcionário Cadastrado com Sucesso.";
	final String MSG_EDITADO_SUCESSO = "Funcionário Editado com Sucesso.";
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

	private String tipoVisao;

	@PostConstruct
	public void init() {
		try {
			if (getFlash().get("obj") != null) {
				Object vetorDados[] = (Object[]) getFlash().get("obj");
				funcionario = (Funcionario) vetorDados[0];
				tipoVisao = (String) vetorDados[1];
			}

			if (null == funcionario)
				funcionario = new Funcionario();
			listaFuncionarios = funcionarioService.listarTodos();
			this.inicializaCombos();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void redirecionaVisualizar(Funcionario funcionario) {
		try {
			Object obj[] = { funcionario, "v" };

			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("obj", obj);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("cadastroFuncionarios.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void redirecionaEditar(Funcionario funcionario) {
		try {
			Object obj[] = { funcionario, "e" };

			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("obj", obj);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("cadastroFuncionarios.jsf?faces-redirect=true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Flash getFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
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
			if (tipoVisao.equalsIgnoreCase("e")) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("listarFuncionarios.jsf");
				MensagensUtil.adicionaMensagemSucesso(MSG_EDITADO_SUCESSO);
			} else {
				MensagensUtil.adicionaMensagemSucesso(MSG_CADASTRO_SUCESSO);
				this.limpaCampos();
			}

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

	public String getTipoVisao() {
		return tipoVisao;
	}

	public void setTipoVisao(String tipoVisao) {
		this.tipoVisao = tipoVisao;
	}

}
