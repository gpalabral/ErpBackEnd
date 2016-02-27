/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumBanco {
    	BANCO_NACIONALA_DE_BOLIVIA("BNB"),
    	BANCO_DE_CREDITO("BCP"),
        BANCO_MERCANTIL_SANTA_CRUZ("BMSC"),
        BANCO_SOL("BSOL");
    
    private String codigo;
    
    private EnumBanco(String codigo){
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
