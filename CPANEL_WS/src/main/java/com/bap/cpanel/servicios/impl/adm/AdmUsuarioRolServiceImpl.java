 package com.bap.cpanel.servicios.impl.adm;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.cpanel.modelo.adm.AdmRol;
import com.bap.cpanel.modelo.adm.AdmUsuario;
import com.bap.cpanel.modelo.adm.AdmUsuarioRol;
import com.bap.cpanel.servicios.adm.AdmRolService;
import com.bap.cpanel.servicios.adm.AdmUsuarioService;
import com.bap.cpanel.servicios.adm.AdmUsuarioRolService;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdmUsuarioRolServiceImpl extends AbstractJpaDAO<AdmUsuarioRol> implements AdmUsuarioRolService {

     IGenericDao<AdmUsuarioRol> dao;
     
    @Autowired
    public AdmRolService admRolService;
    @Autowired
    public AdmUsuarioService admUsuarioService;

    @Autowired
    public void setDao(IGenericDao<AdmUsuarioRol> daoToSet) {
        dao = daoToSet;
        dao.setClazz(AdmUsuarioRol.class);
    }
    
    public AdmUsuarioRol persistAdmUsuarioRol(AdmUsuarioRol admUsuarioRol) throws Exception {
        try {
            System.out.println("test ");
            admUsuarioRol.setIdUsuarioRol(null);
            admUsuarioRol.setUsuarioAlta("TEST");
            admUsuarioRol.setFechaAlta(new Date());
            dao.create(admUsuarioRol);
            return admUsuarioRol;
        } catch (Exception e) {
            throw e;
        }

    }

    public List<AdmUsuarioRol> getAdmUsuarioRol() {
        List<AdmUsuarioRol> listaAdmUsuarioRol = find(""
                + "select a "
                + "from AdmUsuarioRol a "
                + "where a.fechaBaja is null "
                + "order by fechaAlta ASC");
        if (!listaAdmUsuarioRol.isEmpty()) {
            return listaAdmUsuarioRol;
        }
        return Collections.EMPTY_LIST;
    }
    
    public AdmUsuarioRol mergeAdmUsuarioRol(AdmUsuarioRol admUsuarioRol) throws Exception {
        try {
            admUsuarioRol.setFechaModificacion(new Date());
            admUsuarioRol.setUsuarioModificacion("TEST");
            dao.update(admUsuarioRol);
            return admUsuarioRol;
        } catch (Exception e) {
            throw e;
        }
    }

    
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void asignacionRolesaUsuario(List<AdmRol> listaRolesAsignados, Long idUsuario ) throws Exception {
        
        System.out.println("iod"+idUsuario);
        System.out.println("iod"+listaRolesAsignados);
        try {
            List<AdmRol> listaRolesBD = admRolService.getAdmUsuarioRol(idUsuario);
            AdmUsuario admUsuario = admUsuarioService.getAdmUsuario(idUsuario);
            System.out.println("admUsuario" + admUsuario);
            
            AdmUsuarioRol admUsuarioRol;
            
            for (AdmRol admRol : listaRolesAsignados) {                
                
                if (!listaRolesBD.contains(admRol)) {  
                    
                    admUsuarioRol = new AdmUsuarioRol();
                    admUsuarioRol.setAdmRol(admRol);
                    admUsuarioRol.setAdmUsuario(admUsuario);
                    persistAdmUsuarioRol(admUsuarioRol);            
                }
            }
            for (AdmRol admRolBD : listaRolesBD) {       
                
                if (!listaRolesAsignados.contains(admRolBD)) {  
                    removeAdmUsuarioRol(getRegisterByIdAdmRolAndIdAdmUsuario(admRolBD.getIdRol(), admUsuario.getIdUsuario()).getIdUsuarioRol());
                }               
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void removeAdmUsuarioRol(Long idUsuarioRol) throws Exception {
        try {
            AdmUsuarioRol admUsuarioRol = getAdmUsuarioRol(idUsuarioRol);
            admUsuarioRol.setFechaBaja(new Date());
            admUsuarioRol.setUsuarioBaja("TEST");
            dao.remove(admUsuarioRol);
        } catch (Exception e) {
            throw e;
        }
    }

    public AdmUsuarioRol getAdmUsuarioRol(Long idUsurioRol) throws Exception {
        try {
            AdmUsuarioRol admUsuarioRol = dao.findOne(idUsurioRol);
            return admUsuarioRol;
        } catch (Exception e) {
            throw e;
        }
    }
    
     public AdmUsuarioRol getRegisterByIdAdmRolAndIdAdmUsuario(Long idRol, Long idUsuario) throws Exception {
        try {
            List<AdmUsuarioRol> list = find(""
                    + "select b "
                    + "from AdmUsuarioRol b "
                    + "where b.fechaBaja is null "
                    + "and b.admRol.idRol = " + idRol + " "
                    + "and b.admUsuario.idUsuario = " + idUsuario + "");
            if (!list.isEmpty()) {
                return list.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return new AdmUsuarioRol();
    }
}