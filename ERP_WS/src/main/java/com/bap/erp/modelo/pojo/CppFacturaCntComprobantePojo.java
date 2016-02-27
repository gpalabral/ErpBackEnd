package com.bap.erp.modelo.pojo;

import com.bap.erp.commons.entities.mappings.Comprobante;
import com.bap.erp.modelo.cpp.CppFactura;

public class CppFacturaCntComprobantePojo {
    private String idProveedorCliente = "";
    private CppFactura cppFactura;
    private Comprobante comprobante;

    public CppFactura getCppFactura() {
        return cppFactura;
    }

    public void setCppFactura(CppFactura cppFactura) {
        this.cppFactura = cppFactura;
    }          

    public String getIdProveedorCliente() {
        return idProveedorCliente;
    }

    public void setIdProveedorCliente(String idProveedorCliente) {
        this.idProveedorCliente = idProveedorCliente;
    }

    /**
     * @return the comprobante
     */
    public Comprobante getComprobante() {
        return comprobante;
    }

    /**
     * @param comprobante the comprobante to set
     */
    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

}
