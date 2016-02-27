package com.bap.erp.servicios.cpc;

import com.bap.erp.modelo.cpc.CpcActividadEconomica;
import java.util.List;

public interface CpcActividadEconomicaService {
    
    CpcActividadEconomica persistCpcActividadEconomica (CpcActividadEconomica cpcActividadEconomica);
    
    List<CpcActividadEconomica> getCpcActividadEconomica()throws Exception;
    
    CpcActividadEconomica getCpcActividadEconomicaById(Long idActividadEconomica) throws Exception;
    
    List<CpcActividadEconomica> getActividadesEconomicasWithDosificacion()throws Exception;
    
    Boolean verificaSiExisteCodigo(String codigo)throws Exception;
    
    List<CpcActividadEconomica> getActividadEconomicaByIdPagoContrato(Long idPagoContrato)throws Exception;
    
    List<CpcActividadEconomica> getActividadEconomicaByIdContrato(Long idContrato)throws Exception;
   
}