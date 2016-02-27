/*/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.cpanel.modelo.adm;

import com.bap.cpanel.modelo.CPANEL;
import com.bap.cpanel.modelo.par.ParEstadoPermiso;
import com.bap.cpanel.modelo.par.ParTipo;
import com.bap.erp.commons.entities.AbstractEntity;
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

/**
 *
 * @author javier
 */
@Entity
@Table(name = "ADM_PERMISO")
public class AdmPermiso extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = CPANEL.serialVersionIdCPanel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso", nullable = false)
    private Long idPermiso;

    @Column(name = "detalle", length = 150, nullable = false)
    private String detalle;

    @Column(name = "codigo", length = 150, nullable = false)
    private String codigo;

    @ManyToOne()
    @JoinColumn(name = "par_estado_permiso", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEstadoPermiso parEstadoPermiso;

    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", nullable = false)
    private AdmRol admRol;

    @ManyToOne()
    @JoinColumn(name = "par_tipo", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipo parTipo;

    @Column(name = "id_padre", length = 50)
    private Long idPadre;
    
    @ManyToOne
    @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo", nullable = false)
    private AdmModulo admModulo;

    public Long getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Long idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public ParEstadoPermiso getParEstadoPermiso() {
        return parEstadoPermiso;
    }

    public void setParEstadoPermiso(ParEstadoPermiso parEstadoPermiso) {
        this.parEstadoPermiso = parEstadoPermiso;
    }

    public AdmRol getAdmRol() {
        return admRol;
    }

    public void setAdmRol(AdmRol admRol) {
        this.admRol = admRol;
    }

    public ParTipo getParTipo() {
        return parTipo;
    }

    public void setParTipo(ParTipo parTipo) {
        this.parTipo = parTipo;
    }

    public Long getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Long idPadre) {
        this.idPadre = idPadre;
    }

    public AdmModulo getAdmModulo() {
        return admModulo;
    }

    public void setAdmModulo(AdmModulo admModulo) {
        this.admModulo = admModulo;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    
}
