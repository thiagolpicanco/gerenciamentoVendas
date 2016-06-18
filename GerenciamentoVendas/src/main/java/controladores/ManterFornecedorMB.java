package controladores;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import entidades.Fornecedor;
import entidades.Funcionario;
import entidades.Fornecedor;
import servicos.FornecedorService;
import util.MensagensUtil;

@ViewScoped
@ManagedBean
public class ManterFornecedorMB {

	final String MSG_FORNECEDOR_CADASTRADO = "Fornecedor cadastrado com sucesso.";
	final String MSG_FORNECEDOR_EDITADO = "Fornecedor editado com sucesso.";
	final String MSG_FORNECEDOR_ERRO = "Fornecedor ao cadastrar produto: ";
	final String MSG_FORNECEDOR_REMOVIDO = "Fornecedor removido com sucesso";

	@EJB
	FornecedorService fornecedorService;

	private List<Fornecedor> listaFornecedores;

	private Fornecedor fornecedor;

	String tipoVisao;

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	@PostConstruct
	public void init() {

		if (getFlash().get("obj") != null) {
			Object vetorDados[] = (Object[]) getFlash().get("obj");
			fornecedor = (Fornecedor) vetorDados[0];
			tipoVisao = (String) vetorDados[1];
		} else {
			fornecedor = new Fornecedor();
		}
		listaFornecedores();
	}

	public void listaFornecedores() {
		listaFornecedores = fornecedorService.listarTodos();
	}

	public void limparCampos() {
		fornecedor = new Fornecedor();
	}

	public void cadastrarFornecedor() {

		try {
			fornecedorService.cadastraFornecedor(this.fornecedor);

			if (tipoVisao.equalsIgnoreCase("e")) {
				MensagensUtil.adicionaMensagemSucesso(MSG_FORNECEDOR_EDITADO);
				FacesContext.getCurrentInstance().getExternalContext().redirect("listaFornecedores.jsf");

			} else {

				MensagensUtil.adicionaMensagemSucesso(MSG_FORNECEDOR_CADASTRADO);
				this.limparCampos();
			}
		} catch (Exception e) {
			MensagensUtil.adicionaMensagemErro(MSG_FORNECEDOR_ERRO + e.getMessage());
		}

	}

	public Flash getFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	public void redirecionaVisualizar(Fornecedor fornecedor) {
		try {
			Object obj[] = { fornecedor, "v" };

			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("obj", obj);
			FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrarFornecedores.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void redirecionaEditar(Fornecedor fornecedor) {
		try {
			Object obj[] = { fornecedor, "e" };

			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("obj", obj);
			FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrarFornecedores.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Fornecedor> getListaFornecedores() {
		return listaFornecedores;
	}

	public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
		this.listaFornecedores = listaFornecedores;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getTipoVisao() {
		return tipoVisao;
	}

	public void setTipoVisao(String tipoVisao) {
		this.tipoVisao = tipoVisao;
	}

}
