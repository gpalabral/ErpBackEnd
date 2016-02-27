/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws.rh;

import com.bap.erp.modelo.rh.RhPlanillaImpositiva;
import com.bap.erp.servicios.rh.RhPlanillaImpositivaService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
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
@Path("/rhPlanillaImpositiva")
@Api(value = "rhPlanillaImpositiva", description = "SW para rhPlanillaImpositiva")
public class RhPlanillaImpositivaWS {

    @Autowired
    private RhPlanillaImpositivaService rhPlanillaImpositivaService;

    public RhPlanillaImpositivaWS() {
    }

    @PUT
    @Path("/guardaPlanillaImpositiva")
    @ApiOperation(value = "operacion para guardar un listado de Planilla Impositiva")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardaPlanillaImpositiva(@ApiParam("listaPlanillaImpositiva") List<RhPlanillaImpositiva> listaPlanillaImpositiva) {
        try {
            listaPlanillaImpositiva = rhPlanillaImpositivaService.guardaPlanillaImpositiva(listaPlanillaImpositiva);
            return Response.status(200).entity(listaPlanillaImpositiva).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}
