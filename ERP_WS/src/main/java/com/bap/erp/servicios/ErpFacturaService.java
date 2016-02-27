package com.bap.erp.servicios;

import com.bap.erp.modelo.ban.DocumentoPago;
import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.pojo.CpcConciliacionContablePojo;
import com.bap.erp.modelo.pojo.CpcFacturaEmitidaCpcDetalleFacturaPojo;
import com.bap.erp.modelo.pojo.CpcFacturaEmitidaPojo;
import com.bap.erp.modelo.pojo.CpcLibroDeBancarizacionPorVentasPojo;
import com.bap.erp.modelo.pojo.CppLibroDeComprasPojo;
import com.bap.erp.modelo.pojo.CpcLibroDeVentasHuaweiPojo;
import com.bap.erp.modelo.pojo.CpcLibroDeVentasPojo;
import com.bap.erp.modelo.pojo.ErpFacturaRetencionPojo;
import com.bap.erp.modelo.pojo.ErpFacturasBancariasPojo;
import com.bap.erp.modelo.pojo.ErpFacturasDatosPojo;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ErpFacturaService {

    ErpFactura persistCpcFacturaEmitida(ErpFactura cpcFacturaEmitida) throws Exception;

    ErpFactura getCpcFacturaEmitidaById(Long idFacturaEmitida) throws Exception;

    ErpFactura mergeCpcFacturaEmitida(ErpFactura cpcFacturaEmitida) throws Exception;

    List<ErpFactura> getCpcFacturaEmitidaList() throws Exception;

    String genereCodigoDeControl(Long nit, String numeroFactura, String nroAutorizacion, Date fechaFactura, BigDecimal monto, String llaveDosificacion) throws Exception;

    ErpFactura persistCpcFacturaEmitidaCpcDetalleFacturaPojo(CpcFacturaEmitidaCpcDetalleFacturaPojo cpcFacturaEmitidaCpcDetalleFacturaPojo) throws Exception;

    List<ErpFactura> getCpcFacturaEmitidaProveedorClienteDocumentoPago(CppProveedorCliente cppProveedorCliente, DocumentoPago documentoPago) throws Exception;

    List<ErpFactura> getListCpcFacturaEmitida() throws Exception;

    List<ErpFactura> getCpcFacturaEmitidaListporMesyAnio() throws Exception;

    List<CpcFacturaEmitidaPojo> getCpcFacturaEmitidaPorMesyAnio(int month, int year) throws Exception;

    Long generaNumeroFactura(Long idDosificacion) throws Exception;

    List<ErpFactura> getLibroDeVentas(int month, int year, String modulo) throws Exception;

    CpcFacturaEmitidaPojo getFacturaEmitidaNombreConcatenadoById(Long idFacturaEmitida) throws Exception;

    ErpFactura guardaFacturaEmitida(ErpFactura cpcFacturaEmitida) throws Exception;

    List<ErpFactura> getListaCpcFacturaEmitidaById(Long idPagoContrato) throws Exception;

    Boolean getVerificaFacturaParaDosificacionesByIdDosificacion(Long idDosificacion) throws Exception;

    List<ErpFactura> getXMLByIdFacturaEmitida(Long idFacturaEmitida) throws Exception;

    List<ErpFactura> getListadoCpcFacturaEmitida() throws Exception;

    List<CpcLibroDeVentasPojo> getLibroDeVentasPojo(int month, int year) throws Exception;

    BigDecimal getSumaFacturaEmitidasByIdPagoContrato(Long idPagoContrato) throws Exception;

    List<CpcFacturaEmitidaPojo> getCpcNotaDebitoCreditoByMesYAnio(int month, int year, String codigoDocMercantil, String estadoPago, String codigoEstadoFactura) throws Exception;

    public String getGeneraXML(Long idFacturaEmitida) throws Exception;

    List<ErpFactura> getCpcFacturaEmitidaByIdPagoContratoAndEstado(Long idPagoContrato, String estadoPago) throws Exception;

    List<CpcLibroDeBancarizacionPorVentasPojo> getLibroDeBancarizacionPorVentas(int month, int year) throws Exception;

    void persistFacturasNoUtilizadas() throws Exception;

    List<CpcFacturaEmitidaPojo> getCpcFacturaEmitidaPorCliente(Long idCliente) throws Exception;

    List<CpcConciliacionContablePojo> getReferenciacionNotaDebitoFiscal(List<CpcConciliacionContablePojo> listaDebitoFiscal, List<CpcConciliacionContablePojo> listaIngresos) throws Exception;

    List<CpcLibroDeVentasHuaweiPojo> getLibroDeVentasHuawei(Long idProveedorCliente, int month, int year) throws Exception;

    List<Integer> getGestionesFacturadas(String modulo) throws Exception;

    void persistFacturasReferenciadas(List<CpcConciliacionContablePojo> listaConciliacionContable) throws Exception;

    Boolean getVerdaderoSiElContratoEsModificable(Long idContrato) throws Exception;

    List<CpcConciliacionContablePojo> seleccionaDatosDeExcel(InputStream fileInputStream) throws Exception;

    List<CpcLibroDeVentasHuaweiPojo> getReporteControlDeIngresos(Date fechaInicial, Date fechaFinal) throws Exception;

    List<ErpFactura> getFacturasPorProveedorCliente(CppProveedorCliente cppProveedorCliente) throws Exception;

    List<CppLibroDeComprasPojo> getLibroDeComprasPojo(int mes, int anio) throws Exception;
    
    List<ErpFacturaRetencionPojo> getFacturaRetencionByIdProveedorCliente(Long idProveedorCliente)throws Exception;
    
    void noBancarizaFacturaRetencion(Long idFacturaRetencion, String tipoFacturaRetencion)throws Exception;
    
    List<CpcLibroDeBancarizacionPorVentasPojo> getLibroDeBancarizacionPorCompras(int mes, int anio)throws Exception;
    
    List<Integer> getGestionesDeFacturasRetencionesBancarizadas()throws Exception;
    
    List<ErpFacturasBancariasPojo> importaExcelFacturasBancarias(InputStream fileInputStream) throws Exception;

    List<CpcConciliacionContablePojo> getReferenciacionNotaCreditoFiscal(List<CpcConciliacionContablePojo> listaCreditoFiscal) throws Exception;
    
    void guardaFacturasBancarias(ErpFacturasDatosPojo erpFacturasDatosPojo) throws Exception;
    
}
