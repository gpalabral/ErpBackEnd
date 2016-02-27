/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.commons.utils.DateUtils;
import com.bap.erp.modelo.cpc.CpcPagoContrato;
import com.bap.erp.modelo.pojo.CpcPagoContratoPojo;
import com.bap.erp.servicios.cpc.CpcPagoContratoService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
import java.math.BigDecimal;
import java.util.Date;
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
 * @author Javier
 */
@Component
@Path("/cpcPagoContrato")
@Api(value = "cpcPagoContrato", description = "WS for CpcPagoContrato")
public class CpcPagoContratoWS {

    @Autowired
    private CpcPagoContratoService cpcPagoContratoService;

    public CpcPagoContratoWS() {

    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cpcPagoContrato")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcPagoContrato() {
        List<CpcPagoContrato> list = cpcPagoContratoService.getCpcPagoContrato();
        return Response.status(200).entity(list).build();
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cpcPagoContrato")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCppPagoContrato(@ApiParam(value = "cpcPagoContrato", required = true) CpcPagoContrato cpcPagoContrato) {
        try {
            cpcPagoContratoService.persistCpcPagoContrato(cpcPagoContrato);
            return Response.status(200).entity(cpcPagoContrato).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getlistaCpcPagoContratoByFechaProgramada/{parEstadoPago}")
    @ApiOperation(value = "get CpcPagoContrato By FechaProgramada")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcPagoContratoByFechaProgramadaEstadoPago(@PathParam("parEstadoPago") String parEstadoPago) {
        try {
            List<CpcPagoContratoPojo> listaCpcPagoContrato = cpcPagoContratoService.listaCpcPagoContratoByFechaProgramadaEstadoPago(parEstadoPago);
            return Response.status(200).entity(listaCpcPagoContrato).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCpcPagoContratoPorId/{idPagoContrato}")
    @ApiOperation(value = "get CpcPagoContrato By idPagoContrato")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcPagoContratoPorId(@PathParam("idPagoContrato") Long idPagoContrato) {
        try {
            CpcPagoContrato cpcPagoContrato = cpcPagoContratoService.getCpcPagoContratoById(idPagoContrato);
            return Response.status(200).entity(cpcPagoContrato).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCpcPagoContratoPorIdContrato/{idContrato}")
    @ApiOperation(value = "get CpcPagoContrato By idPagoContrato")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcPagoContratoPorIdContrato(@PathParam("idContrato") Long idContrato) {
        try {
            List<CpcPagoContrato> cpcPagoContrato = cpcPagoContratoService.getCpcPagoContratoByIdContrato(idContrato);
            return Response.status(200).entity(cpcPagoContrato).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getCpcPagoContratIdContratoByEstadoPago/{idContrato}/{parEstadoPago}")
    @ApiOperation(value = "get CpcPagoContrato By idContrato")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcPagoContratoIdContratoByEstadoPago(@PathParam("idContrato") Long idContrato, @PathParam("parEstadoPago") String parEstadoPago) {
        try {            
            List<CpcPagoContrato> cpcPagoContrato = cpcPagoContratoService.getCpcPagoContratoIdContratoByEstadoPago(idContrato, parEstadoPago);
            return Response.status(200).entity(cpcPagoContrato).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getCobrosPorFacturarPorContrato/{idContrato}")
    @ApiOperation(value = "get CpcPagoContrato By idContrato")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCobrosPorFacturarPorContrato(@PathParam("idContrato") Long idContrato) {
        try {            
            List<CpcPagoContrato> cpcPagoContrato = cpcPagoContratoService.getCobrosPorFacturarPorContrato(idContrato);
            return Response.status(200).entity(cpcPagoContrato).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCpcPagoContratoConProveedorClientePorIdPagoContrato/{idPagoContrato}")
    @ApiOperation(value = "get CpcPagoContrato By idPagoContrato")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcPagoContratoConProveedorClientePorIdPagoContrato(@PathParam("idPagoContrato") Long idPagoContrato) {
        try {
            CpcPagoContratoPojo cpcPagoContrato = cpcPagoContratoService.listaCpcPagoContrato(idPagoContrato);
            return Response.status(200).entity(cpcPagoContrato).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @POST
    @Path("/getNroPago/{idContrato}")
    @ApiOperation(value = "Retrieves the next nropago number")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNroPago(@PathParam(value = "idContrato") Long idContrato) {
        try {
            Long numeroGenerado = cpcPagoContratoService.generaNroPago(idContrato);
            return Response.status(200).entity(numeroGenerado).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @POST
    @Path("/sumaMontoPagoContrato")
    @ApiOperation(value = "Retrieves the total")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sumaMontoPagoContrato(@ApiParam(value = "listaPagoContrato") List<CpcPagoContrato> listaPagoContrato) {
        try {
//            Float total = new Float("0");
            BigDecimal total = BigDecimal.ZERO;
            for (CpcPagoContrato cpcPagoContrato : listaPagoContrato) {
                total = total.add(cpcPagoContrato.getMontoProgramado());
            }
            System.out.println("EL NUMERO ES::::::: "+total);
            return Response.status(200).entity(total).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/edit")
    @ApiOperation(value = "edit CpcPagoContratos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@ApiParam("CpcPagoContratos") CpcPagoContrato cpcPagoContrato) {
        try {
            cpcPagoContrato = cpcPagoContratoService.mergeCpcPagoContrato(cpcPagoContrato);
            return Response.status(200).entity(cpcPagoContrato).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @POST
    @Path("/verificaTiempoDisponible")
    @ApiOperation(value = "Verifies the days")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificaTiempoDisponible(@ApiParam(value = "cpcPagoContrato") CpcPagoContrato cpcPagoContrato) {
        try {
            Boolean respuesta = cpcPagoContratoService.verificaTiempoDisponible(cpcPagoContrato);
            return Response.status(200).entity(respuesta).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @POST
    @Path("/removeAndPersist")
    @ApiOperation(value = "Remove and Persist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeAndPersistCpcPagoContrato(@ApiParam(value = "cpcPagoContrato") CpcPagoContrato cpcPagoContrato) {
        try {
            CpcPagoContrato respuesta = cpcPagoContratoService.cambiaCpcPagoContrato(cpcPagoContrato);
            return Response.status(200).entity(respuesta).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @POST
    @Path("/sumaDias/{fecha}/{dias}")
    @ApiOperation(value = "add days to a date")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sumaDias(@PathParam("fecha") Date fecha, @PathParam("dias") int dias) {
        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");            
//            Date fechaFormat = sdf.parse(fecha);
            Date fechaFinal = DateUtils.sumaDias(fecha, dias);
            return Response.status(200).entity(fechaFinal).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/actualizaPagosContratoEnMora")
    @ApiOperation(value = "actualizaPagoContrato")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizaPagosContratoEnMora() {
        try {
            cpcPagoContratoService.actualizaPagosContratoEnMora();
            return Response.status(200).entity(true).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getPagoContratoPendientePojo/{idContrato}/{cantidadDias}")
    @ApiOperation(value = "get CpcPagoContrato By FechaProgramada")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPagoContratoPendientePojo(@PathParam("idContrato") Long idContrato, @PathParam("cantidadDias") Integer cantidadDias) {
        try {
            List<CpcPagoContratoPojo> listaCpcPagoContrato = cpcPagoContratoService.getPagoContratoPendientePojo(idContrato, cantidadDias);
            return Response.status(200).entity(listaCpcPagoContrato).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
}
