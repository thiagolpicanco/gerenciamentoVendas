package persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import entidades.Login;
import entidades.Produto;

@Stateless
public class LoginDao extends GerencialDao<Login> {

	public Login buscaLogin(String usuario, String senha) {
		Login loginRetorno;
		StringBuilder sb = new StringBuilder();

		sb.append("select l from Login l ");
		sb.append("where l.usuario = :usuario ");
		sb.append(" and l.senha = :senha ");

		Query query = getEntityManager().createQuery(sb.toString());

		query.setParameter("usuario", usuario);
		query.setParameter("senha", senha);

		try {
			loginRetorno = (Login) query.getSingleResult();
			return loginRetorno;
		} catch (Exception e) {
			return null;
		}

	}

}
