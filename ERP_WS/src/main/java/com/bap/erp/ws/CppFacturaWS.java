/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.cpp.CppFactura;
import com.bap.erp.modelo.pojo.CppFacturaCntComprobantePojo;
import com.bap.erp.servicios.cpp.CppFacturaService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jonas
 */
@Component
@Path("/cppFactura")
@Api(value = "cppFactura", description = "WS for CppFactura")
public class CppFacturaWS {

    @Autowired
    private CppFacturaService cppFacturaService;

    public CppFacturaWS() {

    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cppFactura")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCppFactura() {
        List<CppFactura> list = cppFacturaService.getCppFactura();
        return Response.status(200).entity(list).build();
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cppFactura")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCppFactura(@ApiParam(value = "cppFactura", required = true) CppFactura cppFactura) {
        cppFacturaService.persistCppFactura(cppFactura);
        return Response.status(200).entity(cppFactura).build();

    }

    @PUT
    @Path("/putCppFacturaCntComprobantePojo")
    @ApiOperation(value = "operation to INSERT a CppFacturaCntComprobantePojo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCppFacturaCppPlanPagoPojo(@ApiParam(value = "cppFacturaCntComprobantePojo", required = true) CppFacturaCntComprobantePojo cppFacturaCntComprobantePojo) {
        CppFacturaCntComprobantePojo cppFacturaCntComprobantePojoFinal = cppFacturaService.guardaCppFacturaCntComprobantePojo(cppFacturaCntComprobantePojo);
        return Response.status(200).entity(cppFacturaCntComprobantePojoFinal).build();
    }



}
