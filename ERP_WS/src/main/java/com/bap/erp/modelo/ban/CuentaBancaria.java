package com.bap.erp.modelo.ban;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.par.ParBanco;
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
@Table(name = "CUENTA_BANCARIA")
public class CuentaBancaria extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta_bancaria")
    private Long idCuentaBancaria;

    @ManyToOne()
    @JoinColumn(name = "par_banco", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParBanco parBanco;

    @Column(name = "numero_cuenta", length = 50, nullable = false)
    private String numeroCuenta;

    @ManyToOne()
    @JoinColumn(name = "par_tipo_moneda", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoMoneda parTipoMoneda;

    @Column(name = "propietario_cuenta", length = 5, nullable = false)
    private String propietarioCuenta;

    @JoinColumn(name = "id_proveedor_cliente", referencedColumnName = "id_proveedor_cliente")
    @ManyToOne(optional = true)
    private CppProveedorCliente cppProveedorCliente;

    public Long getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(Long idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public ParBanco getParBanco() {
        return parBanco;
    }

    public void setParBanco(ParBanco parBanco) {
        this.parBanco = parBanco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public ParTipoMoneda getParTipoMoneda() {
        return parTipoMoneda;
    }

    public void setParTipoMoneda(ParTipoMoneda parTipoMoneda) {
        this.parTipoMoneda = parTipoMoneda;
    }

    public String getPropietarioCuenta() {
        return propietarioCuenta;
    }

    public void setPropietarioCuenta(String propietarioCuenta) {
        this.propietarioCuenta = propietarioCuenta;
    }

    public CppProveedorCliente getCppProveedorCliente() {
        return cppProveedorCliente;
    }

    public void setCppProveedorCliente(CppProveedorCliente cppProveedorCliente) {
        this.cppProveedorCliente = cppProveedorCliente;
    }

    
}
