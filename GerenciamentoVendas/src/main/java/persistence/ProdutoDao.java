package persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import entidades.Produto;

@Stateless
public class ProdutoDao extends GerencialDao<Produto> {

	public Produto buscaProduto(Produto produto) throws Exception {
		return getEntityManager().find(Produto.class, produto.getId());
	}

	public List<Produto> filtrarProdutos(Produto filtro) {
		List<Produto> listaProdutos;

		StringBuilder hql = new StringBuilder();
		hql.append("select p from Produto p ");
		hql.append("where p.id.codigo is not null ");

		if (null != filtro.getNome() && !filtro.getNome().isEmpty()) {
			hql.append(" and p.nome = :nome");
		}

		if (null != filtro.getId().getCodigo() && !filtro.getId().getCodigo().equals(0)) {
			hql.append(" and p.id.codigo = :id ");
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

		if (null != filtro.getId().getCodigo() && !filtro.getId().getCodigo().equals(0)) {
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

	public Produto buscaPorTamanhoEProduto(Produto produto) {

		StringBuilder sb = new StringBuilder();

		sb.append("select e from Produto e ");
		sb.append("where e.id.codigo = :produto ");
		sb.append(" and e.id.tamanho = :tamanho ");

		Query query = getEntityManager().createQuery(sb.toString());

		query.setParameter("produto", produto.getId().getCodigo());
		query.setParameter("tamanho", produto.getId().getTamanho());

		try {
			produto = (Produto) query.getSingleResult();
			return produto;
		} catch (Exception e) {
			return null;
		}

	}

	public List<Produto> listaProdutoLike(Produto produto) {
		StringBuilder sb = new StringBuilder();

		sb.append("select e from Produto e  where e.id.codigo is not null ");
		if (produto.getId().getCodigo() != null) {
			sb.append(" and  e.id.codigo = :codigo ");
		}

		if (produto.getNome() != null || !produto.getNome().isEmpty()) {
			sb.append(" and lower(e.nome) like :nome ");
		}

		Query query = getEntityManager().createQuery(sb.toString());
		if (produto.getId().getCodigo() != null) {
			query.setParameter("codigo", produto.getId().getCodigo());
		}
		if (produto.getNome() != null || !produto.getNome().isEmpty()) {
			query.setParameter("nome", produto.getNome().toLowerCase() + "%");
		}

		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}

	}

	public List<Produto> listaProdutosPendentes() {
		List<Produto> listaProdutos;
		StringBuilder sb = new StringBuilder();

		sb.append("select e from Produto e ");
		sb.append(" where e.qtdAtual < e.qtdMinima ");

		Query query = getEntityManager().createQuery(sb.toString());

		try {
			listaProdutos = query.getResultList();
			return listaProdutos;
		} catch (Exception e) {
			return null;
		}

	}

	public List<String> listaTamanhosPorProduto(Produto produto) {
		List<String> listaTamanhos = null;
		StringBuilder sb = new StringBuilder();

		sb.append("select e.tamanho from ProdutoEstoque e ");
		sb.append("where e.produto = :produto");
		sb.append("select e from Estoque e ");
		Query query = getEntityManager().createQuery(sb.toString());

		query.setParameter("produto", produto);

		try {
			listaTamanhos = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaTamanhos;
	}

}
