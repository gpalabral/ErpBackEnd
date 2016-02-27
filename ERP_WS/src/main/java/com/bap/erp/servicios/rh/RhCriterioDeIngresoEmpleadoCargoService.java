package com.bap.erp.servicios.rh;

import com.bap.erp.modelo.pojo.rh.RhEmpleadoCriterioDeIngresoPojo;
import com.bap.erp.modelo.rh.RhCriterioDeIngreso;
import com.bap.erp.modelo.rh.RhCriterioDeIngresoEmpleadoCargo;
import com.bap.erp.modelo.rh.RhEmpleado;
import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

public interface RhCriterioDeIngresoEmpleadoCargoService {

    RhCriterioDeIngresoEmpleadoCargo persistRhCriterioDeIngresoEmpleadoCargo(RhCriterioDeIngresoEmpleadoCargo rhCriterioDeIngresoEmpleadoCargo) throws Exception;

    RhCriterioDeIngresoEmpleadoCargo mergeRhCriterioDeIngresoEmpleadoCargo(RhCriterioDeIngresoEmpleadoCargo rhCriterioDeIngresoEmpleadoCargo) throws Exception;

    boolean existeCriterioDeIngresoEmpleadoCargoPorPeriodo(Long idPeriodoGestion) throws Exception;

    void registrarCriterioDeIngresoPorPeriodo(Long idPeriodoGestion) throws Exception;

    List<RhEmpleadoCriterioDeIngresoPojo> cargaEmpleadoCriterioDeIngreso(Long idPeriodoGestion) throws Exception;

    List<RhCriterioDeIngresoEmpleadoCargo> obtieneCriterioDeIngresoEmpleadoCargo(RhEmpleado rhEmpleado, Long idPeriodoGestion) throws Exception;

    RhCriterioDeIngresoEmpleadoCargo obtieneCriterioDeIngresoEmpleadoCargo(Long idEmpleadoCargo, Long idCriterioDeIngreso, Long idPeriodoGestion) throws Exception;

    void modificaListaCriterioDeIngresoEmpleadoCargo(List<RhEmpleadoCriterioDeIngresoPojo> listaEmpleadoCriterioDeIngresoPojo, Long idPeriodoGestion) throws Exception;

    void importaValoresExcel(InputStream fileInputStream, Long idPeriodoGestion) throws Exception;

    RhCriterioDeIngresoEmpleadoCargo encuentraCriterioDeIngresoEmpleadoCargo(Long idEmpleadoCargo, Long idCriterioDeIngreso, Long idPeriodoGestion) throws Exception;

    RhCriterioDeIngresoEmpleadoCargo encuentraBonoProduccion(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception;

    BigDecimal sumaOtrosBonos(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception;

    void actualizaCriterioDeIngresoEmpleadoCargo(RhCriterioDeIngreso rhCriterioDeIngreso, Long idPeriodoGestion) throws Exception;

    List<RhCriterioDeIngresoEmpleadoCargo> obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception;

    void modificaCamposEmpleadoCargoListaRhCriterioDeIngresoEmpleadoCargo(RhEmpleadoCargo rhEmpleadoCargoNuevo, Long idPeriodoGestion) throws Exception;
    
    List<RhCriterioDeIngresoEmpleadoCargo> obtieneCriterioDeIngresoEmpleadoCargoPorIdDescuentoAndIdPeriodoParaEliminar(Long idCriterioDeIngreso, Long idPeriodoGestion) throws Exception;

}
