/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws.rh;

import com.bap.erp.modelo.rh.RhRcIva;
import com.bap.erp.servicios.rh.RhRcIvaService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jonas
 */
@Component
@Path("/rhRcIva")
@Api(value = "rhRcIva", description = "SW para rhRcIva")
public class RhRcIvaWS {

    @Autowired
    private RhRcIvaService rhRcIvaService;

    public RhRcIvaWS() {
    }
   
    
    @PATCH
    @Path("/modificaRhRcIva")
    @ApiOperation(value = "operacion para modificar un listado de RcIvas")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificaRhVariacion(@ApiParam("listaVariacion") List<RhRcIva> listaRhRcIva) {
        try {            
            rhRcIvaService.modificaListaRhRcIva(listaRhRcIva);
            return Response.status(200).entity(listaRhRcIva).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
}
