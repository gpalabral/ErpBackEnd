package com.bap.erp.servicios.impl.rh;

import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.utils.ObjectUtils;
import com.bap.erp.enums.EnumEstado;
import com.bap.erp.modelo.rh.RhEmpleado;
import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import com.bap.erp.servicios.rh.RhCargoService;
import com.bap.erp.servicios.rh.RhCriterioDeIngresoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhDescuentoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhEmpleadoService;
import com.bap.erp.servicios.rh.RhPrimasService;
import com.bap.erp.servicios.rh.RhRcIvaService;
import com.bap.erp.servicios.rh.RhVariacionService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RhEmpleadoCargoServiceImpl extends AbstractJpaDAO<RhEmpleadoCargo> implements RhEmpleadoCargoService {

    IGenericDao<RhEmpleadoCargo> dao;

    @Autowired
    public RhEmpleadoService rhEmpleadoService;

    @Autowired
    public RhCargoService rhCargoService;

    @Autowired
    public RhVariacionService rhVariacionService;

    @Autowired
    public RhCriterioDeIngresoEmpleadoCargoService rhCriterioDeIngresoEmpleadoCargoService;

    @Autowired
    public RhDescuentoEmpleadoCargoService rhDescuentoEmpleadoCargoService;

    @Autowired
    public RhRcIvaService rhRcIvaService;

    @Autowired
    public RhPrimasService rhPrimasService;

    @Autowired
    public void setDao(IGenericDao<RhEmpleadoCargo> daoToSet) {
        dao = daoToSet;
        dao.setClazz(RhEmpleadoCargo.class);
    }

    @Override
    public RhEmpleadoCargo persistRhEmpleadoCargo(RhEmpleadoCargo rhEmpleadoCargo) throws Exception {
        try {
            dao.create(rhEmpleadoCargo);
            return rhEmpleadoCargo;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhEmpleadoCargo mergeRhEmpleadoCargo(RhEmpleadoCargo rhEmpleadoCargo) throws Exception {
        try {
            rhEmpleadoCargo.setFechaAlta(new Date());
            rhEmpleadoCargo.setUsuarioAlta("SYS");
            rhEmpleadoCargo.setFechaModificacion(new Date());
            rhEmpleadoCargo.setUsuarioModificacion("TEST");
            dao.update(rhEmpleadoCargo);
            return rhEmpleadoCargo;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public RhEmpleadoCargo persistModificaRhEmpleadoCargo(RhEmpleadoCargo rhEmpleadoCargo) throws Exception {
        try {
            RhEmpleado rhEmpleado = rhEmpleadoCargo.getRhEmpleado();
            rhEmpleadoService.removeRhEmpleado(rhEmpleado.getIdEmpleado());
            rhEmpleado.setIdEmpleado(null);
            rhEmpleado = rhEmpleadoService.persistRhEmpleado(rhEmpleado);
            RhEmpleadoCargo rhEmpleadoCargoBD = rhEmpleadoCargo;
            if (rhEmpleadoCargoBD.getIdEmpleadoCargo() != null) {
                removeRhEmpleadoCargo(rhEmpleadoCargo.getIdEmpleadoCargo());
                rhEmpleadoCargoBD.setIdAntecesor(rhEmpleadoCargo.getIdEmpleadoCargo());
                rhEmpleadoCargoBD.setIdEmpleadoCargo(null);
                rhEmpleadoCargoBD.setRhEmpleado(rhEmpleado);
                persistEmpleadoCargo(rhEmpleadoCargoBD);
            }
            return rhEmpleadoCargo;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhEmpleadoCargo getRhEmpleadoCargoById(Long idEmpleadoCargo) throws Exception {
        try {
            return dao.findOne(idEmpleadoCargo);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RhEmpleadoCargo> obtieneEmpleadoCargoVigente(Long idPeriodoGestion) throws Exception {
        try {
            List<RhEmpleadoCargo> listaEmpleadoCargo = dao.find(""
                    + "select j "
                    + "from RhEmpleadoCargo j "
                    + "where j.fechaBaja is null "
                    + "and j.rhEmpleado.parEstado.codigo = '" + EnumEstado.VIGENTE.getCodigo() + "' "
                    + "order by j.rhEmpleado.codigo asc");
            return listaEmpleadoCargo;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public RhEmpleadoCargo persistEmpleadoCargo(RhEmpleadoCargo rhEmpleadoCargo) throws Exception {
        try {
            RhEmpleado rhEmpleado = rhEmpleadoCargo.getRhEmpleado();
            rhEmpleadoService.mergeRhEmpleado(rhEmpleado);
            ObjectUtils.printObjectState(rhEmpleadoCargo);
            rhEmpleadoCargo = persistRhEmpleadoCargo(rhEmpleadoCargo);
            return rhEmpleadoCargo;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhEmpleadoCargo obtieneEmpleadoCargoPorIdEmpleado(Long idEmpleado) throws Exception {
        try {
            List<RhEmpleadoCargo> listaEmpleadoCargo = dao.find(""
                    + "select h "
                    + "from RhEmpleadoCargo h "
                    + "where h.fechaBaja is null "
                    + "and h.rhEmpleado.parEstado.codigo = '" + EnumEstado.VIGENTE.getCodigo() + "' "
                    + "and h.rhEmpleado.idEmpleado= '" + idEmpleado + "' "
                    + "order by h.rhEmpleado.codigo asc");
            return listaEmpleadoCargo.size() > 0 ? listaEmpleadoCargo.get(0) : null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void removeRhEmpleadoCargo(Long idEmpleadoCargo) throws Exception {
        try {
            RhEmpleadoCargo rhEmpleadoCargo = dao.findOne(idEmpleadoCargo);
            rhEmpleadoCargo.setFechaBaja(new Date());
            rhEmpleadoCargo.setUsuarioBaja("SYS");
            dao.update(rhEmpleadoCargo);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhEmpleadoCargo obtieneEmpleadoCargoPorCodigo(String codigo) throws Exception {
        try {
            List<RhEmpleadoCargo> lista = dao.find(""
                    + "select j "
                    + "from RhEmpleadoCargo j "
                    + "where j.fechaBaja is null "
                    + "and j.rhEmpleado.codigo = '" + codigo + "' ");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public RhEmpleadoCargo persistModificaRhEmpleadoCargoMasTablasRelacionadas(RhEmpleadoCargo rhEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {

            RhEmpleadoCargo rhEmpleadoCargoBD;

            //MODIFICA EMPLEADO CARGO
            rhEmpleadoCargoBD = persistModificaRhEmpleadoCargo(rhEmpleadoCargo);

            //Variaciones
            rhVariacionService.modificaCamposEmpleadoCargoParaVariacion(rhEmpleadoCargoBD, idPeriodoGestion);

            //CONCEPTOS DE INGRESOS
            rhCriterioDeIngresoEmpleadoCargoService.modificaCamposEmpleadoCargoListaRhCriterioDeIngresoEmpleadoCargo(rhEmpleadoCargoBD, idPeriodoGestion);

            //OTROS DESCUENTOS
            rhDescuentoEmpleadoCargoService.modificaCamposEmpleadoCargoListaRhDescuentoEmpleadoCargo(rhEmpleadoCargoBD, idPeriodoGestion);

            //RC-IVA
            rhRcIvaService.modificaCamposEmpleadoCargoParaRcIva(rhEmpleadoCargoBD, idPeriodoGestion);

            //PRIMAS
            rhPrimasService.modificaCamposEmpleadoCargoParaPrimas(rhEmpleadoCargoBD, idPeriodoGestion);

            return rhEmpleadoCargo;
        } catch (Exception e) {
            throw e;
        }
    }
}
