package com.bap.erp.modelo.pojo;

import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.modelo.ErpDetalleFactura;
import java.util.List;

public class CpcFacturaEmitidaCpcDetalleFacturaPojo {
    private ErpFactura cpcFacturaEmitida;
    private List<ErpDetalleFactura> listaCpcDetalleFactura;
//    private Long idPagoContrato;

    public ErpFactura getCpcFacturaEmitida() {
        return cpcFacturaEmitida;
    }

    public void setCpcFacturaEmitida(ErpFactura cpcFacturaEmitida) {
        this.cpcFacturaEmitida = cpcFacturaEmitida;
    }   

    public List<ErpDetalleFactura> getListaCpcDetalleFactura() {
        return listaCpcDetalleFactura;
    }

    public void setListaCpcDetalleFactura(List<ErpDetalleFactura> listaCpcDetalleFactura) {
        this.listaCpcDetalleFactura = listaCpcDetalleFactura;
    }

//    public Long getIdPagoContrato() {
//        return idPagoContrato;
//    }
//
//    public void setIdPagoContrato(Long idPagoContrato) {
//        this.idPagoContrato = idPagoContrato;
//    }
            
}
