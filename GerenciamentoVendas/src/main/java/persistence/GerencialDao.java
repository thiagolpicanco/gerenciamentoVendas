package persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import arquitetura.Entidade;

public class GerencialDao<T extends Entidade> extends Dao<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
		
	}
	


}
