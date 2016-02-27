package com.bap.erp.modelo.pojo;

import com.bap.erp.modelo.ban.CuentaBancaria;
import com.bap.erp.modelo.cpp.CppFormaPagoCobro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CppFormaPagoCobroCuentasBancariasPojo implements Serializable{        
    private CppFormaPagoCobro cppFormaPagoCobro = new CppFormaPagoCobro();
    private List<CuentaBancaria> listaCuentasBancarias = new ArrayList<CuentaBancaria>();

    public CppFormaPagoCobro getCppFormaPagoCobro() {
        return cppFormaPagoCobro;
    }

    public void setCppFormaPagoCobro(CppFormaPagoCobro cppFormaPagoCobro) {
        this.cppFormaPagoCobro = cppFormaPagoCobro;
    }

    public List<CuentaBancaria> getListaCuentasBancarias() {
        return listaCuentasBancarias;
    }

    public void setListaCuentasBancarias(List<CuentaBancaria> listaCuentasBancarias) {
        this.listaCuentasBancarias = listaCuentasBancarias;
    }        
    
}
