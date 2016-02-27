/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws.rh;

import com.bap.erp.modelo.pojo.rh.RhEmpleadoDescuentoPojo;
import com.bap.erp.servicios.rh.RhDescuentoEmpleadoCargoService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jonas
 */
@Component
@Path("/rhDescuentoEmpleadoCargo")
@Api(value = "rhDescuentoEmpleadoCargo", description = "SW para rhDescuentoEmpleadoCargo")
public class RhDescuentoEmpleadoCargoWS {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhDescuentoEmpleadoCargoWS.class);
    
    @Autowired
    private RhDescuentoEmpleadoCargoService rhDescuentoEmpleadoCargoService;

    public RhDescuentoEmpleadoCargoWS() {
    }

    @PATCH
    @Path("/modificaRhDescuentoEmpleadoCargo/{idPeriodoGestion}")
    @ApiOperation(value = "operacion para modificar un DescuentoEmpeladoCargo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificaRhDescuentoEmpleadoCargo(@ApiParam("listaRhEmpleadoDescuentoPojo") List<RhEmpleadoDescuentoPojo> listaRhEmpleadoDescuentoPojo, @PathParam("idPeriodoGestion") Long idPeriodoGestion) {
        try {            
            rhDescuentoEmpleadoCargoService.modificaDescuentoEmpleadoCargo(listaRhEmpleadoDescuentoPojo, idPeriodoGestion);
            return Response.status(200).entity(true).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
                   
}
