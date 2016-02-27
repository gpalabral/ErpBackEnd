package com.bap.erp.modelo.rh;

import com.bap.erp.modelo.*;
import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.par.ParEstado;
import com.bap.erp.modelo.par.ParEstadoPeriodoGestion;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "RH_PERIODO_GESTION")
public class RhPeriodoGestion extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_periodo_gestion", nullable = false)
    private Long idPeriodoGestion;

    @ManyToOne()
    @JoinColumn(name = "par_estado_periodo_gestion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEstadoPeriodoGestion parEstadoPeriodoGestion;

    @Column(name = "periodo")
    private int periodo;
    
    @Column(name = "gestion")
    private int gestion;
    
    @Column(name = "inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    
    @Column(name = "fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fin;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodoGestion != null ? idPeriodoGestion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RhPeriodoGestion)) {
            return false;
        }
        RhPeriodoGestion other = (RhPeriodoGestion) object;
        if ((this.idPeriodoGestion == null && other.idPeriodoGestion != null) || (this.idPeriodoGestion != null && !this.idPeriodoGestion.equals(other.idPeriodoGestion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhPeriodoGestion[ idPeriodoGestion=" + idPeriodoGestion + ":  "+this.periodo+" / "+this.gestion+"]";
    }

    public Long getIdPeriodoGestion() {
        return idPeriodoGestion;
    }

    public void setIdPeriodoGestion(Long idPeriodoGestion) {
        this.idPeriodoGestion = idPeriodoGestion;
    }

    public ParEstadoPeriodoGestion getParEstadoPeriodoGestion() {
        return parEstadoPeriodoGestion;
    }

    public void setParEstadoPeriodoGestion(ParEstadoPeriodoGestion parEstadoPeriodoGestion) {
        this.parEstadoPeriodoGestion = parEstadoPeriodoGestion;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }
    
}