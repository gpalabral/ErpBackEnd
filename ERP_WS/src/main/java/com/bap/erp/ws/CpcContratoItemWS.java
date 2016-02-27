/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.cpc.CpcContratoItem;
import com.bap.erp.servicios.cpc.CpcContratoItemService;
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
 * @author ben
 */
@Component
@Path("/cpccontratoitem")
@Api(value = "cpccontratoitem", description = "WS for CpcContratoItem")
public class CpcContratoItemWS {

    @Autowired
    private CpcContratoItemService cpcContratoItemService;

    public CpcContratoItemWS() {

    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cpcContratoItem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response CpcContratoItem() throws Exception {
        List<CpcContratoItem> list = cpcContratoItemService.getCpcContratoItem();
        return Response.status(200).entity(list).build();
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cpcContratoItem")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCpcContrato(@ApiParam(value = "cpccontratoitem", required = true) CpcContratoItem cpcContratoItem) {
        try {
            cpcContratoItemService.persistCpcContratoItem(cpcContratoItem);
            return Response.status(200).entity(cpcContratoItem).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getCpcContratoItem/{idContrato}")
    @ApiOperation(value = "get CppContacto By idContrato")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcContratoItem(@PathParam("idContrato") Long idContrato) {
        try {
            List<CpcContratoItem> lista = cpcContratoItemService.getCpcContratoItemByIdContrato(idContrato);
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}
