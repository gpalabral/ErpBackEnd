/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.ErpDepartamento;
import com.bap.erp.servicios.ErpDepartamentoService;
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
@Path("/erpDepartamento")
@Api(value = "erpDepartamento", description = "WS for erpDepartamento")
public class ErpDepartamentoWS {

    @Autowired
    private ErpDepartamentoService erpDepartamentoService;

    public ErpDepartamentoWS() {

    }
   
    @GET
    @Path("/getListaDepartamentosByEstado/{estado}")
    @ApiOperation(value = "Get All ErpDepartamento for Estado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaDepartamentosPorEstado(@PathParam("estado") String estado) {
        try {
            List<ErpDepartamento> listaDepartamento = erpDepartamentoService.listErpDepartamentoByEstado(estado);
            return Response.status(200).entity(listaDepartamento).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    
}
