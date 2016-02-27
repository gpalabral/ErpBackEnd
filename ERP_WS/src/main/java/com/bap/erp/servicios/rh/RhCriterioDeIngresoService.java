package com.bap.erp.servicios.rh;

import com.bap.erp.modelo.rh.RhCriterioDeIngreso;
import java.util.List;

public interface RhCriterioDeIngresoService {

    RhCriterioDeIngreso persistRhCriterioDeIngreso(RhCriterioDeIngreso rhCriterioDeIngreso) throws Exception;
    
    RhCriterioDeIngreso mergeRhCriterioDeIngreso(RhCriterioDeIngreso criterioDeIngreso) throws Exception;

    List<RhCriterioDeIngreso> listaRhCriterioDeIngreso() throws Exception;
    
    Boolean verificaExistenciaCodigoCriterioDeIngreso(String codigo) throws Exception;       
    
    RhCriterioDeIngreso obtieneCriterioDeIngresoPorDescripcion(String descripcion)throws Exception; 
    
    void removeRhCriterioDeIngreso(Long idCriterioDeIngreso) throws Exception;
    
    Boolean verificaCriterioDeIngresoEmpleadoCargoPorIdDescuentoAndIdPeriodoParaEliminar(Long idCriterioDeIngreso, Long idPeriodoGestion) throws Exception;
    
}
