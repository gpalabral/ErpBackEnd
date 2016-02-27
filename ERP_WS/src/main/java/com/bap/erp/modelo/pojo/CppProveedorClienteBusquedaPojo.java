package com.bap.erp.modelo.pojo;

import java.io.Serializable;

public class CppProveedorClienteBusquedaPojo implements Serializable {
    
    private Long idProveedorCliente;
    private String nombre;
    private String tipoRegistro;
    private String tipoProveedor;

    public Long getIdProveedorCliente() {
        return idProveedorCliente;
    }

    public void setIdProveedorCliente(Long idProveedorCliente) {
        this.idProveedorCliente = idProveedorCliente;
    }   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tipoRegistro
     */
    public String getTipoRegistro() {
        return tipoRegistro;
    }

    /**
     * @param tipoRegistro the tipoRegistro to set
     */
    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    /**
     * @return the tipoProveedor
     */
    public String getTipoProveedor() {
        return tipoProveedor;
    }

    /**
     * @param tipoProveedor the tipoProveedor to set
     */
    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }
    
}
