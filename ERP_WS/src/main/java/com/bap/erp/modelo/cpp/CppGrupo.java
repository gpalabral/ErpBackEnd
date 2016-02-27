package com.bap.erp.modelo.cpp;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import com.bap.erp.modelo.par.ParRecurrencia;

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
@Table(name = "CPP_GRUPO")
public class CppGrupo extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo", nullable = false)
    private Long idGrupo;
    
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @ManyToOne()    
    @JoinColumn(name = "par_recurrencia", referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParRecurrencia parRecurrencia; //periodica, no_periodica
    
//    @Column(name = "id_cnt_entidad_cta_x_pagar", length = 50, nullable = false)
//    private Long idCntEntidadCtaXPagar;
//    
//    @Column(name = "id_cnt_entidad_doc_x_pagar", length = 50, nullable = false)
//    private Long idCntEntidadDocXPagar;
//
//    @Column(name = "id_cnt_entidad_anticipo", length = 50, nullable = false)
//    private Long idCntEntidadAnticipo;
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupo != null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CppGrupo)) {
            return false;
        }
        CppGrupo other = (CppGrupo) object;
        if ((this.idGrupo == null && other.idGrupo != null) || (this.idGrupo != null && !this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CppGrupo[ idGrupo=" + idGrupo + " ]";
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ParRecurrencia getParRecurrencia() {
        return parRecurrencia;
    }

    public void setParRecurrencia(ParRecurrencia parRecurrencia) {
        this.parRecurrencia = parRecurrencia;
    }

//    /**
//     * @return the idCntEntidadCtaXPagar
//     */
//    public Long getIdCntEntidadCtaXPagar() {
//        return idCntEntidadCtaXPagar;
//    }
//
//    /**
//     * @param idCntEntidadCtaXPagar the idCntEntidadCtaXPagar to set
//     */
//    public void setIdCntEntidadCtaXPagar(Long idCntEntidadCtaXPagar) {
//        this.idCntEntidadCtaXPagar = idCntEntidadCtaXPagar;
//    }
//
//    /**
//     * @return the idCntEntidadDocXPagar
//     */
//    public Long getIdCntEntidadDocXPagar() {
//        return idCntEntidadDocXPagar;
//    }
//
//    /**
//     * @param idCntEntidadDocXPagar the idCntEntidadDocXPagar to set
//     */
//    public void setIdCntEntidadDocXPagar(Long idCntEntidadDocXPagar) {
//        this.idCntEntidadDocXPagar = idCntEntidadDocXPagar;
//    }
//
//    /**
//     * @return the idCntEntidadAnticipo
//     */
//    public Long getIdCntEntidadAnticipo() {
//        return idCntEntidadAnticipo;
//    }
//
//    /**
//     * @param idCntEntidadAnticipo the idCntEntidadAnticipo to set
//     */
//    public void setIdCntEntidadAnticipo(Long idCntEntidadAnticipo) {
//        this.idCntEntidadAnticipo = idCntEntidadAnticipo;
//    }
    
    

}
