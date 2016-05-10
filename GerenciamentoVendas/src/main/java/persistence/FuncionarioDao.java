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

		if (null != filtro.getId() && !filtro.getId().equals(0)) {
			hql.append(" and p.id = :id ");
		}

		if (null != filtro.getCpfCnpj() && !filtro.getCpfCnpj().isEmpty()) {
			hql.append(" and p.cpfCnpj = :cpf");
		}

		if (null != filtro.getTipoFuncionario()) {
			hql.append(" and p.tipoFuncionario = :tipo");
		}

		if (null != filtro.getEmail() && !filtro.getEmail().isEmpty()) {
			hql.append(" and p.email = :email");
		}

		Query query = getEntityManager().createQuery(hql.toString());
		if (null != filtro.getNome() && !filtro.getNome().isEmpty()) {
			query.setParameter("nome", filtro.getNome());
		}

		if (null != filtro.getId() && !filtro.getId().equals(0)) {
			query.setParameter("id", filtro.getId());
		}

		if (null != filtro.getCpfCnpj() && !filtro.getCpfCnpj().isEmpty()) {
			query.setParameter("cpf", filtro.getCpfCnpj());
		}

		if (null != filtro.getTipoFuncionario()) {
			query.setParameter("tipo", filtro.getTipoFuncionario());
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
