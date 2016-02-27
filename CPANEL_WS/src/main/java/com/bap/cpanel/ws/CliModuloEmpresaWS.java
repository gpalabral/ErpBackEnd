package com.bap.cpanel.ws;

import com.bap.cpanel.modelo.cli.CliModuloEmpresa;
import com.bap.cpanel.servicios.cli.CliModuloEmpresaService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
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
@Path("/climoduloempresa")
@Api(value = "climoduloempresa", description = "WS for CliModeloEmpresa")
public class CliModuloEmpresaWS {

    @Autowired
    private CliModuloEmpresaService cliModuloEmpresaService;

    public CliModuloEmpresaWS() {
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT an climoduloempresa")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCliModuloEmpresa(@ApiParam(value = "climoduloempresa", required = true) CliModuloEmpresa cliModuloEmpresa) {
        try {
            cliModuloEmpresa = cliModuloEmpresaService.persistCliModuloEmpresa(cliModuloEmpresa);
            return Response.status(200).entity(cliModuloEmpresa).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrievs all climoduloempresa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCliModuloEmpresa() {
        try {
            List<CliModuloEmpresa> lista = cliModuloEmpresaService.getCliModuloEmpresa();
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCliModuloEmpresa/{idModuloEmpresa}")
    @ApiOperation(value = "get CliModuloEmpresa By idModuloEmpresa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCliModuloEmpresa(@PathParam("idModuloEmpresa") Long idModuloEmpresa) {
        try {
            CliModuloEmpresa cliModuloEmpresa = cliModuloEmpresaService.getCliModuloEmpresaByIdModuloEmpresa(idModuloEmpresa);
            return Response.status(200).entity(cliModuloEmpresa).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
}
