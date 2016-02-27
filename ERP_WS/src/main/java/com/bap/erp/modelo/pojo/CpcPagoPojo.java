package com.bap.erp.modelo.pojo;

import java.math.BigDecimal;

/**
 * @author Jonathan
 */
public class CpcPagoPojo {
    
    private Long idFacturaEmitida;    
    private BigDecimal montoPrimeraMoneda;
    private BigDecimal montoSegundaMoneda;
    private BigDecimal montoAcumuladoPrimeraMoneda;
    private BigDecimal montoAcumuladoSegundaMoneda;
    private String tipoDocumentoMercantil;    

    public Long getIdFacturaEmitida() {
        return idFacturaEmitida;
    }

    public void setIdFacturaEmitida(Long idFacturaEmitida) {
        this.idFacturaEmitida = idFacturaEmitida;
    }

    public BigDecimal getMontoPrimeraMoneda() {
        return montoPrimeraMoneda;
    }

    public void setMontoPrimeraMoneda(BigDecimal montoPrimeraMoneda) {
        this.montoPrimeraMoneda = montoPrimeraMoneda;
    }

    public BigDecimal getMontoSegundaMoneda() {
        return montoSegundaMoneda;
    }

    public void setMontoSegundaMoneda(BigDecimal montoSegundaMoneda) {
        this.montoSegundaMoneda = montoSegundaMoneda;
    }

    public BigDecimal getMontoAcumuladoPrimeraMoneda() {
        return montoAcumuladoPrimeraMoneda;
    }

    public void setMontoAcumuladoPrimeraMoneda(BigDecimal montoAcumuladoPrimeraMoneda) {
        this.montoAcumuladoPrimeraMoneda = montoAcumuladoPrimeraMoneda;
    }

    public BigDecimal getMontoAcumuladoSegundaMoneda() {
        return montoAcumuladoSegundaMoneda;
    }

    public void setMontoAcumuladoSegundaMoneda(BigDecimal montoAcumuladoSegundaMoneda) {
        this.montoAcumuladoSegundaMoneda = montoAcumuladoSegundaMoneda;
    }

    public String getTipoDocumentoMercantil() {
        return tipoDocumentoMercantil;
    }

    public void setTipoDocumentoMercantil(String tipoDocumentoMercantil) {
        this.tipoDocumentoMercantil = tipoDocumentoMercantil;
    }
        
}
