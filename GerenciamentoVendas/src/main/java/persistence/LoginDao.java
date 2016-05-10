package persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import entidades.Login;
import entidades.Produto;
import entidades.ProdutoEstoque;

@Stateless
public class LoginDao extends GerencialDao<Login> {

	public Login buscaLogin(Login login) {
		Login loginRetorno;
		StringBuilder sb = new StringBuilder();

		sb.append("select l from Login l ");
		sb.append("where l.usuario = :usuario ");
		sb.append(" and l.senha = :senha ");

		Query query = getEntityManager().createQuery(sb.toString());

		query.setParameter("usuario", login.getUsuario());
		query.setParameter("senha", login.getSenha());

		try {
			loginRetorno = (Login) query.getSingleResult();
			return loginRetorno;
		} catch (Exception e) {
			throw e;
		}

	}

}
