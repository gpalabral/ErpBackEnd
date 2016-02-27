/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.ws;

import com.bap.erp.modelo.ban.CuentaBancaria;
import com.bap.erp.modelo.pojo.CuentaBancariaPojo;
import com.bap.erp.servicios.ban.CuentaBancariaService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
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

/**
 *
 * @author Jonathan
 */
@Component
@Path("/cuentaBancaria")
@Api(value = "cuentaBancaria", description = "WS for CuentaBancaria")
public class CuentaBancariaWS {

    @Autowired
    CuentaBancariaService cuentaBancariaService;

    public CuentaBancariaWS() {

    }

    @GET
    @Path("/getCuentaBancariaById/{idCuentaBancaria}")
    @ApiOperation(value = "get cuentaBancaria by idCuentaBancaria")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCuentaBancariaById(@PathParam("idCuentaBancaria") Long idCuentaBancaria) {
        try {
            CuentaBancaria cuentaBancaria = cuentaBancariaService.getCuentaBancariaById(idCuentaBancaria);
            return Response.status(200).entity(cuentaBancaria).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @PUT
    @Path("/putCuentaBancaria")
    @ApiOperation(value = "operation to INSERT a cuentaBancaria")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCuentaBancaria(@ApiParam(value = "cuentaBancaria", required = true) CuentaBancaria cuentaBancaria) {
        try {
            cuentaBancaria = cuentaBancariaService.persistCuentaBancaria(cuentaBancaria);
            return Response.status(200).entity(cuentaBancaria).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCuentaBancariaByIdCliente/{idProveedorCliente}")
    @ApiOperation(value = "get cuentaBancaria from Empresa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCuentaBancariaByIdCliente(@PathParam(value = "idProveedorCliente") Long idProveedorCliente) {
        try {
            List<CuentaBancaria> listaCuentaBancaria = cuentaBancariaService.getCuentaBancariaByIdProveedorCliente(idProveedorCliente);
            return Response.status(200).entity(listaCuentaBancaria).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("/getCuentaBancariaPojoByIdProveedorCliente/{idProveedorCliente}")
    @ApiOperation(value = "get cuentaBancaria from ProveedorCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCuentaBancariaPojoByIdProveedorCliente(@PathParam(value = "idProveedorCliente") Long idProveedorCliente) {
        try {
            List<CuentaBancariaPojo> listaCuentasProveedorCliente = cuentaBancariaService.getCuentaBancariaPojoByIdProveedorCliente(idProveedorCliente);
            return Response.status(200).entity(listaCuentasProveedorCliente).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("/getCuentaBancariaPojoByEmpresa/{propietarioCuenta}")
    @ApiOperation(value = "get cuentaBancaria from Empresa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCuentaBancariaPojoByEmpresa(@PathParam("propietarioCuenta") String propietarioCuenta) {        
        try {
            List<CuentaBancariaPojo> listaCuentasEmpresa = cuentaBancariaService.getCuentaBancariaPojoByEmpresa(propietarioCuenta);
            return Response.status(200).entity(listaCuentasEmpresa).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }

//    @GET
//    @Path("/getlistaCpcPagoContratoByFechaProgramada/{parEstadoPago}")
//    @ApiOperation(value = "get CpcPagoContrato By FechaProgramada")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getCpcPagoContratoByFechaProgramadaEstadoPago(@PathParam("parEstadoPago") String parEstadoPago) {
//        try {
//            List<CpcPagoContratoPojo> listaCpcPagoContrato = cpcPagoContratoService.listaCpcPagoContratoByFechaProgramadaEstadoPago(parEstadoPago);
//            return Response.status(200).entity(listaCpcPagoContrato).build();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(500).entity(e).build();
//        }
//    }

}
