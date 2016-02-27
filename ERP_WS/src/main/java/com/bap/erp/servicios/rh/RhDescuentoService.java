package com.bap.erp.servicios.rh;

import com.bap.erp.modelo.rh.RhDescuento;
import java.util.List;

public interface RhDescuentoService {

    public RhDescuento persistRhDescuento(RhDescuento rhDescuento) throws Exception; 
    
    RhDescuento mergeRhDescuento(RhDescuento rhDescuento) throws Exception;
    
    public List<RhDescuento> obtieneDescuentosVigentes()throws Exception;
    
    RhDescuento obtieneDescuentoPorDescripcion(String descripcion)throws Exception;
    
    Boolean verificaExistenciaCodigoDescuento(String codigo) throws Exception;
    
    void removeRhDescuento(Long idDescuento) throws Exception;
    
    Boolean verificaDescuentosPorIdDescuentoAndIdPeriodoParaEliminar(Long idDescuento, Long idPeriodoGestion) throws Exception;
    
    public List<RhDescuento> obtieneDescuentosVigentesPorPeriodo(Long idPeriodoGestion)throws Exception;
}
