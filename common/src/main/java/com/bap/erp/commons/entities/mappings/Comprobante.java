package com.bap.erp.commons.entities.mappings;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Comprobante implements Serializable{

    private Long idCntComprobantePojo; //id que devolvera para guardarse en Cpp
    private String creditoFiscalTransitorio;
    private Long idTipoDeCambio;
    private Float monto;
    private Float montoSegMoneda;
    private String razonSocial;
    private String numeroDeFactura;
    private String parametrosAutorizacion; //factura,bcp,poliza,
    private Date fechaFactura; 
    private Long nit;
    private String nroAutorizacion;
    private String codigoControl;
    private Float excento;
    private Float ice;
    private Float iva;
    private Float descuento;
    private Float excentoSegMoneda;    
    private Float iceSegMoneda;    
    private Float ivaSegMoneda;
    private Float descuentoSegMoneda;
    private String concepto;
    private String glosa;
    private String loginUsuario;
    private Date fechaComprobante;
    private List<CuentaMonto> listaCuentas;
    //aumentado para cuentas por pagar
    private Long idProveedorCliente;
    private Long idDosificacion;
    

    public String getCreditoFiscalTransitorio() {
        return creditoFiscalTransitorio;
    }

    public void setCreditoFiscalTransitorio(String creditoFiscalTransitorio) {
        this.creditoFiscalTransitorio = creditoFiscalTransitorio;
    }

    public Long getIdTipoDeCambio() {
        return idTipoDeCambio;
    }

    public void setIdTipoDeCambio(Long idTipoDeCambio) {
        this.idTipoDeCambio = idTipoDeCambio;
    }  

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNumeroDeFactura() {
        return numeroDeFactura;
    }

    public void setNumeroDeFactura(String numeroDeFactura) {
        this.numeroDeFactura = numeroDeFactura;
    }

    public String getParametrosAutorizacion() {
        return parametrosAutorizacion;
    }

    public void setParametrosAutorizacion(String parametrosAutorizacion) {
        this.parametrosAutorizacion = parametrosAutorizacion;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public String getNroAutorizacion() {
        return nroAutorizacion;
    }

    public void setNroAutorizacion(String nroAutorizacion) {
        this.nroAutorizacion = nroAutorizacion;
    }

    public String getCodigoControl() {
        return codigoControl;
    }

    public void setCodigoControl(String codigoControl) {
        this.codigoControl = codigoControl;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public Long getIdCntComprobantePojo() {
        return idCntComprobantePojo;
    }

    public void setIdCntComprobantePojo(Long idCntComprobantePojo) {
        this.idCntComprobantePojo = idCntComprobantePojo;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public Float getMontoSegMoneda() {
        return montoSegMoneda;
    }

    public void setMontoSegMoneda(Float montoSegMoneda) {
        this.montoSegMoneda = montoSegMoneda;
    }

    public Float getExcento() {
        return excento;
    }

    public void setExcento(Float excento) {
        this.excento = excento;
    }

    public Float getIce() {
        return ice;
    }

    public void setIce(Float ice) {
        this.ice = ice;
    }

    public Float getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    public Float getExcentoSegMoneda() {
        return excentoSegMoneda;
    }

    public void setExcentoSegMoneda(Float excentoSegMoneda) {
        this.excentoSegMoneda = excentoSegMoneda;
    }

    public Float getIceSegMoneda() {
        return iceSegMoneda;
    }

    public void setIceSegMoneda(Float iceSegMoneda) {
        this.iceSegMoneda = iceSegMoneda;
    }

    public Float getIvaSegMoneda() {
        return ivaSegMoneda;
    }

    public void setIvaSegMoneda(Float ivaSegMoneda) {
        this.ivaSegMoneda = ivaSegMoneda;
    }

    public Float getDescuentoSegMoneda() {
        return descuentoSegMoneda;
    }

    public void setDescuentoSegMoneda(Float descuentoSegMoneda) {
        this.descuentoSegMoneda = descuentoSegMoneda;
    }

    /**
     * @return the listaCuentas
     */
    public List<CuentaMonto> getListaCuentas() {
        return listaCuentas;
    }

    /**
     * @param listaCuentas the listaCuentas to set
     */
    public void setListaCuentas(List<CuentaMonto> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public Date getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(Date fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }      

    public Long getIdProveedorCliente() {
        return idProveedorCliente;
    }

    public void setIdProveedorCliente(Long idProveedorCliente) {
        this.idProveedorCliente = idProveedorCliente;
    }    

    public Long getIdDosificacion() {
        return idDosificacion;
    }

    public void setIdDosificacion(Long idDosificacion) {
        this.idDosificacion = idDosificacion;
    }
    
    
}
