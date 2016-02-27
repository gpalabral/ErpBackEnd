package com.bap.erp.ws.rh;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.bap.erp.modelo.rh.RhEmpleado;
import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import com.bap.erp.servicios.rh.RhEmpleadoCargoService;
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
 * @author jonas
 */
@Component
@Path("/rhEmpleadoCargo")
@Api(value = "rhEmpleadoCargo", description = "SW para rhEmpleadoCargo")
public class RhEmpleadoCargoWS {

    @Autowired
    private RhEmpleadoCargoService rhEmpleadoCargoService;

    public RhEmpleadoCargoWS() {
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operacion para insertar una EmpleadoCargo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putRhEmpleado(@ApiParam("rhEmpleadoCargo") RhEmpleadoCargo rhEmpleadoCargo) {
        try {
            rhEmpleadoCargo = rhEmpleadoCargoService.persistEmpleadoCargo(rhEmpleadoCargo);
            return Response.status(200).entity(rhEmpleadoCargo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/obtieneEmpleadoCargoPorIdEmpleado/{idEmpleado}")
    @ApiOperation(value = "Retrieves a RhEmpleadoCargo by idEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtieneEmpleadoCargoPorIdEmpleado(@PathParam("idEmpleado") Long idEmpleado) {
        try {
            RhEmpleadoCargo rhEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoPorIdEmpleado(idEmpleado);
            if (rhEmpleadoCargo == null) {
                return Response.status(200).entity(0).build();
            } else {
                return Response.status(200).entity(rhEmpleadoCargo).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/modificaRhEmpleadoCargo")
    @ApiOperation(value = "operacion para modificar un RhEmpleadoCargo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificaRhEmpleadoCargo(@ApiParam(value = "rhEmpleadoCargo", required = true) RhEmpleadoCargo rhEmpleadoCargo) {
        try {

            rhEmpleadoCargo = rhEmpleadoCargoService.persistModificaRhEmpleadoCargo(rhEmpleadoCargo);
            return Response.status(200).entity(rhEmpleadoCargo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/modificaRhEmpleadoCargoMasTablasRelacionadas/{idPeriodoGestion}")
    @ApiOperation(value = "operacion para modificar un RhEmpleadoCargo mas Tablas Relacionadas")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificaRhEmpleadoCargoMasTablasRelacionadas(@ApiParam(value = "rhEmpleadoCargo", required = true) RhEmpleadoCargo rhEmpleadoCargo, @PathParam("idPeriodoGestion") Long idPeriodoGestion) {
        try {

            rhEmpleadoCargo = rhEmpleadoCargoService.persistModificaRhEmpleadoCargoMasTablasRelacionadas(rhEmpleadoCargo, idPeriodoGestion);
            return Response.status(200).entity(rhEmpleadoCargo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
