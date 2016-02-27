package com.bap.erp.modelo.rh;

import com.bap.erp.modelo.*;
import com.bap.erp.commons.entities.AbstractEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RH_RC_IVA")
public class RhRcIva extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rc_iva", nullable = false)
    private Long idRcIva;
    
    @JoinColumn(name = "id_empleado_cargo", referencedColumnName = "id_empleado_cargo")
    @ManyToOne(optional = false)
    private RhEmpleadoCargo rhEmpleadoCargo;
    
    @JoinColumn(name = "id_periodo_gestion", referencedColumnName = "id_periodo_gestion")
    @ManyToOne(optional = false)
    private RhPeriodoGestion rhPeriodoGestion;

    @Column(name = "saldo_acumulado", precision = 18, scale = 2)
    private BigDecimal saldoAcumulado;

    @Column(name = "monto", precision = 18, scale = 2)
    private BigDecimal monto;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRcIva != null ? idRcIva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RhRcIva)) {
            return false;
        }
        RhRcIva other = (RhRcIva) object;
        if ((this.idRcIva == null && other.idRcIva != null) || (this.idRcIva != null && !this.idRcIva.equals(other.idRcIva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhRcIva[ idRcIva=" + idRcIva + " ]";
    }

    public Long getIdRcIva() {
        return idRcIva;
    }

    public void setIdRcIva(Long idRcIva) {
        this.idRcIva = idRcIva;
    }

    public RhEmpleadoCargo getRhEmpleadoCargo() {
        return rhEmpleadoCargo;
    }

    public void setRhEmpleadoCargo(RhEmpleadoCargo rhEmpleadoCargo) {
        this.rhEmpleadoCargo = rhEmpleadoCargo;
    }

    public RhPeriodoGestion getRhPeriodoGestion() {
        return rhPeriodoGestion;
    }

    public void setRhPeriodoGestion(RhPeriodoGestion rhPeriodoGestion) {
        this.rhPeriodoGestion = rhPeriodoGestion;
    }

    public BigDecimal getSaldoAcumulado() {
        return saldoAcumulado;
    }

    public void setSaldoAcumulado(BigDecimal saldoAcumulado) {
        this.saldoAcumulado = saldoAcumulado;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    
}
