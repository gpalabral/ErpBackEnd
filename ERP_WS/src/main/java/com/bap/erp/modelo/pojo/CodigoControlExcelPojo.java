package com.bap.erp.modelo.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CodigoControlExcelPojo implements Serializable{

    private Long nit;
    private Long nroFactura;
    private Long nroAutorizacion;
    private Date fechaFactura;
//    private Float monto;
    private BigDecimal monto;
    private String llaveDosificacion;
    private String codigoControl;

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public Long getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(Long nroFactura) {
        this.nroFactura = nroFactura;
    }

    public Long getNroAutorizacion() {
        return nroAutorizacion;
    }

    public void setNroAutorizacion(Long nroAutorizacion) {
        this.nroAutorizacion = nroAutorizacion;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

//    public Float getMonto() {
//        return monto;
//    }
//
//    public void setMonto(Float monto) {
//        this.monto = monto;
//    }

    public String getLlaveDosificacion() {
        return llaveDosificacion;
    }

    public void setLlaveDosificacion(String llaveDosificacion) {
        this.llaveDosificacion = llaveDosificacion;
    }

    public String getCodigoControl() {
        return codigoControl;
    }

    public void setCodigoControl(String codigoControl) {
        this.codigoControl = codigoControl;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    
    
}
