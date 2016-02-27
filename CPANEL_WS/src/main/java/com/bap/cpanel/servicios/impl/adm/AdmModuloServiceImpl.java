package com.bap.cpanel.servicios.impl.adm;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.cpanel.modelo.adm.AdmModulo;
import com.bap.cpanel.modelo.adm.AdmPermiso;
import com.bap.cpanel.modelo.adm.AdmUsuario;
import com.bap.cpanel.modelo.adm.AdmUsuarioRol;
import com.bap.cpanel.modelo.pojo.EntidadPojo;
import com.bap.cpanel.servicios.adm.AdmModuloService;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmModuloServiceImpl /*extends AbstractJpaDAO<AdmModulo>*/ implements AdmModuloService {

    IGenericDao<AdmModulo> dao;


    @Autowired
    public void setDao(IGenericDao<AdmModulo> daoToSet) throws Exception {
        try {
            dao = daoToSet;
            dao.setClazz(AdmModulo.class);
        } catch (Exception e) {
            throw e;
        }

    }

    public List<AdmModulo> getAdmModulo() throws Exception {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw e;
        }

    }

    public AdmModulo getAdmModuloByIdModulo(Long idModulo) throws Exception {
        try {
            return dao.findOne(idModulo);
        } catch (Exception e) {
            throw e;
        }
    }

    public AdmModulo persistAdmModulo(AdmModulo admModulo) throws Exception {
        try {
            admModulo.setIdModulo(null);
            admModulo.setUsuarioAlta("TEST");
            admModulo.setFechaAlta(new Date());
            dao.create(admModulo);
            return admModulo;
        } catch (Exception e) {
            throw e;
        }

    }

    public AdmModulo mergeAdmModulo(AdmModulo admModulo) throws Exception {
        try {
            admModulo.setFechaModificacion(new Date());
            admModulo.setUsuarioModificacion("TEST");
            dao.update(admModulo);
            return admModulo;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<AdmModulo> getAdmModuloByAdmUsuario(AdmUsuario admUsuario) {
//        List<AdmModulo> lista=find("select distinct(rm.admModulo) "
//                + "from AdmRolModulo rm, AdmUsuarioRol ur "
//                + "where rm.admRol.idRol=ur.admRol.idRol "
//                + "and ur.admUsuario.idUsuario="+AdmUsuario.getIdUsuario()+"");        
        List<AdmModulo> lista=dao.find("select distinct(p.admModulo) "
                + "from AdmPermiso p, AdmUsuarioRol ur "
                + "where p.admRol.idRol=ur.admRol.idRol "
                + "and ur.admUsuario.idUsuario="+admUsuario.getIdUsuario()+"");
        return lista;
    }
    
     public List<AdmModulo> listAdmModuloByAdmUsuario(Long idUsuario)throws Exception {
         try {
              List<AdmModulo> listaAdmModulo=dao.find("select distinct(p.admModulo) "
                + "from AdmPermiso p, AdmUsuarioRol ur "
                + "where p.admRol.idRol=ur.admRol.idRol "
                + "and ur.admUsuario.idUsuario="+idUsuario);            
        return listaAdmModulo;
         } catch (Exception e) {
             throw e;
         }
       
    }
     
     
     
}
