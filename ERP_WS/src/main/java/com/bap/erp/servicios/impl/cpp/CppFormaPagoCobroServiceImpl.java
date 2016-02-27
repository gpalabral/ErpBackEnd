package com.bap.erp.servicios.impl.cpp;

import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.modelo.cpp.CppFormaPagoCobro;
import com.bap.erp.modelo.par.ParEstado;
import com.bap.erp.servicios.cpp.CppFormaPagoCobroService;
import com.bap.erp.servicios.par.ParValorService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CppFormaPagoCobroServiceImpl extends AbstractJpaDAO<CppFormaPagoCobro> implements CppFormaPagoCobroService {

    IGenericDao<CppFormaPagoCobro> dao;

    @Autowired
    public ParValorService parValorService;

    @Autowired
    public void setDao(IGenericDao<CppFormaPagoCobro> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CppFormaPagoCobro.class);
    }

    public CppFormaPagoCobro persistCppFormaPagoCobro(CppFormaPagoCobro cppFormaPagoCobro) throws Exception {
        try {

            cppFormaPagoCobro.setParEstado((ParEstado) parValorService.find(ParEstado.class, "VIG"));
            cppFormaPagoCobro.setIdFormaPagoCobro(null);
            cppFormaPagoCobro.setFechaAlta(new Date());
            cppFormaPagoCobro.setUsuarioAlta("TEST");

            dao.create(cppFormaPagoCobro);
            return cppFormaPagoCobro;
        } catch (Exception e) {
            throw e;
        }
    }

    public CppFormaPagoCobro mergeCppFormaPagoCobro(CppFormaPagoCobro cppFormaPagoCobro) throws Exception {
        try {
            cppFormaPagoCobro.setFechaAlta(new Date());
            cppFormaPagoCobro.setUsuarioAlta("SYS");
            cppFormaPagoCobro.setFechaModificacion(new Date());
            cppFormaPagoCobro.setUsuarioModificacion("TEST");
            dao.update(cppFormaPagoCobro);
            return cppFormaPagoCobro;
        } catch (Exception e) {
            throw e;
        }
    }

    public void removeCppFormaPagoCobro(String idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<CppFormaPagoCobro> getCppFormaPagoCobro() throws Exception {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public CppFormaPagoCobro getCppFormaPagoCobroByIdProveedorCliente(Long idProveedorCliente) throws Exception {
        try {
            List<CppFormaPagoCobro> lista = find(""
                    + "select j "
                    + "from CppFormaPagoCobro j "
                    + "where j.cppProveedorCliente.idProveedorCliente = " + idProveedorCliente + " "
                    + "and j.fechaBaja is null");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
        return null;
        } catch (Exception e) {
            throw e;
        }
    }
}
