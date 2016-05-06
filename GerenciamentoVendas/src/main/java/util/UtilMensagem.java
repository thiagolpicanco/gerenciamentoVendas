package util;



import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

 
public class UtilMensagem {

    /**
     * Atributo local, definido o padrão da aplicação
     */
    private static Locale LOCAL = new Locale("pt", "BR");
    /**
     * Atributo referencia
     */
    private List<String> referencias;
    /**
     * Atributo resourceBundle
     */
    private List<ResourceBundle> resourceBundles = new ArrayList<ResourceBundle>();

    public UtilMensagem(String referencia) {
	referencias = new ArrayList<String>();
	this.referencias.add(referencia);
	resourceBundles.add(ResourceBundle.getBundle(referencia, LOCAL));
    }

  
    public UtilMensagem(List<String> referencias) {
	this.referencias = referencias;
	for (String referencia : referencias) {
	    resourceBundles.add(ResourceBundle.getBundle(referencia, LOCAL));
	}
    }

    public void adicionarReferencia(String referencia) {
	this.referencias.add(referencia);
	resourceBundles.add(ResourceBundle.getBundle(referencia, LOCAL));
    }

    public String obterMensagem(String chave) {
	String mensagem = null;
	if (chave != null) {
	    for (ResourceBundle reourceBundle : this.resourceBundles) {
		try {
		    mensagem = reourceBundle.getString(chave);
		    if ((mensagem != null) && (!mensagem.isEmpty())) {
			break;
		    }
		} catch (MissingResourceException e) {
		}
	    }
	}
	if (mensagem == null) {
	    mensagem = '!' + chave + '!';
	}
	return mensagem;
    }

    /**
     * 
     * @author jonatha.chaves
     * @verison 1.0.0
     *
     *          <p>
     *          Método responsável por obter uma mensagem para chave indicada e inserir parametros na mensagem obtida.
     *          Exemplo: msm=Texto com o {0} e {1}.
     *          <p>
     * 
     * @param chave
     * @param parametros
     * @return {@link String}
     */
    public String obterMensagem(String chave, Object... parametros) {
	try {
	    return MessageFormat.format(obterMensagem(chave), parametros);
	} catch (MissingResourceException e) {
	    return '!' + chave + '!';
	}
    }

    /**
     * 
     * @author jonatha.chaves
     * @verison 1.0.0
     *
     *          <p>
     *          Método responsável por alterar o idioma da mensagem. Importante lembrar que o arquivo precisa existir
     *          para vínculo ocorrer.
     *          <p>
     *
     * @param local
     */
    public void setLocalidade(final Locale local) {
	List<ResourceBundle> resourceBundles = new ArrayList<ResourceBundle>();
	for (String referencia : referencias) {
	    resourceBundles.add(ResourceBundle.getBundle(referencia, local));
	}
	this.resourceBundles = resourceBundles;
    }

}
