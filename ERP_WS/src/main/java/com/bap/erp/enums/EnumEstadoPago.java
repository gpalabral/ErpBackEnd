/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumEstadoPago {
    	PENDIENTE("PEND"),
//        PAGADO("PAG"),
    	BANCARIZADO("BAN"),
    	NO_BANCARIZADO("NBAN"),
        FACTURADO("FACT"),
        MORA("MOR");
    
    private String codigo;
    
    private EnumEstadoPago(String codigo){
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
