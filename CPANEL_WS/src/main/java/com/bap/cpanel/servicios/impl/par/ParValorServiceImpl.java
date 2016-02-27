package com.bap.cpanel.servicios.impl.par;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.commons.dao.IGenericDao;
import com.bap.cpanel.modelo.par.ParDepartamento;
import com.bap.cpanel.modelo.par.ParEstadoPermiso;
import com.bap.cpanel.modelo.par.ParEstadoUsuario;
import com.bap.cpanel.modelo.par.ParTipo;
import com.bap.cpanel.modelo.par.ParTipoDocumento;
import com.bap.cpanel.modelo.par.ParTipoMoneda;
import com.bap.cpanel.modelo.par.ParValor;
import com.bap.cpanel.servicios.par.ParValorService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParValorServiceImpl extends AbstractJpaDAO<ParValor> implements ParValorService {

    IGenericDao<ParValor> dao;
    IGenericDao<ParEstadoUsuario> parEstadoUsuario;
    IGenericDao<ParTipoDocumento> parTipoDocumento;
    IGenericDao<ParEstadoPermiso> parEstadoPermiso;
    IGenericDao<ParTipoMoneda> parTipoMoneda;
    IGenericDao<ParTipo> parTipo;
    IGenericDao<ParDepartamento> parDepartamento;

    @Autowired
    public void setDao(IGenericDao<ParValor> daoToSet) {
        dao = daoToSet;
        dao.setClazz(ParValor.class);
    }

    @Autowired
    public void setParEstadoUsuario(IGenericDao<ParEstadoUsuario> parEstadoUsuarioToSet) {
        parEstadoUsuario = parEstadoUsuarioToSet;
        parEstadoUsuario.setClazz(ParEstadoUsuario.class);
    }
    
    @Autowired
    public void setParTipoDocumento(IGenericDao<ParTipoDocumento> parTipoDocumentoToSet) {
        parTipoDocumento = parTipoDocumentoToSet;
        parTipoDocumento.setClazz(ParTipoDocumento.class);
    }
    
    @Autowired
    public void setParEstadoPermiso(IGenericDao<ParEstadoPermiso> parEstadoPermisoToSet) {
        parEstadoPermiso = parEstadoPermisoToSet;
        parEstadoPermiso.setClazz(ParEstadoPermiso.class);
    }
    
    @Autowired
    public void setParTipoMoneda(IGenericDao<ParTipoMoneda> parTipoMonedaToSet) {
        parTipoMoneda = parTipoMonedaToSet;
        parTipoMoneda.setClazz(ParTipoMoneda.class);
    }
    
    @Autowired
    public void setParTipo(IGenericDao<ParTipo> parTipoToSet) {
        parTipo = parTipoToSet;
        parTipo.setClazz(ParTipo.class);
    }
    
    @Autowired
    public void setParDepartamento(IGenericDao<ParDepartamento> parDepartamentoToSet) {
        parDepartamento = parDepartamentoToSet;
        parDepartamento.setClazz(ParDepartamento.class);
    }

    public ParValor persistParValor(ParValor parValor) {

        parValor.setIdParValor(null);
        parValor.setFechaAlta(new Date());
        parValor.setUsuarioAlta("GUS");

        dao.create(parValor);
        return parValor;
    }

    public List<ParValor> getParValor() {
        return dao.findAll();
    }

    public Object find(Class clazz, String id) {
        return dao.findOne(clazz, id);
    }

    public List<ParEstadoUsuario> getListParEstadoUsuario() throws Exception {
        try {
            return parEstadoUsuario.findAll();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<ParTipoDocumento> getListParTipoDocumento() throws Exception {
        try {
            return parTipoDocumento.findAll();
        } catch (Exception e) {
            throw e;
        }
    }  
    
    public List<ParEstadoPermiso> getListParEstadoPermiso() throws Exception {
        try {
            return parEstadoPermiso.findAll();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<ParTipoMoneda> getListParTipoMoneda() throws Exception {
        try {
            return parTipoMoneda.findAll();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<ParTipo> getListParTipo() throws Exception {
        try {
            return parTipo.findAll();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<ParDepartamento> getListParDepartamento() throws Exception {
        try {
            return parDepartamento.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

}
