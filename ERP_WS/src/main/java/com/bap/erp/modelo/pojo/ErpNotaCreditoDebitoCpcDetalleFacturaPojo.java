package com.bap.erp.modelo.pojo;

import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.modelo.ErpDetalleFactura;
import com.bap.erp.modelo.ErpNotaCreditoDebito;
import java.util.List;

public class ErpNotaCreditoDebitoCpcDetalleFacturaPojo {
    private ErpNotaCreditoDebito erpNotaCreditoDebito;
    private List<ErpDetalleFactura> listaCpcDetalleFactura;

    public ErpNotaCreditoDebito getErpNotaCreditoDebito() {
        return erpNotaCreditoDebito;
    }

    public void setErpNotaCreditoDebito(ErpNotaCreditoDebito erpNotaCreditoDebito) {
        this.erpNotaCreditoDebito = erpNotaCreditoDebito;
    }
    
    public List<ErpDetalleFactura> getListaCpcDetalleFactura() {
        return listaCpcDetalleFactura;
    }

    public void setListaCpcDetalleFactura(List<ErpDetalleFactura> listaCpcDetalleFactura) {
        this.listaCpcDetalleFactura = listaCpcDetalleFactura;
    }
            
}
