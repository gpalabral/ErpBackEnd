package com.bap.erp.servicios.impl.rh;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.utils.DateUtils;
import com.bap.erp.commons.utils.NumberUtils;
import com.bap.erp.enums.EnumBonoAntiguedad;
import com.bap.erp.enums.EnumCondicionPension;
import com.bap.erp.enums.EnumEstadoPeriodoGestion;
import com.bap.erp.enums.EnumTipoContratoEmpleado;
import com.bap.erp.modelo.par.ParEstadoPeriodoGestion;
import com.bap.erp.modelo.par.ParPorcentajeAntiguedad;
import com.bap.erp.modelo.pojo.rh.RhTransferenciasBancariasPojo;
import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import com.bap.erp.modelo.rh.RhParametros;
import com.bap.erp.modelo.rh.RhPeriodoGestion;
import com.bap.erp.modelo.rh.RhPlanillaImpositiva;
import com.bap.erp.modelo.rh.RhPlanillaSueldos;
import com.bap.erp.modelo.rh.RhVariacion;
import com.bap.erp.servicios.par.ParValorService;
import com.bap.erp.servicios.rh.RhCriterioDeIngresoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhDescuentoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhParametrosService;
import com.bap.erp.servicios.rh.RhPeriodoGestionService;
import com.bap.erp.servicios.rh.RhPlanillaImpositivaService;
import com.bap.erp.servicios.rh.RhPlanillaSueldosService;
import com.bap.erp.servicios.rh.RhVariacionService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RhPlanillaSueldosServiceImpl implements RhPlanillaSueldosService {

    private final BigDecimal TRECE_MIL = new BigDecimal(13000L);
    private final BigDecimal VEINTICINCO_MIL = new BigDecimal(25000L);
    private final BigDecimal TREINTA_Y_CINCO_MIL = new BigDecimal(35000L);
    private final BigDecimal CERO = new BigDecimal(0L);
    private final BigDecimal CERO_PUNTO_CERO_UNO = new BigDecimal(0.01f);
    private final BigDecimal CERO_PUNTO_CERO_CINCO = new BigDecimal(0.05f);
    private final BigDecimal CERO_PUNTO_DIEZ = new BigDecimal(0.10f);
    private final BigDecimal CIEN = new BigDecimal(100L);
    private final BigDecimal TREINTA = new BigDecimal(30L);
    private final BigDecimal DOSCIENTOS_CUARENTA = new BigDecimal(240L);
    private final int SESENTA_Y_CINCO = 65;
    private final BigDecimal DOS = new BigDecimal(2L);
    private final BigDecimal UNO = new BigDecimal(1L);
    private final BigDecimal TRES = new BigDecimal(3L);

    IGenericDao<RhPlanillaSueldos> dao;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhPlanillaSueldosServiceImpl.class);

    @Autowired
    public ParValorService parValorService;
    @Autowired
    public RhParametrosService rhParametrosService;
    @Autowired
    public RhEmpleadoCargoService rhEmpleadoCargoService;
    @Autowired
    public RhVariacionService rhVariacionService;
    @Autowired
    public RhPeriodoGestionService rhPeriodoGestionService;
    @Autowired
    public RhDescuentoEmpleadoCargoService rhDescuentoEmpleadoCargoService;
    @Autowired
    public RhCriterioDeIngresoEmpleadoCargoService rhCriterioDeIngresoEmpleadoCargoService;
    @Autowired
    public RhPlanillaImpositivaService rhPlanillaImpositivaService;

    @Autowired
    public void setDao(IGenericDao<RhPlanillaSueldos> daoToSet) {
        dao = daoToSet;
        dao.setClazz(RhPlanillaSueldos.class);
    }

    public String aniosMesesDiasTrabajados(RhPeriodoGestion rhPeriodoGestion, RhEmpleadoCargo rhEmpleadoCargo) throws Exception {
        try {
            String aniosMesesDiasTrabajados = DateUtils.aniosMesesDiasEntreDosFechas(rhEmpleadoCargo.getRhEmpleado().getFechaIngreso(), rhPeriodoGestion.getFin());
            return aniosMesesDiasTrabajados;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal porcentajeAntiguedad(String aniosTrabajados) throws Exception {
        try {
            String[] datos = aniosTrabajados.split("-");
            int anio = Integer.parseInt(datos[0]);
            if (Integer.parseInt(datos[1]) > 0) {
                anio++;
            } else if (Integer.parseInt(datos[2]) > 0) {
                anio++;
            }
            log.info("aniiiiiooo::: " + anio);
            ParPorcentajeAntiguedad parPorcentajeAntiguedad = parValorService.getParPorcentajeAntiguedadSegunAnio(anio);
            return NumberUtils.redondeaBigDecimal(new BigDecimal(parPorcentajeAntiguedad.getPorcentaje()), 2);
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal bonoAntiguedad(RhEmpleadoCargo rhEmpleadoCargo, RhPeriodoGestion rhPeriodoGestion) throws Exception {
        try {
            BigDecimal bonoAntiguedad;
            BigDecimal previoBonoAntiguedad;
            RhParametros rhParametros = rhParametrosService.obtieneRhParametrosPorPeriodoGestion(rhPeriodoGestion.getIdPeriodoGestion());
            BigDecimal sueldoMinimoNacional = rhParametros.getSueldoMinimoNacional();
            log.info("sueldo Minimo::::::::::: " + sueldoMinimoNacional);
            BigDecimal porcentajeAntiguedad = porcentajeAntiguedad(aniosMesesDiasTrabajados(rhPeriodoGestion, rhEmpleadoCargo));
            log.info("porcentaje antiguedad::: " + porcentajeAntiguedad);
            RhVariacion rhVariacion = rhVariacionService.obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(rhEmpleadoCargo.getIdEmpleadoCargo(), rhPeriodoGestion.getIdPeriodoGestion());
            BigDecimal montoPorTipoAntiguedad = montoSegunTipoBonoAntiguedad(rhParametros.getParTipoBonoAntiguedad().getCodigo(), rhPeriodoGestion.getIdPeriodoGestion(), rhEmpleadoCargo.getIdEmpleadoCargo());
            log.info("monto por tipo antiguedad::: " + montoPorTipoAntiguedad);
            if (montoPorTipoAntiguedad.compareTo(CERO) == 0) {
                previoBonoAntiguedad = rhEmpleadoCargo.getSueldo().multiply(porcentajeAntiguedad.divide(CIEN, 5, RoundingMode.HALF_UP));
            } else if (montoPorTipoAntiguedad.compareTo(DOS) == 0) {
                previoBonoAntiguedad = CERO;
            } else {
                 log.info("ultimo:::");
                previoBonoAntiguedad = (sueldoMinimoNacional.multiply(montoPorTipoAntiguedad)).multiply(porcentajeAntiguedad.divide(CIEN, 5, RoundingMode.HALF_UP));
            }
            log.info("previo a bono Antiguedad::: " + previoBonoAntiguedad);
            log.info("rhVariacion::: "+rhVariacion);
            log.info("dias trabajados:::::::::::: " + rhVariacion.getDiasTrabajados());
            if (rhVariacion.getDiasTrabajados().compareTo(TREINTA) == 0) {
                bonoAntiguedad = previoBonoAntiguedad;
            } else {
                bonoAntiguedad = (previoBonoAntiguedad.divide(TREINTA, 5, RoundingMode.HALF_UP)).multiply(rhVariacion.getDiasTrabajados());
            }
            return NumberUtils.redondeaBigDecimal(bonoAntiguedad, 2);
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal montoSegunTipoBonoAntiguedad(String codigoTipoBonoAntiguedad, Long idPeriodoGestion, Long idEmpleadoCargo) throws Exception {
        try {
            BigDecimal monto = new BigDecimal(BigInteger.ZERO);
            if (codigoTipoBonoAntiguedad.equals(EnumBonoAntiguedad.UN_MINIMO_NACIONAL.getCodigo())) {
                monto = UNO;
            } else if (codigoTipoBonoAntiguedad.equals(EnumBonoAntiguedad.TRES_MINIMOS_NACIONALES.getCodigo())) {
                monto = TRES;
            } else if (codigoTipoBonoAntiguedad.equals(EnumBonoAntiguedad.SUELDO_BASICO.getCodigo())) {
                monto = CERO;
            } else if (codigoTipoBonoAntiguedad.equals(EnumBonoAntiguedad.NINGUNO.getCodigo())) {
                monto = DOS;
            }
            return monto;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal ingresoPorDiasTrabajados(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            RhVariacion rhVariacion = rhVariacionService.obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(idEmpleadoCargo, idPeriodoGestion);
            RhEmpleadoCargo rhEmpleadoCargo = rhEmpleadoCargoService.getRhEmpleadoCargoById(idEmpleadoCargo);
            BigDecimal ingreso = rhEmpleadoCargo.getSueldo();
            if (rhVariacion.getDiasTrabajados().compareTo(TREINTA) == -1) {
                ingreso = (rhEmpleadoCargo.getSueldo().divide(TREINTA, 5, RoundingMode.HALF_UP)).multiply(rhVariacion.getDiasTrabajados());
            }
            return ingreso;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public List<RhPlanillaSueldos> generaPlanillaDeSueldos(Long idPeriodoGestion) throws Exception {
        try {
            List<RhPlanillaSueldos> listaPlanillaSueldos = new ArrayList<RhPlanillaSueldos>();
            RhPlanillaSueldos rhPlanillaSueldos;
            RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.encuentraRhPeriodoGestionPorId(idPeriodoGestion);
            List<RhEmpleadoCargo> listaEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoVigente(idPeriodoGestion);
            RhParametros rhParametros = rhParametrosService.obtieneRhParametrosPorPeriodoGestion(idPeriodoGestion);
            BigDecimal porcentajeHorasNocturnas = new BigDecimal(rhParametros.getHorasNocturnas());
            porcentajeHorasNocturnas = porcentajeHorasNocturnas.divide(CIEN);
            for (RhEmpleadoCargo empleadoCargo : listaEmpleadoCargo) {
                rhPlanillaSueldos = new RhPlanillaSueldos();
                rhPlanillaSueldos = rhPlanillaSueldosFactory(empleadoCargo, rhPeriodoGestion);
                listaPlanillaSueldos.add(rhPlanillaSueldos);
            }
            return listaPlanillaSueldos;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhPlanillaSueldos rhPlanillaSueldosFactory(RhEmpleadoCargo rhEmpleadoCargo, RhPeriodoGestion rhPeriodoGestion) throws Exception {
        try {
            RhPlanillaSueldos rhPlanillaSueldos = new RhPlanillaSueldos();
            RhVariacion rhVariacion = rhVariacionService.obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(rhEmpleadoCargo.getIdEmpleadoCargo(), rhPeriodoGestion.getIdPeriodoGestion());
            rhPlanillaSueldos.setRhPeriodoGestion(rhPeriodoGestion);
            rhPlanillaSueldos.setRhEmpleadoCargo(rhEmpleadoCargo);
            rhPlanillaSueldos.setBonoAntiguedad(bonoAntiguedad(rhEmpleadoCargo, rhPeriodoGestion));
            rhPlanillaSueldos.setDiasTrabajados(rhVariacion.getDiasTrabajados());
            rhPlanillaSueldos.setIngresoDiasTrabajados(NumberUtils.redondeaBigDecimal(ingresoPorDiasTrabajados(rhEmpleadoCargo.getIdEmpleadoCargo(), rhPeriodoGestion.getIdPeriodoGestion()), 2));
            rhPlanillaSueldos.setDiasDomingo(rhVariacion.getDiasDomingo());
            rhPlanillaSueldos.setIngresoDiasDomingo(NumberUtils.redondeaBigDecimal(ingresoDiasDomingo(rhEmpleadoCargo.getIdEmpleadoCargo(), rhPeriodoGestion.getIdPeriodoGestion()), 2));
            rhPlanillaSueldos.setHorasExtras(rhVariacion.getHorasExtras());
            rhPlanillaSueldos.setIngresoHorasExtras(NumberUtils.redondeaBigDecimal(ingresoHorasExtras(rhEmpleadoCargo.getIdEmpleadoCargo(), rhPeriodoGestion.getIdPeriodoGestion()), 2));
            rhPlanillaSueldos.setHorasNocturnas(rhVariacion.getHorasNocturnas());
            rhPlanillaSueldos.setIngresoHorasNocturnas(NumberUtils.redondeaBigDecimal(ingresoHorasNocturnas(rhEmpleadoCargo.getIdEmpleadoCargo(), rhPeriodoGestion.getIdPeriodoGestion()), 2));
            rhPlanillaSueldos.setHorasDomingo(rhVariacion.getHorasDomingo());
            rhPlanillaSueldos.setIngresoHorasDomingo(NumberUtils.redondeaBigDecimal(ingresoHorasDomingo(rhEmpleadoCargo.getIdEmpleadoCargo(), rhPeriodoGestion.getIdPeriodoGestion()), 2));

            rhPlanillaSueldos.setPorcentajeAntiguedad(porcentajeAntiguedad(aniosMesesDiasTrabajados(rhPeriodoGestion, rhEmpleadoCargo)));
            rhPlanillaSueldos.setBonoProduccion(rhCriterioDeIngresoEmpleadoCargoService.encuentraBonoProduccion(rhEmpleadoCargo.getIdEmpleadoCargo(), rhPeriodoGestion.getIdPeriodoGestion()).getMontoCriterioDeIngreso());
            rhPlanillaSueldos.setOtrosBonos(NumberUtils.redondeaBigDecimal(rhCriterioDeIngresoEmpleadoCargoService.sumaOtrosBonos(rhEmpleadoCargo.getIdEmpleadoCargo(), rhPeriodoGestion.getIdPeriodoGestion()).add(obtieneOtrosBonos(rhEmpleadoCargo.getIdEmpleadoCargo(), rhPeriodoGestion.getIdPeriodoGestion())), 2));
            rhPlanillaSueldos.setTotalGanado(NumberUtils.redondeaBigDecimal(rhPlanillaSueldos.getBonoAntiguedad()
                    .add(rhPlanillaSueldos.getIngresoDiasTrabajados())
                    .add(rhPlanillaSueldos.getIngresoHorasExtras())
                    .add(rhPlanillaSueldos.getIngresoHorasNocturnas())
                    .add(rhPlanillaSueldos.getIngresoHorasDomingo())
                    .add(rhPlanillaSueldos.getBonoProduccion())
                    .add(rhPlanillaSueldos.getIngresoDiasDomingo())
                    .add(rhPlanillaSueldos.getOtrosBonos()), 2));
            rhPlanillaSueldos.setAfp(calculoAfp(rhEmpleadoCargo.getIdEmpleadoCargo(), rhPeriodoGestion.getIdPeriodoGestion(), rhPlanillaSueldos.getTotalGanado()));
            rhPlanillaSueldos.setAporteNacionalSolidario(calculoAporteNacionalSolidario(rhEmpleadoCargo.getIdEmpleadoCargo(), rhPeriodoGestion.getIdPeriodoGestion(), rhPlanillaSueldos.getTotalGanado()));
            rhPlanillaSueldos.setRcIva(rhPlanillaImpositivaService.rhPlanillaImpositivaFactory(null, rhPlanillaSueldos.getTotalGanado(), rhEmpleadoCargo.getIdEmpleadoCargo(), rhPeriodoGestion.getIdPeriodoGestion()).getImpuestoRcIva());
            rhPlanillaSueldos.setOtrosDescuentos(NumberUtils.redondeaBigDecimal((rhDescuentoEmpleadoCargoService.otrosDescuentoPorEmpleadoYPeriodo(rhEmpleadoCargo.getIdEmpleadoCargo(), rhPeriodoGestion.getIdPeriodoGestion())).add(descuentoDiasFalta(rhEmpleadoCargo.getIdEmpleadoCargo(), rhPeriodoGestion.getIdPeriodoGestion()).add(descuentoDiasMulta(rhEmpleadoCargo.getIdEmpleadoCargo(), rhPeriodoGestion.getIdPeriodoGestion()))), 2));
            rhPlanillaSueldos.setTotalDescuentos(NumberUtils.redondeaBigDecimal(rhPlanillaSueldos.getAfp().add(rhPlanillaSueldos.getAporteNacionalSolidario()).add(rhPlanillaSueldos.getRcIva()).add(rhPlanillaSueldos.getOtrosDescuentos()), 2));
            rhPlanillaSueldos.setLiquidoPagable(NumberUtils.redondeaBigDecimal(rhPlanillaSueldos.getTotalGanado().subtract(rhPlanillaSueldos.getTotalDescuentos()), 2));
            return rhPlanillaSueldos;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhPlanillaSueldos persistRhPlanillaSueldos(RhPlanillaSueldos rhPlanillaSueldos) throws Exception {
        try {
            rhPlanillaSueldos.setIdPlanillaSueldos(null);
            dao.create(rhPlanillaSueldos);
            return rhPlanillaSueldos;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RhPlanillaSueldos> cargaPlanillaSueldosPorIdPeriodoGestionNoContabilizada(Long idPeriodoGestion) throws Exception {
        try {
            List<RhPlanillaSueldos> listaPlanillaSueldos = dao.find(""
                    + "select j "
                    + "from RhPlanillaSueldos j "
                    + "where j.fechaBaja is null and j.rhPeriodoGestion.parEstadoPeriodoGestion.codigo!='" + EnumEstadoPeriodoGestion.CONTABILIZADO.getCodigo() + "' "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            return listaPlanillaSueldos;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public BigDecimal calculoAfp(Long idEmpleadoCargo, Long idPeriodoGestion, BigDecimal totalGanado) throws Exception {
        try {
            RhParametros rhParametros = rhParametrosService.obtieneRhParametrosPorPeriodoGestion(idPeriodoGestion);
            RhEmpleadoCargo rhEmpleadoCargo = rhEmpleadoCargoService.getRhEmpleadoCargoById(idEmpleadoCargo);
            BigDecimal fci = totalGanado.multiply((rhParametros.getFondoCapitalizacionIndividual().divide(CIEN)));
            BigDecimal rc = totalGanado.multiply(rhParametros.getRiesgoComun().divide(CIEN));
            BigDecimal comision = totalGanado.multiply(rhParametros.getComision().divide(CIEN));
            BigDecimal aporteLaboralSolidario = totalGanado.multiply(rhParametros.getAporteSolidarioLaborales().divide(CIEN));
            BigDecimal afp = new BigDecimal(BigInteger.ZERO);
            log.info("empleado-------" + rhEmpleadoCargo.getRhEmpleado().getNombreCompleto());
            log.info("fci------------" + fci);
            log.info("rc-------------" + rc);
            log.info("comision-------" + comision);
            log.info("aporteLaboral--" + aporteLaboralSolidario);
            log.info("codigoPension--" + rhEmpleadoCargo.getRhEmpleado().getParCondicionPension().getCodigo());
            log.info("tipoContrato---" + rhEmpleadoCargo.getParTipoContratoEmpleado().getCodigo());
            log.info("Aporta---------" + rhEmpleadoCargo.getRhEmpleado().getAporta());
            log.info("edad-----------" + edadEmpleado(rhEmpleadoCargo, idPeriodoGestion));
            if (rhEmpleadoCargo.getRhEmpleado().getParCondicionPension().getCodigo().equals(EnumCondicionPension.NINGUNO.getCodigo()) && rhEmpleadoCargo.getParTipoContratoEmpleado().getCodigo().equals(EnumTipoContratoEmpleado.CONTRATO_INDEFINIDO.getCodigo())) {
                if (edadEmpleado(rhEmpleadoCargo, idPeriodoGestion) <= SESENTA_Y_CINCO) {
                    afp = fci.add(rc).add(comision).add(aporteLaboralSolidario);
                } else {
                    afp = fci.add(comision).add(aporteLaboralSolidario);
                }
            } else if ((rhEmpleadoCargo.getRhEmpleado().getParCondicionPension().getCodigo().equals(EnumCondicionPension.JUBILADO.getCodigo()) || rhEmpleadoCargo.getRhEmpleado().getParCondicionPension().getCodigo().equals(EnumCondicionPension.R_VEJEZ.getCodigo())) && rhEmpleadoCargo.getParTipoContratoEmpleado().getCodigo().equals(EnumTipoContratoEmpleado.CONTRATO_INDEFINIDO.getCodigo())) {
                if (rhEmpleadoCargo.getRhEmpleado().getAporta() && (edadEmpleado(rhEmpleadoCargo, idPeriodoGestion) <= SESENTA_Y_CINCO)) {
                    afp = fci.add(rc).add(comision).add(aporteLaboralSolidario);
                } else if (rhEmpleadoCargo.getRhEmpleado().getAporta() && edadEmpleado(rhEmpleadoCargo, idPeriodoGestion) > SESENTA_Y_CINCO) {
                    afp = fci.add(comision).add(aporteLaboralSolidario);
                } else if (!rhEmpleadoCargo.getRhEmpleado().getAporta() && edadEmpleado(rhEmpleadoCargo, idPeriodoGestion) <= SESENTA_Y_CINCO) {
                    afp = rc.add(comision).add(aporteLaboralSolidario);
                } else if (!rhEmpleadoCargo.getRhEmpleado().getAporta() && edadEmpleado(rhEmpleadoCargo, idPeriodoGestion) > SESENTA_Y_CINCO) {
                    afp = comision.add(aporteLaboralSolidario);
                }
            } else if (rhEmpleadoCargo.getRhEmpleado().getParCondicionPension().getCodigo().equals(EnumCondicionPension.INVALIDEZ.getCodigo()) && rhEmpleadoCargo.getParTipoContratoEmpleado().getCodigo().equals(EnumTipoContratoEmpleado.CONTRATO_INDEFINIDO.getCodigo())) {
                afp = fci.add(rc).add(comision).add(aporteLaboralSolidario);
            }
            return NumberUtils.redondeaBigDecimal(afp, 2);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public BigDecimal calculoAporteNacionalSolidario(Long idEmpleadoCargo, Long idPeriodoGestion, BigDecimal totalGanado) throws Exception {
        try {
            BigDecimal aporteNacSoli = CERO;
            BigDecimal dif13 = totalGanado.subtract(TRECE_MIL);
            BigDecimal dif25 = totalGanado.subtract(VEINTICINCO_MIL);
            BigDecimal dif35 = totalGanado.subtract(TREINTA_Y_CINCO_MIL);
            if (dif13.compareTo(CERO) == 1) //totalGanado - 13000 > 0
            {
                dif13 = dif13.multiply(CERO_PUNTO_CERO_UNO);// diferencia = diferencia * 0.01
                aporteNacSoli = aporteNacSoli.add(dif13); // APNASOL=APNASOL+diferencia
            }
            if (dif25.compareTo(CERO) == 1) //totalGanado - 25000 > 0
            {
                dif25 = dif25.multiply(CERO_PUNTO_CERO_CINCO);// diferencia = diferencia * 0.01
                aporteNacSoli = aporteNacSoli.add(dif25); // APNASOL=APNASOL+diferencia
            }
            if (dif35.compareTo(CERO) == 1) //totalGanado - 35000 > 0
            {
                dif35 = dif35.multiply(CERO_PUNTO_DIEZ);// diferencia = diferencia * 0.10
                aporteNacSoli = aporteNacSoli.add(dif35); // APNASOL=APNASOL+diferencia
            }
            return NumberUtils.redondeaBigDecimal(aporteNacSoli, 2);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public int edadEmpleado(RhEmpleadoCargo rhEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.getRhPeriodoGestionById(idPeriodoGestion);
            String aniosTrabajados = DateUtils.aniosMesesDiasEntreDosFechas(rhEmpleadoCargo.getRhEmpleado().getFechaNacimiento(), rhPeriodoGestion.getFin());
            log.info("ANIOS TRABAJADOS----- " + aniosTrabajados);
            String[] datos = aniosTrabajados.split("-");
            int anio = Integer.parseInt(datos[0]);
            if (anio == 65) {
                if (Integer.parseInt(datos[1]) > 0) {
                    anio++;
                } else if (Integer.parseInt(datos[2]) > 0) {
                    anio++;
                }
            }
            return anio;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean existePlanillaSueldosPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {
            List<RhPlanillaSueldos> listaPlanillaSueldos = dao.find(""
                    + "select j "
                    + "from RhPlanillaSueldos j "
                    + "where j.fechaBaja is null "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + " ");
            return !listaPlanillaSueldos.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal ingresoHorasExtras(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal ingresoHorasExtras;
            RhEmpleadoCargo rhEmpleadoCargo = rhEmpleadoCargoService.getRhEmpleadoCargoById(idEmpleadoCargo);
            RhVariacion rhVariacion = rhVariacionService.obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(idEmpleadoCargo, idPeriodoGestion);
            ingresoHorasExtras = ((rhEmpleadoCargo.getSueldo().divide(DOSCIENTOS_CUARENTA, 5, RoundingMode.HALF_UP)).multiply(rhVariacion.getHorasExtras())).multiply(DOS);
            return ingresoHorasExtras;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal ingresoHorasNocturnas(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal ingresoHorasNocturnas;
            RhEmpleadoCargo rhEmpleadoCargo = rhEmpleadoCargoService.getRhEmpleadoCargoById(idEmpleadoCargo);
            RhVariacion rhVariacion = rhVariacionService.obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(idEmpleadoCargo, idPeriodoGestion);
            RhParametros rhParametros = rhParametrosService.obtieneRhParametrosPorPeriodoGestion(idPeriodoGestion);
            BigDecimal porcentajeHorasNocturnas = new BigDecimal(rhParametros.getHorasNocturnas());
            porcentajeHorasNocturnas = porcentajeHorasNocturnas.divide(CIEN);
            ingresoHorasNocturnas = ((rhEmpleadoCargo.getSueldo().divide(DOSCIENTOS_CUARENTA, 5, RoundingMode.HALF_UP)).multiply(rhVariacion.getHorasNocturnas())).multiply(porcentajeHorasNocturnas);
            return ingresoHorasNocturnas;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal ingresoHorasDomingo(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal ingresoHorasDomingo;
            RhEmpleadoCargo rhEmpleadoCargo = rhEmpleadoCargoService.getRhEmpleadoCargoById(idEmpleadoCargo);
            RhVariacion rhVariacion = rhVariacionService.obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(idEmpleadoCargo, idPeriodoGestion);
            ingresoHorasDomingo = ((rhEmpleadoCargo.getSueldo().divide(DOSCIENTOS_CUARENTA, 5, RoundingMode.HALF_UP)).multiply(rhVariacion.getHorasDomingo())).multiply(DOS);
            return ingresoHorasDomingo;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal ingresoDiasDomingo(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal ingresoDiasDomingo;
            RhEmpleadoCargo rhEmpleadoCargo = rhEmpleadoCargoService.getRhEmpleadoCargoById(idEmpleadoCargo);
            RhVariacion rhVariacion = rhVariacionService.obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(idEmpleadoCargo, idPeriodoGestion);
            ingresoDiasDomingo = ((rhEmpleadoCargo.getSueldo().divide(TREINTA, 5, RoundingMode.HALF_UP)).multiply(rhVariacion.getDiasDomingo())).multiply(DOS);
            return ingresoDiasDomingo;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal ingresoDiasAjuste(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal ingresoDiasAjuste;
            RhEmpleadoCargo rhEmpleadoCargo = rhEmpleadoCargoService.getRhEmpleadoCargoById(idEmpleadoCargo);
            RhVariacion rhVariacion = rhVariacionService.obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(idEmpleadoCargo, idPeriodoGestion);
            ingresoDiasAjuste = (rhEmpleadoCargo.getSueldo().divide(TREINTA, 5, RoundingMode.HALF_UP)).multiply(rhVariacion.getDiasAjuste());
            return ingresoDiasAjuste;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal ingresoHorasAjuste(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal ingresoHorasAjuste;
            RhEmpleadoCargo rhEmpleadoCargo = rhEmpleadoCargoService.getRhEmpleadoCargoById(idEmpleadoCargo);
            RhVariacion rhVariacion = rhVariacionService.obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(idEmpleadoCargo, idPeriodoGestion);
            ingresoHorasAjuste = (rhEmpleadoCargo.getSueldo().divide(DOSCIENTOS_CUARENTA, 5, RoundingMode.HALF_UP)).multiply(rhVariacion.getHorasAjuste());
            return ingresoHorasAjuste;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal ingresoDiasFeriado(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal ingresoDiasFeriado;
            RhEmpleadoCargo rhEmpleadoCargo = rhEmpleadoCargoService.getRhEmpleadoCargoById(idEmpleadoCargo);
            RhVariacion rhVariacion = rhVariacionService.obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(idEmpleadoCargo, idPeriodoGestion);
            ingresoDiasFeriado = (rhEmpleadoCargo.getSueldo().divide(TREINTA, 5, RoundingMode.HALF_UP)).multiply(rhVariacion.getDiasFeriado()).multiply(DOS);
            return ingresoDiasFeriado;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal ingresoHorasFeriado(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal ingresoHorasFeriado;
            RhEmpleadoCargo rhEmpleadoCargo = rhEmpleadoCargoService.getRhEmpleadoCargoById(idEmpleadoCargo);
            RhVariacion rhVariacion = rhVariacionService.obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(idEmpleadoCargo, idPeriodoGestion);
            ingresoHorasFeriado = (rhEmpleadoCargo.getSueldo().divide(DOSCIENTOS_CUARENTA, 5, RoundingMode.HALF_UP)).multiply(rhVariacion.getHorasFeriado()).multiply(DOS);
            return ingresoHorasFeriado;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal ingresoDiasDescanso(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal ingresoDiasDescanso;
            RhEmpleadoCargo rhEmpleadoCargo = rhEmpleadoCargoService.getRhEmpleadoCargoById(idEmpleadoCargo);
            RhVariacion rhVariacion = rhVariacionService.obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(idEmpleadoCargo, idPeriodoGestion);
            ingresoDiasDescanso = (rhEmpleadoCargo.getSueldo().divide(TREINTA, 5, RoundingMode.HALF_UP)).multiply(rhVariacion.getDiasDescanso());
            return ingresoDiasDescanso;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal ingresoDiasDescansoTrabajado(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal ingresoDiasDescansoTrabajado;
            RhEmpleadoCargo rhEmpleadoCargo = rhEmpleadoCargoService.getRhEmpleadoCargoById(idEmpleadoCargo);
            RhVariacion rhVariacion = rhVariacionService.obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(idEmpleadoCargo, idPeriodoGestion);
            ingresoDiasDescansoTrabajado = (rhEmpleadoCargo.getSueldo().divide(TREINTA, 5, RoundingMode.HALF_UP)).multiply(rhVariacion.getDiasDescansoTrabajados());
            return ingresoDiasDescansoTrabajado;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal descuentoDiasFalta(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal ingresoDiasFalta = new BigDecimal(BigInteger.ZERO);
            RhEmpleadoCargo rhEmpleadoCargo = rhEmpleadoCargoService.getRhEmpleadoCargoById(idEmpleadoCargo);
            RhVariacion rhVariacion = rhVariacionService.obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(idEmpleadoCargo, idPeriodoGestion);
            if (rhVariacion.getDiasDeFalta().compareTo(CERO) == 1) {
                ingresoDiasFalta = ((rhEmpleadoCargo.getSueldo().divide(TREINTA, 5, RoundingMode.HALF_UP)).multiply(rhVariacion.getDiasDeFalta())).add(rhEmpleadoCargo.getSueldo().divide(TREINTA, 5, RoundingMode.HALF_UP));
            }
            return ingresoDiasFalta;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal descuentoDiasMulta(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal ingresoDiasMulta = new BigDecimal(BigInteger.ZERO);
            RhEmpleadoCargo rhEmpleadoCargo = rhEmpleadoCargoService.getRhEmpleadoCargoById(idEmpleadoCargo);
            RhVariacion rhVariacion = rhVariacionService.obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(idEmpleadoCargo, idPeriodoGestion);
            if (rhVariacion.getDiasDeMulta().compareTo(CERO) == 1) {
                ingresoDiasMulta = ((rhEmpleadoCargo.getSueldo().divide(TREINTA, 5, RoundingMode.HALF_UP)).multiply(rhVariacion.getDiasDeMulta())).add(rhEmpleadoCargo.getSueldo().divide(TREINTA, 5, RoundingMode.HALF_UP));
            }
            return ingresoDiasMulta;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal obtieneOtrosBonos(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal otrosDescuentos = new BigDecimal(BigInteger.ZERO);
            otrosDescuentos = ingresoDiasAjuste(idEmpleadoCargo, idPeriodoGestion).add(ingresoDiasFeriado(idEmpleadoCargo, idPeriodoGestion)).add(ingresoHorasAjuste(idEmpleadoCargo, idPeriodoGestion)).add(ingresoHorasFeriado(idEmpleadoCargo, idPeriodoGestion)).add(ingresoDiasDescanso(idEmpleadoCargo, idPeriodoGestion)).add(ingresoDiasDescansoTrabajado(idEmpleadoCargo, idPeriodoGestion));
            return otrosDescuentos;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public List<RhPlanillaSueldos> guardaRhPlanillaSueldos(List<RhPlanillaSueldos> listaPlanillaSueldos) throws Exception {
        try {
            List<RhPlanillaSueldos> listaGuardada = new ArrayList<>();
//            ParEstadoLiquidacion parEstadoLiquidacion = parValorService.obtieneParEstadoLiquidacion(EnumEstadoLiquidacion.LIQUIDADO.getCodigo());
            RhPlanillaSueldos rhPlanillaSueldosGuardado;
            Long idPeriodoGestion = new Long("0");
            for (RhPlanillaSueldos rhPlanillaSueldos : listaPlanillaSueldos) {
                rhPlanillaSueldosGuardado = new RhPlanillaSueldos();
//                rhPlanillaSueldos.setParEstadoLiquidacion(parEstadoLiquidacion);
                idPeriodoGestion = rhPlanillaSueldos.getRhPeriodoGestion().getIdPeriodoGestion();
                rhPlanillaSueldosGuardado = persistRhPlanillaSueldos(rhPlanillaSueldos);
                listaGuardada.add(rhPlanillaSueldosGuardado);
            }

            //Autor:Henrry, estas lineas de codigo guarda planilla impositiva.
            List<RhPlanillaImpositiva> listaRhPlanillaImpositiva = rhPlanillaImpositivaService.generaPlanillaImpositiva(idPeriodoGestion);
            rhPlanillaImpositivaService.guardaPlanillaImpositiva(listaRhPlanillaImpositiva);
            //FIN

            //Autor:Henrry, estas lineas de codigo permiten cambiar el estado de la gestion.
            if (listaPlanillaSueldos.size() > 0) {
                ParEstadoPeriodoGestion parEstadoPeriodoGestion = parValorService.obtieneParEstadoPeriodoGestion(EnumEstadoPeriodoGestion.LIQUIDADO.getCodigo());
                RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.getRhPeriodoGestionById(idPeriodoGestion);
                rhPeriodoGestion.setParEstadoPeriodoGestion(parEstadoPeriodoGestion);
                rhPeriodoGestionService.mergeRhPeriodoGestion(rhPeriodoGestion);
            }
            //FIN
            return listaGuardada;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deletePlanillaSueldos(Long idPeriodoGestion) throws Exception {
        log.info("*********Borrando Planilla de Sueldos**************");
        List<RhPlanillaSueldos> lista = cargaPlanillaSueldosPorIdPeriodoGestionNoContabilizada(idPeriodoGestion);
        for (RhPlanillaSueldos sueldo : lista) {
            dao.delete(sueldo);
        }

    }

    @Override
    public List<RhPlanillaSueldos> cargaPlanillaSueldosPorIdPeriodoGestionEstadosLiquidadoContabilizado(Long idPeriodoGestion) throws Exception {
        try {
            List<RhPlanillaSueldos> listaPlanillaSueldos = dao.find(""
                    + "select h "
                    + "from RhPlanillaSueldos h "
                    + "where h.fechaBaja is null "
                    + "and (h.rhPeriodoGestion.parEstadoPeriodoGestion.codigo!='" + EnumEstadoPeriodoGestion.LIQUIDADO.getCodigo() + "' or h.rhPeriodoGestion.parEstadoPeriodoGestion.codigo!='" + EnumEstadoPeriodoGestion.CONTABILIZADO.getCodigo() + "') "
                    + "and h.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            return listaPlanillaSueldos;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RhTransferenciasBancariasPojo> listaTransferencias(Long idPeriodoGestion) throws Exception {
        try {
            List<RhTransferenciasBancariasPojo> listaTransferencia = new ArrayList<>();
            List<RhPlanillaSueldos> lista = cargaPlanillaSueldosPorIdPeriodoGestionEstadosLiquidadoContabilizado(idPeriodoGestion);
            RhTransferenciasBancariasPojo rhTransferenciasBancariasPojo;
            for (RhPlanillaSueldos rhPlanillaSueldos : lista) {
                rhTransferenciasBancariasPojo = new RhTransferenciasBancariasPojo();
                rhTransferenciasBancariasPojo.setIdEmpleadoCargo(rhPlanillaSueldos.getRhEmpleadoCargo().getIdEmpleadoCargo());
                rhTransferenciasBancariasPojo.setNombreCompleto(rhPlanillaSueldos.getRhEmpleadoCargo().getRhEmpleado().getNombreCompleto());
                rhTransferenciasBancariasPojo.setNroCuenta(rhPlanillaSueldos.getRhEmpleadoCargo().getRhEmpleado().getNumeroCuentaBancaria());
                rhTransferenciasBancariasPojo.setMontoAbonar(rhPlanillaSueldos.getLiquidoPagable());
                listaTransferencia.add(rhTransferenciasBancariasPojo);
            }
            return listaTransferencia;
        } catch (Exception e) {
            throw e;
        }
    }

}
