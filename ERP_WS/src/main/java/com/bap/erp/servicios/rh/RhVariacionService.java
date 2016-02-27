package com.bap.erp.servicios.rh;

import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import com.bap.erp.modelo.rh.RhVariacion;
import java.io.InputStream;
import java.util.List;

public interface RhVariacionService {

    RhVariacion persistRhVariacion(RhVariacion rhVariacion) throws Exception;    
    
    boolean existeVariacionPorPeriodo(Long idPeriodoGestion) throws Exception;
    
    void registrarRhVariacionPorPeriodo(Long idPeriodoGestion) throws Exception;
    
    List<RhVariacion> getRhVariacionPorPeriodo(Long idPeriodoGestion) throws Exception;
    
    void importaValoresExcel(InputStream fileInputStream, Long idPeriodoGestion) throws Exception;
    
    RhVariacion obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception;
    
    RhVariacion mergeRhVariacion(RhVariacion rhVariacion)throws Exception;
    
    void modificaRhVariacion(List<RhVariacion> listaVariacion, Long idPeriodoGestion)throws Exception;
    
    void modificaCamposEmpleadoCargoParaVariacion(RhEmpleadoCargo rhEmpleadoCargoNuevo, Long idPeriodoGestion) throws Exception;
    
    
}
