package persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import entidades.Cliente;
import entidades.Cliente;

@Stateless
public class ClienteDao extends GerencialDao<Cliente> {

	public List<Cliente> filtrarClientees(Cliente filtro) {
		List<Cliente> listaClientes;

		StringBuilder hql = new StringBuilder();
		hql.append("select p from Cliente p ");
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
			listaClientes = query.getResultList();
			return listaClientes;
		} catch (Exception e) {
			return null;
		}
	}

	public Cliente findByCPF(String cpf) {
		Cliente cli = null;
		StringBuilder hql = new StringBuilder();
		hql.append("select p from Cliente p ");
		hql.append("where p.id is not null ");

		if (null != cpf && cpf.isEmpty()) {
			hql.append(" and p.cpf= :cpf");
		}

		Query query = getEntityManager().createQuery(hql.toString());

		if (null != cpf && cpf.isEmpty()) {
			query.setParameter("cpf", cpf);
		}

		try {
			cli = (Cliente) query.getSingleResult();
			return cli;
		} catch (Exception e) {
			return null;
		}
	}

}
