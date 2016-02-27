package com.bap.erp.modelo.cpc;

import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import com.bap.erp.modelo.ban.DocumentoPago;
import com.bap.erp.modelo.cpp.CppRetencion;
import com.bap.erp.modelo.par.ParTipoModulo;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//***@Javier*****
@Entity
@Table(name = "CPC_PAGO")
public class CpcPago extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago", nullable = false)
    private Long idPago;

    @JoinColumn(name = "id_factura", referencedColumnName = "id_factura")
    @ManyToOne(optional = false)
    private ErpFactura erpFactura;

    @JoinColumn(name = "id_documento_pago", referencedColumnName = "id_documento_pago")
    @ManyToOne(optional = false)
    private DocumentoPago documentoPago;

    @Column(name = "monto_pagado_primera_moneda", precision = 18, scale = 5)
    private BigDecimal montoPagadoPrimeraMoneda;

    @Column(name = "monto_pagado_segunda_moneda", precision = 18, scale = 5)
    private BigDecimal montoPagadoSegundaMoneda;

    @Column(name = "monto_acumulado_primera_moneda", precision = 18, scale = 5)
    private BigDecimal montoAcumuladoPrimeraMoneda;

    @Column(name = "monto_acumulado_segunda_moneda", precision = 18, scale = 5)
    private BigDecimal montoAcumuladoSegundaMoneda;

    @JoinColumn(name = "id_retencion", referencedColumnName = "id_retencion")
    @ManyToOne(optional = true)
    private CppRetencion cppRetencion;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_modulo", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoModulo parTipoModulo;

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public ErpFactura getErpFactura() {
        return erpFactura;
    }

    public void setErpFactura(ErpFactura erpFactura) {
        this.erpFactura = erpFactura;
    }

    public DocumentoPago getDocumentoPago() {
        return documentoPago;
    }

    public void setDocumentoPago(DocumentoPago documentoPago) {
        this.documentoPago = documentoPago;
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

    public CppRetencion getCppRetencion() {
        return cppRetencion;
    }

    public void setCppRetencion(CppRetencion cppRetencion) {
        this.cppRetencion = cppRetencion;
    }

    public ParTipoModulo getParTipoModulo() {
        return parTipoModulo;
    }

    public void setParTipoModulo(ParTipoModulo parTipoModulo) {
        this.parTipoModulo = parTipoModulo;
    }
    
}
