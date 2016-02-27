package com.bap.erp.modelo.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class CpcConciliacionContablePojo {

    private Long idFacturaEmitida;
    private Integer numero;
    private Date fechaFactura;
    private Long numeroDeFactura;    
    private String nroFacturaInterno;
    private String nroContrato;
    private BigDecimal debitoFiscal;    
    private Date glDate;
    private String batchNameDebitoFiscal;
    private String entryItem;
    private String credits;
    private String enterCredits;
    private String conciliado;
    private String enterCurrency;
    private String salesContractNo;
    private String cuentaContable;        
    private String batchNameIngresos;
    private String conciliadoIngreso;

    /**
     * @return the idFacturaEmitida
     */
    public Long getIdFacturaEmitida() {
        return idFacturaEmitida;
    }

    /**
     * @param idFacturaEmitida the idFacturaEmitida to set
     */
    public void setIdFacturaEmitida(Long idFacturaEmitida) {
        this.idFacturaEmitida = idFacturaEmitida;
    }

    /**
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
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

    public Long getNumeroDeFactura() {
        return numeroDeFactura;
    }

    public void setNumeroDeFactura(Long numeroDeFactura) {
        this.numeroDeFactura = numeroDeFactura;
    } 

    /**
     * @return the nroFacturaInterno
     */
    public String getNroFacturaInterno() {
        return nroFacturaInterno;
    }

    /**
     * @param nroFacturaInterno the nroFacturaInterno to set
     */
    public void setNroFacturaInterno(String nroFacturaInterno) {
        this.nroFacturaInterno = nroFacturaInterno;
    }

    public BigDecimal getDebitoFiscal() {
        return debitoFiscal;
    }

    public void setDebitoFiscal(BigDecimal debitoFiscal) {
        this.debitoFiscal = debitoFiscal;
    }


    /**
     * @return the glDate
     */
    public Date getGlDate() {
        return glDate;
    }

    /**
     * @param glDate the glDate to set
     */
    public void setGlDate(Date glDate) {
        this.glDate = glDate;
    }


    /**
     * @return the credits
     */
    public String getCredits() {
        return credits;
    }

    /**
     * @param credits the credits to set
     */
    public void setCredits(String credits) {
        this.credits = credits;
    }

    /**
     * @return the conciliado
     */
    public String getConciliado() {
        return conciliado;
    }

    /**
     * @param conciliado the conciliado to set
     */
    public void setConciliado(String conciliado) {
        this.conciliado = conciliado;
    }

    public String getEnterCurrency() {
        return enterCurrency;
    }

    public void setEnterCurrency(String enterCurrency) {
        this.enterCurrency = enterCurrency;
    }
   
    public String getSalesContractNo() {
        return salesContractNo;
    }

    public void setSalesContractNo(String salesContractNo) {
        this.salesContractNo = salesContractNo;
    }

    public String getEnterCredits() {
        return enterCredits;
    }

    public void setEnterCredits(String enterCredits) {
        this.enterCredits = enterCredits;
    }
    
    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
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

    public String getNroContrato() {
        return nroContrato;
    }

    public void setNroContrato(String nroContrato) {
        this.nroContrato = nroContrato;
    }

    public String getEntryItem() {
        return entryItem;
    }

    public void setEntryItem(String entryItem) {
        this.entryItem = entryItem;
    }

    public String getConciliadoIngreso() {
        return conciliadoIngreso;
    }

    public void setConciliadoIngreso(String conciliadoIngreso) {
        this.conciliadoIngreso = conciliadoIngreso;
    }        
    
}
