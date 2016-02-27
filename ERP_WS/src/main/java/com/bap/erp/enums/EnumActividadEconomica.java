/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumActividadEconomica {
    	VENTA_DE_EQUIPOS("VEQU"),
    	SERVICIO_DE_MANTENIMIENTO("SMAN"),
        SOLUCIONES("SOLU"),
        ALQUILERES("ALQU");
    
    private String codigo;
    
    private EnumActividadEconomica(String codigo){
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
