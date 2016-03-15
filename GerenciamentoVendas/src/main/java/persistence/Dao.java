package persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Dao<T> {

	@PersistenceContext
	EntityManager entityManager;
	
	private Class< T > classe;
	
	public final void setClasse( Class< T > classe ){
	      this.classe = classe;
	   }

	public void gravarAtualizar(T obj) {
		entityManager.merge(obj);
	}

	
	
}
