package persistence;

import javax.ejb.Stateless;
import javax.persistence.Query;

import entidades.Estoque;
import entidades.Produto;

@Stateless
public class EstoqueDao extends GerencialDao<Estoque> {

	public void cadastrarProdutoEstoque(Estoque estoque) throws Exception {
		super.gravar(estoque);
	}

	public Estoque buscaPorTamanhoEProduto(Estoque estoque) {
		
		StringBuilder sb = new StringBuilder();

		sb.append("select e from Estoque e ");
		sb.append("where e.produto = :produto ");
		sb.append(" and e.tamanho = :tamanho ");

		Query query = getEntityManager().createQuery(sb.toString());

		query.setParameter("produto", estoque.getProduto());
		query.setParameter("tamanho", estoque.getTamanho());

		try {
			estoque = (Estoque) query.getSingleResult();
			return estoque;
		} catch (Exception e) {
			return null;
		}

	}

}
