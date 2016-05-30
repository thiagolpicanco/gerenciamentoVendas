//package servicos;
//
//import java.util.List;
//
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//
//import entidades.ProdutoEstoque;
//import entidades.Produto;
//import exceptions.GerencialException;
//import persistence.EstoqueDao;
//
///**
// * 
// * @author thiago.picanco
// *
// */
//
//@Stateless
//public class EstoqueService {
//
//	@EJB
//	EstoqueDao estoqueDao;
//
//	public void atualizarProdutoEstoque(ProdutoEstoque estoque) throws Exception {
//		estoqueDao.atualizar(estoque);
//	}
//
//	public List<ProdutoEstoque> listarTodosProdutosVinculados() {
//		return estoqueDao.listarTudo();
//	}
//
//	public List<String> listaTamanhosPorProduto(Produto produto) {
//		return estoqueDao.listaTamanhosPorProduto(produto);
//	}
//
//	public void vinculaProdutoEstoque(ProdutoEstoque estoque) throws GerencialException, Exception {
//		if (!isDuplicado(estoque)) {
//			try {
//				estoqueDao.cadastrarProdutoEstoque(estoque);
//			} catch (Exception e) {
//				throw e;
//			}
//		} else {
//
//		}
//	}
//
//	public void removerVinculoProduto(ProdutoEstoque estoque) throws Exception {
//		if (estoque.getQtdAtual() > 0) {
//			throw new GerencialException("Produto não pode ser removido pois contem quantidade em estoque");
//		} else {
//			estoqueDao.excluir(estoque);
//		}
//
//	}
//
//	public void entradaEstoque(ProdutoEstoque estoque) {
//		try {
//			estoqueDao.atualizar(estoque);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void saidaEstoque(ProdutoEstoque estoque) {
//		try {
//
//		} catch (Exception e) {
//
//		}
//	}
//
//	public Boolean isDuplicado(ProdutoEstoque estoque) {
//		if (null != estoqueDao.buscaPorTamanhoEProduto(estoque)) {
//			return Boolean.TRUE;
//		} else {
//			return Boolean.FALSE;
//		}
//	}
//}
