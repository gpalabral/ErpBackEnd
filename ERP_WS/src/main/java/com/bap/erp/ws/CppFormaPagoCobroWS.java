package com.bap.erp.ws;

import com.bap.erp.modelo.cpp.CppFormaPagoCobro;
import com.bap.erp.modelo.cpp.CppGrupo;
import com.bap.erp.servicios.cpp.CppFormaPagoCobroService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/cppFormaPagoCobro")
@Api(value = "cppFormaPagoCobro", description = "WS for CppFormaPagoCobro")
public class CppFormaPagoCobroWS {

    @Autowired
    private CppFormaPagoCobroService cppFormaPagoCobroService;

    public CppFormaPagoCobroWS() {
    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cppFormaPagoCobro")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCppGrupo() {
        try {
             List<CppFormaPagoCobro> list = cppFormaPagoCobroService.getCppFormaPagoCobro();
        return Response.status(200).entity(list).build();
        }  catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
       
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cppFormaPagoCobro")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCppFormaPagoCobro(@ApiParam(value = "cppFormaPagoCobro", required = true) CppFormaPagoCobro cppFormaPagoCobro) {
        try {
            cppFormaPagoCobroService.persistCppFormaPagoCobro(cppFormaPagoCobro);
            return Response.status(200).entity(cppFormaPagoCobro).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
