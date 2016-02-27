/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.servicios.cpc;

import com.bap.erp.modelo.cpc.CpcPagoContrato;
import com.bap.erp.modelo.pojo.CpcPagoContratoPojo;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Javier
 */
public interface CpcPagoContratoService {
    
    CpcPagoContrato persistCpcPagoContrato(CpcPagoContrato cpcPagoContrato) throws Exception;//CREATE
    
    CpcPagoContrato mergeCpcPagoContrato(CpcPagoContrato cpcPagoContrato) throws Exception;//UPDATE
 
    List<CpcPagoContrato> getCpcPagoContrato(); //RETRIEVE
      
    List<CpcPagoContratoPojo>  listaCpcPagoContratoByFechaProgramadaEstadoPago(String parEstadoPago) throws Exception;
    
    //List<CpcPagoContratoPojo> listaCpcPagoContratoPorIdContratoEstadoPago(String parEstadoPago) throws Exception;
    
    CpcPagoContrato getCpcPagoContratoById(Long idPagoContrato) throws Exception;
    
    List<CpcPagoContrato>  getCpcPagoContratoByIdContrato(Long idContrato) throws Exception;
    
    List<CpcPagoContrato> getCpcPagoContratoIdContratoByEstadoPago(Long idContrato, String parEstadoPago) throws Exception;

    CpcPagoContratoPojo listaCpcPagoContrato(Long idPagoContrato) throws Exception;
    
    Long generaNroPago(Long idContrato)throws Exception;
    
    Boolean verificaTiempoDisponible(CpcPagoContrato cpcPagoContrato)throws Exception;
    
    CpcPagoContrato cambiaCpcPagoContrato(CpcPagoContrato cpcPagoContrato) throws Exception;
    
    void actualizaPagosContratoEnMora()throws Exception;
    
    void removeCpcPagoContrato(Long idPagoContrato) throws Exception;
    
    List<CpcPagoContrato> getListaPagoContratoPendienteByDias(Long idContrato, Integer cantidadDias) throws Exception;
    
    List<CpcPagoContratoPojo> getPagoContratoPendientePojo(Long idContrato, Integer cantidadDias) throws Exception;
    
//    CpcPagoContrato getCpcPagoContratoCpcFarturaEmitida(Long idPagoContrato, Float montoSum) throws Exception;
    CpcPagoContrato getCpcPagoContratoCpcFarturaEmitida(Long idPagoContrato, BigDecimal montoSum) throws Exception;
    
    List<CpcPagoContrato> getListaPagoContratoPend(Long idContrato, Integer cantidadDias) throws Exception;
    
//    List<CpcPagoContrato> listaCpcPagoContratoPorIdContratoyEstado(Long idContrato, String parEstadoPago) throws Exception;
    
    Boolean verificaSiElPagoEsCompleto(Long idPagoContrato)throws Exception;
    
    List<CpcPagoContrato> getCobrosPorFacturarPorContrato(Long idContrato) throws Exception;
    
    void deleteCpcPagoContrato(CpcPagoContrato cpcPagoContrato)throws Exception;
}