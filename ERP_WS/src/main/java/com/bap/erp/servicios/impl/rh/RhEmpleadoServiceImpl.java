package com.bap.erp.servicios.impl.rh;

import com.bap.erp.aspects.UserSessionAspect;
import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.utils.DateUtils;
import com.bap.erp.enums.EnumEstado;
import com.bap.erp.enums.EnumVacacionesValores;
import com.bap.erp.modelo.par.ParEstado;
import com.bap.erp.modelo.rh.RhEmpleado;
import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import com.bap.erp.modelo.rh.RhPeriodoGestion;
import com.bap.erp.servicios.par.ParValorService;
import com.bap.erp.servicios.rh.RhEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhEmpleadoService;
import com.bap.erp.servicios.rh.RhPeriodoGestionService;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RhEmpleadoServiceImpl implements RhEmpleadoService {
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhEmpleadoServiceImpl.class);
    
    IGenericDao<RhEmpleado> dao;
    
    @Autowired
    private ParValorService parValorService;
    
    @Autowired
    private RhEmpleadoCargoService rhEmpleadoCargoService;
    
    @Autowired
    private RhPeriodoGestionService rhPeriodoGestionService;
    
    @Autowired
    public void setDao(IGenericDao<RhEmpleado> daoToSet) {
        dao = daoToSet;
        dao.setClazz(RhEmpleado.class);
    }
    
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public RhEmpleado persistRhEmpleado(RhEmpleado rhEmpleado) throws Exception {
        try {
            log.info("el objeto es::: " + rhEmpleado.getParCondicionPension());
            rhEmpleado.setIdEmpleado(null);
//            rhEmpleado.setFechaAlta(new Date());
//            rhEmpleado.setUsuarioAlta("SYS");
            dao.create(rhEmpleado);
            return rhEmpleado;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public RhEmpleado mergeRhEmpleado(RhEmpleado rhEmpleado) throws Exception {
        try {
            rhEmpleado.setFechaAlta(new Date());
            rhEmpleado.setUsuarioAlta("SYS");
            rhEmpleado.setFechaModificacion(new Date());
            rhEmpleado.setUsuarioModificacion("TEST");
            dao.update(rhEmpleado);
            return rhEmpleado;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void removeRhEmpleado(Long idEmpleado) throws Exception {
        try {
            RhEmpleado rhEmpleado = dao.findOne(idEmpleado);
            rhEmpleado.setFechaBaja(new Date());
            rhEmpleado.setUsuarioBaja("SYS");
            dao.update(rhEmpleado);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public List<RhEmpleado> listaRhEmpleado() throws Exception {
        try {
            List<RhEmpleado> listaRhEmpleado = dao.find(""
                    + "select h "
                    + "from RhEmpleado h "
                    + "where h.fechaBaja is null "
                    + "and h.parEstado.codigo = '" + EnumEstado.VIGENTE.getCodigo() + "' "
                    + "order by h.codigo asc");
            if (!listaRhEmpleado.isEmpty()) {
                return listaRhEmpleado;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public String codigoEmpleado() throws Exception {
        try {
            List<RhEmpleado> listaEmpleado = dao.find(""
                    + "select j "
                    + "from RhEmpleado j "
                    + "order by j.codigo desc");
            if (!listaEmpleado.isEmpty()) {
                Integer numero = Integer.parseInt(listaEmpleado.get(0).getCodigo()) + 1;
                return numero.toString();
            }
            return "1";
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public RhEmpleado persistModificacionRhEmpleado(RhEmpleado rhEmpleado) throws Exception {
        try {
            RhEmpleado rhEmpleadoBD = dao.findOne(rhEmpleado.getIdEmpleado());
            ParEstado parEstadoUno = parValorService.findParEstadoByCodigo(EnumEstado.NO_VIGENTE.getCodigo());
            rhEmpleadoBD.setParEstado(parEstadoUno);
            removeRhEmpleado(rhEmpleadoBD.getIdEmpleado());
            rhEmpleado.setIdAntecesor(rhEmpleadoBD.getIdEmpleado());
            ParEstado parEstadoDos = parValorService.findParEstadoByCodigo(EnumEstado.VIGENTE.getCodigo());
            rhEmpleado.setParEstado(parEstadoDos);
            rhEmpleado.setIdEmpleado(null);
            persistRhEmpleado(rhEmpleado);
            RhEmpleadoCargo rhEmpleadoCargoBD = rhEmpleadoCargoService.obtieneEmpleadoCargoPorIdEmpleado(rhEmpleadoBD.getIdEmpleado());
            RhEmpleadoCargo rhEmpleadoCargo = rhEmpleadoCargoBD;
            rhEmpleadoCargoService.removeRhEmpleadoCargo(rhEmpleadoCargoBD.getIdEmpleadoCargo());
            rhEmpleadoCargo.setRhEmpleado(rhEmpleado);
            rhEmpleadoCargoService.mergeRhEmpleadoCargo(rhEmpleadoCargo);
            return rhEmpleado;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public RhEmpleado getRhEmpleadoById(Long idEmpleado) throws Exception {
        try {
            return dao.findOne(idEmpleado);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public RhEmpleado obtieneEmpleadoPorCodigo(String codigo) throws Exception {
        try {
            List<RhEmpleado> listaEmpleado = dao.find(""
                    + "select j "
                    + "from RhEmpleado j "
                    + "where j.fechaBaja is null "
                    + "and j.codigo = '" + codigo + "' "
                    + "and j.parEstado.codigo = '" + EnumEstado.VIGENTE.getCodigo() + "'");
            if (!listaEmpleado.isEmpty()) {
                return listaEmpleado.get(0);
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public RhEmpleado obtieneEmpleadoPorIdEmpleadoCargo(Long idEmpleadoCargo) throws Exception {
        try {
            List<RhEmpleado> listaEmpleado = dao.find(""
                    + "select j.rhEmpleado "
                    + "from RhEmpleadoCargo j "
                    + "where j.fechaBaja is null "
                    + "and j.rhEmpleado.fechaBaja is null "
                    + "and j.idEmpleadoCargo = " + idEmpleadoCargo + "");
            if (!listaEmpleado.isEmpty()) {
                return listaEmpleado.get(0);
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @param fechaInicial
     * @param fechaFinal
     * @return int
     * @throws Exception
     */
    @Override
    public int obtieneDiasVacacion(Date fechaInicial, Date fechaFinal) throws Exception {
        try {
            int valorPrimerAnio = Integer.parseInt(EnumVacacionesValores.PRIMER_VALOR_LIMITE_VACACION_ANIOS.getCodigo());
            int valorSegundoAnio = Integer.parseInt(EnumVacacionesValores.SEGUNDO_VALOR_LIMITE_VACACION_ANIOS.getCodigo());
            int valorPrimerDias = Integer.parseInt(EnumVacacionesValores.PRIMER_VALOR_VACACION_DIAS.getCodigo());
            int valorSegundoDias = Integer.parseInt(EnumVacacionesValores.SEGUNDO_VALOR_VACACION_DIAS.getCodigo());
            int valorTerceroDias = Integer.parseInt(EnumVacacionesValores.TERCERO_VALOR_VACACION_DIAS.getCodigo());
            int totalAnios;
            log.info("FECHA INICIAL:" + fechaInicial);
            log.info("FECHA FINAL:" + fechaFinal);
            log.info("FECHA ACTUAL:" + new Date());
            String valorResultante = DateUtils.aniosMesesDiasEntreDosFechas(fechaInicial, fechaFinal);
            log.info("VALOR FORMATO DEVUELTO:" + valorResultante);
            String[] divideResultado = valorResultante.split("-");
            for (int i = 0; i < divideResultado.length; i++) {
                log.info("VALOR [" + i + "]=" + divideResultado[i]);
            }
            totalAnios = Integer.parseInt(divideResultado[0]);
            if (Integer.parseInt(divideResultado[1]) > 0) {
                totalAnios++;
            } else {
                if (Integer.parseInt(divideResultado[2]) > 0) {
                    totalAnios++;
                }
            }
            return totalAnios <= valorPrimerAnio ? valorPrimerDias : totalAnios <= valorSegundoAnio ? valorSegundoDias : valorTerceroDias;
            
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    @Override
    public List<RhEmpleado> listaRhEmpleadoConCargoAsignado() throws Exception {
        try {
            
            List<RhEmpleado> listaRhEmpleado = dao.find(""
                    + "select h "
                    + "from RhEmpleado h "
                    + "where h.fechaBaja is null "
                    + "and h.parEstado.codigo = '" + EnumEstado.VIGENTE.getCodigo() + "' and h.idEmpleado in "
                    + "(select r.rhEmpleado.idEmpleado "
                    + "from RhEmpleadoCargo r "
                    + "where r.fechaBaja is null and r.rhCargo.idCargo is not null "
                    + "and r.rhEmpleado.parEstado.codigo = '" + EnumEstado.VIGENTE.getCodigo() + "')");
            if (!listaRhEmpleado.isEmpty()) {
                return listaRhEmpleado;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public List<RhEmpleado> listaRhEmpleadosNuevosSinVariaciones(Long idPeriodoGestion) throws Exception {
        try {
            RhPeriodoGestion periodoGestion = rhPeriodoGestionService.getRhPeriodoGestionById(idPeriodoGestion);
            log.info("Empleados nuevos del periodo::: " + periodoGestion);
            
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(periodoGestion.getInicio().getTime() + 86400000L);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            
            List<RhEmpleado> listaRhEmpleado = dao.find(""
                    + "select h "
                    + "from RhEmpleado h "
                    + "where h.fechaBaja is null "
                    + "and h.parEstado.codigo = '" + EnumEstado.VIGENTE.getCodigo() + "' and "
                    + "MONTH(h.fechaIngreso) = " + (month + 1) + " and  YEAR(h.fechaIngreso)= " + year + " and h.idEmpleado NOT IN ("
                    + " select j.rhEmpleadoCargo.rhEmpleado.idEmpleado "
                    + "from RhVariacion j "
                    + "where j.fechaBaja is null "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + ")");
            
            return listaRhEmpleado;
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public List<RhEmpleado> listaRhEmpleadosNuevosSinRcIva(Long idPeriodoGestion) throws Exception {
        try {
            RhPeriodoGestion periodoGestion = rhPeriodoGestionService.getRhPeriodoGestionById(idPeriodoGestion);
            log.info("Empleados nuevos del periodo::: " + periodoGestion);
            
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(periodoGestion.getInicio().getTime() + 86400000L);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            
            List<RhEmpleado> listaRhEmpleado = dao.find(""
                    + "select h "
                    + "from RhEmpleado h "
                    + "where h.fechaBaja is null "
                    + "and h.parEstado.codigo = '" + EnumEstado.VIGENTE.getCodigo() + "' and "
                    + "MONTH(h.fechaIngreso) = " + (month + 1) + " and  YEAR(h.fechaIngreso)= " + year + " and h.idEmpleado NOT IN ("
                    + " select j.rhEmpleadoCargo.rhEmpleado.idEmpleado "
                    + "from RhRcIva j "
                    + "where j.fechaBaja is null "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + ")");
            
            return listaRhEmpleado;
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public List<RhEmpleado> listaRhEmpleadosNuevosSinDescuentos(Long idPeriodoGestion) throws Exception {
        try {
            RhPeriodoGestion periodoGestion = rhPeriodoGestionService.getRhPeriodoGestionById(idPeriodoGestion);
            log.info("Empleados nuevos del periodo::: " + periodoGestion);
            
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(periodoGestion.getInicio().getTime() + 86400000L);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            
            List<RhEmpleado> listaRhEmpleado = dao.find(""
                    + "select h "
                    + "from RhEmpleado h "
                    + "where h.fechaBaja is null "
                    + "and h.parEstado.codigo = '" + EnumEstado.VIGENTE.getCodigo() + "' and "
                    + "MONTH(h.fechaIngreso) = " + (month + 1) + " and  YEAR(h.fechaIngreso)= " + year + " and h.idEmpleado NOT IN ("
                    + " select j.rhEmpleadoCargo.rhEmpleado.idEmpleado "
                    + "from RhDescuentoEmpleadoCargo j "
                    + "where j.fechaBaja is null "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + ")");
            
            return listaRhEmpleado;
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public List<RhEmpleado> listaRhEmpleadosNuevosSinCriteriosIngreso(Long idPeriodoGestion) throws Exception {
        try {
            RhPeriodoGestion periodoGestion = rhPeriodoGestionService.getRhPeriodoGestionById(idPeriodoGestion);
            log.info("Empleados nuevos del periodo::: " + periodoGestion);
            
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(periodoGestion.getInicio().getTime() + 86400000L);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            
            List<RhEmpleado> listaRhEmpleado = dao.find(""
                    + "select h "
                    + "from RhEmpleado h "
                    + "where h.fechaBaja is null "
                    + "and h.parEstado.codigo = '" + EnumEstado.VIGENTE.getCodigo() + "' and "
                    + "MONTH(h.fechaIngreso) = " + (month + 1) + " and  YEAR(h.fechaIngreso)= " + year + " and h.idEmpleado NOT IN ("
                    + " select j.rhEmpleadoCargo.rhEmpleado.idEmpleado "
                    + "from RhCriterioDeIngresoEmpleadoCargo j "
                    + "where j.fechaBaja is null "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + ")");
            
            return listaRhEmpleado;
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public List<RhEmpleado> listaRhEmpleadosNuevosSinPrima(Long idPeriodoGestion) throws Exception {
        try {
            RhPeriodoGestion periodoGestion = rhPeriodoGestionService.getRhPeriodoGestionById(idPeriodoGestion);
            log.info("Empleados nuevos del periodo::: " + periodoGestion);
            
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(periodoGestion.getInicio().getTime() + 86400000L);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            
            List<RhEmpleado> listaRhEmpleado = dao.find(""
                    + "select h "
                    + "from RhEmpleado h "
                    + "where h.fechaBaja is null "
                    + "and h.parEstado.codigo = '" + EnumEstado.VIGENTE.getCodigo() + "' and "
                    + "MONTH(h.fechaIngreso) = " + (month + 1) + " and  YEAR(h.fechaIngreso)= " + year + " and h.idEmpleado NOT IN ("
                    + " select j.rhEmpleadoCargo.rhEmpleado.idEmpleado "
                    + "from RhPrimas j "
                    + "where j.fechaBaja is null "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + ")");
            
            return listaRhEmpleado;
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public List<RhEmpleado> listaRhEmpleadoPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {
            log.info("IID PERIODO GESTION:" + idPeriodoGestion);
            RhPeriodoGestion periodoGestion = rhPeriodoGestionService.getRhPeriodoGestionById(idPeriodoGestion);
            log.info("Empleados nuevos del periodo::: " + periodoGestion);
            
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(new Date().getTime() + 86400000L);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DATE);
            
            log.info("OBJETO PERIODO:" + periodoGestion);
            
            int mes = periodoGestion.getPeriodo();
            int anio = periodoGestion.getGestion();
            
            List<RhEmpleado> listaRhEmpleado = dao.find(""
                    + "select h "
                    + "from RhEmpleado h "
                    + "where h.fechaBaja is null "
                    + "and (h.parEstado.codigo = '" + EnumEstado.VIGENTE.getCodigo() + "' or h.parEstado.codigo = '" + EnumEstado.VIGENTE.getCodigo() + "')"
                    + "and (h.fechaIngreso is null or (MONTH(h.fechaIngreso) <= " + mes + " and  YEAR(h.fechaIngreso)<= " + anio + ")) "
                    + "and (h.fechaRetiro is null or (MONTH(h.fechaRetiro) >= " + (month + 1) + " and  YEAR(h.fechaRetiro)>= " + year + "))");
            if (!listaRhEmpleado.isEmpty()) {
                return listaRhEmpleado;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void importaEmpleadosExcel(InputStream fileInputStream) throws Exception {
        try {
            
        } catch (Exception e) {
            throw e;
        }
    }
    
}
