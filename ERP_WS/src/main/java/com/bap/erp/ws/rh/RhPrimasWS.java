/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws.rh;

import com.bap.erp.modelo.rh.RhPrimas;
import com.bap.erp.servicios.rh.RhPrimasService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jonas
 */
@Component
@Path("/rhPrimas")
@Api(value = "rhPrimas", description = "SW para RhPrimas")
public class RhPrimasWS {

    @Autowired
    private RhPrimasService rhPrimasService;

    public RhPrimasWS() {
    }

    @PATCH
    @Path("/modificaRhPrimas")
    @ApiOperation(value = "operacion para modificar un listado de RhPrimas")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificaRhPrimas(@ApiParam("listaRhPrimas") List<RhPrimas> listaRhPrimas) {
        try {
            listaRhPrimas = rhPrimasService.modificaListaRhPrimas(listaRhPrimas);
            return Response.status(200).entity(listaRhPrimas).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
