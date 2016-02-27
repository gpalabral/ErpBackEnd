/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws.rh;

import com.bap.erp.enums.EnumEstadoPeriodoGestion;
import com.bap.erp.enums.EnumTipoAplicacionDescuentoCriterioDeIngreso;
import com.bap.erp.modelo.par.ParTipoAplicacionDescuentoCriterioDeIngreso;
import com.bap.erp.modelo.par.ParTipoDescuento;
import com.bap.erp.modelo.rh.RhDescuento;
import com.bap.erp.modelo.rh.RhPeriodoGestion;
import com.bap.erp.servicios.par.ParValorService;
import com.bap.erp.servicios.rh.RhDescuentoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhDescuentoService;
import com.bap.erp.servicios.rh.RhPeriodoGestionService;
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
 * @author Henrry Guzmán
 */
@Component
@Path("/rhDescuento")
@Api(value = "rhDescuento", description = "SW para rhDescuento")
public class RhDescuentoWS {

    @Autowired
    private RhDescuentoService rhDescuentoService;
    @Autowired
    private RhDescuentoEmpleadoCargoService rhDescuentoEmpleadoCargoService;
    @Autowired
    private ParValorService parValorService;
    @Autowired
    private RhPeriodoGestionService rhPeriodoGestionService;

    public RhDescuentoWS() {
    }

    @PUT
    @Path("/persistRhDescuento/{idPeriodoGestion}")
    @ApiOperation(value = "operacion para insertar una RhDescuento")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persistRhDescuento(@ApiParam(value = "rhDescuento", required = true) RhDescuento rhDescuento, @PathParam("idPeriodoGestion") Long idPeriodoGestion) {
        try {
            ParTipoAplicacionDescuentoCriterioDeIngreso parTipoAplicacionDescuentoCriterioDeIngreso = parValorService.obtieneTipoAplicacionDescuentoPorCodigo(EnumTipoAplicacionDescuentoCriterioDeIngreso.MONTO.getCodigo());
            rhDescuento.setParTipoAplicacionDescuentoCriterioDeIngreso(parTipoAplicacionDescuentoCriterioDeIngreso);
            rhDescuento = rhDescuentoService.persistRhDescuento(rhDescuento);
            rhDescuentoEmpleadoCargoService.actualizaDescuentoEmpleadoCargo(rhDescuento, idPeriodoGestion);
            return Response.status(200).entity(rhDescuento).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/modificaRhDescuento")
    @ApiOperation(value = "modifica RhDescuento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificaRhDescuento(@ApiParam("rhDescuento") RhDescuento rhDescuento) {
        try {
            rhDescuento = rhDescuentoService.mergeRhDescuento(rhDescuento);
            return Response.status(200).entity(rhDescuento).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/listaRhDescuento")
    @ApiOperation(value = "obtiene lista de RhDescuento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaRhDescuento() throws Exception {
        try {
            List<RhDescuento> list = rhDescuentoService.obtieneDescuentosVigentes();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/listaRhDescuentoPorPeriodoGestion/{idPeriodoGestion}")
    @ApiOperation(value = "obtiene lista de RhDescuento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaRhDescuento(@PathParam("idPeriodoGestion") Long idPeriodoGestion) throws Exception {
        try {
            RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.encuentraRhPeriodoGestionPorId(idPeriodoGestion);
            List<RhDescuento> list;
            if (rhPeriodoGestion.getParEstadoPeriodoGestion().getCodigo().equals(EnumEstadoPeriodoGestion.LIQUIDADO.getCodigo()) || rhPeriodoGestion.getParEstadoPeriodoGestion().getCodigo().equals(EnumEstadoPeriodoGestion.CONTABILIZADO.getCodigo())) {
                list = rhDescuentoService.obtieneDescuentosVigentesPorPeriodo(idPeriodoGestion);
            } else {
                list = rhDescuentoService.obtieneDescuentosVigentes();
            }
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/verificaExistenciaCodigoDescuento/{codigo}")
    @ApiOperation(value = "existeCodigo RhDescuento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificaExistenciaCodigoDescuento(@PathParam("codigo") String codigo) {
        try {
            return Response.status(200).entity(rhDescuentoService.verificaExistenciaCodigoDescuento(codigo)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/verificaDescuentosPorIdDescuentoAndIdPeriodoParaEliminar/{idDescuento}/{idPeriodoGestion}")
    @ApiOperation(value = "verifica datos RhDescuento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificaDescuentosPorIdDescuentoAndIdPeriodoParaEliminar(@PathParam("idDescuento") Long idDescuento, @PathParam("idPeriodoGestion") Long idPeriodoGestion) {
        try {
            return Response.status(200).entity(rhDescuentoService.verificaDescuentosPorIdDescuentoAndIdPeriodoParaEliminar(idDescuento, idPeriodoGestion)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/deleteRhDescuento/{idDescuento}")
    @ApiOperation(value = "delete RhDescuento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRhDescuento(@PathParam("idDescuento") Long idDescuento) {
        try {
            rhDescuentoService.removeRhDescuento(idDescuento);
            return Response.status(200).entity(true).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
