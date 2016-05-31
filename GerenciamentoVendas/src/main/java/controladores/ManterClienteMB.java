package controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entidades.Cliente;
import servicos.ClienteService;

@ManagedBean
@SessionScoped
public class ManterClienteMB {

	@EJB
	ClienteService clienteService;

	// ------VARIAVEIS-------//

	private List<Cliente> listaClientes;

	private Cliente cliente;

	@PostConstruct
	public void init() {
		cliente = new Cliente();
	}

	public void limparCampos() {
		cliente = new Cliente();
	}

	public void cadastrarCliente() {
		clienteService.cadastraCliente(this.cliente);
	}

	public void atualizaCliente() {
		clienteService.atualizaCliente(cliente);
	}

	public void listarClientes() {
		clienteService.listarTodos();
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

}
