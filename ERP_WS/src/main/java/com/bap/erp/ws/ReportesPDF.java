package com.bap.erp.ws;

import com.bap.erp.commons.utils.DateUtils;
import com.bap.erp.commons.utils.NumberUtils;
import com.bap.erp.commons.utils.ObjectUtils;
import com.bap.erp.enums.EnumTipoMoneda;
import com.bap.erp.enums.EnumTipoTransaccion;
import com.bap.erp.modelo.ErpDetalleFactura;
import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.modelo.ErpNotaCreditoDebito;
import com.bap.erp.modelo.cpc.CpcSucursal;
import com.bap.erp.modelo.pojo.ErpNotaCreditoDebitoCpcDetalleFacturaPojo;
import com.bap.erp.modelo.rh.RhPlanillaSueldos;
import com.bap.erp.servicios.ErpDetalleFacturaService;
import com.bap.erp.servicios.ErpFacturaService;
import com.bap.erp.servicios.ErpNotaCreditoDebitoService;
import com.bap.erp.servicios.cpp.CppProveedorClienteService;
import com.bap.erp.servicios.rh.RhPlanillaSueldosService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/reportes")
@Api(value = "reportes", description = "WS for reportes")
public class ReportesPDF {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ReportesPDF.class);

    @Resource
    HttpServletRequest request;

    @Context
    HttpServletResponse response;

    @Resource
    ServletContext context;

    @Autowired
    private ErpFacturaService erpFacturaService;

    @Autowired
    private ErpDetalleFacturaService cpcDetalleFacturaService;

    @Autowired
    private CppProveedorClienteService cppProveedorClienteService;

    @Autowired
    private ErpNotaCreditoDebitoService erpNotaCreditoDebitoService;
    
    @Autowired
    private RhPlanillaSueldosService rhPlanillaSueldosService;

    @GET
    @Path("/getFacturaComputarizada/{idFacturaEmitida}")
    @ApiOperation(value = "Factura Computarizada")
    @Produces("application/pdf")
    public Response getFacturaComputarizada(@PathParam(value = "idFacturaEmitida") Long idFacturaEmitida) {

        try {

            log.info("Recuperando datos para ::: " + idFacturaEmitida);

            ErpFactura cpcFacturaEmitida = erpFacturaService.getCpcFacturaEmitidaById(idFacturaEmitida);

            byte[] qrCode = getQRCode(cpcFacturaEmitida);

            byte[] original = getFacturaPdf(cpcFacturaEmitida, "ORIGINAL", qrCode, "facturaEmitida.jasper", 18);
            byte[] copiaEmisor = getFacturaPdf(cpcFacturaEmitida, "COPIA EMISOR", qrCode, "facturaEmitida.jasper", 18);

            byte[] res = mergePdfFiles(original, copiaEmisor);

            ResponseBuilder responsePDF = Response.ok((Object) res);

            return responsePDF.build();

        } catch (Exception e) {
            log.error("ERROR:::", e);

            return Response.status(500).entity(e).build();
        }

    }

    @GET
    @Path("/getNotaCreditoDebito/{idNotaCreditoDebito}")
    @ApiOperation(value = "Nota Credito Debito")
    @Produces("application/pdf")
    public Response getNotaCreditoDebito(@PathParam(value = "idNotaCreditoDebito") Long idNotaCreditoDebito) {
        try {
            ErpNotaCreditoDebitoCpcDetalleFacturaPojo erpNotaCreditoDebitoCpcDetalleFacturaPojo = erpNotaCreditoDebitoService.getErpNotaCreditoDebito(idNotaCreditoDebito);
            ErpFactura cpcFacturaEmitida = erpNotaCreditoDebitoCpcDetalleFacturaPojo.getErpNotaCreditoDebito().getErpFactura();

            byte[] qrCode = getQRCode(erpNotaCreditoDebitoCpcDetalleFacturaPojo.getErpNotaCreditoDebito());
            byte[] nota = getNotaCreditoDebitoPdf(cpcFacturaEmitida, erpNotaCreditoDebitoCpcDetalleFacturaPojo, "Original Emisor - Nota de Cr"+"\u00E9"+"dito", qrCode);
            ResponseBuilder responsePDF = Response.ok((Object) nota);

            return responsePDF.build();
        } catch (Exception e) {
            log.error("ERROR:::", e);

            return Response.status(500).entity(e).build();
        }

    }
    
    ///{filas}/{magico}
    
    @GET
    @Path("/getFacturaExportacion/{idFactura}")
    @ApiOperation(value = "Facturacion de Exportacion")
    @Produces("application/pdf")
    public Response getFacturaExportacion(@PathParam(value = "idFactura") Long idFactura/*, @PathParam(value = "filas") Long filas, @PathParam(value = "magico") Long magico*/) {
        try {

             log.info("Recuperando datos para ::: " + idFactura);
             
//             if(magico == null)
//                magico = 15L;
//             if(filas == null)
//                 filas=0L;

            ErpFactura cpcFacturaEmitida = erpFacturaService.getCpcFacturaEmitidaById(idFactura);

            byte[] qrCode = getQRCode(cpcFacturaEmitida);

            byte[] original = getFacturaExportacionPdf(cpcFacturaEmitida, "ORIGINAL", qrCode, "facturaExportacion.jasper", 15/*,filas.intValue()*/);
            byte[] copiaEmisor = getFacturaExportacionPdf(cpcFacturaEmitida, "COPIA EMISOR", qrCode, "facturaExportacion.jasper", 15/*,filas.intValue()*/);

            byte[] res = mergePdfFiles(original, copiaEmisor);
            
            ResponseBuilder responsePDF = Response.ok((Object) res);

            return responsePDF.build();
        } catch (Exception e) {
            log.error("ERROR:::", e);

            return Response.status(500).entity(e).build();
        }

    }
    

    private byte[] getQRCode(ErpFactura cpcFacturaEmitida) throws Exception {
        try {
            String fechaEmision = DateUtils.dateFormat(cpcFacturaEmitida.getFechaFactura());

            String qrData = "147612027|" + cpcFacturaEmitida.getNumeroFactura() + "|" + cpcFacturaEmitida.getCpcDosificacion().getNumeroAutorizacion() + "|" + fechaEmision + "|"
                    + NumberUtils.redondeaBigDecimal(cpcFacturaEmitida.getMontoPrimeraMoneda(), 2) + "|"
                    + NumberUtils.redondeaBigDecimal(cpcFacturaEmitida.getMontoPrimeraMoneda(), 2) + "|" + cpcFacturaEmitida.getCodigoControl()
                    + "|" + cpcFacturaEmitida.getCppProveedorCliente().getNit() + "|0|0|0|0";

            /**
             * ****
             */
            log.info("qrCodeData:: " + qrData + "  size: " + qrData.length());

            ByteArrayOutputStream out = QRCode.from(qrData).to(ImageType.JPG).withSize(250, 250).stream();

            byte[] qrCode = out.toByteArray();
            //log.info("qrCode::::: " + qrCode);
            return qrCode;

        } catch (Exception e) {

            log.error("Jasper Error", e);

            throw new Exception(e);
        }

    }
    
    private byte[] getQRCode(ErpNotaCreditoDebito erpNotaCreditoDebito) throws Exception {
        try {
            String fechaEmision = DateUtils.dateFormat(erpNotaCreditoDebito.getFechaNotaCreditoDebito());

            String qrData = "147612027|" + erpNotaCreditoDebito.getNumeroNotaCreditoDebito() + "|" + erpNotaCreditoDebito.getCpcDosificacion().getNumeroAutorizacion() + "|" + fechaEmision + "|"
                    + NumberUtils.redondeaBigDecimal(erpNotaCreditoDebito.getImporteTotalPrimeraMoneda(), 2) + "|"
                    + NumberUtils.redondeaBigDecimal(erpNotaCreditoDebito.getImporteTotalPrimeraMoneda(), 2) + "|" + erpNotaCreditoDebito.getCodigoControlNotaDebitoCredito()
                    + "|" + erpNotaCreditoDebito.getErpFactura().getCppProveedorCliente().getNit() + "|0|0|0|0";

            /**
             * ****
             */
            log.info("qrCodeData:: " + qrData + "  size: " + qrData.length());

            ByteArrayOutputStream out = QRCode.from(qrData).to(ImageType.JPG).withSize(250, 250).stream();

            byte[] qrCode = out.toByteArray();
            //log.info("qrCode::::: " + qrCode);
            return qrCode;

        } catch (Exception e) {

            log.error("Jasper Error", e);

            throw new Exception(e);
        }

    }

    private byte[] getFacturaPdf(ErpFactura cpcFacturaEmitida, String tipoFactura, byte[] qrCode, String jasperReportFileName, int numRowsToComplete) throws Exception {
        try {
            
            ObjectUtils.printObjectState(cpcFacturaEmitida, ":::::::::::::::::::cpcFacturaEmitida::::::::::::::::");


            String fechaEmision = DateUtils.dateFormat(cpcFacturaEmitida.getFechaFactura());
            String cliente = cppProveedorClienteService.getNombreCliente(cpcFacturaEmitida.getCppProveedorCliente());

            InputStream stream = context.getResourceAsStream("/WEB-INF/reports/"+jasperReportFileName);

            String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/images") + File.separatorChar;

            log.info("realPath::: " + realPath);

            byte[] pdfFile = null;

            Map<String, Object> reportParam = new HashMap<String, Object>();

            InputStream qrInputStream = new ByteArrayInputStream(qrCode);

            /**
             * ********PARAMETROS********
             */
            reportParam.put("LUGAR_FECHA", "La Paz, " + DateUtils.convierteFechaALiteral(fechaEmision));
            reportParam.put("NIT_CLIENTE", cpcFacturaEmitida.getCppProveedorCliente().getNit());
            reportParam.put("CLIENTE", cliente);
            reportParam.put("NRO_FACTURA", cpcFacturaEmitida.getNumeroFactura());
            reportParam.put("NRO_AUTORIZACION", cpcFacturaEmitida.getCpcDosificacion().getNumeroAutorizacion());
            reportParam.put("LITERAL", NumberUtils.convierteNumeroALetra(cpcFacturaEmitida.getMontoPrimeraMoneda(), "BOLIVIANOS"));
            reportParam.put("CODIGO_CONTROL", cpcFacturaEmitida.getCodigoControl());
            reportParam.put("FECHA_LIMITE", DateUtils.dateFormat(cpcFacturaEmitida.getCpcDosificacion().getFechaLimiteEmision()));
            reportParam.put("TOTAL", NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getMontoPrimeraMoneda(), 2));
            reportParam.put("ACTIVIDAD", cpcFacturaEmitida.getCpcDosificacion().getCpcActividadEconomica().getDescripcion());
            reportParam.put("QR_DATA", qrInputStream);
            reportParam.put("TIPO_FACTURA", tipoFactura);
            reportParam.put("IMAGES_PATH", realPath);
            
            /**  DIRECCION SUCURSAL   ***/
            CpcSucursal sucursal= cpcFacturaEmitida.getCpcDosificacion().getCpcSucursal();
            reportParam.put("SUC_NRO", "SUCURSAL - "+sucursal.getNumeroSucursal());
            reportParam.put("SUC_DIRECCION", sucursal.getDireccion());
            reportParam.put("SUC_TELEFONOS", "Telefonos: "+sucursal.getTelefonoUno()+(sucursal.getTelefonoDos()!=null && !sucursal.getTelefonoDos().equals("")?" - "+sucursal.getTelefonoDos():""));
            reportParam.put("SUC_LOCALIDAD", sucursal.getParLocalizacion().getDescripcion()+" "+sucursal.getNombreLocalizacion());
            reportParam.put("SUC_CIUDAD", sucursal.getParDepartamento().getDescripcion()+" - BOLIVIA");
            
            //PARAMETROS PARA FACTURA DE EXPORTACION
            log.info("cpcFacturaEmitida.getParModalidadTransaccion()::: "+cpcFacturaEmitida.getParModalidadTransaccion().getCodigo());
            if(EnumTipoTransaccion.EXPORTACIONES.getCodigo().equals(cpcFacturaEmitida.getParTipoTransaccion().getCodigo()))
            {   reportParam.put("MONEDA_FACTURA", "$US");
            } 
            
            //Tiene Contrato?
            if (cpcFacturaEmitida.getCpcPagoContrato() == null)//Asumimos bolivianos
            {
                reportParam.put("MONEDA_CONTRATO", EnumTipoMoneda.BOLIVIANOS.getCodigo());
            } else {
                reportParam.put("MONEDA_CONTRATO", cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getParTipoMoneda());
            }
            reportParam.put("ESTADO_FACTURA", cpcFacturaEmitida.getParEstadoFactura().getCodigo());
            if (cpcFacturaEmitida.getCuentaBancaria() != null) {
                reportParam.put("DEPOSITO_CTA", "Depositar en la Cta. Cte.: " + cpcFacturaEmitida.getCuentaBancaria().getParBanco().getDescripcion() + " - Cuenta No " + cpcFacturaEmitida.getCuentaBancaria().getNumeroCuenta());
            }

            /**
             * *******DETAILS*******
             */
            //ObjectUtils.printObjectState(cpcFacturaEmitida, ":::::::::::::::::::cpcFacturaEmitida:::::::::::::::::::::");
            List<ErpDetalleFactura> detalles = cpcDetalleFacturaService.getCpcDetalleFacturaByFacturaEmitida(cpcFacturaEmitida);

            ErpDetalleFactura extra = new ErpDetalleFactura();
            if (cpcFacturaEmitida.getCpcPagoContrato() != null) {
                log.info("Agregando la nueva REF::: "+cpcFacturaEmitida.getReferencia());
                extra = new ErpDetalleFactura();
                extra.setDetalleFactura("REF. " + cpcFacturaEmitida.getReferencia());
                detalles.add(extra);

                extra = new ErpDetalleFactura();
                extra.setDetalleFactura("OC: " + cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getNroContratoCliente());
                detalles.add(extra);

                extra = new ErpDetalleFactura();
                extra.setDetalleFactura("CONTRATO HUAWEI: " + cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getNroContrato());
                detalles.add(extra);

                //Moneda Contrato
                if (cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getParTipoMoneda().getCodigo().equals(EnumTipoMoneda.DOLARES.getCodigo())) {
                    extra = new ErpDetalleFactura();
                    extra.setDetalleFactura("Importe en USD: " + NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getMontoSegundaMoneda(), 2));
                    detalles.add(extra);

                    extra = new ErpDetalleFactura();
                    extra.setDetalleFactura("Tasa de Cambio (Bs/USD): " + NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getTipoCambioFactura(), 2));
                    detalles.add(extra);
                }

            }
            /**
             * ******************Completar filas*********************
             */
            int loops = numRowsToComplete - detalles.size();
            log.info("Num of detalles before:: " + detalles.size());
            for (int i = 1; i <= loops; i++) {
                extra = new ErpDetalleFactura();
                extra.setDetalleFactura("");
                detalles.add(extra);
            }

            log.info("Num of detalles after:: " + detalles.size());

            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(detalles);

            /**
             * **********FILLING***********
             */
            log.info("reportParam::: " + reportParam);
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, reportParam, ds);

            /**
             * ***********EXPORT*************
             */
            pdfFile = JasperExportManager.exportReportToPdf(jasperPrint);
            return pdfFile;

        } catch (JRException e) {
            log.error("Jasper Error", e);

            throw new Exception(e);
        } catch (Exception e) {
            log.error("ERROR:::", e);

            throw new Exception(e);
        }

    }

    private byte[] getFacturaExportacionPdf(ErpFactura cpcFacturaEmitida, String tipoFactura, byte[] qrCode, String jasperReportFileName, int numRowsToComplete/*,int filas*/) throws Exception {
        try {
            
            ObjectUtils.printObjectState(cpcFacturaEmitida, ":::::::::::::::::::cpcFacturaEmitida::::::::::::::::");
            
            String fechaEmision = DateUtils.dateFormat(cpcFacturaEmitida.getFechaFactura());
            String cliente = cppProveedorClienteService.getNombreCliente(cpcFacturaEmitida.getCppProveedorCliente());

            InputStream stream = context.getResourceAsStream("/WEB-INF/reports/"+jasperReportFileName);

            String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/images") + File.separatorChar;

            log.info("realPath::: " + realPath);

            byte[] pdfFile = null;

            Map<String, Object> reportParam = new HashMap<String, Object>();

            InputStream qrInputStream = new ByteArrayInputStream(qrCode);

            /**
             * ********PARAMETROS********
             */
            reportParam.put("LUGAR_FECHA", "La Paz, " + DateUtils.convierteFechaALiteral(fechaEmision));
            reportParam.put("NIT_CLIENTE", cpcFacturaEmitida.getCppProveedorCliente().getNit());
            reportParam.put("CLIENTE", cliente);
            reportParam.put("DIRECCION_CLIENTE", cpcFacturaEmitida.getCppProveedorCliente().getDireccion());
            
            reportParam.put("NRO_FACTURA", cpcFacturaEmitida.getNumeroFactura());
            reportParam.put("NRO_AUTORIZACION", cpcFacturaEmitida.getCpcDosificacion().getNumeroAutorizacion());
            
            reportParam.put("MONEDA_TRANSACCION", "Dolar Americano");
            reportParam.put("TIPO_CAMBIO", "1 $US: Bs. "+NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getTipoCambioFactura(), 2));
            
            reportParam.put("LITERAL", NumberUtils.convierteNumeroALetra(cpcFacturaEmitida.getMontoPrimeraMoneda(), "BOLIVIANOS"));
            reportParam.put("LITERAL_SEGUNDA", NumberUtils.convierteNumeroALetra(cpcFacturaEmitida.getMontoSegundaMoneda(), "DOLARES AMERICANOS"));

            reportParam.put("CODIGO_CONTROL", cpcFacturaEmitida.getCodigoControl());
            reportParam.put("FECHA_LIMITE", DateUtils.dateFormat(cpcFacturaEmitida.getCpcDosificacion().getFechaLimiteEmision()));
            reportParam.put("TOTAL_PRIMERA_MONEDA", NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getMontoPrimeraMoneda(), 2));
            reportParam.put("TOTAL_SEGUNDA_MONEDA", NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getMontoSegundaMoneda(), 2));
            reportParam.put("ACTIVIDAD", cpcFacturaEmitida.getCpcDosificacion().getCpcActividadEconomica().getDescripcion());
            reportParam.put("QR_DATA", qrInputStream);
            reportParam.put("TIPO_FACTURA", tipoFactura);
            reportParam.put("IMAGES_PATH", realPath);
            
            /**  DIRECCION SUCURSAL   ***/
            CpcSucursal sucursal= cpcFacturaEmitida.getCpcDosificacion().getCpcSucursal();
            reportParam.put("SUC_NRO", "SUCURSAL - "+sucursal.getNumeroSucursal());
            reportParam.put("SUC_DIRECCION", sucursal.getDireccion());
            reportParam.put("SUC_TELEFONOS", "Telefonos: "+sucursal.getTelefonoUno()+(sucursal.getTelefonoDos()!=null && !sucursal.getTelefonoDos().equals("")?" - "+sucursal.getTelefonoDos():""));
            reportParam.put("SUC_LOCALIDAD", sucursal.getParLocalizacion().getDescripcion()+" "+sucursal.getNombreLocalizacion());
            reportParam.put("SUC_CIUDAD", sucursal.getParDepartamento().getDescripcion()+" - BOLIVIA");
            
            
            if(cpcFacturaEmitida.getGastosTransporte().compareTo(new BigDecimal("0"))==0)
            {   reportParam.put("GASTOS_TRANSPORTE", "0.00");
            }
            else
            {   reportParam.put("GASTOS_TRANSPORTE", NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getGastosTransporte(), 2));
            }
            
            if(cpcFacturaEmitida.getGastosSeguro().compareTo(new BigDecimal("0"))==0)
                reportParam.put("GASTOS_SEGURO", "0.00");
            else
                reportParam.put("GASTOS_SEGURO", NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getGastosSeguro(), 2));
            
            if(cpcFacturaEmitida.getTotalFob().compareTo(new BigDecimal("0"))==0)
                reportParam.put("TOTAL_FOB", "0.00");
            else
                reportParam.put("TOTAL_FOB", NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getTotalFob(), 2));
            
            if(cpcFacturaEmitida.getTransporteInternacional().compareTo(new BigDecimal("0"))==0)
                reportParam.put("TRANSPORTE", "0.00");
            else
                reportParam.put("TRANSPORTE", NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getTransporteInternacional(), 2));
            
            if(cpcFacturaEmitida.getSeguroInternacional().compareTo(new BigDecimal("0"))==0)
                reportParam.put("SEGURO", "0.00");
            else
                reportParam.put("SEGURO", NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getSeguroInternacional(), 2));
            
            if(cpcFacturaEmitida.getOtrosGastos().compareTo(new BigDecimal("0"))==0)
                reportParam.put("OTROS", "0.00");
            else
                reportParam.put("OTROS", NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getOtrosGastos(), 2));
            
            if(cpcFacturaEmitida.getValorBruto().compareTo(new BigDecimal("0"))==0)
                reportParam.put("VALOR_BRUTO", "0.00");
            else
                reportParam.put("VALOR_BRUTO", NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getValorBruto(), 2));
            
            
            
            
            //PARAMETROS PARA FACTURA DE EXPORTACION
            log.info("cpcFacturaEmitida.getParModalidadTransaccion()::: "+cpcFacturaEmitida.getParModalidadTransaccion().getCodigo());
            if(EnumTipoTransaccion.EXPORTACIONES.getCodigo().equals(cpcFacturaEmitida.getParTipoTransaccion().getCodigo()))
            {   reportParam.put("MONEDA_FACTURA", "$US");
            } 
            
            //Tiene Contrato?
            if (cpcFacturaEmitida.getCpcPagoContrato() == null)//Asumimos bolivianos
            {
                reportParam.put("MONEDA_CONTRATO", EnumTipoMoneda.BOLIVIANOS.getCodigo());
            } else {
                reportParam.put("MONEDA_CONTRATO", cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getParTipoMoneda());
            }
            reportParam.put("ESTADO_FACTURA", cpcFacturaEmitida.getParEstadoFactura().getCodigo());
            if (cpcFacturaEmitida.getCuentaBancaria() != null) {
                reportParam.put("DEPOSITO_CTA", "Depositar en la Cta. Cte.: " + cpcFacturaEmitida.getCuentaBancaria().getParBanco().getDescripcion() + " - Cuenta No " + cpcFacturaEmitida.getCuentaBancaria().getNumeroCuenta());
            }

            /**
             * *******DETAILS*******
             */
            //ObjectUtils.printObjectState(cpcFacturaEmitida, ":::::::::::::::::::cpcFacturaEmitida:::::::::::::::::::::");
            List<ErpDetalleFactura> detalles = cpcDetalleFacturaService.getCpcDetalleFacturaByFacturaEmitida(cpcFacturaEmitida);

            ErpDetalleFactura extra = new ErpDetalleFactura();
            if (cpcFacturaEmitida.getCpcPagoContrato() != null) {

                extra.setDetalleFactura("OC: " + cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getNroContratoCliente());
                detalles.add(extra);

                extra = new ErpDetalleFactura();
                extra.setDetalleFactura("CONTRATO HUAWEI: " + cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getNroContrato());
                detalles.add(extra);

                //Moneda Contrato
                if (cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getParTipoMoneda().equals(EnumTipoMoneda.DOLARES.getCodigo())) {
                    extra = new ErpDetalleFactura();
                    extra.setDetalleFactura("Importe en USD: " + NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getMontoSegundaMoneda(), 2));
                    detalles.add(extra);

                    extra = new ErpDetalleFactura();
                    extra.setDetalleFactura("Tasa de Cambio (Bs/USD): " + NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getTipoCambioFactura(), 2));
                    detalles.add(extra);
                }

            }
            /**
             * ******************Completar filas*********************
             */
            int loops = numRowsToComplete - detalles.size();
            log.info("Num of detalles before:: " + detalles.size());
            for (int i = 1; i <= loops; i++) {
                extra = new ErpDetalleFactura();
                extra.setDetalleFactura("");
                detalles.add(extra);
            }

            log.info("Num of detalles after:: " + detalles.size());
            
//            detalles=new ArrayList<ErpDetalleFactura>();
//            
//            for (int i = 1; i <= filas; i++) {
//                            extra = new ErpDetalleFactura();
//                extra.setDetalleFactura("FUCK!!!!"+i);
//                detalles.add(extra);
//
//            }
//            log.info("DETALLES SIZE::::: "+detalles.size());
//            int mod=detalles.size() % numRowsToComplete;
//            if(mod > 0)
//            {   for (int i = 1; i <= numRowsToComplete - mod ; i++) { //+ 18 + 10
//                            extra = new ErpDetalleFactura();
//                extra.setDetalleFactura("FUCK!!!!"+i);
//                detalles.add(extra);
//
//                }
//            }
//            log.info("DETALLES SIZE::::: "+detalles.size());

            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(detalles);

            /**
             * **********FILLING***********
             */
            log.info("reportParam::: " + reportParam);
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, reportParam, ds);

            /**
             * ***********EXPORT*************
             */
            pdfFile = JasperExportManager.exportReportToPdf(jasperPrint);
            return pdfFile;

        } catch (JRException e) {
            log.error("Jasper Error", e);

            throw new Exception(e);
        } catch (Exception e) {
            log.error("ERROR:::", e);

            throw new Exception(e);
        }

    }

    
    private byte[] getNotaCreditoDebitoPdf(ErpFactura cpcFacturaEmitida, ErpNotaCreditoDebitoCpcDetalleFacturaPojo erpNotaCreditoDebitoPojo, String tipoFactura, byte[] qrCode) throws Exception {
        try {
            ErpNotaCreditoDebito erpNotaCreditoDebito=erpNotaCreditoDebitoPojo.getErpNotaCreditoDebito();
            
            String fechaEmisionNotaFiscal = DateUtils.dateFormat(erpNotaCreditoDebito.getFechaNotaCreditoDebito());
            String fechaEmisionFactura = DateUtils.dateFormat(cpcFacturaEmitida.getFechaFactura());
            
            String cliente = cppProveedorClienteService.getNombreCliente(cpcFacturaEmitida.getCppProveedorCliente());

            InputStream stream = context.getResourceAsStream("/WEB-INF/reports/creditoDebito.jasper");

            String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/images") + File.separatorChar;

            byte[] pdfFile = null;

            Map<String, Object> reportParam = new HashMap<String, Object>();

            InputStream qrInputStream = new ByteArrayInputStream(qrCode);

            /**
             * ********PARAMETROS********
             */
            reportParam.put("LUGAR_FECHA", "La Paz, " + DateUtils.convierteFechaALiteral(fechaEmisionNotaFiscal));
            reportParam.put("NIT_CLIENTE", cpcFacturaEmitida.getCppProveedorCliente().getNit());
            reportParam.put("CLIENTE", cliente);

            reportParam.put("NRO_FACTURA", cpcFacturaEmitida.getNumeroFactura());
            reportParam.put("NRO_AUTORIZACION", cpcFacturaEmitida.getCpcDosificacion().getNumeroAutorizacion());
            reportParam.put("FECHA_FACTURA", fechaEmisionFactura);
            reportParam.put("TOTAL_FACTURA", NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getMontoPrimeraMoneda(), 2));

            reportParam.put("NRO_NOTA_FISCAL", erpNotaCreditoDebito.getNumeroNotaCreditoDebito());
            reportParam.put("NRO_AUTO_NOTA_FISCAL", erpNotaCreditoDebito.getCpcDosificacion().getNumeroAutorizacion());
            reportParam.put("TOTAL_NOTA_FISCAL", NumberUtils.decimalFormatBigDecimal(erpNotaCreditoDebito.getImporteTotalPrimeraMoneda(), 2));
            reportParam.put("CREDITO_TOTAL_NOTA_FISCAL", NumberUtils.decimalFormatBigDecimal(erpNotaCreditoDebito.getImporteTotalPrimeraMoneda().multiply(new BigDecimal("0.13")), 2));

            reportParam.put("LITERAL", NumberUtils.convierteNumeroALetra(erpNotaCreditoDebito.getImporteTotalPrimeraMoneda(), "BOLIVIANOS"));
            reportParam.put("CODIGO_CONTROL", erpNotaCreditoDebito.getCodigoControlNotaDebitoCredito());
            reportParam.put("FECHA_LIMITE", DateUtils.dateFormat(erpNotaCreditoDebito.getCpcDosificacion().getFechaLimiteEmision()));
            
            reportParam.put("ACTIVIDAD", cpcFacturaEmitida.getCpcDosificacion().getCpcActividadEconomica().getDescripcion());
            reportParam.put("QR_DATA", qrInputStream);
            reportParam.put("TIPO_NOTA", tipoFactura);
            reportParam.put("IMAGES_PATH", realPath);

            //Tiene Contrato?
            if (cpcFacturaEmitida.getCpcPagoContrato() == null)//Asumimos bolivianos
            {
                reportParam.put("MONEDA_CONTRATO", EnumTipoMoneda.BOLIVIANOS.getCodigo());
            } else {
                reportParam.put("MONEDA_CONTRATO", cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getParTipoMoneda());
            }
            reportParam.put("ESTADO_FACTURA", cpcFacturaEmitida.getParEstadoFactura().getCodigo());
            if (cpcFacturaEmitida.getCuentaBancaria() != null) {
                reportParam.put("DEPOSITO_CTA", "Depositar en la Cta. Cte.: " + cpcFacturaEmitida.getCuentaBancaria().getParBanco().getDescripcion() + " - Cuenta No " + cpcFacturaEmitida.getCuentaBancaria().getNumeroCuenta());
            }

            /**
             * *******DETAILS*******
             */
            //ObjectUtils.printObjectState(cpcFacturaEmitida, ":::::::::::::::::::cpcFacturaEmitida:::::::::::::::::::::");
            List<ErpDetalleFactura> datos = new ArrayList<ErpDetalleFactura>();
            
            //Agregamos el detalle de la transaccion original a los datos
            List<ErpDetalleFactura> detalles = cpcDetalleFacturaService.getCpcDetalleFacturaByFacturaEmitida(cpcFacturaEmitida);
            for (ErpDetalleFactura detalle : detalles) {
                detalle.setCodigo("DATOS DE LA TRANSACCION ORIGINAL");
                datos.add(detalle);
            }
            
             /**
             * ******************Completar filas*********************
             */
            ErpDetalleFactura extra = new ErpDetalleFactura();
            int numRows = 6;
            int loops = numRows - detalles.size();
            log.info("DATOS DE LA TRANSACCION ORIGINAL Num of detalles before:: " + datos.size()+" loops::: "+loops);
            for (int i = 1; i <= loops; i++) {
                extra = new ErpDetalleFactura();
                extra.setCodigo("DATOS DE LA TRANSACCION ORIGINAL");
                extra.setDetalleFactura("");
                datos.add(extra);
            }
            
            log.info("DATOS AFTER ADDING:: " + datos.size());
            
            
            //Agregamos el detalle de la nota de credito-debito
            List<ErpDetalleFactura> detalleNC=erpNotaCreditoDebitoPojo.getListaCpcDetalleFactura();
            for (ErpDetalleFactura detalle : detalleNC) {
                detalle.setCodigo("DETALLE DE LA DEVOLUCION O RESCISION DE SERVICIO");
                datos.add(detalle);
            }
            
            log.info("DETALLE DE LA DEVOLUCION O RESCISION DE SERVICIO:: " + datos.size());
            
             /**
             * ******************Completar filas*********************
             */
            loops = numRows - detalleNC.size();
            log.info("Num of detalles before:: " + detalleNC.size()+" loops::: "+loops);
            for (int i = 1; i <= loops; i++) {
                extra = new ErpDetalleFactura();
                extra.setCodigo("DETALLE DE LA DEVOLUCION O RESCISION DE SERVICIO");
                extra.setDetalleFactura("");
                datos.add(extra);
            }
             log.info("DATOS AFTER ADDING:: " + datos.size());
            
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(datos);
            /**
             * **********FILLING***********
             */
            log.info("reportParam::: " + reportParam);
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, reportParam, ds);

            /**
             * ***********EXPORT*************
             */
            pdfFile = JasperExportManager.exportReportToPdf(jasperPrint);
            return pdfFile;

        } catch (JRException e) {
            log.error("Jasper Error", e);

            throw new Exception(e);
        } catch (Exception e) {
            log.error("ERROR:::", e);

            throw new Exception(e);
        }

    }

    private byte[] mergePdfFiles(byte[] original, byte[] copiaEmisor) throws Exception {

        try {
            PDFMergerUtility ut = new PDFMergerUtility();
            ut.addSource(new ByteArrayInputStream(original));
            ut.addSource(new ByteArrayInputStream(copiaEmisor));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ut.setDestinationStream(baos);
            ut.mergeDocuments();
            baos = (ByteArrayOutputStream) ut.getDestinationStream();
            return baos.toByteArray();
        } catch (IOException e) {
            log.error("Error merging PDF files: ", e);
            throw new Exception(e);
        } catch (COSVisitorException ex) {
            log.error("Error merging PDF files: ", ex);
            throw new Exception(ex);
        }

    }
    
    @GET
    @Path("/getPlanillaSueldos/{idPeriodoGestion}")
    @ApiOperation(value = "Planilla Sueldos y Salarios")
    @Produces("application/pdf")
    public Response getPlanillaSueldos(@PathParam(value = "idPeriodoGestion") Long idPeriodoGestion) {

        try {

            List<RhPlanillaSueldos> planillaList=rhPlanillaSueldosService.cargaPlanillaSueldosPorIdPeriodoGestionNoContabilizada(idPeriodoGestion);

            byte[] planilla = getPlanillaPdf(idPeriodoGestion, "rhPlanillaSueldos.jasper");
            
            
            ResponseBuilder responsePDF = Response.ok((Object) planilla);

            return responsePDF.build();

        } catch (Exception e) {
            log.error("ERROR:::", e);

            return Response.status(500).entity(e).build();
        }

    }
    
    private byte[] getPlanillaPdf(Long idPeriodoGestion, String jasperReportFileName) throws Exception {
        try {
            InputStream stream = context.getResourceAsStream("/WEB-INF/reports/"+jasperReportFileName);
            String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/images") + File.separatorChar;
            log.info("realPath::: " + realPath);
            byte[] pdfFile = null;
            Map<String, Object> reportParam = new HashMap<String, Object>();

            /****DATOS***/
            List<RhPlanillaSueldos> planillaList=rhPlanillaSueldosService.cargaPlanillaSueldosPorIdPeriodoGestionNoContabilizada(idPeriodoGestion);

            
            
            /**
             * ********PARAMETROS********
             */
//            reportParam.put("LUGAR_FECHA", "La Paz, " + DateUtils.convierteFechaALiteral(fechaEmision));
//            reportParam.put("NIT_CLIENTE", cpcFacturaEmitida.getCppProveedorCliente().getNit());
//            reportParam.put("CLIENTE", cliente);
//            reportParam.put("NRO_FACTURA", cpcFacturaEmitida.getNumeroFactura());
//            reportParam.put("NRO_AUTORIZACION", cpcFacturaEmitida.getCpcDosificacion().getNumeroAutorizacion());
//            reportParam.put("LITERAL", NumberUtils.convierteNumeroALetra(cpcFacturaEmitida.getMontoPrimeraMoneda(), "BOLIVIANOS"));
//            reportParam.put("CODIGO_CONTROL", cpcFacturaEmitida.getCodigoControl());
//            reportParam.put("FECHA_LIMITE", DateUtils.dateFormat(cpcFacturaEmitida.getCpcDosificacion().getFechaLimiteEmision()));
//            reportParam.put("TOTAL", NumberUtils.decimalFormatBigDecimal(cpcFacturaEmitida.getMontoPrimeraMoneda(), 2));
//            reportParam.put("ACTIVIDAD", cpcFacturaEmitida.getCpcDosificacion().getCpcActividadEconomica().getDescripcion());
//            reportParam.put("QR_DATA", qrInputStream);
//            reportParam.put("TIPO_FACTURA", tipoFactura);
//            reportParam.put("IMAGES_PATH", realPath);
            
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(planillaList);

            /**
             * **********FILLING***********
             */
            log.info("reportParam::: " + reportParam);
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, reportParam, ds);

            /**
             * ***********EXPORT*************
             */
            pdfFile = JasperExportManager.exportReportToPdf(jasperPrint);
            return pdfFile;

        } catch (JRException e) {
            log.error("Jasper Error", e);

            throw new Exception(e);
        } catch (Exception e) {
            log.error("ERROR:::", e);

            throw new Exception(e);
        }

    }

}
