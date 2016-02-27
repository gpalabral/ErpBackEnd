package com.bap.erp.modelo.cpc;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import com.bap.erp.modelo.par.ParEstadoPago;
import com.bap.erp.modelo.par.ParTipoHito;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Javier
 */
@Entity
@Table(name = "CPC_PAGO_CONTRATO")
public class CpcPagoContrato extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago_contrato", nullable = false)
    private Long idPagoContrato;

    @JoinColumn(name = "id_contrato", referencedColumnName = "id_contrato")
    @ManyToOne(optional = false)
    private CpcContrato cpcContrato;

    @Column(name = "nro_pago", length = 30)
    private Long nroPago;

    @Column(name = "descripcion_pago", length = 70)
    private String descripcionPago;

    @Column(name = "monto_programado", precision = 18, scale = 5)
    private BigDecimal montoProgramado;

    @Column(name = "monto_programado_seg_moneda", precision = 18, scale = 5)
    private BigDecimal montoProgramadoSegMoneda;

    @Column(name = "fecha_programada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProgramada;

    @Column(name = "monto_facturado", precision = 18, scale = 5)
    private BigDecimal montoFacturado;

    @Column(name = "monto_facturado_seg_moneda", precision = 18, scale = 5)
    private BigDecimal montoFacturadoSegMoneda;

    @Column(name = "porcentaje_programado", precision = 18, scale = 5)
    private BigDecimal porcentajeProgramado;

    @Column(name = "porcentaje_facturado", precision = 18, scale = 5)
    private BigDecimal porcentajeFacturado;

    @Column(name = "monto_pagado_primera_moneda", precision = 18, scale = 5)
    private BigDecimal montoPagadoPrimeraMoneda;

    @Column(name = "monto_pagado_segunda_moneda", precision = 18, scale = 5)
    private BigDecimal montoPagadoSegundaMoneda;        
    
    @ManyToOne()
    @JoinColumn(name = "par_estado_pago", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEstadoPago parEstadoPago;
    
    @ManyToOne()
    @JoinColumn(name = "par_tipo_hito", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoHito parTipoHito;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPagoContrato != null ? idPagoContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CpcPagoContrato)) {
            return false;
        }
        CpcPagoContrato other = (CpcPagoContrato) object;
        if ((this.idPagoContrato == null && other.idPagoContrato != null) || (this.idPagoContrato != null && !this.idPagoContrato.equals(other.idPagoContrato))) {
            return false;
        }
        return true;
    }

    public Long getIdPagoContrato() {
        return idPagoContrato;
    }

    public void setIdPagoContrato(Long idPagoContrato) {
        this.idPagoContrato = idPagoContrato;
    }

    public CpcContrato getCpcContrato() {
        return cpcContrato;
    }

    public void setCpcContrato(CpcContrato cpcContrato) {
        this.cpcContrato = cpcContrato;
    }

    public Long getNroPago() {
        return nroPago;
    }

    public void setNroPago(Long nroPago) {
        this.nroPago = nroPago;
    }

    public String getDescripcionPago() {
        return descripcionPago;
    }

    public void setDescripcionPago(String descripcionPago) {
        this.descripcionPago = descripcionPago;
    }

    public Date getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(Date fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    
    public ParEstadoPago getParEstadoPago() {
        return parEstadoPago;
    }

    public void setParEstadoPago(ParEstadoPago parEstadoPago) {
        this.parEstadoPago = parEstadoPago;
    }

    public BigDecimal getMontoProgramado() {
        return montoProgramado;
    }

    public void setMontoProgramado(BigDecimal montoProgramado) {
        this.montoProgramado = montoProgramado;
    }

    public BigDecimal getMontoProgramadoSegMoneda() {
        return montoProgramadoSegMoneda;
    }

    public void setMontoProgramadoSegMoneda(BigDecimal montoProgramadoSegMoneda) {
        this.montoProgramadoSegMoneda = montoProgramadoSegMoneda;
    }

    public BigDecimal getMontoFacturado() {
        return montoFacturado;
    }

    public void setMontoFacturado(BigDecimal montoFacturado) {
        this.montoFacturado = montoFacturado;
    }

    public BigDecimal getMontoFacturadoSegMoneda() {
        return montoFacturadoSegMoneda;
    }

    public void setMontoFacturadoSegMoneda(BigDecimal montoFacturadoSegMoneda) {
        this.montoFacturadoSegMoneda = montoFacturadoSegMoneda;
    }

    public BigDecimal getPorcentajeProgramado() {
        return porcentajeProgramado;
    }

    public void setPorcentajeProgramado(BigDecimal porcentajeProgramado) {
        this.porcentajeProgramado = porcentajeProgramado;
    }

    public BigDecimal getPorcentajeFacturado() {
        return porcentajeFacturado;
    }

    public void setPorcentajeFacturado(BigDecimal porcentajeFacturado) {
        this.porcentajeFacturado = porcentajeFacturado;
    }

    public BigDecimal getMontoPagadoPrimeraMoneda() {
        return montoPagadoPrimeraMoneda;
    }

    public void setMontoPagadoPrimeraMoneda(BigDecimal montoPagadoPrimeraMoneda) {
        this.montoPagadoPrimeraMoneda = montoPagadoPrimeraMoneda;
    }

    public BigDecimal getMontoPagadoSegundaMoneda() {
        return montoPagadoSegundaMoneda;
    }

    public void setMontoPagadoSegundaMoneda(BigDecimal montoPagadoSegundaMoneda) {
        this.montoPagadoSegundaMoneda = montoPagadoSegundaMoneda;
    }

    public ParTipoHito getParTipoHito() {
        return parTipoHito;
    }

    public void setParTipoHito(ParTipoHito parTipoHito) {
        this.parTipoHito = parTipoHito;
    }   

}
