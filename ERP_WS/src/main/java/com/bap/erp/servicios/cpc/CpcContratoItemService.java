package com.bap.erp.servicios.cpc;

import com.bap.erp.modelo.cpc.CpcContratoItem;
import java.util.List;

public interface CpcContratoItemService {

    CpcContratoItem persistCpcContratoItem(CpcContratoItem cpcContratoItem) throws Exception;

    CpcContratoItem mergeCpcContratoItem(CpcContratoItem cpcContratoItem) throws Exception;

    List<CpcContratoItem> getCpcContratoItem() throws Exception;

    CpcContratoItem getCpcContratoItemById(Long idContratoItem) throws Exception;
    
    List<CpcContratoItem> getCpcContratoItemByIdContrato(Long idContrato) throws Exception;
    
    void deleteCpcContratoItem(CpcContratoItem cpcContratoItem)throws Exception;
}
