package com.bap.cpanel.ws;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.cpanel.modelo.cli.CliEmpresa;
import com.bap.cpanel.servicios.cli.CliEmpresaService;
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


@Component
@Path("/cliempresa")
@Api(value = "cliempresa", description = "WS for empresa")
public class CliEmpresaWS {
    
    @Autowired
    private CliEmpresaService cliEmpresaService;
    
    @GET
    @Path("/getById/{idEmpresa}")
    @ApiOperation(value = "get Empresa By Id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCliEmpresaById(@PathParam("idEmpresa") Long idEmpresa) {

        CliEmpresa cliEmpresa=cliEmpresaService.getCliEmpresaById(idEmpresa);
        return Response.status(200).entity(cliEmpresa).build();

    }
    
    @PATCH
    @Path("/editCliEmpresa")
    @ApiOperation(value = "edit CliEmpresa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editCliEmpresa(@ApiParam("CliEmpresa") CliEmpresa cliEmpresa) {
        try {
            cliEmpresa = cliEmpresaService.mergeCliEmpresa(cliEmpresa);
            return Response.status(200).entity(cliEmpresa).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cliEmpresa")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCliEmpresa(@ApiParam(value = "cliEmpresa", required = true) CliEmpresa cliEmpresa) {
        try {
            cliEmpresa = cliEmpresaService.persistCliEmpresa(cliEmpresa);
            return Response.status(200).entity(cliEmpresa).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/get")
    @ApiOperation(value = "Retrievs all cliEmpresa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCliEmpresa() {
        try {
            List<CliEmpresa> lista = cliEmpresaService.getCliEmpresa();
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
}
