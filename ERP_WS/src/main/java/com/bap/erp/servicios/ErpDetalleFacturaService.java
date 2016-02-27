package com.bap.erp.servicios;

import com.bap.erp.modelo.ErpDetalleFactura;
import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.modelo.ErpNotaCreditoDebito;
import java.math.BigDecimal;
import java.util.List;

public interface ErpDetalleFacturaService {
        
    ErpDetalleFactura persistCpcDetalleFactura(ErpDetalleFactura cpcDetalleFactura) throws Exception;
    
    List<ErpDetalleFactura> getCpcDetalleFactura ()throws Exception;
    
    List<ErpDetalleFactura> getCpcDetalleFacturaByFacturaEmitida (ErpFactura cpcFacturaEmitida)throws Exception;
    
    List<ErpDetalleFactura> getCpcDetalleFacturaByNotaCreditoDebito (ErpNotaCreditoDebito erpNotaCreditoDebito)throws Exception;
    
    ErpDetalleFactura mergeCpcDetalleFactura(ErpDetalleFactura cpcDetalleFactura)throws Exception;
    
    ErpDetalleFactura getCpcDetalleFacturaById(Long idDetalleFactura) throws Exception;        
        
}