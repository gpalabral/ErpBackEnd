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
@Table(name = "RH_SECCION")
public class RhSeccion extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seccion", nullable = false)
    private Long idSeccion;
    
        
    @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento")
    @ManyToOne(optional = false)
    private ErpDepartamento erpDepartamento;
    
    @Column(name = "codigo", precision = 18, scale = 2)
    private String codigo;

    @Column(name = "descripcion", precision = 18, scale = 2)
    private String descripcion;

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeccion != null ? idSeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RhSeccion)) {
            return false;
        }
        RhSeccion other = (RhSeccion) object;
        if ((this.idSeccion == null && other.idSeccion != null) || (this.idSeccion != null && !this.idSeccion.equals(other.idSeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhSeccion[ idSeccion=" + idSeccion + " ]";
    }

    public Long getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Long idSeccion) {
        this.idSeccion = idSeccion;
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

    public ErpDepartamento getErpDepartamento() {
        return erpDepartamento;
    }

    public void setErpDepartamento(ErpDepartamento erpDepartamento) {
        this.erpDepartamento = erpDepartamento;
    }
            
}
