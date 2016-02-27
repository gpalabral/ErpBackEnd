 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.cpanel.ws;

import com.bap.cpanel.exceptions.InvalidCredentialsException;
import com.bap.cpanel.exceptions.SessionAlreadyCreatedException;
import com.bap.cpanel.exceptions.UnableToCreateAdmUsuarioException;
import com.bap.cpanel.modelo.adm.AdmUsuario;
import com.bap.cpanel.modelo.pojo.AdmUsuarioPojo;
import com.bap.cpanel.modelo.pojo.PerfilUsuarioPojo;
import com.bap.cpanel.servicios.adm.AdmSessionService;
import com.bap.cpanel.servicios.adm.AdmUsuarioService;
import com.bap.erp.commons.entities.UserToken;
import com.bap.erp.commons.exceptions.EncodingPasswordException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author henrry
 */
@Component
@Path("/admusuario")
@Api(value = "admusuario", description = "WS for AdmUsuario")
public class AdmUsuarioWS {
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AdmUsuarioWS.class);

    @Context
    HttpHeaders headers;

    @Autowired
    private AdmUsuarioService admUsuarioService;

    @Autowired
    private AdmSessionService admSessionService;
    
    

    public AdmUsuarioWS() {

    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a admusuario")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putAdmUsuario(@ApiParam(value = "admusuario", required = true) AdmUsuario admUsuario) {
        try {
            admUsuario = admUsuarioService.persistAdmUsuario(admUsuario);
            return Response.status(200).entity(admUsuario).build();
        } catch (UnableToCreateAdmUsuarioException e) {
            return Response.status(500).entity(e).build();
        } catch (EncodingPasswordException ex) {
            return Response.status(500).entity(ex).build();
        }
    }

    @POST
    @Path("/authenticate")
    @ApiOperation(value = "verifies  username and password credentials")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(@FormParam("username") String username, @FormParam("password") String password) {
        if (username == null || password == null) {
            return Response.status(500).entity(new Exception("Username or password don't not valid values")).build();
        }
        try {
            UserToken userToken = admUsuarioService.autenticarUsuario(username, password);

            return Response.status(200).entity(userToken).build();
        } catch (InvalidCredentialsException e) {
            return Response.status(401).entity(e).build();
        } catch (EncodingPasswordException e) {
            return Response.status(500).entity(e).build();
        } catch (SessionAlreadyCreatedException e) {
            return Response.status(403).entity(e).build();
        }
    }

    @POST
    @Path("/validate/token")
    @ApiOperation(value = "verifies  username and password credentials")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateToken(@FormParam("username") String username, @FormParam("token") String token) {
        if (username == null || token == null) {
            return Response.status(500).entity(new Exception("Username or token don't have valid values")).build();
        }
        UserToken userToken = new UserToken();
        userToken.setUserName(username);
        userToken.setToken(token);

        if (admSessionService.isValidToken(username, token)) {
            return Response.status(200).entity(userToken).build();
        } else {
            return Response.status(401).entity(userToken).build();
        }

    }

    @GET
    @Path("/getAll")
    @ApiOperation(value = "Retrievs all admusuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmUsuario() {
        MultivaluedMap<String, String> headerMap = headers.getRequestHeaders();
        List<String> token = headerMap.get("token");
        try {
            List<AdmUsuario> lista = admUsuarioService.getAdmUsuario();
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getById/{idUsuario}")
    @ApiOperation(value = "get AdmUsuario By Id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmUsuarioById(@PathParam("idUsuario") Long idUsuario) {
        try {
            AdmUsuario admUsuario = admUsuarioService.getAdmUsuarioById(idUsuario);
            return Response.status(200).entity(admUsuario).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getUsuarioEstado/{estado}")
    @ApiOperation(value = "get AdmUsuario By Estado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaAdmUsuarioEstado(@PathParam("estado") String estado) {
        try {
            List<AdmUsuario> listaUsuario = admUsuarioService.getListaAdmUsuarioEstado(estado);
            return Response.status(200).entity(listaUsuario).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }

    }

    @GET
    @Path("/getAdmUsuarioConPersona")
    @ApiOperation(value = "get AdmUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmUsuarioConPersona() {
        try {
            List<AdmUsuarioPojo> admUsuarioPojo = admUsuarioService.listaAdmUsuarioPojo();
            return Response.status(200).entity(admUsuarioPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PUT
    @Path("/putAdmUsuarioAdmPersona")
    @ApiOperation(value = "operation to INSERT a AdmUsuarioAdmPersona")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putAdmUsuarioAdmPersona(@ApiParam(value = "admUsuario", required = true) AdmUsuario admUsuario) {
        try {
            AdmUsuario admUsuarioFinal = admUsuarioService.guardaAdmUsuarioAdmPersona(admUsuario);
            return Response.status(200).entity(admUsuarioFinal).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/editAdmUsuario")
    @ApiOperation(value = "edit AdmUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editAdmUsuario(@ApiParam("AdmUsuario") AdmUsuario admUsuario) {
        try {
            admUsuario = admUsuarioService.mergeAdmUsuario(admUsuario);
            return Response.status(200).entity(admUsuario).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/editAdmUsuarioAdmPersona")
    @ApiOperation(value = "edit AdmUsuarioAdmPersona")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editAdmUsuarioAdmPersona(@ApiParam("AdmUsuario") AdmUsuario admUsuario) {
        try {
            admUsuario = admUsuarioService.mergeAdmUsuarioAdmPersona(admUsuario);
            return Response.status(200).entity(admUsuario).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/removeAdmUsuarioAdmPersona/{idUsuario}")
    @ApiOperation(value = "remove AdmUsuarioAdmPersona")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeAdmUsuarioAdmPersona(@PathParam("idUsuario") Long idUsuario) {
        try {
            admUsuarioService.removeAdmUsuarioAdmPersona(idUsuario);
            return Response.status(200).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getAdmUsuarioPerfilPojo/{idUsuario}")
    @ApiOperation(value = "get AdmUsuario an AdmPerfil")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmUsuarioPerfilPojo(@PathParam("idUsuario") Long idUsuario) {
        try {
           PerfilUsuarioPojo admUsuarioPojo = admUsuarioService.getPerfilUsuarioPojo(idUsuario);
            return Response.status(200).entity(admUsuarioPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @POST
    @Path("/verificaContrasenia")
    @ApiOperation(value = "verifies  username and password credentials")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificaContrasenia(@FormParam("username") String username, @FormParam("password") String password) {
        if (username == null || password == null) {
            return Response.status(500).entity(new Exception("Username or password don't not valid values")).build();
        }
        try {
            Boolean check = admUsuarioService.checkPassword(username, password);

            return Response.status(200).entity(check).build();
        } catch (InvalidCredentialsException e) {
            return Response.status(401).entity(e).build();
        } catch (EncodingPasswordException e) {
            return Response.status(500).entity(e).build();
        } catch (SessionAlreadyCreatedException e) {
            return Response.status(403).entity(e).build();
        }
    }
    
    @PATCH
    @Path("/editPassword")
    @ApiOperation(value = "edit Password")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPassword(@FormParam("username") String username, @FormParam("passwordActual") String passwordActual, @FormParam(value="passwordNuevo") String passwordNuevo, @FormParam(value="repeatPasswordNuevo") String repeatPasswordNuevo) {
        try {
            AdmUsuario admUsuario = admUsuarioService.mergePassword(username, passwordActual, passwordNuevo, repeatPasswordNuevo);
            return Response.status(200).entity(admUsuario).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getAdmUsuarioByLogin/{login}")
    @ApiOperation(value = "get AdmUsuario by login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmUsuarioByLogin(@PathParam("login") String login) {
        try {
            AdmUsuario admUsuario = admUsuarioService.getAdmUsuario(login);
            return Response.status(200).entity(admUsuario).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getUserCache/{token}")
    @ApiOperation(value = "getUserCache")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserCache(@PathParam("token") String token) {
        try {
            
            String username=admUsuarioService.getUserCache(token);
            log.info(username);
            Map<String,String> map=new HashMap<String,String>();
            map.put(username, token);
            return Response.status(200).entity(map).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
}
