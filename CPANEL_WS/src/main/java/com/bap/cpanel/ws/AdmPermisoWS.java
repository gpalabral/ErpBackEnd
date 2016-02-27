package com.bap.cpanel.ws;

import com.bap.cpanel.modelo.adm.AdmPermiso;
import com.bap.cpanel.modelo.adm.AdmRol;
import com.bap.cpanel.modelo.pojo.EntidadArbolPojo;
import com.bap.cpanel.modelo.pojo.EntidadPojo;
import com.bap.cpanel.servicios.adm.AdmPermisoService;
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
@Path("/admpermiso")
@Api(value = "admpermiso", description = "WS for AdmPermiso")
public class AdmPermisoWS {

    @Autowired
    private AdmPermisoService admPermisoService;
    private Object admComponenteService;

    public AdmPermisoWS() {

    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT an admpermiso")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putAdmpermiso(@ApiParam(value = "admpermiso", required = true) AdmPermiso admPermiso) {
        try {
            admPermiso = admPermisoService.persistAdmPermiso(admPermiso);
            return Response.status(200).entity(admPermiso).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrievs all admpermiso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmPermiso() {
        try {
            List<AdmPermiso> lista = admPermisoService.getAdmPermiso();
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getPermiso/{idRol}")
    @ApiOperation(value = "get AdmPermiso By idRol")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmPermiso(@PathParam("idRol") Long idRol) {
        try {
             List<AdmPermiso> listaAdmPermiso = admPermisoService.getAdmPermiso(idRol);
             return Response.status(200).entity(listaAdmPermiso).build();
            } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getAdmModuloPermiso/{idModulo}")
    @ApiOperation(value = "get AdmModuloPermiso By idModulo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmModuloPermiso(@PathParam("idModulo") Long idModulo) {
        try {
            List<AdmPermiso> admPermiso = admPermisoService.getAdmModuloPermiso(idModulo);
            return Response.status(200).entity(admPermiso).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getListaEntidadArbolPojo/{idUsuario}/{idModulo}")
    @ApiOperation(value ="get ListaEntidadArbolPojo By idUsuario and idModulo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaEntidadArbolPojo(@PathParam("idUsuario") Long idUsuario, @PathParam("idModulo") Long idModulo){
        try {
            List<EntidadArbolPojo> list = admPermisoService.getListaEntidadArbolPojo(idUsuario, idModulo);
            return  Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}



    