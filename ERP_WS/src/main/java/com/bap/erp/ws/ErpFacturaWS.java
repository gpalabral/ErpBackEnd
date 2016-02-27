/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.commons.utils.DateUtils;
import com.bap.erp.commons.utils.NumberUtils;
import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.modelo.pojo.CodigoControlPojo;
import com.bap.erp.modelo.pojo.CpcConciliacionContablePojo;
import com.bap.erp.modelo.pojo.CpcFacturaEmitidaCpcDetalleFacturaPojo;
import com.bap.erp.modelo.pojo.CpcFacturaEmitidaPojo;
import com.bap.erp.modelo.pojo.CpcLibroDeBancarizacionPorVentasPojo;
import com.bap.erp.modelo.pojo.CpcLibroDeVentasHuaweiPojo;
import com.bap.erp.modelo.pojo.CpcLibroDeVentasPojo;
import com.bap.erp.modelo.pojo.CppLibroDeComprasPojo;
import com.bap.erp.modelo.pojo.ErpFacturaRetencionPojo;
import com.bap.erp.modelo.pojo.ErpFacturasDatosPojo;
import com.bap.erp.servicios.ErpFacturaService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jonas
 */
@Component
@Path("/erpFactura")
@Api(value = "erpFactura", description = "WS for erpFactura")
public class ErpFacturaWS {
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ErpFacturaWS.class);

    @Autowired
    private ErpFacturaService erpFacturaService;

    public ErpFacturaWS() {

    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a ErpFactura")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCpcDosificaciones(@ApiParam(value = "cpcFacturaEmitida", required = true) ErpFactura cpcFacturaEmitida) {
        try {
            cpcFacturaEmitida = erpFacturaService.guardaFacturaEmitida(cpcFacturaEmitida);
            return Response.status(200).entity(cpcFacturaEmitida).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }

    }

    @GET
    @Path("/getCpcFacturaEmitidaById/{idFacturaEmitida}")
    @ApiOperation(value = "Retrieves a ErpFactura by idFacturaEmitida")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcFacturaEmitidaById(@PathParam("idFacturaEmitida") Long idFacturaEmitida) {
        try {
            ErpFactura cpcFacturaEmitida = erpFacturaService.getCpcFacturaEmitidaById(idFacturaEmitida);
            return Response.status(200).entity(cpcFacturaEmitida).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @POST
    @Path("/generaCodigoDeControl")
    @ApiOperation(value = "Generates the control code")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generaCodigoDeControl(@FormParam("nit") Long nit,
            @FormParam("numeroFactura") String numeroFactura,
            @FormParam("numeroAutorizacion") String numeroAutorizacion,
            @FormParam("fechaFactura") String fechaFactura,
            @FormParam("monto") BigDecimal monto,
            @FormParam("llaveDosificacion") String llaveDosificacion) {

        Date fechaFinal = new Date(fechaFactura);

        try {
            String codigoDeControl = erpFacturaService.genereCodigoDeControl(nit, numeroFactura, numeroAutorizacion, fechaFinal, monto, llaveDosificacion);
            CodigoControlPojo codigoControlPojo = new CodigoControlPojo();
            codigoControlPojo.setCodigoControl(codigoDeControl);
            return Response.status(200).entity(codigoControlPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PUT
    @Path("/putCpcFacturaEmitidaCpcDetalleFacturaPojo")
    @ApiOperation(value = "operation to INSERT a CpcFacturaEmitidaCpcDetalleFacturaPojo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCpcFacturaEmitidaCpcDetalleFacturaPojo(@ApiParam(value = "cpcFacturaEmitidaCpcDetalleFacturaPojo", required = true) CpcFacturaEmitidaCpcDetalleFacturaPojo cpcFacturaEmitidaCpcDetalleFacturaPojo) {
        try {
            
            log.info("cpcFacturaEmitidaCpcDetalleFacturaPojo:: "+cpcFacturaEmitidaCpcDetalleFacturaPojo);
            
            ErpFactura cpcFacturaEmitida = erpFacturaService.persistCpcFacturaEmitidaCpcDetalleFacturaPojo(cpcFacturaEmitidaCpcDetalleFacturaPojo);
            return Response.status(200).entity(cpcFacturaEmitida).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCpcFacturaEmitida")
    @ApiOperation(value = "Retrieves a ErpFactura")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcFacturaEmitida() {
        try {
            List<ErpFactura> lista = erpFacturaService.getListCpcFacturaEmitida();
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }

    }

    @GET
    @Path("/getCpcFacturaEmitidaByMonthyYear/{month}/{year}")
    @ApiOperation(value = "Retrieves a ErpFactura by Month and Year")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcFacturaEmitidaByMonthyYear(@PathParam("month") int month, @PathParam("year") int year) {
        try {

            List<CpcFacturaEmitidaPojo> cpcFacturaEmitida = erpFacturaService.getCpcFacturaEmitidaPorMesyAnio(month, year);
            return Response.status(200).entity(cpcFacturaEmitida).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getLibroDeVentas/{month}/{year}")
    @ApiOperation(value = "Retrieves a List of ErpFactura for Libro de Ventas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLibroDeVentas(@PathParam("month") int month, @PathParam("year") int year) {
        try {
            List<CpcLibroDeVentasPojo> listaCpcLibroDeVentasPojo = erpFacturaService.getLibroDeVentasPojo(month, year);
            return Response.status(200).entity(listaCpcLibroDeVentasPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getFacturaEmitidaNombreConcatenadoById/{idFacturaEmitida}")
    @ApiOperation(value = "Retrieves a CpcFacturaEmitidaPojo by idFacturaEmitida")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFacturaEmitidaNombreConcatenadoById(@PathParam("idFacturaEmitida") Long idFacturaEmitida) {
        try {
            CpcFacturaEmitidaPojo cpcFacturaEmitidaPojo = erpFacturaService.getFacturaEmitidaNombreConcatenadoById(idFacturaEmitida);
            return Response.status(200).entity(cpcFacturaEmitidaPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getListaCpcFacturaEmitidaById/{idPagoContrato}")
    @ApiOperation(value = "Retrieves a CpcPagoContrato")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaCpcFacturaEmitidaById(@PathParam("idPagoContrato") Long idPagoContrato) {
        try {
            List<ErpFactura> cpcFacturaEmitida = erpFacturaService.getListaCpcFacturaEmitidaById(idPagoContrato);
            return Response.status(200).entity(cpcFacturaEmitida).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getVerificaFacturaParaDosificacion/{idDosificacion}")
    @ApiOperation(value = "Retrieves a CpcDosificacion Boolean IdDosificacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVerificaFacturaParaDosificacionByIdDosificacion(@PathParam("idDosificacion") Long idDosificacion) {
        try {
            Boolean valor = erpFacturaService.getVerificaFacturaParaDosificacionesByIdDosificacion(idDosificacion);
            return Response.status(200).entity(valor).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getNumeroALiteral/{numero}/{tipoMoneda}")
    @ApiOperation(value = "Retrieves tax calculations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumeroALiteral(@PathParam(value = "numero") BigDecimal numero, @PathParam(value = "tipoMoneda") String tipoMoneda) {
        try {
            List<String> listaLiteral = new ArrayList<String>();
//            String literal = numero.toString();
            String literal = NumberUtils.convierteNumeroALetra(numero, tipoMoneda);
            listaLiteral.add(literal);
            return Response.status(200).entity(listaLiteral).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCpcNotaDebitoCreditoByMonthAndYear/{mes}/{anio}/{codigoDocMercantil}/{estadoPago}/{codigoEstadoFactura}")
    @ApiOperation(value = "Retrieves a ErpFactura by Month and Year for Notas de Debito")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcNotaDebitoCreditoByMonthAndYear(@PathParam("mes") int mes, @PathParam("anio") int anio, @PathParam("codigoDocMercantil") String codigoDocMercantil, @PathParam("estadoPago") String estadoPago, @PathParam("codigoEstadoFactura") String codigoEstadoFactura) {
        try {
            List<CpcFacturaEmitidaPojo> cpcFacturaEmitida = erpFacturaService.getCpcNotaDebitoCreditoByMesYAnio(mes, anio, codigoDocMercantil, estadoPago, codigoEstadoFactura);
            return Response.status(200).entity(cpcFacturaEmitida).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getGeneraXML/{idFacturaEmitida}")
    @ApiOperation(value = "Retrieves all getGeneraXML By IdFacturaEmitida")
    //@Produces(MediaType.APPLICATION_XML)
    public Response getGeneraXML(@PathParam("idFacturaEmitida") Long idFacturaEmitida) {
        try {
            String xmlString = erpFacturaService.getGeneraXML(idFacturaEmitida);
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));

            if (stream == null) {
                throw new Exception("No existe factura con id: " + idFacturaEmitida);
            }
            return Response.status(200).type(MediaType.TEXT_XML).entity(stream).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getGeneraXMLtexto/{idFacturaEmitida}")
    @ApiOperation(value = "Retrieves all getGeneraXML By IdFacturaEmitida")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getGeneraXMLtexto(@PathParam("idFacturaEmitida") Long idFacturaEmitida) {
        try {
            String xmlString = erpFacturaService.getGeneraXML(idFacturaEmitida);
            return Response.status(200).entity(xmlString).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/edit")
    @ApiOperation(value = "edit ErpFactura")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@ApiParam("ErpFactura") ErpFactura cpcFacturaEmitida) {
        try {
            ErpFactura nuevaFacturaEmitida = erpFacturaService.getCpcFacturaEmitidaById(cpcFacturaEmitida.getIdFactura());
            nuevaFacturaEmitida.setParEstadoFactura(cpcFacturaEmitida.getParEstadoFactura());
            nuevaFacturaEmitida.setNroFacturaInterno(cpcFacturaEmitida.getNroFacturaInterno());
            nuevaFacturaEmitida.setParEstadoPago(cpcFacturaEmitida.getParEstadoPago());
            nuevaFacturaEmitida.setNroContrato(cpcFacturaEmitida.getNroContrato());
            nuevaFacturaEmitida.setFechaAceptacion(cpcFacturaEmitida.getFechaAceptacion());
            cpcFacturaEmitida = erpFacturaService.mergeCpcFacturaEmitida(nuevaFacturaEmitida);
            return Response.status(200).entity(cpcFacturaEmitida).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getLibroDeBancarizacionPorVentas/{month}/{year}")
    @ApiOperation(value = "Retrieves a List of ErpFactura for Libro de Ventas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLibroDeBancarizacionPorVentas(@PathParam("month") int month, @PathParam("year") int year) {
        try {
            List<CpcLibroDeBancarizacionPorVentasPojo> lista = erpFacturaService.getLibroDeBancarizacionPorVentas(month, year);
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCpcFacturaEmitidaByCliente/{idCliente}")
    @ApiOperation(value = "Retrieves a ErpFactura by Month and Year")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcFacturaEmitidaByCliente(@PathParam("idCliente") Long idCliente) {
        try {
            List<CpcFacturaEmitidaPojo> cpcFacturaEmitida = erpFacturaService.getCpcFacturaEmitidaPorCliente(idCliente);
            return Response.status(200).entity(cpcFacturaEmitida).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getLibroDeVentasHuawei/{month}/{year}")
    @ApiOperation(value = "Libro de Ventas de Huawei")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLibroDeVentasHuawei(@PathParam("month") int month, @PathParam("year") int year) {
        try {
            List<CpcLibroDeVentasHuaweiPojo> listaCpcLibroDeVentasPojo = erpFacturaService.getLibroDeVentasHuawei(0L, month, year);
            return Response.status(200).entity(listaCpcLibroDeVentasPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getGestionesFacturadas/{modulo}")
    @ApiOperation(value = "Libro de Ventas de Huawei")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGestionesFacturadas(@PathParam("modulo") String modulo) {
        try {
            List<Integer> gestiones = erpFacturaService.getGestionesFacturadas(modulo);
            return Response.status(200).entity(gestiones).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PUT
    @Path("/putReferenciacionContable")
    @ApiOperation(value = "operacion para referenciar una factura")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putReferenciacionContable(@ApiParam(value = "listaConciliacion", required = true) List<CpcConciliacionContablePojo> listaConciliacion) {
        try {
            erpFacturaService.persistFacturasReferenciadas(listaConciliacion);
            return Response.status(200).entity(true).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

//    @GET
//    @Path("/getReportesControlDeIngresos/{idProveedorCliente}/{month}/{year}")
//    @ApiOperation(value = "Libro de Ventas de Huawei")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getReportesControlDeIngresos(@PathParam("idProveedorCliente") Long idProveedorCliente, @PathParam("month") int month, @PathParam("year") int year) {
//        try {            
//            List<CpcLibroDeVentasHuaweiPojo> listaCpcLibroDeVentasPojo = erpFacturaService.getLibroDeVentasHuawei(idProveedorCliente, month, year);
//            return Response.status(200).entity(listaCpcLibroDeVentasPojo).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(500).entity(e).build();
//        }
//    }
    @GET
    @Path("/getReporteControlDeIngresos/{fechaInicial}/{fechaFinal}")
    @ApiOperation(value = "Reporte de Control de Igresos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReporteControlDeIngresos(@PathParam("fechaInicial") Long fechaInicial, @PathParam("fechaFinal") Long fechaFinal) {
        try {
            List<CpcLibroDeVentasHuaweiPojo> listaCpcLibroDeVentasPojo = erpFacturaService.getReporteControlDeIngresos(DateUtils.convertToDate(fechaInicial), DateUtils.convertToDate(fechaFinal));
            return Response.status(200).entity(listaCpcLibroDeVentasPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getLibroDeCompras/{mes}/{anio}")
    @ApiOperation(value = "Retrieves a List of ErpFactura for Libro de Compras")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLibroDeCompras(@PathParam("mes") int mes, @PathParam("anio") int anio) {
        try {
            List<CppLibroDeComprasPojo> listaCpcLibroDeComprasPojo = erpFacturaService.getLibroDeComprasPojo(mes, anio);
            return Response.status(200).entity(listaCpcLibroDeComprasPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getFacturaRetencionParaBancarizacion/{idProveedorCliente}")
    @ApiOperation(value = "Retrieves a List of ErpFactura and Retencion for Bancarizacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFacturaRetencionParaBancarizacion(@PathParam("idProveedorCliente") Long idProveedorCliente) {
        try {
            List<ErpFacturaRetencionPojo> listaFacturaRetencion = erpFacturaService.getFacturaRetencionByIdProveedorCliente(idProveedorCliente);
            return Response.status(200).entity(listaFacturaRetencion).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/noBancarizarFacturaRetencion/{idFacturaRetencion}/{tipoDocumentoMercantil}")
    @ApiOperation(value = "no Bancarizar Factura o Retencion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response noBancarizarFacturaRetencion(@PathParam("idFacturaRetencion") Long idFacturaRetencion, @PathParam("tipoDocumentoMercantil") String tipoDocumentoMercantil) {
        try {
            erpFacturaService.noBancarizaFacturaRetencion(idFacturaRetencion, tipoDocumentoMercantil);
            return Response.status(200).entity(true).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getLibroDeBancarizacionPorCompras/{mes}/{anio}")
    @ApiOperation(value = "Retrieves a List of ErpFacturaRetencion for Libro de Compras")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLibroDeBancarizacionPorCompras(@PathParam("mes") int mes, @PathParam("anio") int anio) {
        try {
            List<CpcLibroDeBancarizacionPorVentasPojo> lista = erpFacturaService.getLibroDeBancarizacionPorCompras(mes, anio);
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    
    @GET
    @Path("/getGestionesDeFacturasRetencionesBancarizadas")
    @ApiOperation(value = "Gestiones con facturas o retenciones Bancarizadas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGestionesDeFacturasRetencionesBancarizadas() {
        try {
            List<Integer> gestiones = erpFacturaService.getGestionesDeFacturasRetencionesBancarizadas();
            return Response.status(200).entity(gestiones).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    
    @PUT
    @Path("/putFacturasBancarias")
    @ApiOperation(value = "operation to INSERT a ErpFactura")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putFacturasBancarias(@ApiParam(value = "erpFacturasDatosPojo", required = true) ErpFacturasDatosPojo erpFacturasDatosPojo) {
        try {
             erpFacturaService.guardaFacturasBancarias(erpFacturasDatosPojo);
            return Response.status(200).entity(erpFacturasDatosPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }

    }
}
