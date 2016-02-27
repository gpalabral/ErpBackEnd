package com.bap.erp.servicios.impl.rh;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.modelo.rh.RhParametros;
import com.bap.erp.servicios.rh.RhCriterioDeIngresoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhDescuentoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhParametrosService;
import com.bap.erp.servicios.rh.RhPrimasService;
import com.bap.erp.servicios.rh.RhRcIvaService;
import com.bap.erp.servicios.rh.RhVariacionService;
import com.bap.erp.ws.ImportWS;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RhParametrosServiceImpl implements RhParametrosService {

    IGenericDao<RhParametros> dao;

    @Autowired
    private RhDescuentoEmpleadoCargoService rhDescuentoEmpleadoCargoService;
    @Autowired
    private RhVariacionService rhVariacionService;
    @Autowired
    private RhRcIvaService rhRcIvaService;
    @Autowired
    private RhPrimasService rhPrimasService;
    @Autowired
    private RhCriterioDeIngresoEmpleadoCargoService rhCriterioDeIngresoEmpleadoCargoService;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ImportWS.class);

    @Autowired
    public void setDao(IGenericDao<RhParametros> daoToSet) {
        dao = daoToSet;
        dao.setClazz(RhParametros.class);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public RhParametros persistRhParametrosDatosGenerales(RhParametros rhParametros) throws Exception {
        try {
            rhParametros.setIdParametros(null);
            rhParametros.setFechaAlta(new Date());
            rhParametros.setUsuarioAlta("TEST");
            dao.create(rhParametros);
            return rhParametros;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public RhParametros persistModificaRhParametrosPatronalesAndLaborales(RhParametros rhParametros) throws Exception {
        try {
//            rhParametros.setFechaAlta(new Date());
//            rhParametros.setUsuarioAlta("SYS");
            rhParametros.setFechaModificacion(new Date());
            rhParametros.setUsuarioModificacion("TEST");
            dao.update(rhParametros);
            return rhParametros;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhParametros obtieneRhParametrosPorPeriodoGestion(Long idPeriodoGestion) throws Exception {
        //Metodo obtiene Parametros por idPeridoGestion, esto funciona para el caso de que solo exista un registro por periodo y no asi varios.
        //Para soportar varios se debera de hacer un analisis a la tabla Parametros.
        try {
            List<RhParametros> listaEmpleado = dao.find(""
                    + "select h "
                    + "from RhParametros h "
                    + "where h.fechaBaja is null "
                    + "and h.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion);
            if (!listaEmpleado.isEmpty()) {
                return listaEmpleado.get(0);
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public RhParametros guardaParametrosIniciales(RhParametros rhParametros) throws Exception {
        try {
            persistRhParametrosDatosGenerales(rhParametros);
            Long idPeriodoGestion = rhParametros.getRhPeriodoGestion().getIdPeriodoGestion();
            rhDescuentoEmpleadoCargoService.registraDescuentoEmpleadoCargoPorPeriodo(idPeriodoGestion);
            rhVariacionService.registrarRhVariacionPorPeriodo(idPeriodoGestion);
            rhCriterioDeIngresoEmpleadoCargoService.registrarCriterioDeIngresoPorPeriodo(idPeriodoGestion);
            rhRcIvaService.registrarRhRcIvaPorPeriodo(idPeriodoGestion);
            
            rhPrimasService.registrarRhPrimasPorPeriodo(idPeriodoGestion);            
            return rhParametros;
        } catch (Exception e) {
            throw e;
        }
    }    
    
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public RhParametros modificaParametrosIniciales(RhParametros rhParametros) throws Exception {
        try {
            persistModificaRhParametrosPatronalesAndLaborales(rhParametros);
            Long idPeriodoGestion = rhParametros.getRhPeriodoGestion().getIdPeriodoGestion();            
            rhDescuentoEmpleadoCargoService.registraDescuentoEmpleadoCargoPorPeriodo(idPeriodoGestion);
            rhVariacionService.registrarRhVariacionPorPeriodo(idPeriodoGestion);
            rhCriterioDeIngresoEmpleadoCargoService.registrarCriterioDeIngresoPorPeriodo(idPeriodoGestion);
            rhRcIvaService.registrarRhRcIvaPorPeriodo(idPeriodoGestion);
            rhRcIvaService.actualizaSaldoRcIva(idPeriodoGestion);
            rhPrimasService.registrarRhPrimasPorPeriodo(idPeriodoGestion);            
            return rhParametros;
        } catch (Exception e) {
            throw e;
        }
    }

}
