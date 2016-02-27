package com.bap.erp.servicios.impl.cpc;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.modelo.cpc.CpcContratoItem;
import com.bap.erp.servicios.cpc.CpcContratoItemService;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CpcContratoItemServiceImpl extends AbstractJpaDAO<CpcContratoItem> implements CpcContratoItemService {

    IGenericDao<CpcContratoItem> dao;

    @Autowired
    public void setDao(IGenericDao<CpcContratoItem> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CpcContratoItem.class);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public CpcContratoItem persistCpcContratoItem(CpcContratoItem cpcContratoItem) throws Exception {
        try {
            cpcContratoItem.setIdContratoItem(null);
            cpcContratoItem.setUsuarioAlta("TEST");
            cpcContratoItem.setFechaAlta(new Date());
            dao.create(cpcContratoItem);
            return cpcContratoItem;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcContratoItem> getCpcContratoItem() throws Exception {
        try {

            List<CpcContratoItem> listaCpcContratoItem = find(""
                    + "select b "
                    + "from CpcContratoItem b "
                    + "where b.fechaBaja is null "
                    + "order by fechaAlta ASC");
            if (!listaCpcContratoItem.isEmpty()) {
                return listaCpcContratoItem;
            }
            return Collections.EMPTY_LIST;

        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public CpcContratoItem mergeCpcContratoItem(CpcContratoItem cpcContratoItem) throws Exception {
        try {
            cpcContratoItem.setFechaModificacion(new Date());
            cpcContratoItem.setUsuarioModificacion("TEST");
            dao.update(cpcContratoItem);
            return cpcContratoItem;
        } catch (Exception e) {
            throw e;
        }
    }

    public CpcContratoItem getCpcContratoItemById(Long idContratoItem) throws Exception {
        try {
            return dao.findOne(idContratoItem);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<CpcContratoItem> getCpcContratoItemByIdContrato(Long idContrato) throws Exception {
        try {
            List<CpcContratoItem> listaCpcContratoItem = find(""
                    + "select a "
                    + "from CpcContratoItem a "
                    + "where a.fechaBaja is null "
                    + "and cpcContrato.idContrato =" +idContrato+ " "
                    + "order by a.fechaAlta ASC");
            if (!listaCpcContratoItem.isEmpty()) {
                return listaCpcContratoItem;
            }
            return Collections.EMPTY_LIST;

        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void deleteCpcContratoItem(CpcContratoItem cpcContratoItem) throws Exception {
        try {
            dao.deleteById(cpcContratoItem.getIdContratoItem());
        } catch (Exception e) {
            throw e;
        }
    }
}
