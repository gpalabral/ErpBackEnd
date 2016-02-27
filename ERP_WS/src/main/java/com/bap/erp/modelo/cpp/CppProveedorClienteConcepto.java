package com.bap.erp.modelo.cpp;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
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
@Table(name = "CPP_PROVEEDOR_CLIENTE_CONCEPTO")
public class CppProveedorClienteConcepto extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor_cliente_concepto", nullable = false)
    private Long idProveedorClienteConcepto;
    
    @JoinColumn(name = "id_proveedor_cliente", referencedColumnName = "id_proveedor_cliente")
    @ManyToOne(optional = false)
    private CppProveedorCliente cppProveedorCliente;
       
    @JoinColumn(name = "id_concepto", referencedColumnName = "id_concepto")
    @ManyToOne(optional = false)
    private CppConcepto cppConcepto;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedorClienteConcepto != null ? idProveedorClienteConcepto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CppProveedorClienteConcepto)) {
            return false;
        }
        CppProveedorClienteConcepto other = (CppProveedorClienteConcepto) object;
        if ((this.idProveedorClienteConcepto == null && other.idProveedorClienteConcepto != null) || (this.idProveedorClienteConcepto != null && !this.idProveedorClienteConcepto.equals(other.idProveedorClienteConcepto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CppProveedorClienteConcepto[ idProveedorClienteConcepto=" + idProveedorClienteConcepto + " ]";
    }        

    public Long getIdProveedorClienteConcepto() {
        return idProveedorClienteConcepto;
    }

    public void setIdProveedorClienteConcepto(Long idProveedorClienteConcepto) {
        this.idProveedorClienteConcepto = idProveedorClienteConcepto;
    }

    public CppProveedorCliente getCppProveedorCliente() {
        return cppProveedorCliente;
    }

    public void setCppProveedorCliente(CppProveedorCliente cppProveedorCliente) {
        this.cppProveedorCliente = cppProveedorCliente;
    }

    public CppConcepto getCppConcepto() {
        return cppConcepto;
    }

    public void setCppConcepto(CppConcepto cppConcepto) {
        this.cppConcepto = cppConcepto;
    }

    
}
