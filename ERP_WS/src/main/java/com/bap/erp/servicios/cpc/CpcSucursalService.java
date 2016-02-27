package com.bap.erp.servicios.cpc;

import com.bap.erp.modelo.cpc.CpcSucursal;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import java.util.List;

public interface CpcSucursalService {

    CpcSucursal persistCpcSucursal(CpcSucursal cpcSucursal);

    List<CpcSucursal> getCpcSucursal() throws Exception;

    List<EntidadArbolPojo> getCpcSucursalArbol()throws Exception;
    
    Long generaNumeroSucursal()throws Exception;
    
    CpcSucursal getCpcSucursalByIdSucursal(Long idSucursal)throws Exception;

    CpcSucursal mergeCpcSucursal(CpcSucursal cpcSucursal)throws Exception;
    
    List<CpcSucursal> getSucursalesFiltradoFactura(String caracteristicaEspecial, String estadoProceso) throws Exception;
    
    CpcSucursal sucursalPorNumeroSucursal(Long numero_sucursal) throws Exception;
    
//    CpcSucursal getCpcDosificacionPreEstablecido() throws Exception;

}
