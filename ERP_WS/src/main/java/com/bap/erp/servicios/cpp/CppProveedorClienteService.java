package com.bap.erp.servicios.cpp;

import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.pojo.CppFormaPagoCobroCuentasBancariasPojo;
import com.bap.erp.modelo.pojo.CppProveedorClienteBusquedaPojo;
import com.bap.erp.modelo.pojo.CppProveedorClientePojo;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import com.bap.erp.modelo.pojo.ErpProveedorClientePojo;
import java.util.List;

public interface CppProveedorClienteService {

    CppProveedorCliente persistCppProveedorCliente(CppProveedorCliente cppProveedorCliente) throws Exception;//CREATE

    CppProveedorCliente mergeCppProveedorCliente(CppProveedorCliente cppProveedorCliente) throws Exception;//UPDATE

    void removeCppProveedorCliente(Long idProveedorCliente) throws Exception;//eliminacion logica

    List<CppProveedorCliente> getCppProveedorCliente(); //RETRIEVE

    List<CppProveedorCliente> getListaCppProveedorClientePorTipoRegistro(String tipoRegistro);

    CppProveedorCliente guardaProveedorClienteContactoFormaPagoCobro(CppProveedorClientePojo cppProveedorClientePojo) throws Exception;

    CppProveedorCliente getCppProveedorCliente(Long idCppProveedorCliente);

    List<CppProveedorCliente> getListaProveedoresNoAsignadosPorIdConcepto(Long idConcepto);

    List<CppProveedorCliente> listaProveedoresConConceptosAsignados();

    List<EntidadArbolPojo> getProveedorClienteTree(String tipoRegistro)throws Exception;

    List<CppProveedorCliente> getProveedorClientePorIdConcepto(Long idConcepto, String asignado) throws Exception;

    List<EntidadArbolPojo> getProveedorClienteTree(List<CppProveedorCliente> listaProveedores)throws Exception;        
    
    CppProveedorCliente modificaProveedorClienteContactoFormaPagoCobroConceptos(CppProveedorClientePojo cppProveedorClientePojo) throws Exception;
    
    List<CppProveedorCliente> listaCpcProveedorClientePorTipoRegistro(String tipoReg) throws Exception;
    
    List<CppProveedorClienteBusquedaPojo> getCpcProveedorClienteBusquedaPojo(String tipoRegistro) throws Exception;
    
    String getNombreCliente(CppProveedorCliente cppProveedorCliente);
    
    CppFormaPagoCobroCuentasBancariasPojo persistCppFormaPagoCobroCuentasBancariasPojo(CppFormaPagoCobroCuentasBancariasPojo cppFormaPagoCobroCuentasBancariasPojo)throws Exception;
    
    CppFormaPagoCobroCuentasBancariasPojo getCppFormaPagoCobroCuentasBancariasPojoByIdProveedorCliente(Long idProveedorCliente)throws Exception;
    
    List<CppProveedorClienteBusquedaPojo> getCppProveedorClienteParaBancarizacion(String tipoRegistro)throws Exception;
    
    CppProveedorCliente getRegistroAnulado()throws Exception;
    
    List<ErpProveedorClientePojo> getListProveedorCliente()throws Exception;
    
    Boolean verificaSiProveedorClienteNoEstaAsociado(Long idProveedorCliente)throws Exception;
    
//   CPP
    List<ErpProveedorClientePojo> getCppProveedorClienteParaBancarizacionCpp()throws Exception;
}
