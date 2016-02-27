/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.cpc.CpcPago;
import com.bap.erp.servicios.cpc.CpcPagoService;
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
 * @author Javier
 */
@Component
@Path("/cpcpago")
@Api(value = "cpcpago", description = "WS for CpcPago")
public class CpcPagoWS {

    @Autowired
    private CpcPagoService cpcPagoService;

    public CpcPagoWS() {

    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cpcPago")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcPago() throws Exception {
        try {
            List<CpcPago> list = cpcPagoService.getCpcPago();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getById/{idPago}")
    @ApiOperation(value = "Retrieves all cpcPago")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcPagoByIdPago(@PathParam("idPago") Long idPago) throws Exception {
        try {
            CpcPago list = cpcPagoService.getCpcPagoByIdPago(idPago);
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cpcpago")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCpcPago(@ApiParam(value = "cpcpago", required = true) CpcPago cpcPago) {
        try {
            cpcPagoService.persistCpcPago(cpcPago);
            return Response.status(200).entity(cpcPago).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
