/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.cpanel.servicios.adm;

import com.bap.cpanel.modelo.adm.AdmRol;
import com.bap.cpanel.modelo.adm.AdmUsuario;
import com.bap.cpanel.modelo.pojo.EntidadArbolPojo;
import com.bap.cpanel.modelo.pojo.EntidadPojo;
import java.util.List;

/**
 *
 * @author Javier
 */
public interface AdmRolService {
    
    AdmRol persistAdmRol(AdmRol admRol) throws Exception;

    public List<AdmRol> getAdmRol() throws Exception;
    
    List<AdmRol> getAdmUsuarioRol(Long idUsuario) throws Exception;
    
    List<EntidadPojo> getListaEntidadPojoByUsuario(AdmUsuario admUsuario) throws Exception;
    
    List<EntidadArbolPojo> getListaEntidadArbolPojo() throws Exception;
    
    AdmRol getAdmRolById(Long idRol) throws Exception;
    
    EntidadArbolPojo getEntidadArbolPojo(Long idUsuario) throws Exception;
    
    List<AdmRol> getListaAdmRolByUsuario(AdmUsuario admUsuario) throws Exception;
    
    List<AdmRol> getAdmUsuarioSinRol(Long idUsuario) throws Exception;
    
//    List<AdmRol> getAdmRolByIdModulo(Long idModulo) throws Exception;
    
    AdmRol mergeAdmRol(AdmRol admRol) throws Exception;
    
    List<AdmRol> getAdmUsuarioSinRolByIdModulo(Long idUsuario, String idModulo) throws Exception;
    
    List<AdmRol> getAdmUsuarioConRolByIdModulo(Long idUsuario, String idModulo) throws Exception;
    
    public boolean isNumeric(String cadena) throws Exception;

}
