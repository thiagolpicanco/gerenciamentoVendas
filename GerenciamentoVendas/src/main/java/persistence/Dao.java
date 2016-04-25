package persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;

@Stateless
public abstract class Dao<T> {

	@PersistenceContext
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	private Class<T> classe;

	public final void setClasse(Class<T> classe) {
		this.classe = classe;
	}

	public void gravarAtualizar(T obj) {
		entityManager.merge(obj);

	}

	public void deletar(T obj) {
		entityManager.remove(obj);

	}
	
	

	public abstract List<T> findAll();
		
	
	public abstract T findById();
	}


