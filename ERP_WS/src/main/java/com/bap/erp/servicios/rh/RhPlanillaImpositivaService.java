package com.bap.erp.servicios.rh;

import com.bap.erp.modelo.rh.RhPlanillaImpositiva;
import java.math.BigDecimal;
import java.util.List;

public interface RhPlanillaImpositivaService {

//    void generaPlanillaImpositiva(Long idPeriodoGestion)throws Exception;    
    List<RhPlanillaImpositiva> generaPlanillaImpositiva(Long idPeriodoGestion) throws Exception;

    BigDecimal obtieneSaldoActualizado(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception;

    BigDecimal obtieneSaldoAnterior(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception;

    boolean existePlanillaImpositivaPorPeriodo(Long idPeriodoGestion) throws Exception;

    List<RhPlanillaImpositiva> cargaPlanillaImpositivaPorPeriodoGestionNoContabilizado(Long idPeriodoGestion) throws Exception;
    
    List<RhPlanillaImpositiva> cargaPlanillaImpositivaPorPeriodoGestionEstadosLiquidadoContabilizado(Long idPeriodoGestion) throws Exception;

    RhPlanillaImpositiva persistRhPlanillaImpositiva(RhPlanillaImpositiva rhPlanillaImpositiva) throws Exception;

    List<RhPlanillaImpositiva> guardaPlanillaImpositiva(List<RhPlanillaImpositiva> listaPlanillaImpositiva) throws Exception;

    void deleteRhPlanillaImpositiva(Long idPeriodoGestion) throws Exception;

    RhPlanillaImpositiva rhPlanillaImpositivaFactory(RhPlanillaImpositiva rhPlanillaImpositiva, BigDecimal totalGanado, Long idEmpleadoCargo, Long idPeriodoGestion)throws Exception;        
        
}
