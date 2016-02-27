package com.bap.erp.modelo.rh;

import com.bap.erp.modelo.*;
import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.par.ParTipoAplicacionDescuentoCriterioDeIngreso;
import java.io.Serializable;
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
@Table(name = "RH_CRITERIO_DE_INGRESO")
public class RhCriterioDeIngreso extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_criterio_de_ingreso", nullable = false)
    private Long idCriterioDeIngreso;

    @Column(name = "codigo", length = 50, nullable=false)
    private String codigo;
    
    
    @Column(name = "descripcion", length = 50, nullable=false)
    private String descripcion;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_aplicacion_descuento_criterio_de_ingreso", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoAplicacionDescuentoCriterioDeIngreso parTipoAplicacionDescuentoCriterioDeIngreso; //Monto, Porcentaje

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCriterioDeIngreso != null ? idCriterioDeIngreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RhCriterioDeIngreso)) {
            return false;
        }
        RhCriterioDeIngreso other = (RhCriterioDeIngreso) object;
        if ((this.idCriterioDeIngreso == null && other.idCriterioDeIngreso != null) || (this.idCriterioDeIngreso != null && !this.idCriterioDeIngreso.equals(other.idCriterioDeIngreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhCriterioDeIngreso[ idCriterioDeIngreso=" + idCriterioDeIngreso + " ]";
    }

    public Long getIdCriterioDeIngreso() {
        return idCriterioDeIngreso;
    }

    public void setIdCriterioDeIngreso(Long idCriterioDeIngreso) {
        this.idCriterioDeIngreso = idCriterioDeIngreso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ParTipoAplicacionDescuentoCriterioDeIngreso getParTipoAplicacionDescuentoCriterioDeIngreso() {
        return parTipoAplicacionDescuentoCriterioDeIngreso;
    }

    public void setParTipoAplicacionDescuentoCriterioDeIngreso(ParTipoAplicacionDescuentoCriterioDeIngreso parTipoAplicacionDescuentoCriterioDeIngreso) {
        this.parTipoAplicacionDescuentoCriterioDeIngreso = parTipoAplicacionDescuentoCriterioDeIngreso;
    }
  
}
