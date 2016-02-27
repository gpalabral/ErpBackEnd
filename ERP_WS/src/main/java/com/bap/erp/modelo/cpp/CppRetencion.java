package com.bap.erp.modelo.cpp;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import com.bap.erp.modelo.ErpAplicante;
import com.bap.erp.modelo.par.ParEstado;
import com.bap.erp.modelo.par.ParEstadoPago;
import com.bap.erp.modelo.par.ParModalidadTransaccion;
import com.bap.erp.modelo.par.ParTipoAlicuota;
import com.bap.erp.modelo.par.ParTipoAplicacionRetencion;
import com.bap.erp.modelo.par.ParTipoDocumentoMercantil;
import com.bap.erp.modelo.par.ParTipoMoneda;
import com.bap.erp.modelo.par.ParTipoRetencion;
import com.bap.erp.modelo.par.ParTipoTransaccion;
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

@Entity
@Table(name = "CPP_RETENCION")
public class CppRetencion extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_retencion", nullable = false)
    private Long idRetencion;

    @Column(name = "concepto", length = 100)
    private String concepto;

    @Column(name = "monto_primera_moneda", precision = 18, scale = 5, nullable = true)
    private BigDecimal montoPrimeraMoneda;

    @Column(name = "monto_segunda_moneda", precision = 18, scale = 5, nullable = true)
    private BigDecimal montoSegundaMoneda;

    @Column(name = "iue_primera_moneda", precision = 18, scale = 5, nullable = true)
    private BigDecimal iuePrimeraMoneda;

    @Column(name = "iue_segunda_moneda", precision = 18, scale = 5, nullable = true)
    private BigDecimal iueSegundaMoneda;

    @Column(name = "it_primera_moneda", precision = 18, scale = 5, nullable = true)
    private BigDecimal itPrimeraMoneda;

    @Column(name = "it_segunda_moneda", precision = 18, scale = 5, nullable = true)
    private BigDecimal itSegundaMoneda;

    @Column(name = "iva_primera_moneda", precision = 18, scale = 5, nullable = true)
    private BigDecimal ivaPrimeraMoneda;

    @Column(name = "iva_segunda_moneda", precision = 18, scale = 5, nullable = true)
    private BigDecimal ivaSegundaMoneda;

    @Column(name = "monto_remanente_primera_moneda", precision = 18, scale = 5, nullable = true)
    private BigDecimal montoRemanentePrimeraMoneda;

    @Column(name = "monto_remanente_segunda_moneda", precision = 18, scale = 5, nullable = true)
    private BigDecimal montoRemanenteSegundaMoneda;

    @Column(name = "fecha_registro", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_moneda", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoMoneda parTipoMoneda;

    @Column(name = "tipo_cambio", precision = 18, scale = 5, nullable = true)
    private BigDecimal tipoCambio;


    @ManyToOne()
    @JoinColumn(name = "par_tipo_transaccion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoTransaccion parTipoTransaccion;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_aplicacion_retencion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoAplicacionRetencion parTipoAplicacionRetencion;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_retencion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoRetencion parTipoRetencion;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_alicuota", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoAlicuota parTipoAlicuota;

    @JoinColumn(name = "id_aplicante", referencedColumnName = "id_aplicante")
    @ManyToOne(optional = true)
    private ErpAplicante erpAplicante;

    @ManyToOne()
    @JoinColumn(name = "par_estado", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEstado parEstadoRetencion;

    @JoinColumn(name = "id_proveedor_cliente", referencedColumnName = "id_proveedor_cliente")
    @ManyToOne(optional = true)
    private CppProveedorCliente cppProveedorCliente;

    @Column(name = "nro_retencion_interno")
    private String nroRetencionInterno;

    @ManyToOne()
    @JoinColumn(name = "par_modalidad_transaccion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParModalidadTransaccion parModalidadTransaccion;

    @Column(name = "nro_contrato", length = 100)
    private String nroContrato;

    @ManyToOne()
    @JoinColumn(name = "par_estado_pago", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEstadoPago parEstadoPago;
    
    @ManyToOne()
    @JoinColumn(name = "par_tipo_documento_mercantil", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoDocumentoMercantil parTipoDocumentoMercantil;
    
    @Column(name = "numero_retencion")
    private Long numeroRetencion;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRetencion != null ? idRetencion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CppRetencion)) {
            return false;
        }
        CppRetencion other = (CppRetencion) object;
        if ((this.idRetencion == null && other.idRetencion != null) || (this.idRetencion != null && !this.idRetencion.equals(other.idRetencion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CppRetencion[ idRetencion=" + idRetencion + " ]";
    }

    public Long getIdRetencion() {
        return idRetencion;
    }

    public void setIdRetencion(Long idRetencion) {
        this.idRetencion = idRetencion;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
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

    public BigDecimal getIuePrimeraMoneda() {
        return iuePrimeraMoneda;
    }

    public void setIuePrimeraMoneda(BigDecimal iuePrimeraMoneda) {
        this.iuePrimeraMoneda = iuePrimeraMoneda;
    }

    public BigDecimal getIueSegundaMoneda() {
        return iueSegundaMoneda;
    }

    public void setIueSegundaMoneda(BigDecimal iueSegundaMoneda) {
        this.iueSegundaMoneda = iueSegundaMoneda;
    }

    public BigDecimal getItPrimeraMoneda() {
        return itPrimeraMoneda;
    }

    public void setItPrimeraMoneda(BigDecimal itPrimeraMoneda) {
        this.itPrimeraMoneda = itPrimeraMoneda;
    }

    public BigDecimal getItSegundaMoneda() {
        return itSegundaMoneda;
    }

    public void setItSegundaMoneda(BigDecimal itSegundaMoneda) {
        this.itSegundaMoneda = itSegundaMoneda;
    }

    public BigDecimal getIvaPrimeraMoneda() {
        return ivaPrimeraMoneda;
    }

    public void setIvaPrimeraMoneda(BigDecimal ivaPrimeraMoneda) {
        this.ivaPrimeraMoneda = ivaPrimeraMoneda;
    }

    public BigDecimal getIvaSegundaMoneda() {
        return ivaSegundaMoneda;
    }

    public void setIvaSegundaMoneda(BigDecimal ivaSegundaMoneda) {
        this.ivaSegundaMoneda = ivaSegundaMoneda;
    }

    public BigDecimal getMontoRemanentePrimeraMoneda() {
        return montoRemanentePrimeraMoneda;
    }

    public void setMontoRemanentePrimeraMoneda(BigDecimal montoRemanentePrimeraMoneda) {
        this.montoRemanentePrimeraMoneda = montoRemanentePrimeraMoneda;
    }

    public BigDecimal getMontoRemanenteSegundaMoneda() {
        return montoRemanenteSegundaMoneda;
    }

    public void setMontoRemanenteSegundaMoneda(BigDecimal montoRemanenteSegundaMoneda) {
        this.montoRemanenteSegundaMoneda = montoRemanenteSegundaMoneda;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public ParTipoMoneda getParTipoMoneda() {
        return parTipoMoneda;
    }

    public void setParTipoMoneda(ParTipoMoneda parTipoMoneda) {
        this.parTipoMoneda = parTipoMoneda;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public ParTipoAplicacionRetencion getParTipoAplicacionRetencion() {
        return parTipoAplicacionRetencion;
    }

    public void setParTipoAplicacionRetencion(ParTipoAplicacionRetencion parTipoAplicacionRetencion) {
        this.parTipoAplicacionRetencion = parTipoAplicacionRetencion;
    }

    public ParTipoRetencion getParTipoRetencion() {
        return parTipoRetencion;
    }

    public void setParTipoRetencion(ParTipoRetencion parTipoRetencion) {
        this.parTipoRetencion = parTipoRetencion;
    }

    public ParTipoAlicuota getParTipoAlicuota() {
        return parTipoAlicuota;
    }

    public void setParTipoAlicuota(ParTipoAlicuota parTipoAlicuota) {
        this.parTipoAlicuota = parTipoAlicuota;
    }

    public ErpAplicante getErpAplicante() {
        return erpAplicante;
    }

    public void setErpAplicante(ErpAplicante erpAplicante) {
        this.erpAplicante = erpAplicante;
    }

    public ParEstado getParEstadoRetencion() {
        return parEstadoRetencion;
    }

    public void setParEstadoRetencion(ParEstado parEstadoRetencion) {
        this.parEstadoRetencion = parEstadoRetencion;
    }

    public CppProveedorCliente getCppProveedorCliente() {
        return cppProveedorCliente;
    }

    public void setCppProveedorCliente(CppProveedorCliente cppProveedorCliente) {
        this.cppProveedorCliente = cppProveedorCliente;
    }

    public String getNroRetencionInterno() {
        return nroRetencionInterno;
    }

    public void setNroRetencionInterno(String nroRetencionInterno) {
        this.nroRetencionInterno = nroRetencionInterno;
    }

    public ParModalidadTransaccion getParModalidadTransaccion() {
        return parModalidadTransaccion;
    }

    public void setParModalidadTransaccion(ParModalidadTransaccion parModalidadTransaccion) {
        this.parModalidadTransaccion = parModalidadTransaccion;
    }

    public String getNroContrato() {
        return nroContrato;
    }

    public void setNroContrato(String nroContrato) {
        this.nroContrato = nroContrato;
    }

    public ParTipoTransaccion getParTipoTransaccion() {
        return parTipoTransaccion;
    }

    public void setParTipoTransaccion(ParTipoTransaccion parTipoTransaccion) {
        this.parTipoTransaccion = parTipoTransaccion;
    }

    public ParEstadoPago getParEstadoPago() {
        return parEstadoPago;
    }

    public void setParEstadoPago(ParEstadoPago parEstadoPago) {
        this.parEstadoPago = parEstadoPago;
    }

    public ParTipoDocumentoMercantil getParTipoDocumentoMercantil() {
        return parTipoDocumentoMercantil;
    }

    public void setParTipoDocumentoMercantil(ParTipoDocumentoMercantil parTipoDocumentoMercantil) {
        this.parTipoDocumentoMercantil = parTipoDocumentoMercantil;
    }

    public Long getNumeroRetencion() {
        return numeroRetencion;
    }

    public void setNumeroRetencion(Long numeroRetencion) {
        this.numeroRetencion = numeroRetencion;
    }
        
}
