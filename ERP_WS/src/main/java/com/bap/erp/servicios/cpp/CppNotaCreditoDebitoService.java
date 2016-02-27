/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.servicios.cpp;

import com.bap.erp.modelo.ErpDetalleFactura;
import com.bap.erp.modelo.ErpNotaCreditoDebito;
import com.bap.erp.modelo.pojo.CpcLibroDeComprasPojo;
import java.util.List;

/**
 *
 * @author Henrry Guzmán
 */
public interface CppNotaCreditoDebitoService {

    List<ErpDetalleFactura> listaLibroDeCompras(int month, int year) throws Exception;

    List<CpcLibroDeComprasPojo> listaLibroDeComprasNotaCreditoDebito(int month, int year) throws Exception;

    List<ErpNotaCreditoDebito> consultaLibroDeComprasNotaCreditoDebito(int month, int year) throws Exception;

    List<CpcLibroDeComprasPojo> libroDeComprasNotaCreditoDebito(int month, int year) throws Exception;

}
