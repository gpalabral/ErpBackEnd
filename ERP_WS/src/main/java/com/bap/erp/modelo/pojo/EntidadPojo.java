package com.bap.erp.modelo.pojo;

public class EntidadPojo {

    private Long idEntidadPojo;
    private String descripcion;
    private String tipo;    
    private String mascara;

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
