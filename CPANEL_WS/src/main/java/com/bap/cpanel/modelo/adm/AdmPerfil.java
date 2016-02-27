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

@Entity
@Table(name = "ADM_PERFIL")
public class AdmPerfil extends AbstractEntity implements Serializable {
    
    private static final long serialVersionUID = CPANEL.serialVersionIdCPanel;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Long idPerfil;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private AdmUsuario admUsuario;
    
    @Column(name = "clave", length = 50, nullable = false)
    private String clave;
    
    @Column(name = "valor", length = 50, nullable = false)
    private String valor;
    
    @Column(name = "descripcion", length = 50, nullable = false)
    private String descripcion;

    /**
     * @return the idPerfil
     */
    public Long getIdPerfil() {
        return idPerfil;
    }

    /**
     * @param idPerfil the idPerfil to set
     */
    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    /**
     * @return the admUsuario
     */
    public AdmUsuario getAdmUsuario() {
        return admUsuario;
    }

    /**
     * @param admUsuario the admUsuario to set
     */
    public void setAdmUsuario(AdmUsuario admUsuario) {
        this.admUsuario = admUsuario;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
