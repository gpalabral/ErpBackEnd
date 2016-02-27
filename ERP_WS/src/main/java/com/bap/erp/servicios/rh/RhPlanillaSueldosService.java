package com.bap.erp.servicios.rh;

import com.bap.erp.modelo.pojo.rh.RhTransferenciasBancariasPojo;
import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import com.bap.erp.modelo.rh.RhPeriodoGestion;
import com.bap.erp.modelo.rh.RhPlanillaSueldos;
import java.math.BigDecimal;
import java.util.List;

public interface RhPlanillaSueldosService {
    
//    void generaPlanillaDeSueldos(Long idPeriodoGestion)throws Exception;
    List<RhPlanillaSueldos> generaPlanillaDeSueldos(Long idPeriodoGestion)throws Exception;
    
    RhPlanillaSueldos persistRhPlanillaSueldos(RhPlanillaSueldos rhPlanillaSueldos)throws Exception;
    
    List<RhPlanillaSueldos> cargaPlanillaSueldosPorIdPeriodoGestionNoContabilizada(Long idPeriodoGestion)throws Exception;
    
    BigDecimal calculoAfp(Long idEmpleadoCargo, Long idPeriodoGestion, BigDecimal totalGanado)throws Exception;
    
    BigDecimal calculoAporteNacionalSolidario(Long idEmpleadoCargo, Long idPeriodoGestion, BigDecimal totalGanado) throws Exception; 
    
    int edadEmpleado(RhEmpleadoCargo rhEmpleadoCargo, Long rhPeriodoGestion)throws Exception;
    
    boolean existePlanillaSueldosPorPeriodo(Long idPeriodoGestion)throws Exception;
    
//    BigDecimal totalGanado(RhEmpleadoCargo rhEmpleadoCargo, RhPeriodoGestion rhPeriodoGestion)throws Exception;
    
    List<RhPlanillaSueldos> guardaRhPlanillaSueldos(List<RhPlanillaSueldos> listaPlanillaSueldos)throws Exception;
        
    void deletePlanillaSueldos(Long idPeriodoGestion)throws Exception;
    
    List<RhPlanillaSueldos> cargaPlanillaSueldosPorIdPeriodoGestionEstadosLiquidadoContabilizado(Long idPeriodoGestion) throws Exception;
    
    RhPlanillaSueldos rhPlanillaSueldosFactory(RhEmpleadoCargo rhEmpleadoCargo, RhPeriodoGestion rhPeriodoGestion)throws Exception;    
    
    List<RhTransferenciasBancariasPojo> listaTransferencias(Long idPeriodoGestion)throws Exception;
}
