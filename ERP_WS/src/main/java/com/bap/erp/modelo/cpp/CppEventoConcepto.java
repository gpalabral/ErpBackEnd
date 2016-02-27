package com.bap.erp.modelo.cpp;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CPP_EVENTO_CONCEPTO")
public class CppEventoConcepto extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento_concepto", nullable = false)
    private Long idEventoConcepto;        
    
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    @ManyToOne(optional = false)
    private CppGrupo cppGrupo;

    @Column(name = "monto", length = 30)
    private String monto;
    
    @Column(name = "documento", length = 50)
    private String documento;
    
    @Column(name = "numero_cuota", length = 50)
    private Long numeroCuota;
    
    @Column(name = "fecha_evento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEvento;            
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEventoConcepto != null ? idEventoConcepto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CppEventoConcepto)) {
            return false;
        }
        CppEventoConcepto other = (CppEventoConcepto) object;
        if ((this.idEventoConcepto == null && other.idEventoConcepto != null) || (this.idEventoConcepto != null && !this.idEventoConcepto.equals(other.idEventoConcepto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CppEventoConcepto[ idEventoConcepto=" + idEventoConcepto + " ]";
    }

    public Long getIdEventoConcepto() {
        return idEventoConcepto;
    }

    public void setIdEventoConcepto(Long idEventoConcepto) {
        this.idEventoConcepto = idEventoConcepto;
    }

    public CppGrupo getCppGrupo() {
        return cppGrupo;
    }

    public void setCppGrupo(CppGrupo cppGrupo) {
        this.cppGrupo = cppGrupo;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Long getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Long numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }
    
    
}
