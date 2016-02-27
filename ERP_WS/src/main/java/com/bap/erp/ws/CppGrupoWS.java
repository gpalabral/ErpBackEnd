package com.bap.erp.ws;

import com.bap.erp.modelo.cpp.CppGrupo;
import com.bap.erp.modelo.pojo.CntCuentaPlanCuentasPojo;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import com.bap.erp.servicios.cpp.CppGrupoService;
import com.bap.erp.ws.clients.ErpContabilidadClient;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/cppgrupo")
@Api(value = "cppgrupo", description = "WS for CppGrupo")
public class CppGrupoWS {

    @Autowired
    private CppGrupoService cppGrupoService;

    @Autowired
    private ErpContabilidadClient erpContabilidadClient;

    public CppGrupoWS() {
    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cppGrupo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCppGrupo() {
        List<CppGrupo> list = cppGrupoService.getCppGrupo();
        return Response.status(200).entity(list).build();
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cppgrupo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCppGrupo(
            @ApiParam(value = "cppgrupo", required = true) CppGrupo cppGrupo) {

        cppGrupoService.persistCppGrupo(cppGrupo);

        return Response.status(200).entity(cppGrupo).build();
    }

    @GET
    @Path("/getGruposConceptos")
    @ApiOperation(value = "Retrieves all cppGrupo and cppConcepto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGruposConceptos() {
        List<EntidadArbolPojo> listaOrdenada = cppGrupoService.getListaEntidadArbolPojo();
        return Response.status(200).entity(listaOrdenada).build();
    }

    @GET
    @Path("/getGrupo")
    @ApiOperation(value = "Retrieves a cppGrupo by Id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGruposConceptos(@QueryParam("idCppGrupo") String idCppGrupo) {
        CppGrupo cppGrupo = cppGrupoService.getCppGrupo(Long.parseLong(idCppGrupo));
        return Response.status(200).entity(cppGrupo).build();
    }

    @GET
    @Path("/getCntEntidadById/{idEntidad}")
    @ApiOperation(value = "get CntEntidad By Id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCntEntidadById(@PathParam("idEntidad") String idEntidad) {
        try {
            CntCuentaPlanCuentasPojo cntCuentaPlanCuentasPojo = erpContabilidadClient.getCntCuentaPlanCuentasPojoByIdEntidad(idEntidad);
            return Response.status(200).entity(cntCuentaPlanCuentasPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/edit")
    @ApiOperation(value = "edit CppGrupo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@ApiParam("CppGrupo") CppGrupo cppGrupo) {
        cppGrupo = cppGrupoService.mergeCppGrupo(cppGrupo);
        return Response.status(200).entity(cppGrupo).build();
    }

    @GET
    @Path("/getGruposConConceptosAsignadosTree")
    @ApiOperation(value = "Retrieves all Grupos with assigned Conceptos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGruposConConceptosAsignadosTree() {
        List<EntidadArbolPojo> listaOrdenada = cppGrupoService.getGruposConConceptosAsignados();
        return Response.status(200).entity(listaOrdenada).build();
    }

}
