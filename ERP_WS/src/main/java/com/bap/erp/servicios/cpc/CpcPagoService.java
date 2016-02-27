package com.bap.erp.servicios.cpc;

import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.modelo.cpc.CpcPago;
import java.math.BigDecimal;
import java.util.List;

public interface CpcPagoService {
        
    List<CpcPago> getCpcPago()throws Exception;
    
    CpcPago getCpcPagoByIdPago(Long idPago) throws Exception;
    
    public CpcPago persistCpcPago(CpcPago cpcPago) throws Exception;
    
//    public Float saldoPagosPorFactura(ErpFactura cpcFaturaEmitida,String montoPagado)throws Exception;
    public BigDecimal saldoPagosPorFactura(ErpFactura cpcFaturaEmitida,String montoPagado)throws Exception;
    
    List<CpcPago> listaPagosOrdenados(int month, int year, String modulo)throws Exception;
    
    BigDecimal montoPagadoAcumulado(Long idFactura, String tipoMoneda)throws Exception;
    
//    Facturacion por Compras
    BigDecimal montoPagadoAcumuladoFacturaRetencion(Long idFacturaRetencion, String tipoFacturaRetencion)throws Exception;
}