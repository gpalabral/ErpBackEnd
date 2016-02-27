package com.bap.erp.ws.rh;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.bap.erp.modelo.rh.RhPeriodoGestion;
import com.bap.erp.servicios.rh.RhPeriodoGestionService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
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
@Path("/rhPeriodoGestion")
@Api(value = "rhPeriodoGestion", description = "SW para rhPeriodoGestion")
public class RhPeriodoGestionWS {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhPeriodoGestionWS.class);

    @Autowired
    private RhPeriodoGestionService rhPeriodoGestionService;

    public RhPeriodoGestionWS() {
    }

    @GET
    @Path("/existeGestion/{periodo}/{gestion}")
    @ApiOperation(value = "verifica si existe gestion y periodo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response existeGestion(@PathParam("periodo") int periodo, @PathParam("gestion") int gestion) {
        try {
            Boolean existe = rhPeriodoGestionService.existePeriodoGestion(periodo, gestion);
            return Response.status(200).entity(existe).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/obtieneRegistroPorPeriodoGestion/{periodo}/{gestion}")
    @ApiOperation(value = "verifica si existe gestion y periodo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtieneRegistroPorPeriodoGestion(@PathParam("periodo") int periodo, @PathParam("gestion") int gestion) {
        try {
            RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.obtieneRegistroPorPeriodoGestion(periodo, gestion);
            return Response.status(200).entity(rhPeriodoGestion).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/obtienePeriodoGestionUltimoVigente")
    @ApiOperation(value = "obtiene vigente ultimo RhPeriodoGestion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtienePeriodoGestionUltimoVigente() throws Exception {
        try {
            RhPeriodoGestion periodoGestion = rhPeriodoGestionService.obtienePeriodoGestionUltimoVigente();
            if (periodoGestion == null) {
                return Response.status(200).entity(0).build();
            } else {
                return Response.status(200).entity(periodoGestion).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/obtienePeriodoGestionUltimoNoVigente")
    @ApiOperation(value = "obtiene no vigente ultimo RhPeriodoGestion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtienePeriodoGestionUltimoNoVigente() throws Exception {
        try {
            RhPeriodoGestion periodoGestion = rhPeriodoGestionService.obtienePeriodoGestionUltimoNoVigente();
            if (periodoGestion == null) {
                return Response.status(200).entity(0).build();
            } else {
                return Response.status(200).entity(periodoGestion).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getPeriodoGestionById/{idPeriodo}")
    @ApiOperation(value = "obtiene no vigente ultimo RhPeriodoGestion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeriodoGestionById(@PathParam("idPeriodo") Long iPeriodo) throws Exception {
        try {
            RhPeriodoGestion periodoGestion = rhPeriodoGestionService.encuentraRhPeriodoGestionPorId(iPeriodo);
            return Response.status(200).entity(periodoGestion).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PUT
    @Path("/persistRhPeriodoGestionCompleto")
    @ApiOperation(value = "operacion para insertar una PeriodoGestion completo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persistRhPeriodoGestionCompleto(@ApiParam(value = "rhPeriodoGestion", required = true) RhPeriodoGestion rhPeriodoGestion) {
        try {
            rhPeriodoGestion = rhPeriodoGestionService.persistRhPeriodoGestionCompleto(rhPeriodoGestion);
            return Response.status(200).entity(rhPeriodoGestion).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
