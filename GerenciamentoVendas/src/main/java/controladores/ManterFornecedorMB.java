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
import exceptions.GerencialException;
import entidades.Fornecedor;
import servicos.FornecedorService;
import util.MensagensUtil;

@ViewScoped
@ManagedBean
public class ManterFornecedorMB {

	final String MSG_FORNECEDOR_CADASTRADO = "Fornecedor cadastrado com sucesso.";
	final String MSG_FORNECEDOR_EDITADO = "Fornecedor editado com sucesso.";
	final String MSG_FORNECEDOR_ERRO = "Erro ao cadastrar produto: ";
	final String MSG_FORNECEDOR_REMOVIDO = "Fornecedor removido com sucesso";
	final String MSG_CNPJ_CADASTRADO = "Erro: CNPJ já cadastrado";

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

		if (getFlash().get("forn") != null) {
			Object vetorDados[] = (Object[]) getFlash().get("forn");
			fornecedor = (Fornecedor) vetorDados[0];
			tipoVisao = (String) vetorDados[1];
		} else {
			fornecedor = new Fornecedor();
			tipoVisao = "p";
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

			if (tipoVisao.equalsIgnoreCase("e")) {
				if (fornecedorService.findByCNPJ(this.fornecedor.getCpfCnpj()).size() > 1) {
					throw new GerencialException(MSG_CNPJ_CADASTRADO);
				}
				fornecedorService.cadastraFornecedor(this.fornecedor);
				MensagensUtil.adicionaMensagemSucesso(MSG_FORNECEDOR_EDITADO);
				FacesContext.getCurrentInstance().getExternalContext().redirect("listaFornecedores.jsf");

			} else {
				if (fornecedorService.findByCNPJ(this.fornecedor.getCpfCnpj()).size() > 0) {
					throw new GerencialException(MSG_CNPJ_CADASTRADO);
				}
				fornecedorService.cadastraFornecedor(this.fornecedor);
				MensagensUtil.adicionaMensagemSucesso(MSG_FORNECEDOR_CADASTRADO);
				this.limparCampos();
			}

		} catch (GerencialException e) {
			MensagensUtil.adicionaMensagemErro(e.getMessage());
		} catch (Exception e) {
			MensagensUtil.adicionaMensagemErro(MSG_FORNECEDOR_ERRO + e.getMessage());
		}

	}

	public void filtrarFornecedores() {
		listaFornecedores = fornecedorService.listaPorFiltro(fornecedor);
	}

	public Flash getFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	public void redirecionaVisualizar(Fornecedor fornecedor) {
		try {
			Object obj[] = { fornecedor, "v" };

			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("forn", obj);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("cadastrarFornecedores.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void redirecionaEditar(Fornecedor fornecedor) {
		try {
			Object obj[] = { fornecedor, "e" };

			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("forn", obj);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("cadastrarFornecedores.jsf?faces-redirect=true");
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
