package com.bap.erp.servicios.impl.rh;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.modelo.rh.RhCriterioDeIngreso;
import com.bap.erp.modelo.rh.RhCriterioDeIngresoEmpleadoCargo;
import com.bap.erp.modelo.rh.RhDescuentoEmpleadoCargo;
import com.bap.erp.servicios.rh.RhCriterioDeIngresoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhCriterioDeIngresoService;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RhCriterioDeIngresoServiceImpl implements RhCriterioDeIngresoService {

    IGenericDao<RhCriterioDeIngreso> dao;
    
    @Autowired
    public RhCriterioDeIngresoEmpleadoCargoService rhCriterioDeIngresoEmpleadoCargoService;


    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhCriterioDeIngresoServiceImpl.class);

    @Autowired
    public void setDao(IGenericDao<RhCriterioDeIngreso> daoToSet) {
        dao = daoToSet;
        dao.setClazz(RhCriterioDeIngreso.class);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public RhCriterioDeIngreso persistRhCriterioDeIngreso(RhCriterioDeIngreso rhCriterioDeIngreso) throws Exception {
        try {
            rhCriterioDeIngreso.setIdCriterioDeIngreso(null);
            rhCriterioDeIngreso.setFechaAlta(new Date());
            rhCriterioDeIngreso.setUsuarioAlta("TEST");
            dao.create(rhCriterioDeIngreso);
            return rhCriterioDeIngreso;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public RhCriterioDeIngreso mergeRhCriterioDeIngreso(RhCriterioDeIngreso criterioDeIngreso) throws Exception {
        try {
            criterioDeIngreso.setFechaAlta(new Date());
            criterioDeIngreso.setUsuarioAlta("TEST");
            criterioDeIngreso.setFechaModificacion(new Date());
            criterioDeIngreso.setUsuarioModificacion("TEST");
            dao.update(criterioDeIngreso);
            return criterioDeIngreso;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RhCriterioDeIngreso> listaRhCriterioDeIngreso() throws Exception {
        try {
            List<RhCriterioDeIngreso> listaRhCriterioDeIngreso = dao.find(""
                    + "select h "
                    + "from RhCriterioDeIngreso h "
                    + "where h.fechaBaja is null");
            if (!listaRhCriterioDeIngreso.isEmpty()) {
                return listaRhCriterioDeIngreso;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean verificaExistenciaCodigoCriterioDeIngreso(String codigo) throws Exception {
        try {
            List<RhCriterioDeIngreso> listaRhCriterioDeIngreso = dao.find(""
                    + "select h "
                    + "from RhCriterioDeIngreso h "
                    + "where h.fechaBaja is null and h.codigo='" + codigo + "'");
            return listaRhCriterioDeIngreso.size() > 0;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhCriterioDeIngreso obtieneCriterioDeIngresoPorDescripcion(String descripcion) throws Exception {
        try {
            List<RhCriterioDeIngreso> lista = dao.find(""
                    + "select j "
                    + "from RhCriterioDeIngreso j "
                    + "where j.fechaBaja is null "
                    + "and j.descripcion = '" + descripcion + "' ");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return new RhCriterioDeIngreso();
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public void removeRhCriterioDeIngreso(Long idCriterioDeIngreso) throws Exception {
        try {
            RhCriterioDeIngreso criterioDeIngreso = dao.findOne(idCriterioDeIngreso);
            criterioDeIngreso.setFechaBaja(new Date());
            criterioDeIngreso.setUsuarioBaja("SYS");
            dao.update(criterioDeIngreso);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public Boolean verificaCriterioDeIngresoEmpleadoCargoPorIdDescuentoAndIdPeriodoParaEliminar(Long idCriterioDeIngreso, Long idPeriodoGestion) throws Exception {
        try {
            List<RhCriterioDeIngresoEmpleadoCargo> lista = rhCriterioDeIngresoEmpleadoCargoService.obtieneCriterioDeIngresoEmpleadoCargoPorIdDescuentoAndIdPeriodoParaEliminar(idCriterioDeIngreso, idPeriodoGestion);
            int cantidadOriginal = lista.size();
            int cantidadAuxiliar = 0;
            for (RhCriterioDeIngresoEmpleadoCargo criterioDeIngresoEmpleadoCargo : lista) {
                if (criterioDeIngresoEmpleadoCargo.getMontoCriterioDeIngreso().compareTo(BigDecimal.ZERO) == 0) {
                    cantidadAuxiliar++;
                }
                System.out.println("OBJETO " + cantidadAuxiliar + ":" + criterioDeIngresoEmpleadoCargo.getMontoCriterioDeIngreso());
            }
            return cantidadOriginal == cantidadAuxiliar;
        } catch (Exception e) {
            throw e;
        }
    }

}
