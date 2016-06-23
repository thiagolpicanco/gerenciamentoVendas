package persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import entidades.Fornecedor;
import entidades.Funcionario;

@Stateless
public class FornecedorDao extends GerencialDao<Fornecedor> {

	public List<Fornecedor> filtrarFornecedores(Fornecedor filtro) {
		List<Fornecedor> listaFornecedores;

		StringBuilder hql = new StringBuilder();
		hql.append("select p from Fornecedor p ");
		hql.append("where p.id is not null ");

		if (filtro.getNome() != null || !filtro.getNome().isEmpty()) {
			hql.append(" and lower(p.nome) like :nome ");
		}
		if (null != filtro.getCpfCnpj() && !filtro.getCpfCnpj().isEmpty()) {
			hql.append(" and p.cpf= :cpf");
		}

		Query query = getEntityManager().createQuery(hql.toString());
		if (filtro.getNome() != null || !filtro.getNome().isEmpty()) {
			query.setParameter("nome", filtro.getNome().toLowerCase() + "%");
		}

		if (null != filtro.getCpfCnpj() && !filtro.getCpfCnpj().isEmpty()) {
			query.setParameter("cpf", filtro.getCpfCnpj());
		}

		try {
			listaFornecedores = query.getResultList();
			return listaFornecedores;
		} catch (Exception e) {
			return null;
		}

	}

	public List<Fornecedor> findByCNPJ(String cnpj) {
		List<Fornecedor> fornecedor = null;
		StringBuilder hql = new StringBuilder();
		hql.append("select p from Fornecedor p ");
		hql.append("where p.id is not null ");
		hql.append(" and p.cpfCnpj= :cnpj");

		Query query = getEntityManager().createQuery(hql.toString());

		query.setParameter("cnpj", cnpj);

		try {
			fornecedor = query.getResultList();
			return fornecedor;
		} catch (Exception e) {
			return null;
		}
	}

}
