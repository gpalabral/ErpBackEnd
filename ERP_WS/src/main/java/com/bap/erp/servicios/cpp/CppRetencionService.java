/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.servicios.cpp;

import com.bap.erp.modelo.cpp.CppRetencion;
import java.util.List;

/**
 *
 * @author Jonas
 */
public interface CppRetencionService {
    
    CppRetencion persistCppRetencion(CppRetencion cppRetencion)throws Exception;
    
    List<CppRetencion> getRetencionesByIdProveedorCliente(Long idProveedorCliente)throws Exception;
    
    CppRetencion findCppRetencionById(Long idRetencion)throws Exception;
    
    CppRetencion mergeCppRetencion(CppRetencion cppRetencion)throws Exception;
    
    List<CppRetencion> listaRetencionesPorEstadoPago(String estadoPago)throws Exception;
}

