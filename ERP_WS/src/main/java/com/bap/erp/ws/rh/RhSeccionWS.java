/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws.rh;

import com.bap.erp.modelo.rh.RhSeccion;
import com.bap.erp.servicios.rh.RhSeccionService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jonas - Henrry
 */
@Component
@Path("/rhSeccion")
@Api(value = "rhSeccion", description = "SW para rhSeccion")
public class RhSeccionWS {

    @Autowired
    private RhSeccionService rhSeccionService;

    public RhSeccionWS() {
    }    

    @GET
    @Path("/listaSeccionesPorIdDepartamento/{idDepartamento}")
    @ApiOperation(value = "Retorna todas las Secciones por Departamento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaRhEmpleado(@PathParam (value="idDepartamento")Long idDepartamento) throws Exception {
        try {
            List<RhSeccion> listaSecciones = rhSeccionService.listaRhSeccionPorIdDepartamento(idDepartamento);
            return Response.status(200).entity(listaSecciones).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
