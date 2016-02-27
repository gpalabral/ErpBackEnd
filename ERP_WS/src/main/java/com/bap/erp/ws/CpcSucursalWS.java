/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.cpc.CpcSucursal;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import com.bap.erp.servicios.cpc.CpcSucursalService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("/cpcsucursal")
@Api(value = "cpcsucursal", description = "WS for CpcSucursal")
public class CpcSucursalWS {

    @Autowired
    private CpcSucursalService cpcSucursalService;

    public CpcSucursalWS() {

    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cpcSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcSucursal() {
        try {
            List<CpcSucursal> list = cpcSucursalService.getCpcSucursal();
            return Response.status(200).entity(list).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT cpcsucursal")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCpcSucursal(@ApiParam(value = "cpcsucursal", required = true) CpcSucursal cpcSucursal) {
        cpcSucursalService.persistCpcSucursal(cpcSucursal);
        return Response.status(200).entity(cpcSucursal).build();
    }

    @GET
    @Path("/getSucursalArbol")
    @ApiOperation(value = "Retrieves a CppSucursal list in a tree format")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSucursalArbol() {
        try {
            List<EntidadArbolPojo> sucursalArbol = cpcSucursalService.getCpcSucursalArbol();
            return Response.status(200).entity(sucursalArbol).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @POST
    @Path("/getNumeroSucursal")
    @ApiOperation(value = "Retrieves the next sucursal number")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumeroSucursal() {
        try {
            Long numeroGenerado = cpcSucursalService.generaNumeroSucursal();
            return Response.status(200).entity(numeroGenerado).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCpcSucursalByIdSucursal/{idSucursal}")
    @ApiOperation(value = "Retrieves a CpcSucursal by idSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcSucursalByIdSucursal(@PathParam("idSucursal") String idSucursal) {
        try {
            CpcSucursal cpcSucursal = cpcSucursalService.getCpcSucursalByIdSucursal(Long.parseLong(idSucursal));
            return Response.status(200).entity(cpcSucursal).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/edit")
    @ApiOperation(value = "edit CpcSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@ApiParam("CpcSucursal") CpcSucursal cpcSucursal) {
        try {
            cpcSucursal = cpcSucursalService.mergeCpcSucursal(cpcSucursal);
            return Response.status(200).entity(cpcSucursal).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getSucursalesDosificacion/{caracteristicaEspecial}/{estadoProceso}")
    @ApiOperation(value = "Retrieves a Sucursal list with Factura and Dosification")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSucursalesDosificacion(@PathParam("caracteristicaEspecial") String caracteristicaEspecial, @PathParam("estadoProceso") String estadoProceso) {
        try {
            List<CpcSucursal> listaSucursales = cpcSucursalService.getSucursalesFiltradoFactura(caracteristicaEspecial, estadoProceso);
            return Response.status(200).entity(listaSucursales).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    //nuevo javier
    @GET
    @Path("/getSucursal/{numeroSucursal}")
    @ApiOperation(value = "Retrieves all cpcSucursalListaSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSucursalPorNumeroSucursal(@PathParam("numeroSucursal") Long numero_Sucursal) {
        try {
            CpcSucursal sucursal = cpcSucursalService.sucursalPorNumeroSucursal(numero_Sucursal);
            return Response.status(200).entity(sucursal).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

//    @GET
//    @Path("/getCpcSucursalPreEstablecido")
//    @ApiOperation(value = "get CpcSucursalPreEstablecido")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getCpcSucursalPreEstablecido() {
//        try {
//            CpcSucursal cpcDosificacion = cpcSucursalService.getCpcDosificacionPreEstablecido();
//            return Response.status(200).entity(cpcDosificacion).build();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(500).entity(e).build();
//        }
//    }        
}
