package com.bap.cpanel.ws;

import com.bap.cpanel.modelo.adm.AdmRol;
import com.bap.cpanel.modelo.adm.AdmUsuario;
import com.bap.cpanel.modelo.pojo.EntidadArbolPojo;
import com.bap.cpanel.servicios.adm.AdmRolService;
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
@Path("/admrol")
@Api(value = "admrol", description = "WS for AdmRol")
public class AdmRolWS {

    @Autowired
    private AdmRolService admRolService;


    public AdmRolWS() {
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT an admrol")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putAdmrol(@ApiParam(value = "admrol", required = true) AdmRol admRol) {  
        try {
            admRol= admRolService.persistAdmRol(admRol);
            return Response.status(200).entity(admRol).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/get")
    @ApiOperation(value = "Retrievs all admpersonas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmRol() {
        try {
            List<AdmRol> lista= admRolService.getAdmRol();
        return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getUsuarioRol/{idUsuario}")
    @ApiOperation(value = "get AdmUsuarioRol By idUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmUsuarioRol(@PathParam("idUsuario") Long idUsuario) {
        try {
            List<AdmRol> listaAdmUsuarioRol= admRolService.getAdmUsuarioRol(idUsuario);
        return Response.status(200).entity(listaAdmUsuarioRol).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getUsuarioRol")
    @ApiOperation(value = "get UsuarioRol")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaEntidadArbolPojo() {
        try {
            List<EntidadArbolPojo> listaAdmRol= admRolService.getListaEntidadArbolPojo();
        return Response.status(200).entity(listaAdmRol).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getAdmUsuarioRol/{idUsuario}")
    @ApiOperation(value = "get AdmUsuarioRol By idUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListRoles(@PathParam("idUsuario") Long idUsuario) {
        try {
            List<AdmRol> listaAdmUsuarioRol = admRolService.getAdmUsuarioRol(idUsuario);
            return Response.status(200).entity(listaAdmUsuarioRol).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getAdmUsuarioSinRol/{idUsuario}")
    @ApiOperation(value = "get AdmUsuarioSinRol By idUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmUsuarioSinRol(@PathParam("idUsuario") Long idUsuario) {
        try {
            List<AdmRol> admUsuarioSinRol = admRolService.getAdmUsuarioSinRol(idUsuario);
            return Response.status(200).entity(admUsuarioSinRol).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
//    @GET
//    @Path("/getAdmRol/{idModulo}")
//    @ApiOperation(value ="get AdmRol By idModulo")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAdmRolByIdModulo (@PathParam("idModulo") Long idModulo){
//        try {
//            List<AdmRol> listadmRol = admRolService.getAdmRolByIdModulo(idModulo);
//            return  Response.status(200).entity(listadmRol).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(500).entity(e).build();
//        }
//    }
    
    @PATCH
    @Path("/editAdmRol")
    @ApiOperation(value = "edit AdmRol")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editAdmRol(@ApiParam("AdmRol") AdmRol admRol) {
        try {
            admRol = admRolService.mergeAdmRol(admRol);
            return Response.status(200).entity(admRol).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getAdmUsuarioSinRoles/{idUsuario}/{idModulo}")
    @ApiOperation(value ="get AdmUsuarioConRoles By idUsuario and idModulo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmUsuarioSinRolByIdModulo (@PathParam("idUsuario") Long idUsuario, @PathParam(value="idModulo") String idModulo){
        try {
            List<AdmRol> listadmRol = admRolService.getAdmUsuarioSinRolByIdModulo(idUsuario, idModulo);
            return  Response.status(200).entity(listadmRol).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getAdmUsuarioConRoles/{idUsuario}/{idModulo}")
    @ApiOperation(value ="get AdmUsuarioConRoles By idUsuario and idModulo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmUsuarioConRolByIdModulo (@PathParam("idUsuario") Long idUsuario, @PathParam("idModulo") String idModulo){
        try {
            List<AdmRol> listadmRol = admRolService.getAdmUsuarioConRolByIdModulo(idUsuario, idModulo);
            return  Response.status(200).entity(listadmRol).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}

