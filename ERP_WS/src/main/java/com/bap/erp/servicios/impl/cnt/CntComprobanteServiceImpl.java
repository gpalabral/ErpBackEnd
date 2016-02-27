package com.bap.erp.servicios.impl.cnt;

import com.bap.erp.commons.entities.mappings.Comprobante;
import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.servicios.cnt.CntComprobanteService;
import org.springframework.stereotype.Service;

@Service
public class CntComprobanteServiceImpl implements CntComprobanteService {

    public Comprobante cargaComprobante(ErpFactura cpcFacturaEmitida) throws Exception {
        try {
            Comprobante comprobante = new Comprobante();
//            comprobante.setIdProveedorCliente(cpcFacturaEmitida.getCppProveedorCliente().getIdProveedorCliente());
//            comprobante.setIdDosificacion(cpcFacturaEmitida.getCpcDosificacion().getIdDosificacion());
//            comprobante.setMonto(cpcFacturaEmitida.getMontoPrimeraMoneda());
//            comprobante.setMontoSegMoneda(cpcFacturaEmitida.getMontoSegundaMoneda());
//            comprobante.setFechaFactura(cpcFacturaEmitida.getFechaFactura());
//            comprobante.setNumeroDeFactura(cpcFacturaEmitida.getNumeroFactura().toString());
//            comprobante.setCodigoControl(cpcFacturaEmitida.getCodigoControl());
////            comprobante.setRazonSocial(cpcFacturaEmitida);
//            
//            comprobante.setCodigoControl(cpcFacturaEmitida.getCodigoControl());
////            comprobante.set
            
            return comprobante;
        } catch (Exception e) {
            throw e;
        }
    }
    
}
