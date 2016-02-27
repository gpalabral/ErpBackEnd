package com.bap.cpanel.ws;

import com.bap.cpanel.modelo.adm.AdmPersona;
import com.bap.cpanel.servicios.adm.AdmPersonaService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.jaxrs.PATCH;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Path("/admpersona")
@Api(value = "admpersona", description = "WS for AdmPersona")
public class AdmPersonaWS {

    @Autowired
    private AdmPersonaService admPersonaService;

    public AdmPersonaWS() {

    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT an admpersona")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putAdmpersona(@ApiParam(value = "admpersona", required = true) AdmPersona admPersona) {
        try {
            admPersona = admPersonaService.persistAdmPersona(admPersona);
            return Response.status(200).entity(admPersona).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrievs all admpersonas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmPersona() {
        try {
            List<AdmPersona> lista = admPersonaService.getAdmPersona();
            return Response.status(200).entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/edit")
    @ApiOperation(value = "edit AdmPersona")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@ApiParam("AdmPersona") AdmPersona admPersona) {
        try {
            admPersona =admPersonaService.mergeAdmPersona(admPersona);
            return Response.status(200).entity(admPersona).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }  

}
