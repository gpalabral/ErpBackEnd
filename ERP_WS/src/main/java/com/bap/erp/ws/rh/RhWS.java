/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws.rh;

import com.bap.erp.enums.EnumTipoEntidad;
import com.bap.erp.modelo.pojo.rh.RhEmpleadoCriterioDeIngresoPojo;
import com.bap.erp.modelo.pojo.rh.RhEmpleadoDescuentoPojo;
import com.bap.erp.modelo.rh.RhEmpleado;
import com.bap.erp.modelo.rh.RhPlanillaAportesSeguridadSocial;
import com.bap.erp.modelo.rh.RhPlanillaImpositiva;
import com.bap.erp.modelo.rh.RhPlanillaSueldos;
import com.bap.erp.modelo.rh.RhPrimas;
import com.bap.erp.modelo.rh.RhRcIva;
import com.bap.erp.modelo.rh.RhVariacion;
import com.bap.erp.servicios.rh.RhCriterioDeIngresoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhDescuentoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhEmpleadoService;
import com.bap.erp.servicios.rh.RhLiquidacionService;
import com.bap.erp.servicios.rh.RhPlanillaAportesSeguridadSocialService;
import com.bap.erp.servicios.rh.RhPlanillaImpositivaService;
import com.bap.erp.servicios.rh.RhPlanillaSueldosService;
import com.bap.erp.servicios.rh.RhPrimasService;
import com.bap.erp.servicios.rh.RhRcIvaService;
import com.bap.erp.servicios.rh.RhVariacionService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("/rhWS")
@Api(value = "rhWS", description = "SW para RRHH")
public class RhWS {

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

    @Autowired
    private RhPlanillaSueldosService rhPlanillaSueldosService;

    @Autowired
    private RhPlanillaImpositivaService rhPlanillaImpositivaService;

    @Autowired
    private RhEmpleadoService rhEmpleadoService;

    @Autowired
    private RhLiquidacionService rhLiquidacionService;
    
    @Autowired
    private RhPlanillaAportesSeguridadSocialService rhPlanillaAportesSeguridadSocialService;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhWS.class);

    public RhWS() {
    }

    @GET
    @Path("/generaRegistrosPorPeriodo/{idPeriodoGestion}/{tipoEntidad}")
    @ApiOperation(value = "operacion para generar registros de la entidad por periodo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response generaRegistrosPorPeriodo(@PathParam("idPeriodoGestion") Long idPeriodoGestion, @PathParam("tipoEntidad") String tipoEntidad) {
        try {
            /**
             * *************DESCUENTOS******************
             */
            if (tipoEntidad.equals(EnumTipoEntidad.DESCUENTOS.getCodigo())) {
                if (!rhDescuentoEmpleadoCargoService.existeDescuentoPorPeriodo(idPeriodoGestion)) {
                    rhDescuentoEmpleadoCargoService.registraDescuentoEmpleadoCargoPorPeriodo(idPeriodoGestion);
                }
                List<RhEmpleadoDescuentoPojo> listaEmpleadoDescuentoPojo = rhDescuentoEmpleadoCargoService.cargaEmpleadoDescuento(idPeriodoGestion);
                return Response.status(200).entity(listaEmpleadoDescuentoPojo).build();
            }/**
             * *************VARIACIONES******************
             */
            else if (tipoEntidad.equals(EnumTipoEntidad.VARIACIONES.getCodigo())) {
                if (!rhVariacionService.existeVariacionPorPeriodo(idPeriodoGestion)) {
                    rhVariacionService.registrarRhVariacionPorPeriodo(idPeriodoGestion);
                }
                List<RhVariacion> listaVariaciones = rhVariacionService.getRhVariacionPorPeriodo(idPeriodoGestion);

                return Response.status(200).entity(listaVariaciones).build();
            }/**
             * *************CRITERIOS DE DESCUENTO******************
             */
            else if (tipoEntidad.equals(EnumTipoEntidad.CRITERIO_DE_INGRESO.getCodigo())) {
                if (!rhCriterioDeIngresoEmpleadoCargoService.existeCriterioDeIngresoEmpleadoCargoPorPeriodo(idPeriodoGestion)) {
                    rhCriterioDeIngresoEmpleadoCargoService.registrarCriterioDeIngresoPorPeriodo(idPeriodoGestion);
                }
                List<RhEmpleadoCriterioDeIngresoPojo> listaEmpleadoCriterioDeIngresoPojo = rhCriterioDeIngresoEmpleadoCargoService.cargaEmpleadoCriterioDeIngreso(idPeriodoGestion);
                return Response.status(200).entity(listaEmpleadoCriterioDeIngresoPojo).build();
            }/**
             * *************RC IVA******************
             */
            else if (tipoEntidad.equals(EnumTipoEntidad.RC_IVA.getCodigo())) {
                if (!rhRcIvaService.existeRcIvaPorPeriodo(idPeriodoGestion)) {
                    rhRcIvaService.registrarRhRcIvaPorPeriodo(idPeriodoGestion);
                }
                List<RhRcIva> listaRhRcIva = rhRcIvaService.listaRhRcIvaPorPeriodo(idPeriodoGestion);
                return Response.status(200).entity(listaRhRcIva).build();
            }/**
             * *************PRIMAS******************
             */
            else if (tipoEntidad.equals(EnumTipoEntidad.PRIMAS.getCodigo())) {
                if (!rhPrimasService.existePrimasPorPeriodo(idPeriodoGestion)) {
                    rhPrimasService.registrarRhPrimasPorPeriodo(idPeriodoGestion);
                }
                List<RhPrimas> listaRhPrimas = rhPrimasService.listaRhPrimasPorPeriodo(idPeriodoGestion);
                return Response.status(200).entity(listaRhPrimas).build();
            }/**
             * *************PLANILLA SUELDOS******************
             */
            else if (tipoEntidad.equals(EnumTipoEntidad.PLANILLA_SUELDOS.getCodigo())) {
                List<RhPlanillaSueldos> listaRhPlanillaSueldos;
                if (rhPlanillaSueldosService.existePlanillaSueldosPorPeriodo(idPeriodoGestion)) {
                    listaRhPlanillaSueldos = rhPlanillaSueldosService.cargaPlanillaSueldosPorIdPeriodoGestionEstadosLiquidadoContabilizado(idPeriodoGestion);
                } else {
                    listaRhPlanillaSueldos = rhPlanillaSueldosService.generaPlanillaDeSueldos(idPeriodoGestion);
                }
                return Response.status(200).entity(listaRhPlanillaSueldos).build();
            }/**
             * *************PLANILLA IMPOSITIVA******************
             */
            else if (tipoEntidad.equals(EnumTipoEntidad.PLANILLA_IMPOSITIVA.getCodigo())) {
                List<RhPlanillaImpositiva> listaRhPlanillaImpositiva;
                if (rhPlanillaImpositivaService.existePlanillaImpositivaPorPeriodo(idPeriodoGestion)) {
                    listaRhPlanillaImpositiva = rhPlanillaImpositivaService.cargaPlanillaImpositivaPorPeriodoGestionEstadosLiquidadoContabilizado(idPeriodoGestion);
                } else {
                    listaRhPlanillaImpositiva = rhPlanillaImpositivaService.generaPlanillaImpositiva(idPeriodoGestion);
                }
                return Response.status(200).entity(listaRhPlanillaImpositiva).build();
            } /**
             * *************PLANILLA APORTES SEGURIDAD SOCIAL******************
             */
            else if (tipoEntidad.equals(EnumTipoEntidad.PLANILLA_APORTES_SEGURIDAD_SOCIAL.getCodigo())) {
                List<RhPlanillaAportesSeguridadSocial> listaRhPlanillaAportesSeguridadSocial;
                if (rhPlanillaAportesSeguridadSocialService.existePlanillaAportesSeguridadSocialPorPeriodo(idPeriodoGestion)) {
                    listaRhPlanillaAportesSeguridadSocial = rhPlanillaAportesSeguridadSocialService.cargaPlanillaAportesSeguridadSocialPorPeriodoGestion(idPeriodoGestion);
                } else {
                    listaRhPlanillaAportesSeguridadSocial = rhPlanillaAportesSeguridadSocialService.generaPlanillaAportesSeguridadSocial(idPeriodoGestion);
                }
                return Response.status(200).entity(listaRhPlanillaAportesSeguridadSocial).build();
            } else {
                return Response.status(200).entity(false).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }

    }

    @GET
    @Path("/obtieneSaldoActualizado/{idEmpleadoCargo}/{idPeriodoGestion}")
    @ApiOperation(value = "obtiene Saldo Actualizado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtieneSaldoActualizado(@PathParam("idEmpleadoCargo") Long idEmpleadoCargo, @PathParam("idPeriodoGestion") Long idPeriodoGestion) {
        try {
            BigDecimal resultado = rhPlanillaImpositivaService.obtieneSaldoActualizado(idEmpleadoCargo, idPeriodoGestion);
            return Response.status(200).entity(resultado).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/listaRhEmpleadosNuevosByPeriodoGestion/{idPeriodoGestion}/{tipoEntidad}")
    @ApiOperation(value = "Retrieves all RhEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generaRegistrosEmpleadosNuevos(@PathParam("idPeriodoGestion") Long idPeriodoGestion, @PathParam("tipoEntidad") String tipoEntidad) throws Exception {
        try {
            /**
             * *************DESCUENTOS******************
             */
            if (tipoEntidad.equals(EnumTipoEntidad.DESCUENTOS.getCodigo())) {
                List<RhEmpleado> list = rhEmpleadoService.listaRhEmpleadosNuevosSinDescuentos(idPeriodoGestion);
                return Response.status(200).entity(list).build();
            }/**
             * *************VARIACIONES******************
             */
            else if (tipoEntidad.equals(EnumTipoEntidad.VARIACIONES.getCodigo())) {
                List<RhEmpleado> list = rhEmpleadoService.listaRhEmpleadosNuevosSinVariaciones(idPeriodoGestion);
                return Response.status(200).entity(list).build();
            }/**
             * *************CRITERIOS DE DESCUENTO******************
             */
            else if (tipoEntidad.equals(EnumTipoEntidad.CRITERIO_DE_INGRESO.getCodigo())) {
                List<RhEmpleado> list = rhEmpleadoService.listaRhEmpleadosNuevosSinCriteriosIngreso(idPeriodoGestion);
                return Response.status(200).entity(list).build();
            }/**
             * *************RC IVA******************
             */
            else if (tipoEntidad.equals(EnumTipoEntidad.RC_IVA.getCodigo())) {
                List<RhEmpleado> list = rhEmpleadoService.listaRhEmpleadosNuevosSinRcIva(idPeriodoGestion);
                return Response.status(200).entity(list).build();
            }/**
             * *************PRIMAS******************
             */
            else if (tipoEntidad.equals(EnumTipoEntidad.PRIMAS.getCodigo())) {
                List<RhEmpleado> list = rhEmpleadoService.listaRhEmpleadosNuevosSinPrima(idPeriodoGestion);
                return Response.status(200).entity(list).build();
            }
            return Response.status(200).entity(false).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @DELETE
    @Path("/liquidacion/eliminarPlanillas/{idPeriodoGestion}")
    @ApiOperation(value = "operacion para guardar un listado de Planilla de Sueldos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarLiquidacion(@PathParam("idPeriodoGestion") Long idPeriodoGestion) {
        try {
            rhLiquidacionService.eliminarLiquidacion(idPeriodoGestion);
            return Response.status(200).entity(true).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/existeLiquidacion/{idPeriodoGestion}/{tipoEntidad}")
    @ApiOperation(value = "operacion para verificar si existen liquidaciones en BD")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response existeLiquidacion(@PathParam("idPeriodoGestion") Long idPeriodoGestion, @PathParam("tipoEntidad") String tipoEntidad) {
        try {
            /**
             * *************PLANILLA SUELDOS******************
             */
            if (tipoEntidad.equals(EnumTipoEntidad.PLANILLA_SUELDOS.getCodigo())) {

                return Response.status(200).entity(rhPlanillaSueldosService.existePlanillaSueldosPorPeriodo(idPeriodoGestion)).build();
            }/**
             * *************PLANILLA IMPOSITIVA******************
             */
            else if (tipoEntidad.equals(EnumTipoEntidad.PLANILLA_IMPOSITIVA.getCodigo())) {
                return Response.status(200).entity(rhPlanillaImpositivaService.existePlanillaImpositivaPorPeriodo(idPeriodoGestion)).build();
            } else {
                return Response.status(200).entity(false).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }

    }

}
