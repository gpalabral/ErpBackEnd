/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws.rh;

import com.bap.erp.modelo.rh.RhParametros;
import com.bap.erp.servicios.rh.RhParametrosService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
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
 * @author jonas - henrry
 */
@Component
@Path("/rhParametros")
@Api(value = "rhParametros", description = "SW para rhParametros")
public class RhParametrosWS {

    @Autowired
    private RhParametrosService rhParametrosService;

    public RhParametrosWS() {
    }

    @PUT
    @Path("/persistRhParametrosDatosGenerales")
    @ApiOperation(value = "operacion para insertar una RhParametros")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persistRhParametrosDatosGenerales(@ApiParam(value = "rhParametros", required = true) RhParametros rhParametros) {
        try {
            rhParametros = rhParametrosService.guardaParametrosIniciales(rhParametros);            
            return Response.status(200).entity(rhParametros).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }

    }

    @PATCH
    @Path("/persistModificaRhParametrosPatronalesAndLaborales")
    @ApiOperation(value = "modifica RhParametros")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificaRhCargo(@ApiParam("rhParametros") RhParametros rhParametros) {
        try {
             rhParametros = rhParametrosService.modificaParametrosIniciales(rhParametros);
            return Response.status(200).entity(rhParametros).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/obtieneRhParametrosPorPeriodoGestion/{idPeriodoGestion}")
    @ApiOperation(value = "Retrieves a RhParametros by idPeriodoGestion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtieneRhParametrosPorPeriodoGestion(@PathParam("idPeriodoGestion") Long idPeriodoGestion) {
        try {
            RhParametros rhParametros = rhParametrosService.obtieneRhParametrosPorPeriodoGestion(idPeriodoGestion);            
            if (rhParametros == null) {
                return Response.status(200).entity(0).build();
            } else {                
                return Response.status(200).entity(rhParametros).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
