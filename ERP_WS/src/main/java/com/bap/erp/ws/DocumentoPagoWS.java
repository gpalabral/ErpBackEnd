/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.commons.utils.ObjectUtils;
import com.bap.erp.modelo.ban.DocumentoPago;
import com.bap.erp.modelo.pojo.DocumentoPagoFacturasPojo;
import com.bap.erp.servicios.ban.DocumentoPagoService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
import java.math.BigDecimal;
import java.util.List;
import javax.ws.rs.Consumes;
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
 * @author Javier
 */
@Component
@Path("/documentoPago")
@Api(value = "documentoPago", description = "WS for DocumentoPago")
public class DocumentoPagoWS {

    @Autowired
    private DocumentoPagoService documentoPagoService;

    public DocumentoPagoWS() {

    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all documentoPago")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DocumentoPago() {
        try {
            List<DocumentoPago> list = documentoPagoService.getDocumentoPago();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a documentoPago")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putDocumentoPago(@ApiParam(value = "documento_Pago", required = true) DocumentoPago documentoPago) {
        try {
            System.out.println("primera:::");
            ObjectUtils.printObjectState(documentoPago);
            documentoPago = documentoPagoService.persistDocumentoPago(documentoPago);
            return Response.status(200).entity(documentoPago).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getDocumentoPagoPorId/{idDocumentoPago}")
    @ApiOperation(value = "get DocumentoPago By idDocumentoPago")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDocumentoPagoPorId(@PathParam("idDocumentoPago") Long idDocumentoPago) {
        try {
            DocumentoPago documentoPago = documentoPagoService.getDocumentoPagoById(idDocumentoPago);
            return Response.status(200).entity(documentoPago).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/edit")
    @ApiOperation(value = "edit DocumentoPago")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@ApiParam("DocumentoPago") DocumentoPago documentoPago) {
        try {
            documentoPago = documentoPagoService.mergeDocumentoPago(documentoPago);
            return Response.status(200).entity(documentoPago).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @POST
    @Path("/getNumeroPago/{idFacturaEmitida}")
    @ApiOperation(value = "Retrieves the next paid number")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumeroPago(@PathParam(value = "idFacturaEmitida")Long idFacturaEmitida) {
        try {
            Long numeroGenerado = documentoPagoService.generaNumeroPago(idFacturaEmitida);
            return Response.status(200).entity(numeroGenerado).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getDocumentoPagoMontoAcumulado/{idFacturaEmitida}")
    @ApiOperation(value = "Retrieves the next paid montoAcumulado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMontoAcumulado(@PathParam(value = "idFacturaEmitida")Long idFacturaEmitida) {
        try {
            BigDecimal montoAcumulado = documentoPagoService.generaMontoAcumulado(idFacturaEmitida);
            return Response.status(200).entity(montoAcumulado).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @PUT
    @Path("/putDocumentoPagoFacturasPojo")
    @ApiOperation(value = "operation to INSERT a documentoPago")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putDocumentoPagoFacturasPojo(@ApiParam(value = "documentoPagoFacturasPojo", required = true) DocumentoPagoFacturasPojo documentoPagoFacturasPojo) {
        try {
            documentoPagoService.guardaDocumentoPagoFacturasPojo(documentoPagoFacturasPojo);
            return Response.status(200).entity(documentoPagoFacturasPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @PUT
    @Path("/putDocumentoPagoFacturasRetencionPojo")
    @ApiOperation(value = "operation to INSERT a documentoPago")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putDocumentoPagoFacturasRetencionPojo(@ApiParam(value = "documentoPagoFacturasPojo", required = true) DocumentoPagoFacturasPojo documentoPagoFacturasPojo) {
        try {
            documentoPagoService.guardaDocumentoPagoFacturasRetencionesPojo(documentoPagoFacturasPojo);
            return Response.status(200).entity(documentoPagoFacturasPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
        
    @GET
    @Path("/getGestionesBancarizadasPorModulo/{modulo}")
    @ApiOperation(value = "Libro de Ventas de Huawei")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGestionesBancarizadasPorModulo(@PathParam("modulo") String modulo) {
        try {
            List<Integer> gestiones = documentoPagoService.getGestionesFacturadas(modulo);
            return Response.status(200).entity(gestiones).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}
