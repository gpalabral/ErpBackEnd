package com.bap.erp.modelo.rh;

import com.bap.erp.modelo.*;
import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.par.ParEstadoLiquidacion;
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

@Entity
@Table(name = "RH_PLANILLA_IMPOSITIVA")
public class RhPlanillaImpositiva extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planilla_impositiva", nullable = false)
    private Long idPlanillaImpositiva;

    @JoinColumn(name = "id_empleado_cargo", referencedColumnName = "id_empleado_cargo")
    @ManyToOne(optional = false)
    private RhEmpleadoCargo rhEmpleadoCargo;

    @JoinColumn(name = "id_periodo_gestion", referencedColumnName = "id_periodo_gestion")
    @ManyToOne(optional = false)
    private RhPeriodoGestion rhPeriodoGestion;

    @Column(name = "total_ganado", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalGanado;

    @JoinColumn(name = "id_primas", referencedColumnName = "id_primas")
    @ManyToOne(optional = false)
    private RhPrimas rhPrimas;

    @Column(name = "afp", nullable = false, precision = 10, scale = 2)
    private BigDecimal afp;

    @Column(name = "aporte_nacional_solidario", nullable = false, precision = 10, scale = 2)
    private BigDecimal aporteNacionalSolidario;

    @Column(name = "sueldo_neto", nullable = false, precision = 10, scale = 2)
    private BigDecimal sueldoNeto;

    @Column(name = "dos_salarios_minimos", nullable = false, precision = 10, scale = 2)
    private BigDecimal dosSalariosMinimos;

    @Column(name = "base_imponible", nullable = false, precision = 10, scale = 2)
    private BigDecimal baseImponible;

    @Column(name = "debito_fiscal", nullable = false, precision = 10, scale = 2)
    private BigDecimal debitoFiscal;

    @Column(name = "credito_fiscal", nullable = false, precision = 10, scale = 2)
    private BigDecimal creditoFiscal;

    @Column(name = "computo_dos_minimos_nacionales", nullable = false, precision = 10, scale = 2)
    private BigDecimal computoDosMinimosNacionales;

    @Column(name = "saldo_periodo_anterior", nullable = false, precision = 10, scale = 2)
    private BigDecimal saldoPeriodoAnterior;

    @Column(name = "saldo_periodo_siguiente", nullable = false, precision = 10, scale = 2)
    private BigDecimal saldoPeriodoSiguiente;

    @Column(name = "impuesto_rc_iva", nullable = false, precision = 10, scale = 2)
    private BigDecimal impuestoRcIva;

    @ManyToOne()
    @JoinColumn(name = "par_estado_liquidacion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEstadoLiquidacion parEstadoLiquidacion;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanillaImpositiva != null ? idPlanillaImpositiva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RhPlanillaImpositiva)) {
            return false;
        }
        RhPlanillaImpositiva other = (RhPlanillaImpositiva) object;
        if ((this.idPlanillaImpositiva == null && other.idPlanillaImpositiva != null) || (this.idPlanillaImpositiva != null && !this.idPlanillaImpositiva.equals(other.idPlanillaImpositiva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhPlanillaImpositiva[ idPlanillaImpositiva=" + idPlanillaImpositiva + " ]";
    }

    public Long getIdPlanillaImpositiva() {
        return idPlanillaImpositiva;
    }

    public void setIdPlanillaImpositiva(Long idPlanillaImpositiva) {
        this.idPlanillaImpositiva = idPlanillaImpositiva;
    }

    public RhEmpleadoCargo getRhEmpleadoCargo() {
        return rhEmpleadoCargo;
    }

    public void setRhEmpleadoCargo(RhEmpleadoCargo rhEmpleadoCargo) {
        this.rhEmpleadoCargo = rhEmpleadoCargo;
    }

    public RhPeriodoGestion getRhPeriodoGestion() {
        return rhPeriodoGestion;
    }

    public void setRhPeriodoGestion(RhPeriodoGestion rhPeriodoGestion) {
        this.rhPeriodoGestion = rhPeriodoGestion;
    }

    public BigDecimal getTotalGanado() {
        return totalGanado;
    }

    public void setTotalGanado(BigDecimal totalGanado) {
        this.totalGanado = totalGanado;
    }

    public RhPrimas getRhPrimas() {
        return rhPrimas;
    }

    public void setRhPrimas(RhPrimas rhPrimas) {
        this.rhPrimas = rhPrimas;
    }

    public BigDecimal getAfp() {
        return afp;
    }

    public void setAfp(BigDecimal afp) {
        this.afp = afp;
    }

    public BigDecimal getAporteNacionalSolidario() {
        return aporteNacionalSolidario;
    }

    public void setAporteNacionalSolidario(BigDecimal aporteNacionalSolidario) {
        this.aporteNacionalSolidario = aporteNacionalSolidario;
    }

    public BigDecimal getSueldoNeto() {
        return sueldoNeto;
    }

    public void setSueldoNeto(BigDecimal sueldoNeto) {
        this.sueldoNeto = sueldoNeto;
    }

    public BigDecimal getDosSalariosMinimos() {
        return dosSalariosMinimos;
    }

    public void setDosSalariosMinimos(BigDecimal dosSalariosMinimos) {
        this.dosSalariosMinimos = dosSalariosMinimos;
    }

    public BigDecimal getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(BigDecimal baseImponible) {
        this.baseImponible = baseImponible;
    }

    public BigDecimal getDebitoFiscal() {
        return debitoFiscal;
    }

    public void setDebitoFiscal(BigDecimal debitoFiscal) {
        this.debitoFiscal = debitoFiscal;
    }

    public BigDecimal getCreditoFiscal() {
        return creditoFiscal;
    }

    public void setCreditoFiscal(BigDecimal creditoFiscal) {
        this.creditoFiscal = creditoFiscal;
    }

    public BigDecimal getComputoDosMinimosNacionales() {
        return computoDosMinimosNacionales;
    }

    public void setComputoDosMinimosNacionales(BigDecimal computoDosMinimosNacionales) {
        this.computoDosMinimosNacionales = computoDosMinimosNacionales;
    }

    public BigDecimal getSaldoPeriodoAnterior() {
        return saldoPeriodoAnterior;
    }

    public void setSaldoPeriodoAnterior(BigDecimal saldoPeriodoAnterior) {
        this.saldoPeriodoAnterior = saldoPeriodoAnterior;
    }

    public BigDecimal getSaldoPeriodoSiguiente() {
        return saldoPeriodoSiguiente;
    }

    public void setSaldoPeriodoSiguiente(BigDecimal saldoPeriodoSiguiente) {
        this.saldoPeriodoSiguiente = saldoPeriodoSiguiente;
    }

    public BigDecimal getImpuestoRcIva() {
        return impuestoRcIva;
    }

    public void setImpuestoRcIva(BigDecimal impuestoRcIva) {
        this.impuestoRcIva = impuestoRcIva;
    }

    public ParEstadoLiquidacion getParEstadoLiquidacion() {
        return parEstadoLiquidacion;
    }

    public void setParEstadoLiquidacion(ParEstadoLiquidacion parEstadoLiquidacion) {
        this.parEstadoLiquidacion = parEstadoLiquidacion;
    }
    
    
}
