package com.bap.erp.servicios.cpc;

import com.bap.erp.modelo.cpc.CpcTipoCambio;
import java.util.Date;
import java.util.List;

public interface CpcTipoCambioService {
        
    List<CpcTipoCambio> getCpcTipoCambio()throws Exception;
    
    CpcTipoCambio getCpcTipoCambioByIdTipoCambio(Long idTipoCambio) throws Exception;
    
    public CpcTipoCambio persistCpcTipoCambio(CpcTipoCambio cpcTipoCambio) throws Exception;
    
    CpcTipoCambio mergeCpcTipoCambio(CpcTipoCambio cpcTipoCambio) throws Exception;
    
    List<CpcTipoCambio> getListCpcTipoCambio() throws Exception;
    
    Boolean getCpcTipoCambioByFecha(Date fecha) throws Exception;
}