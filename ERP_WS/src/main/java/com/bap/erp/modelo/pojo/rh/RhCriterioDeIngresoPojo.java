package com.bap.erp.modelo.pojo.rh;

import java.io.Serializable;
import java.math.BigDecimal;

public class RhCriterioDeIngresoPojo implements Serializable{
    
    private Long idCriterioDeIngreso;       
    private String descripcion;
    private BigDecimal monto;

    public Long getIdCriterioDeIngreso() {
        return idCriterioDeIngreso;
    }

    public void setIdCriterioDeIngreso(Long idCriterioDeIngreso) {
        this.idCriterioDeIngreso = idCriterioDeIngreso;
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
