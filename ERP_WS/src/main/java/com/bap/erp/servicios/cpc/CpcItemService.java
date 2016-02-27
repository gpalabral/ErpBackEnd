package com.bap.erp.servicios.cpc;

import com.bap.erp.modelo.cpc.CpcItem;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import java.math.BigDecimal;
import java.util.List;

public interface CpcItemService {

    CpcItem persistCpcItem(CpcItem cpcItem) throws Exception;

    List<CpcItem> getCpcItem() throws Exception;

    List<EntidadArbolPojo> getCpcItemArbol() throws Exception;

    public CpcItem mergeCpcItem(CpcItem cpcItem) throws Exception;

    CpcItem cpcItemPorIdItem(Long idItem) throws Exception;

    BigDecimal calculaMontoDolares(BigDecimal monto, BigDecimal montoDolar, String tipoMoneda) throws Exception;

//    List<CpcItem> getCpcContratoItem(Long idContrato) throws Exception;

    List<CpcItem> getCpcItemByIdContrato(Long idContrato) throws Exception;    

    List<EntidadArbolPojo> getCpcItemArbolPorCpcContrato(Long idContrato) throws Exception;
    
    Boolean verificaSiElCodigoExiste(String codigo)throws Exception;
    
    List<CpcItem> getCpcItemList()throws Exception;

}
