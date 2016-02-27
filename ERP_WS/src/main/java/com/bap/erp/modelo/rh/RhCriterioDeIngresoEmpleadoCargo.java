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
@Table(name = "RH_CRITERIO_DE_INGRESO_EMPLEADO_CARGO")
public class RhCriterioDeIngresoEmpleadoCargo extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_criterio_de_ingreso_empleado_cargo", nullable = false)
    private Long idCriterioDeIngresoEmpleadoCargo;

    @JoinColumn(name = "id_criterio_de_ingreso", referencedColumnName = "id_criterio_de_ingreso")
    @ManyToOne(optional = false)
    private RhCriterioDeIngreso rhCriterioDeIngreso;

    @JoinColumn(name = "id_empleado_cargo", referencedColumnName = "id_empleado_cargo")
    @ManyToOne(optional = false)
    private RhEmpleadoCargo rhEmpleadoCargo;

    @Column(name = "monto_criterio_de_ingreso", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoCriterioDeIngreso;

    @JoinColumn(name = "id_periodo_gestion", referencedColumnName = "id_periodo_gestion")
    @ManyToOne(optional = false)
    private RhPeriodoGestion rhPeriodoGestion;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCriterioDeIngresoEmpleadoCargo != null ? idCriterioDeIngresoEmpleadoCargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RhCriterioDeIngresoEmpleadoCargo)) {
            return false;
        }
        RhCriterioDeIngresoEmpleadoCargo other = (RhCriterioDeIngresoEmpleadoCargo) object;
        if ((this.idCriterioDeIngresoEmpleadoCargo == null && other.idCriterioDeIngresoEmpleadoCargo != null) || (this.idCriterioDeIngresoEmpleadoCargo != null && !this.idCriterioDeIngresoEmpleadoCargo.equals(other.idCriterioDeIngresoEmpleadoCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhCriterioDeIngresoEmpleadoCargo[ idCriterioDeIngresoEmpleadoCargo=" + idCriterioDeIngresoEmpleadoCargo + " ]";
    }

    public Long getIdCriterioDeIngresoEmpleadoCargo() {
        return idCriterioDeIngresoEmpleadoCargo;
    }

    public void setIdCriterioDeIngresoEmpleadoCargo(Long idCriterioDeIngresoEmpleadoCargo) {
        this.idCriterioDeIngresoEmpleadoCargo = idCriterioDeIngresoEmpleadoCargo;
    }

    public RhCriterioDeIngreso getRhCriterioDeIngreso() {
        return rhCriterioDeIngreso;
    }

    public void setRhCriterioDeIngreso(RhCriterioDeIngreso rhCriterioDeIngreso) {
        this.rhCriterioDeIngreso = rhCriterioDeIngreso;
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

    public BigDecimal getMontoCriterioDeIngreso() {
        return montoCriterioDeIngreso;
    }

    public void setMontoCriterioDeIngreso(BigDecimal montoCriterioDeIngreso) {
        this.montoCriterioDeIngreso = montoCriterioDeIngreso;
    }        
    
}
