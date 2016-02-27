package com.bap.erp.servicios.cpp;

import com.bap.erp.modelo.cpp.CppFormaPagoCobro;
import java.util.List;

public interface CppFormaPagoCobroService {
    
    CppFormaPagoCobro persistCppFormaPagoCobro(CppFormaPagoCobro cppFormaPagoCobro) throws Exception;//CREATE

    CppFormaPagoCobro mergeCppFormaPagoCobro(CppFormaPagoCobro cppFormaPagoCobro)throws Exception;//UPDATE
    
    void removeCppFormaPagoCobro(String idFormaPagoCobro);//DELETE
    
    List<CppFormaPagoCobro> getCppFormaPagoCobro()throws Exception; //RETRIEVE
    
    CppFormaPagoCobro getCppFormaPagoCobroByIdProveedorCliente(Long idProveedorCliente)throws Exception;
    
}
