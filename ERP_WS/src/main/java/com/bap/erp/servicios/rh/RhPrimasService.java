package com.bap.erp.servicios.rh;

import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import com.bap.erp.modelo.rh.RhPrimas;
import java.io.InputStream;
import java.util.List;

public interface RhPrimasService {
    
    boolean existePrimasPorPeriodo(Long idPeriodoGestion) throws Exception;
    
    void registrarRhPrimasPorPeriodo(Long idPeriodoGestion) throws Exception;
    
    RhPrimas persistRhPrimas(RhPrimas rhRcIva)throws Exception;
    
    List<RhPrimas> listaRhPrimasPorPeriodo(Long idPeriodoGestion)throws Exception;
    
    void importaPrimasExcel(InputStream fileInputStream, Long idPeriodoGestion) throws Exception;
    
    RhPrimas obtieneRhPrimasPorIdEmpleadoCargo(Long idEmpleadoCargo, Long idPeriodoGestion)throws Exception;
    
    RhPrimas mergeRhPrimas(RhPrimas rhRcIva)throws Exception;
    
    List<RhPrimas> modificaListaRhPrimas(List<RhPrimas> listaRhPrimas)throws Exception;
    
    void modificaCamposEmpleadoCargoParaPrimas(RhEmpleadoCargo rhEmpleadoCargoNuevo, Long idPeriodoGestion) throws Exception;
    
}
