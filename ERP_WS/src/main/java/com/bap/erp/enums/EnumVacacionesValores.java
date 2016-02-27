/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumVacacionesValores {
    	PRIMER_VALOR_VACACION_DIAS("15"),
        SEGUNDO_VALOR_VACACION_DIAS("20"),
        TERCERO_VALOR_VACACION_DIAS("30"),
        PRIMER_VALOR_LIMITE_VACACION_ANIOS("4"),
        SEGUNDO_VALOR_LIMITE_VACACION_ANIOS("9"),
        TERCERO_VALOR_LIMITE_VACACION_ANIOS("10");
    
    private String codigo;
    
    private EnumVacacionesValores(String codigo){
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
