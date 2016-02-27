package com.bap.erp.servicios.rh;

import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import java.util.List;

public interface RhEmpleadoCargoService {
    
    RhEmpleadoCargo persistRhEmpleadoCargo(RhEmpleadoCargo rhEmpleadoCargo) throws Exception;
    
    RhEmpleadoCargo mergeRhEmpleadoCargo(RhEmpleadoCargo rhEmpleadoCargo) throws Exception;
    
    RhEmpleadoCargo persistModificaRhEmpleadoCargo(RhEmpleadoCargo rhEmpleadoCargo) throws Exception;
    
    RhEmpleadoCargo getRhEmpleadoCargoById(Long idEmpleadoCargo) throws Exception;
    
    List<RhEmpleadoCargo> obtieneEmpleadoCargoVigente(Long idPeriodoGestion)throws Exception;
    
    RhEmpleadoCargo persistEmpleadoCargo(RhEmpleadoCargo rhEmpleadoCargo)throws Exception;
    
    RhEmpleadoCargo obtieneEmpleadoCargoPorIdEmpleado(Long idEmpleado) throws Exception;
    
    void removeRhEmpleadoCargo(Long idEmpleadoCargo) throws Exception;       
    
    RhEmpleadoCargo obtieneEmpleadoCargoPorCodigo(String codigo)throws Exception;
    
    RhEmpleadoCargo persistModificaRhEmpleadoCargoMasTablasRelacionadas(RhEmpleadoCargo rhEmpleadoCargo, Long idPeriodoGestion) throws Exception;
    
}
