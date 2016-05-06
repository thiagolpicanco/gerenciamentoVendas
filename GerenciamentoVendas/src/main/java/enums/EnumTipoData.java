package enums;


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
