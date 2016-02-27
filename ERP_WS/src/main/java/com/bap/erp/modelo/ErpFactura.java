package com.bap.erp.modelo;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ban.CuentaBancaria;
import com.bap.erp.modelo.cpc.CpcDosificacion;
import com.bap.erp.modelo.cpc.CpcPagoContrato;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.par.ParEstadoFactura;
import com.bap.erp.modelo.par.ParEstadoPago;
import com.bap.erp.modelo.par.ParModalidadTransaccion;
import com.bap.erp.modelo.par.ParTipoAplicacionRetencion;
import com.bap.erp.modelo.par.ParTipoCompra;
import com.bap.erp.modelo.par.ParTipoDocumentoMercantil;
import com.bap.erp.modelo.par.ParTipoModulo;
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
@Table(name = "ERP_FACTURA")
public class ErpFactura extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura", nullable = false)
    private Long idFactura;

    @JoinColumn(name = "id_proveedor_cliente", referencedColumnName = "id_proveedor_cliente")
    @ManyToOne(optional = false)
    private CppProveedorCliente cppProveedorCliente;

    @JoinColumn(name = "id_dosificacion", referencedColumnName = "id_dosificacion")
    @ManyToOne(optional = true)
    private CpcDosificacion cpcDosificacion;

    @Column(name = "id_padre")
    private Long idPadre;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "monto_primera_moneda", nullable = false, precision = 18, scale = 5)
    private BigDecimal montoPrimeraMoneda;

    @Column(name = "monto_segunda_moneda", nullable = false, precision = 18, scale = 5)
    private BigDecimal montoSegundaMoneda;

    @Column(name = "fecha_factura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFactura;

    @Column(name = "numero_factura")
    private Long numeroFactura;

    @ManyToOne()
    @JoinColumn(name = "par_estado_factura", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))//no existe en conta
    private ParEstadoFactura parEstadoFactura;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_documento_mercantil", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoDocumentoMercantil parTipoDocumentoMercantil;

    @JoinColumn(name = "id_pago_contrato", referencedColumnName = "id_pago_contrato")
    @ManyToOne(optional = true)
    private CpcPagoContrato cpcPagoContrato;

    @Column(name = "codigo_control")
    private String codigoControl;

    @Column(name = "incoterm")
    private String incoterm;

    @Column(name = "puerto_destino")
    private String puertoDestino;

    @Column(name = "valor_bruto", precision = 18, scale = 5)
    private BigDecimal valorBruto;

    @Column(name = "gastos_transporte", precision = 18, scale = 5)
    private BigDecimal gastosTransporte;

    @Column(name = "gastos_seguro", precision = 18, scale = 5)
    private BigDecimal gastosSeguro;

    @Column(name = "total_fob", precision = 18, scale = 5)
    private BigDecimal totalFob;

    @Column(name = "transporte_internacional", precision = 18, scale = 5)
    private BigDecimal transporteInternacional;

    @Column(name = "seguro_internacional", precision = 18, scale = 5)
    private BigDecimal seguroInternacional;

    @Column(name = "otros_gastos", precision = 18, scale = 5)
    private BigDecimal otrosGastos;

    @Column(name = "id_cbte_contable")
    private Long idCbteContable;

    @Column(name = "tipo_cambio_factura", precision = 18, scale = 5)
    private BigDecimal tipoCambioFactura;

    @Column(name = "total_descuento_primera_moneda", precision = 18, scale = 5)
    private BigDecimal totalDescuentoPrimeraMoneda;

    @Column(name = "total_descuento_segunda_moneda", precision = 18, scale = 5)
    private BigDecimal totalDescuentoSegundaMoneda;

    @ManyToOne()
    @JoinColumn(name = "par_modalidad_transaccion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParModalidadTransaccion parModalidadTransaccion;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_transaccion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoTransaccion parTipoTransaccion;

    @Column(name = "glosa")
    private String glosa;

    @Column(name = "concepto")
    private String concepto;

    @Column(name = "ice_primera_moneda", precision = 18, scale = 5)
    private BigDecimal icePrimeraMoneda;

    @Column(name = "ice_segunda_moneda", precision = 18, scale = 5)
    private BigDecimal iceSegundaMoneda;

    @ManyToOne()
    @JoinColumn(name = "par_estado_pago", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEstadoPago parEstadoPago;

    @Column(name = "nro_factura_interno")
    private String nroFacturaInterno;

    @JoinColumn(name = "id_cuenta_bancaria", referencedColumnName = "id_cuenta_bancaria")
    @ManyToOne(optional = true)
    private CuentaBancaria cuentaBancaria;

    @Column(name = "fecha_aceptacion", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAceptacion;

    @Column(name = "referenciado")
    private String referenciado;

    @Column(name = "batch_name_debito_fiscal")
    private String batchNameDebitoFiscal;

    @Column(name = "nro_contrato", length = 100)
    private String nroContrato;
    
    @Column(name = "batch_name_ingresos")
    private String batchNameIngresos;
    
    @Column(name = "cuenta_contable")
    private String cuentaContable;
        
    @ManyToOne()
    @JoinColumn(name = "par_tipo_modulo", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoModulo parTipoModulo;
    
    @JoinColumn(name = "id_aplicante", referencedColumnName = "id_aplicante")
    @ManyToOne(optional = true)
    private ErpAplicante erpAplicante;
    
    @ManyToOne()
    @JoinColumn(name = "par_tipo_aplicacion_retencion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoAplicacionRetencion parTipoAplicacionRetencion;
    
    @Column(name = "numero_dui")
    private String numeroDui;
    
    @Column(name = "numero_autorizacion")
    private String numeroAutorizacion;
    
    @Column(name = "referenciado_ingresos")
    private String referenciadoIngresos;

    @Column(name = "excento_primera_moneda", precision = 18, scale = 5)
    private BigDecimal excentoPrimeraMoneda;

    @Column(name = "excento_segunda_moneda", precision = 18, scale = 5)
    private BigDecimal excentoSegundaMoneda;
    
    @Column(name = "iva_primera_moneda", precision = 18, scale = 5)
    private BigDecimal ivaPrimeraMoneda;

    @Column(name = "iva_segunda_moneda", precision = 18, scale = 5)
    private BigDecimal ivaSegundaMoneda;
    
    @ManyToOne()
    @JoinColumn(name = "par_tipo_compra", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoCompra parTipoCompra;
    
    @Column(name = "referencia")
    private String referencia;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFactura != null ? idFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ErpFactura)) {
            return false;
        }
        ErpFactura other = (ErpFactura) object;
        if ((this.idFactura == null && other.idFactura != null) || (this.idFactura != null && !this.idFactura.equals(other.idFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ErpFactura[ idFactura=" + idFactura + " ]";
    }

    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public String getCodigoControl() {
        return codigoControl;
    }

    public void setCodigoControl(String codigoControl) {
        this.codigoControl = codigoControl;
    }

    public String getIncoterm() {
        return incoterm;
    }

    public void setIncoterm(String incoterm) {
        this.incoterm = incoterm;
    }

    public Long getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Long idPadre) {
        this.idPadre = idPadre;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public ParTipoDocumentoMercantil getParTipoDocumentoMercantil() {
        return parTipoDocumentoMercantil;
    }

    public void setParTipoDocumentoMercantil(ParTipoDocumentoMercantil parTipoDocumentoMercantil) {
        this.parTipoDocumentoMercantil = parTipoDocumentoMercantil;
    }

    public String getPuertoDestino() {
        return puertoDestino;
    }

    public void setPuertoDestino(String puertoDestino) {
        this.puertoDestino = puertoDestino;
    }

    public Long getIdCbteContable() {
        return idCbteContable;
    }

    public void setIdCbteContable(Long idCbteContable) {
        this.idCbteContable = idCbteContable;
    }

    public ParModalidadTransaccion getParModalidadTransaccion() {
        return parModalidadTransaccion;
    }

    public void setParModalidadTransaccion(ParModalidadTransaccion parModalidadTransaccion) {
        this.parModalidadTransaccion = parModalidadTransaccion;
    }

    public ParTipoTransaccion getParTipoTransaccion() {
        return parTipoTransaccion;
    }

    public void setParTipoTransaccion(ParTipoTransaccion parTipoTransaccion) {
        this.parTipoTransaccion = parTipoTransaccion;
    }

    public CppProveedorCliente getCppProveedorCliente() {
        return cppProveedorCliente;
    }

    public void setCppProveedorCliente(CppProveedorCliente cppProveedorCliente) {
        this.cppProveedorCliente = cppProveedorCliente;
    }

    public CpcDosificacion getCpcDosificacion() {
        return cpcDosificacion;
    }

    public void setCpcDosificacion(CpcDosificacion cpcDosificacion) {
        this.cpcDosificacion = cpcDosificacion;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public ParEstadoFactura getParEstadoFactura() {
        return parEstadoFactura;
    }

    public void setParEstadoFactura(ParEstadoFactura parEstadoFactura) {
        this.parEstadoFactura = parEstadoFactura;
    }

    public CpcPagoContrato getCpcPagoContrato() {
        return cpcPagoContrato;
    }

    public void setCpcPagoContrato(CpcPagoContrato cpcPagoContrato) {
        this.cpcPagoContrato = cpcPagoContrato;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public ParEstadoPago getParEstadoPago() {
        return parEstadoPago;
    }

    public void setParEstadoPago(ParEstadoPago parEstadoPago) {
        this.parEstadoPago = parEstadoPago;
    }

    public String getNroFacturaInterno() {
        return nroFacturaInterno;
    }

    public void setNroFacturaInterno(String nroFacturaInterno) {
        this.nroFacturaInterno = nroFacturaInterno;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
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

    public BigDecimal getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(BigDecimal valorBruto) {
        this.valorBruto = valorBruto;
    }

    public BigDecimal getGastosTransporte() {
        return gastosTransporte;
    }

    public void setGastosTransporte(BigDecimal gastosTransporte) {
        this.gastosTransporte = gastosTransporte;
    }

    public BigDecimal getGastosSeguro() {
        return gastosSeguro;
    }

    public void setGastosSeguro(BigDecimal gastosSeguro) {
        this.gastosSeguro = gastosSeguro;
    }

    public BigDecimal getTotalFob() {
        return totalFob;
    }

    public void setTotalFob(BigDecimal totalFob) {
        this.totalFob = totalFob;
    }

    public BigDecimal getTransporteInternacional() {
        return transporteInternacional;
    }

    public void setTransporteInternacional(BigDecimal transporteInternacional) {
        this.transporteInternacional = transporteInternacional;
    }

    public BigDecimal getSeguroInternacional() {
        return seguroInternacional;
    }

    public void setSeguroInternacional(BigDecimal seguroInternacional) {
        this.seguroInternacional = seguroInternacional;
    }

    public BigDecimal getOtrosGastos() {
        return otrosGastos;
    }

    public void setOtrosGastos(BigDecimal otrosGastos) {
        this.otrosGastos = otrosGastos;
    }

    public BigDecimal getTipoCambioFactura() {
        return tipoCambioFactura;
    }

    public void setTipoCambioFactura(BigDecimal tipoCambioFactura) {
        this.tipoCambioFactura = tipoCambioFactura;
    }

    public BigDecimal getTotalDescuentoPrimeraMoneda() {
        return totalDescuentoPrimeraMoneda;
    }

    public void setTotalDescuentoPrimeraMoneda(BigDecimal totalDescuentoPrimeraMoneda) {
        this.totalDescuentoPrimeraMoneda = totalDescuentoPrimeraMoneda;
    }

    public BigDecimal getTotalDescuentoSegundaMoneda() {
        return totalDescuentoSegundaMoneda;
    }

    public void setTotalDescuentoSegundaMoneda(BigDecimal totalDescuentoSegundaMoneda) {
        this.totalDescuentoSegundaMoneda = totalDescuentoSegundaMoneda;
    }

    public BigDecimal getIcePrimeraMoneda() {
        return icePrimeraMoneda;
    }

    public void setIcePrimeraMoneda(BigDecimal icePrimeraMoneda) {
        this.icePrimeraMoneda = icePrimeraMoneda;
    }

    public BigDecimal getIceSegundaMoneda() {
        return iceSegundaMoneda;
    }

    public void setIceSegundaMoneda(BigDecimal iceSegundaMoneda) {
        this.iceSegundaMoneda = iceSegundaMoneda;
    }

    public Date getFechaAceptacion() {
        return fechaAceptacion;
    }

    public void setFechaAceptacion(Date fechaAceptacion) {
        this.fechaAceptacion = fechaAceptacion;
    }

    public String getReferenciado() {
        return referenciado;
    }

    public void setReferenciado(String referenciado) {
        this.referenciado = referenciado;
    }   

    public String getNroContrato() {
        return nroContrato;
    }

    public void setNroContrato(String nroContrato) {
        this.nroContrato = nroContrato;
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

    public ParTipoModulo getParTipoModulo() {
        return parTipoModulo;
    }

    public void setParTipoModulo(ParTipoModulo parTipoModulo) {
        this.parTipoModulo = parTipoModulo;
    }

    public ErpAplicante getErpAplicante() {
        return erpAplicante;
    }

    public void setErpAplicante(ErpAplicante erpAplicante) {
        this.erpAplicante = erpAplicante;
    }

    public ParTipoAplicacionRetencion getParTipoAplicacionRetencion() {
        return parTipoAplicacionRetencion;
    }

    public void setParTipoAplicacionRetencion(ParTipoAplicacionRetencion parTipoAplicacionRetencion) {
        this.parTipoAplicacionRetencion = parTipoAplicacionRetencion;
    }

    public String getNumeroDui() {
        return numeroDui;
    }

    public void setNumeroDui(String numeroDui) {
        this.numeroDui = numeroDui;
    }

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public String getReferenciadoIngresos() {
        return referenciadoIngresos;
    }

    public void setReferenciadoIngresos(String referenciadoIngresos) {
        this.referenciadoIngresos = referenciadoIngresos;
    }

    public BigDecimal getExcentoPrimeraMoneda() {
        return excentoPrimeraMoneda;
    }

    public void setExcentoPrimeraMoneda(BigDecimal excentoPrimeraMoneda) {
        this.excentoPrimeraMoneda = excentoPrimeraMoneda;
    }

    public BigDecimal getExcentoSegundaMoneda() {
        return excentoSegundaMoneda;
    }

    public void setExcentoSegundaMoneda(BigDecimal excentoSegundaMoneda) {
        this.excentoSegundaMoneda = excentoSegundaMoneda;
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

    public ParTipoCompra getParTipoCompra() {
        return parTipoCompra;
    }

    public void setParTipoCompra(ParTipoCompra parTipoCompra) {
        this.parTipoCompra = parTipoCompra;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
            
}
