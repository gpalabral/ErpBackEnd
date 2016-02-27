package com.bap.erp.servicios.impl.rh;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.utils.ObjectUtils;
import com.bap.erp.modelo.rh.RhCargo;
import com.bap.erp.servicios.rh.RhCargoService;
import com.bap.erp.ws.ImportWS;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RhCargoServiceImpl implements RhCargoService {

    IGenericDao<RhCargo> dao;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ImportWS.class);

    @Autowired
    public void setDao(IGenericDao<RhCargo> daoToSet) {
        dao = daoToSet;
        dao.setClazz(RhCargo.class);
    }

    public RhCargo persistRhCargo(RhCargo rhCargo) throws Exception {
        try {
            rhCargo.setIdCargo(null);
            if(rhCargo.getRhSeccion().getIdSeccion() == null){
                rhCargo.setRhSeccion(null);
            }
            ObjectUtils.printObjectState(rhCargo);
//            rhCargo.setFechaAlta(new Date());
//            rhCargo.setUsuarioAlta("TEST");
            dao.create(rhCargo);
            return rhCargo;
        } catch (Exception e) {
            throw e;
        }
    }

    public RhCargo mergeRhCargo(RhCargo rhCargo) throws Exception {
        try {
            rhCargo.setFechaAlta(new Date());
            rhCargo.setUsuarioAlta("TEST");
            rhCargo.setFechaModificacion(new Date());
            rhCargo.setUsuarioModificacion("TEST");
            dao.update(rhCargo);
            return rhCargo;
        } catch (Exception e) {
            throw e;
        }
    }

    public void removeRhCargo(Long idCargo) throws Exception {
        try {
            RhCargo rhCargo = dao.findOne(idCargo);
            rhCargo.setFechaBaja(new Date());
            rhCargo.setUsuarioBaja("SYS");
            dao.update(rhCargo);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<RhCargo> listaRhCargo() throws Exception {
        try {
            List<RhCargo> listaRhCargo = dao.find(""
                    + "select h "
                    + "from RhCargo h "
                    + "where h.fechaBaja is null");
            if (!listaRhCargo.isEmpty()) {
                return listaRhCargo;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    //Autor: Henrry Guzmán
    public Boolean verificaExistenciaCodigoRhCargo(String codigo) throws Exception {
        try {
            List<RhCargo> listaRhCargo = dao.find(""
                    + "select h "
                    + "from RhCargo h "
                    + "where h.fechaBaja is null and h.codigo='" + codigo + "'");
            return listaRhCargo.size() > 0;
        } catch (Exception e) {
            throw e;
        }
    }

}
