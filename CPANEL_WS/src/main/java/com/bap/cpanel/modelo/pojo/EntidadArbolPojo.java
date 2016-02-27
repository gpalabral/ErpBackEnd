package com.bap.cpanel.modelo.pojo;

import java.util.ArrayList;
import java.util.List;

public class EntidadArbolPojo {
    
    private Long idEntidadArbolPojo;
    private String descripcion;
    private String tipo;    
    private String mascara;   
    
    private List<EntidadPojo> children = new ArrayList<EntidadPojo>();       

    public Long getIdEntidadArbolPojo() {
        return idEntidadArbolPojo;
    }

    public void setIdEntidadArbolPojo(Long idEntidadArbolPojo) {
        this.idEntidadArbolPojo = idEntidadArbolPojo;
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

    public List<EntidadPojo> getChildren() {
        return children;
    }

    public void setChildren(List<EntidadPojo> children) {
        this.children = children;
    }

    
    
}
