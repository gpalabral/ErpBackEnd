package com.bap.erp.servicios;

import com.bap.erp.modelo.ErpNotaCreditoDebito;
import com.bap.erp.modelo.pojo.ErpNotaCreditoDebitoCpcDetalleFacturaPojo;
import java.math.BigDecimal;
import java.util.List;

public interface ErpNotaCreditoDebitoService {

    ErpNotaCreditoDebito persistErpNotaCreditoDebito(ErpNotaCreditoDebito erpNotaCreditoDebito) throws Exception;
    
    ErpNotaCreditoDebito persistErpNotaCreditoDebitoCpcDetalleFacturaPojo(ErpNotaCreditoDebitoCpcDetalleFacturaPojo erpNotaCreditoDebitoCpcDetalleFacturaPojo) throws Exception; 
    
    ErpNotaCreditoDebitoCpcDetalleFacturaPojo getErpNotaCreditoDebito(Long idNotaCreditoDebito) throws Exception;
    
    Long generaNumeroNotaCreditoDebito(Long idDosificacion) throws Exception;
    
    List<ErpNotaCreditoDebito> getAllErpNotaCreditoDebitoByIdFatura(Long idFactura)throws Exception;
    
    BigDecimal montoTotalCreditoDebitoPorFactura(Long idFactura, String tipoMoneda)throws Exception;
}
