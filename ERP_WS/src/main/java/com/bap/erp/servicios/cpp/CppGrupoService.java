package com.bap.erp.servicios.cpp;

import com.bap.erp.modelo.cpp.CppGrupo;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.pojo.Entidad;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import java.util.List;

public interface CppGrupoService {
    
    CppGrupo persistCppGrupo(CppGrupo cppGrupo);//CREATE

    CppGrupo mergeCppGrupo(CppGrupo cppGrupo);//UPDATE
    
    void removeCppGrupo(Long idCppGrupo)throws Exception;//DELETE        
    
    List<CppGrupo> getCppGrupo(); //RETRIEVE
    
    CppGrupo getCppGrupo(Long id);
    
    List<CppGrupo> getListaGrupos();       
    
    List<Entidad> getListaGruposYConceptos();
    
    List<EntidadArbolPojo> getListaEntidadArbolPojo();        
    
    List<CppGrupo> getListaGruposPorProveedorCliente(CppProveedorCliente cppProveedorCliente);      
    
    List<EntidadArbolPojo> getGruposConConceptosAsignados();
    
    List<CppGrupo> getListaGruposConConceptos();
    
    void bajaCppGrupo(CppGrupo cppGrupo)throws Exception;
    
}
