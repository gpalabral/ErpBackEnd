/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.servicios.cpp;

import com.bap.erp.modelo.cpc.CpcDosificacion;
import com.bap.erp.modelo.cpp.CppConcepto;
import com.bap.erp.modelo.cpp.CppGrupo;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.pojo.CppConceptoProveedoresPojo;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import com.bap.erp.modelo.pojo.EntidadPojo;
import java.util.List;

/**
 *
 * @author Jonas
 */
public interface CppConceptoService {
    
    CppConcepto persistCppConcepto(CppConcepto cppConcepto)throws Exception;//CREATE

    CppConcepto mergeCppConcepto(CppConcepto cppConcepto) throws Exception;//UPDATE
    
    void removeCppConcepto(Long idConcepto)throws Exception;//DELETE
    
    List<CppConcepto> getListCppConcepto(); //RETRIEVE
    
    CppConcepto getCppConcepto(Long idCppConcepto);
    
    List<CppConcepto> getListaCppConceptoOrdenadosPorGrupo();       
    
    List<CppConcepto> getListaCppConceptoByGrupo(CppGrupo cppGrupo);
    
    List<EntidadPojo> getListaEntidadPojoByGrupo(CppGrupo cppGrupo);
    
    List<EntidadArbolPojo> getListaEntidadArbolPojoPorProveedor(CppProveedorCliente cppProveedorCliente);
    
    List<EntidadPojo> getListaEntidadPojoByGrupoAndProveedorCliente(CppGrupo cppGrupo, CppProveedorCliente cppProveedorCliente);
    
    List<CppConcepto> getListaConceptosPorGrupoyProveedor(CppGrupo cppGrupo, CppProveedorCliente cppProveedorCliente);
    
    List<EntidadPojo> getEntidadPojoPorListaConceptos(List<CppConcepto> listaConceptos);
    
    List<CppConcepto> getCppConceptoNoAsignadosPorGrupoYProveedor(CppGrupo cppGrupo, CppProveedorCliente cppProveedorCliente);  
    
    List<EntidadArbolPojo> getGruposConceptosNoAsignadosAProveedor(CppProveedorCliente cppProveedorCliente);           
    
    List<CppConcepto> getListaCppConceptoByProveedor(CppProveedorCliente cppProveedorCliente);        
    
    void deleteCppConcepto(Long idConcepto)throws Exception; //Da de baja un registro
    
    List<CppConcepto> getConceptosByEntidadArbolPojo(List<EntidadArbolPojo> entidadArbolPojoList)throws Exception;
      
    CppConceptoProveedoresPojo persistCppConceptoProveedorPojo(CppConceptoProveedoresPojo cppConceptoProveedoresPojo)throws Exception;
    
    CppConceptoProveedoresPojo mergeCppConceptoProveedorPojo(CppConceptoProveedoresPojo cppConceptoProveedoresPojo)throws Exception;
}

