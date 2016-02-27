 package com.bap.cpanel.servicios.impl.cli;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.cpanel.modelo.adm.AdmUsuarioRol;
import com.bap.cpanel.modelo.cli.CliModuloEmpresa;
import com.bap.cpanel.servicios.cli.CliModuloEmpresaService;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CliModuloEmpresaServiceImp extends AbstractJpaDAO<AdmUsuarioRol> implements CliModuloEmpresaService {

     IGenericDao<CliModuloEmpresa> dao;

    @Autowired
    public void setDao(IGenericDao<CliModuloEmpresa> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CliModuloEmpresa.class);
    }
    
    public CliModuloEmpresa persistCliModuloEmpresa(CliModuloEmpresa cliModuloEmpresa) throws Exception {
        try {
            System.out.println("test ");
            cliModuloEmpresa.setIdModuloEmpresa(null);
            cliModuloEmpresa.setUsuarioAlta("TEST");
            cliModuloEmpresa.setFechaAlta(new Date());
            dao.create(cliModuloEmpresa);
            return cliModuloEmpresa;
        } catch (Exception e) {
            throw e;
        }

    }

     public List<CliModuloEmpresa> getCliModuloEmpresa() throws Exception {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw e;
        }

    }

    public CliModuloEmpresa getCliModuloEmpresaByIdModuloEmpresa(Long idModuloEmpresa) throws Exception {
        try {
            return dao.findOne(idModuloEmpresa);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public CliModuloEmpresa mergeCliModuloEmpresa(CliModuloEmpresa cliModuloEmpresa) throws Exception {
        try {
            cliModuloEmpresa.setFechaModificacion(new Date());
            cliModuloEmpresa.setUsuarioModificacion("TEST");
            dao.update(cliModuloEmpresa);
            return cliModuloEmpresa;
        } catch (Exception e) {
            throw e;
        }
    }        
}