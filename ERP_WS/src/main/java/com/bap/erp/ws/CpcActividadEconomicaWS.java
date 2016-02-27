/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.cpc.CpcActividadEconomica;
import com.bap.erp.servicios.cpc.CpcActividadEconomicaService;
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

/**
 *
 * @author Jonas
 */
@Component
@Path("/cpcActividadEconomica")
@Api(value = "cpcActividadEconomica", description = "WS for CpcActividadEconomica")
public class CpcActividadEconomicaWS {

    @Autowired
    private CpcActividadEconomicaService cpcActividadEconomicaService;

    public CpcActividadEconomicaWS() {

    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cpcActividadEconomica")
    @Produces(MediaType.APPLICATION_JSON)
    public Response CpcActividadEconomica() {
        try {
            List<CpcActividadEconomica> list = cpcActividadEconomicaService.getCpcActividadEconomica();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cpcActividadEconomica")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCpcActividadEconomica(@ApiParam(value = "cpcActividadEconomica", required = true) CpcActividadEconomica cpcActividadEconomica) {
        try {
            cpcActividadEconomicaService.persistCpcActividadEconomica(cpcActividadEconomica);
            return Response.status(200).entity(cpcActividadEconomica).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCpcActividadEconomica/{idActividadEconomica}")
    @ApiOperation(value = "Retrieves a CpcActividadEconomica by idActividadEconomica")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcActividadEconomicaByIdBanco(@PathParam("idActividadEconomica") Long idActividadEconomica) {
        try {
            CpcActividadEconomica cpcActividadEconomica = cpcActividadEconomicaService.getCpcActividadEconomicaById(idActividadEconomica);
            return Response.status(200).entity(cpcActividadEconomica).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getActividadesEconomicasWithDosificacion")
    @ApiOperation(value = "Retrieves all cpcActividadEconomica with dosification registered")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActividadesEconomicasWithDosificacion() {
        try {
            List<CpcActividadEconomica> list = cpcActividadEconomicaService.getActividadesEconomicasWithDosificacion();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getVerificaSiCodigoExiste/{codigo}")
    @ApiOperation(value = "Retrieves true if the code exist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVerificaFacturaParaDosificacionByIdDosificacion(@PathParam("codigo") String codigo) {
        try {
            Boolean existe = cpcActividadEconomicaService.verificaSiExisteCodigo(codigo);
            return Response.status(200).entity(existe).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getActividadEconomicaByIdPagoContrato/{idPagoContrato}")
    @ApiOperation(value = "Retrieves all the ActividadEconomica by idPagoContrato")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActividadEconomicaByIdPagoContrato(@PathParam("idPagoContrato") Long idPagoContrato) {
        try {
            List<CpcActividadEconomica> listaActividadesEconomicas = cpcActividadEconomicaService.getActividadEconomicaByIdPagoContrato(idPagoContrato);
            return Response.status(200).entity(listaActividadesEconomicas).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getActividadEconomicaByIdContrato/{idContrato}")
    @ApiOperation(value = "Retrieves All ActividadEconomica by IdContrato")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActividadEconomicaByIdContrato(@PathParam("idContrato") Long idContrato) {
        try {
            List<CpcActividadEconomica> listaActividadesEconomicas = cpcActividadEconomicaService.getActividadEconomicaByIdContrato(idContrato);
            return Response.status(200).entity(listaActividadesEconomicas).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }      
    
}
