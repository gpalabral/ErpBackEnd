package com.bap.erp.ws.rh;

import com.bap.erp.enums.EnumTipoEntidad;
import com.bap.erp.modelo.pojo.rh.RhEmpleadoCriterioDeIngresoPojo;
import com.bap.erp.modelo.pojo.rh.RhEmpleadoDescuentoPojo;
import com.bap.erp.modelo.rh.RhPrimas;
import com.bap.erp.modelo.rh.RhRcIva;
import com.bap.erp.modelo.rh.RhVariacion;
import com.bap.erp.servicios.rh.RhCriterioDeIngresoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhDescuentoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhPrimasService;
import com.bap.erp.servicios.rh.RhRcIvaService;
import com.bap.erp.servicios.rh.RhVariacionService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import java.io.InputStream;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Component;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;

@Component
@Path("/rhImport")
@Api(value = "rhImport", description = "WS para importacion RRHH")
public class RhImportWS {

    @Autowired
    private RhDescuentoEmpleadoCargoService rhDescuentoEmpleadoCargoService;
    @Autowired
    private RhVariacionService rhVariacionService;
    @Autowired
    private RhCriterioDeIngresoEmpleadoCargoService rhCriterioDeIngresoEmpleadoCargoService;
    @Autowired
    private RhRcIvaService rhRcIvaService;
    @Autowired
    private RhPrimasService rhPrimasService;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhImportWS.class);

    @POST
    @Path("/importacionRRHH/xls")
    @ApiOperation(value = "Importacion de RRHH")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response importacionRRHH(
            @FormDataParam("uploadFile") InputStream fileInputStream,
            @FormDataParam("uploadFile") FormDataContentDisposition fileDetail,
            @FormDataParam("tipoEntidad") String tipoEntidad,
            @FormDataParam("idPeriodoGestion") Long idPeriodoGestion) {
        try {
            /**
             * *************DESCUENTOS******************
             */
            if (tipoEntidad.equals(EnumTipoEntidad.DESCUENTOS.getCodigo())) {
                rhDescuentoEmpleadoCargoService.importaValoresExcel(fileInputStream, idPeriodoGestion);
                List<RhEmpleadoDescuentoPojo> listaEmpleadoDescuentoPojo = rhDescuentoEmpleadoCargoService.cargaEmpleadoDescuento(idPeriodoGestion);
                return Response.status(200).entity(listaEmpleadoDescuentoPojo).build();
            } /**
             * *************VARIACIONES******************
             */
            else if (tipoEntidad.equals(EnumTipoEntidad.VARIACIONES.getCodigo())) {
                rhVariacionService.importaValoresExcel(fileInputStream, idPeriodoGestion);
                List<RhVariacion> listaVariacion = rhVariacionService.getRhVariacionPorPeriodo(idPeriodoGestion);
                return Response.status(200).entity(listaVariacion).build();
            }/**
             * *************CRITERIOS DE INGRESO******************
             */
            else if (tipoEntidad.equals(EnumTipoEntidad.CRITERIO_DE_INGRESO.getCodigo())) {
                rhCriterioDeIngresoEmpleadoCargoService.importaValoresExcel(fileInputStream, idPeriodoGestion);
                List<RhEmpleadoCriterioDeIngresoPojo> listaEmpleadoCriterioDeIngresoPojo = rhCriterioDeIngresoEmpleadoCargoService.cargaEmpleadoCriterioDeIngreso(idPeriodoGestion);
                return Response.status(200).entity(listaEmpleadoCriterioDeIngresoPojo).build();
            }/**
             * *************RCIVA******************
             */
            else if (tipoEntidad.equals(EnumTipoEntidad.RC_IVA.getCodigo())) {
                rhRcIvaService.importaValoresExcel(fileInputStream, idPeriodoGestion);
                List<RhRcIva> listaRhRcIva = rhRcIvaService.listaRhRcIvaPorPeriodo(idPeriodoGestion);
                return Response.status(200).entity(listaRhRcIva).build();
            }/**
             * *************PRIMAS******************
             */
            else if (tipoEntidad.equals(EnumTipoEntidad.PRIMAS.getCodigo())) {
                rhPrimasService.importaPrimasExcel(fileInputStream, idPeriodoGestion);
                List<RhPrimas> listaRhPrimas = rhPrimasService.listaRhPrimasPorPeriodo(idPeriodoGestion);
                return Response.status(200).entity(listaRhPrimas).build();
            } else {
                return Response.ok(true).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

}
