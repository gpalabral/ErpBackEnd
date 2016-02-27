package com.bap.erp.modelo.pojo;

import com.bap.erp.modelo.ErpDetalleFactura;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ErpDetFacturaNotaCredito implements Serializable{
    
    private Long idDetalleFactura;
    private String detalleFactura;
    private int cantidad;
    private BigDecimal precioUnitarioPrimeraMoneda;
    private BigDecimal subtotalPrimeraMoneda;
    private List<ErpDetalleFactura> listaCpcDetalleFactura;

    /**
     * @return the idDetalleFactura
     */
    public Long getIdDetalleFactura() {
        return idDetalleFactura;
    }

    /**
     * @param idDetalleFactura the idDetalleFactura to set
     */
    public void setIdDetalleFactura(Long idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    /**
     * @return the detalleFactura
     */
    public String getDetalleFactura() {
        return detalleFactura;
    }

    /**
     * @param detalleFactura the detalleFactura to set
     */
    public void setDetalleFactura(String detalleFactura) {
        this.detalleFactura = detalleFactura;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the precioUnitarioPrimeraMoneda
     */
    public BigDecimal getPrecioUnitarioPrimeraMoneda() {
        return precioUnitarioPrimeraMoneda;
    }

    /**
     * @param precioUnitarioPrimeraMoneda the precioUnitarioPrimeraMoneda to set
     */
    public void setPrecioUnitarioPrimeraMoneda(BigDecimal precioUnitarioPrimeraMoneda) {
        this.precioUnitarioPrimeraMoneda = precioUnitarioPrimeraMoneda;
    }

    /**
     * @return the subtotalPrimeraMoneda
     */
    public BigDecimal getSubtotalPrimeraMoneda() {
        return subtotalPrimeraMoneda;
    }

    /**
     * @param subtotalPrimeraMoneda the subtotalPrimeraMoneda to set
     */
    public void setSubtotalPrimeraMoneda(BigDecimal subtotalPrimeraMoneda) {
        this.subtotalPrimeraMoneda = subtotalPrimeraMoneda;
    }

    /**
     * @return the listaCpcDetalleFactura
     */
    public List<ErpDetalleFactura> getListaCpcDetalleFactura() {
        return listaCpcDetalleFactura;
    }

    /**
     * @param listaCpcDetalleFactura the listaCpcDetalleFactura to set
     */
    public void setListaCpcDetalleFactura(List<ErpDetalleFactura> listaCpcDetalleFactura) {
        this.listaCpcDetalleFactura = listaCpcDetalleFactura;
    }
    
}
