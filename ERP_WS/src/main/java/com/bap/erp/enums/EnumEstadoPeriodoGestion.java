/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Henrry Guzman
 */
public enum EnumEstadoPeriodoGestion {
    	VIGENTE("VIG"),
    	LIQUIDADO("LIQ"),
        CONTABILIZADO("CONT");
    
    private String codigo;
    
    private EnumEstadoPeriodoGestion(String codigo){
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
