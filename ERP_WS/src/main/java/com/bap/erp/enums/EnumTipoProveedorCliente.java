/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumTipoProveedorCliente {
    	NATURAL("NAT"),
	JURIDICO("JUR");
    
    private String codigo;
    
    private EnumTipoProveedorCliente(String codigo){
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
