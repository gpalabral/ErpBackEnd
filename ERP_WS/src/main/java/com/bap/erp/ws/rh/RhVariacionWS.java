/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws.rh;

import com.bap.erp.modelo.rh.RhVariacion;
import com.bap.erp.servicios.rh.RhVariacionService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jonas
 */
@Component
@Path("/rhVariacion")
@Api(value = "rhVariacion", description = "SW para rhVariacion")
public class RhVariacionWS {

    @Autowired
    private RhVariacionService rhVariacionService;

    public RhVariacionWS() {
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operacion para insertar una Variacion")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putRhVariacion(@ApiParam(value = "rhVariacion", required = true) RhVariacion rhVariacion) {
        try {
            rhVariacion = rhVariacionService.persistRhVariacion(rhVariacion);
            return Response.status(200).entity(rhVariacion).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }

    }
    
    @PATCH
    @Path("/modificaRhVariacion/{idPeriodoGestion}")
    @ApiOperation(value = "operacion para modificar un listado de Variaciones")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificaRhVariacion(@ApiParam("listaVariacion") List<RhVariacion> listaVariacion, @PathParam("idPeriodoGestion") Long idPeriodoGestion) {
        try {            
            rhVariacionService.modificaRhVariacion(listaVariacion, idPeriodoGestion);
            return Response.status(200).entity(true).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    
}
