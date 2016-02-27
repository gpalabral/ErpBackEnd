/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.servicios.cpp;

import com.bap.erp.modelo.cpp.CppFactura;
import com.bap.erp.modelo.pojo.CppFacturaCntComprobantePojo;
import java.util.List;

/**
 *
 * @author jonas
 */
public interface CppFacturaService {
    
    CppFactura persistCppFactura(CppFactura cppFactura);//CREATE

    CppFactura mergeCppFactura(CppFactura cppFactura);//UPDATE
    
    void removeCppFactura(String idFactura);//DELETE
    
    List<CppFactura> getCppFactura(); //RETRIEVE
    
    CppFacturaCntComprobantePojo guardaCppFacturaCntComprobantePojo(CppFacturaCntComprobantePojo cppFacturaCntComprobantePojo);        
        
}
