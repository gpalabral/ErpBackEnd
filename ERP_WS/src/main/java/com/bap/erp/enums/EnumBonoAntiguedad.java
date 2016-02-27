/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumBonoAntiguedad {
        UN_MINIMO_NACIONAL("1MIN"),
        TRES_MINIMOS_NACIONALES("3MIN"),
        SUELDO_BASICO("SUBC"),
        NINGUNO("NING");
    
    private String codigo;
    
    private EnumBonoAntiguedad(String codigo){
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
