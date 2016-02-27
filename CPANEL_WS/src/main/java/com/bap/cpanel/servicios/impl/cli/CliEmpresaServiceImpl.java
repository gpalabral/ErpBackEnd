package com.bap.cpanel.servicios.impl.cli;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.cpanel.modelo.cli.CliEmpresa;
import com.bap.cpanel.servicios.cli.CliEmpresaService;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.commons.utils.LogUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CliEmpresaServiceImpl extends AbstractJpaDAO<CliEmpresa> implements CliEmpresaService {

    IGenericDao<CliEmpresa> dao;

    @Autowired
    public void setDao(IGenericDao<CliEmpresa> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CliEmpresa.class);
    }

    public CliEmpresa getCliEmpresaById(Long idEmpresa) {
        CliEmpresa cliEmpresa = dao.findOne(idEmpresa);
        return cliEmpresa;
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public CliEmpresa persistCliEmpresa(CliEmpresa cliEmpresa) throws Exception {
        try {
            cliEmpresa.setIdEmpresa(null);
            cliEmpresa.setFechaAlta(new Date());
            cliEmpresa.setUsuarioAlta("DEFAULT");
            dao.create(cliEmpresa);
            LogUtil.log("EXITO!!!");
            return cliEmpresa;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public CliEmpresa mergeCliEmpresa(CliEmpresa cliEmpresa) throws Exception {
        try {
            cliEmpresa.setFechaModificacion(new Date());
            cliEmpresa.setUsuarioModificacion("TEST");
            dao.update(cliEmpresa);
            return cliEmpresa;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<CliEmpresa> getCliEmpresa() throws Exception {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw e;
        }

    }

}
