/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.cpp.CppRetencion;
import com.bap.erp.servicios.cpp.CppRetencionService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jonas
 */
@Component
@Path("/cppRetencion")
@Api(value = "cppRetencion", description = "WS for cppRetencion")
public class CppRetencionWS {

    @Autowired
    private CppRetencionService cppRetencionService;

    public CppRetencionWS() {

    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a CppRetencion")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCppRetencion(@ApiParam(value = "cppRetencion", required = true) CppRetencion cppRetencion) {
        try {
            cppRetencion = cppRetencionService.persistCppRetencion(cppRetencion);            
            return Response.status(200).entity(cppRetencion).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }

    }

    
}
