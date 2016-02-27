/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.cpanel.servicios.adm;

import com.bap.cpanel.modelo.adm.AdmPermiso;
import com.bap.cpanel.modelo.pojo.EntidadArbolPojo;
import com.bap.cpanel.modelo.pojo.EntidadPojo;
import java.util.List;

/**
 *
 * @author gus
 */
public interface AdmPermisoService {
    
    AdmPermiso persistAdmPermiso(AdmPermiso admPermiso)throws Exception;

    List<AdmPermiso> getAdmPermiso() throws Exception;
    
    List<AdmPermiso> getAdmPermiso(Long idRol) throws Exception; 
    
    List<AdmPermiso> getAdmModuloPermiso(Long idModulo) throws Exception;
    
    List<AdmPermiso> getAdmUsuarioConRol(Long idUsuario, Long idModulo) throws Exception;
    
    List<EntidadPojo> getListaEntidadPojoByPermiso(Long idUsuario, Long idModulo) throws Exception;
    
    List<EntidadArbolPojo> getListaEntidadArbolPojo(Long idUsuario, Long idModulo) throws Exception;
    
    List<String> listAdmPermisosByAdmModulo(Long idUsuario,Long idModulo)throws Exception;
}
