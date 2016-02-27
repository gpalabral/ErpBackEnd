/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.commons.entities.mappings.Comprobante;
import com.bap.erp.servicios.ErpFacturaService;
import com.bap.erp.ws.clients.ErpContabilidadClient;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jonas
 */
@Component
@Path("/cntComprobante")
@Api(value = "cntComprobante", description = "WS for save Comprobantes in Conta's DataBase")
public class CntComprobanteWS {

    @Autowired
    private ErpContabilidadClient erpContabilidadClient;
    
    @Autowired
    private ErpFacturaService erpFacturaService;

    public CntComprobanteWS() {

    }

    @POST
    @Path("/getComprobante")
    @ApiOperation(value = "Retrieves a idComprobante by saving in Conta's DB")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getComprobante(@ApiParam(value = "comprobante", required = true) Comprobante comprobante) {
        comprobante = erpContabilidadClient.getComprobante(comprobante);
        if (comprobante == null) {
            Response.status(500).entity("Error en los datos enviados").build();
        }
        return Response.status(200).entity(comprobante).build();
    }

    @GET
    @Path("/getNumeroFactura/{idDosificacion}")
    @ApiOperation(value = "Retrieves the next factura's number")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumeroFactura(@PathParam(value = "idDosificacion") Long idDosificacion) {
        try {    
            Long numeroFactura = erpFacturaService.generaNumeroFactura(idDosificacion);
            return Response.status(200).entity(numeroFactura).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
