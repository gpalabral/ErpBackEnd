package com.bap.erp.ws.rh;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.bap.erp.modelo.rh.RhEmpleado;
import com.bap.erp.servicios.rh.RhEmpleadoService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
import java.util.Date;
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
 * @author jonas
 */
@Component
@Path("/rhEmpleado")
@Api(value = "rhEmpleado", description = "SW para rhEmpleado")
public class RhEmpleadoWS {

    @Autowired
    private RhEmpleadoService rhEmpleadoService;

    public RhEmpleadoWS() {
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operacion para insertar una Empleado")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putRhEmpleado(@ApiParam(value = "rhEmpleado", required = true) RhEmpleado rhEmpleado) {
        try {
            rhEmpleado = rhEmpleadoService.persistRhEmpleado(rhEmpleado);
            return Response.status(200).entity(rhEmpleado).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/listaRhEmpleado")
    @ApiOperation(value = "Retrieves all RhEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaRhEmpleado() throws Exception {
        try {
            List<RhEmpleado> list = rhEmpleadoService.listaRhEmpleado();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/obtieneCodigo")
    @ApiOperation(value = "Obtiene el siguiente Codigo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtieneCodigo() throws Exception {
        try {
            String codigo = rhEmpleadoService.codigoEmpleado();
            return Response.status(200).entity(codigo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/modificaRhEmpleado")
    @ApiOperation(value = "operacion para modificar un RhEmpleado")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificacionRhEmpleado(@ApiParam(value = "rhEmpleado", required = true) RhEmpleado rhEmpleado) {
        try {
            rhEmpleado = rhEmpleadoService.persistModificacionRhEmpleado(rhEmpleado);
            return Response.status(200).entity(rhEmpleado).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getRhEmpleadoByIdEmpleado/{idEmpleado}")
    @ApiOperation(value = "Retrieves a RhEmpleado by idEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRhEmpleadoByIdEmpleado(@PathParam("idEmpleado") Long idEmpleado) {
        try {
            RhEmpleado rhEmpleado = rhEmpleadoService.getRhEmpleadoById(idEmpleado);
            return Response.status(200).entity(rhEmpleado).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/obtieneDiasVacacionEmpleado/{fechaInicial}/{fechaFinal}")
    @ApiOperation(value = "Obtiene dias vacacion para empleado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtieneDiasVacacionEmpleado(@PathParam("fechaInicial") String fechaInicial, @PathParam("fechaFinal") String fechaFinal) {
        try {
            Date fechaInicialConvertido = new Date(fechaInicial);
            Date fechaFinalConvertido = new Date(fechaFinal);
            int diasVacacion = rhEmpleadoService.obtieneDiasVacacion(fechaInicialConvertido, fechaFinalConvertido);
            return Response.status(200).entity(diasVacacion).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/listaRhEmpleadoConCargoAsignado")
    @ApiOperation(value = "Retrieves all RhEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaRhEmpleadoConCargoAsignado() throws Exception {
        try {
            List<RhEmpleado> list = rhEmpleadoService.listaRhEmpleadoConCargoAsignado();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/listaRhEmpleadoPorPeriodo/{idPeriodoGestion}")
    @ApiOperation(value = "Retrieves all RhEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaRhEmpleadoPorPeriodo(@PathParam("idPeriodoGestion") Long idPeriodoGestion) throws Exception {
        try {
            List<RhEmpleado> list = rhEmpleadoService.listaRhEmpleadoPorPeriodo(idPeriodoGestion);
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
}
