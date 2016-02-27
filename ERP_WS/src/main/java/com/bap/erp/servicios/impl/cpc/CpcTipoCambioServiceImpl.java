package com.bap.erp.servicios.impl.cpc;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.modelo.cpc.CpcTipoCambio;
import com.bap.erp.servicios.cpc.CpcTipoCambioService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CpcTipoCambioServiceImpl extends AbstractJpaDAO<CpcTipoCambio> implements CpcTipoCambioService {

    IGenericDao<CpcTipoCambio> dao;

    @Autowired
    public void setDao(IGenericDao<CpcTipoCambio> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CpcTipoCambio.class);
    }

    public CpcTipoCambio persistCpcTipoCambio(CpcTipoCambio cpcTipoCambio) throws Exception {
        try {
            cpcTipoCambio.setIdTipoCambio(null);
            cpcTipoCambio.setUsuarioAlta("TEST");
            cpcTipoCambio.setFechaAlta(new Date());
            dao.create(cpcTipoCambio);
            return cpcTipoCambio;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcTipoCambio> getCpcTipoCambio() throws Exception {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public CpcTipoCambio getCpcTipoCambioByIdTipoCambio(Long idTipoCambio) throws Exception {
        try {
            return dao.findOne(idTipoCambio);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public CpcTipoCambio mergeCpcTipoCambio(CpcTipoCambio cpcTipoCambio) throws Exception {
        try {
            cpcTipoCambio.setFechaModificacion(new Date());
            cpcTipoCambio.setUsuarioModificacion("TEST");
            dao.update(cpcTipoCambio);
            return cpcTipoCambio;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<CpcTipoCambio> getListCpcTipoCambio() throws Exception {
        try {
            List<CpcTipoCambio> lista = find(""
                    + "select a "
                    + "from CpcTipoCambio a "
                    + "where a.fechaBaja is null "
                    + "order by a.fecha DESC");
            if (!lista.isEmpty()) {
                return lista;
            }
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Boolean getCpcTipoCambioByFecha(Date fecha) throws Exception {
         try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
            String fechaFormato = formato.format(fecha);
            List<CpcTipoCambio> lista = find(""
                    + "select a "
                    + "from CpcTipoCambio a "
                    + "where a.fechaBaja is null "
                    + "and a.fecha = '" + fechaFormato +"'");
            return !lista.isEmpty();
        } catch (Exception e) {
            throw e;
        }
        
    }
}
