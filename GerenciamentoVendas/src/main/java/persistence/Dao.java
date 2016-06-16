package persistence;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Query;

import arquitetura.Entidade;
import exceptions.PersistenciaException;
import util.UtilMensagem;

public abstract class Dao<T> {
	public static final byte NADA = 0;
	public static final byte GRAVAR = 1;
	public static final byte ATUALIZAR = 2;
	public static final byte EXCLUIR = 3;
	private transient Class<T> classeEntidade = null;
	private transient StringBuilder consultaListaTudoSB = null;
	/**
	 * 
	 */
	private static UtilMensagem UTIL_MENSAGEM = new UtilMensagem("propriedades/persistencia");

	protected abstract EntityManager getEntityManager();

	public void flush() throws PersistenciaException {
		try {
			getEntityManager().flush();
		} catch (Exception e) {
			throw new PersistenciaException(UTIL_MENSAGEM.obterMensagem("caixadao.001"), e);
		}
	}

	public void gravar(T entidade) throws PersistenciaException {
		gravar(entidade, true);
	}

	public void gravar(T entidade, boolean flush) throws PersistenciaException {
		if (entidade != null) {
			String nomeAtributo = ((Entidade) entidade).verificarAtributoNaoNulo();
			if (nomeAtributo != null) {
				throw new PersistenciaException(UTIL_MENSAGEM.obterMensagem("caixadao.002", nomeAtributo));
			}
			getEntityManager().persist(entidade);
			if (flush) {
				flush();
			}
		}
	}

	public void gravar(List<T> listaEntidades) throws PersistenciaException {
		gravar(listaEntidades, true);
	}

	public void gravar(List<T> listaEntidades, boolean flush) throws PersistenciaException {
		if ((listaEntidades != null) && (!listaEntidades.isEmpty())) {
			for (T t : listaEntidades) {
				gravar(t, false);
			}
			if (flush) {
				flush();
			}
		}
	}

	public T atualizar(T entidade) throws PersistenciaException {
		return atualizar(entidade, true);
	}

	public T atualizar(T entidade, boolean flush) throws PersistenciaException {
		if (entidade != null) {
			String nomeAtributo = ((Entidade) entidade).verificarAtributoNaoNulo();
			if (nomeAtributo != null) {
				throw new PersistenciaException(UTIL_MENSAGEM.obterMensagem("caixadao.002", nomeAtributo));
			}
			T retorno = (T) getEntityManager().merge(entidade);
			if (flush) {
				flush();
			}
			return retorno;
		}
		return null;
	}

	public void atualizar(List<T> listaEntidades) throws PersistenciaException {
		atualizar(listaEntidades, true);
	}

	public void atualizar(List<T> listaEntidades, boolean flush) throws PersistenciaException {
		if ((listaEntidades != null) && (!listaEntidades.isEmpty())) {
			for (T t : listaEntidades) {
				atualizar(t, false);
			}
			if (flush) {
				flush();
			}
		}
	}

	public byte gravarOuAtualizar(T entidade) throws PersistenciaException {
		return gravarOuAtualizar(entidade, true);
	}

	public byte gravarOuAtualizar(T entidade, boolean flush) throws PersistenciaException {
		byte retorno = 0;
		if (entidade != null) {
			String nomeAtributo = ((Entidade) entidade).verificarAtributoNaoNulo();
			if (nomeAtributo != null) {
				throw new PersistenciaException(UTIL_MENSAGEM.obterMensagem("caixadao.002", nomeAtributo));
			}
			if (((Entidade) entidade).ehNovo()) {
				getEntityManager().persist(entidade);
				retorno = GRAVAR;
			} else {
				getEntityManager().merge(entidade);
				retorno = ATUALIZAR;
			}
			if (flush) {
				flush();
			}
		}
		return retorno;
	}

	public void gravarOuAtualizar(List<T> listaEntidades) throws PersistenciaException {
		gravarOuAtualizar(listaEntidades, true);
	}

	public void gravarOuAtualizar(List<T> listaEntidades, boolean flush) throws PersistenciaException {
		if ((listaEntidades != null) && (!listaEntidades.isEmpty())) {
			for (T entidade : listaEntidades) {
				gravarOuAtualizar(entidade, false);
			}
			if (flush) {
				flush();
			}
		}
	}

	public byte excluir(T entidade) throws PersistenciaException {
		return excluir(entidade, true);
	}

	public byte excluir(T entidade, boolean flush) throws PersistenciaException {
		byte retornar = 0;
		if ((entidade != null) && (!((Entidade) entidade).ehNovo())) {
			Entidade entidadeExcluir = (Entidade) consultar(((Entidade) entidade).getValorPk());
			if (entidadeExcluir != null) {
				getEntityManager().remove(entidadeExcluir);
				retornar = EXCLUIR;
				if (flush) {
					flush();
				}
			}
		}
		return retornar;
	}

	public void excluir(List<T> listaEntidades) throws PersistenciaException {
		excluir(listaEntidades, true);
	}

	public void excluir(List<T> listaEntidades, boolean flush) throws PersistenciaException {
		if ((listaEntidades != null) && (!listaEntidades.isEmpty())) {
			for (T entidade : listaEntidades) {
				excluir(entidade, false);
			}
			if (flush) {
				flush();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Class<T> getClasseEntidade() {
		if (this.classeEntidade == null) {
			try {
				Type tipoSuperClasseGenerica = getClass().getGenericSuperclass();
				Field tipoArgumentoAtributoAtual = tipoSuperClasseGenerica.getClass()
						.getDeclaredField("actualTypeArguments");
				tipoArgumentoAtributoAtual.setAccessible(true);
				Type[] tipoArgumentoValorAtual = (Type[]) tipoArgumentoAtributoAtual.get(tipoSuperClasseGenerica);
				this.classeEntidade = ((Class<T>) tipoArgumentoValorAtual[0]);
			} catch (Throwable t) {
				this.classeEntidade = null;
			}
		}
		return this.classeEntidade;
	}

	public T consultar(Serializable pk) {
		if (pk != null) {
			try {
				return (T) getEntityManager().find(getClasseEntidade(), pk);
			} catch (Throwable t) {
				return null;
			}
		}
		return null;
	}

	public T consultar(Entidade entidade) {
		List<T> listaResultados = listar(entidade);
		if ((listaResultados != null) && (!listaResultados.isEmpty())) {
			return (T) listaResultados.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public T consultarEntidadesFilhas(Serializable valorPk) {
		if (valorPk != null) {
			try {
				StringBuilder consulta = new StringBuilder(getConsultaListaTudoSB().toString());
				Field[] atributos = getClasseEntidade().getDeclaredFields();
				String nomePk = "";
				for (Field atributo : atributos) {
					if (atributo.isAnnotationPresent(JoinColumn.class)) {
						consulta.append(" LEFT JOIN FETCH entidade." + atributo.getName() + " " + atributo.getName());
						consultarEntidadesFilhas(consulta, atributo);
					} else if (atributo.isAnnotationPresent(OneToMany.class)) {
						consulta.append(" LEFT JOIN FETCH entidade." + atributo.getName() + " " + atributo.getName());
					} else if (atributo.isAnnotationPresent(Id.class)) {
						nomePk = atributo.getName();
					}
				}
				consulta.append(" WHERE entidade." + nomePk + " = :" + nomePk);
				Query query = getEntityManager().createQuery(consulta.toString());
				query.setParameter(nomePk, valorPk);
				return (T) query.getSingleResult();
			} catch (Throwable t) {
				return null;
			}
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private StringBuilder consultarEntidadesFilhas(StringBuilder consulta, Field atributo) {
		Class<? extends Entidade> classeAtributo = (Class) atributo.getGenericType();
		Field[] atributos = classeAtributo.getDeclaredFields();
		for (Field atributoFilho : atributos) {
			if (atributoFilho.isAnnotationPresent(JoinColumn.class)) {
				consulta.append(" LEFT JOIN FETCH " + atributo.getName() + "." + atributoFilho.getName() + " "
						+ atributoFilho.getName());
				consultarEntidadesFilhas(consulta, atributoFilho);
			}
		}
		return consulta;
	}

	private StringBuilder getConsultaListaTudoSB() {
		if (this.consultaListaTudoSB == null) {
			this.consultaListaTudoSB = new StringBuilder();
			this.consultaListaTudoSB
					.append("SELECT entidade FROM " + getClasseEntidade().getSimpleName() + " entidade");
		}
		return this.consultaListaTudoSB;
	}

	@SuppressWarnings("unchecked")
	public List<T> listarTudo() {
		return getEntityManager().createQuery(getConsultaListaTudoSB().toString()).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> listaTodosOrdenadoPor(String... ordernarAtributo) {
		StringBuilder consulta = new StringBuilder(getConsultaListaTudoSB().toString());
		if ((ordernarAtributo != null) && (ordernarAtributo.length > 0)) {
			consulta.append(" ORDER BY ");
			for (int i = 0; i < ordernarAtributo.length; i++) {
				if (i > 0) {
					consulta.append(", ");
				}
				consulta.append("entidade." + ordernarAtributo[i]);
			}
		}
		Query query = getEntityManager().createQuery(consulta.toString());
		return query.getResultList();
	}

	public List<T> listar(Entidade entidade) {
		return listaTodosOrdenadoPor(entidade, new String[0]);
	}

	@SuppressWarnings("unchecked")
	public List<T> listaTodosOrdenadoPor(Entidade entidade, String... ordendarAtributo) {
		StringBuilder consulta = new StringBuilder(getConsultaListaTudoSB() + " WHERE 1 = 1");
		HashMap<String, Object> filtroMap = new HashMap<String, Object>();
		if (entidade != null) {
			for (Field atributo : entidade.getListaAtributos()) {
				Object valorAtributo = entidade.getValorAtributo(atributo);
				if (valorAtributo != null) {
					if ((valorAtributo instanceof String)) {
						if (!((String) valorAtributo).trim().isEmpty()) {
							consulta.append(
									" AND UPPER(entidade." + atributo.getName() + ") LIKE :" + atributo.getName());
							filtroMap.put(atributo.getName(),
									"%" + valorAtributo.toString().trim().toUpperCase() + "%");
						}
					} else if ((valorAtributo instanceof Entidade)) {
						if (((Entidade) valorAtributo).ehNovo()) {
							consulta.append(" AND entidade." + atributo.getName() + " IS NULL");
						} else {
							consulta.append(" AND entidade." + atributo.getName() + " = :" + atributo.getName());
							filtroMap.put(atributo.getName(), valorAtributo);
						}
					} else {
						consulta.append(" AND entidade." + atributo.getName() + " = :" + atributo.getName());
						filtroMap.put(atributo.getName(), valorAtributo);
					}
				}
			}
		}
		if ((ordendarAtributo != null) && (ordendarAtributo.length > 0)) {
			consulta.append(" ORDER BY ");
			for (int i = 0; i < ordendarAtributo.length; i++) {
				if (i > 0) {
					consulta.append(", ");
				}
				consulta.append("entidade." + ordendarAtributo[i]);
			}
		}
		Query query = getEntityManager().createQuery(consulta.toString());
		for (String fieldName : filtroMap.keySet()) {
			query.setParameter(fieldName, filtroMap.get(fieldName));
		}
		return query.getResultList();
	}

	protected List<T> consultaPorAtributoUnico(Entidade entidade, Field atributo) {
		List<Field> listaAtributos = new ArrayList<Field>();
		listaAtributos.add(atributo);
		return consultaPorAtributoUnico(entidade, listaAtributos);
	}

	@SuppressWarnings("unchecked")
	protected List<T> consultaPorAtributoUnico(Entidade entidade, List<Field> listaAtributos) {
		if ((entidade != null) && (listaAtributos != null) && (!listaAtributos.isEmpty())) {
			StringBuilder consulta = new StringBuilder(getConsultaListaTudoSB() + " WHERE 1 = 1 ");
			HashMap<String, Object> filtroMap = new HashMap<String, Object>();
			if ((!entidade.ehNovo()) && (entidade.getAtributoPk() != null)) {
				Field atributo = entidade.getAtributoPk();
				consulta.append(" AND entidade." + atributo.getName() + " <> :" + atributo.getName());
				filtroMap.put(atributo.getName(), entidade.getValorPk());
			}
			for (Field atributo : listaAtributos) {
				Object valueAtributo = entidade.getValorAtributo(atributo);
				if (valueAtributo != null) {
					if (((valueAtributo instanceof String)) && (!((String) valueAtributo).trim().isEmpty())) {
						consulta.append(" AND entidade." + atributo.getName() + " LIKE :" + atributo.getName());
					} else {
						consulta.append(" AND entidade." + atributo.getName() + " = :" + atributo.getName());
					}
					filtroMap.put(atributo.getName(), valueAtributo);
				}
			}
			Query query = getEntityManager().createQuery(consulta.toString());
			for (String nomeAtributo : filtroMap.keySet()) {
				query.setParameter(nomeAtributo, filtroMap.get(nomeAtributo));
			}
			return query.getResultList();
		}
		return null;
	}
}
