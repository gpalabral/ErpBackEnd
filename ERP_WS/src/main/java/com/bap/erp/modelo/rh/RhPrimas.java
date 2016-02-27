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
@Table(name = "RH_PRIMAS")
public class RhPrimas extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_primas", nullable = false)
    private Long idPrimas;

    @JoinColumn(name = "id_empleado_cargo", referencedColumnName = "id_empleado_cargo")
    @ManyToOne(optional = false)
    private RhEmpleadoCargo rhEmpleadoCargo;

    @JoinColumn(name = "id_periodo_gestion", referencedColumnName = "id_periodo_gestion")
    @ManyToOne(optional = false)
    private RhPeriodoGestion rhPeriodoGestion;

    @Column(name = "monto_prima", precision = 18, scale = 2)
    private BigDecimal montoPrima;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrimas != null ? idPrimas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RhPrimas)) {
            return false;
        }
        RhPrimas other = (RhPrimas) object;
        if ((this.idPrimas == null && other.idPrimas != null) || (this.idPrimas != null && !this.idPrimas.equals(other.idPrimas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhPrimas[ idPrimas=" + idPrimas + " ]";
    }

    public Long getIdPrimas() {
        return idPrimas;
    }

    public void setIdPrimas(Long idPrimas) {
        this.idPrimas = idPrimas;
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

    public BigDecimal getMontoPrima() {
        return montoPrima;
    }

    public void setMontoPrima(BigDecimal montoPrima) {
        this.montoPrima = montoPrima;
    }

}
