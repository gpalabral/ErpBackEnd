package com.bap.erp.modelo.pojo;

import com.bap.erp.modelo.par.ParEstadoFactura;
import java.math.BigDecimal;
import java.util.Date;

public class CpcLibroDeComprasPojo {
    
    private Integer especificacion; //Preguntar
    private Integer numero; //Preguntar
    private Date fechaNotaCreditoDebito; //Tabla Nota Crédito - Débito
    private Long numeroNotaCreditoDebito; //Tabla Nota Crédito - Débito
    private Long numeroAutorizacionNotaCreditoDebito; //Tabla Nota Crédito-Débito, esta tabla tiene el idDosificacion, entonces obtenemos Tabla Dosificacion
    private ParEstadoFactura parEstadoFacturaNotaCreditoDebito;// Tabla Nota Crédito - Débito
    private String nitCi; // Tabla Nota Crédito-Débito, obtenemos por parte del idProveedor
    private String nombreRazonSocial; // Tabla Nota Crédito-Débito, obtenemos por parte del idProveedor
    private BigDecimal montoTotalDevolucionPrimeraMonedaNotaCreditoDebito; //Tabla Nota Crédito - Débito
    private BigDecimal montoTotalDevolucionSegundaMonedaNotaCreditoDebito; //Tabla Nota Crédito - Débito
    private BigDecimal ivaNotaCreditoDebito; //Tabla Nota Crédito - Débito
    private String codigoControlNotaCreditoDebito; //Tabla Nota Crédito - Débito
    private Date fechaFacturaOriginal; //Tabla Detalle Factura, obtenemos los datos de la factura por medio del idPadre(Esta es una Factura)
    private Long numeroFacturaOriginal; //Tabla Detalle Factura, obtenemos los datos de la factura por medio del idPadre(Esta es una Factura)
    private String numeroAutorizacionFacturaOriginal; //Tabla Detalle Factura, obtenemos los datos de la factura por medio del idPadre(Esta es una Factura)
    private BigDecimal montoTotalFacturaOriginalPrimeraMoneda; //Tabla Detalle Factura, obtenemos los datos de la factura por medio del idPadre(Esta es una Factura)
    private BigDecimal montoTotalFacturaOriginalSegundaMoneda; //Tabla Detalle Factura, obtenemos los datos de la factura por medio del idPadre(Esta es una Factura)

    public Integer getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(Integer especificacion) {
        this.especificacion = especificacion;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFechaNotaCreditoDebito() {
        return fechaNotaCreditoDebito;
    }

    public void setFechaNotaCreditoDebito(Date fechaNotaCreditoDebito) {
        this.fechaNotaCreditoDebito = fechaNotaCreditoDebito;
    }

    public Long getNumeroNotaCreditoDebito() {
        return numeroNotaCreditoDebito;
    }

    public void setNumeroNotaCreditoDebito(Long numeroNotaCreditoDebito) {
        this.numeroNotaCreditoDebito = numeroNotaCreditoDebito;
    }

    public Long getNumeroAutorizacionNotaCreditoDebito() {
        return numeroAutorizacionNotaCreditoDebito;
    }

    public void setNumeroAutorizacionNotaCreditoDebito(Long numeroAutorizacionNotaCreditoDebito) {
        this.numeroAutorizacionNotaCreditoDebito = numeroAutorizacionNotaCreditoDebito;
    }

    public ParEstadoFactura getParEstadoFacturaNotaCreditoDebito() {
        return parEstadoFacturaNotaCreditoDebito;
    }

    public void setParEstadoFacturaNotaCreditoDebito(ParEstadoFactura parEstadoFacturaNotaCreditoDebito) {
        this.parEstadoFacturaNotaCreditoDebito = parEstadoFacturaNotaCreditoDebito;
    }

    public String getNitCi() {
        return nitCi;
    }

    public void setNitCi(String nitCi) {
        this.nitCi = nitCi;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public BigDecimal getMontoTotalDevolucionPrimeraMonedaNotaCreditoDebito() {
        return montoTotalDevolucionPrimeraMonedaNotaCreditoDebito;
    }

    public void setMontoTotalDevolucionPrimeraMonedaNotaCreditoDebito(BigDecimal montoTotalDevolucionPrimeraMonedaNotaCreditoDebito) {
        this.montoTotalDevolucionPrimeraMonedaNotaCreditoDebito = montoTotalDevolucionPrimeraMonedaNotaCreditoDebito;
    }

    public BigDecimal getMontoTotalDevolucionSegundaMonedaNotaCreditoDebito() {
        return montoTotalDevolucionSegundaMonedaNotaCreditoDebito;
    }

    public void setMontoTotalDevolucionSegundaMonedaNotaCreditoDebito(BigDecimal montoTotalDevolucionSegundaMonedaNotaCreditoDebito) {
        this.montoTotalDevolucionSegundaMonedaNotaCreditoDebito = montoTotalDevolucionSegundaMonedaNotaCreditoDebito;
    }

    public BigDecimal getIvaNotaCreditoDebito() {
        return ivaNotaCreditoDebito;
    }

    public void setIvaNotaCreditoDebito(BigDecimal ivaNotaCreditoDebito) {
        this.ivaNotaCreditoDebito = ivaNotaCreditoDebito;
    }

    public String getCodigoControlNotaCreditoDebito() {
        return codigoControlNotaCreditoDebito;
    }

    public void setCodigoControlNotaCreditoDebito(String codigoControlNotaCreditoDebito) {
        this.codigoControlNotaCreditoDebito = codigoControlNotaCreditoDebito;
    }

    public Date getFechaFacturaOriginal() {
        return fechaFacturaOriginal;
    }

    public void setFechaFacturaOriginal(Date fechaFacturaOriginal) {
        this.fechaFacturaOriginal = fechaFacturaOriginal;
    }

    public Long getNumeroFacturaOriginal() {
        return numeroFacturaOriginal;
    }

    public void setNumeroFacturaOriginal(Long numeroFacturaOriginal) {
        this.numeroFacturaOriginal = numeroFacturaOriginal;
    }

    public String getNumeroAutorizacionFacturaOriginal() {
        return numeroAutorizacionFacturaOriginal;
    }

    public void setNumeroAutorizacionFacturaOriginal(String numeroAutorizacionFacturaOriginal) {
        this.numeroAutorizacionFacturaOriginal = numeroAutorizacionFacturaOriginal;
    }

    public BigDecimal getMontoTotalFacturaOriginalPrimeraMoneda() {
        return montoTotalFacturaOriginalPrimeraMoneda;
    }

    public void setMontoTotalFacturaOriginalPrimeraMoneda(BigDecimal montoTotalFacturaOriginalPrimeraMoneda) {
        this.montoTotalFacturaOriginalPrimeraMoneda = montoTotalFacturaOriginalPrimeraMoneda;
    }

    public BigDecimal getMontoTotalFacturaOriginalSegundaMoneda() {
        return montoTotalFacturaOriginalSegundaMoneda;
    }

    public void setMontoTotalFacturaOriginalSegundaMoneda(BigDecimal montoTotalFacturaOriginalSegundaMoneda) {
        this.montoTotalFacturaOriginalSegundaMoneda = montoTotalFacturaOriginalSegundaMoneda;
    }

   
    
    
}
