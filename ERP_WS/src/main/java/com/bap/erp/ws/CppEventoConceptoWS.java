package com.bap.erp.ws;

import com.bap.erp.modelo.cpp.CppEventoConcepto;
import com.bap.erp.servicios.cpp.CppEventoConceptoService;
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
@Path("/cppEventoConcepto")
@Api(value="cppEventoConcepto", description="WS for CppEventoConcepto")
public class CppEventoConceptoWS {
    
    @Autowired
    private CppEventoConceptoService cppEventoConceptoService;
    
    public CppEventoConceptoWS()
    {
    }
    
    @GET
    @Path("/get")
    @ApiOperation(value="Retrieves all CppEventoConcepto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCppEventoConcepto() {
        List<CppEventoConcepto> list=cppEventoConceptoService.getCppEventoConcepto();
        return Response.status(200).entity(list).build();
    }
    
    @PUT
    @Path("/put")
    @ApiOperation(value="operation to INSERT a cppEventoConcepto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCppEventoConcepto(
		@ApiParam(value="cppEventoConcepto",required=true)  CppEventoConcepto cppEventoConcepto) {
        
        cppEventoConceptoService.persistCppEventoConcepto(cppEventoConcepto);
        
        return Response.status(200).entity(cppEventoConcepto).build();
    }
    
    
}
