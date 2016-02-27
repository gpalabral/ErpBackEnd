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
@Table(name = "RH_DESCUENTO")
public class RhDescuento extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descuento", nullable = false)
    private Long idDescuento;
    
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
        hash += (idDescuento != null ? idDescuento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RhDescuento)) {
            return false;
        }
        RhDescuento other = (RhDescuento) object;
        if ((this.idDescuento == null && other.idDescuento != null) || (this.idDescuento != null && !this.idDescuento.equals(other.idDescuento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhDescuento[ idDescuento=" + idDescuento + " ]";
    }

    public Long getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(Long idDescuento) {
        this.idDescuento = idDescuento;
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
