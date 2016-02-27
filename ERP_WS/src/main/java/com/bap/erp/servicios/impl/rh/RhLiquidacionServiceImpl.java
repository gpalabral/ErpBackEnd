package com.bap.erp.servicios.impl.rh;

import com.bap.erp.enums.EnumEstadoPeriodoGestion;
import com.bap.erp.modelo.par.ParEstadoPeriodoGestion;
import com.bap.erp.modelo.rh.RhPeriodoGestion;
import com.bap.erp.servicios.par.ParValorService;
import com.bap.erp.servicios.rh.RhLiquidacionService;
import com.bap.erp.servicios.rh.RhPeriodoGestionService;
import com.bap.erp.servicios.rh.RhPlanillaImpositivaService;
import com.bap.erp.servicios.rh.RhPlanillaSueldosService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RhLiquidacionServiceImpl implements RhLiquidacionService {

    @Autowired
    private RhPlanillaSueldosService rhPlanillaSueldosService;

    @Autowired
    private RhPlanillaImpositivaService rhPlanillaImpositivaService;

    @Autowired
    private RhPeriodoGestionService rhPeriodoGestionService;

    @Autowired
    private ParValorService parValorService;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhLiquidacionServiceImpl.class);

    @Override
    @Transactional
    public void eliminarLiquidacion(Long idPeriodoGestion) throws Exception {

        try {
            log.info("Borrando....LIQUIDACION para:  " + idPeriodoGestion);
            rhPlanillaSueldosService.deletePlanillaSueldos(idPeriodoGestion);
            rhPlanillaImpositivaService.deleteRhPlanillaImpositiva(idPeriodoGestion);

            //Autor:Henrry, estas lineas de codigo permiten cambiar el estado de la gestion.
            ParEstadoPeriodoGestion parEstadoPeriodoGestion = parValorService.obtieneParEstadoPeriodoGestion(EnumEstadoPeriodoGestion.VIGENTE.getCodigo());
            RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.getRhPeriodoGestionById(idPeriodoGestion);
            rhPeriodoGestion.setParEstadoPeriodoGestion(parEstadoPeriodoGestion);
            rhPeriodoGestionService.mergeRhPeriodoGestion(rhPeriodoGestion);
            //FIN

        } catch (Exception e) {
            throw e;
        }

    }

}
