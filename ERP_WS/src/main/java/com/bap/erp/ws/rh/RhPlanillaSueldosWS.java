/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws.rh;

import com.bap.erp.enums.EnumEstadoPeriodoGestion;
import com.bap.erp.modelo.pojo.rh.RhTransferenciasBancariasPojo;
import com.bap.erp.modelo.rh.RhPeriodoGestion;
import com.bap.erp.modelo.rh.RhPlanillaSueldos;
import com.bap.erp.servicios.rh.RhPeriodoGestionService;
import com.bap.erp.servicios.rh.RhPlanillaSueldosService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import java.util.ArrayList;
import static java.util.Collections.list;
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
 * @author jonas
 */
@Component
@Path("/rhPlanillaSueldos")
@Api(value = "rhPlanillaSueldos", description = "SW para rhPlanillaSueldos")
public class RhPlanillaSueldosWS {

    @Autowired
    private RhPlanillaSueldosService rhPlanillaSueldosService;
    @Autowired
    private RhPeriodoGestionService rhPeriodoGestionService;
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhPlanillaSueldosWS.class);

    public RhPlanillaSueldosWS() {
    }

    @PUT
    @Path("/guardaPlanillaSueldos")
    @ApiOperation(value = "operacion para guardar un listado de Planilla de Sueldos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardaPlanillaSueldos(@ApiParam("listaPlanillaSueldos") List<RhPlanillaSueldos> listaPlanillaSueldos) {
        try {
            listaPlanillaSueldos = rhPlanillaSueldosService.guardaRhPlanillaSueldos(listaPlanillaSueldos);
            return Response.status(200).entity(listaPlanillaSueldos).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/listaTransferenciasBancarias/{idPeriodoGestion}")
    @ApiOperation(value = "lista los empleados para las transferencias bancarias")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaTransferenciasBancarias(@PathParam("idPeriodoGestion") Long idPeriodoGestion) throws Exception {
        try {            
            RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.encuentraRhPeriodoGestionPorId(idPeriodoGestion);
            List<RhTransferenciasBancariasPojo> lista = new ArrayList<>();
            if (rhPeriodoGestion.getParEstadoPeriodoGestion().getCodigo().equals(EnumEstadoPeriodoGestion.LIQUIDADO.getCodigo()) || rhPeriodoGestion.getParEstadoPeriodoGestion().getCodigo().equals(EnumEstadoPeriodoGestion.CONTABILIZADO.getCodigo())) {
                lista = rhPlanillaSueldosService.listaTransferencias(idPeriodoGestion);
            }
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
}
