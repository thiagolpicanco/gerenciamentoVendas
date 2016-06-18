package servicos;

import java.util.List;

import javax.ejb.EJB;
/**
 * 
 * @author thiago.picanco
 *
 */
import javax.ejb.Stateless;

import entidades.Venda;
import exceptions.PersistenciaException;
import persistence.VendaDao;

@Stateless
public class VendaService {
	@EJB
	private VendaDao vendaDao;

	// public void efetuarVenda(Venda venda) {
	// venda.setValorTotal(this.getValorTotalVenda(venda));
	//
	// }
	//
	// public Double getValorTotalVenda(Venda venda) {
	// Double total = 0d;
	// for (ProdutoEstoque produtoEstoque : venda.getListaProdutos()) {
	// total += produtoEstoque.getValorVenda();
	// }
	//
	// return total;
	// }

	public List<Venda> filtraVenda(Venda venda) {
		return vendaDao.filtrarVendas(venda);
	}

	public void cadastraVenda(Venda venda) throws Exception {

		vendaDao.gravarOuAtualizar(venda);

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
