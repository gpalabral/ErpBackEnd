package com.bap.erp.modelo.rh;

import com.bap.erp.modelo.*;
import com.bap.erp.commons.entities.AbstractEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RH_VARIACION")
public class RhVariacion extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_variacion", nullable = false)
    private Long idVariacion;

    @Column(name = "item", nullable = false)
    private Long item;

    @JoinColumn(name = "id_empleado_cargo", referencedColumnName = "id_empleado_cargo")
    @ManyToOne(optional = false)
    private RhEmpleadoCargo rhEmpleadoCargo;

    @JoinColumn(name = "id_periodo_gestion", referencedColumnName = "id_periodo_gestion")
    @ManyToOne(optional = false)
    private RhPeriodoGestion rhPeriodoGestion;

    @Column(name = "dias_trabajados", nullable = false, precision = 10, scale = 2)
    private BigDecimal diasTrabajados;

    @Column(name = "dias_no_trabajados", nullable = false, precision = 10, scale = 2)
    private BigDecimal diasNoTrabajados;

    @Column(name = "dias_de_falta", nullable = false, precision = 10, scale = 2)
    private BigDecimal diasDeFalta;

    @Column(name = "dias_de_multa", nullable = false, precision = 10, scale = 2)
    private BigDecimal diasDeMulta;

    @Column(name = "horas_extras", nullable = false, precision = 10, scale = 2)
    private BigDecimal horasExtras;

    @Column(name = "horas_nocturnas", nullable = false, precision = 10, scale = 2)
    private BigDecimal horasNocturnas;

    @Column(name = "horas_domingo", nullable = false, precision = 10, scale = 2)
    private BigDecimal horasDomingo;

    @Column(name = "horas_feriado", nullable = false, precision = 10, scale = 2)
    private BigDecimal horasFeriado;

    @Column(name = "horas_ajuste", nullable = false, precision = 10, scale = 2)
    private BigDecimal horasAjuste;

    @Column(name = "dias_domingo", nullable = false, precision = 10, scale = 2)
    private BigDecimal diasDomingo;

    @Column(name = "dias_feriado", nullable = false, precision = 10, scale = 2)
    private BigDecimal diasFeriado;

    @Column(name = "dias_ajuste", nullable = false, precision = 10, scale = 2)
    private BigDecimal diasAjuste;

    @Column(name = "dias_descanso", nullable = false, precision = 10, scale = 2)
    private BigDecimal diasDescanso;

    @Column(name = "dias_descanso_trabajados", nullable = false, precision = 10, scale = 2)
    private BigDecimal diasDescansoTrabajados;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVariacion != null ? idVariacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RhVariacion)) {
            return false;
        }
        RhVariacion other = (RhVariacion) object;
        if ((this.idVariacion == null && other.idVariacion != null) || (this.idVariacion != null && !this.idVariacion.equals(other.idVariacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhVariacion[ idVariacion=" + idVariacion + " ]";
    }

    public Long getIdVariacion() {
        return idVariacion;
    }

    public void setIdVariacion(Long idVariacion) {
        this.idVariacion = idVariacion;
    }

    public BigDecimal getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(BigDecimal diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    public BigDecimal getDiasNoTrabajados() {
        return diasNoTrabajados;
    }

    public void setDiasNoTrabajados(BigDecimal diasNoTrabajados) {
        this.diasNoTrabajados = diasNoTrabajados;
    }

    public BigDecimal getDiasDeFalta() {
        return diasDeFalta;
    }

    public void setDiasDeFalta(BigDecimal diasDeFalta) {
        this.diasDeFalta = diasDeFalta;
    }

    public BigDecimal getDiasDeMulta() {
        return diasDeMulta;
    }

    public void setDiasDeMulta(BigDecimal diasDeMulta) {
        this.diasDeMulta = diasDeMulta;
    }

    public BigDecimal getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(BigDecimal horasExtras) {
        this.horasExtras = horasExtras;
    }

    public BigDecimal getHorasNocturnas() {
        return horasNocturnas;
    }

    public void setHorasNocturnas(BigDecimal horasNocturnas) {
        this.horasNocturnas = horasNocturnas;
    }

    public BigDecimal getHorasDomingo() {
        return horasDomingo;
    }

    public void setHorasDomingo(BigDecimal horasDomingo) {
        this.horasDomingo = horasDomingo;
    }

    public BigDecimal getHorasFeriado() {
        return horasFeriado;
    }

    public void setHorasFeriado(BigDecimal horasFeriado) {
        this.horasFeriado = horasFeriado;
    }

    public BigDecimal getHorasAjuste() {
        return horasAjuste;
    }

    public void setHorasAjuste(BigDecimal horasAjuste) {
        this.horasAjuste = horasAjuste;
    }

    public BigDecimal getDiasDomingo() {
        return diasDomingo;
    }

    public void setDiasDomingo(BigDecimal diasDomingo) {
        this.diasDomingo = diasDomingo;
    }

    public BigDecimal getDiasFeriado() {
        return diasFeriado;
    }

    public void setDiasFeriado(BigDecimal diasFeriado) {
        this.diasFeriado = diasFeriado;
    }

    public BigDecimal getDiasAjuste() {
        return diasAjuste;
    }

    public void setDiasAjuste(BigDecimal diasAjuste) {
        this.diasAjuste = diasAjuste;
    }

    public Long getItem() {
        return item;
    }

    public void setItem(Long item) {
        this.item = item;
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

    public BigDecimal getDiasDescanso() {
        return diasDescanso;
    }

    public void setDiasDescanso(BigDecimal diasDescanso) {
        this.diasDescanso = diasDescanso;
    }

    public BigDecimal getDiasDescansoTrabajados() {
        return diasDescansoTrabajados;
    }

    public void setDiasDescansoTrabajados(BigDecimal diasDescansoTrabajados) {
        this.diasDescansoTrabajados = diasDescansoTrabajados;
    }

}
