package com.bap.erp.servicios.rh;

import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import com.bap.erp.modelo.rh.RhRcIva;
import java.io.InputStream;
import java.util.List;

public interface RhRcIvaService {
    
    boolean existeRcIvaPorPeriodo(Long idPeriodoGestion) throws Exception;
    
    void registrarRhRcIvaPorPeriodo(Long idPeriodoGestion) throws Exception;
    
    RhRcIva persistRhRcIva(RhRcIva rhRcIva)throws Exception;
    
    List<RhRcIva> listaRhRcIvaPorPeriodo(Long idPeriodoGestion)throws Exception;
    
    void importaValoresExcel(InputStream fileInputStream, Long idPeriodoGestion) throws Exception;
    
    RhRcIva obtieneRhRcIvaPorIdEmpleadoCargo(Long idEmpleadoCargo, Long idPeriodoGestion)throws Exception;
    
    RhRcIva mergeRhRcIva(RhRcIva rhRcIva)throws Exception;
    
    List<RhRcIva> modificaListaRhRcIva(List<RhRcIva> listaRhRcIva)throws Exception;
    
    void modificaCamposEmpleadoCargoParaRcIva(RhEmpleadoCargo rhEmpleadoCargoNuevo, Long idPeriodoGestion) throws Exception;
    
    void actualizaSaldoRcIva(Long idPeriodoGestion)throws Exception;
    
}
