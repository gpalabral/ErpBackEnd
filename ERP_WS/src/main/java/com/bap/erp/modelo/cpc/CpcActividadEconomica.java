package com.bap.erp.modelo.cpc;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//AUTOR: Jonathan

@Entity
@Table(name = "CPC_ACTIVIDAD_ECONOMICA")
public class CpcActividadEconomica extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad_economica")
    private Long idActividadEconomica;
    
    @Column(name = "codigo", length = 20)
    private String codigo;
    
    @Column(name = "descripcion", length = 100)
    private String descripcion;
    
    @Column(name = "estado", length = 100)
    private String estado;

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividadEconomica != null ? idActividadEconomica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CpcActividadEconomica)) {
            return false;
        }
        CpcActividadEconomica other = (CpcActividadEconomica) object;
        if ((this.idActividadEconomica == null && other.idActividadEconomica != null) || (this.idActividadEconomica != null && !this.idActividadEconomica.equals(other.idActividadEconomica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CpcActividadEconomica[ idActividadEconomica=" + idActividadEconomica + " ]";
    }
    
    public Long getIdActividadEconomica() {
        return idActividadEconomica;
    }

    public void setIdActividadEconomica(Long idActividadEconomica) {
        this.idActividadEconomica = idActividadEconomica;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
