package arquitetura;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;

import util.UtilData;


/**
 * 
 * @author jonatha.chaves
 * @version 1.0.0
 *
 *          <p>
 *          CaixaSerializable
 *          </p>
 *          <p>
 *          Descrição: Classe responsável por Serializar Entidades
 *          </p>
 *          <br>
 *          <b>Empresa:</b> Cef - Caixa Econômica Federal
 *
 */
public class Serializar implements Serializable {

	/**
	 * Serial da Classe CaixaSerializable
	 */
	private static final long serialVersionUID = -4369220260112188596L;

	/**
	 * 
	 */
	private transient Class<?> clazz = null;

	/**
	 * 
	 * @author jonatha.chaves
	 * @version 1.0.0
	 *
	 *          <p>
	 *          Método responsável por recuperar a classe da Entidade
	 *          <p>
	 * @return {@link Class<?>}
	 */
	public Class<?> getClazz() {
		if (this.clazz == null) {
			if (getClass().getName().contains("javassist")) {
				this.clazz = getClass().getSuperclass();
			} else {
				this.clazz = getClass();
			}
		}
		return this.clazz;
	}

	/**
	 * 
	 * @author jonatha.chaves
	 * @version 1.0.0
	 *
	 *          <p>
	 *          Método responsável por recuperar o valor do atributo
	 *          <p>
	 * @param atributo
	 * @return {@link Object}
	 */
	public Object getValorAtributo(Field atributo) {
		if (atributo == null) {
			return null;
		}
		try {
			atributo.setAccessible(true);
			return atributo.get(this);
		} catch (Throwable t) {
		}
		return null;
	}

	/**
	 * 
	 * @author jonatha.chaves
	 * @version 1.0.0
	 *
	 *          <p>
	 *          Sobrescrita responsável por retornas os nomes e valores de cada
	 *          campo da entidade
	 *          <p>
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder toStringSB = new StringBuilder();
		toStringSB.append(getClazz().getName() + "[");
		Field[] fields = getClazz().getDeclaredFields();
		for (Field atributo : fields) {
			if ((atributo.getModifiers() != 26) && (atributo.getModifiers() != 25)) {
				Object valorAtributo = getValorAtributo(atributo);
				if ((valorAtributo instanceof Date)) {
					toStringSB.append(
							atributo.getName() + "=" + UtilData.SF_DD_MM_AAAA_HH_MM_SS.format(valorAtributo) + ",");
				} else if ((valorAtributo instanceof Collection)) {
					toStringSB.append(atributo.getName() + ".size=" + ((Collection<?>) valorAtributo).size() + ",");
				} else {
					toStringSB.append(atributo.getName() + "=" + valorAtributo + ",");
				}
			}
		}
		if (toStringSB.charAt(toStringSB.length() - 1) == ',') {
			toStringSB.deleteCharAt(toStringSB.length() - 1);
		}
		toStringSB.append("]");
		return toStringSB.toString();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if ((other != null & getClazz().isInstance(other))) {
			return hashCode() == other.hashCode();
		}
		return false;
	}

	/**
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 83;
		Field[] atributos = getClazz().getDeclaredFields();
		if (atributos != null) {
			for (Field atributo : atributos) {
				Object fieldValue = getValorAtributo(atributo);
				hash += 17 * (fieldValue != null ? fieldValue.hashCode() : 0);
			}
		}
		return hash;
	}
}
