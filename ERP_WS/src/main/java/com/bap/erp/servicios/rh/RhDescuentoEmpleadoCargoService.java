package com.bap.erp.servicios.rh;

import com.bap.erp.modelo.pojo.rh.RhEmpleadoDescuentoPojo;
import com.bap.erp.modelo.rh.RhDescuento;
import com.bap.erp.modelo.rh.RhDescuentoEmpleadoCargo;
import com.bap.erp.modelo.rh.RhEmpleado;
import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

public interface RhDescuentoEmpleadoCargoService {

    RhDescuentoEmpleadoCargo persistRhDescuentoEmpleadoCargo(RhDescuentoEmpleadoCargo rhDescuentoEmpleadoCargo) throws Exception;

    RhDescuentoEmpleadoCargo mergeRhDescuentoEmpleadoCargo(RhDescuentoEmpleadoCargo rhDescuentoEmpleadoCargo) throws Exception;

    void registraDescuentoEmpleadoCargoPorPeriodo(Long idPeriodoGestion) throws Exception;

    Boolean existeDescuentoPorPeriodo(Long idPeriodoGestion) throws Exception;

    List<RhEmpleadoDescuentoPojo> cargaEmpleadoDescuento(Long idPeriodoGestion) throws Exception;

    List<RhDescuentoEmpleadoCargo> obtieneDescuentosPorEmpleado(RhEmpleado rhEmpleado, Long idPeriodoGestion) throws Exception;

    void importaValoresExcel(InputStream fileInputStream, Long idPeriodoGestion) throws Exception;

    void modificaEmpleadoCargoPorIdEmpleadoIdCargoMonto(Long idEmpleado, Long idDescuento, BigDecimal monto, Long idPeriodoGestion) throws Exception;

    void modificaDescuentoEmpleadoCargo(List<RhEmpleadoDescuentoPojo> listaEmpleadoDescuentoPojo, Long idPeriodoGestion) throws Exception;

    BigDecimal otrosDescuentoPorEmpleadoYPeriodo(Long idRhEmpleadoCargo, Long idPeriodoGestion) throws Exception;

    void actualizaDescuentoEmpleadoCargo(RhDescuento rhDescuento, Long idPeriodoGestion) throws Exception;

    List<RhDescuentoEmpleadoCargo> obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception;

    void modificaCamposEmpleadoCargoListaRhDescuentoEmpleadoCargo(RhEmpleadoCargo rhEmpleadoCargoNuevo, Long idPeriodoGestion) throws Exception;        
    
    List<RhDescuentoEmpleadoCargo> obtieneDescuentosPorIdDescuentoAndIdPeriodo(Long idDescuento, Long idPeriodoGestion) throws Exception;

}
