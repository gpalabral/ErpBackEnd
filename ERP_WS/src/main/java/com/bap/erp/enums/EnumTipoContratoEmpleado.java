/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumTipoContratoEmpleado {
        CONTRATO_INDEFINIDO("IND"),    	
        CONTRATO_A_PLAZO_FIJO("FIJ");
    
    private String codigo;
    
    private EnumTipoContratoEmpleado(String codigo){
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
