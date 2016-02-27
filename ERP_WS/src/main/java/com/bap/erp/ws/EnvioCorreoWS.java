/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.correo.EmailManagerService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jonas
 */
@Component
@Path("/correo")
@Api(value = "correo", description = "WS for EnvioCorreo")
public class EnvioCorreoWS {
    
    private static Logger log = Logger.getLogger(EnvioCorreoWS.class);

    @Autowired
    private EmailManagerService emailManagerService;

    public EnvioCorreoWS() {        
    }

    @POST
    @Path("/enviaCorreo")
    @ApiOperation(value = "Send mail")
    @Produces(MediaType.APPLICATION_JSON)
    public Response enviaCorreo(@FormParam("idFacturaEmitida") Long idFacturaEmitida) {
        try {
            
            log.info("idFacturaEmitida::: "+idFacturaEmitida);
            
            emailManagerService.enviarCorreoPrueba(idFacturaEmitida);
            return Response.status(200).entity("OK").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
