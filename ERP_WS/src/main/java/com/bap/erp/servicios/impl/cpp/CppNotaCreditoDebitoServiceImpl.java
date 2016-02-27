/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.servicios.impl.cpp;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.enums.EnumTipoProveedorCliente;
import com.bap.erp.modelo.ErpDetalleFactura;
import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.modelo.ErpNotaCreditoDebito;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.pojo.CpcLibroDeComprasPojo;
import com.bap.erp.servicios.ErpDetalleFacturaService;
import com.bap.erp.servicios.ErpFacturaService;
import com.bap.erp.servicios.cpp.CppNotaCreditoDebitoService;
import com.bap.erp.servicios.cpp.CppProveedorClienteConceptoService;
import com.bap.erp.servicios.cpp.CppProveedorClienteService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Henrry Guzmán
 */
@Service
public class CppNotaCreditoDebitoServiceImpl implements CppNotaCreditoDebitoService {

    IGenericDao<ErpDetalleFactura> dao;
    IGenericDao<ErpNotaCreditoDebito> daoNotaCreditoDebito;

    @Autowired
    public CppProveedorClienteService cppProveedorClienteService;
    @Autowired
    public ErpDetalleFacturaService erpDetalleFacturaService;

    @Autowired
    public void setDao(IGenericDao<ErpDetalleFactura> daoToSet) {
        dao = daoToSet;
        dao.setClazz(ErpDetalleFactura.class);
    }

    @Autowired
    public void setDaoNotaCreditoDebito(IGenericDao<ErpNotaCreditoDebito> daoToSet) {
        daoNotaCreditoDebito = daoToSet;
        daoNotaCreditoDebito.setClazz(ErpNotaCreditoDebito.class);
    }

    public List<ErpDetalleFactura> listaLibroDeCompras(int month, int year) throws Exception {
        try {
            List<ErpDetalleFactura> listaDetalleNotaCreditoDebito = dao.find(""
                    + "select h "
                    + "from ErpDetalleFactura h "
                    + "where h.fechaBaja is null "
                    + "and h.erpNotaCreditoDebito.idNotaCreditoDebito in ("
                    + "select distinct r.idNotaCreditoDebito "
                    + "from ErpNotaCreditoDebito r "
                    + "where r.fechaBaja is null "
                    + "and MONTH(r.fechaNotaCreditoDebito) = " + month + " "
                    + "and YEAR(r.fechaNotaCreditoDebito) = " + year + " )");
            if (!listaDetalleNotaCreditoDebito.isEmpty()) {
                return listaDetalleNotaCreditoDebito;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcLibroDeComprasPojo> listaLibroDeComprasNotaCreditoDebito(int month, int year) throws Exception {
        List<CpcLibroDeComprasPojo> listaLibroDeComprasNotaCreditoDebito = new ArrayList<CpcLibroDeComprasPojo>();
        int numero = 1;
        for (ErpDetalleFactura detalleFactura : listaLibroDeCompras(month, year)) {
            CpcLibroDeComprasPojo cpcLibroDeComprasPojo = new CpcLibroDeComprasPojo();
            cpcLibroDeComprasPojo.setEspecificacion(0);//PREGUNTAR
            cpcLibroDeComprasPojo.setNumero(numero);//PREGUNTAR
            cpcLibroDeComprasPojo.setFechaNotaCreditoDebito(detalleFactura.getErpNotaCreditoDebito().getFechaNotaCreditoDebito());//Tabla Nota Crédito - Débito
            cpcLibroDeComprasPojo.setNumeroNotaCreditoDebito(detalleFactura.getErpNotaCreditoDebito().getNumeroNotaCreditoDebito());//Tabla Nota Crédito - Débito
            cpcLibroDeComprasPojo.setNumeroAutorizacionNotaCreditoDebito(detalleFactura.getErpNotaCreditoDebito().getCpcDosificacion().getNumeroAutorizacion());//Tabla Nota Crédito-Débito, esta tabla tiene el idDosificacion, entonces obtenemos Tabla Dosificacion
            cpcLibroDeComprasPojo.setParEstadoFacturaNotaCreditoDebito(detalleFactura.getErpNotaCreditoDebito().getParEstadoFactura());// Tabla Nota Crédito - Débito
            ErpDetalleFactura erpDetalleFactura = erpDetalleFacturaService.getCpcDetalleFacturaById(detalleFactura.getIdPadre());
            System.out.println("NUMERO AUTO:" + erpDetalleFactura.getErpFactura().getNumeroAutorizacion());
            String a = Long.toString(erpDetalleFactura.getErpFactura().getCppProveedorCliente().getNit());
            String b = erpDetalleFactura.getErpFactura().getCppProveedorCliente().getNumeroDocumento();
            String nitCi = a;// Puede tambien ser la variable b
            cpcLibroDeComprasPojo.setNitCi(nitCi);// Tabla Nota Crédito-Débito, obtenemos por parte del idProveedor

            cpcLibroDeComprasPojo.setNombreRazonSocial(cppProveedorClienteService.getNombreCliente(erpDetalleFactura.getErpFactura().getCppProveedorCliente()));// Tabla Nota Crédito-Débito, obtenemos por parte del idProveedor
            cpcLibroDeComprasPojo.setMontoTotalDevolucionPrimeraMonedaNotaCreditoDebito(detalleFactura.getErpNotaCreditoDebito().getImporteTotalPrimeraMoneda());//Tabla Nota Crédito - Débito
            cpcLibroDeComprasPojo.setMontoTotalDevolucionSegundaMonedaNotaCreditoDebito(detalleFactura.getErpNotaCreditoDebito().getImporteTotalSegundaMoneda());//Tabla Nota Crédito - Débito
            cpcLibroDeComprasPojo.setIvaNotaCreditoDebito(BigDecimal.ZERO);//Tabla Nota Crédito - Débito, Se debe de adicionar a la Tabla Nota Crédito - Débito
            cpcLibroDeComprasPojo.setCodigoControlNotaCreditoDebito(detalleFactura.getErpNotaCreditoDebito().getCodigoControlNotaDebitoCredito());//Tabla Nota Crédito - Débito

            cpcLibroDeComprasPojo.setFechaFacturaOriginal(erpDetalleFactura.getErpFactura().getFechaFactura());//Tabla Detalle Factura, obtenemos los datos de la factura por medio del idPadre(Esta es una Factura)
            cpcLibroDeComprasPojo.setNumeroFacturaOriginal(erpDetalleFactura.getErpFactura().getNumeroFactura());//Tabla Detalle Factura, obtenemos los datos de la factura por medio del idPadre(Esta es una Factura)
            cpcLibroDeComprasPojo.setNumeroAutorizacionFacturaOriginal(erpDetalleFactura.getErpFactura().getNumeroAutorizacion() != null ? erpDetalleFactura.getErpFactura().getNumeroAutorizacion() : "");//Tabla Detalle Factura, obtenemos los datos de la factura por medio del idPadre(Esta es una Factura)

            cpcLibroDeComprasPojo.setMontoTotalFacturaOriginalPrimeraMoneda(erpDetalleFactura.getErpFactura().getMontoPrimeraMoneda());//Tabla Detalle Factura, obtenemos los datos de la factura por medio del idPadre(Esta es una Factura)
            cpcLibroDeComprasPojo.setMontoTotalFacturaOriginalSegundaMoneda(erpDetalleFactura.getErpFactura().getMontoSegundaMoneda());//Tabla Detalle Factura, obtenemos los datos de la factura por medio del idPadre(Esta es una Factura)
            numero++;
            listaLibroDeComprasNotaCreditoDebito.add(cpcLibroDeComprasPojo);
        }
        if (!listaLibroDeComprasNotaCreditoDebito.isEmpty()) {
            return listaLibroDeComprasNotaCreditoDebito;
        }
        return Collections.EMPTY_LIST;

    }

    public List<ErpNotaCreditoDebito> consultaLibroDeComprasNotaCreditoDebito(int month, int year) throws Exception {
        try {
            List<ErpNotaCreditoDebito> listaNotaCreditoDebito = daoNotaCreditoDebito.find(""
                    + "select r from ErpNotaCreditoDebito r "
                    + "where r.fechaBaja is null and r.parEstadoFactura.codigo='V' "
                    + "and MONTH(r.fechaNotaCreditoDebito) = " + month + " "
                    + "and YEAR(r.fechaNotaCreditoDebito) = " + year + " "
                    + "order by r.fechaAlta ASC");
            if (!listaNotaCreditoDebito.isEmpty()) {
                return listaNotaCreditoDebito;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<CpcLibroDeComprasPojo> libroDeComprasNotaCreditoDebito(int month, int year) throws Exception {
        List<CpcLibroDeComprasPojo> listaLibroDeComprasNotaCreditoDebito = new ArrayList<CpcLibroDeComprasPojo>();
        int numero = 1;
        for (ErpNotaCreditoDebito erpNotaCreditoDebito : consultaLibroDeComprasNotaCreditoDebito(month, year)) {
            CpcLibroDeComprasPojo cpcLibroDeComprasPojo = new CpcLibroDeComprasPojo();
            cpcLibroDeComprasPojo.setEspecificacion(0);//PREGUNTAR
            cpcLibroDeComprasPojo.setNumero(numero);//PREGUNTAR
            cpcLibroDeComprasPojo.setFechaNotaCreditoDebito(erpNotaCreditoDebito.getFechaNotaCreditoDebito());//Tabla Nota Crédito - Débito
            cpcLibroDeComprasPojo.setNumeroNotaCreditoDebito(erpNotaCreditoDebito.getNumeroNotaCreditoDebito());//Tabla Nota Crédito - Débito
            cpcLibroDeComprasPojo.setNumeroAutorizacionNotaCreditoDebito(erpNotaCreditoDebito.getCpcDosificacion().getNumeroAutorizacion());//Tabla Nota Crédito-Débito, esta tabla tiene el idDosificacion, entonces obtenemos Tabla Dosificacion
            cpcLibroDeComprasPojo.setParEstadoFacturaNotaCreditoDebito(erpNotaCreditoDebito.getParEstadoFactura());// Tabla Nota Crédito - Débito
            String valorNit = Long.toString(erpNotaCreditoDebito.getErpFactura().getCppProveedorCliente().getNit());
            Long nit = erpNotaCreditoDebito.getErpFactura().getCppProveedorCliente().getNit();
            String nroDocumento = erpNotaCreditoDebito.getErpFactura().getCppProveedorCliente().getNumeroDocumento();

            String nitCi = nit != null ? nit != 0 ? Long.toString(nit) : nroDocumento != null ? !"".equals(nroDocumento) ? nroDocumento : nroDocumento : "" : nroDocumento != null ? !"".equals(nroDocumento) ? nroDocumento : nroDocumento : "";
            cpcLibroDeComprasPojo.setNitCi(nitCi);// Tabla Nota Crédito-Débito, obtenemos por parte del idProveedor
            cpcLibroDeComprasPojo.setNombreRazonSocial(cppProveedorClienteService.getNombreCliente(erpNotaCreditoDebito.getErpFactura().getCppProveedorCliente()));// Tabla Nota Crédito-Débito, obtenemos por parte del idProveedor
            cpcLibroDeComprasPojo.setMontoTotalDevolucionPrimeraMonedaNotaCreditoDebito(erpNotaCreditoDebito.getImporteTotalPrimeraMoneda());//Tabla Nota Crédito - Débito
            cpcLibroDeComprasPojo.setMontoTotalDevolucionSegundaMonedaNotaCreditoDebito(erpNotaCreditoDebito.getImporteTotalSegundaMoneda());//Tabla Nota Crédito - Débito
            
            cpcLibroDeComprasPojo.setIvaNotaCreditoDebito((erpNotaCreditoDebito.getImporteTotalPrimeraMoneda().multiply(new BigDecimal("0.13"))).setScale(2, BigDecimal.ROUND_HALF_UP));//Tabla Nota Crédito - Débito, Se debe de adicionar a la Tabla Nota Crédito - Débito
            cpcLibroDeComprasPojo.setCodigoControlNotaCreditoDebito(erpNotaCreditoDebito.getCodigoControlNotaDebitoCredito());//Tabla Nota Crédito - Débito

            cpcLibroDeComprasPojo.setFechaFacturaOriginal(erpNotaCreditoDebito.getErpFactura().getFechaFactura());//Tabla Detalle Factura, obtenemos los datos de la factura por medio del idPadre(Esta es una Factura)
            cpcLibroDeComprasPojo.setNumeroFacturaOriginal(erpNotaCreditoDebito.getErpFactura().getNumeroFactura());//Tabla Detalle Factura, obtenemos los datos de la factura por medio del idPadre(Esta es una Factura)
            cpcLibroDeComprasPojo.setNumeroAutorizacionFacturaOriginal(erpNotaCreditoDebito.getErpFactura().getNumeroAutorizacion() != null ? erpNotaCreditoDebito.getErpFactura().getNumeroAutorizacion() : "");//Tabla Detalle Factura, obtenemos los datos de la factura por medio del idPadre(Esta es una Factura)

            cpcLibroDeComprasPojo.setMontoTotalFacturaOriginalPrimeraMoneda(erpNotaCreditoDebito.getErpFactura().getMontoPrimeraMoneda());//Tabla Detalle Factura, obtenemos los datos de la factura por medio del idPadre(Esta es una Factura)
            cpcLibroDeComprasPojo.setMontoTotalFacturaOriginalSegundaMoneda(erpNotaCreditoDebito.getErpFactura().getMontoSegundaMoneda());//Tabla Detalle Factura, obtenemos los datos de la factura por medio del idPadre(Esta es una Factura)
            numero++;
            listaLibroDeComprasNotaCreditoDebito.add(cpcLibroDeComprasPojo);

        }
        if (!listaLibroDeComprasNotaCreditoDebito.isEmpty()) {
            return listaLibroDeComprasNotaCreditoDebito;
        }
        return Collections.EMPTY_LIST;

    }

}
