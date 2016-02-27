package com.bap.erp.modelo.pojo;

import com.bap.erp.modelo.cpp.CppContacto;
import com.bap.erp.modelo.cpp.CppFormaPagoCobro;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import java.util.ArrayList;
import java.util.List;

public class CppProveedorClientePojo {
    private CppProveedorCliente cppProveedorCliente;
    private List<CppContacto> listaCppContacto;
    private CppFormaPagoCobro cppFormaPagoCobro;
    private List<EntidadArbolPojo> listaCppGruposConceptos = new ArrayList<EntidadArbolPojo>();

    public CppProveedorCliente getCppProveedorCliente() {
        return cppProveedorCliente;
    }

    public void setCppProveedorCliente(CppProveedorCliente cppProveedorCliente) {
        this.cppProveedorCliente = cppProveedorCliente;
    }

    public List<CppContacto> getListaCppContacto() {
        return listaCppContacto;
    }

    public void setListaCppContacto(List<CppContacto> listaCppContacto) {
        this.listaCppContacto = listaCppContacto;
    }

    public CppFormaPagoCobro getCppFormaPagoCobro() {
        return cppFormaPagoCobro;
    }

    public void setCppFormaPagoCobro(CppFormaPagoCobro cppFormaPagoCobro) {
        this.cppFormaPagoCobro = cppFormaPagoCobro;
    }    

    public List<EntidadArbolPojo> getListaCppGruposConceptos() {
        return listaCppGruposConceptos;
    }

    public void setListaCppGruposConceptos(List<EntidadArbolPojo> listaCppGruposConceptos) {
        this.listaCppGruposConceptos = listaCppGruposConceptos;
    }
    
}
