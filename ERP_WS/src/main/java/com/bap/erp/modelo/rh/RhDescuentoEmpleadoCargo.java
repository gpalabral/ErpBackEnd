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
@Table(name = "RH_DESCUENTO_EMPLEADO_CARGO")
public class RhDescuentoEmpleadoCargo extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descuento_empleado_cargo", nullable = false)
    private Long idDescuentoEmpleadoCargo;

    @JoinColumn(name = "id_descuento", referencedColumnName = "id_descuento")
    @ManyToOne(optional = false)
    private RhDescuento rhDescuento;

    @JoinColumn(name = "id_empleado_cargo", referencedColumnName = "id_empleado_cargo")
    @ManyToOne(optional = false)
    private RhEmpleadoCargo rhEmpleadoCargo;

    @Column(name = "monto_descuento", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoDescuento;
    
    @JoinColumn(name = "id_periodo_gestion", referencedColumnName = "id_periodo_gestion")
    @ManyToOne(optional = false)
    private RhPeriodoGestion rhPeriodoGestion;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDescuentoEmpleadoCargo != null ? idDescuentoEmpleadoCargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RhDescuentoEmpleadoCargo)) {
            return false;
        }
        RhDescuentoEmpleadoCargo other = (RhDescuentoEmpleadoCargo) object;
        if ((this.idDescuentoEmpleadoCargo == null && other.idDescuentoEmpleadoCargo != null) || (this.idDescuentoEmpleadoCargo != null && !this.idDescuentoEmpleadoCargo.equals(other.idDescuentoEmpleadoCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhDescuentoEmpleadoCargo[ idDescuentoEmpleadoCargo=" + idDescuentoEmpleadoCargo + " ]";
    }

    public Long getIdDescuentoEmpleadoCargo() {
        return idDescuentoEmpleadoCargo;
    }

    public void setIdDescuentoEmpleadoCargo(Long idDescuentoEmpleadoCargo) {
        this.idDescuentoEmpleadoCargo = idDescuentoEmpleadoCargo;
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

    public RhDescuento getRhDescuento() {
        return rhDescuento;
    }

    public void setRhDescuento(RhDescuento rhDescuento) {
        this.rhDescuento = rhDescuento;
    }

    public BigDecimal getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(BigDecimal montoDescuento) {
        this.montoDescuento = montoDescuento;
    }
        
}
