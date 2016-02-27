package com.bap.erp.modelo.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class CpcLibroDeBancarizacionPorVentasPojo {    
    private Integer modalidadDeTransaccion; //1 contado - 2 Credito
    private Date fechaFactura;
    private Integer tipoTransaccion; //1 Ventas - 2 Exportaciones - 3 Otros
    private String numeroFactura;
//    private Float montoFactura;
    private BigDecimal montoFactura;
    private Long nroAutorizacion; 
    private String nitCiCliente; //nit o documento de Identidad
    private String razonSocialNombreCliente; //razon social o el nombre del cliente
    private String nroCuentaDocumentoPago;
//    private Float montoPagadoDocPago;
//    private Float montoAcumulado;
    private BigDecimal montoPagadoDocPago;
    private BigDecimal montoAcumulado;
    private Integer nitEntidadFinanciera;
    private String nroDocumentoPago;
    private Integer tipoDeDocumentoDePago; // 1 Cheque de cualquier naturaleza - 2 orden de transferencia - 3 ordenes de transferencia electronica - 4 Transferencia de Fondos - 5 Tarjeta de Debito - 6 Tarjeta de Credito - 7 Tarjeta Prepagada - 8 Deposito en cuenta 
    private Date fechaDelDocumentoDePago;

    /**
     * @return the modalidadDeTransaccion
     */
    public Integer getModalidadDeTransaccion() {
        return modalidadDeTransaccion;
    }

    /**
     * @param modalidadDeTransaccion the modalidadDeTransaccion to set
     */
    public void setModalidadDeTransaccion(Integer modalidadDeTransaccion) {
        this.modalidadDeTransaccion = modalidadDeTransaccion;
    }

    /**
     * @return the fechaFactura
     */
    public Date getFechaFactura() {
        return fechaFactura;
    }

    /**
     * @param fechaFactura the fechaFactura to set
     */
    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    /**
     * @return the tipoTransaccion
     */
    public Integer getTipoTransaccion() {
        return tipoTransaccion;
    }

    /**
     * @param tipoTransaccion the tipoTransaccion to set
     */
    public void setTipoTransaccion(Integer tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    /**
     * @return the numeroFactura
     */
    public String getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * @param numeroFactura the numeroFactura to set
     */
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }



    public Long getNroAutorizacion() {
        return nroAutorizacion;
    }

    public void setNroAutorizacion(Long nroAutorizacion) {
        this.nroAutorizacion = nroAutorizacion;
    }

    /**
     * @return the nitCiCliente
     */
    public String getNitCiCliente() {
        return nitCiCliente;
    }

    /**
     * @param nitCiCliente the nitCiCliente to set
     */
    public void setNitCiCliente(String nitCiCliente) {
        this.nitCiCliente = nitCiCliente;
    }

    /**
     * @return the razonSocialNombreCliente
     */
    public String getRazonSocialNombreCliente() {
        return razonSocialNombreCliente;
    }

    /**
     * @param razonSocialNombreCliente the razonSocialNombreCliente to set
     */
    public void setRazonSocialNombreCliente(String razonSocialNombreCliente) {
        this.razonSocialNombreCliente = razonSocialNombreCliente;
    }

    /**
     * @return the nroCuentaDocumentoPago
     */
    public String getNroCuentaDocumentoPago() {
        return nroCuentaDocumentoPago;
    }

    /**
     * @param nroCuentaDocumentoPago the nroCuentaDocumentoPago to set
     */
    public void setNroCuentaDocumentoPago(String nroCuentaDocumentoPago) {
        this.nroCuentaDocumentoPago = nroCuentaDocumentoPago;
    }

   
    /**
     * @return the nitEntidadFinanciera
     */
    public Integer getNitEntidadFinanciera() {
        return nitEntidadFinanciera;
    }

    /**
     * @param nitEntidadFinanciera the nitEntidadFinanciera to set
     */
    public void setNitEntidadFinanciera(Integer nitEntidadFinanciera) {
        this.nitEntidadFinanciera = nitEntidadFinanciera;
    }

    /**
     * @return the nroDocumentoPago
     */
    public String getNroDocumentoPago() {
        return nroDocumentoPago;
    }

    /**
     * @param nroDocumentoPago the nroDocumentoPago to set
     */
    public void setNroDocumentoPago(String nroDocumentoPago) {
        this.nroDocumentoPago = nroDocumentoPago;
    }

    /**
     * @return the tipoDeDocumentoDePago
     */
    public Integer getTipoDeDocumentoDePago() {
        return tipoDeDocumentoDePago;
    }

    /**
     * @param tipoDeDocumentoDePago the tipoDeDocumentoDePago to set
     */
    public void setTipoDeDocumentoDePago(Integer tipoDeDocumentoDePago) {
        this.tipoDeDocumentoDePago = tipoDeDocumentoDePago;
    }

    /**
     * @return the fechaDelDocumentoDePago
     */
    public Date getFechaDelDocumentoDePago() {
        return fechaDelDocumentoDePago;
    }

    /**
     * @param fechaDelDocumentoDePago the fechaDelDocumentoDePago to set
     */
    public void setFechaDelDocumentoDePago(Date fechaDelDocumentoDePago) {
        this.fechaDelDocumentoDePago = fechaDelDocumentoDePago;
    }

    public BigDecimal getMontoFactura() {
        return montoFactura;
    }

    public void setMontoFactura(BigDecimal montoFactura) {
        this.montoFactura = montoFactura;
    }

    public BigDecimal getMontoPagadoDocPago() {
        return montoPagadoDocPago;
    }

    public void setMontoPagadoDocPago(BigDecimal montoPagadoDocPago) {
        this.montoPagadoDocPago = montoPagadoDocPago;
    }

    public BigDecimal getMontoAcumulado() {
        return montoAcumulado;
    }

    public void setMontoAcumulado(BigDecimal montoAcumulado) {
        this.montoAcumulado = montoAcumulado;
    }
       
    
}
