package com.bap.erp.servicios.cpc;

import com.bap.erp.modelo.cpc.CpcDosificacion;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import java.util.List;

public interface CpcDosificacionService {

    CpcDosificacion persistCpcDosificacion(CpcDosificacion cpcDosificacion)throws Exception;

    List<CpcDosificacion> getCpcDosificacion() throws Exception;

    List<EntidadArbolPojo> getCpcDosificacionArbol() throws Exception;

    List<CpcDosificacion> listaCpcDosificacionesPorEstadoyCaracEsp(Long idSucursal, String parEstadoProceso, String parCaracteristicaEspecial) throws Exception;

    CpcDosificacion mergeCpcDosificacion(CpcDosificacion cpcDosificacion) throws Exception;

    List<EntidadArbolPojo> getCpcDosificacionArbolPorSucursal(Long idSucursal) throws Exception;

    List<CpcDosificacion> listaCpcDosificacionPorIdSucursal(Long idSucursal) throws Exception;

    CpcDosificacion getCpcDosificacionById(Long idDosificacion) throws Exception;

//    CpcDosificacion getCpcDosificacionByIdSucursal(Long idSucursal) throws Exception;
//    CpcDosificacion getCpcDosificacionPreEstablecida() throws Exception;
    List<CpcDosificacion> getListaCpcDosificacionByIdDosificacion(Long idDosificacion) throws Exception;

    List<CpcDosificacion> getListaCpcDosificacionByIdSucursalAndCodigoDocMercantil(Long idSucursal, String codigoDocMercantil) throws Exception;

//    Long getNumeroFacturaActual(Long idDosificacion) throws Exception;
//    Boolean getVerificaExistenciaPreEstablecidoParaDosificacionesByIdSucursal(Long idSucursal) throws Exception;
    CpcDosificacion getCpcDosificacionesByIdSucursalModalidadFacEstadoProceso(Long idSucursal, String parModalidadFacturacion, String parEstadoProceso) throws Exception;

    List<CpcDosificacion> listaDosificacionesVencidas() throws Exception;

    List<CpcDosificacion> getCpcDosificacionesPorIdContratoIdSucurEstProcCaracEspModFact(Long idSucursal, Long idContrato, String parEstadoProceso, String parCaracteristicaEspecial, String parModalidadFacturacion) throws Exception;
    
    String obtieneConcatenaModalidadFacturacionPorActividadEconomica(Long idActividadEconomica) throws Exception;
    
}
