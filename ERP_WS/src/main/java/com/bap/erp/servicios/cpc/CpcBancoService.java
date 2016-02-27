package com.bap.erp.servicios.cpc;

import com.bap.erp.modelo.cpc.CpcBanco;
import java.util.List;

public interface CpcBancoService {
    
    CpcBanco persistCpcBanco (CpcBanco cpcBanco);
    
    List<CpcBanco> getCpcBanco()throws Exception;
    
    CpcBanco getCpcBancoById(Long idBanco) throws Exception;
   
}