package com.bap.erp.servicios.impl;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.modelo.ErpDepartamento;
import com.bap.erp.servicios.ErpDepartamentoService;
import com.bap.erp.ws.ImportWS;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErpDepartamentoServiceImpl implements ErpDepartamentoService {

    IGenericDao<ErpDepartamento> dao;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ImportWS.class);

    @Autowired
    public void setDao(IGenericDao<ErpDepartamento> daoToSet) {
        dao = daoToSet;
        dao.setClazz(ErpDepartamento.class);
    }

    public List<ErpDepartamento> listErpDepartamentoByEstado(String estado) throws Exception {
        try {
            List<ErpDepartamento> lista = dao.find(""
                    + "select j "
                    + "from ErpDepartamento j "
                    + "where j.fechaBaja is null "
                    + "and j.parEstado.codigo = '" + estado + "' ");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

}
