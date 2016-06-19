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

	public Fornecedor findByCNPJ(String cnpj) {
		Fornecedor fornecedor = null;
		StringBuilder hql = new StringBuilder();
		hql.append("select p from Fornecedor p ");
		hql.append("where p.id is not null ");

		if (null != cnpj && cnpj.isEmpty()) {
			hql.append(" and p.cpfCnpj= :cnpj");
		}

		Query query = getEntityManager().createQuery(hql.toString());

		if (null != cnpj && cnpj.isEmpty()) {
			query.setParameter("cnpj", cnpj);
		}

		try {
			fornecedor = (Fornecedor) query.getSingleResult();
			return fornecedor;
		} catch (Exception e) {
			return null;
		}
	}

}
