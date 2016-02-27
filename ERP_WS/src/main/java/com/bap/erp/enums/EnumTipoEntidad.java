/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumTipoEntidad {
        DESCUENTOS("DES"),
    	VARIACIONES("VAR"),
    	CRITERIO_DE_INGRESO("CIN"),
    	PRIMAS("PRI"),
    	PLANILLA_SUELDOS("PSUE"),
    	PLANILLA_IMPOSITIVA("PIMP"),
    	PLANILLA_APORTES_SEGURIDAD_SOCIAL("PASS"),
    	EMPLEADOS("EMP"),
    	RC_IVA("RCIVA");
    
    private String codigo;
    
    private EnumTipoEntidad(String codigo){
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
