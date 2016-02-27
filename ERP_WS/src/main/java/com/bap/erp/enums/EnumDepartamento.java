/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumDepartamento {
    	LA_PAZ("LP"),
        ORURO("OR"),
        POTOSI("POT"),
        COCHABAMBA("CBBA"),
        SUCRE("SUC"),
        TARIJA("TAR"),
    	BENI("BEN"),
        PANDO("PAN"),
        SANTA_CRUZ("SCZ");
    
    private String codigo;
    
    private EnumDepartamento(String codigo){
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
