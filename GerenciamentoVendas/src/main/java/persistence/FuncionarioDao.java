package persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Funcionario;
import entidades.Produto;

@Stateless
public class FuncionarioDao extends GerencialDao<Funcionario> {

	public List<Funcionario> filtrarFuncionarios(Funcionario filtro) {
		List<Funcionario> listaFuncionarios;

		StringBuilder hql = new StringBuilder();
		hql.append("select p from Funcionario p ");
		hql.append("where p.id is not null ");

		if (null != filtro.getNome() && !filtro.getNome().isEmpty()) {
			hql.append(" and p.nome = :nome");
		}

		if (null != filtro.getCpf() && !filtro.getCpf().isEmpty()) {
			hql.append(" and p.cpf= :cpf");
		}

		if (null != filtro.getCargo() && !filtro.getCargo().isEmpty()) {
			hql.append(" and p.cargo = :tipo");
		}

		if (null != filtro.getEmail() && !filtro.getEmail().isEmpty()) {
			hql.append(" and p.email = :email");
		}

		Query query = getEntityManager().createQuery(hql.toString());
		if (null != filtro.getNome() && !filtro.getNome().isEmpty()) {
			query.setParameter("nome", filtro.getNome());
		}

		if (null != filtro.getCpf() && !filtro.getCpf().isEmpty()) {
			query.setParameter("cpf", filtro.getCpf());
		}

		if (null != filtro.getCargo() && !filtro.getCargo().isEmpty()) {
			query.setParameter("tipo", filtro.getCargo());
		}

		if (null != filtro.getEmail() && !filtro.getEmail().isEmpty()) {
			query.setParameter("email", filtro.getEmail());
		}

		try {
			listaFuncionarios = query.getResultList();
			return listaFuncionarios;
		} catch (Exception e) {
			return null;
		}

	}

}
