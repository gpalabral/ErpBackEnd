package com.bap.erp.modelo.cpc;

import com.bap.erp.modelo.cpp.*;
import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.modelo.ERP;
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
@Table(name = "CPC_CONTRATO_ACTIVIDAD_ECONOMICA")
public class CpcContratoActividadEconomica extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = ERP.serialVersionIdErp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato_actividad_economica", nullable = false)
    private Long idContratoActividadEconomica;
    
    @JoinColumn(name = "id_contrato", referencedColumnName = "id_contrato")
    @ManyToOne(optional = false)
    private CpcContrato cpcContrato;
       
    @JoinColumn(name = "id_actividad_economica", referencedColumnName = "id_actividad_economica")
    @ManyToOne(optional = false)
    private CpcActividadEconomica cpcActividadEconomica;

    public Long getIdContratoActividadEconomica() {
        return idContratoActividadEconomica;
    }

    public void setIdContratoActividadEconomica(Long idContratoActividadEconomica) {
        this.idContratoActividadEconomica = idContratoActividadEconomica;
    }

    public CpcContrato getCpcContrato() {
        return cpcContrato;
    }

    public void setCpcContrato(CpcContrato cpcContrato) {
        this.cpcContrato = cpcContrato;
    }

    public CpcActividadEconomica getCpcActividadEconomica() {
        return cpcActividadEconomica;
    }

    public void setCpcActividadEconomica(CpcActividadEconomica cpcActividadEconomica) {
        this.cpcActividadEconomica = cpcActividadEconomica;
    }

    

    
}
