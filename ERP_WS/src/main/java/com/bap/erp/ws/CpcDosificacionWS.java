/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.cpc.CpcDosificacion;
import com.bap.erp.modelo.pojo.ConcatenaActividadEconomicaPojo;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import com.bap.erp.servicios.cpc.CpcDosificacionService;
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
 * @author ben
 */
@Component
@Path("/cpcdosificaciones")
@Api(value = "cpcdosificaciones", description = "WS for CpcDosificaciones")
public class CpcDosificacionWS {

    @Autowired
    private CpcDosificacionService cpcDosificacionService;

    public CpcDosificacionWS() {

    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cpcDosificacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response CpcDosificaciones() {
        try {

            List<CpcDosificacion> list = cpcDosificacionService.getCpcDosificacion();
            return Response.status(200).entity(list).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PUT    
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cpcdosificacion")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCpcDosificaciones(@ApiParam(value = "cpcDosificacion", required = true) CpcDosificacion cpcDosificacion) {
        try {
            cpcDosificacionService.persistCpcDosificacion(cpcDosificacion);
            return Response.status(200).entity(cpcDosificacion).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }

    }

    @GET
    @Path("/getDosificacionArbol")
    @ApiOperation(value = "Retrieves a CpcDosificacion list in a tree format")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDosificacionArbol() {
        try {
            List<EntidadArbolPojo> dosificacionArbol = cpcDosificacionService.getCpcDosificacionArbol();
            return Response.status(200).entity(dosificacionArbol).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCpcDosificacionesPorEstadoyCaracEsp/{idSucursal}/{parEstadoProceso}/{parCaracEsp}")
    @ApiOperation(value = "get CpcDosificacion By idSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcDosificacionesPorEstadoyCaracEsp(@PathParam("idSucursal") Long idSucursal, @PathParam("parEstadoProceso") String parEstadoProceso, @PathParam("parCaracEsp") String parCaracEsp) {
        try {
            List<CpcDosificacion> listaCpcDosificaciones = cpcDosificacionService.listaCpcDosificacionesPorEstadoyCaracEsp(idSucursal, parEstadoProceso, parCaracEsp);
            return Response.status(200).entity(listaCpcDosificaciones).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCpcDosificacionesPorId/{idDosificaciones}")
    @ApiOperation(value = "get CpcDosificacion By idDosificaciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcDosificacionesPorId(@PathParam("idDosificaciones") Long idDosificaciones) {
        try {
            CpcDosificacion cpcDosificacion = cpcDosificacionService.getCpcDosificacionById(idDosificaciones);
            return Response.status(200).entity(cpcDosificacion).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/edit")
    @ApiOperation(value = "edit CpcDosificaciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@ApiParam("CpcDosificaciones") CpcDosificacion cpcDosificacion) {
        try {
            cpcDosificacion = cpcDosificacionService.mergeCpcDosificacion(cpcDosificacion);
            return Response.status(200).entity(cpcDosificacion).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCpcDosificacionesArbolPorSucursal/{idSucursal}")
    @ApiOperation(value = "get CpcDosificacionArbol By idSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcDosificacionesArbolPorSucursal(@PathParam("idSucursal") Long idSucursal) {
        try {
            List<EntidadArbolPojo> listaCpcDosificacionesArbol = cpcDosificacionService.getCpcDosificacionArbolPorSucursal(idSucursal);
            return Response.status(200).entity(listaCpcDosificacionesArbol).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

//    @GET
//    @Path("/getCpcDosificacionesPreEstablecido/{idSucursal}")
//    @ApiOperation(value = "get CpcDosificacionPreEstablecido By idSucursal")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getCpcDosificacionesPreEstablecidoPorIdSucursal(@PathParam("idSucursal") Long idSucursal) {
//        try {
//            CpcDosificacion cpcDosificacion = cpcDosificacionService.getCpcDosificacionByIdSucursal(idSucursal);
//            return Response.status(200).entity(cpcDosificacion).build();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(500).entity(e).build();
//        }
//    }
//    @GET
//    @Path("/getCpcDosificacionesPreEstablecido")
//    @ApiOperation(value = "get CpcDosificacionPreEstablecido")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getCpcDosificacionesPreEstablecido() {
//        try {
//            CpcDosificacion cpcDosificacion = cpcDosificacionService.getCpcDosificacionPreEstablecida();
//            return Response.status(200).entity(cpcDosificacion).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(500).entity(e).build();
//        }
//    }
    @GET
    @Path("/getListaCpcDosificacionByIdDosificacion/{idSucursal}")
    @ApiOperation(value = "Retrieves a CpcContrato list IdDosificacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaCpcDosificacionByIdDosificacion(@PathParam("idSucursal") Long idSucursal) {
        try {

            List<CpcDosificacion> list = cpcDosificacionService.getListaCpcDosificacionByIdDosificacion(idSucursal);
            return Response.status(200).entity(list).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getListaCpcDosificacionByIdSucursalAndCodigoDocMercantil/{idSucursal}/{codigoDocMercantil}")
    @ApiOperation(value = "Retrieves a CpcDosificacion list by IdSucursal and DocMercantil code")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaCpcDosificacionByIdSucursalAndCodigoDocMercantil(@PathParam("idSucursal") Long idSucursal, @PathParam("codigoDocMercantil") String codigoDocMercantil) {
        try {
            List<CpcDosificacion> list = cpcDosificacionService.getListaCpcDosificacionByIdSucursalAndCodigoDocMercantil(idSucursal, codigoDocMercantil);
            return Response.status(200).entity(list).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

//    @GET
//    @Path("/getVerificaExistenciaPreEstablecidoParaDosificacionesByIdSucursal/{idSucursal}")
//    @ApiOperation(value = "Retrieves a CpcDosificacion Boolean IdDosificacion")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getVerificaExistenciaPreEstablecidoParaDosificacionesByIdSucursal(@PathParam("idSucursal") Long idSucursal) {
//        try {
//
//            Boolean valor = cpcDosificacionService.getVerificaExistenciaPreEstablecidoParaDosificacionesByIdSucursal(idSucursal);
//            return Response.status(200).entity(valor).build();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(500).entity(e).build();
//        }
//    }
    @GET
    @Path("/getCpcDosificaciones/{idSucursal}/{parModalidadFacturacion}/{parEstadoProceso}")
    @ApiOperation(value = "get CpcDosificacion By idSucursal and parModalidadFacturacion and parEstadoProceso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cpcDosificacionesByIdSucModalidadFacEstProc(@PathParam("idSucursal") Long idSucursal, @PathParam("parModalidadFacturacion") String parModalidadFacturacion, @PathParam("parEstadoProceso") String parEstadoProceso) {
        try {
            CpcDosificacion listaCpcDosificaciones = cpcDosificacionService.getCpcDosificacionesByIdSucursalModalidadFacEstadoProceso(idSucursal, parModalidadFacturacion, parEstadoProceso);
            return Response.status(200).entity(listaCpcDosificaciones).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCpcDosificacionesPorIdContratoIdSucurEstProcCaracEspModFact/{idSucursal}/{idContrato}/{parEstadoProceso}/{parCaracEsp}/{parModFact}")
    @ApiOperation(value = "CpcDosificacion por idSucursal,IdCOntrato,parEstadoProceso,parCaracEsp,parModFact")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpcDosificacionesPorIdContratoIdSucurEstProcCaracEspModFact(@PathParam("idSucursal") Long idSucursal, @PathParam("idContrato") Long idContrato, @PathParam("parEstadoProceso") String parEstadoProceso, @PathParam("parCaracEsp") String parCaracEsp, @PathParam("parModFact") String parModFact) {
        try {
            List<CpcDosificacion> listaCpcDosificaciones = cpcDosificacionService.getCpcDosificacionesPorIdContratoIdSucurEstProcCaracEspModFact(idSucursal, idContrato, parEstadoProceso, parCaracEsp, parModFact);
            return Response.status(200).entity(listaCpcDosificaciones).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/obtieneConcatenaModalidadFacturacionPorActividadEconomica/{idActividadEconomica}")
    @ApiOperation(value = "CpcDosificacion por idSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtieneConcatenaModalidadFacturacionPorActividadEconomica(@PathParam("idActividadEconomica") Long idActividadEconomica) {
        try {
            ConcatenaActividadEconomicaPojo concatenaActividadEconomicaPojo = new ConcatenaActividadEconomicaPojo();
            concatenaActividadEconomicaPojo.setCadenaModalidadFacturacion(cpcDosificacionService.obtieneConcatenaModalidadFacturacionPorActividadEconomica(idActividadEconomica));
            return Response.status(200).entity(concatenaActividadEconomicaPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
