package controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.Fornecedor;
import servicos.FornecedorService;
import util.MensagensUtil;

@ViewScoped
@ManagedBean
public class ManterFornecedorMB {

	final String MSG_FORNECEDOR_CADASTRADO = "Fornecedor cadastrado com sucesso.";
	final String MSG_FORNECEDOR_ERRO = "Fornecedor ao cadastrar produto: ";
	final String MSG_FORNECEDOR_REMOVIDO = "Fornecedor removido com sucesso";
	
	@EJB
	FornecedorService fornecedorService;

	private List<Fornecedor> listaFornecedores;

	private Fornecedor fornecedor;

	

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	@PostConstruct
	public void init() {
		fornecedor = new Fornecedor();
	}

	public void listaFornecedores() {
		listaFornecedores = fornecedorService.listarTodos();
	}

	
	
	
	
	
	
	public void cadastrarFornecedor(){
		
		try {
			fornecedorService.cadastraFornecedor(this.fornecedor);
			MensagensUtil.adicionaMensagemSucesso(MSG_FORNECEDOR_CADASTRADO);
		} catch (Exception e) {
			MensagensUtil.adicionaMensagemErro(MSG_FORNECEDOR_ERRO + e.getMessage()); 
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

}
