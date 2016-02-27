package com.bap.erp.modelo.pojo.rh;

import java.io.Serializable;
import java.util.List;

public class RhGeneraGridPojo implements Serializable{
    
    private List<String> titulos;
    private List<RhEmpleadoDescuentoPojo> listaEmpleadosDescuento;

    public List<String> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<String> titulos) {
        this.titulos = titulos;
    }

    public List<RhEmpleadoDescuentoPojo> getListaEmpleadosDescuento() {
        return listaEmpleadosDescuento;
    }

    public void setListaEmpleadosDescuento(List<RhEmpleadoDescuentoPojo> listaEmpleadosDescuento) {
        this.listaEmpleadosDescuento = listaEmpleadosDescuento;
    }
        
}
