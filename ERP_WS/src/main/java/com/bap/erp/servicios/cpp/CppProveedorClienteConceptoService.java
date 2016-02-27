package com.bap.erp.servicios.cpp;

import com.bap.erp.modelo.cpp.CppConcepto;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.cpp.CppProveedorClienteConcepto;
import com.bap.erp.modelo.pojo.ProveedorGrupoConcepto;
import java.util.List;

public interface CppProveedorClienteConceptoService{
    
    CppProveedorClienteConcepto persistCppProveedorClienteConcepto(CppProveedorClienteConcepto cppProveedorClienteConcepto) throws Exception;
    
    CppProveedorClienteConcepto mergeProveedorClienteConcepto(CppProveedorClienteConcepto cppProveedorClienteConcepto)throws Exception;
    
    void removeProveedorClienteConcepto(Long idProveedorClienteConcepto)throws Exception;
    
    List<CppProveedorClienteConcepto> getCppProveedorClienteConcepto(); 
    
    List<CppProveedorClienteConcepto> listaCppProveedorClienteConceptoPorIdCppProveedorCliente(Long idCppProveedorCliente);
    
    List<CppProveedorClienteConcepto> listaCppProveedorClienteConceptoPorIdConcepto(Long idConcepto);
    
    List<CppProveedorClienteConcepto> getCppProveedorClienteConceptoOrdenados(); 
        
    List<ProveedorGrupoConcepto> getProveedorGrupoConcepto();
    
    void removeConceptosNoAsignados(List<CppConcepto> listaConceptos, CppProveedorCliente cppProveedorCliente) throws Exception;
    
    CppProveedorClienteConcepto getCppProveedorClienteConcepto(Long idProveedorClienteConcepto)throws Exception;
    
    CppProveedorClienteConcepto getRegisterByIdCppConceptoAndIdCppProveedor(Long idConcepto, Long idProveedorCliente)throws Exception;
    
    void removeProveedoresNoAsignados(List<CppProveedorCliente> listaProveedorClientes, CppConcepto cppConcepto) throws Exception;
}
