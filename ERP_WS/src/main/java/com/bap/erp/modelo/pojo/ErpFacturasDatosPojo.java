package com.bap.erp.modelo.pojo;

import java.util.ArrayList;
import java.util.List;

public class ErpFacturasDatosPojo {

    private Long idProveedorCliente;
    private String codigoModalidadTransaccion;
    private String codigoTipoTransaccion;
    private String codigoTipoDocumentoMercantil;
    private List<ErpFacturasBancariasPojo> listaFacturasBancarias=new ArrayList<ErpFacturasBancariasPojo>();

    public Long getIdProveedorCliente() {
        return idProveedorCliente;
    }

    public void setIdProveedorCliente(Long idProveedorCliente) {
        this.idProveedorCliente = idProveedorCliente;
    }

    public String getCodigoModalidadTransaccion() {
        return codigoModalidadTransaccion;
    }

    public void setCodigoModalidadTransaccion(String codigoModalidadTransaccion) {
        this.codigoModalidadTransaccion = codigoModalidadTransaccion;
    }

    public String getCodigoTipoTransaccion() {
        return codigoTipoTransaccion;
    }

    public void setCodigoTipoTransaccion(String codigoTipoTransaccion) {
        this.codigoTipoTransaccion = codigoTipoTransaccion;
    }

    public String getCodigoTipoDocumentoMercantil() {
        return codigoTipoDocumentoMercantil;
    }

    public void setCodigoTipoDocumentoMercantil(String codigoTipoDocumentoMercantil) {
        this.codigoTipoDocumentoMercantil = codigoTipoDocumentoMercantil;
    }

    public List<ErpFacturasBancariasPojo> getListaFacturasBancarias() {
        return listaFacturasBancarias;
    }

    public void setListaFacturasBancarias(List<ErpFacturasBancariasPojo> listaFacturasBancarias) {
        this.listaFacturasBancarias = listaFacturasBancarias;
    }

    
    

}
