/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.cpanel.modelo.cli;

import com.bap.cpanel.modelo.adm.*;
import com.bap.cpanel.modelo.cli.*;
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
@Table(name = "CLI_MODULO_EMPRESA")
public class CliModuloEmpresa extends AbstractEntity implements Serializable{
    
    private static final long serialVersionUID = CPANEL.serialVersionIdCPanel;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modulo_empresa", nullable = false)
    private Long idModuloEmpresa;
    
    @ManyToOne
    @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo", nullable = false)
    private AdmModulo admModulo;
    
    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false)
    private CliEmpresa cliEmpresa;

    public Long getIdModuloEmpresa() {
        return idModuloEmpresa;
    }

    public void setIdModuloEmpresa(Long idModuloEmpresa) {
        this.idModuloEmpresa = idModuloEmpresa;
    }

    public AdmModulo getAdmModulo() {
        return admModulo;
    }

    public void setAdmModulo(AdmModulo admModulo) {
        this.admModulo = admModulo;
    }

    

    public CliEmpresa getCliEmpresa() {
        return cliEmpresa;
    }

    public void setCliEmpresa(CliEmpresa cliEmpresa) {
        this.cliEmpresa = cliEmpresa;
    }

    
}
