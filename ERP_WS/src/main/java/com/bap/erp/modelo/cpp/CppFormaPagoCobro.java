package com.bap.erp.modelo.cpp;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import com.bap.erp.modelo.cpc.CpcBanco;
import com.bap.erp.modelo.par.ParEstado;
import com.bap.erp.modelo.par.ParFormaDePago;
import com.bap.erp.modelo.par.ParTipoMoneda;
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
@Table(name = "CPP_FORMA_PAGO_COBRO")
public class CppFormaPagoCobro extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forma_pago_cobro", nullable = false)
    private Long idFormaPagoCobro;

    @Column(name = "limite_credito", length = 20)
    private String limiteCredito;

    @Column(name = "dias_pago_credito", length = 20)
    private String diasPagoCredito;

    @ManyToOne()
    @JoinColumn(name = "par_forma_de_pago", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParFormaDePago parFormaDePago; //efectivo, cheque, etc..

//    @Column(name = "numero_cuenta", length = 30)
//    private String numeroCuenta;

//    @JoinColumn(name = "id_banco", referencedColumnName = "id_banco")
//    @ManyToOne(optional = false)
//    private CpcBanco cpcBanco;

    @Column(name = "proveedor_combustible", nullable = true)
    private Boolean proveedorCombustible;

    @ManyToOne()
    @JoinColumn(name = "par_estado", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEstado parEstado; //Vigente, No Vigente

//    @Column(name = "beneficiario", length = 50)
//    private String beneficiario;

    @JoinColumn(name = "id_proveedor_cliente", referencedColumnName = "id_proveedor_cliente")
    @ManyToOne(optional = false)
    private CppProveedorCliente cppProveedorCliente;

//    @ManyToOne()
//    @JoinColumn(name = "par_tipo_moneda", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
//    private ParTipoMoneda parTipoMoneda; //Bolivianos, Dolares

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormaPagoCobro != null ? idFormaPagoCobro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CppFormaPagoCobro)) {
            return false;
        }
        CppFormaPagoCobro other = (CppFormaPagoCobro) object;
        if ((this.idFormaPagoCobro == null && other.idFormaPagoCobro != null) || (this.idFormaPagoCobro != null && !this.idFormaPagoCobro.equals(other.idFormaPagoCobro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CppFormaPagoCobro[ idFormaPagoCobro=" + idFormaPagoCobro + " ]";
    }

    public ParEstado getParEstado() {
        return parEstado;
    }

    public void setParEstado(ParEstado parEstado) {
        this.parEstado = parEstado;
    }

    public Long getIdFormaPagoCobro() {
        return idFormaPagoCobro;
    }

    public void setIdFormaPagoCobro(Long idFormaPagoCobro) {
        this.idFormaPagoCobro = idFormaPagoCobro;
    }

    public String getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(String limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getDiasPagoCredito() {
        return diasPagoCredito;
    }

    public void setDiasPagoCredito(String diasPagoCredito) {
        this.diasPagoCredito = diasPagoCredito;
    }

    public ParFormaDePago getParFormaDePago() {
        return parFormaDePago;
    }

    public void setParFormaDePago(ParFormaDePago parFormaDePago) {
        this.parFormaDePago = parFormaDePago;
    }

    public CppProveedorCliente getCppProveedorCliente() {
        return cppProveedorCliente;
    }

    public void setCppProveedorCliente(CppProveedorCliente cppProveedorCliente) {
        this.cppProveedorCliente = cppProveedorCliente;
    }

    public Boolean getProveedorCombustible() {
        return proveedorCombustible;
    }

    public void setProveedorCombustible(Boolean proveedorCombustible) {
        this.proveedorCombustible = proveedorCombustible;
    }

}
