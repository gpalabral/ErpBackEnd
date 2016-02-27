package com.bap.erp.ws;

import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.pojo.CppFormaPagoCobroCuentasBancariasPojo;
import com.bap.erp.modelo.pojo.CppProveedorClienteBusquedaPojo;
import com.bap.erp.modelo.pojo.CppProveedorClientePojo;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import com.bap.erp.modelo.pojo.EntidadPojo;
import com.bap.erp.modelo.pojo.ErpProveedorClientePojo;
import com.bap.erp.servicios.cpp.CppConceptoService;
import com.bap.erp.servicios.cpp.CppContactoService;
import com.bap.erp.servicios.cpp.CppFormaPagoCobroService;
import com.bap.erp.servicios.cpp.CppProveedorClienteService;
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
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
@Path("/cppProveedorCliente")
@Api(value = "cppProveedorCliente", description = "WS for CppProveedorCliente")
public class CppProveedorClienteWS {

    @Autowired
    private CppProveedorClienteService cppProveedorClienteService;
    @Autowired
    private CppContactoService cppContactoService;
    @Autowired
    private CppFormaPagoCobroService cppFormaPagoCobroService;
    @Autowired
    private CppConceptoService cppConceptoService;

    public CppProveedorClienteWS() {
    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all cppProveedorCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCppProveedorCliente() {
        List<CppProveedorCliente> list = cppProveedorClienteService.getCppProveedorCliente();
        return Response.status(200).entity(list).build();
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a cppProveedorCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCppProveedorCliente(@ApiParam(value = "cppProveedorCliente", required = true) CppProveedorCliente cppProveedorCliente) {
        try {
            cppProveedorClienteService.persistCppProveedorCliente(cppProveedorCliente);
            return Response.status(200).entity(cppProveedorCliente).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getListaCppProveedorClientePorTipoRegistro")
    @ApiOperation(value = "Retrieves all cppProveedorCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaCppProveedorClientePorTipoRegistro(@QueryParam("tipoRegistro") String tipoRegistro) {
        List<CppProveedorCliente> list = cppProveedorClienteService.getListaCppProveedorClientePorTipoRegistro(tipoRegistro);
        return Response.status(200).entity(list).build();
    }

    @PUT
    @Path("/putCppProveedorClientePojo")
    @ApiOperation(value = "operation to INSERT a CppProveedorClienteCppContactoCppFormaPagoCobro ")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCppProveedorClientePojo(@ApiParam(value = "cppProveedorClientePojo", required = true) CppProveedorClientePojo cppProveedorClientePojo) {
        try {
            CppProveedorCliente cppProveedorCliente = cppProveedorClienteService.guardaProveedorClienteContactoFormaPagoCobro(cppProveedorClientePojo);
            return Response.status(200).entity(cppProveedorCliente).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCppProveedorClientePojo")
    @ApiOperation(value = "Retrieves cppProveedorClientePojo by idProveedorCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCppProveedorClientePojo(@QueryParam("idProveedorCliente") String idProveedorCliente) throws Exception {
        try {
            CppProveedorClientePojo cppProveedorClientePojo = new CppProveedorClientePojo();
            cppProveedorClientePojo.setCppProveedorCliente(cppProveedorClienteService.getCppProveedorCliente(Long.parseLong(idProveedorCliente)));
            cppProveedorClientePojo.setListaCppContacto(cppContactoService.listaContactosPorIdProveedor(idProveedorCliente));
            cppProveedorClientePojo.setCppFormaPagoCobro(cppFormaPagoCobroService.getCppFormaPagoCobroByIdProveedorCliente(Long.parseLong(idProveedorCliente)));
            cppProveedorClientePojo.setListaCppGruposConceptos(cppConceptoService.getListaEntidadArbolPojoPorProveedor(cppProveedorClientePojo.getCppProveedorCliente()));
            return Response.status(200).entity(cppProveedorClientePojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getListaProvCliNoAsignadosPorIdConcepto")
    @ApiOperation(value = "operation to list CppProveedorClienteConcepto by IdConcepto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getListaProvCliNoAsignadosPorIdConcepto(@QueryParam("idConcepto") String idConcepto) {
        List<CppProveedorCliente> lista = cppProveedorClienteService.getListaProveedoresNoAsignadosPorIdConcepto(Long.parseLong(idConcepto));
        return Response.status(200).entity(lista).build();
    }

    @GET
    @Path("/getGruposConceptosByProveedorCliente")
    @ApiOperation(value = "Retrieves all cppGrupo and cppConcepto by ProveedorCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGruposConceptosByProveedorCliente(@QueryParam("idCppProveedorCliente") String idCppProveedorCliente) {
        CppProveedorCliente cppProveedorCliente = cppProveedorClienteService.getCppProveedorCliente(Long.parseLong(idCppProveedorCliente));
        List<EntidadArbolPojo> listaOrdenada = cppConceptoService.getListaEntidadArbolPojoPorProveedor(cppProveedorCliente);
        return Response.status(200).entity(listaOrdenada).build();
    }

    @GET
    @Path("/getProveedorClienteTree/{tipoRegistro}")
    @ApiOperation(value = "Retrieves all cppProveedorCliente by tipoRegistro")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProveedorClienteTree(@PathParam("tipoRegistro") String tipoRegistro) throws Exception {
        try {
            List<EntidadArbolPojo> list = cppProveedorClienteService.getProveedorClienteTree(tipoRegistro);
            return Response.status(200).entity(list).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getGruposConceptosNoAsignadosTree/{idProveedorCliente}")
    @ApiOperation(value = "Retrieves all not asigned cppGrupo and cppConcepto by ProveedorCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGruposConceptosNoAsignadosTree(@PathParam("idProveedorCliente") String idProveedorCliente) {
        CppProveedorCliente cppProveedorCliente = cppProveedorClienteService.getCppProveedorCliente(Long.parseLong(idProveedorCliente));
        List<EntidadArbolPojo> listaOrdenada = cppConceptoService.getGruposConceptosNoAsignadosAProveedor(cppProveedorCliente);
        return Response.status(200).entity(listaOrdenada).build();
    }

    @GET
    @Path("/getProveedorClientePorIdConcepto/{idConcepto}/{asignados}")
    @ApiOperation(value = "Retrieves all not asigned cppGrupo and cppConcepto by ProveedorCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProveedorClientePorIdConcepto(@PathParam("idConcepto") String idConcepto, @PathParam("asignados") String asignados) {
        try {
            List<EntidadArbolPojo> listaOrdenada = cppProveedorClienteService.getProveedorClienteTree(cppProveedorClienteService.getProveedorClientePorIdConcepto(Long.parseLong(idConcepto), asignados));
            return Response.status(200).entity(listaOrdenada).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getProveedorCliente/{idProveedorCliente}")
    @ApiOperation(value = "Retrieves a CppProveedorCliente By IdProveedorCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProveedorCliente(@PathParam("idProveedorCliente") String idProveedorCliente) {
        CppProveedorCliente cppProveedorCliente = cppProveedorClienteService.getCppProveedorCliente(Long.parseLong(idProveedorCliente));
        return Response.status(200).entity(cppProveedorCliente).build();
    }

    @PATCH
    @Path("/edit")
    @ApiOperation(value = "edit CppProveedorClientePojo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@ApiParam("CppProveedorClientePojo") CppProveedorClientePojo cppProveedorClientePojo) {
        try {
            CppProveedorCliente cppProveedorCliente = cppProveedorClienteService.modificaProveedorClienteContactoFormaPagoCobroConceptos(cppProveedorClientePojo);
            return Response.status(200).entity(cppProveedorCliente).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getListByIdConcepto/{idConcepto}/{asignados}")
    @ApiOperation(value = "Retrieves all CppProveedorCliente by idConcepto and if it is assigned or not")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListByIdConcepto(@PathParam("idConcepto") String idConcepto, @PathParam("asignados") String asignados) {
        try {
            List<CppProveedorCliente> listaOrdenada = cppProveedorClienteService.getProveedorClientePorIdConcepto(Long.parseLong(idConcepto), asignados);
            return Response.status(200).entity(listaOrdenada).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/editProveedorCliente")
    @ApiOperation(value = "edit CppProveedorCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editProveedorCliente(@ApiParam("CppProveedorCliente") CppProveedorCliente cppProveedorCliente) {
        try {
            cppProveedorCliente = cppProveedorClienteService.mergeCppProveedorCliente(cppProveedorCliente);
            return Response.status(200).entity(cppProveedorCliente).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getListBytipoRegistro/{tipoRegistro}")
    @ApiOperation(value = "Retrieves all CppProveedorCliente by tipoRegistro")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListByTipoRegistro(@PathParam("parTipoRegistro.codigo") String tipoReg) {
        try {
            List<CppProveedorCliente> listatipoRegistro = cppProveedorClienteService.listaCpcProveedorClientePorTipoRegistro(tipoReg);
            return Response.status(200).entity(listatipoRegistro).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getProveedorClienteBusquedaPojo/{tipoRegistro}")
    @ApiOperation(value = "Retrieves all cppProveedorClienteBusquedaPojo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProveedorClienteBusquedaPojo(@PathParam(value = "tipoRegistro") String tipoRegistro) {
        try {
            List<CppProveedorClienteBusquedaPojo> list = cppProveedorClienteService.getCpcProveedorClienteBusquedaPojo(tipoRegistro);
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PUT
    @Path("/putFormaPagoCobroCuentasBancariasPojo")
    @ApiOperation(value = "operation to INSERT a putFormaPagoCobroCuentasBancariasPojo ")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putFormaPagoCobroCuentasBancariasPojo(@ApiParam(value = "cppFormaPagoCobroCuentasBancariasPojo", required = true) CppFormaPagoCobroCuentasBancariasPojo cppFormaPagoCobroCuentasBancariasPojo) {
        try {
            cppFormaPagoCobroCuentasBancariasPojo = cppProveedorClienteService.persistCppFormaPagoCobroCuentasBancariasPojo(cppFormaPagoCobroCuentasBancariasPojo);
            return Response.status(200).entity(cppFormaPagoCobroCuentasBancariasPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCpcFormaPagoCobroCuentasBancariasPojo/{idProveedorCliente}")
    @ApiOperation(value = "get cpcFormaPagoCobroCuentasBancariasPojo by idProveedorCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCuentaBancariaByIdCliente(@PathParam(value = "idProveedorCliente") Long idProveedorCliente) {
        try {
            CppFormaPagoCobroCuentasBancariasPojo cppFormaPagoCobroCuentasBancariasPojo = cppProveedorClienteService.getCppFormaPagoCobroCuentasBancariasPojoByIdProveedorCliente(idProveedorCliente);
            return Response.status(200).entity(cppFormaPagoCobroCuentasBancariasPojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getProveedorClienteParaBancarizar/{tipoRegistro}")
    @ApiOperation(value = "get All CppProveedorCliente for Bancarizacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProveedorClienteParaBancarizar(@PathParam(value = "tipoRegistro") String tipoRegistro) {
        try {
            List<CppProveedorClienteBusquedaPojo> listaProveedorCliente = cppProveedorClienteService.getCppProveedorClienteParaBancarizacion(tipoRegistro);
            return Response.status(200).entity(listaProveedorCliente).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getListProveedorCliente")
    @ApiOperation(value = "get All CppProveedorCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListProveedorCliente() {
        try {
            List<ErpProveedorClientePojo> listaProveedorCliente = cppProveedorClienteService.getListProveedorCliente();
            return Response.status(200).entity(listaProveedorCliente).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/verificaSiProveedorClienteNoEstaAsociado/{idProveedorCliente}")
    @ApiOperation(value = "Retrieves true if the code exist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVerificaFacturaParaDosificacionByIdDosificacion(@PathParam("idProveedorCliente") Long idProveedorCliente) {
        try {
            Boolean elimina = cppProveedorClienteService.verificaSiProveedorClienteNoEstaAsociado(idProveedorCliente);
            return Response.status(200).entity(elimina).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
//    MODULO CPP

    @PATCH
    @Path("/deleteProveedorCliente/{idProveedorCliente}")
    @ApiOperation(value = "delete CppProveedorCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProveedorCliente(@PathParam("idProveedorCliente")Long idProveedorCliente) {
        try {
            cppProveedorClienteService.removeCppProveedorCliente(idProveedorCliente);
            return Response.status(200).entity(true).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
//    Listado de Proveedores registrado en facturas por compras disponibles para bancarizar.
    @GET
    @Path("/getCppProveedorClienteParaBancarizar")
    @ApiOperation(value = "Retrieves CppProveedorCliente para Bancarizar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCppProveedorClienteParaBancarizar() {
        try {
            List<ErpProveedorClientePojo> listaProveedorClientePojo = cppProveedorClienteService.getCppProveedorClienteParaBancarizacionCpp();
            return Response.status(200).entity(listaProveedorClientePojo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
        
    

}
