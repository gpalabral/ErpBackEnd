package com.bap.erp.servicios.impl.rh;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.modelo.rh.RhDescuento;
import com.bap.erp.modelo.rh.RhDescuentoEmpleadoCargo;
import com.bap.erp.servicios.rh.RhDescuentoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhDescuentoService;
import com.bap.erp.ws.ImportWS;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RhDescuentoServiceImpl implements RhDescuentoService {

    IGenericDao<RhDescuento> dao;

    @Autowired
    public RhDescuentoEmpleadoCargoService rhDescuentoEmpleadoCargoService;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ImportWS.class);

    @Autowired
    public void setDao(IGenericDao<RhDescuento> daoToSet) {
        dao = daoToSet;
        dao.setClazz(RhDescuento.class);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public RhDescuento persistRhDescuento(RhDescuento rhDescuento) throws Exception {
        try {
            dao.create(rhDescuento);
            return rhDescuento;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public RhDescuento mergeRhDescuento(RhDescuento rhDescuento) throws Exception {
        try {
            rhDescuento.setFechaAlta(new Date());
            rhDescuento.setUsuarioAlta("TEST");
            rhDescuento.setFechaModificacion(new Date());
            rhDescuento.setUsuarioModificacion("TEST");
            dao.update(rhDescuento);
            return rhDescuento;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RhDescuento> obtieneDescuentosVigentes() throws Exception {
        try {
            List<RhDescuento> listaRhDescuento = dao.find(""
                    + "select j "
                    + "from RhDescuento j "
                    + "where j.fechaBaja is null ");
            return listaRhDescuento;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhDescuento obtieneDescuentoPorDescripcion(String descripcion) throws Exception {
        try {
            List<RhDescuento> listaDescuento = dao.find(""
                    + "select j "
                    + "from RhDescuento j "
                    + "where j.fechaBaja is null "
                    + "and j.descripcion = '" + descripcion + "'");
            if (!listaDescuento.isEmpty()) {
                return listaDescuento.get(0);
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    //Autor: Henrry Guzmán
    @Override
    public Boolean verificaExistenciaCodigoDescuento(String codigo) throws Exception {
        try {
            List<RhDescuento> listaRhDescuento = dao.find(""
                    + "select h "
                    + "from RhDescuento h "
                    + "where h.fechaBaja is null and h.codigo='" + codigo + "'");
            return listaRhDescuento.size() > 0;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void removeRhDescuento(Long idDescuento) throws Exception {
        try {
            RhDescuento rhDescuento = dao.findOne(idDescuento);
            rhDescuento.setFechaBaja(new Date());
            rhDescuento.setUsuarioBaja("SYS");
            dao.update(rhDescuento);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean verificaDescuentosPorIdDescuentoAndIdPeriodoParaEliminar(Long idDescuento, Long idPeriodoGestion) throws Exception {
        try {
            List<RhDescuentoEmpleadoCargo> lista = rhDescuentoEmpleadoCargoService.obtieneDescuentosPorIdDescuentoAndIdPeriodo(idDescuento, idPeriodoGestion);
            int cantidadOriginal = lista.size();
            int cantidadAuxiliar = 0;
            for (RhDescuentoEmpleadoCargo descuentoEmpleadoCargo : lista) {
                if (descuentoEmpleadoCargo.getMontoDescuento().compareTo(BigDecimal.ZERO) == 0) {
                    cantidadAuxiliar++;
                }
                System.out.println("OBJETO " + cantidadAuxiliar + ":" + descuentoEmpleadoCargo.getMontoDescuento());
            }
            return cantidadOriginal == cantidadAuxiliar;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RhDescuento> obtieneDescuentosVigentesPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {
            List<RhDescuento> listaDescuento = dao.find(""
                    + "select j.rhDescuento "
                    + "from RhDescuentoEmpleadoCargo j "
                    + "where j.rhPeriodoGestion.idPeriodoGestion = "+idPeriodoGestion+"");
            return listaDescuento;
        } catch (Exception e) {
            throw e;
        }
    }

}
