package com.bap.erp.servicios.impl.rh;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.utils.NumberUtils;
import com.bap.erp.enums.EnumEstadoLiquidacion;
import com.bap.erp.enums.EnumEstadoPeriodoGestion;
import com.bap.erp.modelo.par.ParEstadoLiquidacion;
import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import com.bap.erp.modelo.rh.RhParametros;
import com.bap.erp.modelo.rh.RhPeriodoGestion;
import com.bap.erp.modelo.rh.RhPlanillaImpositiva;
import com.bap.erp.modelo.rh.RhRcIva;
import com.bap.erp.servicios.par.ParValorService;
import com.bap.erp.servicios.rh.RhDescuentoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhParametrosService;
import com.bap.erp.servicios.rh.RhPeriodoGestionService;
import com.bap.erp.servicios.rh.RhPlanillaImpositivaService;
import com.bap.erp.servicios.rh.RhPlanillaSueldosService;
import com.bap.erp.servicios.rh.RhPrimasService;
import com.bap.erp.servicios.rh.RhRcIvaService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RhPlanillaImpositivaServiceImpl implements RhPlanillaImpositivaService {

    private final BigDecimal DOS = new BigDecimal(2L);
    private final BigDecimal DEBITO = new BigDecimal("0.13");
    private final BigDecimal CERO = new BigDecimal(0L);

    IGenericDao<RhPlanillaImpositiva> dao;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhPlanillaImpositivaServiceImpl.class);

    @Autowired
    public RhEmpleadoCargoService rhEmpleadoCargoService;
    @Autowired
    public RhPeriodoGestionService rhPeriodoGestionService;
    @Autowired
    public RhDescuentoEmpleadoCargoService rhDescuentoEmpleadoCargoService;
    @Autowired
    public RhRcIvaService rhRcIvaService;
    @Autowired
    public RhPlanillaSueldosService rhPlanillaSueldosService;
    @Autowired
    public RhPrimasService rhPrimasService;
    @Autowired
    public RhParametrosService rhParametrosService;
    @Autowired
    public ParValorService parValorService;

    @Autowired
    public void setDao(IGenericDao<RhPlanillaImpositiva> daoToSet) {
        dao = daoToSet;
        dao.setClazz(RhPlanillaImpositiva.class);
    }

    @Override
    public List<RhPlanillaImpositiva> generaPlanillaImpositiva(Long idPeriodoGestion) throws Exception {
        try {
            List<RhPlanillaImpositiva> listaPlanillaImpositiva = new ArrayList<RhPlanillaImpositiva>();
            RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.encuentraRhPeriodoGestionPorId(idPeriodoGestion);
            List<RhEmpleadoCargo> listaEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoVigente(idPeriodoGestion);
            RhPlanillaImpositiva rhPlanillaImpositiva;
            for (RhEmpleadoCargo rhEmpleadoCargo : listaEmpleadoCargo) {
                rhPlanillaImpositiva = new RhPlanillaImpositiva();
                rhPlanillaImpositiva.setRhEmpleadoCargo(rhEmpleadoCargo);
                rhPlanillaImpositiva.setRhPeriodoGestion(rhPeriodoGestion);
                rhPlanillaImpositiva.setTotalGanado(rhPlanillaSueldosService.rhPlanillaSueldosFactory(rhEmpleadoCargo, rhPeriodoGestion).getTotalGanado());
                rhPlanillaImpositiva = rhPlanillaImpositivaFactory(rhPlanillaImpositiva, rhPlanillaImpositiva.getTotalGanado(), rhEmpleadoCargo.getIdEmpleadoCargo(), idPeriodoGestion);
                listaPlanillaImpositiva.add(rhPlanillaImpositiva);
            }
            return listaPlanillaImpositiva;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhPlanillaImpositiva rhPlanillaImpositivaFactory(RhPlanillaImpositiva rhPlanillaImpositiva, BigDecimal totalGanado, Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            RhParametros rhParametros = rhParametrosService.obtieneRhParametrosPorPeriodoGestion(idPeriodoGestion);
            if (rhPlanillaImpositiva == null) {
                rhPlanillaImpositiva = new RhPlanillaImpositiva();
            }
            rhPlanillaImpositiva.setRhPrimas(rhPrimasService.obtieneRhPrimasPorIdEmpleadoCargo(idEmpleadoCargo, idPeriodoGestion));
            rhPlanillaImpositiva.setAfp(rhPlanillaSueldosService.calculoAfp(idEmpleadoCargo, idPeriodoGestion, totalGanado));
            rhPlanillaImpositiva.setAporteNacionalSolidario(rhPlanillaSueldosService.calculoAporteNacionalSolidario(idEmpleadoCargo, idPeriodoGestion, totalGanado));
            rhPlanillaImpositiva.setDosSalariosMinimos(rhParametros.getSueldoMinimoNacional().multiply(DOS));
            rhPlanillaImpositiva.setSueldoNeto(totalGanado.add(rhPlanillaImpositiva.getRhPrimas().getMontoPrima()).subtract(rhPlanillaImpositiva.getAfp()).subtract(rhPlanillaImpositiva.getAporteNacionalSolidario()));
            rhPlanillaImpositiva.setBaseImponible(obtieneBaseImponible(rhPlanillaImpositiva.getSueldoNeto(), rhPlanillaImpositiva.getDosSalariosMinimos()));
            rhPlanillaImpositiva.setComputoDosMinimosNacionales(rhPlanillaImpositiva.getDosSalariosMinimos().multiply(DEBITO));
            rhPlanillaImpositiva.setDebitoFiscal(rhPlanillaImpositiva.getBaseImponible().multiply(DEBITO));
            rhPlanillaImpositiva.setCreditoFiscal(obtieneValorForm110(idEmpleadoCargo, idPeriodoGestion));
            rhPlanillaImpositiva.setSaldoPeriodoAnterior(obtieneSaldoActualizado(idEmpleadoCargo, idPeriodoGestion));
            BigDecimal saldoImpuesto = saldoImpuesto(rhPlanillaImpositiva.getDebitoFiscal(), rhPlanillaImpositiva.getComputoDosMinimosNacionales(), rhPlanillaImpositiva.getCreditoFiscal(), idPeriodoGestion, idEmpleadoCargo);
            if (saldoImpuesto.compareTo(CERO) == 1) {
                rhPlanillaImpositiva.setImpuestoRcIva(saldoImpuesto);
                rhPlanillaImpositiva.setSaldoPeriodoSiguiente(CERO);
            } else {
                rhPlanillaImpositiva.setSaldoPeriodoSiguiente(saldoImpuesto.negate());
                rhPlanillaImpositiva.setImpuestoRcIva(CERO);
            }
            return rhPlanillaImpositiva;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal obtieneBaseImponible(BigDecimal sueldoNeto, BigDecimal dosSalariosMinimos) throws Exception {
        try {
            BigDecimal baseImponible;
            baseImponible = sueldoNeto.subtract(dosSalariosMinimos);
            if (baseImponible.compareTo(CERO) == 1) {
                return baseImponible;
            }
            return CERO;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal obtieneValorForm110(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            return rhRcIvaService.obtieneRhRcIvaPorIdEmpleadoCargo(idEmpleadoCargo, idPeriodoGestion).getMonto();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public BigDecimal obtieneSaldoActualizado(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal resultado = new BigDecimal("0");
            RhPeriodoGestion periodoGetion = rhPeriodoGestionService.getRhPeriodoGestionById(idPeriodoGestion);
            RhPeriodoGestion periodoGetionAnterior = rhPeriodoGestionService.obtieneRegistroPorPeriodoGestionAnterior(idPeriodoGestion);
            RhParametros rhParametro = rhParametrosService.obtieneRhParametrosPorPeriodoGestion(periodoGetion.getIdPeriodoGestion());
            if (periodoGetionAnterior != null) {
                RhParametros rhParametroAnterior = rhParametrosService.obtieneRhParametrosPorPeriodoGestion(periodoGetionAnterior.getIdPeriodoGestion());
                RhRcIva rhRcIva = rhRcIvaService.obtieneRhRcIvaPorIdEmpleadoCargo(idEmpleadoCargo, periodoGetionAnterior.getIdPeriodoGestion());
                if (rhRcIva != null) {
                    if (rhParametroAnterior.getTipoUfv().compareTo(rhParametro.getTipoUfv()) == 0) {
                        resultado = rhRcIva.getSaldoAcumulado();
                    } else {
                        resultado = (rhRcIva.getSaldoAcumulado().divide(rhParametroAnterior.getTipoUfv(), 5, RoundingMode.HALF_UP)).multiply(rhParametro.getTipoUfv());
                    }
                } else {
                    resultado = CERO;
                }
            }
            return resultado;

        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal saldoImpuesto(BigDecimal debitoFiscal, BigDecimal computo, BigDecimal creditoFiscal, Long idPeriodoGestion, Long idEmpleadoCargo) throws Exception {
        try {
            BigDecimal saldoImpuesto = ((debitoFiscal.subtract(computo)).subtract(creditoFiscal)).subtract(obtieneSaldoAnterior(idEmpleadoCargo, idPeriodoGestion));
            return NumberUtils.redondeaBigDecimal(saldoImpuesto, 2);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public BigDecimal obtieneSaldoAnterior(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal resultado = new BigDecimal("0");
            RhPeriodoGestion periodoGetionAnterior = rhPeriodoGestionService.obtieneRegistroPorPeriodoGestionAnterior(idPeriodoGestion);
            if (periodoGetionAnterior != null) {
                RhRcIva rhRcIva = rhRcIvaService.obtieneRhRcIvaPorIdEmpleadoCargo(idEmpleadoCargo, periodoGetionAnterior.getIdPeriodoGestion());
                if (rhRcIva != null) {
                    resultado = rhRcIva.getSaldoAcumulado();
                }else{
                    resultado = CERO;
                }
            }
            return resultado;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean existePlanillaImpositivaPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {
            List<RhPlanillaImpositiva> lista = dao.find(""
                    + "select j "
                    + "from RhPlanillaImpositiva j "
                    + "where j.fechaBaja is null "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            return !lista.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RhPlanillaImpositiva> cargaPlanillaImpositivaPorPeriodoGestionNoContabilizado(Long idPeriodoGestion) throws Exception {
        try {
            List<RhPlanillaImpositiva> lista = dao.find(""
                    + "select j "
                    + "from RhPlanillaImpositiva j "
                    + "where j.fechaBaja is null and j.rhPeriodoGestion.parEstadoPeriodoGestion.codigo='" + EnumEstadoPeriodoGestion.LIQUIDADO.getCodigo() + "' "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RhPlanillaImpositiva> cargaPlanillaImpositivaPorPeriodoGestionEstadosLiquidadoContabilizado(Long idPeriodoGestion) throws Exception {
        try {
            List<RhPlanillaImpositiva> lista = dao.find(""
                    + "select h "
                    + "from RhPlanillaImpositiva h "
                    + "where h.fechaBaja is null "
                    + "and (h.rhPeriodoGestion.parEstadoPeriodoGestion.codigo!='" + EnumEstadoPeriodoGestion.LIQUIDADO.getCodigo() + "' or h.rhPeriodoGestion.parEstadoPeriodoGestion.codigo!='" + EnumEstadoPeriodoGestion.CONTABILIZADO.getCodigo() + "') "
                    + "and h.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhPlanillaImpositiva persistRhPlanillaImpositiva(RhPlanillaImpositiva rhPlanillaImpositiva) throws Exception {
        try {
            rhPlanillaImpositiva.setIdPlanillaImpositiva(null);
            dao.create(rhPlanillaImpositiva);
            return rhPlanillaImpositiva;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RhPlanillaImpositiva> guardaPlanillaImpositiva(List<RhPlanillaImpositiva> listaPlanillaImpositiva) throws Exception {
        try {
            List<RhPlanillaImpositiva> listaGuardada = new ArrayList<RhPlanillaImpositiva>();
            ParEstadoLiquidacion parEstadoLiquidacion = parValorService.obtieneParEstadoLiquidacion(EnumEstadoLiquidacion.LIQUIDADO.getCodigo());
            RhPlanillaImpositiva rhPlanillaImpositivaGuardada;
            for (RhPlanillaImpositiva rhPlanillaImpositiva : listaPlanillaImpositiva) {
                rhPlanillaImpositiva.setParEstadoLiquidacion(parEstadoLiquidacion);
                rhPlanillaImpositivaGuardada = new RhPlanillaImpositiva();
                rhPlanillaImpositivaGuardada = persistRhPlanillaImpositiva(rhPlanillaImpositiva);
                listaGuardada.add(rhPlanillaImpositivaGuardada);
            }
            return listaGuardada;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteRhPlanillaImpositiva(Long idPeriodoGestion) throws Exception {
        log.info("*********Borrando Planilla Impositiva**************");
        List<RhPlanillaImpositiva> lista = cargaPlanillaImpositivaPorPeriodoGestionNoContabilizado(idPeriodoGestion);
        for (RhPlanillaImpositiva imp : lista) {
            dao.delete(imp);
        }
    }

}
