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
@Table(name = "RH_PLANILLA_APORTES_SEGURIDAD_SOCIAL")
public class RhPlanillaAportesSeguridadSocial extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planilla_aportes_seguridad_social", nullable = false)
    private Long idPlanillaAportesSeguridadSocial;

    @JoinColumn(name = "id_periodo_gestion", referencedColumnName = "id_periodo_gestion")
    @ManyToOne(optional = false)
    private RhPeriodoGestion rhPeriodoGestion;

    @JoinColumn(name = "id_empleado_cargo", referencedColumnName = "id_empleado_cargo")
    @ManyToOne(optional = false)
    private RhEmpleadoCargo rhEmpleadoCargo;

    @Column(name = "total_ganado", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalGanado;
    
    @Column(name = "sip_patronal", nullable = false, precision = 18, scale = 2)
    private BigDecimal sipPatronal;
    
    @Column(name = "sip_laboral", nullable = false, precision = 18, scale = 2)
    private BigDecimal sipLaboral;
    
    @Column(name = "sip_subtotal", nullable = false, precision = 18, scale = 2)
    private BigDecimal sipSubtotal;
    
    @Column(name = "ans_patronal", nullable = false, precision = 18, scale = 2)
    private BigDecimal ansPatronal;
    
    @Column(name = "ans_laboral", nullable = false, precision = 18, scale = 2)
    private BigDecimal ansLaboral;
    
    @Column(name = "ans_subtotal", nullable = false, precision = 18, scale = 2)
    private BigDecimal ansSubtotal;
    
    @Column(name = "provivienda", nullable = false, precision = 18, scale = 2)
    private BigDecimal provivienda;
    
    @Column(name = "infocal", nullable = false, precision = 18, scale = 2)
    private BigDecimal infocal;
    
    @Column(name = "caja_salud", nullable = false, precision = 18, scale = 2)
    private BigDecimal cajaSalud;
    
    @Column(name = "total_general", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalGeneral;       

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanillaAportesSeguridadSocial != null ? idPlanillaAportesSeguridadSocial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RhPlanillaAportesSeguridadSocial)) {
            return false;
        }
        RhPlanillaAportesSeguridadSocial other = (RhPlanillaAportesSeguridadSocial) object;
        if ((this.idPlanillaAportesSeguridadSocial == null && other.idPlanillaAportesSeguridadSocial != null) || (this.idPlanillaAportesSeguridadSocial != null && !this.idPlanillaAportesSeguridadSocial.equals(other.idPlanillaAportesSeguridadSocial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhPlanillaAportesSeguridadSocial[ idPlanillaAportesSeguridadSocial=" + idPlanillaAportesSeguridadSocial + " ]";
    }

    public Long getIdPlanillaAportesSeguridadSocial() {
        return idPlanillaAportesSeguridadSocial;
    }

    public void setIdPlanillaAportesSeguridadSocial(Long idPlanillaAportesSeguridadSocial) {
        this.idPlanillaAportesSeguridadSocial = idPlanillaAportesSeguridadSocial;
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

    public BigDecimal getTotalGanado() {
        return totalGanado;
    }

    public void setTotalGanado(BigDecimal totalGanado) {
        this.totalGanado = totalGanado;
    }

    public BigDecimal getSipPatronal() {
        return sipPatronal;
    }

    public void setSipPatronal(BigDecimal sipPatronal) {
        this.sipPatronal = sipPatronal;
    }

    public BigDecimal getSipLaboral() {
        return sipLaboral;
    }

    public void setSipLaboral(BigDecimal sipLaboral) {
        this.sipLaboral = sipLaboral;
    }

    public BigDecimal getSipSubtotal() {
        return sipSubtotal;
    }

    public void setSipSubtotal(BigDecimal sipSubtotal) {
        this.sipSubtotal = sipSubtotal;
    }

    public BigDecimal getAnsPatronal() {
        return ansPatronal;
    }

    public void setAnsPatronal(BigDecimal ansPatronal) {
        this.ansPatronal = ansPatronal;
    }

    public BigDecimal getAnsLaboral() {
        return ansLaboral;
    }

    public void setAnsLaboral(BigDecimal ansLaboral) {
        this.ansLaboral = ansLaboral;
    }

    public BigDecimal getAnsSubtotal() {
        return ansSubtotal;
    }

    public void setAnsSubtotal(BigDecimal ansSubtotal) {
        this.ansSubtotal = ansSubtotal;
    }

    public BigDecimal getProvivienda() {
        return provivienda;
    }

    public void setProvivienda(BigDecimal provivienda) {
        this.provivienda = provivienda;
    }

    public BigDecimal getInfocal() {
        return infocal;
    }

    public void setInfocal(BigDecimal infocal) {
        this.infocal = infocal;
    }

    public BigDecimal getCajaSalud() {
        return cajaSalud;
    }

    public void setCajaSalud(BigDecimal cajaSalud) {
        this.cajaSalud = cajaSalud;
    }

    public BigDecimal getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(BigDecimal totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

    
}
