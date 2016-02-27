package com.bap.erp.servicios.rh;

import com.bap.erp.modelo.rh.RhPlanillaAportesSeguridadSocial;
import java.util.List;

public interface RhPlanillaAportesSeguridadSocialService {
    
    boolean existePlanillaAportesSeguridadSocialPorPeriodo(Long idPeriodoGestion) throws Exception;
    
    List<RhPlanillaAportesSeguridadSocial> cargaPlanillaAportesSeguridadSocialPorPeriodoGestion(Long idPeriodoGestion) throws Exception;
 
    List<RhPlanillaAportesSeguridadSocial> generaPlanillaAportesSeguridadSocial(Long idPeriodoGestion) throws Exception;
    
    RhPlanillaAportesSeguridadSocial rhPlanillaAportesSeguridadSocialFactory(RhPlanillaAportesSeguridadSocial rhPlanillaAportesSeguridadSocial)throws Exception;
}
