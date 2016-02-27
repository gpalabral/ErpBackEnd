package com.bap.erp.modelo.pojo;

import com.bap.erp.modelo.cpc.CpcPagoContrato;

public class CpcPagoContratoPojo {
    private CpcPagoContrato cpcPagoContrato;
    private String nombre;

    public CpcPagoContrato getCpcPagoContrato() {
        return cpcPagoContrato;
    }

    public void setCpcPagoContrato(CpcPagoContrato cpcPagoContrato) {
        this.cpcPagoContrato = cpcPagoContrato;
    }
        
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
