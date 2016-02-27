package com.bap.erp.servicios.impl.rh;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.enums.EnumBonoAntiguedad;
import com.bap.erp.enums.EnumEstadoPeriodoGestion;
import com.bap.erp.modelo.par.ParTipoBonoAntiguedad;
import com.bap.erp.modelo.rh.RhParametros;
import com.bap.erp.modelo.rh.RhPeriodoGestion;
import com.bap.erp.servicios.par.ParValorService;
import com.bap.erp.servicios.rh.RhDescuentoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhParametrosService;
import com.bap.erp.servicios.rh.RhPeriodoGestionService;
import com.bap.erp.servicios.rh.RhVariacionService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RhPeriodoGestionServiceImpl implements RhPeriodoGestionService {

//    private final BigDecimal DOCE = new BigDecimal(12L);
//    private final BigDecimal CERO_CINCO = new BigDecimal(0.5f);
//    private final BigDecimal TRES = new BigDecimal(3L);
//    private final BigDecimal DIEZ = new BigDecimal(10L);
//    private final BigDecimal TRENTA_Y_CINCO = new BigDecimal(35L);
//    private final BigDecimal DOS = new BigDecimal(2L);
//    private final BigDecimal CIENTO_VEINTITRES = new BigDecimal(123L);
//    private final BigDecimal TRECE = new BigDecimal(13L);
//    private final BigDecimal RIESGO_COMUN = new BigDecimal(1.71f);        
    private final BigDecimal CERO = new BigDecimal(0L);

    IGenericDao<RhPeriodoGestion> dao;

    @Autowired
    private RhParametrosService rhParametrosService;

    @Autowired
    private ParValorService parValorService;

    @Autowired
    private RhDescuentoEmpleadoCargoService rhDescuentoEmpleadoCargoService;

    @Autowired
    private RhVariacionService rhVariacionService;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhPeriodoGestionServiceImpl.class);

    @Autowired
    public void setDao(IGenericDao<RhPeriodoGestion> daoToSet) {
        dao = daoToSet;
        dao.setClazz(RhPeriodoGestion.class);
    }

    @Override
    public RhPeriodoGestion encuentraRhPeriodoGestionPorId(Long idPeriodoGestion) throws Exception {
        try {
            RhPeriodoGestion rhPeriodoGestion = dao.findOne(idPeriodoGestion);
            return rhPeriodoGestion;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhPeriodoGestion persistRhPeriodoGestion(RhPeriodoGestion rhPeriodoGestion) throws Exception {
        try {
            rhPeriodoGestion.setIdPeriodoGestion(null);

            log.info("Periodo::: " + rhPeriodoGestion.getPeriodo());
            log.info("Gestion:: " + rhPeriodoGestion.getGestion());

            //Iniciamos el inicio y fin del periodo/gestion
            Calendar cal = Calendar.getInstance();

            if (rhPeriodoGestion.getPeriodo() == 1) {
                cal.set(Calendar.MONTH, Calendar.JANUARY);
            }
            if (rhPeriodoGestion.getPeriodo() == 2) {
                cal.set(Calendar.MONTH, Calendar.FEBRUARY);
            }
            if (rhPeriodoGestion.getPeriodo() == 3) {
                cal.set(Calendar.MONTH, Calendar.MARCH);
            }
            if (rhPeriodoGestion.getPeriodo() == 4) {
                cal.set(Calendar.MONTH, Calendar.APRIL);
            }
            if (rhPeriodoGestion.getPeriodo() == 5) {
                cal.set(Calendar.MONTH, Calendar.MAY);
            }
            if (rhPeriodoGestion.getPeriodo() == 6) {
                cal.set(Calendar.MONTH, Calendar.JUNE);
            }
            if (rhPeriodoGestion.getPeriodo() == 7) {
                cal.set(Calendar.MONTH, Calendar.JULY);
            }
            if (rhPeriodoGestion.getPeriodo() == 8) {
                cal.set(Calendar.MONTH, Calendar.AUGUST);
            }
            if (rhPeriodoGestion.getPeriodo() == 9) {
                cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
            }
            if (rhPeriodoGestion.getPeriodo() == 10) {
                cal.set(Calendar.MONTH, Calendar.OCTOBER);
            }
            if (rhPeriodoGestion.getPeriodo() == 11) {
                cal.set(Calendar.MONTH, Calendar.NOVEMBER);
            }
            if (rhPeriodoGestion.getPeriodo() == 12) {
                cal.set(Calendar.MONTH, Calendar.DECEMBER);
            }

            cal.set(Calendar.YEAR, rhPeriodoGestion.getGestion());
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date firstDayOfMonth = cal.getTime();

            log.info("firstDayOfMonth:: " + firstDayOfMonth);

            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date lastDayOfMonth = cal.getTime();

            log.info("lastDayOfMonth:: " + lastDayOfMonth);

            rhPeriodoGestion.setInicio(firstDayOfMonth);
            rhPeriodoGestion.setFin(lastDayOfMonth);

            dao.create(rhPeriodoGestion);

            return rhPeriodoGestion;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public RhPeriodoGestion mergeRhPeriodoGestion(RhPeriodoGestion rhPeriodoGestion) throws Exception {
        try {
            rhPeriodoGestion.setFechaAlta(new Date());
            rhPeriodoGestion.setUsuarioAlta("TEST");
            rhPeriodoGestion.setFechaModificacion(new Date());
            rhPeriodoGestion.setUsuarioModificacion("TEST");
            dao.update(rhPeriodoGestion);
            return rhPeriodoGestion;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean existePeriodoGestion(int periodo, int gestion) throws Exception {
        try {
            List<RhPeriodoGestion> lista = dao.find(""
                    + "select j "
                    + "from RhPeriodoGestion j "
                    + "where j.fechaBaja is null "
                    + "and j.periodo = " + periodo + " "
                    + "and j.gestion = " + gestion + "");
            return !lista.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhPeriodoGestion obtieneRegistroPorPeriodoGestion(int periodo, int gestion) throws Exception {
        try {
            List<RhPeriodoGestion> lista = dao.find(""
                    + "select j "
                    + "from RhPeriodoGestion j "
                    + "where j.fechaBaja is null "
                    + "and j.periodo = " + periodo + " "
                    + "and j.gestion = " + gestion + "");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhPeriodoGestion obtienePeriodoGestionUltimoVigente() throws Exception {
        try {
            List<RhPeriodoGestion> lista = dao.find(""
                    + "select h "
                    + "from RhPeriodoGestion h "
                    + "where h.fechaBaja is null "
                    + "and h.parEstadoPeriodoGestion.codigo = '" + EnumEstadoPeriodoGestion.VIGENTE.getCodigo() + "' "
                    + "order by h.fechaAlta desc");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhPeriodoGestion obtienePeriodoGestionUltimoNoVigente() throws Exception {
        try {
            List<RhPeriodoGestion> lista = dao.find(""
                    + "select h "
                    + "from RhPeriodoGestion h "
                    + "where h.fechaBaja is null "
                    + "and h.parEstadoPeriodoGestion.codigo = '" + EnumEstadoPeriodoGestion.LIQUIDADO.getCodigo() + "' "
                    + "order by h.fechaAlta desc");
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
    public RhPeriodoGestion persistRhPeriodoGestionCompleto(RhPeriodoGestion rhPeriodoGestion) throws Exception {
        try {
            rhPeriodoGestion = persistRhPeriodoGestion(rhPeriodoGestion);

            //INICIO: Metodos externos para la creacion automatica
            System.out.println("MUESTRA OBJETO:" + obtienePeriodoGestionUltimoNoVigente());
            RhPeriodoGestion rhPeriodoGestionAnterior = obtienePeriodoGestionUltimoNoVigente();
            RhParametros rhParametros = new RhParametros();
            ParTipoBonoAntiguedad parTipoBonoAntiguedad = parValorService.obtieneParTipoBonoAntiguedadPorCodigo(EnumBonoAntiguedad.TRES_MINIMOS_NACIONALES.getCodigo());
            if (rhPeriodoGestionAnterior == null) {
                rhParametros.setAfp(0);
                rhParametros.setAporteSolidarioLaborales(CERO);
                rhParametros.setAporteSolidarioPatronales(0);
                rhParametros.setCajaSalud(0);
                rhParametros.setComision(CERO);
                rhParametros.setFondoCapitalizacionIndividual(CERO);
                rhParametros.setHorasNocturnas(0);
                rhParametros.setInfocal(0);
                rhParametros.setNumeroAguinaldos(2);
                rhParametros.setNumeroPatronal("0");
                rhParametros.setNumeroPrimas(0);
                rhParametros.setProvivienda(0);
                rhParametros.setRc_iva(0);
                rhParametros.setRiesgoComun(CERO);
                rhParametros.setSueldoMinimoNacional(CERO);
                rhParametros.setTipoCambio(CERO);
                rhParametros.setTipoUfv(CERO);
                rhParametros.setParTipoBonoAntiguedad(parTipoBonoAntiguedad);
                rhParametros.setRhCargoAprueba(null);
                rhParametros.setRhCargoEncargado(null);
                rhParametros.setRhEmpleadoEncargado(null);
                rhParametros.setRhEmpleadoAprueba(null);
                rhParametros.setRhPeriodoGestion(rhPeriodoGestion);
                rhParametrosService.persistRhParametrosDatosGenerales(rhParametros);
            } else {
                // INICIO Generacion de Parametrizacion por Periodo
                rhParametros = rhParametrosService.obtieneRhParametrosPorPeriodoGestion(rhPeriodoGestionAnterior.getIdPeriodoGestion());
                RhParametros rhParametrosClone = (RhParametros) rhParametros.clone();
                rhParametrosClone.setRhPeriodoGestion(rhPeriodoGestion);
                rhParametrosClone.setUsuarioAlta(null);
                rhParametrosClone.setUsuarioModificacion(null);
                rhParametrosClone.setUsuarioBaja(null);
                rhParametrosClone.setFechaAlta(null);
                rhParametrosClone.setFechaModificacion(null);
                rhParametrosClone.setFechaBaja(null);
                rhParametrosService.persistRhParametrosDatosGenerales(rhParametrosClone);
            }
            return rhPeriodoGestion;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<RhPeriodoGestion> getRhPeriodoGestion() throws Exception {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhPeriodoGestion getRhPeriodoGestionById(Long idPeriodoGestion) throws Exception {
        try {
            return dao.findOne(idPeriodoGestion);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhPeriodoGestion obtieneRegistroPorPeriodoGestionAnterior(Long idPeriodoGestion) throws Exception {
        try {
            RhPeriodoGestion periodoGestion = getRhPeriodoGestionById(idPeriodoGestion);
            int periodo = periodoGestion.getPeriodo();
            int gestion = periodoGestion.getGestion();
            Boolean sw = false;
            List<RhPeriodoGestion> lista;
            if (getRhPeriodoGestion().size() > 1) {
                periodo--;
                gestion = periodo == 0 ? gestion-- : gestion;
                periodo = periodo == 0 ? 12 : periodo;
                lista = dao.find(""
                        + "select j "
                        + "from RhPeriodoGestion j "
                        + "where j.fechaBaja is null "
                        + "and j.periodo = " + periodo + " "
                        + "and j.gestion = " + gestion + "");

                return !lista.isEmpty() ? lista.get(0) : null;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
