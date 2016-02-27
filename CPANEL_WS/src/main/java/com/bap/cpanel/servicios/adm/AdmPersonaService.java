package com.bap.cpanel.servicios.adm;

import com.bap.cpanel.modelo.adm.AdmPersona;
import java.util.List;

public interface AdmPersonaService {
    
    AdmPersona persistAdmPersona (AdmPersona admPersona) throws Exception;
    
    List<AdmPersona> getAdmPersona() throws Exception;
    
    AdmPersona getAdmPersona(Long idAdmPersona) throws Exception;
    
    AdmPersona mergeAdmPersona(AdmPersona admPersona) throws Exception;
}
