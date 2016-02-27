package com.bap.erp.commons.entities.mappings;

import java.util.ArrayList;
import java.util.List;

public class EntidadArbolPojo {
    
    private Long idEntidadPojo;
    private String descripcion;
    private String tipo;    
    private String mascara;   
    
    private List<EntidadPojo> children = new ArrayList<EntidadPojo>();       

    public List<EntidadPojo> getChildren() {
        return children;
    }

    public void setChildren(List<EntidadPojo> children) {
        this.children = children;
    }

    public Long getIdEntidadPojo() {
        return idEntidadPojo;
    }

    public void setIdEntidadPojo(Long idEntidadPojo) {
        this.idEntidadPojo = idEntidadPojo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }
        
    
}
