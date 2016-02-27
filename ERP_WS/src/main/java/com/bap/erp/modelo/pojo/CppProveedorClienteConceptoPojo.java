package com.bap.erp.modelo.pojo;

import com.bap.erp.modelo.cpp.CppConcepto;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import java.util.ArrayList;
import java.util.List;

public class CppProveedorClienteConceptoPojo {
    private CppProveedorCliente cppProveedorCliente;
    private List<CppConcepto> listaCppConcepto = new ArrayList<CppConcepto>();

    public CppProveedorCliente getCppProveedorCliente() {
        return cppProveedorCliente;
    }

    public void setCppProveedorCliente(CppProveedorCliente cppProveedorCliente) {
        this.cppProveedorCliente = cppProveedorCliente;
    }

    public List<CppConcepto> getListaCppConcepto() {
        return listaCppConcepto;
    }

    public void setListaCppConcepto(List<CppConcepto> listaCppConcepto) {
        this.listaCppConcepto = listaCppConcepto;
    }

    
     
    
}
