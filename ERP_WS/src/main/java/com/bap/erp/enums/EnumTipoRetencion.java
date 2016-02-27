/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumTipoRetencion {
    	SIN_RETENCION("SRET"),
    	BIENES("BIEN"),
        SERVICIOS("SERV"),
        RC_IVA("RIVA"),
        ALQUILERES("ALQU"),
        REMESAS_AL_EXTERIOR("REXT"),
        IUE_IT("IUIT");
    
    private String codigo;
    
    private EnumTipoRetencion(String codigo){
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
