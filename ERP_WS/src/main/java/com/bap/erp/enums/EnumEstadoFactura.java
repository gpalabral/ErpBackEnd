/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumEstadoFactura {
    	ANULADA("A"),
    	VALIDA("V"),
        EXTRAVIADA("E"),
        NO_UTILIZADA("N"),
        EMITIDA_EN_CONTINGENCIA("C");
    
    private String codigo;
    
    private EnumEstadoFactura(String codigo){
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
