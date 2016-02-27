package com.bap.erp.modelo.cpc;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import com.bap.erp.modelo.ban.CuentaBancaria;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.par.ParTipoMoneda;
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
@Table(name = "CPC_CONTRATO")
public class CpcContrato extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_contrato", nullable = false)
    private Long idContrato;

    @JoinColumn(name = "id_proveedor_cliente", referencedColumnName = "id_proveedor_cliente")
    @ManyToOne(optional = false)
    private CppProveedorCliente cppProveedorCliente;

    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    @ManyToOne(optional = false)
    private CpcSucursal cpcSucursal;

    @Column(name = "nro_contrato", length = 50)
    private String nroContrato;

    @Column(name = "fecha_contrato")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaContrato;

    @Column(name = "fecha_vigencia_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVigenciaInicio;

    @Column(name = "fecha_vigencia_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVigenciaFin;

    @Column(name = "nro_cuotas", length = 50)
    private Long nroCuotas;

    @Column(name = "monto_primera_moneda", precision = 18, scale = 5)
    private BigDecimal montoPrimeraMoneda;

    @Column(name = "monto_segunda_moneda", precision = 18, scale = 5)
    private BigDecimal montoSegundaMoneda;
    
    @Column(name = "tipo_cambio", precision = 18, scale = 5)
    private BigDecimal tipoCambio;

    @Column(name = "nro_contrato_cliente", length = 50)
    private String nroContratoCliente;

    @JoinColumn(name = "id_cuenta_bancaria", referencedColumnName = "id_cuenta_bancaria")
    @ManyToOne(optional = true)
    private CuentaBancaria cuentaBancaria;

    @ManyToOne()
    @JoinColumn(name = "moneda_contrato", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoMoneda parTipoMoneda;
    
    @Column(name = "nombre_contrato", length = 100, nullable = false)
    private String nombreContrato;

    public Long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
    }

    public CppProveedorCliente getCppProveedorCliente() {
        return cppProveedorCliente;
    }

    public void setCppProveedorCliente(CppProveedorCliente cppProveedorCliente) {
        this.cppProveedorCliente = cppProveedorCliente;
    }

    public CpcSucursal getCpcSucursal() {
        return cpcSucursal;
    }

    public void setCpcSucursal(CpcSucursal cpcSucursal) {
        this.cpcSucursal = cpcSucursal;
    }

    public String getNroContrato() {
        return nroContrato;
    }

    public void setNroContrato(String nroContrato) {
        this.nroContrato = nroContrato;
    }

    public Date getFechaVigenciaInicio() {
        return fechaVigenciaInicio;
    }

    public void setFechaVigenciaInicio(Date fechaVigenciaInicio) {
        this.fechaVigenciaInicio = fechaVigenciaInicio;
    }

    public Date getFechaVigenciaFin() {
        return fechaVigenciaFin;
    }

    public void setFechaVigenciaFin(Date fechaVigenciaFin) {
        this.fechaVigenciaFin = fechaVigenciaFin;
    }

    public Long getNroCuotas() {
        return nroCuotas;
    }

    public void setNroCuotas(Long nroCuotas) {
        this.nroCuotas = nroCuotas;
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

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    
    public String getNroContratoCliente() {
        return nroContratoCliente;
    }

    public void setNroContratoCliente(String nroContratoCliente) {
        this.nroContratoCliente = nroContratoCliente;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public ParTipoMoneda getParTipoMoneda() {
        return parTipoMoneda;
    }

    public void setParTipoMoneda(ParTipoMoneda parTipoMoneda) {
        this.parTipoMoneda = parTipoMoneda;
    }

    public String getNombreContrato() {
        return nombreContrato;
    }

    public void setNombreContrato(String nombreContrato) {
        this.nombreContrato = nombreContrato;
    }
    
}
