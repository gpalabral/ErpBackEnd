package com.bap.erp.servicios.impl.cpc;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.modelo.cpc.CpcBanco;
import com.bap.erp.servicios.cpc.CpcBancoService;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CpcBancoServiceImpl extends AbstractJpaDAO<CpcBanco> implements CpcBancoService {

    IGenericDao<CpcBanco> dao;

    @Autowired
    public void setDao(IGenericDao<CpcBanco> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CpcBanco.class);
    }

    public CpcBanco persistCpcBanco(CpcBanco cpcBanco) {
        cpcBanco.setIdBanco(null);
        cpcBanco.setUsuarioAlta("TEST");
        cpcBanco.setFechaAlta(new Date());
        dao.create(cpcBanco);
        return cpcBanco;
    }

    public List<CpcBanco> getCpcBanco() throws Exception {
        try {

            List<CpcBanco> listaCpcBanco = find(""
                    + "select b "
                    + "from CpcBanco b "
                    + "where b.fechaBaja is null "
                    + "order by fechaAlta ASC");
            if (!listaCpcBanco.isEmpty()) {
                return listaCpcBanco;
            }
            return Collections.EMPTY_LIST;

        } catch (Exception e) {
            throw e;
        }
    }

    public CpcBanco getCpcBancoById(Long idBanco) throws Exception {
        try {
            return dao.findOne(idBanco);
        } catch (Exception e) {
            throw e;
        }
    }
}
