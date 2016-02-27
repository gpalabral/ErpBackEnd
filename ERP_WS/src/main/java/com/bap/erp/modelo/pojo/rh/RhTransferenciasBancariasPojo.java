package com.bap.erp.modelo.pojo.rh;

import java.io.Serializable;
import java.math.BigDecimal;

public class RhTransferenciasBancariasPojo implements Serializable{
    
    private Long idEmpleadoCargo;       
    private String nombreCompleto;
    private String nroCuenta;
    private BigDecimal montoAbonar;

    public Long getIdEmpleadoCargo() {
        return idEmpleadoCargo;
    }

    public void setIdEmpleadoCargo(Long idEmpleadoCargo) {
        this.idEmpleadoCargo = idEmpleadoCargo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }   

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public BigDecimal getMontoAbonar() {
        return montoAbonar;
    }

    public void setMontoAbonar(BigDecimal montoAbonar) {
        this.montoAbonar = montoAbonar;
    }

    
}
