package controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import entidades.Funcionario;
import entidades.Produto;
import entidades.Venda;
import servicos.ClienteService;
import servicos.FuncionarioService;
import servicos.ProdutoService;
import servicos.VendaService;

public class ManterVendaMB {

	@EJB
	VendaService vendaService;
	@EJB
	ClienteService clienteService;
	@EJB
	ProdutoService produtoService;
	@EJB
	FuncionarioService funcionarioService;
	// -------VARIAVEIS------------//

	private Venda venda;

	private List<Venda> listaVendas;

	private Funcionario funcionarioResponsavel;

	private List<Funcionario> listaFuncionarios;
	
	private List<Produto> listaProdutos;

	@PostConstruct
	public void init() {

	}

	
	
	
	public void efetuarVenda(){
		
	}
	
	
	
	
	public void listaFuncionarios() {
		listaFuncionarios = funcionarioService.listarTodos();
	}

}
