package com.bap.erp.servicios.par;

import com.bap.erp.modelo.par.ParActividadEconomica;
import com.bap.erp.modelo.par.ParBanco;
import com.bap.erp.modelo.par.ParCaracteristicaEspecial;
import com.bap.erp.modelo.par.ParDepartamento;
import com.bap.erp.modelo.par.ParEstado;
import com.bap.erp.modelo.par.ParEstadoFactura;
import com.bap.erp.modelo.par.ParEstadoPago;
import com.bap.erp.modelo.par.ParEstadoProceso;
import com.bap.erp.modelo.par.ParFormaDePago;
import com.bap.erp.modelo.par.ParMunicipio;
import com.bap.erp.modelo.par.ParLocalizacion;
import com.bap.erp.modelo.par.ParMes;
import com.bap.erp.modelo.par.ParModalidadFacturacion;
import com.bap.erp.modelo.par.ParModalidadTransaccion;
import com.bap.erp.modelo.par.ParPeriodo;
import com.bap.erp.modelo.par.ParRecurrencia;
import com.bap.erp.modelo.par.ParTipoAlicuota;
import com.bap.erp.modelo.par.ParTipoAplicacionRetencion;
import com.bap.erp.modelo.par.ParCliente;
import com.bap.erp.modelo.par.ParCondicionPension;
import com.bap.erp.modelo.par.ParEstadoCivil;
import com.bap.erp.modelo.par.ParEstadoLiquidacion;
import com.bap.erp.modelo.par.ParEstadoPeriodoGestion;
import com.bap.erp.modelo.par.ParGenero;
import com.bap.erp.modelo.par.ParPorcentajeAntiguedad;
import com.bap.erp.modelo.par.ParSeccion;
import com.bap.erp.modelo.par.ParTipoAplicacionDescuentoCriterioDeIngreso;
import com.bap.erp.modelo.par.ParTipoBonoAntiguedad;
import com.bap.erp.modelo.par.ParTipoCompra;
import com.bap.erp.modelo.par.ParTipoContacto;
import com.bap.erp.modelo.par.ParTipoContratoEmpleado;
import com.bap.erp.modelo.par.ParTipoDescuento;
import com.bap.erp.modelo.par.ParTipoDocumento;
import com.bap.erp.modelo.par.ParTipoDocumentoMercantil;
import com.bap.erp.modelo.par.ParTipoDocumentoPago;
import com.bap.erp.modelo.par.ParTipoEmpleado;
import com.bap.erp.modelo.par.ParTipoHito;
import com.bap.erp.modelo.par.ParTipoItem;
import com.bap.erp.modelo.par.ParTipoModulo;
import com.bap.erp.modelo.par.ParTipoMoneda;
import com.bap.erp.modelo.par.ParTipoPago;
import com.bap.erp.modelo.par.ParTipoProveedorCliente;
import com.bap.erp.modelo.par.ParTipoRegistro;
import com.bap.erp.modelo.par.ParTipoRetencion;
import com.bap.erp.modelo.par.ParTipoTransaccion;
import com.bap.erp.modelo.par.ParValor;
import com.bap.erp.modelo.pojo.EntidadPojo;
import java.util.List;

public interface ParValorService {

    ParValor persistParValor(ParValor parValor);

    List<ParValor> getParValor();

    Object find(Class clazz, String id);

    List<ParActividadEconomica> getListParActividadEconomica() throws Exception;

    List<ParBanco> getListParBanco() throws Exception;

    List<ParCaracteristicaEspecial> getListParCaracteristicaEspecial() throws Exception;

    List<ParDepartamento> getListParDepartamento() throws Exception;

    List<ParEstado> getListParEstado() throws Exception;

    List<ParEstadoFactura> getListParEstadoFactura() throws Exception;

    List<ParEstadoPago> getListParEstadoPago() throws Exception;

    List<ParEstadoProceso> getListParEstadoProceso() throws Exception;

    List<ParFormaDePago> getListParFormaDePago() throws Exception;

    List<ParLocalizacion> getListParLocalizacion() throws Exception;

    List<ParMes> getListParMes() throws Exception;

    List<ParModalidadFacturacion> getListParModalidadFacturacion() throws Exception;

    List<ParModalidadTransaccion> getListParModalidadTransaccion() throws Exception;

    List<ParMunicipio> getListParMunicipio() throws Exception;

    List<ParMunicipio> getListParMunicipioByDepartamento(String codigoDepartamento) throws Exception;

    List<ParPeriodo> getListParPeriodo() throws Exception;

    List<ParRecurrencia> getListParRecurrencia() throws Exception;

    List<ParTipoContacto> getListParTipoContacto() throws Exception;

    List<ParTipoDocumento> getListParTipoDocumento() throws Exception;

    List<ParTipoDocumentoMercantil> getListParTipoDocumentoMercantil() throws Exception;

    List<ParTipoDocumentoPago> getListParTipoDocumentoPago() throws Exception;

    List<ParTipoMoneda> getListParTipoMoneda() throws Exception;

    List<ParTipoPago> getListParTipoPago() throws Exception;

    List<ParTipoProveedorCliente> getListParTipoProveedorCliente() throws Exception;

    List<ParTipoRegistro> getListParTipoRegistro() throws Exception;

    List<ParTipoRetencion> getListParTipoRetencion() throws Exception;

    List<ParTipoTransaccion> getListParTipoTransaccion() throws Exception;

    List<ParTipoItem> getListParTipoItem() throws Exception;

    List<EntidadPojo> getMunicipiosPojoPorDepartamento(String codigoDepartamento) throws Exception;

    List<ParValor> getListMunicipiosByDepartamento(String codigoDepartamento) throws Exception;

    List<ParMunicipio> getListaMunicipiosByCodigoDepartamento(String codigoDepartamento) throws Exception;

    ParValor findByCodigo(String codigo) throws Exception;

    List<ParCaracteristicaEspecial> getListParCaracteristicaEspecialParafacturacion() throws Exception;

    List<ParTipoTransaccion> getTipoTransaccionPorModulo(String modulo) throws Exception;

    List<ParTipoHito> getListTipoHito() throws Exception;

    void actualizaDatosAlicuotas(List<ParTipoAlicuota> listaAlicuotas) throws Exception;

    ParValor mergeParValor(ParValor parValor) throws Exception;

    ParTipoAlicuota findParTipoAlicuotaByCodigo(String codigo) throws Exception;

    List<ParTipoCompra> getListParTipoCompra() throws Exception;

    List<ParTipoAplicacionRetencion> getListParTipoAplicacionRetencion() throws Exception;

    List<ParCliente> getListParCliente() throws Exception;

    List<ParTipoAlicuota> getListParTipoAlicuota() throws Exception;

    List<ParTipoModulo> getListParTipoModulo() throws Exception;

    List<ParTipoDocumentoMercantil> getParTipoDocumentoMercantilByGrupo(String grupo) throws Exception;

    ParEstadoPago findParEstadoPagoByCodigo(String codigo) throws Exception;

    List<ParTipoTransaccion> getListParTipoTransaccionFactura() throws Exception;

    List<ParTipoTransaccion> getListParTipoTransaccionRetencion() throws Exception;

    ParTipoModulo findParTipoModuloByCodigo(String codigo) throws Exception;

    List<ParEstadoCivil> getParEstadoCivil();

    List<ParCondicionPension> getParCondicionPension();

    List<ParTipoEmpleado> getParTipoEmpleado();

    List<ParGenero> getParGenero();

    List<ParSeccion> getParSeccion();

    ParEstado findParEstadoByCodigo(String codigo) throws Exception;

    List<ParTipoContratoEmpleado> getParTipoContratoEmpleado() throws Exception;

    Boolean verificaExistenciaDeDatosAlicuota() throws Exception;

    ParPorcentajeAntiguedad getParPorcentajeAntiguedadSegunAnio(int anio) throws Exception;

    ParTipoBonoAntiguedad obtieneParTipoBonoAntiguedadPorCodigo(String codigo) throws Exception;

    List<ParEstadoLiquidacion> getListParEstadoLiquidacion() throws Exception;

    ParEstadoLiquidacion obtieneParEstadoLiquidacion(String codigo) throws Exception;

    ParTipoAplicacionDescuentoCriterioDeIngreso obtieneTipoAplicacionDescuentoPorCodigo(String codigo) throws Exception;

    ParEstadoPeriodoGestion obtieneParEstadoPeriodoGestion(String codigo) throws Exception;

    ParGenero obtieneParGeneroPorCodigo(String codigo) throws Exception;
    
    ParEstadoFactura obtieneParEstadoFacturaPorCodigo(String codigo)throws Exception;
}
