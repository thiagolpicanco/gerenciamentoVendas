package enums;

import java.util.ArrayList;
import java.util.Collection;



public enum EnumExtensaoArquivo {

    PDF("application/pdf", "PDF", ".pdf"), XLS("application/vnd.ms-excel", "Excel", ".xls"), XLSX(
	    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "Excel", ".xlsx"), CSV("text/csv",
	    "Excel", ".csv"), DOC("application/msword", "Word", ".doc"), DOCX(
	    "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "Word", ".docx"), XML(
	    "text/xml", "Xml", ".xml"), ZIP("application/octet-stream", "Zip", ".zip"), TXT("text/plain",
	    "Bloco de Notas", ".txt"), INDEFINIDO("", "Outros Documentos", "");

    private String contentType;

    private String aplicativo;

    private String extensao;

    /**
     * 
     * @author jonatha.chaves
     * @version 1.1.0
     *
     * Construtor
     *
     * @param contentType
     * @param aplicativo
     * @param extensao
     */
    private EnumExtensaoArquivo(final String contentType, final String aplicativo, final String extensao) {
	this.contentType = contentType;
	this.aplicativo = aplicativo;
	this.extensao = extensao;
    }

    /**
     * Retorna o valor do atributo contentType.
     * 
     * @return contentType
     */
    public String getContentType() {
	return this.contentType;
    }

    /**
     * Retorna o valor do atributo aplicativo.
     * 
     * @return aplicativo
     */
    public String getAplicativo() {
	return this.aplicativo;
    }
    
    /**
     * Retorna o valor do atributo extensao.
     * 
     * @return extensao
     */
    public String getExtensao() {
	return this.extensao;
    }
    
    /**
     * 
     * @author jonatha.chaves
     * @version 1.0.0
     *
     * <p> Método responsável por retornar os possíveis valores do content type do excel <p>
     * @return {@link Collection<EnumExtensaoArquivo>}
     */
    public static Collection<EnumExtensaoArquivo> extensoesExcel() {
	final Collection<EnumExtensaoArquivo> retorno = new ArrayList<EnumExtensaoArquivo>();
	retorno.add(XLS);
	retorno.add(XLSX);
	return retorno;
    }


    /**
     * 
     * @author jonatha.chaves
     * @version 1.1.0
     *
     * <p> Método responsável por obter por ContentType <p>
     * @param contentType
     * @return {@link EnumExtensaoArquivo}
     */
    public static EnumExtensaoArquivo obterPorContentType(final String contentType) {
	EnumExtensaoArquivo retorno = EnumExtensaoArquivo.INDEFINIDO;
	for (final EnumExtensaoArquivo result : EnumExtensaoArquivo.values()) {
	    if (result.getContentType().equals(contentType)) {
		retorno = result;
	    }
	}
	return retorno;
    }
    
    /**
     * 
     * @author jonatha.chaves
     * @version 1.1.0
     *
     * <p> Método responsável por obter por Extensao <p>
     * @param extensao
     * @return {@link EnumExtensaoArquivo}
     */
    public static EnumExtensaoArquivo obterPorExtensao(final String extensao) {
	EnumExtensaoArquivo retorno = EnumExtensaoArquivo.INDEFINIDO;
	for (final EnumExtensaoArquivo result : EnumExtensaoArquivo.values()) {
	    if (result.getExtensao().equalsIgnoreCase(extensao)) {
		retorno = result;
		break;
	    }
	}
	return retorno;
    }
}