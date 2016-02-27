/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.enums;

/**
 *
 * @author Jonas
 */
public enum EnumTipoDocumentoPago {
    	CHEQUE("1"),
    	ORDEN_DE_TRANSFERENCIA("2"),
    	ORDEN_DE_TRANSFERENCIA_ELECTRONICA("3"),
    	TRANSFERENCIA_DE_FONDOS("4"),
    	TARJETA_DE_DEBITO("5"),
        TARJETA_DE_CREDITO("6"),
        TARJETA_PREPAGADA("7"),
        DEPOSITO_EN_CUENTA("8"),
        CARTAAS_DE_CREDITO("9"),
        OTROS("10");
    
    private String codigo;
    
    private EnumTipoDocumentoPago(String codigo){
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
