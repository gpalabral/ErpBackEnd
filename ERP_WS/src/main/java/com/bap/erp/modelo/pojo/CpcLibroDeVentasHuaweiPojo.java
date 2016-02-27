package com.bap.erp.modelo.pojo;

import java.math.BigDecimal;

public class CpcLibroDeVentasHuaweiPojo {
   
    private String fechaAceptacionFactura;
    private Long nitCliente;
    private String nombreCliente;
    private Integer gestionFactura;    
    private String numeroContrato;
    private String numeroContratoCliente;
    private BigDecimal montoTotalContratoDolares;
    private BigDecimal montoTotalContratoBolivianos;
    private BigDecimal tipoCambioContrato;
    private BigDecimal montoTotalContratoUSD;
    private BigDecimal montoTotalContratoBOB;
    private BigDecimal anticipo;
    private BigDecimal entrega;
    private BigDecimal facturacionPorPac;
    private BigDecimal facturacionPorFac;
    private String tipoFactura;
    private BigDecimal montoFacturaDolares;
    private BigDecimal montoFacturaBolivianos;
    private BigDecimal montoFacturaUSD;
    private BigDecimal montoFacturaBOB;
    private BigDecimal ingresoFacturado;
    private BigDecimal ingresoPorExportacion;
    private BigDecimal ingresoPorVentasAlExteriorSinFacturar;
    private BigDecimal iva;
    private BigDecimal it;
    private BigDecimal ingresoNeto;
    private BigDecimal porcentajeFacturacion;//En relacion al contrato
    private String estadoFacturacion;//POD, FAC o PAC
    private BigDecimal noCompensable;
    private Long numeroFactura;
    private String fechaEmisionFactura;
    private String mesDeLaFactura;
    private int diasDeRetraso;
    private String mesDeFacturacion;
    private BigDecimal revenueAccrued; //Incluir en esta columna el 87% del ingreso facturado
    private String numeroFacturaInterno;
    private String batchNameDebitoFiscal; //referenciacion
    private String batchNameIngresos; //referenciacion
    private String cuentaContable; //referenciacion
    
    
//    private BigDecimal tipoCambioFactura;
//    private String nombreContrato;
//    private String hito;
//    private String detalleFactura;
//    private String plazoCredito;
//    private Date fechaAplicacionFactura;
//    private Date fechaEntregaFactura;

    public String getNombreCliente() {        
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getNumeroContratoCliente() {
        return numeroContratoCliente;
    }

    public void setNumeroContratoCliente(String numeroContratoCliente) {
        this.numeroContratoCliente = numeroContratoCliente;
    } 

    public BigDecimal getMontoTotalContratoDolares() {
        return montoTotalContratoDolares;
    }

    public void setMontoTotalContratoDolares(BigDecimal montoTotalContratoDolares) {
        this.montoTotalContratoDolares = montoTotalContratoDolares;
    }

    public BigDecimal getMontoTotalContratoBolivianos() {
        return montoTotalContratoBolivianos;
    }

    public void setMontoTotalContratoBolivianos(BigDecimal montoTotalContratoBolivianos) {
        this.montoTotalContratoBolivianos = montoTotalContratoBolivianos;
    }

    public BigDecimal getTipoCambioContrato() {
        return tipoCambioContrato;
    }

    public void setTipoCambioContrato(BigDecimal tipoCambioContrato) {
        this.tipoCambioContrato = tipoCambioContrato;
    }

    public BigDecimal getMontoTotalContratoUSD() {
        return montoTotalContratoUSD;
    }

    public void setMontoTotalContratoUSD(BigDecimal montoTotalContratoUSD) {
        this.montoTotalContratoUSD = montoTotalContratoUSD;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }
   

    public BigDecimal getMontoFacturaDolares() {
        return montoFacturaDolares;
    }

    public void setMontoFacturaDolares(BigDecimal montoFacturaDolares) {
        this.montoFacturaDolares = montoFacturaDolares;
    }

    public BigDecimal getMontoFacturaBolivianos() {
        return montoFacturaBolivianos;
    }

    public void setMontoFacturaBolivianos(BigDecimal montoFacturaBolivianos) {
        this.montoFacturaBolivianos = montoFacturaBolivianos;
    }   

    public BigDecimal getMontoFacturaUSD() {
        return montoFacturaUSD;
    }

    public void setMontoFacturaUSD(BigDecimal montoFacturaUSD) {
        this.montoFacturaUSD = montoFacturaUSD;
    }

    public Long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
    

    public String getNumeroFacturaInterno() {
        return numeroFacturaInterno;
    }

    public void setNumeroFacturaInterno(String numeroFacturaInterno) {
        this.numeroFacturaInterno = numeroFacturaInterno;
    }

    public Long getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(Long nitCliente) {
        this.nitCliente = nitCliente;
    }   

    public BigDecimal getMontoTotalContratoBOB() {
        return montoTotalContratoBOB;
    }

    public void setMontoTotalContratoBOB(BigDecimal montoTotalContratoBOB) {
        this.montoTotalContratoBOB = montoTotalContratoBOB;
    }

    public BigDecimal getAnticipo() {
        return anticipo;
    }

    public void setAnticipo(BigDecimal anticipo) {
        this.anticipo = anticipo;
    }

    public BigDecimal getEntrega() {
        return entrega;
    }

    public void setEntrega(BigDecimal entrega) {
        this.entrega = entrega;
    }

    public BigDecimal getFacturacionPorPac() {
        return facturacionPorPac;
    }

    public void setFacturacionPorPac(BigDecimal facturacionPorPac) {
        this.facturacionPorPac = facturacionPorPac;
    }

    public BigDecimal getFacturacionPorFac() {
        return facturacionPorFac;
    }

    public void setFacturacionPorFac(BigDecimal facturacionPorFac) {
        this.facturacionPorFac = facturacionPorFac;
    }

    public BigDecimal getMontoFacturaBOB() {
        return montoFacturaBOB;
    }

    public void setMontoFacturaBOB(BigDecimal montoFacturaBOB) {
        this.montoFacturaBOB = montoFacturaBOB;
    }

    public BigDecimal getIngresoFacturado() {
        return ingresoFacturado;
    }

    public void setIngresoFacturado(BigDecimal ingresoFacturado) {
        this.ingresoFacturado = ingresoFacturado;
    }

    public BigDecimal getIngresoPorExportacion() {
        return ingresoPorExportacion;
    }

    public void setIngresoPorExportacion(BigDecimal ingresoPorExportacion) {
        this.ingresoPorExportacion = ingresoPorExportacion;
    }

    public BigDecimal getIngresoPorVentasAlExteriorSinFacturar() {
        return ingresoPorVentasAlExteriorSinFacturar;
    }

    public void setIngresoPorVentasAlExteriorSinFacturar(BigDecimal ingresoPorVentasAlExteriorSinFacturar) {
        this.ingresoPorVentasAlExteriorSinFacturar = ingresoPorVentasAlExteriorSinFacturar;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getIt() {
        return it;
    }

    public void setIt(BigDecimal it) {
        this.it = it;
    }

    public BigDecimal getIngresoNeto() {
        return ingresoNeto;
    }

    public void setIngresoNeto(BigDecimal ingresoNeto) {
        this.ingresoNeto = ingresoNeto;
    }

    public BigDecimal getPorcentajeFacturacion() {
        return porcentajeFacturacion;
    }

    public void setPorcentajeFacturacion(BigDecimal porcentajeFacturacion) {
        this.porcentajeFacturacion = porcentajeFacturacion;
    }

    public String getEstadoFacturacion() {
        return estadoFacturacion;
    }

    public void setEstadoFacturacion(String estadoFacturacion) {
        this.estadoFacturacion = estadoFacturacion;
    }

    public String getMesDeLaFactura() {
        return mesDeLaFactura;
    }

    public void setMesDeLaFactura(String mesDeLaFactura) {
        this.mesDeLaFactura = mesDeLaFactura;
    }

    public String getMesDeFacturacion() {
        return mesDeFacturacion;
    }

    public void setMesDeFacturacion(String mesDeFacturacion) {
        this.mesDeFacturacion = mesDeFacturacion;
    }

    public BigDecimal getRevenueAccrued() {
        return revenueAccrued;
    }

    public void setRevenueAccrued(BigDecimal revenueAccrued) {
        this.revenueAccrued = revenueAccrued;
    }

    public String getBatchNameDebitoFiscal() {
        return batchNameDebitoFiscal;
    }

    public void setBatchNameDebitoFiscal(String batchNameDebitoFiscal) {
        this.batchNameDebitoFiscal = batchNameDebitoFiscal;
    }

    public String getBatchNameIngresos() {
        return batchNameIngresos;
    }

    public void setBatchNameIngresos(String batchNameIngresos) {
        this.batchNameIngresos = batchNameIngresos;
    }

    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }         

    public Integer getGestionFactura() {
        return gestionFactura;
    }

    public void setGestionFactura(Integer gestionFactura) {
        this.gestionFactura = gestionFactura;
    }

    public String getFechaAceptacionFactura() {
        return fechaAceptacionFactura;
    }

    public void setFechaAceptacionFactura(String fechaAceptacionFactura) {
        this.fechaAceptacionFactura = fechaAceptacionFactura;
    }

    public BigDecimal getNoCompensable() {
        return noCompensable;
    }

    public void setNoCompensable(BigDecimal noCompensable) {
        this.noCompensable = noCompensable;
    }

    public String getFechaEmisionFactura() {
        return fechaEmisionFactura;
    }

    public void setFechaEmisionFactura(String fechaEmisionFactura) {
        this.fechaEmisionFactura = fechaEmisionFactura;
    }

    public int getDiasDeRetraso() {
        return diasDeRetraso;
    }

    public void setDiasDeRetraso(int diasDeRetraso) {
        this.diasDeRetraso = diasDeRetraso;
    }
               
    
}
