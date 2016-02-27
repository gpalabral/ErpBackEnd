/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.cpc.CpcContrato;
import com.bap.erp.modelo.pojo.CpcContratoPojo;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import com.bap.erp.servicios.ErpFacturaService;
import com.bap.erp.servicios.cpc.CpcContratoService;
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
 * @author ben
 */
@Component
@Path("/cpccontrato")
@Api(value = "cpccontrato", description = "WS for CpcContrato")
public class CpcContratoWS {

    @Autowired
    private CpcContratoService cpcContratoService;
    
    @Autowired
    private ErpFacturaService erpFacturaService;

    public CpcContratoWS() {

    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cpcContrato")
    @Produces(MediaType.APPLICATION_JSON)
    public Response CpcContrato() throws Exception {
        try {
            List<CpcContrato> list = cpcContratoService.getCpcContrato();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cpcContrato")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCpcContrato(@ApiParam(value = "cpccontrato", required = true) CpcContrato cpcContrato) {
        try {
            cpcContratoService.persistCpcContrato(cpcContrato);
            return Response.status(200).entity(cpcContrato).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }

    }

    @GET
    @Path("/getContratoArbol")
    @ApiOperation(value = "Retrieves a CpcContrato list in a tree format")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContratoArbol() {
        try {
            List<EntidadArbolPojo> dosificacionArbol = cpcContratoService.getCpcContratoArbol();
            return Response.status(200).entity(dosificacionArbol).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getContratoArbolFiltro")
    @ApiOperation(value = "Retrieves a CpcContrato list in a tree format")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContratoArbolFiltro() {
        try {
            List<EntidadArbolPojo> dosificacionArbol = cpcContratoService.getCpcContratoArbolFiltro();
            return Response.status(200).entity(dosificacionArbol).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PUT
    @Path("/putCpcContratoPojo")
    @ApiOperation(value = "operation to INSERT a cpcContratoPojo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCpcContrato(@ApiParam(value = "cpccontratoPojo", required = true) CpcContratoPojo cpcContratoPojo) {
        try {
            cpcContratoService.guardaContratoPojo(cpcContratoPojo);
            return Response.status(200).entity(cpcContratoPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCpcContratoPorId/{idContrato}")
    @ApiOperation(value = "get CpcContrato By idContrato")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcContratoPorId(@PathParam("idContrato") Long idContrato) {
        try {
            CpcContrato cpcContrato = cpcContratoService.getCpcContratoById(idContrato);
            return Response.status(200).entity(cpcContrato).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getContratoProveedorClienteArbol")
    @ApiOperation(value = "Retrieves a CpcContrato y CpcProveedorCliente list in a tree format")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContratoProveedorClienteArbol() {
        try {
            List<EntidadArbolPojo> listaArbol = cpcContratoService.getCpcContratoProveedorClienteArbol();
            return Response.status(200).entity(listaArbol).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCpcContratoItemByTipoItem{parTipoItem}")
    @ApiOperation(value = "get CpcContratoItem By TipoItem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcContratoItemByTipoItem(@PathParam("parTipoItem") String parTipoItem) throws Exception {
        try {
            List<CpcContrato> list = cpcContratoService.getCpcContratoItemByTipoItem(parTipoItem);
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    
    @GET
    @Path("/getModificaContrato/{idContrato}")
    @ApiOperation(value = "Retrieves true if the code exist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVerdaderoSiElContratoEsModificable(@PathParam("idContrato") Long idContrato) {
        try {
            Boolean esModificable = erpFacturaService.getVerdaderoSiElContratoEsModificable(idContrato);
            return Response.status(200).entity(esModificable).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    

}
