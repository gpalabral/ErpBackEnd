package com.bap.erp.servicios.impl;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.modelo.ErpDetalleFactura;
import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.modelo.ErpNotaCreditoDebito;
import com.bap.erp.servicios.ErpDetalleFacturaService;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ErpDetalleFacturaServiceImpl implements ErpDetalleFacturaService {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ErpDetalleFacturaServiceImpl.class);

    IGenericDao<ErpDetalleFactura> dao;

    @Autowired
    public void setDao(IGenericDao<ErpDetalleFactura> daoToSet) {
        dao = daoToSet;
        dao.setClazz(ErpDetalleFactura.class);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ErpDetalleFactura persistCpcDetalleFactura(ErpDetalleFactura cpcDetalleFactura) throws Exception {
        try {
            cpcDetalleFactura.setIdDetalleFactura(null);
//            cpcDetalleFactura.setUsuarioAlta("TEST");
//            cpcDetalleFactura.setFechaAlta(new Date());
            dao.create(cpcDetalleFactura);
            return cpcDetalleFactura;
        } catch (Exception e) {
            throw e;
        }

    }

    public ErpDetalleFactura mergeCpcDetalleFactura(ErpDetalleFactura cpcDetalleFactura) throws Exception {
        try {
            cpcDetalleFactura.setFechaModificacion(new Date());
            cpcDetalleFactura.setUsuarioModificacion("TEST");
            dao.update(cpcDetalleFactura);
            return cpcDetalleFactura;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ErpDetalleFactura> getCpcDetalleFactura() throws Exception {
        try {
            List<ErpDetalleFactura> listaCpcDetalleFactura = dao.find(""
                    + "select b "
                    + "from ErpDetalleFactura b "
                    + "where b.fechaBaja is null "
                    + "order by b.fechaAlta ASC");
            if (!listaCpcDetalleFactura.isEmpty()) {
                return listaCpcDetalleFactura;
            }
            return Collections.EMPTY_LIST;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ErpDetalleFactura getCpcDetalleFacturaById(Long idDetalleFactura) throws Exception {
        try {
            ErpDetalleFactura cpcDetalleFactura = dao.findOne(idDetalleFactura);
            return cpcDetalleFactura;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<ErpDetalleFactura> getCpcDetalleFacturaByFacturaEmitida(ErpFactura cpcFacturaEmitida) throws Exception {

        List<ErpDetalleFactura> listaCpcDetalleFactura = dao.find(""
                + "select b "
                + "from ErpDetalleFactura b "
                + "where b.fechaBaja is null and b.erpFactura.idFactura=" + cpcFacturaEmitida.getIdFactura() + " "
                + "order by b.fechaAlta ASC");
        if (!listaCpcDetalleFactura.isEmpty()) {
            return listaCpcDetalleFactura;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<ErpDetalleFactura> getCpcDetalleFacturaByNotaCreditoDebito(ErpNotaCreditoDebito erpNotaCreditoDebito) throws Exception {
        ErpDetalleFactura b;
        List<ErpDetalleFactura> listaCpcDetalleFactura = dao.find(""
                + "select b "
                + "from ErpDetalleFactura b "
                + "where b.fechaBaja is null "
                + "and b.erpNotaCreditoDebito.idNotaCreditoDebito = " + erpNotaCreditoDebito.getIdNotaCreditoDebito() + " "
                + "order by b.fechaAlta ASC"
        );
        if (!listaCpcDetalleFactura.isEmpty()) {
            return listaCpcDetalleFactura;
        }
        return Collections.EMPTY_LIST;
    }   

}
