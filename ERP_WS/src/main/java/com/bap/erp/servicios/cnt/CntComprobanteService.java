package com.bap.erp.servicios.cnt;

import com.bap.erp.commons.entities.mappings.Comprobante;
import com.bap.erp.modelo.ErpFactura;

public interface CntComprobanteService {

    Comprobante cargaComprobante(ErpFactura cpcFacturaEmitida)throws Exception;
    
}
