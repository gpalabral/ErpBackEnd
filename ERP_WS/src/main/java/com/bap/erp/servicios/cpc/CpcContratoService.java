package com.bap.erp.servicios.cpc;

import com.bap.erp.modelo.cpc.CpcContrato;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.pojo.CpcContratoPojo;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import java.util.List;

public interface CpcContratoService {
    
    CpcContrato persistCpcContrato (CpcContrato cpcContrato)throws Exception;
    
    CpcContrato mergeCpcContrato(CpcContrato cpcContrato)throws Exception;     
    
    List<CpcContrato> getCpcContrato()throws Exception;
    
    List<CpcContrato> listaCpcContratoByFecha()throws Exception;
    
    List<CpcContrato> listaContrato() throws Exception;
    
    List<EntidadArbolPojo> getCpcContratoArbol() throws Exception;
   
    CpcContrato getCpcContratoById(Long idContrato)throws Exception;
    
    void guardaContratoPojo(CpcContratoPojo cpcContratoPojo) throws Exception;
    
    List<CpcContrato> getCpcContratoByFecha(int month) throws Exception;
    
    List<EntidadArbolPojo> getCpcContratoProveedorClienteArbol() throws Exception;
    
    List<CpcContrato> getCpcContratoItemByTipoItem(String parTipoItem) throws Exception;
    
    List<CpcContrato> listaCpcContratoByEstadoPago() throws Exception;

    List<EntidadArbolPojo> getCpcContratoArbolFiltro() throws Exception; 
    
    void deleteCpcContrato(CpcContrato cpcContrato)throws Exception;
    
    List<CpcContrato> getCpcContratoByCppProveedorCliente(CppProveedorCliente cppProveedorCliente)throws Exception;
}
