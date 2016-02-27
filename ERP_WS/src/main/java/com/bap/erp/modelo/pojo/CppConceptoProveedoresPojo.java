    package com.bap.erp.modelo.pojo;

import com.bap.erp.modelo.cpp.CppConcepto;
import java.util.ArrayList;
import java.util.List;

public class CppConceptoProveedoresPojo {
    private CppConcepto cppConcepto = new CppConcepto();
    private List<EntidadArbolPojo> listaProveedoresClientes = new ArrayList<EntidadArbolPojo>();
   
    public List<EntidadArbolPojo> getListaProveedoresClientes() {
        return listaProveedoresClientes;
    }

    public void setListaProveedoresClientes(List<EntidadArbolPojo> listaProveedoresClientes) {
        this.listaProveedoresClientes = listaProveedoresClientes;
    }

    public CppConcepto getCppConcepto() {
        return cppConcepto;
    }

    public void setCppConcepto(CppConcepto cppConcepto) {
        this.cppConcepto = cppConcepto;
    }        
    
}
