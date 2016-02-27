 package com.bap.cpanel.modelo.adm;

import com.bap.cpanel.modelo.CPANEL;
import com.bap.cpanel.modelo.par.ParEstadoUsuario;
import com.bap.erp.commons.entities.AbstractEntity;
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
@Table(name = "ADM_USUARIO")
public class AdmUsuario extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = CPANEL.serialVersionIdCPanel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "login", length = 50, nullable = false)
    private String login;

    @Column(name = "contrasena", length = 150, nullable = false)
    private String contrasena;

    @ManyToOne()    
    @JoinColumn(name = "par_estado_usuario",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParEstadoUsuario parEstadoUsuario; //Vigente(VIG), No vigente (NVIG)

    @Column(name = "correo_electronico", length = 50)
    private String correoElectronico;

    @Column(name = "fecha_expiracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExpiracion;

    @ManyToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona", nullable = false)
    private AdmPersona admPersona;

    public Long getIdUsuario() {
        return idUsuario;
    }
    
    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ParEstadoUsuario getParEstadoUsuario() {
        return parEstadoUsuario;
    }

    public void setParEstadoUsuario(ParEstadoUsuario parEstadoUsuario) {
        this.parEstadoUsuario = parEstadoUsuario;
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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

    public AdmPersona getAdmPersona() {
        return admPersona;
    }

    public void setAdmPersona(AdmPersona admPersona) {
        this.admPersona = admPersona;
    }

   

        
    
    
    
    
}
