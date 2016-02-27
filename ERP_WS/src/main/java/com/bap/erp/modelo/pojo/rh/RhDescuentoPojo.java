package com.bap.erp.modelo.pojo.rh;

import java.io.Serializable;
import java.math.BigDecimal;

public class RhDescuentoPojo implements Serializable{
    
    private Long idDescuento;       
    private String descripcion;
    private BigDecimal monto;

    public Long getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(Long idDescuento) {
        this.idDescuento = idDescuento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }       
        
}
