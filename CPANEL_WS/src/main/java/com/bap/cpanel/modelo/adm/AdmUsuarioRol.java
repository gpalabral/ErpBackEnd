/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.cpanel.modelo.adm;

import com.bap.cpanel.modelo.CPANEL;
import com.bap.erp.commons.entities.AbstractEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "ADM_USUARIO_ROL")
public class AdmUsuarioRol extends AbstractEntity implements Serializable{
    
    private static final long serialVersionUID = CPANEL.serialVersionIdCPanel;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_rol", nullable = false)
    private Long idUsuarioRol;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private AdmUsuario admUsuario;
    
    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", nullable = false)
    private AdmRol admRol;

    public Long getIdUsuarioRol() {
        return idUsuarioRol;
    }

    public void setIdUsuarioRol(Long idUsuarioRol) {
        this.idUsuarioRol = idUsuarioRol;
    }

    public AdmUsuario getAdmUsuario() {
        return admUsuario;
    }

    public void setAdmUsuario(AdmUsuario admUsuario) {
        this.admUsuario = admUsuario;
    }

    public AdmRol getAdmRol() {
        return admRol;
    }

    public void setAdmRol(AdmRol admRol) {
        this.admRol = admRol;
    }
    
}
