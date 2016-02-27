package com.bap.erp.servicios.rh;

import com.bap.erp.modelo.rh.RhParametros;

public interface RhParametrosService {

    public RhParametros persistRhParametrosDatosGenerales(RhParametros rhParametros) throws Exception;
    
    RhParametros persistModificaRhParametrosPatronalesAndLaborales(RhParametros rhParametros) throws Exception;
    
    RhParametros obtieneRhParametrosPorPeriodoGestion(Long idPeriodoGestion) throws Exception;
    
    RhParametros guardaParametrosIniciales(RhParametros rhParametros)throws Exception;
    
    RhParametros modificaParametrosIniciales(RhParametros rhParametros)throws Exception;
}
