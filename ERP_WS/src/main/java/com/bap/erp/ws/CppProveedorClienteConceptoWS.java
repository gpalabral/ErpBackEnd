package com.bap.erp.ws;

import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.cpp.CppProveedorClienteConcepto;
import com.bap.erp.modelo.pojo.ProveedorGrupoConcepto;
import com.bap.erp.servicios.cpp.CppProveedorClienteConceptoService;
import com.bap.erp.servicios.cpp.CppProveedorClienteService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/cppProveedorClienteConcepto")
@Api(value = "cppProveedorClienteConcepto", description = "WS for CppProveedorClienteConcepto")
public class CppProveedorClienteConceptoWS {

    @Autowired
    private CppProveedorClienteConceptoService cppProveedorClienteConceptoService;

    @Autowired
    private CppProveedorClienteService cppProveedorClienteService;

    public CppProveedorClienteConceptoWS() {
    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cppProveedorClienteContacto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCppProveedorClienteContacto() {
        List<CppProveedorClienteConcepto> list = cppProveedorClienteConceptoService.getCppProveedorClienteConcepto();
        return Response.status(200).entity(list).build();
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cppProveedorClienteContacto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCppProveedorClienteConcepto(@ApiParam(value = "cppProveedorClienteConcepto", required = true) CppProveedorClienteConcepto cppProveedorClienteConcepto) {
        try {
            cppProveedorClienteConceptoService.persistCppProveedorClienteConcepto(cppProveedorClienteConcepto);
            return Response.status(200).entity(cppProveedorClienteConcepto).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getListaPorIdProveedorCliente")
    @ApiOperation(value = "operation to list CppProveedorClienteConcepto by IdCppProveedorCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getListaPorProveedorCliente(@QueryParam("idCppProveedorCliente") String idCppProveedorCliente) {
        List<CppProveedorClienteConcepto> lista = cppProveedorClienteConceptoService.listaCppProveedorClienteConceptoPorIdCppProveedorCliente(Long.parseLong(idCppProveedorCliente));
        return Response.status(200).entity(lista).build();
    }

    @GET
    @Path("/getListaPorIdConcepto")
    @ApiOperation(value = "operation to list CppProveedorClienteConcepto by IdConcepto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getListaPorIdConcepto(@QueryParam("idConcepto") String idConcepto) {
        List<CppProveedorClienteConcepto> lista = cppProveedorClienteConceptoService.listaCppProveedorClienteConceptoPorIdConcepto(Long.parseLong(idConcepto));
        return Response.status(200).entity(lista).build();
    }

    @GET
    @Path("/getListaProveedoresConConceptosAsignados")
    @ApiOperation(value = "operation to list CppProveedorCliente with at least one concept")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getListaProveedoresConConceptosAsignados() {
        List<CppProveedorCliente> lista = cppProveedorClienteService.listaProveedoresConConceptosAsignados();
        return Response.status(200).entity(lista).build();
    }
    
    @GET
    @Path("/getProveedorGrupoConcepto")
    @ApiOperation(value = "operation to list Proveedores Grupos and Conceptos with at least one concept asigned")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProveedorGrupoConcepto() {
        List<ProveedorGrupoConcepto> lista = cppProveedorClienteConceptoService.getProveedorGrupoConcepto();
        return Response.status(200).entity(lista).build();
    }

}
