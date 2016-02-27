/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumCondicionPension {
    	NINGUNO("NING"),
    	JUBILADO("JUBI"),
        R_VEJEZ("RVEJ"),
        RENTISTA("RENT"),
        INVALIDEZ("INVA");
    
    private String codigo;
    
    private EnumCondicionPension(String codigo){
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
