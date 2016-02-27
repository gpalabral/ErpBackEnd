/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumTipoTransaccion {
    	VENTAS("VENT"),
    	EXPORTACIONES("EXPO"),
        OTROS("OTR"),
        COMPRA_CON_FACTURA("1"),
        COMPRA_CON_RETENCIONES("2"),
        COMPRA_DE_INMUEBLES("3");
    
    private String codigo;
    
    private EnumTipoTransaccion(String codigo){
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
