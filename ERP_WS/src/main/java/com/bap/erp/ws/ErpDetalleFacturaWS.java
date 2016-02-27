/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.ErpDetalleFactura;
import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.servicios.ErpDetalleFacturaService;
import com.bap.erp.servicios.ErpFacturaService;
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

/**
 *
 * @author Jonas
 */

@Component
@Path("/erpDetalleFactura")
@Api(value = "erpDetalleFactura", description = "WS for ErpDetalleFactura")
public class ErpDetalleFacturaWS {

    @Autowired
    private ErpDetalleFacturaService cpcDetalleFacturaService;
    
    @Autowired
    private ErpFacturaService erpFacturaService;

    public ErpDetalleFacturaWS() {

    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cpcDetalleFactura")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcDetalleFactura() throws Exception {
        List<ErpDetalleFactura> list = cpcDetalleFacturaService.getCpcDetalleFactura();
        return Response.status(200).entity(list).build();
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cpcDetalleFactura")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Response putCpcDetalleFactura(@ApiParam(value = "cpcDetalleFactura", required = true) ErpDetalleFactura cpcDetalleFactura){
        try {
            cpcDetalleFacturaService.persistCpcDetalleFactura(cpcDetalleFactura);
            return Response.status(200).entity(cpcDetalleFactura).build();
        } catch (Exception e) {
         e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
   
    @PATCH
    @Path("/edit")
    @ApiOperation(value = "edit CpcDetalleFactura")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@ApiParam("CpcDetalleFactura") ErpDetalleFactura cpcDetalleFactura) {
        try {
            cpcDetalleFactura = cpcDetalleFacturaService.mergeCpcDetalleFactura(cpcDetalleFactura);
            return Response.status(200).entity(cpcDetalleFactura).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getCpcDetalleFactura/{idDetalleFactura}")
    @ApiOperation(value = "Retrieves all cpcDetalleFactura")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetalleFacturaPorid(@PathParam("idDetalleFactura") Long idDetalleFactura) {
        try {
            ErpDetalleFactura cpcDetalleFactura = cpcDetalleFacturaService.getCpcDetalleFacturaById(idDetalleFactura);
            return Response.status(200).entity(cpcDetalleFactura).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getCpcDetalleFacturaByIdFacturaEmitida/{idFacturaEmitida}")
    @ApiOperation(value = "Retrieves all cpcDetalleFactura By IdFacturaEmitida")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetalleFacturaByIdFacturaEmitida(@PathParam("idFacturaEmitida") Long idFacturaEmitida) {
        try {
            ErpFactura erpFactura=erpFacturaService.getCpcFacturaEmitidaById(idFacturaEmitida);
            
            List<ErpDetalleFactura> cpcDetalleFactura = cpcDetalleFacturaService.getCpcDetalleFacturaByFacturaEmitida(erpFactura);
            return Response.status(200).entity(cpcDetalleFactura).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
}
