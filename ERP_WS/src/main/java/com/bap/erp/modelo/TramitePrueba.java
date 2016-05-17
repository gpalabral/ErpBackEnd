package com.bap.erp.modelo;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.par.ParEstado;
import com.bap.erp.modelo.par.ParTipoAplicante;
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
@Table(name = "TramitePrueba")
public class TramitePrueba extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTramite", nullable = false)
    private Long idTramite;
    @Column(name = "IdExpediente")
    private Long idExpediente;
    @Column(name = "IdRegional")
    private Long idRegional;

    @Column(name = "Observacion")
    private String observacion;

    @Column(name = "Tramite")
    private String tramite;
    
    @Column(name = "TipoTramite")
    private String tipoTramite;
    @Column(name = "EtapaTramite")
    private String etapaTramite;
    @Column(name = "EstadoProceso")
    private String estadoProceso;
    
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
     @Column(name = "FechaIngreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
     
    @Column(name = "TienePrioridad")
    private Boolean tienePrioridad;
    
    @Column(name = "TramiteUnipersonal")
    private Boolean tramiteUnipersonal;
    
    @Column(name = "Vigente")
    private Boolean vigente;
    
    @Column(name = "Usuario")
    private Long usuario;

    @Column(name = "FechaUltimaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimaModificacion;
   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTramite != null ? idTramite.hashCode() : 0);
        return hash;
    }

    public Long getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(Long idTramite) {
        this.idTramite = idTramite;
    }

    public Long getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Long idExpediente) {
        this.idExpediente = idExpediente;
    }

    public Long getIdRegional() {
        return idRegional;
    }

    public void setIdRegional(Long idRegional) {
        this.idRegional = idRegional;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public String getEtapaTramite() {
        return etapaTramite;
    }

    public void setEtapaTramite(String etapaTramite) {
        this.etapaTramite = etapaTramite;
    }

    public String getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(String estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Boolean getTienePrioridad() {
        return tienePrioridad;
    }

    public void setTienePrioridad(Boolean tienePrioridad) {
        this.tienePrioridad = tienePrioridad;
    }

    public Boolean getTramiteUnipersonal() {
        return tramiteUnipersonal;
    }

    public void setTramiteUnipersonal(Boolean tramiteUnipersonal) {
        this.tramiteUnipersonal = tramiteUnipersonal;
    }

    public Boolean getVigente() {
        return vigente;
    }

    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    
        
}
