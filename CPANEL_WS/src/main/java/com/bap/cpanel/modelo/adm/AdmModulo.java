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
@Table(name = "ADM_MODULO")
public class AdmModulo extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = CPANEL.serialVersionIdCPanel;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modulo")
    private Long idModulo;
    
    @Column(name = "nombre", length = 50)
    private String nombre;

    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
