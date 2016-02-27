/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumCaracteristicaEspecial {
    	NINGUNA("NIN"),
        FACTURA_COMERCIAL_DE_EXPORTACION("FCE"),
        NOTA_DE_DEBITO_CREDITO("NDC");
    
    private String codigo;
    
    private EnumCaracteristicaEspecial(String codigo){
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
