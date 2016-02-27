/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.cpanel.servicios.adm;

import com.bap.cpanel.modelo.adm.AdmRol;
import com.bap.cpanel.modelo.adm.AdmUsuario;
import com.bap.cpanel.modelo.adm.AdmUsuarioRol;
import java.util.List;

/**
 *
 * @author Javier
 */
public interface AdmUsuarioRolService {
    
AdmUsuarioRol persistAdmUsuarioRol(AdmUsuarioRol admUsuarioRol) throws Exception;

public List<AdmUsuarioRol> getAdmUsuarioRol() throws Exception;
    
AdmUsuarioRol mergeAdmUsuarioRol(AdmUsuarioRol admUsuarioRol) throws Exception;
        
void asignacionRolesaUsuario(List<AdmRol> listaRolesAsignados, Long idUsuario ) throws Exception;

void removeAdmUsuarioRol(Long idUsuarioRol) throws Exception;
   
AdmUsuarioRol getAdmUsuarioRol(Long idUsuarioRol) throws Exception;

AdmUsuarioRol getRegisterByIdAdmRolAndIdAdmUsuario(Long idRol, Long idUsuario) throws Exception;



}
