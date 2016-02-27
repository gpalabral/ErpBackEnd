package com.bap.cpanel.servicios.impl.adm;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.cpanel.modelo.adm.AdmPersona;
import com.bap.cpanel.servicios.adm.AdmPersonaService;
import com.bap.cpanel.servicios.adm.AdmUsuarioService;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmPersonaServiceImpl extends AbstractJpaDAO<AdmPersona> implements AdmPersonaService {

    IGenericDao<AdmPersona> dao;

    @Autowired
    private AdmUsuarioService admUsuarioService;

    @Autowired
    public void setDao(IGenericDao<AdmPersona> daoToSet) throws Exception {
        try {
            dao = daoToSet;
            dao.setClazz(AdmPersona.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public AdmPersona getAdmPersona(Long idAdmPersona) throws Exception {
        try {
            return dao.findOne(idAdmPersona);
        } catch (Exception e) {
            throw e;
        }
    }

    public AdmPersona persistAdmPersona(AdmPersona admPersona) throws Exception {
        try {
            admPersona.setIdPersona(null);
            admPersona.setUsuarioAlta("TEST");
            admPersona.setFechaAlta(new Date());
            dao.create(admPersona);
            return admPersona;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<AdmPersona> getAdmPersona() throws Exception {
        try {
            List<AdmPersona> listaAdmPersona = find(""
                    + "select j "
                    + "from AdmPersona j "
                    + "where j.fechaBaja is null "
                    + "order by fechaAlta ASC");
            if (!listaAdmPersona.isEmpty()) {
                return listaAdmPersona;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public AdmPersona mergeAdmPersona(AdmPersona admPersona) throws Exception {
        try {
            admPersona.setFechaModificacion(new Date());
            admPersona.setUsuarioModificacion("TEST");
            dao.update(admPersona);
            return admPersona;
        } catch (Exception e) {
            throw e;
        }
    }
    
//    public void removeAdmPersona(AdmPersona admPersona) throws Exception {
//        try {
////            AdmPersona admPersona = getAdmPersona(idPersona);
//            admPersona.setFechaBaja(new Date());
//            admPersona.setUsuarioBaja("TEST");
//            dao.remove(admPersona);
//        } catch (Exception e) {
//            throw e;
//        }
//    }
}
