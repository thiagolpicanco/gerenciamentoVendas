/**
 * Copyright (c) 2015 Caixa Econômica Federal. Todos os direitos
 * reservados.
 *
 * Caixa Econômica Federal - arquiteturajee6 - Sistema de Crédito Rural
 *
 * Este software foi desenvolvido sob demanda da CAIXA e está
 * protegido por leis de direitos autorais e tratados internacionais. As
 * condições de cópia e utilização do todo ou partes dependem de autorização da
 * empresa. Cópias não são permitidas sem expressa autorização. Não pode ser
 * comercializado ou utilizado para propósitos particulares.
 *
 * Uso exclusivo da Caixa Econômica Federal. A reprodução ou distribuição não
 * autorizada deste programa ou de parte dele, resultará em punições civis e
 * criminais e os infratores incorrem em sanções previstas na legislação em
 * vigor.
 *
 * Histórico do Subversion:
 *
 * LastChangedRevision: $Revision$
 * LastChangedBy: $Author$
 * LastChangedDate: $Date$
 *
 * HeadURL: $HeadURL$
 *
 */	
package arquitetura;


/**
 * <p>EnumTipoData</p>
 *
 * <p>Descrição: Tipos de formatação de data</p>
 *
 * <br><b>Empresa:</b> Cef - Caixa Econômica Federal
 *
 *
 * @author jonatha.chaves
 *
 * @version 1.0.0
 */
public enum EnumTipoData {
    
    
    /**
     * Atributo AAAAMMDDHHMM
     * Formato: yyyyMMddhhmm
     */
    AAAAMMDDHHMM("yyyyMMddhhmm"),    
    /**
     * Atributo SS_MM_HH_DD_MM_aaaa
     * Formato: ss_MM_hh_dd_mm_aaaa
     */
    SS_MM_HH_DD_MM_AAAA("ss mm hh dd MM yyyy"),
    /**
     * Atributo AAAA
     * Formato: aaaa
     */
    AAAA("yyyy"),
    /**
     * Atributo AAAA_MM_dd
     * Formato: aaaa-MM-dd
     */
    AAAA_MM_dd("yyyy-MM-dd"),
    /**
     * Atributo DD_MM_AAAA
     * Formato: dd/MM/aaaa
     */
    DD_MM_AAAA("dd/MM/yyyy"), 
    /**
     * Atributo DD_MM_AAAA_HH_MM_SS
     * Formato: dd/MM/aaaa HH:mm:ss
     */
    DD_MM_AAAA_HH_MM_SS("dd/MM/yyyy HH:mm:ss");

    /** Atributo tipoFormatoData. */
    private String tipoFormatoData;   
   
    /**
     * 
     * Construtor
     * 
     * Responsável pela criação de novas instâncias desta classe.
     * 
     * @param tipoFormatoData
     */
    private EnumTipoData(final String tipoFormatoData) {
	this.tipoFormatoData = tipoFormatoData;
    }
    
    /**
     * Retorna o valor do atributo tipoFormatoData.
     *
     * @return tipoFormatoData
     */
    public String getTipoFormatoData() {
        return tipoFormatoData;
    }

    /**
     * Define o valor do atributo tipoFormatoData.
     *
     * @param tipoFormatoData valor a ser atribuído
     */
    public void setTipoFormatoData(String tipoFormatoData) {
        this.tipoFormatoData = tipoFormatoData;
    }

}
