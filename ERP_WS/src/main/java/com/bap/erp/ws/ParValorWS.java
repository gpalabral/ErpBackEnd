package com.bap.erp.ws;

import com.bap.erp.modelo.par.ParActividadEconomica;
import com.bap.erp.modelo.par.ParBanco;
import com.bap.erp.modelo.par.ParDepartamento;
import com.bap.erp.modelo.par.ParFormaDePago;
import com.bap.erp.modelo.par.ParLocalizacion;
import com.bap.erp.modelo.par.ParMunicipio;
import com.bap.erp.modelo.par.ParEstadoProceso;
import com.bap.erp.modelo.par.ParCaracteristicaEspecial;
import com.bap.erp.modelo.par.ParCliente;
import com.bap.erp.modelo.par.ParCondicionPension;
import com.bap.erp.modelo.par.ParEstado;
import com.bap.erp.modelo.par.ParEstadoCivil;
import com.bap.erp.modelo.par.ParEstadoFactura;
import com.bap.erp.modelo.par.ParEstadoLiquidacion;
import com.bap.erp.modelo.par.ParEstadoPago;
import com.bap.erp.modelo.par.ParGenero;
import com.bap.erp.modelo.par.ParMes;
import com.bap.erp.modelo.par.ParModalidadFacturacion;
import com.bap.erp.modelo.par.ParModalidadTransaccion;
import com.bap.erp.modelo.par.ParPeriodo;
import com.bap.erp.modelo.par.ParRecurrencia;
import com.bap.erp.modelo.par.ParSeccion;
import com.bap.erp.modelo.par.ParTipoAlicuota;
import com.bap.erp.modelo.par.ParTipoAplicacionRetencion;
import com.bap.erp.modelo.par.ParTipoCompra;
import com.bap.erp.modelo.par.ParTipoContacto;
import com.bap.erp.modelo.par.ParTipoContratoEmpleado;
import com.bap.erp.modelo.par.ParTipoDocumento;
import com.bap.erp.modelo.par.ParTipoDocumentoMercantil;
import com.bap.erp.modelo.par.ParTipoMoneda;
import com.bap.erp.modelo.par.ParTipoProveedorCliente;
import com.bap.erp.modelo.par.ParTipoRegistro;
import com.bap.erp.modelo.par.ParTipoRetencion;
import com.bap.erp.modelo.par.ParTipoTransaccion;
import com.bap.erp.modelo.par.ParTipoDocumentoPago;
import com.bap.erp.modelo.par.ParTipoEmpleado;
import com.bap.erp.modelo.par.ParTipoHito;
import com.bap.erp.modelo.par.ParTipoItem;
import com.bap.erp.modelo.par.ParTipoModulo;
import com.bap.erp.modelo.par.ParTipoPago;
import com.bap.erp.modelo.par.ParValor;
import com.bap.erp.servicios.par.ParValorService;
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

@Component
@Path("/parvalor")
@Api(value = "parvalor", description = "WS for ParValor")
public class ParValorWS {

    @Autowired
    private ParValorService parValorService;

    public ParValorWS() {
    }

    @GET
    @Path("/get")
    @ApiOperation(value = "Retrieves all ParValor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParValor() {
        List<ParValor> list = parValorService.getParValor();
        return Response.status(200).entity(list).build();
    }

    @PUT
    @Path("/put")
    @ApiOperation(value = "operation to INSERT a ParValor")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putParValor(
            @ApiParam(value = "cppgrupo", required = true) ParValor parValor) {

        parValorService.persistParValor(parValor);

        return Response.status(200).entity(parValor).build();
    }

    @GET
    @Path("/getParActividadEconomica")
    @ApiOperation(value = "Retrieves all ParActividadEconomica")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParActividadEconomica() {
        try {
            List<ParActividadEconomica> list = parValorService.getListParActividadEconomica();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParBanco")
    @ApiOperation(value = "Retrieves all ParBanco")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParBanco() {
        try {
            List<ParBanco> list = parValorService.getListParBanco();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParCaracteristicaEspecial")
    @ApiOperation(value = "Retrieves all ParCaracteristicaEspecial")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParCaracteristicaEspecial() {
        try {
            List<ParCaracteristicaEspecial> list = parValorService.getListParCaracteristicaEspecial();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParDepartamento")
    @ApiOperation(value = "Retrieves all ParDepartamento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParDepartamento() {
        try {
            List<ParDepartamento> list = parValorService.getListParDepartamento();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParEstado")
    @ApiOperation(value = "Retrieves all ParEstado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParEstado() {
        try {
            List<ParEstado> list = parValorService.getListParEstado();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParEstadoFactura")
    @ApiOperation(value = "Retrieves all ParEstadoFactura")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParEstadoFactura() {
        try {
            List<ParEstadoFactura> list = parValorService.getListParEstadoFactura();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParEstadoPago")
    @ApiOperation(value = "Retrieves all ParEstadoPago")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParEstadoPago() {
        try {
            List<ParEstadoPago> list = parValorService.getListParEstadoPago();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParEstadoProceso")
    @ApiOperation(value = "Retrieves all ParEstadoProceso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParEstadoProceso() {
        try {
            List<ParEstadoProceso> list = parValorService.getListParEstadoProceso();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParFormaDePago")
    @ApiOperation(value = "Retrieves all ParFormaDePago")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParFormaDePago() {
        try {
            List<ParFormaDePago> list = parValorService.getListParFormaDePago();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParLocalizacion")
    @ApiOperation(value = "Retrieves all ParLocalizacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParLocalizacion() {
        try {
            List<ParLocalizacion> list = parValorService.getListParLocalizacion();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParMes")
    @ApiOperation(value = "Retrieves all ParMes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParMes() {
        try {
            List<ParMes> list = parValorService.getListParMes();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParModalidadFacturacion")
    @ApiOperation(value = "Retrieves all ParModalidadFacturacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParModalidadFacturacion() {
        try {
            List<ParModalidadFacturacion> list = parValorService.getListParModalidadFacturacion();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParModalidadTransaccion")
    @ApiOperation(value = "Retrieves all ParModalidadTransaccion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParModalidadTransaccion() {
        try {
            List<ParModalidadTransaccion> list = parValorService.getListParModalidadTransaccion();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParMunicipio")
    @ApiOperation(value = "Retrieves all ParMunicipio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListgetParMunicipio() {
        try {
            List<ParMunicipio> list = parValorService.getListParMunicipio();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParPeriodo")
    @ApiOperation(value = "Retrieves all ParPeriodo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParPeriodo() {
        try {
            List<ParPeriodo> list = parValorService.getListParPeriodo();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParRecurrencia")
    @ApiOperation(value = "Retrieves all ParRecurrencia")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParRecurrencia() {
        try {
            List<ParRecurrencia> list = parValorService.getListParRecurrencia();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoContacto")
    @ApiOperation(value = "Retrieves all ParTipoContacto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipoContacto() {
        try {
            List<ParTipoContacto> list = parValorService.getListParTipoContacto();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoDocumento")
    @ApiOperation(value = "Retrieves all ParTipoDocumento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipoDocumento() {
        try {
            List<ParTipoDocumento> list = parValorService.getListParTipoDocumento();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoDocumentoMercantil")
    @ApiOperation(value = "Retrieves all ParTipoDocumentoMercantil")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipoDocumentoMercantil() {
        try {
            List<ParTipoDocumentoMercantil> list = parValorService.getListParTipoDocumentoMercantil();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoDocumentoPago")
    @ApiOperation(value = "Retrieves all ParTipoDocumentoPago")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipoDocumentoPago() {
        try {
            List<ParTipoDocumentoPago> list = parValorService.getListParTipoDocumentoPago();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoMoneda")
    @ApiOperation(value = "Retrieves all ParTipoMoneda")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipoMoneda() {
        try {
            List<ParTipoMoneda> list = parValorService.getListParTipoMoneda();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoPago")
    @ApiOperation(value = "Retrieves all ParTipoPago")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipoPago() {
        try {
            List<ParTipoPago> list = parValorService.getListParTipoPago();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoProveedorCliente")
    @ApiOperation(value = "Retrieves all ParTipoProveedorCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipoProveedorCliente() {
        try {
            List<ParTipoProveedorCliente> list = parValorService.getListParTipoProveedorCliente();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoRegistro")
    @ApiOperation(value = "Retrieves all ParTipoRegistro")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParTipoRegistro() {
        try {
            List<ParTipoRegistro> list = parValorService.getListParTipoRegistro();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoRetencion")
    @ApiOperation(value = "Retrieves all ParTipoRetencion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipoRetencion() {
        try {
            List<ParTipoRetencion> list = parValorService.getListParTipoRetencion();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoTransaccion")
    @ApiOperation(value = "Retrieves all ParTipoTransaccion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipoTransaccion() {
        try {
            List<ParTipoTransaccion> list = parValorService.getListParTipoTransaccion();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoItem")
    @ApiOperation(value = "Retrieves all ParTipoItem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipoItem() {
        try {
            List<ParTipoItem> list = parValorService.getListParTipoItem();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParMunicipio/{codigoDepartamento}")
    @ApiOperation(value = "Retrieves a ParMunicipio By codigoDepartamento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParMunicipioByDepartamento(@PathParam("codigoDepartamento") String codigoDepartamento) {
        try {
            List<ParMunicipio> list = parValorService.getListaMunicipiosByCodigoDepartamento(codigoDepartamento);
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParValorByCodigo/{codigo}")
    @ApiOperation(value = "Retrieves a ParValor by codigo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParValorByCodigo(@PathParam("codigo") String codigo) {
        try {
            ParValor parValor = parValorService.findByCodigo(codigo);
            return Response.status(200).entity(parValor).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParCaracteristicaEspecialParaFacturacion")
    @ApiOperation(value = "Retrieves all ParCaracteristicaEspecial for Facturacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParCaracteristicaEspecialParaFacturacion() {
        try {
            List<ParCaracteristicaEspecial> list = parValorService.getListParCaracteristicaEspecialParafacturacion();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoTransaccion/{modulo}")
    @ApiOperation(value = "Retrieves all ParTipoTransaccion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParTipoTransaccion(@PathParam("modulo") String modulo) {
        try {
            List<ParTipoTransaccion> list = parValorService.getTipoTransaccionPorModulo(modulo);
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoHito")
    @ApiOperation(value = "Retrieves all ParTipoHito")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParTipoHito() {
        try {
            List<ParTipoHito> list = parValorService.getListTipoHito();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PATCH
    @Path("/editParTipoAlicuota")
    @ApiOperation(value = "edit ParTipoAlicuota")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editParTipoAlicuota(@ApiParam("listaAlicuotas") List<ParTipoAlicuota> listaAlicuotas) {
        try {
            parValorService.actualizaDatosAlicuotas(listaAlicuotas);
            return Response.status(200).entity(listaAlicuotas).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/findParAlicuotaByCodigo/{codigo}")
    @ApiOperation(value = "Retrieves all ParAlicuota by codigo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findParAlicuotaByCodigo(@PathParam("codigo") String codigo) {
        try {
            ParTipoAlicuota parTipoAlicuota = parValorService.findParTipoAlicuotaByCodigo(codigo);
            return Response.status(200).entity(parTipoAlicuota).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getListParTipoCompra")
    @ApiOperation(value = "Retrieves all ParTipoCompra")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipoCompra() {
        try {
            List<ParTipoCompra> listParTipoCompra = parValorService.getListParTipoCompra();
            return Response.status(200).entity(listParTipoCompra).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getListParCliente")
    @ApiOperation(value = "Retrieves all ParCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParCliente() {
        try {
            List<ParCliente> listParCliente = parValorService.getListParCliente();
            return Response.status(200).entity(listParCliente).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getListParTipoAlicuota")
    @ApiOperation(value = "Retrieves all ParTipoAlicuota")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipoAlicuota() {
        try {
            List<ParTipoAlicuota> listParTipoAlicuotas = parValorService.getListParTipoAlicuota();
            return Response.status(200).entity(listParTipoAlicuotas).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getListParTipoModulo")
    @ApiOperation(value = "Retrieves all ParTipoModulo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipoModulo() {
        try {
            List<ParTipoModulo> listParTipoModulo = parValorService.getListParTipoModulo();
            return Response.status(200).entity(listParTipoModulo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoDocumentoMercantilByGrupo/{grupo}")
    @ApiOperation(value = "Retrieves all ParTipoDocumentoMercantil By Grupo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParTipoDocumentoMercantilByGrupo(@PathParam("grupo") String grupo) {
        try {
            List<ParTipoDocumentoMercantil> list = parValorService.getParTipoDocumentoMercantilByGrupo(grupo);
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    /**
     * Autor: Henrry Guzman Descripcion: Lista todos los tipos de aplicacciones
     * retencion existentes
     */
    @GET
    @Path("/getListParTipoAplicacionRetencion")
    @ApiOperation(value = "Retrieves all ParTipoAplicacionRetencion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListParTipoAplicacionRetencion() {
        try {
            List<ParTipoAplicacionRetencion> listParTipoModulo = parValorService.getListParTipoAplicacionRetencion();
            return Response.status(200).entity(listParTipoModulo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoTransaccionFactura")
    @ApiOperation(value = "Retrieves all getParTipoTransaccionFactura")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParTipoTransaccionFactura() {
        try {
            List<ParTipoTransaccion> list = parValorService.getListParTipoTransaccionFactura();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoTransaccionRetencion")
    @ApiOperation(value = "Retrieves all getParTipoTransaccionRetencion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParTipoTransaccionRetencion() {
        try {
            List<ParTipoTransaccion> list = parValorService.getListParTipoTransaccionRetencion();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParEstadoCivil")
    @ApiOperation(value = "Retrieves all ParEstadoCivil")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParEstadoCivil() {
        try {
            List<ParEstadoCivil> list = parValorService.getParEstadoCivil();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParCondicionPension")
    @ApiOperation(value = "Retrieves all ParCondicionPension")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParCondicionPension() {
        try {
            List<ParCondicionPension> list = parValorService.getParCondicionPension();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoEmpleado")
    @ApiOperation(value = "Retrieves all ParTipoEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParTipoEmpleado() {
        try {
            List<ParTipoEmpleado> list = parValorService.getParTipoEmpleado();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParGenero")
    @ApiOperation(value = "Retrieves all ParGenero")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParGenero() {
        try {
            List<ParGenero> list = parValorService.getParGenero();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParSeccion")
    @ApiOperation(value = "Retrieves all ParSeccion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParSeccion() {
        try {
            List<ParSeccion> list = parValorService.getParSeccion();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getParTipoContratoEmpleado")
    @ApiOperation(value = "Retrieves all ParTipoContratoEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParTipoContratoEmpleado() {
        try {
            List<ParTipoContratoEmpleado> list = parValorService.getParTipoContratoEmpleado();
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/verificaExistenciaDeDatosAlicuota")
    @ApiOperation(value = "Verifica existencia de datos Alicuota")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificaExistenciaDeDatosAlicuota() {
        try {
            Boolean sw = parValorService.verificaExistenciaDeDatosAlicuota();
            if (sw) {
                return Response.status(200).entity(1).build();
            } else {
                return Response.status(200).entity(0).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getParEstadoLiquidacionByCodigo/{codigo}")
    @ApiOperation(value = "Retrieves a ParEstadoLiquidacion by codigo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParEstadoLiquidacionByCodigo(@PathParam("codigo") String codigo) {
        try {
            ParEstadoLiquidacion estadoLiquidacion = parValorService.obtieneParEstadoLiquidacion(codigo);
            return Response.status(200).entity(estadoLiquidacion).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
