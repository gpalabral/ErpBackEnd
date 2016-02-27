package com.bap.erp.modelo.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ErpFacturaRetencionPojo {

    private Long idFacturaRetencion;
    private Date fechaRegistro;
    private String nroContrato;
    private String nroFacturaRetencion;
    private String numeroAutorizacion;
    private String modalidadTransaccion;
    private String tipoTransaccion;
    private BigDecimal montoEmitido;
    private BigDecimal montoABancarizar;
    private BigDecimal montoBancarizado;
    private String tipoDocumentoMercantil;

    public Long getIdFacturaRetencion() {
        return idFacturaRetencion;
    }

    public void setIdFacturaRetencion(Long idFacturaRetencion) {
        this.idFacturaRetencion = idFacturaRetencion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNroContrato() {
        return nroContrato;
    }

    public void setNroContrato(String nroContrato) {
        this.nroContrato = nroContrato;
    }

    public String getNroFacturaRetencion() {
        return nroFacturaRetencion;
    }

    public void setNroFacturaRetencion(String nroFacturaRetencion) {
        this.nroFacturaRetencion = nroFacturaRetencion;
    }

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public String getModalidadTransaccion() {
        return modalidadTransaccion;
    }

    public void setModalidadTransaccion(String modalidadTransaccion) {
        this.modalidadTransaccion = modalidadTransaccion;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public BigDecimal getMontoEmitido() {
        return montoEmitido;
    }

    public void setMontoEmitido(BigDecimal montoEmitido) {
        this.montoEmitido = montoEmitido;
    }

    public BigDecimal getMontoABancarizar() {
        return montoABancarizar;
    }

    public void setMontoABancarizar(BigDecimal montoABancarizar) {
        this.montoABancarizar = montoABancarizar;
    }

    public String getTipoDocumentoMercantil() {
        return tipoDocumentoMercantil;
    }

    public void setTipoDocumentoMercantil(String tipoDocumentoMercantil) {
        this.tipoDocumentoMercantil = tipoDocumentoMercantil;
    }        

    public BigDecimal getMontoBancarizado() {
        return montoBancarizado;
    }

    public void setMontoBancarizado(BigDecimal montoBancarizado) {
        this.montoBancarizado = montoBancarizado;
    }
            
}
