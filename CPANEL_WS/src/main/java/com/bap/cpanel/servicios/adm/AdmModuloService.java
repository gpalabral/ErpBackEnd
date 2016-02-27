/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.cpanel.servicios.adm;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.cpanel.modelo.adm.AdmModulo;
import com.bap.cpanel.modelo.adm.AdmUsuario;
import java.util.List;

/**
 *
 * @author Javier
 */
public interface AdmModuloService {
    
    void setDao(IGenericDao<AdmModulo> daoToSet) throws Exception;
    
    List<AdmModulo> getAdmModulo() throws Exception;
    
    AdmModulo getAdmModuloByIdModulo(Long idModulo) throws Exception;
    
    AdmModulo persistAdmModulo(AdmModulo admModulo) throws Exception;
    
    AdmModulo mergeAdmModulo(AdmModulo admModulo) throws Exception;
    
    List<AdmModulo> getAdmModuloByAdmUsuario(AdmUsuario admUsuario);
    
    List<AdmModulo> listAdmModuloByAdmUsuario(Long idUsuario)throws Exception;
    
}
