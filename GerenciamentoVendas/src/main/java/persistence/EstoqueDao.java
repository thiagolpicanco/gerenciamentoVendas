//package persistence;
//
//import java.util.List;
//
//import javax.ejb.Stateless;
//import javax.persistence.Query;
//
//import entidades.ProdutoEstoque;
//import entidades.Produto;
//
//@Stateless
//public class EstoqueDao extends GerencialDao<ProdutoEstoque> {
//
//	public void cadastrarProdutoEstoque(ProdutoEstoque estoque) throws Exception {
//		super.gravar(estoque);
//	}
//
//	public ProdutoEstoque buscaPorTamanhoEProduto(ProdutoEstoque estoque) {
//
//		StringBuilder sb = new StringBuilder();
//
//		sb.append("select e from ProdutoEstoque e ");
//		sb.append("where e.produto = :produto ");
//		sb.append(" and e.tamanho = :tamanho ");
//
//		Query query = getEntityManager().createQuery(sb.toString());
//
//		query.setParameter("produto", estoque.getProduto());
//		query.setParameter("tamanho", estoque.getTamanho());
//
//		try {
//			estoque = (ProdutoEstoque) query.getSingleResult();
//			return estoque;
//		} catch (Exception e) {
//			return null;
//		}
//
//	}
//
//	public List<String> listaTamanhosPorProduto(Produto produto) {
//		List<String> listaTamanhos = null;
//		StringBuilder sb = new StringBuilder();
//
//		sb.append("select e.tamanho from ProdutoEstoque e ");
//		sb.append("where e.produto = :produto");
//		sb.append("select e from Estoque e ");
//		Query query = getEntityManager().createQuery(sb.toString());
//
//		query.setParameter("produto", produto);
//
//		try {
//			listaTamanhos = query.getResultList();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return listaTamanhos;
//	}
//
//}
