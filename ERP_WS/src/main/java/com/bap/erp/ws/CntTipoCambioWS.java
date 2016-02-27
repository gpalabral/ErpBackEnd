/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.pojo.CntTipoCambioPojo;
import com.bap.erp.ws.clients.ErpContabilidadClient;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jonas
 */
@Component
@Path("/cntTipoCambio")
@Api(value = "cntTipoCambio", description = "WS for CntTipoCambio, formato de la fecha: DD/MM/YYYY")
public class CntTipoCambioWS {
    
    @Autowired
    private ErpContabilidadClient erpContabilidadClient;

    public CntTipoCambioWS() {

    }
    
        @GET
        @Path("/getCntTipoCambioPojo")
        @ApiOperation(value = "Retrieves all cntTipoCambioPojo")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getTipoDeCambio(@QueryParam("fecha") String fecha) {

            CntTipoCambioPojo cntTipoCambioPojo = erpContabilidadClient.getTipoCambio(fecha);

            if(cntTipoCambioPojo==null)
            {   Response.status(500).entity("Formato de fecha invalido").build();
            }

            return Response.status(200).entity(cntTipoCambioPojo).build();
        }
    
}
