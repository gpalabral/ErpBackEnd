/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.cpanel.servicios.cli;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.cpanel.modelo.cli.CliModuloEmpresa;
import java.util.List;

/**
 *
 * @author Javier
 */
public interface CliModuloEmpresaService {
    
    void setDao(IGenericDao<CliModuloEmpresa> daoToSet);
    
    CliModuloEmpresa persistCliModuloEmpresa(CliModuloEmpresa cliModuloEmpresa) throws Exception;
    
    List<CliModuloEmpresa> getCliModuloEmpresa() throws Exception;
    
    CliModuloEmpresa getCliModuloEmpresaByIdModuloEmpresa(Long idModuloEmpresa) throws Exception;
    
    CliModuloEmpresa mergeCliModuloEmpresa(CliModuloEmpresa cliModuloEmpresa) throws Exception;

}
