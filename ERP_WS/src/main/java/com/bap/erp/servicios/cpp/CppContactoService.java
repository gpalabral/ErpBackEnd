/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.servicios.cpp;

import com.bap.erp.modelo.cpp.CppContacto;
import java.util.List;

/**
 *
 * @author paola
 */
public interface CppContactoService {
    
    CppContacto persistCppContacto(CppContacto cppContacto) throws Exception;//CREATE

    CppContacto mergeCppContacto(CppContacto cppContacto) throws Exception;//UPDATE
    
    void removeCppContacto(String idUsuario);//DELETE
    
    List<CppContacto> getCppContacto(); //RETRIEVE
    
    List<CppContacto> listaContactosPorIdProveedor(String idProveedorCliente);
    
    CppContacto getCppContactoByIdContacto(Long idContacto)throws Exception;
    
}
