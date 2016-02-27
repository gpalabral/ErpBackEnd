package com.bap.erp.modelo.pojo;

import java.util.ArrayList;
import java.util.List;

public class ProveedorGrupoConcepto {
    
    private EntidadPojo proveedorCliente = new EntidadPojo();
    private EntidadPojo grupo = new EntidadPojo();
    private EntidadPojo concepto = new EntidadPojo();

    public EntidadPojo getProveedorCliente() {
        return proveedorCliente;
    }

    public void setProveedorCliente(EntidadPojo proveedorCliente) {
        this.proveedorCliente = proveedorCliente;
    }

    public EntidadPojo getGrupo() {
        return grupo;
    }

    public void setGrupo(EntidadPojo grupo) {
        this.grupo = grupo;
    }

    public EntidadPojo getConcepto() {
        return concepto;
    }

    public void setConcepto(EntidadPojo concepto) {
        this.concepto = concepto;
    }
    
    
}
