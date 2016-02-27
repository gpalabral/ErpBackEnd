package com.bap.cpanel.servicios.impl.adm;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.cpanel.modelo.adm.AdmModulo;
import com.bap.cpanel.modelo.adm.AdmPerfil;
import com.bap.cpanel.modelo.adm.AdmUsuario;
import com.bap.cpanel.servicios.adm.AdmPerfilService;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.commons.utils.ObjectUtils;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdmPerfilServiceImpl extends AbstractJpaDAO<AdmPerfil> implements AdmPerfilService {

    IGenericDao<AdmPerfil> dao;

    @Autowired
    public void setDao(IGenericDao<AdmPerfil> daoToSet) {
        dao = daoToSet;
        dao.setClazz(AdmPerfil.class);
    }

    public List<AdmPerfil> getAdmPerfil() throws Exception {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw e;
        }

    }

    public AdmPerfil getAdmPerfilById(Long idPerfil) throws Exception {
        try {
            return dao.findOne(idPerfil);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<AdmPerfil> getAdmPerfilByAdmUsuario(AdmUsuario admUsuario) {
        List<AdmPerfil> lista = find("select o from AdmPerfil o "
                + "where o.admUsuario.idUsuario=" + admUsuario.getIdUsuario() + "");
        return lista;
    }

    public AdmPerfil persistAdmPerfil(AdmPerfil admPerfil) throws Exception {
        try {
            admPerfil.setIdPerfil(null);
            admPerfil.setUsuarioAlta("TEST");
            admPerfil.setFechaAlta(new Date());

            ObjectUtils.printObjectState(admPerfil);

            dao.create(admPerfil);
            return admPerfil;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public AdmPerfil mergeAdmPerfil(AdmPerfil admPerfil) throws Exception {
        try {
            admPerfil.setFechaBaja(new Date());
            admPerfil.setUsuarioBaja("TEST");
            dao.update(admPerfil);
            return admPerfil;
        } catch (Exception e) {
            throw e;
        }
    }

    public void removeAdmPerfil(AdmPerfil admPerfil) throws Exception {
        try {
            admPerfil.setFechaBaja(new Date());
            admPerfil.setUsuarioBaja("TEST");
            dao.update(admPerfil);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void removeAdmPerfil(Long idPerfil) throws Exception {
        try {
            AdmPerfil admPerfil = getAdmPerfilById(idPerfil);
            admPerfil.setFechaBaja(new Date());
            admPerfil.setUsuarioBaja("TEST");
            dao.remove(admPerfil);
        } catch (Exception e) {
            throw e;
        }
    }

    public AdmPerfil getAdmPerfilSucursal(Long idUsuario) throws Exception {
        try {
            List<AdmPerfil> lista = find(""
                    + "select a "
                    + "from AdmPerfil a "
                    + "where a.fechaBaja is null "
                    + "and a.admUsuario.idUsuario = " + idUsuario + " "
                    + "and a.descripcion = 'Sucursal Predeterminada'");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return new AdmPerfil();
        } catch (Exception e) {
            throw e;
        }
    }

    public AdmPerfil getAdmPerfilDosificacion(Long idUsuario) throws Exception {
        try {
            List<AdmPerfil> lista = find(""
                    + "select a "
                    + "from AdmPerfil a "
                    + "where a.fechaBaja is null "
                    + "and a.admUsuario.idUsuario = " + idUsuario + " "
                    + "and a.descripcion = 'Dosificacion Predeterminada'");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return new AdmPerfil();
        } catch (Exception e) {
            throw e;
        }
    }

    public AdmPerfil getAdmPerfilTipoClave(Long idUsuario, String tipoClave) throws Exception {
        try {
            List<AdmPerfil> lista = find(""
                    + "select a "
                    + "from AdmPerfil a "
                    + "where a.fechaBaja is null "
                    + "and a.admUsuario.idUsuario = " + idUsuario + " "
                    + "and a.clave = '" + tipoClave + "'");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return new AdmPerfil();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void removeAdmPerfilById(Long idPerfil) throws Exception {
        try {
            AdmPerfil admPerfil = getAdmPerfilById(idPerfil);
            admPerfil.setFechaBaja(new Date());
            admPerfil.setUsuarioBaja("TEST");
            dao.remove(admPerfil);
        } catch (Exception e) {
            throw e;
        }
    }

    public AdmPerfil guardaAdmDosificacionEnAdmPerfil(AdmPerfil admPerfil) throws Exception {
        try {
            AdmPerfil admPerfilTipo = getAdmPerfilTipoClave(admPerfil.getAdmUsuario().getIdUsuario(), admPerfil.getClave());
            removeAdmPerfil(admPerfilTipo.getIdPerfil());
            AdmPerfil admPerfilAlta = persistAdmPerfil(admPerfil);
            admPerfil.setIdPerfil(null);
            return admPerfilAlta;

        } catch (Exception e) {
            throw e;
        }
    }

}
