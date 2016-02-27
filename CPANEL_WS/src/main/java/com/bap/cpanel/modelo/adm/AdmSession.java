package com.bap.cpanel.modelo.adm;

import com.bap.cpanel.modelo.CPANEL;
import com.bap.erp.commons.entities.AbstractEntity;
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
@Table(name = "ADM_SESSION")
public class AdmSession extends AbstractEntity implements Serializable{
    
    private static final long serialVersionUID = CPANEL.serialVersionIdCPanel;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_session")
    private Long idSession;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private AdmUsuario admUsuario;    
    
    @Column(name = "token", length = 255, nullable=false)
    private String token;
    
    @Column(name = "fecha_expiracion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExpiracion;

    /**
     * @return the idSession
     */
    public Long getIdSession() {
        return idSession;
    }

    /**
     * @param idSession the idSession to set
     */
    public void setIdSession(Long idSession) {
        this.idSession = idSession;
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
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the fechaExpiracion
     */
    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    /**
     * @param fechaExpiracion the fechaExpiracion to set
     */
    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
    
}
