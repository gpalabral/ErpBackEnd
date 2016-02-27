package com.bap.erp.modelo.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class CpcLibroDeVentasPojo {
    
    private Long idFacturaEmitida;
    private Integer numero;
    private Date fechaFactura;
    private Integer numeroDeFactura;
    private Long numeroAutorizacion;
    private String estado;
    private Long nit;    
    private String nombreRazonSocial;
    private BigDecimal importeTotal;
    private BigDecimal importeIceIehdTasas;
    private BigDecimal exportacionesYoperacionesExentas;
    private BigDecimal ventasGravadasAtasaCero;
    private BigDecimal subtotal;
    private BigDecimal descuentoBonificacionYrebajas;
    private BigDecimal importeBaseDebitoFiscal;
    private BigDecimal debitoFiscal;
    private String codigoControl;

    public Long getIdFacturaEmitida() {
        return idFacturaEmitida;
    }

    public void setIdFacturaEmitida(Long idFacturaEmitida) {
        this.idFacturaEmitida = idFacturaEmitida;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Integer getNumeroDeFactura() {
        return numeroDeFactura;
    }

    public void setNumeroDeFactura(Integer numeroDeFactura) {
        this.numeroDeFactura = numeroDeFactura;
    }

    public Long getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(Long numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }        

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }   

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public BigDecimal getImporteIceIehdTasas() {
        return importeIceIehdTasas;
    }

    public void setImporteIceIehdTasas(BigDecimal importeIceIehdTasas) {
        this.importeIceIehdTasas = importeIceIehdTasas;
    }

    public BigDecimal getExportacionesYoperacionesExentas() {
        return exportacionesYoperacionesExentas;
    }

    public void setExportacionesYoperacionesExentas(BigDecimal exportacionesYoperacionesExentas) {
        this.exportacionesYoperacionesExentas = exportacionesYoperacionesExentas;
    }

    public BigDecimal getVentasGravadasAtasaCero() {
        return ventasGravadasAtasaCero;
    }

    public void setVentasGravadasAtasaCero(BigDecimal ventasGravadasAtasaCero) {
        this.ventasGravadasAtasaCero = ventasGravadasAtasaCero;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDescuentoBonificacionYrebajas() {
        return descuentoBonificacionYrebajas;
    }

    public void setDescuentoBonificacionYrebajas(BigDecimal descuentoBonificacionYrebajas) {
        this.descuentoBonificacionYrebajas = descuentoBonificacionYrebajas;
    }

    public BigDecimal getImporteBaseDebitoFiscal() {
        return importeBaseDebitoFiscal;
    }

    public void setImporteBaseDebitoFiscal(BigDecimal importeBaseDebitoFiscal) {
        this.importeBaseDebitoFiscal = importeBaseDebitoFiscal;
    }

    public BigDecimal getDebitoFiscal() {
        return debitoFiscal;
    }

    public void setDebitoFiscal(BigDecimal debitoFiscal) {
        this.debitoFiscal = debitoFiscal;
    }

   
    public String getCodigoControl() {
        return codigoControl;
    }

    public void setCodigoControl(String codigoControl) {
        this.codigoControl = codigoControl;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }        
    
}
