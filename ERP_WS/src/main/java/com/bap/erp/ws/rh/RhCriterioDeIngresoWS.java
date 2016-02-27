/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws.rh;

import com.bap.erp.modelo.rh.RhCriterioDeIngreso;
import com.bap.erp.servicios.rh.RhCriterioDeIngresoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhCriterioDeIngresoService;
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
 * @author Henrry Guzmán
 */
@Component
@Path("/rhCriterioDeIngreso")
@Api(value = "rhCriterioDeIngreso", description = "SW para rhCriterioDeIngreso")
public class RhCriterioDeIngresoWS {

    @Autowired
    private RhCriterioDeIngresoService rhCriterioDeIngresoService;
    @Autowired
    private RhCriterioDeIngresoEmpleadoCargoService rhCriterioDeIngresoEmpleadoCargoService;

    public RhCriterioDeIngresoWS() {
    }

    @PUT
    @Path("/putRhCriterioDeIngreso/{idPeriodoGestion}")
    @ApiOperation(value = "operacion para insertar una RhCriterioDeIngreso")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putRhCriterioDeIngreso(@ApiParam(value = "rhCriterioDeIngreso", required = true) RhCriterioDeIngreso rhCriterioDeIngreso, @PathParam("idPeriodoGestion") Long idPeriodoGestion) {
        try {
            rhCriterioDeIngreso = rhCriterioDeIngresoService.persistRhCriterioDeIngreso(rhCriterioDeIngreso);
            rhCriterioDeIngresoEmpleadoCargoService.actualizaCriterioDeIngresoEmpleadoCargo(rhCriterioDeIngreso, idPeriodoGestion);
            return Response.status(200).entity(rhCriterioDeIngreso).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/modificaRhCriterioDeIngreso")
    @ApiOperation(value = "modifica RhCriterioDeIngreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificaRhCriterioDeIngreso(@ApiParam("rhCriterioDeIngreso") RhCriterioDeIngreso rhCriterioDeIngreso) {
        try {
            rhCriterioDeIngreso = rhCriterioDeIngresoService.mergeRhCriterioDeIngreso(rhCriterioDeIngreso);
            return Response.status(200).entity(rhCriterioDeIngreso).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/listaRhCriterioDeIngreso")
    @ApiOperation(value = "obtiene lista de RhCriterioDeIngreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaRhBono() throws Exception {
        try {
            List<RhCriterioDeIngreso> list = rhCriterioDeIngresoService.listaRhCriterioDeIngreso();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/verificaExistenciaCodigoCriterioDeIngreso/{codigo}")
    @ApiOperation(value = "existeCodigo RhCriterioDeIngreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificaExistenciaCodigoCriterioDeIngreso(@PathParam("codigo") String codigo) {
        try {
            return Response.status(200).entity(rhCriterioDeIngresoService.verificaExistenciaCodigoCriterioDeIngreso(codigo)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/verificaCriterioDeIngresoEmpleadoCargoPorIdDescuentoAndIdPeriodoParaEliminar/{idCriterioDeIngreso}/{idPeriodoGestion}")
    @ApiOperation(value = "verifica datos RhCriterioDeIngreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificaCriterioDeIngresoEmpleadoCargoPorIdDescuentoAndIdPeriodoParaEliminar(@PathParam("idCriterioDeIngreso") Long idCriterioDeIngreso, @PathParam("idPeriodoGestion") Long idPeriodoGestion) {
        try {
            return Response.status(200).entity(rhCriterioDeIngresoService.verificaCriterioDeIngresoEmpleadoCargoPorIdDescuentoAndIdPeriodoParaEliminar(idCriterioDeIngreso, idPeriodoGestion)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/deleteRhCriterioDeIngreso/{idCriterioDeIngreso}")
    @ApiOperation(value = "delete RhCriterioDeIngreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRhCriterioDeIngreso(@PathParam("idCriterioDeIngreso") Long idCriterioDeIngreso) {
        try {
            rhCriterioDeIngresoService.removeRhCriterioDeIngreso(idCriterioDeIngreso);
            return Response.status(200).entity(true).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
