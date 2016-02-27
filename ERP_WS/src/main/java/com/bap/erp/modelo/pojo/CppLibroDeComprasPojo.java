package com.bap.erp.modelo.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class CppLibroDeComprasPojo {
    
    private Long idFactura;
    private Integer numero;
    private Date fechaFacturaODui;
    private Long nitProveedor;     
    private String nombreRazonSocial;
    private Long numeroDeFactura;
    private String numeroDeDui;
    private Long numeroAutorizacion;  
    private BigDecimal importeTotal;
    private BigDecimal importeNoSujetoACreditoFiscal;
    private BigDecimal subtotal;
    private BigDecimal descuentoBonificacionYrebajas;
    private BigDecimal importeBaseCreditoFiscal;
    private BigDecimal creditoFiscal;
    private String codigoControl;
    private String tipoDeCompra;

    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFechaFacturaODui() {
        return fechaFacturaODui;
    }

    public void setFechaFacturaODui(Date fechaFacturaODui) {
        this.fechaFacturaODui = fechaFacturaODui;
    }

    public Long getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(Long nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public Long getNumeroDeFactura() {
        return numeroDeFactura;
    }

    public void setNumeroDeFactura(Long numeroDeFactura) {
        this.numeroDeFactura = numeroDeFactura;
    }  

    public String getNumeroDeDui() {
        return numeroDeDui;
    }

    public void setNumeroDeDui(String numeroDeDui) {
        this.numeroDeDui = numeroDeDui;
    }

    public Long getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(Long numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public BigDecimal getImporteNoSujetoACreditoFiscal() {
        return importeNoSujetoACreditoFiscal;
    }

    public void setImporteNoSujetoACreditoFiscal(BigDecimal importeNoSujetoACreditoFiscal) {
        this.importeNoSujetoACreditoFiscal = importeNoSujetoACreditoFiscal;
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

    public BigDecimal getImporteBaseCreditoFiscal() {
        return importeBaseCreditoFiscal;
    }

    public void setImporteBaseCreditoFiscal(BigDecimal importeBaseCreditoFiscal) {
        this.importeBaseCreditoFiscal = importeBaseCreditoFiscal;
    }

    public BigDecimal getCreditoFiscal() {
        return creditoFiscal;
    }

    public void setCreditoFiscal(BigDecimal creditoFiscal) {
        this.creditoFiscal = creditoFiscal;
    }

    public String getCodigoControl() {
        return codigoControl;
    }

    public void setCodigoControl(String codigoControl) {
        this.codigoControl = codigoControl;
    }

    public String getTipoDeCompra() {
        return tipoDeCompra;
    }

    public void setTipoDeCompra(String tipoDeCompra) {
        this.tipoDeCompra = tipoDeCompra;
    }
    
    
}
