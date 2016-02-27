package com.bap.erp.modelo.pojo.rh;

import java.io.Serializable;
import java.util.List;

public class RhEmpleadoCriterioDeIngresoPojo implements Serializable{
    
    private Long idEmpleadoCargo;
    private String codigo;
    private Long numeroItem;
    private String nombreCompleto;
    private String departamento;
    private String cargo;
    private String carnetIdentidad;
    private List<RhCriterioDeIngresoPojo> listaCriterioDeIngresoPojo;

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

    public Long getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(Long numeroItem) {
        this.numeroItem = numeroItem;
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

    public List<RhCriterioDeIngresoPojo> getListaCriterioDeIngresoPojo() {
        return listaCriterioDeIngresoPojo;
    }

    public void setListaCriterioDeIngresoPojo(List<RhCriterioDeIngresoPojo> listaCriterioDeIngresoPojo) {
        this.listaCriterioDeIngresoPojo = listaCriterioDeIngresoPojo;
    }
       
}
