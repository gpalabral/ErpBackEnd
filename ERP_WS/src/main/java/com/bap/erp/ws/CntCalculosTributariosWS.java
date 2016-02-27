/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.commons.entities.mappings.CalculosTributarios;
import com.bap.erp.ws.clients.ErpContabilidadClient;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jonas
 */
@Component
@Path("/cntCalculosTributarios")
@Api(value = "cntCalculosTributarios", description = "WS for CntCalculosTributarios")
public class CntCalculosTributariosWS {
    
    @Autowired
    private ErpContabilidadClient erpContabilidadClient;

    public CntCalculosTributariosWS() {

    }
    
    @POST
    @Path("/getCntComprobantePojo")
    @ApiOperation(value = "Retrieves tax calculations")
    @Produces(MediaType.APPLICATION_JSON)    
    @Consumes(MediaType.APPLICATION_JSON)    
    public Response getCntComprobantePojo(@ApiParam(value = "calculosTributarios", required = true) CalculosTributarios calculosTributarios) {  
        calculosTributarios = erpContabilidadClient.getCalculosTributarios(calculosTributarios);
        if(calculosTributarios==null){   
            Response.status(500).entity("Error en los datos enviados").build();
        }        
        return Response.status(200).entity(calculosTributarios).build();
    }
            
}
