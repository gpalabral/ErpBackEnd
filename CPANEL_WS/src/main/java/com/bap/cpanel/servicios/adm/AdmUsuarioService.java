package com.bap.cpanel.servicios.adm;

import com.bap.cpanel.exceptions.InvalidCredentialsException;
import com.bap.cpanel.exceptions.SessionAlreadyCreatedException;
import com.bap.cpanel.exceptions.UnableToCreateAdmUsuarioException;
import com.bap.cpanel.modelo.adm.AdmUsuario;
import com.bap.cpanel.modelo.pojo.AdmUsuarioPojo;
import com.bap.cpanel.modelo.pojo.PerfilUsuarioPojo;
import com.bap.erp.commons.entities.UserToken;
import com.bap.erp.commons.exceptions.EncodingPasswordException;
import java.util.List;
import java.util.Map;

public interface AdmUsuarioService {
    
    UserToken autenticarUsuario(String username, String password) throws InvalidCredentialsException, EncodingPasswordException, SessionAlreadyCreatedException;
    
    AdmUsuario persistAdmUsuario(AdmUsuario admUsuario) throws UnableToCreateAdmUsuarioException, EncodingPasswordException;
    
    List<AdmUsuario> getAdmUsuario() throws Exception;

    AdmUsuario getAdmUsuarioById(Long idUsuario) throws Exception;

    public AdmUsuario getAdmUsuarioByEstado(Long estadoUsuario) throws Exception;
    
    List<AdmUsuario> getListaAdmUsuarioEstado(String estado) throws Exception;  
    
    AdmUsuarioPojo AdmUsuarioPojo(Long idUsuario) throws Exception;
    
    List<AdmUsuarioPojo> listaAdmUsuarioPojo() throws Exception;
    
    AdmUsuario guardaAdmUsuarioAdmPersona(AdmUsuario admUsuario) throws Exception;
    
    AdmUsuario mergeAdmUsuario(AdmUsuario admUsuario) throws Exception;
    
    AdmUsuario mergeAdmUsuarioAdmPersona(AdmUsuario admUsuario) throws Exception;
    
    AdmUsuario getAdmUsuario(Long idUsuario) throws Exception;
         
    AdmUsuario removeAdmUsuarioAdmPersona(Long idUsuario) throws Exception;
    
    PerfilUsuarioPojo getPerfilUsuarioPojo(Long idUsuario) throws Exception;
    
    Boolean checkPassword(String username, String password) throws InvalidCredentialsException, EncodingPasswordException, SessionAlreadyCreatedException;

    AdmUsuario mergePassword(String username, String passwordActual, String passwordNuevo, String repeatPasswordNuevo) throws Exception;
    
    AdmUsuario getAdmUsuario(String login) throws Exception;
    
    String getUserCache(String token) throws Exception;
}
