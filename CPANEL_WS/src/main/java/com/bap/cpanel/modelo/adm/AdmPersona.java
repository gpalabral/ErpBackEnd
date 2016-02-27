package com.bap.cpanel.modelo.adm;

import com.bap.cpanel.modelo.CPANEL;
import com.bap.cpanel.modelo.par.ParTipoDocumento;
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

@Entity
@Table(name = "ADM_PERSONA")
public class AdmPersona extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = CPANEL.serialVersionIdCPanel;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long idPersona;
    
    @Column(name = "nombre", length = 50)
    private String nombre;
    
    @Column(name = "ap_paterno", length = 50,nullable=false)
    private String apPaterno;
    
    @Column(name = "ap_materno", length = 50)
    private String apMaterno;
    
    @Column(name = "nombre_completo", length = 50)//POR BORRAR
    private String nombreCompleto;
    
    @Column(name = "nro_documento", length = 15)
    private String nroDocumento;
    
    @ManyToOne()    
    @JoinColumn(name = "par_tipo_documento",referencedColumnName = "codigo",nullable=true,foreignKey = @ForeignKey(name="none"))
    private ParTipoDocumento parTipoDocumento;

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public ParTipoDocumento getParTipoDocumento() {
        return parTipoDocumento;
    }

    public void setParTipoDocumento(ParTipoDocumento parTipoDocumento) {
        this.parTipoDocumento = parTipoDocumento;
    }

    public void setId_Persona(long l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombre+" "+apPaterno+" "+apMaterno;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
}
