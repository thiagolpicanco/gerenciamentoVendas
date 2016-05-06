package controladores;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;

import entidades.Cliente;
import persistence.ClienteDao;
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

}
