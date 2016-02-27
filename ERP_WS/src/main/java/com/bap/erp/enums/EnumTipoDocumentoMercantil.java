/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumTipoDocumentoMercantil {
    	FACTURA("FACT"),
    	NOTA_DE_DEBITO("NODE"),
        RECIBO("RECI"),
        POLIZA_DE_IMPORTACION("POLIM"),
        BOLETO_DE_AVION("BSP"),
        RECIBO_DE_ALQUILER("RALQ"),
        RETENCION("RRET");
    
    private String codigo;
    
    private EnumTipoDocumentoMercantil(String codigo){
        this.codigo = codigo;
    }
    
    
    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
