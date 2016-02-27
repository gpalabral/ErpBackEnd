package com.bap.erp.modelo.pojo.rh;

import java.io.Serializable;
import java.util.List;

public class RhEmpleadoDescuentoPojo implements Serializable{
    
    private Long idEmpleadoCargo;
    private String codigo;
    private Long numeroItem;
    private String nombreCompleto;
    private String departamento;
    private String cargo;
    private String carnetIdentidad;
    private List<RhDescuentoPojo> listaDescuentos;

    public Long getIdEmpleadoCargo() {
        return idEmpleadoCargo;
    }

    public void setIdEmpleadoCargo(Long idEmpleadoCargo) {
        this.idEmpleadoCargo = idEmpleadoCargo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCarnetIdentidad() {
        return carnetIdentidad;
    }

    public void setCarnetIdentidad(String carnetIdentidad) {
        this.carnetIdentidad = carnetIdentidad;
    }

    public List<RhDescuentoPojo> getListaDescuentos() {
        return listaDescuentos;
    }

    public void setListaDescuentos(List<RhDescuentoPojo> listaDescuentos) {
        this.listaDescuentos = listaDescuentos;
    }

    public Long getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(Long numeroItem) {
        this.numeroItem = numeroItem;
    }

    
}
