/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumTipoAplicacionDescuentoCriterioDeIngreso {  
    	MONTO("MONT"),
    	PORCENTAJE("PORC");
    
    private String codigo;
    
    private EnumTipoAplicacionDescuentoCriterioDeIngreso(String codigo){
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

    @Override
    public String toString() {
        return codigo;
    }
    
    
}
