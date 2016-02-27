package com.bap.erp.modelo.pojo;

import com.bap.erp.modelo.ErpFactura;
import java.math.BigDecimal;

public class CpcFacturaEmitidaPojo {

    private ErpFactura cpcFacturaEmitida;
    private String nombre;
    private String nroContrato;
    private String nroContratoCliente;
//    private Float saldoPrimeraMoneda;
//    private Float saldoSegundaMoneda;
    private BigDecimal saldoPrimeraMoneda;
    private BigDecimal saldoSegundaMoneda;

    public ErpFactura getCpcFacturaEmitida() {
        return cpcFacturaEmitida;
    }

    public void setCpcFacturaEmitida(ErpFactura cpcFacturaEmitida) {

        this.cpcFacturaEmitida = cpcFacturaEmitida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNroContrato() {        
        return nroContrato;
    }

    public void setNroContrato(String nroContrato) {
        this.nroContrato = nroContrato;
    }

    public String getNroContratoCliente() {        
        return nroContratoCliente;
    }

    public void setNroContratoCliente(String nroContratoCliente) {
        this.nroContratoCliente = nroContratoCliente;
    }

    public BigDecimal getSaldoPrimeraMoneda() {
        return saldoPrimeraMoneda;
    }

    public void setSaldoPrimeraMoneda(BigDecimal saldoPrimeraMoneda) {
        this.saldoPrimeraMoneda = saldoPrimeraMoneda;
    }

    public BigDecimal getSaldoSegundaMoneda() {
        return saldoSegundaMoneda;
    }

    public void setSaldoSegundaMoneda(BigDecimal saldoSegundaMoneda) {
        this.saldoSegundaMoneda = saldoSegundaMoneda;
    }

   
        
}
