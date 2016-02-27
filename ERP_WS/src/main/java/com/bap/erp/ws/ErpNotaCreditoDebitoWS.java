/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.ErpDetalleFactura;
import com.bap.erp.modelo.ErpNotaCreditoDebito;
import com.bap.erp.modelo.pojo.CpcLibroDeComprasPojo;
import com.bap.erp.modelo.pojo.ErpNotaCreditoDebitoCpcDetalleFacturaPojo;
import com.bap.erp.servicios.ErpNotaCreditoDebitoService;
import com.bap.erp.servicios.cpp.CppNotaCreditoDebitoService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Path("/erpNotaCreditoDebito")
@Api(value = "erpNotaCreditoDebito", description = "WS for erpNotaCreditoDebito")
public class ErpNotaCreditoDebitoWS {

    @Autowired
    private ErpNotaCreditoDebitoService erpNotaCreditoDebitoService;
    @Autowired
    private CppNotaCreditoDebitoService cppNotaCreditoDebitoService;

    public ErpNotaCreditoDebitoWS() {

    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a ErpFactura")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putErpNotaCreditoDebito(@ApiParam(value = "erpNotaCreditoDebito", required = true) ErpNotaCreditoDebito erpNotaCreditoDebito) {
        try {
            erpNotaCreditoDebito = erpNotaCreditoDebitoService.persistErpNotaCreditoDebito(erpNotaCreditoDebito);
            return Response.status(200).entity(erpNotaCreditoDebito).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PUT
    @Path("/putErpNotaCreditoDebitoCpcDetalleFacturaPojo")
    @ApiOperation(value = "operation to INSERT a ErpNotaCreditoDebitoCpcDetalleFacturaPojo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putErpNotaCreditoDebitoCpcDetalleFacturaPojo(@ApiParam(value = "erpNotaCreditoDebitoCpcDetalleFacturaPojo", required = true) ErpNotaCreditoDebitoCpcDetalleFacturaPojo erpNotaCreditoDebitoCpcDetalleFacturaPojo) {
        try {
            ErpNotaCreditoDebito erpNotaCreditoDebito = erpNotaCreditoDebitoService.persistErpNotaCreditoDebitoCpcDetalleFacturaPojo(erpNotaCreditoDebitoCpcDetalleFacturaPojo);
            return Response.status(200).entity(erpNotaCreditoDebito).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getErpNotaCreditoDebito/{idNotaCreditoDebito}")
    @ApiOperation(value = "GET ErpNotaCreditoDebito")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getErpNotaCreditoDebito(@PathParam(value = "idNotaCreditoDebito") Long idNotaCreditoDebito) {
        try {
            ErpNotaCreditoDebitoCpcDetalleFacturaPojo erpNotaCreditoDebitoCpcDetalleFacturaPojo = erpNotaCreditoDebitoService.getErpNotaCreditoDebito(idNotaCreditoDebito);

            return Response.status(200).entity(erpNotaCreditoDebitoCpcDetalleFacturaPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getNumeroNotaCreditoDebito/{idDosificacion}")
    @ApiOperation(value = "Retrieves the next factura's number")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumeroNotaCreditoDebito(@PathParam(value = "idDosificacion") Long idDosificacion) {
        try {
            Long numeroNotaCreditoDebito = erpNotaCreditoDebitoService.generaNumeroNotaCreditoDebito(idDosificacion);
            return Response.status(200).entity(numeroNotaCreditoDebito).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getErpNotaCreditoDebitoByIdFactura/{idFactura}")
    @ApiOperation(value = "Retrieves ErpNotaCreditoDebito by IdFactura")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getErpNotaCreditoDebitoByIdFactura(@PathParam(value = "idFactura") Long idFactura) {
        try {
            List<ErpNotaCreditoDebito> lista = erpNotaCreditoDebitoService.getAllErpNotaCreditoDebitoByIdFatura(idFactura);
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getNotaDebitoCreditoValidasByMonthyYear/{month}/{year}")
    @ApiOperation(value = "Retrieves a ErpFactura by Month and Year")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotaDebitoCreditoValidasByMonthyYear(@PathParam("month") int month, @PathParam("year") int year) {
        try {
            List<CpcLibroDeComprasPojo> cpcFacturaEmitida = cppNotaCreditoDebitoService.libroDeComprasNotaCreditoDebito(month, year);
            return Response.status(200).entity(cpcFacturaEmitida).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}
