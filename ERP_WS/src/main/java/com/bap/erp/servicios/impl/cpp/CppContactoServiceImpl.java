package com.bap.erp.servicios.impl.cpp;

import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.modelo.cpp.CppContacto;
import com.bap.erp.servicios.cpp.CppContactoService;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author paola
 */
@Service
public class CppContactoServiceImpl extends AbstractJpaDAO<CppContacto> implements CppContactoService {

    IGenericDao<CppContacto> dao;

    @Autowired
    public void setDao(IGenericDao<CppContacto> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CppContacto.class);
    }

    public CppContacto persistCppContacto(CppContacto cppContacto) throws Exception {
        try {

            cppContacto.setIdContacto(null);
            cppContacto.setFechaAlta(new Date());
            cppContacto.setUsuarioAlta("TEST");

            dao.create(cppContacto);
            return cppContacto;
        } catch (Exception e) {
            throw e;
        }
    }

    public CppContacto mergeCppContacto(CppContacto cppContacto) throws Exception {
        try {
            cppContacto.setFechaModificacion(new Date());
            cppContacto.setUsuarioModificacion("TEST");
            dao.update(cppContacto);
            return cppContacto;
        } catch (Exception e) {
            throw e;
        }
    }

    public void removeCppContacto(String idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<CppContacto> getCppContacto() {
        return dao.findAll();
    }

    public List<CppContacto> listaContactosPorIdProveedor(String idProveedorCliente) {
        if (idProveedorCliente != null) {
            List<CppContacto> lista = find(""
                    + "select j "
                    + "from CppContacto j "
                    + "where j.fechaBaja is null "
                    + "and j.cppProveedorCliente.idProveedorCliente = " + Long.parseLong(idProveedorCliente) + "");
            if (!lista.isEmpty()) {
                return lista;
            }
        }
        return Collections.EMPTY_LIST;
    }

    public CppContacto getCppContactoByIdContacto(Long idContacto) throws Exception {
        try {
            return dao.findOne(idContacto);
        } catch (Exception e) {
            throw e;    
        }
    }

}
