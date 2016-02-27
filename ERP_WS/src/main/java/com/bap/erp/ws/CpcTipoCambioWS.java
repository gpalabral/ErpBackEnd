/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.cpc.CpcTipoCambio;
import com.bap.erp.servicios.cpc.CpcTipoCambioService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
import java.util.Date;
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
 * @author Javier
 */
@Component
@Path("/cpcTipoCambio")
@Api(value = "cpcTipoCambio", description = "WS for CpcTipoCambio")
public class CpcTipoCambioWS {

    @Autowired
    private CpcTipoCambioService cpcTipoCambioService;

    public CpcTipoCambioWS() {

    }
    
    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a CpcTipoCambio")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCpcTipoCambio(@ApiParam(value = "cpcTipoCambio", required = true) CpcTipoCambio cpcTipoCambio) {
        try {
            cpcTipoCambioService.persistCpcTipoCambio(cpcTipoCambio);
            return Response.status(200).entity(cpcTipoCambio).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @PATCH
    @Path("/editCpcTipoCambio")
    @ApiOperation(value = "edit CpcTipoCambio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editCpcTipoCambio(@ApiParam("CpcTipoCambio") CpcTipoCambio cpcTipoCambio) {
        try {
            cpcTipoCambio = cpcTipoCambioService.mergeCpcTipoCambio(cpcTipoCambio);
            return Response.status(200).entity(cpcTipoCambio).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getCpcTipoCambio")
    @ApiOperation(value = "get CpcTipoCambio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcTipoCambio() {
        try {
            List<CpcTipoCambio> cpcTipoCambio = cpcTipoCambioService.getListCpcTipoCambio();
            return Response.status(200).entity(cpcTipoCambio).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getCpcTipoCambioByFecha/{fecha}")
    @ApiOperation(value = "Retrieves a CpcTipoCambio Boolean fecha")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcTipoCambioByFecha(@PathParam("fecha") Date fecha) {
        try {
            Boolean valor = cpcTipoCambioService.getCpcTipoCambioByFecha(fecha);
            return Response.status(200).entity(valor).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
