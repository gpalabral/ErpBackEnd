package com.bap.erp.modelo.cpp;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import com.bap.erp.modelo.par.ParTipoPago;
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
@Table(name = "CPP_FACTURA")
public class CppFactura extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura", nullable = false)
    private Long idFactura;
    
    @JoinColumn(name = "id_proveedor_cliente", referencedColumnName = "id_proveedor_cliente")
    @ManyToOne(optional = false)
    private CppProveedorCliente cppProveedorCliente;
    
    @Column(name = "id_cnt_comprobante", length = 50, nullable = false)
    private Long idCntComprobante; //id de tabla CntComprobante de ERP-conta
    
    @Column(name = "numero_factura", length = 30)
    private Long numeroFactura;
    
    @Column(name = "numero_pagos", length = 30)
    private Long numeroPagos;
    
    @Column(name = "dias_pagos", length = 30)
    private Long diasPagos;
    
    @ManyToOne()    
    @JoinColumn(name = "par_tipo_pago",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParTipoPago parTipoPago; //Iguales, Distintos

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFactura != null ? idFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CppFactura)) {
            return false;
        }
        CppFactura other = (CppFactura) object;
        if ((this.idFactura == null && other.idFactura != null) || (this.idFactura != null && !this.idFactura.equals(other.idFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CppFactura[ idFactura=" + idFactura + " ]";
    }

    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public CppProveedorCliente getCppProveedorCliente() {
        return cppProveedorCliente;
    }

    public void setCppProveedorCliente(CppProveedorCliente cppProveedorCliente) {
        this.cppProveedorCliente = cppProveedorCliente;
    }

    public Long getIdCntComprobante() {
        return idCntComprobante;
    }

    public void setIdCntComprobante(Long idCntComprobante) {
        this.idCntComprobante = idCntComprobante;
    }

    public Long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Long getNumeroPagos() {
        return numeroPagos;
    }

    public void setNumeroPagos(Long numeroPagos) {
        this.numeroPagos = numeroPagos;
    }

    public Long getDiasPagos() {
        return diasPagos;
    }

    public void setDiasPagos(Long diasPagos) {
        this.diasPagos = diasPagos;
    }

    public ParTipoPago getParTipoPago() {
        return parTipoPago;
    }

    public void setParTipoPago(ParTipoPago parTipoPago) {
        this.parTipoPago = parTipoPago;
    }

   
    
}
