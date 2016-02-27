/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumMes {
    	ENERO("1"),
        FEBRERO("2"),
        MARZO("3"),
        ABRIL("4"),
        MAYO("5"),
        JUNIO("6"),
        JULIO("7"),
        AGOSTO("8"),
        SEPTIEMBRE("9"),
        OCTUBRE("10"),
        NOVIEMBRE("11"),
        DICIEMBRE("12");
    
    private String codigo;
    
    private EnumMes(String codigo){
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
