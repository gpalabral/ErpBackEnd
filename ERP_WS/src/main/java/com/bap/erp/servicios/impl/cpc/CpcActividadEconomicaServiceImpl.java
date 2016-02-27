package com.bap.erp.servicios.impl.cpc;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.enums.EnumCaracteristicaEspecial;
import com.bap.erp.enums.EnumEstadoProceso;
import com.bap.erp.enums.EnumTipoDocumentoMercantil;
import com.bap.erp.modelo.cpc.CpcActividadEconomica;
import com.bap.erp.modelo.cpc.CpcContrato;
import com.bap.erp.modelo.cpc.CpcDosificacion;
import com.bap.erp.modelo.cpc.CpcPagoContrato;
import com.bap.erp.servicios.cpc.CpcActividadEconomicaService;
import com.bap.erp.servicios.cpc.CpcContratoService;
import com.bap.erp.servicios.cpc.CpcPagoContratoService;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CpcActividadEconomicaServiceImpl implements CpcActividadEconomicaService {

    IGenericDao<CpcActividadEconomica> dao;

    @Autowired
    private CpcPagoContratoService cpcPagoContratoService;
    
    @Autowired
    private CpcContratoService cpcContratoService;

    @Autowired
    public void setDao(IGenericDao<CpcActividadEconomica> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CpcActividadEconomica.class);
    }

    public CpcActividadEconomica persistCpcActividadEconomica(CpcActividadEconomica cpcActividadEconomica) {
        cpcActividadEconomica.setIdActividadEconomica(null);
        cpcActividadEconomica.setUsuarioAlta("SYS");
        cpcActividadEconomica.setFechaAlta(new Date());
        dao.create(cpcActividadEconomica);
        return cpcActividadEconomica;
    }

    public List<CpcActividadEconomica> getCpcActividadEconomica() throws Exception {
        try {

            List<CpcActividadEconomica> listaCpcActividadEconomica = dao.find(""
                    + "select j "
                    + "from CpcActividadEconomica j "
                    + "where j.fechaBaja is null "
                    + "order by fechaAlta ASC");
            if (!listaCpcActividadEconomica.isEmpty()) {
                return listaCpcActividadEconomica;
            }
            return Collections.EMPTY_LIST;

        } catch (Exception e) {
            throw e;
        }
    }

    public CpcActividadEconomica getCpcActividadEconomicaById(Long idActividadEconomica) throws Exception {
        try {
            return dao.findOne(idActividadEconomica);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcActividadEconomica> getActividadesEconomicasWithDosificacion() throws Exception {
        try {            
            List<CpcActividadEconomica> listaActividadEconomica = dao.find(""
                    + "select distinct j.cpcActividadEconomica "
                    + "from CpcDosificacion j "
                    + "where j.fechaBaja is null "
                    + "and j.parEstadoProceso.codigo = '" + EnumEstadoProceso.ACTIVA.getCodigo() + "' "
                    + "and j.parCaracteristicaEspecial.codigo = '"+EnumCaracteristicaEspecial.NINGUNA.getCodigo()+"' "
                    + "and j.parTipoDocumentoMercantil.codigo = '"+EnumTipoDocumentoMercantil.FACTURA.getCodigo()+"' ");
            if (!listaActividadEconomica.isEmpty()) {
                return listaActividadEconomica;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean verificaSiExisteCodigo(String codigo) throws Exception {
        try {
            List<CpcActividadEconomica> listaActividadEconomica = dao.find(""
                    + "select j "
                    + "from CpcActividadEconomica j "
                    + "where j.fechaBaja is null "
                    + "and j.codigo = '" + codigo + "'");
            return !listaActividadEconomica.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcActividadEconomica> getActividadEconomicaByIdPagoContrato(Long idPagoContrato) throws Exception {
        try {            
            CpcPagoContrato cpcPagoContrato = cpcPagoContratoService.getCpcPagoContratoById(idPagoContrato);            
            if (cpcPagoContrato != null) {
                List<CpcActividadEconomica> listaActividadEconomica = dao.find(""
                        + "select j.cpcActividadEconomica "
                        + "from CpcContratoActividadEconomica j "
                        + "where j.fechaBaja is null "
                        + "and j.cpcContrato.idContrato = " + cpcPagoContrato.getCpcContrato().getIdContrato() + "");
                if (!listaActividadEconomica.isEmpty()) {
                    return listaActividadEconomica;
                }
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcActividadEconomica> getActividadEconomicaByIdContrato(Long idContrato) throws Exception {
        try {            
            CpcContrato cpcContrato = cpcContratoService.getCpcContratoById(idContrato);            
            if (cpcContrato != null) {
                List<CpcActividadEconomica> listaActividadEconomica = dao.find(""
                        + "select j.cpcActividadEconomica "
                        + "from CpcContratoActividadEconomica j "
                        + "where j.fechaBaja is null "
                        + "and j.cpcContrato.idContrato = " + cpcContrato.getIdContrato() + "");
                if (!listaActividadEconomica.isEmpty()) {
                    return listaActividadEconomica;
                }
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }
}
