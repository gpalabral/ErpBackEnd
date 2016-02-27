/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumMunicipio {
    	HUATAJATA("HUA"),
        COCAPATA("COC"),
        CKOCHAS("CKO"),
        LA_PAZ("LP"),
        EL_ALTO("EAL"),
        COPACABANA("COP"),
        COCHABAMABA("CBBA"),
        QUILLACOLLO("QUI"),
        SANTA_CRUZ("SCZ"),
        COTOCA("COT");
    
    private String codigo;
    
    private EnumMunicipio(String codigo){
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
