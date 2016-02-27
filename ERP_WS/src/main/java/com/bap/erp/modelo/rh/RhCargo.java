package com.bap.erp.modelo.rh;

import com.bap.erp.modelo.*;
import com.bap.erp.commons.entities.AbstractEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RH_CARGO")
public class RhCargo extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo", nullable = false)
    private Long idCargo;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento")
    @ManyToOne(optional = false)
    private ErpDepartamento erpDepartamento;

    @JoinColumn(name = "id_seccion", referencedColumnName = "id_seccion")
    @ManyToOne(optional = true)
    private RhSeccion rhSeccion;

    @Column(name = "nombre_cargo")
    private String nombreCargo;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCargo != null ? idCargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RhCargo)) {
            return false;
        }
        RhCargo other = (RhCargo) object;
        if ((this.idCargo == null && other.idCargo != null) || (this.idCargo != null && !this.idCargo.equals(other.idCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhCargo[ idCargo=" + idCargo + " ]";
    }

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ErpDepartamento getErpDepartamento() {
        return erpDepartamento;
    }

    public void setErpDepartamento(ErpDepartamento erpDepartamento) {
        this.erpDepartamento = erpDepartamento;
    }

    public RhSeccion getRhSeccion() {
        return rhSeccion;
    }

    public void setRhSeccion(RhSeccion rhSeccion) {
        this.rhSeccion = rhSeccion;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

}
