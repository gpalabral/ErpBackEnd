package com.bap.erp.servicios.cpc;

import com.bap.erp.modelo.cpc.CpcContratoActividadEconomica;
import java.util.List;

public interface CpcContratoActividadEconomicaService {
    
    CpcContratoActividadEconomica persistCpcContratoActividadEconomica (CpcContratoActividadEconomica cpcContratoActividadEconomica);      
    
    List<CpcContratoActividadEconomica> getCpcContratoActividadEconomicaByIdContrato(Long idContrato)throws Exception;
    
    void deleteCpcContratoActividadEconomica(CpcContratoActividadEconomica cpcContratoActividadEconomica)throws Exception;
   
}