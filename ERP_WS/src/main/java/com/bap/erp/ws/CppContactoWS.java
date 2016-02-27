/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.cpp.CppContacto;
import com.bap.erp.servicios.cpp.CppContactoService;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jonas
 */
@Component
@Path("/cppcontacto")
@Api(value = "cppcontacto", description = "WS for CppContacto")
public class CppContactoWS {

    @Autowired
    private CppContactoService cppContactoService;

    public CppContactoWS() {
    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cppContacto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCppContacto() {
        List<CppContacto> list = cppContactoService.getCppContacto();
        return Response.status(200).entity(list).build();
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cppcontacto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCppContacto(@ApiParam(value = "cppcontacto", required = true) CppContacto cppContacto) {
        try {

            cppContactoService.persistCppContacto(cppContacto);

            return Response.status(200).entity(cppContacto).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
        
    }

    @GET
    @Path("/getlista")
    @ApiOperation(value = "Retrieves all cppContacto by idCppProveedorCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaCppContactoPorIdProveedor(@QueryParam("idCppProveedorCliente") String idCppProveedorCliente) {
        List<CppContacto> list = cppContactoService.listaContactosPorIdProveedor(idCppProveedorCliente);
        return Response.status(200).entity(list).build();
    }
    

    @GET
    @Path("/getCppContactoByIdContacto/{idContacto}")
    @ApiOperation(value = "Retrieves a CppContacto by idContacto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCppContactoByIdContacto(@PathParam("idContacto") String idContacto) {
        try {            
            CppContacto cppContacto = cppContactoService.getCppContactoByIdContacto(Long.parseLong(idContacto));
            return Response.status(200).entity(cppContacto).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    
    @PATCH
    @Path("/edit")
    @ApiOperation(value = "edit CppContacto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@ApiParam("CppContacto") CppContacto cppContacto) {
        try {
            cppContacto = cppContactoService.mergeCppContacto(cppContacto);
            return Response.status(200).entity(cppContacto).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}
