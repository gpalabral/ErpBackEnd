/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws.rh;

import com.bap.erp.modelo.rh.RhCargo;
import com.bap.erp.servicios.rh.RhCargoService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
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
 * @author jonas - henrry
 */
@Component
@Path("/rhCargo")
@Api(value = "rhCargo", description = "SW para rhCargo")
public class RhCargoWS {

    @Autowired
    private RhCargoService rhCargoService;

    public RhCargoWS() {
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operacion para insertar una RhCargo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putRhEmpleado(@ApiParam(value = "rhCargo", required = true) RhCargo rhCargo) {
        try {
            rhCargo = rhCargoService.persistRhCargo(rhCargo);
            return Response.status(200).entity(rhCargo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }

    }

    @PATCH
    @Path("/modificaRhCargo")
    @ApiOperation(value = "modifica RhCargo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificaRhCargo(@ApiParam("rhCargo") RhCargo rhCargo) {
        try {
            rhCargo = rhCargoService.mergeRhCargo(rhCargo);
            return Response.status(200).entity(rhCargo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/eliminaRhCargo/{idCargo}")
    @ApiOperation(value = "elimina RhCargo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminaRhCargo(@PathParam("idCargo") Long idCargo) {
        try {
            rhCargoService.removeRhCargo(idCargo);
            return Response.status(200).entity(true).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/listaRhCargo")
    @ApiOperation(value = "obtiene lista de RhCargo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaRhCargo() throws Exception {
        try {
            List<RhCargo> list = rhCargoService.listaRhCargo();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/verificaExistenciaCodigoRhCargo/{codigo}")
    @ApiOperation(value = "existeCodigo RhCargo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificaExistenciaCodigoRhCargo(@PathParam("codigo") String codigo) {
        try {
            return Response.status(200).entity(rhCargoService.verificaExistenciaCodigoRhCargo(codigo)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
