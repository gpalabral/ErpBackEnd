/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.cpanel.enums;

/**
 *
 * @author Miriam
 */
public enum EnumPermisoEstado {
    	VIGENTE("VIG"),
	NO_VIGENTE("NVIG");
    
    private String codigo;
    
    private EnumPermisoEstado(String codigo){
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
