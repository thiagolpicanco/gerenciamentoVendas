package controladores;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import entidades.Cliente;
import entidades.Cliente;
import servicos.ClienteService;
import util.MensagensUtil;

@ManagedBean
@ViewScoped
public class ManterClienteMB {

	@EJB
	ClienteService clienteService;

	final String MSG_CADASTRO_SUCESSO = "Cliente Cadastrado com Sucesso.";
	final String MSG_EDITADO_SUCESSO = "Cliente Editado com Sucesso.";
	final String MSG_CADASTRO_ERRO = "Erro na operação:  ";
	final String MSG_CADASTRO_CPF_ERRO = "CPF já cadastrado para um Cliente. ";

	// ------VARIAVEIS-------//

	private List<Cliente> listaClientes;

	private Cliente cliente;

	private String tipoVisao;

	@PostConstruct
	public void init() {

		if (getFlash().get("cli") != null) {
			Object obj[] = (Object[]) getFlash().get("cli");
			cliente = (Cliente) obj[0];
			tipoVisao = (String) obj[1];

		} else {
			cliente = new Cliente();
			listarClientes();
		}
	}

	public void limparCampos() {
		cliente = new Cliente();
	}

	public Flash getFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	public void redirecionaVisualizar(Cliente cliente) {
		try {
			Object obj[] = { cliente, "v" };

			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("cli", obj);
			FacesContext.getCurrentInstance().getExternalContext().redirect("cadastroCliente.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void redirecionaEditar(Cliente cliente) {
		try {
			Object obj[] = { cliente, "e" };

			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("cli", obj);
			FacesContext.getCurrentInstance().getExternalContext().redirect("cadastroCliente.jsf?faces-redirect=true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cadastrarCliente() {
		try {

			if (clienteService.buscaClientePorCPF(this.cliente.getCpfCnpj()) == null) {
				clienteService.cadastraCliente(this.cliente);
				if (tipoVisao.equalsIgnoreCase("e")) {
					MensagensUtil.adicionaMensagemSucesso(MSG_EDITADO_SUCESSO);
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("consultarCliente.jsf?faces-redirect=true.jsf");
				} else {
					MensagensUtil.adicionaMensagemSucesso(MSG_CADASTRO_SUCESSO);
					this.limparCampos();
				}
			} else {
				MensagensUtil.adicionaMensagemErro(MSG_CADASTRO_CPF_ERRO);
			}

		} catch (Exception e) {
			MensagensUtil.adicionaMensagemErro(MSG_CADASTRO_ERRO + e.getMessage());
		}
	}

	public void atualizaCliente() {
		clienteService.atualizaCliente(cliente);
	}

	public void filtraClientes() {
		listaClientes = clienteService.listaPorFiltro(cliente);
	}

	public void listarClientes() {

		listaClientes = clienteService.listarTodos();
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTipoVisao() {
		return tipoVisao;
	}

	public void setTipoVisao(String tipoVisao) {
		this.tipoVisao = tipoVisao;
	}

}
