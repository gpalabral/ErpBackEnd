/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.ErpAplicante;
import com.bap.erp.modelo.pojo.ErpAplicantePojo;
import com.bap.erp.servicios.ErpAplicanteService;
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
 * @author jonas
 */
@Component
@Path("/erpAplicante")
@Api(value = "erpAplicante", description = "WS for erpAplicante")
public class ErpAplicanteWS {

    @Autowired
    private ErpAplicanteService erpAplicanteService;

    public ErpAplicanteWS() {

    }
   
    @GET
    @Path("/getListErpAplicanteByIdDepartamento/{idDepartamento}")
    @ApiOperation(value = "get All the ErpAplicante for idDepartamento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListErpAplicanteByIdDepartamento(@PathParam("idDepartamento") Long idDepartamento) {
        try {
            List<ErpAplicantePojo> listaAplicantes = erpAplicanteService.listErpAplicanteByIdDepartamento(idDepartamento);
            return Response.status(200).entity(listaAplicantes).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    
}
