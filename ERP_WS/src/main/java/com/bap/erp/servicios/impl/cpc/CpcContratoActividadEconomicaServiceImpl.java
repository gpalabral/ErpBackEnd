package com.bap.erp.servicios.impl.cpc;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.modelo.cpc.CpcContratoActividadEconomica;
import com.bap.erp.servicios.cpc.CpcContratoActividadEconomicaService;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CpcContratoActividadEconomicaServiceImpl implements CpcContratoActividadEconomicaService {

    IGenericDao<CpcContratoActividadEconomica> dao;

    @Autowired
    public void setDao(IGenericDao<CpcContratoActividadEconomica> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CpcContratoActividadEconomica.class);
    }

    public CpcContratoActividadEconomica persistCpcContratoActividadEconomica(CpcContratoActividadEconomica cpcContratoActividadEconomica) {
        cpcContratoActividadEconomica.setIdContratoActividadEconomica(null);
        cpcContratoActividadEconomica.setUsuarioAlta("SYS");
        cpcContratoActividadEconomica.setFechaAlta(new Date());
        dao.create(cpcContratoActividadEconomica);
        return cpcContratoActividadEconomica;
    }

    public List<CpcContratoActividadEconomica> getCpcContratoActividadEconomicaByIdContrato(Long idContrato) throws Exception {
        try {            
            List<CpcContratoActividadEconomica> lista = dao.find(""
                    + "select j "
                    + "from CpcContratoActividadEconomica j "
                    + "where j.fechaBaja is null "
                    + "and j.cpcContrato.idContrato = "+idContrato+" ");
            if(!lista.isEmpty()){
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void deleteCpcContratoActividadEconomica(CpcContratoActividadEconomica cpcContratoActividadEconomica) throws Exception {
        try {
            dao.deleteById(cpcContratoActividadEconomica.getIdContratoActividadEconomica());
        } catch (Exception e) {
            throw e;
        }
    }
    
}
