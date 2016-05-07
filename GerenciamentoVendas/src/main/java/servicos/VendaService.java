package servicos;

import java.util.List;

/**
 * 
 * @author thiago.picanco
 *
 */
import javax.ejb.Stateless;

import entidades.Venda;
import entidades.Produto;
import entidades.Venda;
import exceptions.PersistenciaException;
import persistence.VendaDao;

@Stateless
public class VendaService {

	private VendaDao vendaDao;

	public void efetuarVenda(Venda venda) {
		venda.setValorTotal(this.getValorTotalVenda(venda));

	}

	public Double getValorTotalVenda(Venda venda) {
		Double total = 0d;
		for (Produto produto : venda.getListaProdutos()) {
//			total += produto.getValorVenda();
		}

		return total;
	}

	public void geraComissao() {

	}

	public void cadastraVenda(Venda venda) {
		try {
			vendaDao.gravarOuAtualizar(venda);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletaVenda(Venda venda) {
		try {
			vendaDao.excluir(venda);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizaVenda(Venda venda) {
		try {
			vendaDao.gravarOuAtualizar(venda);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Venda> listarTodos() {
		return vendaDao.listarTudo();
	}

}
