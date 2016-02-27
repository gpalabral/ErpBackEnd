package com.bap.erp.commons.entities.mappings;

import java.io.Serializable;
import java.util.List;

public class CalculosTributarios implements Serializable{
        
    private Long idTipoDeCambio;    
    private Float excento;
    private Float ice;
    private Float iva;
    private Float descuento;
    private Float excentoSegMoneda;    
    private Float iceSegMoneda;    
    private Float ivaSegMoneda;
    private Float descuentoSegMoneda;
    private List<CuentaMonto> listaCuentas;

    public Long getIdTipoDeCambio() {
        return idTipoDeCambio;
    }

    public void setIdTipoDeCambio(Long idTipoDeCambio) {
        this.idTipoDeCambio = idTipoDeCambio;
    }     

    public Float getExcento() {
        return excento;
    }

    public void setExcento(Float excento) {
        this.excento = excento;
    }

    public Float getIce() {
        return ice;
    }

    public void setIce(Float ice) {
        this.ice = ice;
    }

    public Float getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    public Float getExcentoSegMoneda() {
        return excentoSegMoneda;
    }

    public void setExcentoSegMoneda(Float excentoSegMoneda) {
        this.excentoSegMoneda = excentoSegMoneda;
    }

    public Float getIceSegMoneda() {
        return iceSegMoneda;
    }

    public void setIceSegMoneda(Float iceSegMoneda) {
        this.iceSegMoneda = iceSegMoneda;
    }

    public Float getIvaSegMoneda() {
        return ivaSegMoneda;
    }

    public void setIvaSegMoneda(Float ivaSegMoneda) {
        this.ivaSegMoneda = ivaSegMoneda;
    }

    public Float getDescuentoSegMoneda() {
        return descuentoSegMoneda;
    }

    public void setDescuentoSegMoneda(Float descuentoSegMoneda) {
        this.descuentoSegMoneda = descuentoSegMoneda;
    }

    /**
     * @return the listaCuentas
     */
    public List<CuentaMonto> getListaCuentas() {
        return listaCuentas;
    }

    /**
     * @param listaCuentas the listaCuentas to set
     */
    public void setListaCuentas(List<CuentaMonto> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }
    
}
