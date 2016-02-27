package com.bap.erp.servicios.impl.rh;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.modelo.rh.RhSeccion;
import com.bap.erp.servicios.rh.RhSeccionService;
import com.bap.erp.ws.ImportWS;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RhSeccionServiceImpl implements RhSeccionService {

    IGenericDao<RhSeccion> dao;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ImportWS.class);

    @Autowired
    public void setDao(IGenericDao<RhSeccion> daoToSet) {
        dao = daoToSet;
        dao.setClazz(RhSeccion.class);
    }

    public List<RhSeccion> listaRhSeccionPorIdDepartamento(Long idDepartamento) throws Exception {
        try {
            List<RhSeccion> listaRhSeccionPorIdDepartamento = dao.find(""
                    + "select h "
                    + "from RhSeccion h "
                    + "where h.fechaBaja is null and h.erpDepartamento.idDepartamento=" + idDepartamento);
            if (!listaRhSeccionPorIdDepartamento.isEmpty()) {
                return listaRhSeccionPorIdDepartamento;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

}
