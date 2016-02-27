package com.bap.cpanel.ws;

import com.bap.cpanel.modelo.adm.AdmPerfil;
import com.bap.cpanel.servicios.adm.AdmPerfilService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
@Path("/admperfil")
@Api(value = "admperfil", description = "WS for AdmPerfil")
public class AdmPerfilWS {

    @Autowired
    private AdmPerfilService admPerfilService;

    public AdmPerfilWS() {
    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrievs all admmodulo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmPerfil() {
        try {
            List<AdmPerfil> lista = admPerfilService.getAdmPerfil();
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/editAdmPerfil")
    @ApiOperation(value = "edit AdmPerfil")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editAdmPerfil(@ApiParam("AdmPerfil") AdmPerfil admPerfil) {
        try {
            admPerfil = admPerfilService.mergeAdmPerfil(admPerfil);
            return Response.status(200).entity(admPerfil).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getAdmPerfilById/{idPerfil}")
    @ApiOperation(value = "get AdmPerfil By idPerfil")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmPerfilById(@PathParam("idPerfil") Long idPerfil) {
        try {
            AdmPerfil admPerfil = admPerfilService.getAdmPerfilById(idPerfil);
            return Response.status(200).entity(admPerfil).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT an admPerfil")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putAdmPerfil(@ApiParam(value = "admPerfil", required = true) AdmPerfil admPerfil) {
        try {
            admPerfil = admPerfilService.persistAdmPerfil(admPerfil);
            return Response.status(200).entity(admPerfil).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getAdmPerfilByDescripcion/{idUsuario}/{tipoClave}")
    @ApiOperation(value = "get AdmPerfil By idUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmPerfilByDescripcion(@PathParam("idUsuario") Long idUsuario, @PathParam("tipoClave") String tipoClave) {
        try {
            AdmPerfil admPerfil = admPerfilService.getAdmPerfilTipoClave(idUsuario, tipoClave);
            return Response.status(200).entity(admPerfil).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @PATCH
    @Path("/remove/{idPerfil}")
    @ApiOperation(value = "remove AdmPerfil by idPerfil")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeAdmPerfilByI(@PathParam("idPerfil") Long idPerfil) {
        try {
            admPerfilService.removeAdmPerfilById(idPerfil);
            return Response.status(200).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @PUT
    @Path("/putAdmPerfil")
    @ApiOperation(value = "operation to INSERT an admPerfil")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response peristAdmPerfil(@ApiParam(value = "admPerfil", required = true) AdmPerfil admPerfil) {
        try {
            admPerfil = admPerfilService.guardaAdmDosificacionEnAdmPerfil(admPerfil);
            return Response.status(200).entity(admPerfil).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
