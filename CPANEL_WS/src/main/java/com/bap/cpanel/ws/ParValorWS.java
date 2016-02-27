package com.bap.cpanel.ws;

import com.bap.cpanel.modelo.par.ParDepartamento;
import com.bap.cpanel.modelo.par.ParEstadoUsuario;
import com.bap.cpanel.modelo.par.ParTipoDocumento;
import com.bap.cpanel.modelo.par.ParEstadoPermiso;
import com.bap.cpanel.modelo.par.ParTipo;
import com.bap.cpanel.modelo.par.ParTipoMoneda;
import com.bap.cpanel.modelo.par.ParValor;
import com.bap.cpanel.servicios.par.ParValorService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/parvalor")
@Api(value = "parvalor", description = "WS for ParValor")
public class ParValorWS {

    @Autowired
    private ParValorService parValorService;

    public ParValorWS() {
    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all ParValor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParValor() {
        List<ParValor> list = parValorService.getParValor();
        return Response.status(200).entity(list).build();
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a ParValor")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putParValor(
        @ApiParam(value = "cppgrupo", required = true) ParValor parValor) {
        parValorService.persistParValor(parValor);
        return Response.status(200).entity(parValor).build();
    }

    @GET
    @Path("/getParEstadoUsuario")
    @ApiOperation(value = "Retrieves all ParEstadoUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParEstadoUsuario() {
        try {
            List<ParEstadoUsuario> list = parValorService.getListParEstadoUsuario();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoDocumento")
    @ApiOperation(value = "Retrieves all ParTipoDocumento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipoDocumento() {
        try {
            List<ParTipoDocumento> list = parValorService.getListParTipoDocumento();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getParEstadoPermiso")
    @ApiOperation(value = "Retrieves all ParEstadoPermiso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParEstadoPermiso() {
        try {
            List<ParEstadoPermiso> list = parValorService.getListParEstadoPermiso();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getParTipoMoneda")
    @ApiOperation(value = "Retrieves all ParTipoMoneda")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipoMoneda() {
        try {
            List<ParTipoMoneda> list = parValorService.getListParTipoMoneda();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getParTipo")
    @ApiOperation(value = "Retrieves all ParTipo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipo() {
        try {
            List<ParTipo> list = parValorService.getListParTipo();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getParDepartamento")
    @ApiOperation(value = "Retrieves all ParDepartamento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParDepartamento() {
        try {
            List<ParDepartamento> list = parValorService.getListParDepartamento();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}
