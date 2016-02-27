package com.bap.cpanel.ws;

import com.bap.cpanel.modelo.adm.AdmModulo;
import com.bap.cpanel.modelo.adm.AdmUsuario;
import com.bap.cpanel.servicios.adm.AdmModuloService;
import com.bap.cpanel.servicios.adm.AdmUsuarioService;
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
@Path("/admmodulo")
@Api(value = "admmodulo", description = "WS for AdmModulo")
public class AdmModuloWS {
    
    @Autowired
    private AdmUsuarioService admUsuarioService;

    @Autowired
    private AdmModuloService admModuloService;

    public AdmModuloWS() {
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT an admmodulo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putAdmModulo(@ApiParam(value = "admmodulo", required = true) AdmModulo admModulo) {
        try {
            admModulo = admModuloService.persistAdmModulo(admModulo);
            return Response.status(200).entity(admModulo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrievs all admmodulo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmModulo() {
        try {
            List<AdmModulo> lista = admModuloService.getAdmModulo();
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getAdmModulo/{idModulo}")
    @ApiOperation(value = "get AdmModulo By idModulo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmModulo(@PathParam("idModulo") Long idModulo) {
        try {
            AdmModulo admModulo = admModuloService.getAdmModuloByIdModulo(idModulo);
            return Response.status(200).entity(admModulo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getAdmModuloByAdmUsuario/{idUsuario}")
    @ApiOperation(value = "get getAdmModuloByAdmUsuario By idUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmModuloByAdmUsuario(@PathParam("idUsuario") Long idUsuario) {
        try {
            AdmUsuario admUsuario=admUsuarioService.getAdmUsuario(idUsuario);
            List<AdmModulo> lista=admModuloService.getAdmModuloByAdmUsuario(admUsuario);
            
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/listAdmModuloByAdmUsuario/{idUsuario}")
    @ApiOperation(value = "listAdmModuloByAdmUsuario By idUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAdmModuloByAdmUsuario(@PathParam("idUsuario") Long idUsuario) {
        try {
//          AdmUsuario admUsuario=admUsuarioService.listAdmModuloByAdmUsuario(idUsuario);
            List<AdmModulo> lista=admModuloService.listAdmModuloByAdmUsuario(idUsuario);
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
        
}
