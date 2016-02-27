package com.bap.erp.modelo.rh;

import com.bap.erp.modelo.*;
import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.par.ParTipoContratoEmpleado;
import com.bap.erp.modelo.par.ParTipoEmpleado;
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
@Table(name = "RH_EMPLEADO_CARGO")
public class RhEmpleadoCargo extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado_cargo", nullable = false)
    private Long idEmpleadoCargo;

    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private RhEmpleado rhEmpleado;

    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    @ManyToOne(optional = false)
    private RhCargo rhCargo;

    @Column(name = "numero_item", nullable = false)
    private Long numeroItem;

    @Column(name = "sueldo", precision = 18, scale = 2)
    private BigDecimal sueldo;

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;

    @Column(name = "id_antecesor", nullable = false)
    private Long idAntecesor;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_contrato_empleado", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoContratoEmpleado parTipoContratoEmpleado;



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleadoCargo != null ? idEmpleadoCargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RhEmpleadoCargo)) {
            return false;
        }
        RhEmpleadoCargo other = (RhEmpleadoCargo) object;
        if ((this.idEmpleadoCargo == null && other.idEmpleadoCargo != null) || (this.idEmpleadoCargo != null && !this.idEmpleadoCargo.equals(other.idEmpleadoCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhEmpleadoCargo[ idEmpleadoCargo=" + idEmpleadoCargo + " ]";
    }

    public Long getIdEmpleadoCargo() {
        return idEmpleadoCargo;
    }

    public void setIdEmpleadoCargo(Long idEmpleadoCargo) {
        this.idEmpleadoCargo = idEmpleadoCargo;
    }

    public RhEmpleado getRhEmpleado() {
        return rhEmpleado;
    }

    public void setRhEmpleado(RhEmpleado rhEmpleado) {
        this.rhEmpleado = rhEmpleado;
    }

    public RhCargo getRhCargo() {
        return rhCargo;
    }

    public void setRhCargo(RhCargo rhCargo) {
        this.rhCargo = rhCargo;
    }

    public Long getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(Long numeroItem) {
        this.numeroItem = numeroItem;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Long getIdAntecesor() {
        return idAntecesor;
    }

    public void setIdAntecesor(Long idAntecesor) {
        this.idAntecesor = idAntecesor;
    }   

    public ParTipoContratoEmpleado getParTipoContratoEmpleado() {
        return parTipoContratoEmpleado;
    }

    public void setParTipoContratoEmpleado(ParTipoContratoEmpleado parTipoContratoEmpleado) {
        this.parTipoContratoEmpleado = parTipoContratoEmpleado;
    }
    
    
}
