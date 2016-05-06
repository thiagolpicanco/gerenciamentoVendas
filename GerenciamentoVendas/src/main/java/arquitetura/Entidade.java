package arquitetura;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import arquitetura.Serializar;


public class Entidade extends Serializar {
    private static final long serialVersionUID = -2668212522464760588L;
    private transient Field atributoPk = null;
    private transient List<Field> listaAtributos = null;
    private transient List<Field> listaAtributosRelacionados = null;

 
    public List<Field> getListaAtributos() {
	if (this.listaAtributos == null) {
	    this.listaAtributos = new ArrayList<Field>();
	    Field[] atributos = super.getClazz().getDeclaredFields();
	    if ((atributos != null) && (atributos.length > 0)) {
		for (Field atributo : atributos) {
		    if ((atributo.isAnnotationPresent(Column.class))
			    || (atributo.isAnnotationPresent(JoinColumn.class))
			    || (atributo.isAnnotationPresent(EmbeddedId.class))) {
			this.listaAtributos.add(atributo);
		    }
		}
	    }
	}
	return this.listaAtributos;
    }

  
    public List<Field> getListaAtributosRelacioandos() {
	if (this.listaAtributosRelacionados == null) {
	    this.listaAtributosRelacionados = new ArrayList<Field>();
	    Field[] atributos = super.getClazz().getDeclaredFields();
	    if ((atributos != null) && (atributos.length > 0)) {
		for (Field atributo : atributos) {
		    if (atributo.isAnnotationPresent(OneToMany.class)) {
			this.listaAtributosRelacionados.add(atributo);
		    }
		}
	    }
	}
	return this.listaAtributosRelacionados;
    }

   
    public Field getAtributoPk() {
	if (this.atributoPk == null) {
	    List<Field> listaAtributos = getListaAtributos();
	    for (Field field : listaAtributos) {
		if ((field.isAnnotationPresent(Id.class)) || (field.isAnnotationPresent(EmbeddedId.class))) {
		    this.atributoPk = field;
		    break;
		}
	    }
	}
	return this.atributoPk;
    }

   
    public Serializable getValorPk() {
	return (Serializable) super.getValorAtributo(getAtributoPk());
    }

    public int hashCode() {
	Serializable pkValue = getValorPk();
	if (pkValue != null) {
	    return pkValue.hashCode();
	}
	int prime = 31;
	int hash = 17;
	List<Field> listaAtributos = getListaAtributos();
	if ((listaAtributos != null) && (!listaAtributos.isEmpty())) {
	    for (Field atributo : listaAtributos) {
		Object fieldValue = getValorAtributo(atributo);
		hash = prime * hash + (fieldValue != null ? fieldValue.hashCode() : 0);
	    }
	}
	return hash;
    }

  
    public boolean ehNovo() {
	return getValorPk() == null;
    }

    
    public String verificarAtributoNaoNulo() {
	List<Field> listaAtritbutos = getListaAtributos();
	if ((listaAtritbutos != null) && (!listaAtritbutos.isEmpty())) {
	    for (Field atributo : listaAtritbutos) {
		boolean nullable = true;
		if (atributo.isAnnotationPresent(Column.class)) {
		    nullable = ((Column) atributo.getAnnotation(Column.class)).nullable();
		} else if (atributo.isAnnotationPresent(JoinColumn.class)) {
		    nullable = ((JoinColumn) atributo.getAnnotation(JoinColumn.class)).nullable();
		}
		if (!nullable) {
		    Object fieldValue = super.getValorAtributo(atributo);
		    if (fieldValue == null) {
			return atributo.getName();
		    }
		    if (((fieldValue instanceof String)) && (fieldValue.toString().trim().isEmpty())) {
			return atributo.getName();
		    }
		}
	    }
	}
	return null;
    }

   
    public boolean verificarAtributoRelacionamento() {
	List<Field> listaAtributos = getListaAtributos();
	if ((listaAtributos != null) && (!listaAtributos.isEmpty())) {
	    for (Field atributo : listaAtributos) {
		if (atributo.isAnnotationPresent(JoinColumn.class)) {
		    return true;
		}
	    }
	}
	return false;
    }

    
    public void iniciarFilhos() throws InstantiationException, IllegalAccessException{
	List<Field> listaAtributos = getListaAtributos();
	if ((listaAtributos != null) && (!listaAtributos.isEmpty())) {
	    for (Field atributo : listaAtributos) {
		if (atributo.isAnnotationPresent(JoinColumn.class)) {
		    Object valorAtributo = getValorAtributo(atributo);
		    if (valorAtributo == null) {
			@SuppressWarnings("unchecked")
			Class<? extends Entidade> atributoClazz = (Class<? extends Entidade>) atributo
				.getGenericType();
			valorAtributo = atributoClazz.newInstance();
		    }
		    ((Entidade) valorAtributo).iniciarFilhos();
		}
	    }
	}
    }

    
    public void limpar() {
	List<Field> listaAtributos = getListaAtributos();
	if ((listaAtributos != null) && (!listaAtributos.isEmpty())) {
	    for (Field atributo : listaAtributos) {
		atributo.setAccessible(true);
		try {
		    atributo.set(this, null);
		} catch (Throwable t) {
		    break;
		}
	    }
	}
    }
}
