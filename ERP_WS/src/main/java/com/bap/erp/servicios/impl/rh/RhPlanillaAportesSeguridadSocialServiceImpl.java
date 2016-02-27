package com.bap.erp.servicios.impl.rh;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.utils.NumberUtils;
import com.bap.erp.enums.EnumEstadoPeriodoGestion;
import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import com.bap.erp.modelo.rh.RhParametros;
import com.bap.erp.modelo.rh.RhPeriodoGestion;
import com.bap.erp.modelo.rh.RhPlanillaAportesSeguridadSocial;
import com.bap.erp.servicios.rh.RhEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhParametrosService;
import com.bap.erp.servicios.rh.RhPeriodoGestionService;
import com.bap.erp.servicios.rh.RhPlanillaAportesSeguridadSocialService;
import com.bap.erp.servicios.rh.RhPlanillaSueldosService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RhPlanillaAportesSeguridadSocialServiceImpl implements RhPlanillaAportesSeguridadSocialService {

    private final BigDecimal CIEN = new BigDecimal(100L);

    @Autowired
    public RhPeriodoGestionService rhPeriodoGestionService;

    @Autowired
    public RhEmpleadoCargoService rhEmpleadoCargoService;

    @Autowired
    public RhPlanillaSueldosService rhPlanillaSueldosService;

    @Autowired
    public RhParametrosService rhParametrosService;

    IGenericDao<RhPlanillaAportesSeguridadSocial> dao;

    @Autowired
    public void setDao(IGenericDao<RhPlanillaAportesSeguridadSocial> daoToSet) {
        dao = daoToSet;
        dao.setClazz(RhPlanillaAportesSeguridadSocial.class);
    }

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhPlanillaAportesSeguridadSocialServiceImpl.class);

    @Override
    public boolean existePlanillaAportesSeguridadSocialPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {
            List<RhPlanillaAportesSeguridadSocial> lista = dao.find(""
                    + "select j "
                    + "from RhPlanillaAportesSeguridadSocial j "
                    + "where j.fechaBaja is null "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            return !lista.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RhPlanillaAportesSeguridadSocial> cargaPlanillaAportesSeguridadSocialPorPeriodoGestion(Long idPeriodoGestion) throws Exception {
        try {
            List<RhPlanillaAportesSeguridadSocial> lista = dao.find(""
                    + "select j "
                    + "from RhPlanillaAportesSeguridadSocial j "
                    + "where j.fechaBaja is null "
                    + "and (j.rhPeriodoGestion.parEstadoPeriodoGestion.codigo!='" + EnumEstadoPeriodoGestion.LIQUIDADO.getCodigo() + "' or j.rhPeriodoGestion.parEstadoPeriodoGestion.codigo!='" + EnumEstadoPeriodoGestion.CONTABILIZADO.getCodigo() + "') "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RhPlanillaAportesSeguridadSocial> generaPlanillaAportesSeguridadSocial(Long idPeriodoGestion) throws Exception {
        try {
            List<RhPlanillaAportesSeguridadSocial> listaPlanillaAportesSeguridadSocial = new ArrayList<RhPlanillaAportesSeguridadSocial>();
            RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.encuentraRhPeriodoGestionPorId(idPeriodoGestion);
            List<RhEmpleadoCargo> listaEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoVigente(idPeriodoGestion);
            RhPlanillaAportesSeguridadSocial rhPlanillaAportesSeguridadSocial;
            for (RhEmpleadoCargo rhEmpleadoCargo : listaEmpleadoCargo) {
                rhPlanillaAportesSeguridadSocial = new RhPlanillaAportesSeguridadSocial();
                rhPlanillaAportesSeguridadSocial.setRhEmpleadoCargo(rhEmpleadoCargo);
                rhPlanillaAportesSeguridadSocial.setRhPeriodoGestion(rhPeriodoGestion);
                rhPlanillaAportesSeguridadSocial.setTotalGanado(rhPlanillaSueldosService.rhPlanillaSueldosFactory(rhEmpleadoCargo, rhPeriodoGestion).getTotalGanado());
                rhPlanillaAportesSeguridadSocial = rhPlanillaAportesSeguridadSocialFactory(rhPlanillaAportesSeguridadSocial);
                listaPlanillaAportesSeguridadSocial.add(rhPlanillaAportesSeguridadSocial);
            }
            return listaPlanillaAportesSeguridadSocial;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhPlanillaAportesSeguridadSocial rhPlanillaAportesSeguridadSocialFactory(RhPlanillaAportesSeguridadSocial rhPlanillaAportesSeguridadSocial) throws Exception {
        try {
            RhParametros rhParametros = rhParametrosService.obtieneRhParametrosPorPeriodoGestion(rhPlanillaAportesSeguridadSocial.getRhPeriodoGestion().getIdPeriodoGestion());
            BigDecimal riesgoComun = rhParametros.getRiesgoComun();
            BigDecimal aporteSolidario = new BigDecimal(rhParametros.getAporteSolidarioPatronales());
            BigDecimal provivienda = new BigDecimal(rhParametros.getProvivienda());
            BigDecimal cajaDeSalud = new BigDecimal(rhParametros.getCajaSalud());
            rhPlanillaAportesSeguridadSocial.setSipPatronal(NumberUtils.redondeaBigDecimal(rhPlanillaAportesSeguridadSocial.getTotalGanado().multiply(riesgoComun.divide(CIEN, 5, RoundingMode.HALF_UP)), 2));
            rhPlanillaAportesSeguridadSocial.setSipLaboral(NumberUtils.redondeaBigDecimal(rhPlanillaSueldosService.calculoAfp(rhPlanillaAportesSeguridadSocial.getRhEmpleadoCargo().getIdEmpleadoCargo(), rhPlanillaAportesSeguridadSocial.getRhPeriodoGestion().getIdPeriodoGestion(), rhPlanillaAportesSeguridadSocial.getTotalGanado()), 2));
            rhPlanillaAportesSeguridadSocial.setSipSubtotal(NumberUtils.redondeaBigDecimal(rhPlanillaAportesSeguridadSocial.getSipPatronal().add(rhPlanillaAportesSeguridadSocial.getSipLaboral()), 2));
            rhPlanillaAportesSeguridadSocial.setAnsPatronal(NumberUtils.redondeaBigDecimal(rhPlanillaAportesSeguridadSocial.getTotalGanado().multiply(aporteSolidario.divide(CIEN, 5, RoundingMode.HALF_UP)), 2));
            rhPlanillaAportesSeguridadSocial.setAnsLaboral(NumberUtils.redondeaBigDecimal(rhPlanillaSueldosService.calculoAporteNacionalSolidario(rhPlanillaAportesSeguridadSocial.getRhEmpleadoCargo().getIdEmpleadoCargo(), rhPlanillaAportesSeguridadSocial.getRhPeriodoGestion().getIdPeriodoGestion(), rhPlanillaAportesSeguridadSocial.getTotalGanado()), 2));
            rhPlanillaAportesSeguridadSocial.setAnsSubtotal(NumberUtils.redondeaBigDecimal(rhPlanillaAportesSeguridadSocial.getAnsPatronal().add(rhPlanillaAportesSeguridadSocial.getAnsLaboral()), 2));
            rhPlanillaAportesSeguridadSocial.setProvivienda(NumberUtils.redondeaBigDecimal(rhPlanillaAportesSeguridadSocial.getTotalGanado().multiply(provivienda.divide(CIEN, 5, RoundingMode.HALF_UP)), 2));
            rhPlanillaAportesSeguridadSocial.setCajaSalud(NumberUtils.redondeaBigDecimal(rhPlanillaAportesSeguridadSocial.getTotalGanado().multiply(cajaDeSalud.divide(CIEN, 5, RoundingMode.HALF_UP)), 2));
            rhPlanillaAportesSeguridadSocial.setTotalGeneral(NumberUtils.redondeaBigDecimal(rhPlanillaAportesSeguridadSocial.getSipSubtotal().add(rhPlanillaAportesSeguridadSocial.getAnsSubtotal().add(rhPlanillaAportesSeguridadSocial.getProvivienda().add(rhPlanillaAportesSeguridadSocial.getCajaSalud()))), 2));
            return rhPlanillaAportesSeguridadSocial;
        } catch (Exception e) {
            throw e;
        }
    }

}
