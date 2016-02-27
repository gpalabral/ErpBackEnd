/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.cpp.CppConcepto;
import com.bap.erp.modelo.pojo.CppConceptoProveedoresPojo;
import com.bap.erp.servicios.cpp.CppConceptoService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author paola
 */
@Component
@Path("/cppconcepto")
@Api(value = "cppconcepto", description = "WS for CppConcepto")
public class CppConceptoWS {

    @Autowired
    private CppConceptoService cppConceptoService;

    public CppConceptoWS() {

    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cppConcepto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCppConcepto() {
        List<CppConcepto> list = cppConceptoService.getListCppConcepto();
        return Response.status(200).entity(list).build();
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cppconcepto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCppConcepto(@ApiParam(value = "cppconcepto", required = true) CppConcepto cppConcepto) {
        try {
            cppConceptoService.persistCppConcepto(cppConcepto);
            return Response.status(200).entity(cppConcepto).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }

    }

    @GET
    @Path("/getConcepto")
    @ApiOperation(value = "Retrieves a CppConcepto by Id")
    public Response getConcepto(@QueryParam("idCppConcepto") String idCppConcepto) {
        CppConcepto cppConcepto = cppConceptoService.getCppConcepto(Long.parseLong(idCppConcepto));
        return Response.status(200).entity(cppConcepto).build();
    }

    @PATCH
    @Path("/edit")
    @ApiOperation(value = "edit CppConcepto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@ApiParam("CppConcepto") CppConcepto cppConcepto) {
        try {
            cppConcepto = cppConceptoService.mergeCppConcepto(cppConcepto);
            return Response.status(200).entity(cppConcepto).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PUT
    @Path("/putCppConceptoProveedoresPojo")
    @ApiOperation(value = "operation to INSERT a cppconcepto with CppProveedoresClientes")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCppConceptoProveedoresPojo(@ApiParam(value = "cppConceptoProveedoresPojo", required = true) CppConceptoProveedoresPojo cppConceptoProveedoresPojo) {
        try {
            cppConceptoProveedoresPojo = cppConceptoService.persistCppConceptoProveedorPojo(cppConceptoProveedoresPojo);
            return Response.status(200).entity(cppConceptoProveedoresPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/editCppConceptoProveedoresPojo")
    @ApiOperation(value = "operation to UPDATE a cppconcepto with CppProveedoresClientes")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editCppConceptoProveedoresPojo(@ApiParam(value = "cppConceptoProveedoresPojo", required = true) CppConceptoProveedoresPojo cppConceptoProveedoresPojo) {
        try {
            cppConceptoProveedoresPojo = cppConceptoService.mergeCppConceptoProveedorPojo(cppConceptoProveedoresPojo);
            return Response.status(200).entity(cppConceptoProveedoresPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
