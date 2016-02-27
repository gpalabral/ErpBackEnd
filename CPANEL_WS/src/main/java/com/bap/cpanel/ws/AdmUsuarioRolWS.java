/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.cpanel.ws;

import com.bap.cpanel.modelo.adm.AdmRol;
import com.bap.cpanel.modelo.adm.AdmUsuarioRol;
import com.bap.cpanel.servicios.adm.AdmUsuarioRolService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

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
 * @author javier
 */
@Component
@Path("/admusuariorol")
@Api(value = "admusuariorol", description = "WS for AdmUsuarioRol")
public class AdmUsuarioRolWS {

    @Autowired
    private AdmUsuarioRolService admUsuarioRolService;

    public AdmUsuarioRolWS() {

    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a admusuariorol")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putAdmUsuarioRol(@ApiParam(value = "admusuariorol", required = true) AdmUsuarioRol admUsuarioRol) {
        try {
            admUsuarioRol = admUsuarioRolService.persistAdmUsuarioRol(admUsuarioRol);
            return Response.status(200).entity(admUsuarioRol).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrievs all admusuariorol")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmUsuarioRol() {
        try {
            List<AdmUsuarioRol> lista = admUsuarioRolService.getAdmUsuarioRol();
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }

    }

    @PATCH
    @Path("/editAdmUsuarioRol")
    @ApiOperation(value = "edit AdmUsuarioRol")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editAdmUsuarioRol(@ApiParam("AdmUsuarioRol") AdmUsuarioRol admUsuarioRol) {
        try {
            admUsuarioRol = admUsuarioRolService.mergeAdmUsuarioRol(admUsuarioRol);
            return Response.status(200).entity(admUsuarioRol).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @POST
    @Path("/getAsignacionRemoveRolesaUsuario/{listaRolesAsignados}/{idUsuario}")
    @ApiOperation(value = "Asignacion y remove a Roles de un Usuario")
    @Produces(MediaType.APPLICATION_JSON)    
    public Response getAsignacionRolesaUsuario(@ApiParam("listaRolesAsignados") List<AdmRol> listaRolesAsignados, @PathParam("idUsuario") Long idUsuario) {
        System.out.println("listadoRolesAsignados:"+ listaRolesAsignados);
        System.out.println("idUsuario prueba:"+ idUsuario);
        try {
            admUsuarioRolService.asignacionRolesaUsuario(listaRolesAsignados,idUsuario);
            return Response.status(200).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}
