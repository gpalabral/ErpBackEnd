/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.cpc.CpcBanco;
import com.bap.erp.servicios.cpc.CpcBancoService;
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
@Path("/cpcbanco")
@Api(value = "cpcbanco", description = "WS for CpcBanco")
public class CpcBancoWS {

    @Autowired
    private CpcBancoService cpcBancoService;

    public CpcBancoWS() {

    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cpcBanco")
    @Produces(MediaType.APPLICATION_JSON)
    public Response CpcBanco() throws Exception {
        List<CpcBanco> list = cpcBancoService.getCpcBanco();
        return Response.status(200).entity(list).build();
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cpcbanco")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCpcBanco(@ApiParam(value = "cpcbanco", required = true) CpcBanco cpcBanco) {
        cpcBancoService.persistCpcBanco(cpcBanco);
        return Response.status(200).entity(cpcBanco).build();

    }

    @GET
     @Path("/getCpcBanco/{idBanco}")
    @ApiOperation(value = "Retrieves a CpcBanco by idBanco")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcBancoByIdBanco(@PathParam("idBanco") Long idBanco) {
        try {
        CpcBanco list = cpcBancoService.getCpcBancoById(idBanco);
           return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}
