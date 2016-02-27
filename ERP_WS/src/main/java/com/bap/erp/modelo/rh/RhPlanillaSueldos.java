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
@Table(name = "RH_PLANILLA_SUELDOS")
public class RhPlanillaSueldos extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planilla_sueldos", nullable = false)
    private Long idPlanillaSueldos;

    @JoinColumn(name = "id_periodo_gestion", referencedColumnName = "id_periodo_gestion")
    @ManyToOne(optional = false)
    private RhPeriodoGestion rhPeriodoGestion;

    @JoinColumn(name = "id_empleado_cargo", referencedColumnName = "id_empleado_cargo")
    @ManyToOne(optional = false)
    private RhEmpleadoCargo rhEmpleadoCargo;

    @Column(name = "dias_trabajados", nullable = false, precision = 18, scale = 2)
    private BigDecimal diasTrabajados;

    @Column(name = "ingreso_dias_trabajados", nullable = false, precision = 18, scale = 2)
    private BigDecimal ingresoDiasTrabajados;

    @Column(name = "dias_domingo", nullable = false, precision = 18, scale = 2)
    private BigDecimal diasDomingo;

    @Column(name = "ingreso_dias_domingo", nullable = false, precision = 18, scale = 2)
    private BigDecimal ingresoDiasDomingo;

    @Column(name = "horas_extras", nullable = false, precision = 18, scale = 2)
    private BigDecimal horasExtras;

    @Column(name = "ingreso_horas_extras", nullable = false, precision = 18, scale = 2)
    private BigDecimal ingresoHorasExtras;

    @Column(name = "horas_nocturnas", nullable = false, precision = 18, scale = 2)
    private BigDecimal horasNocturnas;

    @Column(name = "ingreso_horas_nocturnas", nullable = false, precision = 18, scale = 2)
    private BigDecimal ingresoHorasNocturnas;

    @Column(name = "horas_domingo", nullable = false, precision = 18, scale = 2)
    private BigDecimal horasDomingo;

    @Column(name = "ingreso_horas_domingo", nullable = false, precision = 18, scale = 2)
    private BigDecimal ingresoHorasDomingo;

    @Column(name = "porcentaje_antiguedad", nullable = false, precision = 18, scale = 2)
    private BigDecimal porcentajeAntiguedad;

    @Column(name = "bono_antiguedad", nullable = false, precision = 18, scale = 2)
    private BigDecimal bonoAntiguedad;

    @Column(name = "bono_produccion", nullable = false, precision = 18, scale = 2)
    private BigDecimal bonoProduccion;

    @Column(name = "total_ganado", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalGanado;

    @Column(name = "afp", nullable = false, precision = 18, scale = 2)
    private BigDecimal afp;

    @Column(name = "aporte_nacional_solidario", nullable = false, precision = 18, scale = 2)
    private BigDecimal aporteNacionalSolidario;

    @Column(name = "rc_iva", nullable = false, precision = 18, scale = 2)
    private BigDecimal rcIva;

    @Column(name = "otros_descuentos", nullable = false, precision = 18, scale = 2)
    private BigDecimal otrosDescuentos;

    @Column(name = "total_descuentos", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalDescuentos;

    @Column(name = "liquido_pagable", nullable = false, precision = 18, scale = 2)
    private BigDecimal liquidoPagable;

    @Column(name = "otros_bonos", nullable = false, precision = 18, scale = 2)
    private BigDecimal otrosBonos;

    @ManyToOne()
    @JoinColumn(name = "par_estado_liquidacion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEstadoLiquidacion parEstadoLiquidacion;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanillaSueldos != null ? idPlanillaSueldos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RhPlanillaSueldos)) {
            return false;
        }
        RhPlanillaSueldos other = (RhPlanillaSueldos) object;
        if ((this.idPlanillaSueldos == null && other.idPlanillaSueldos != null) || (this.idPlanillaSueldos != null && !this.idPlanillaSueldos.equals(other.idPlanillaSueldos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhPlanillaSueldos[ idPlanillaSueldos=" + idPlanillaSueldos + " ]";
    }

    public Long getIdPlanillaSueldos() {
        return idPlanillaSueldos;
    }

    public void setIdPlanillaSueldos(Long idPlanillaSueldos) {
        this.idPlanillaSueldos = idPlanillaSueldos;
    }

    public RhPeriodoGestion getRhPeriodoGestion() {
        return rhPeriodoGestion;
    }

    public void setRhPeriodoGestion(RhPeriodoGestion rhPeriodoGestion) {
        this.rhPeriodoGestion = rhPeriodoGestion;
    }

    public RhEmpleadoCargo getRhEmpleadoCargo() {
        return rhEmpleadoCargo;
    }

    public void setRhEmpleadoCargo(RhEmpleadoCargo rhEmpleadoCargo) {
        this.rhEmpleadoCargo = rhEmpleadoCargo;
    }

    public BigDecimal getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(BigDecimal diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    public BigDecimal getIngresoDiasTrabajados() {
        return ingresoDiasTrabajados;
    }

    public void setIngresoDiasTrabajados(BigDecimal ingresoDiasTrabajados) {
        this.ingresoDiasTrabajados = ingresoDiasTrabajados;
    }

    public BigDecimal getDiasDomingo() {
        return diasDomingo;
    }

    public void setDiasDomingo(BigDecimal diasDomingo) {
        this.diasDomingo = diasDomingo;
    }

    public BigDecimal getIngresoDiasDomingo() {
        return ingresoDiasDomingo;
    }

    public void setIngresoDiasDomingo(BigDecimal ingresoDiasDomingo) {
        this.ingresoDiasDomingo = ingresoDiasDomingo;
    }

    public BigDecimal getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(BigDecimal horasExtras) {
        this.horasExtras = horasExtras;
    }

    public BigDecimal getIngresoHorasExtras() {
        return ingresoHorasExtras;
    }

    public void setIngresoHorasExtras(BigDecimal ingresoHorasExtras) {
        this.ingresoHorasExtras = ingresoHorasExtras;
    }

    public BigDecimal getHorasNocturnas() {
        return horasNocturnas;
    }

    public void setHorasNocturnas(BigDecimal horasNocturnas) {
        this.horasNocturnas = horasNocturnas;
    }

    public BigDecimal getIngresoHorasNocturnas() {
        return ingresoHorasNocturnas;
    }

    public void setIngresoHorasNocturnas(BigDecimal ingresoHorasNocturnas) {
        this.ingresoHorasNocturnas = ingresoHorasNocturnas;
    }

    public BigDecimal getHorasDomingo() {
        return horasDomingo;
    }

    public void setHorasDomingo(BigDecimal horasDomingo) {
        this.horasDomingo = horasDomingo;
    }

    public BigDecimal getIngresoHorasDomingo() {
        return ingresoHorasDomingo;
    }

    public void setIngresoHorasDomingo(BigDecimal ingresoHorasDomingo) {
        this.ingresoHorasDomingo = ingresoHorasDomingo;
    }

    public BigDecimal getPorcentajeAntiguedad() {
        return porcentajeAntiguedad;
    }

    public void setPorcentajeAntiguedad(BigDecimal porcentajeAntiguedad) {
        this.porcentajeAntiguedad = porcentajeAntiguedad;
    }

    public BigDecimal getBonoAntiguedad() {
        return bonoAntiguedad;
    }

    public void setBonoAntiguedad(BigDecimal bonoAntiguedad) {
        this.bonoAntiguedad = bonoAntiguedad;
    }

    public BigDecimal getBonoProduccion() {
        return bonoProduccion;
    }

    public void setBonoProduccion(BigDecimal bonoProduccion) {
        this.bonoProduccion = bonoProduccion;
    }

    public BigDecimal getTotalGanado() {
        return totalGanado;
    }

    public void setTotalGanado(BigDecimal totalGanado) {
        this.totalGanado = totalGanado;
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

    public BigDecimal getOtrosDescuentos() {
        return otrosDescuentos;
    }

    public void setOtrosDescuentos(BigDecimal otrosDescuentos) {
        this.otrosDescuentos = otrosDescuentos;
    }

    public BigDecimal getTotalDescuentos() {
        return totalDescuentos;
    }

    public void setTotalDescuentos(BigDecimal totalDescuentos) {
        this.totalDescuentos = totalDescuentos;
    }

    public BigDecimal getLiquidoPagable() {
        return liquidoPagable;
    }

    public void setLiquidoPagable(BigDecimal liquidoPagable) {
        this.liquidoPagable = liquidoPagable;
    }

    public BigDecimal getRcIva() {
        return rcIva;
    }

    public void setRcIva(BigDecimal rcIva) {
        this.rcIva = rcIva;
    }

    public BigDecimal getOtrosBonos() {
        return otrosBonos;
    }

    public void setOtrosBonos(BigDecimal otrosBonos) {
        this.otrosBonos = otrosBonos;
    }

    public ParEstadoLiquidacion getParEstadoLiquidacion() {
        return parEstadoLiquidacion;
    }

    public void setParEstadoLiquidacion(ParEstadoLiquidacion parEstadoLiquidacion) {
        this.parEstadoLiquidacion = parEstadoLiquidacion;
    }

    
}
