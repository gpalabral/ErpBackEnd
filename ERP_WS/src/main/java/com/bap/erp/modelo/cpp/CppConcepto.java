package com.bap.erp.modelo.cpp;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import com.bap.erp.modelo.par.ParPeriodo;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "CPP_CONCEPTO")
public class CppConcepto extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concepto", nullable = false)
    private Long idConcepto;
    
    @Column(name = "descripcion", length = 100)
    private String descripcion;    
    
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    @ManyToOne(optional = true)
    private CppGrupo cppGrupo;    
    
    @Column(name = "monto", precision = 18, scale = 5, nullable = true)
    private BigDecimal monto;
    
    @ManyToOne()    
    @JoinColumn(name = "par_periodo",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParPeriodo parPeriodo; //Mensual, Bimestral, Trimestral, Semestral, Anual    
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConcepto != null ? idConcepto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CppConcepto)) {
            return false;
        }
        CppConcepto other = (CppConcepto) object;
        if ((this.idConcepto == null && other.idConcepto != null) || (this.idConcepto != null && !this.idConcepto.equals(other.idConcepto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CppConcepto[ idConcepto=" + idConcepto + " ]";
    }

    public Long getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Long idConcepto) {
        this.idConcepto = idConcepto;
    }    

//    public Long getIdCntEntidad() {
//        return idCntEntidad;
//    }
//
//    public void setIdCntEntidad(Long idCntEntidad) {
//        this.idCntEntidad = idCntEntidad;
//    }

    public CppGrupo getCppGrupo() {
        return cppGrupo;
    }

    public void setCppGrupo(CppGrupo cppGrupo) {
        this.cppGrupo = cppGrupo;
    }

//    public ParTipoDocumentoMercantil getParTipoDocumentoMercantil() {
//        return parTipoDocumentoMercantil;
//    }
//
//    public void setParTipoDocumentoMercantil(ParTipoDocumentoMercantil parTipoDocumentoMercantil) {
//        this.parTipoDocumentoMercantil = parTipoDocumentoMercantil;
//    }
//
//    public String getTieneFactura() {
//        return tieneFactura;
//    }
//
//    public void setTieneFactura(String tieneFactura) {
//        this.tieneFactura = tieneFactura;
//    }    
//
//    public Long getNumeroCuotas() {
//        return numeroCuotas;
//    }
//
//    public void setNumeroCuotas(Long numeroCuotas) {
//        this.numeroCuotas = numeroCuotas;
//    }

    public ParPeriodo getParPeriodo() {
        return parPeriodo;
    }

    public void setParPeriodo(ParPeriodo parPeriodo) {
        this.parPeriodo = parPeriodo;
    }

//    public ParTipoMoneda getParTipoMoneda() {
//        return parTipoMoneda;
//    }
//
//    public void setParTipoMoneda(ParTipoMoneda parTipoMoneda) {
//        this.parTipoMoneda = parTipoMoneda;
//    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

//    public ParTipoRetencion getParRetencion() {
//        return parRetencion;
//    }
//
//    public void setParRetencion(ParTipoRetencion parRetencion) {
//        this.parRetencion = parRetencion;
//    }
//
//    public ParTipoRetencion getParGrossing() {
//        return parGrossing;
//    }
//
//    public void setParGrossing(ParTipoRetencion parGrossing) {
//        this.parGrossing = parGrossing;
//    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
          
}
