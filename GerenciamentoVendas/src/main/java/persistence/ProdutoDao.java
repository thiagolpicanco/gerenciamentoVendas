package persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import entidades.Produto;

@Stateless
public class ProdutoDao extends GerencialDao<Produto> {

	public List<Produto> filtrarProdutos(Produto filtro) {
		List<Produto> listaProdutos;

		StringBuilder hql = new StringBuilder();
		hql.append("select p from Produto p ");
		hql.append("where p.id is not null ");

		if (null != filtro.getNome() && !filtro.getNome().isEmpty()) {
			hql.append(" and p.nome = :nome");
		}
		

		if (null != filtro.getId() && !filtro.getId().equals(0)) {
			hql.append(" and p.id = :id ");
		}

		if (null != filtro.getFornecedor()) {
			hql.append(" and p.fornecedor = :fornecedor");
		}

		if (null != filtro.getCategoria()) {
			hql.append(" and p.categoria = :categoria");
		}

		Query query = getEntityManager().createQuery(hql.toString());
		if (null != filtro.getNome() && !filtro.getNome().isEmpty()) {
			query.setParameter("nome", filtro.getNome());
		}
		

		if (null != filtro.getId() && !filtro.getId().equals(0)) {
			query.setParameter("id", filtro.getId());
		}

		if (null != filtro.getFornecedor()) {
			query.setParameter("fornecedor", filtro.getFornecedor());
		}

		if (null != filtro.getCategoria()) {
			query.setParameter("categoria", filtro.getCategoria());
		}

		try {
			listaProdutos = query.getResultList();
			return listaProdutos;
		} catch (Exception e) {
			return null;
		}

	}

}
