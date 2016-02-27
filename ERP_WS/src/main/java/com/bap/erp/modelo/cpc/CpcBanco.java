package com.bap.erp.modelo.cpc;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//AUTOR: Javier

@Entity
@Table(name = "CPC_BANCO")
public class CpcBanco extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_banco")
    private Long idBanco;
    
    @Column(name = "nombre", length = 70, nullable=false)
    private String nombre;
    
    @Column(name = "nro_cta", length = 50, nullable=false)
    private Long nroCta;
    
    @Column(name = "nit", length = 30, nullable=false)
    private String nit;

    public Long getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Long idBanco) {
        this.idBanco = idBanco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getNroCta() {
        return nroCta;
    }

    public void setNroCta(Long nroCta) {
        this.nroCta = nroCta;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    
}
