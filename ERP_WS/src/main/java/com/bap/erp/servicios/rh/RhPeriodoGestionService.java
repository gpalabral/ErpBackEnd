package com.bap.erp.servicios.rh;

import com.bap.erp.modelo.rh.RhPeriodoGestion;

public interface RhPeriodoGestionService {
    
    
    RhPeriodoGestion encuentraRhPeriodoGestionPorId(Long idPeriodoGestion)throws Exception;
    
    public RhPeriodoGestion persistRhPeriodoGestion(RhPeriodoGestion rhPeriodoGestion) throws Exception;        
    
    RhPeriodoGestion mergeRhPeriodoGestion(RhPeriodoGestion rhPeriodoGestion) throws Exception;
    
    Boolean existePeriodoGestion(int periodo, int gestion)throws Exception;
    
    RhPeriodoGestion obtieneRegistroPorPeriodoGestion(int periodo, int gestion)throws Exception;
    
    RhPeriodoGestion obtienePeriodoGestionUltimoVigente() throws Exception;
    
    RhPeriodoGestion obtienePeriodoGestionUltimoNoVigente() throws Exception;
    
    RhPeriodoGestion persistRhPeriodoGestionCompleto(RhPeriodoGestion rhPeriodoGestion) throws Exception;
    
    RhPeriodoGestion getRhPeriodoGestionById(Long idPeriodoGestion) throws Exception;
    
    RhPeriodoGestion obtieneRegistroPorPeriodoGestionAnterior(Long idPeriodoGestion) throws Exception;
}
