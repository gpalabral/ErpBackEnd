package com.bap.erp.servicios.rh;

import com.bap.erp.modelo.rh.RhEmpleado;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public interface RhEmpleadoService {
    
    RhEmpleado persistRhEmpleado(RhEmpleado rhEmpleado) throws Exception;
    
    RhEmpleado mergeRhEmpleado(RhEmpleado rhEmpleado) throws Exception;
    
    void removeRhEmpleado(Long idEmpleado) throws Exception;        
    
    List<RhEmpleado> listaRhEmpleado() throws Exception;
    
    String codigoEmpleado()throws Exception;
   
    RhEmpleado persistModificacionRhEmpleado(RhEmpleado rhEmpleado)throws Exception;
    
    RhEmpleado getRhEmpleadoById(Long idEmpleado) throws Exception;        
    
    RhEmpleado obtieneEmpleadoPorCodigo(String codigo)throws Exception;
    
    RhEmpleado obtieneEmpleadoPorIdEmpleadoCargo(Long idEmpleadoCargo)throws Exception;
    
    int obtieneDiasVacacion(Date fechaInicial, Date fechaFinal) throws Exception;
    
    List<RhEmpleado> listaRhEmpleadoConCargoAsignado() throws Exception;
    
    List<RhEmpleado> listaRhEmpleadosNuevosSinVariaciones(Long idPeriodoGestion) throws Exception;
    
    List<RhEmpleado> listaRhEmpleadosNuevosSinRcIva(Long idPeriodoGestion) throws Exception;
    
    List<RhEmpleado> listaRhEmpleadosNuevosSinDescuentos(Long idPeriodoGestion) throws Exception;
    
    List<RhEmpleado> listaRhEmpleadosNuevosSinCriteriosIngreso(Long idPeriodoGestion) throws Exception;
    
    List<RhEmpleado> listaRhEmpleadosNuevosSinPrima(Long idPeriodoGestion) throws Exception;
    
    List<RhEmpleado> listaRhEmpleadoPorPeriodo(Long idPeriodoGestion) throws Exception;
    
    void importaEmpleadosExcel(InputStream fileInputStream) throws Exception;
    
}