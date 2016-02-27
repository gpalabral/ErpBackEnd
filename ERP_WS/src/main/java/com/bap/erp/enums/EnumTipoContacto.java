/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumTipoContacto {
        GERENTE("GER"),
    	CONTACTO_COMERCIAL("CCOM"),
        CONTACO_DE_COBRO_O_PAGO("CDCP");
    
    private String codigo;
    
    private EnumTipoContacto(String codigo){
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
