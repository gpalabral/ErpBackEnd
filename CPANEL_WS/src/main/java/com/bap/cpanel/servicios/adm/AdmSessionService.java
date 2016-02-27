package com.bap.cpanel.servicios.adm;

import com.bap.cpanel.exceptions.SessionAlreadyCreatedException;
import com.bap.cpanel.modelo.adm.AdmUsuario;
import com.bap.erp.commons.entities.UserToken;

public interface AdmSessionService {
    
    boolean isValidToken(String username, String token);
    
    String existsToken(String username);
    
    UserToken persistAdmSession(AdmUsuario admUsuario);
        
}
