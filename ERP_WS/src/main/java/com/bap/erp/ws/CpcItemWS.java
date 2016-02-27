/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.cpc.CpcItem;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import com.bap.erp.servicios.cpc.CpcItemService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
import java.math.BigDecimal;
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
@Path("/cpcitem")
@Api(value = "cpcitem", description = "WS for CpcItem")
public class CpcItemWS {

    @Autowired
    private CpcItemService cpcItemService;

    public CpcItemWS() {

    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cpcItem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcItem() throws Exception {
        List<CpcItem> list = cpcItemService.getCpcItem();
        return Response.status(200).entity(list).build();
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cpcItem")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCpcItem(@ApiParam(value = "cpcItem", required = true) CpcItem cpcItem) {
        try {
            cpcItemService.persistCpcItem(cpcItem);
            return Response.status(200).entity(cpcItem).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }

    }

    @GET
    @Path("/getItemArbol")
    @ApiOperation(value = "Retrieves a CppItem list in a tree format")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemArbol() {
        try {
            List<EntidadArbolPojo> itemArbol = cpcItemService.getCpcItemArbol();
            return Response.status(200).entity(itemArbol).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getCpcItemList")
    @ApiOperation(value = "Retrieves all CppItem list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcItemList() {
        try {
            List<CpcItem> listaItems = cpcItemService.getCpcItemList();
            return Response.status(200).entity(listaItems).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/edit")
    @ApiOperation(value = "edit CpcItem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@ApiParam("CpcItem") CpcItem cpcItem) {
        try {
            cpcItem = cpcItemService.mergeCpcItem(cpcItem);
            return Response.status(200).entity(cpcItem).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getItem/{idItem}")
    @ApiOperation(value = "Retrieves all cpcItemPorIdItem to idItem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcItemPorIdItem(@PathParam("idItem") Long idItem) {
        try {
            CpcItem service = cpcItemService.cpcItemPorIdItem(idItem);
            return Response.status(200).entity(service).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCambioDeMoneda/{montoMoneda}/{montoTipoDeCambio}/{tipoMoneda}")
    @ApiOperation(value = "retrieves ")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCambioDeMoneda(@PathParam("montoMoneda") BigDecimal montoMoneda,
            @PathParam("montoTipoDeCambio") BigDecimal montoTipoDeCambio,
            @PathParam("tipoMoneda") String tipoMoneda) {
        try {
            BigDecimal cambioDeMoneda = cpcItemService.calculaMontoDolares(montoMoneda, montoTipoDeCambio, tipoMoneda);
            return Response.status(200).entity(cambioDeMoneda).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

//    @GET
//    @Path("/getCpcContratoItem/{idContrato}")
//    @ApiOperation(value = "get CppContacto By idContrato")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getCpcContratoItem(@PathParam("idContrato") Long idContrato) {
//        try {
//            List<CpcItem> lista = cpcItemService.getCpcContratoItem(idContrato);
//            return Response.status(200).entity(lista).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(500).entity(e).build();
//        }
//    }
    @POST
    @Path("/sumaMontoItem")
    @ApiOperation(value = "Retrieves the total")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sumaMontoItem(@ApiParam(value = "listaItem") List<CpcItem> listaItem) {
        try {
//            Float total = new Float("0");
            BigDecimal total = BigDecimal.ZERO;
            for (CpcItem cpcItem : listaItem) {
                total = total.add(cpcItem.getPrecioUnitarioPrimeraMoneda());
            }
            return Response.status(200).entity(total).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCpcItemByIdContrato/{idContrato}")
    @ApiOperation(value = "get CppItem By idContrato")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcItemByIdContrato(@PathParam("idContrato") Long idContrato) {
        try {
            List<CpcItem> lista = cpcItemService.getCpcItemByIdContrato(idContrato);
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getContratoItemArbolByIdContrato/{idContrato}")
    @ApiOperation(value = "Retrieves a cpcCpcContratoItemArbol by idContrato and idProveedorCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContratoItemArbolByIdContrato(@PathParam("idContrato") Long idContrato) {
        try {
            List<EntidadArbolPojo> lista = cpcItemService.getCpcItemArbolPorCpcContrato(idContrato);
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/verificaCodigoExiste/{codigo}")
    @ApiOperation(value = "Retrieves a true if codigo exist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificaCodigoExiste(@PathParam("codigo") String codigo) {
        try {
            Boolean respuesta = cpcItemService.verificaSiElCodigoExiste(codigo);
            return Response.status(200).entity(respuesta).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
