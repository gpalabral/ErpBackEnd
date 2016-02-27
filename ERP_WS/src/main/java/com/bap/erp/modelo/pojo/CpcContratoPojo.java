/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.modelo.pojo;

import com.bap.erp.modelo.cpc.CpcActividadEconomica;
import com.bap.erp.modelo.cpc.CpcContrato;
import com.bap.erp.modelo.cpc.CpcContratoItem;
import com.bap.erp.modelo.cpc.CpcPagoContrato;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author henrry
 */
public class CpcContratoPojo {

    private CpcContrato cpcContrato;
    private List<CpcContratoItem> listaCpcContratoItem = new ArrayList<CpcContratoItem>();
    private List<CpcPagoContrato> listaCpcPagoContrato = new ArrayList<CpcPagoContrato>();
    private List<CpcActividadEconomica> listaCpcActividadEconomica = new ArrayList<CpcActividadEconomica>();

    public CpcContrato getCpcContrato() {
        return cpcContrato;
    }

    public void setCpcContrato(CpcContrato cpcContrato) {
        this.cpcContrato = cpcContrato;
    }

    public List<CpcContratoItem> getListaCpcContratoItem() {
        return listaCpcContratoItem;
    }

    public void setListaCpcContratoItem(List<CpcContratoItem> listaCpcContratoItem) {
        this.listaCpcContratoItem = listaCpcContratoItem;
    }

    public List<CpcPagoContrato> getListaCpcPagoContrato() {
        return listaCpcPagoContrato;
    }

    public void setListaCpcPagoContrato(List<CpcPagoContrato> listaCpcPagoContrato) {
        this.listaCpcPagoContrato = listaCpcPagoContrato;
    }

    public List<CpcActividadEconomica> getListaCpcActividadEconomica() {
        return listaCpcActividadEconomica;
    }

    public void setListaCpcActividadEconomica(List<CpcActividadEconomica> listaCpcActividadEconomica) {
        this.listaCpcActividadEconomica = listaCpcActividadEconomica;
    }        

}
