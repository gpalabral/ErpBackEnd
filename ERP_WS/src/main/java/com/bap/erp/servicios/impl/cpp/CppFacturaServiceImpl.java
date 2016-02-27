/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.servicios.impl.cpp;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.modelo.cpp.CppFactura;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.pojo.CpcFacturaEmitidaPojo;
import com.bap.erp.modelo.pojo.CppFacturaCntComprobantePojo;
import com.bap.erp.servicios.cpc.CpcPagoContratoService;
import com.bap.erp.servicios.cpp.CppFacturaService;
import com.bap.erp.servicios.cpp.CppProveedorClienteService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jonas
 */
@Service
public class CppFacturaServiceImpl implements CppFacturaService {

    IGenericDao<CppFactura> dao;

    @Autowired
    public CpcPagoContratoService cpcPlanPagoService;

    @Autowired
    public CppProveedorClienteService cppProveedorClienteService;

    @Autowired
    public void setDao(IGenericDao<CppFactura> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CppFactura.class);
    }

    public CppFactura persistCppFactura(CppFactura cppFactura) {
        cppFactura.setIdFactura(null);
        cppFactura.setFechaAlta(new Date());
        cppFactura.setUsuarioAlta("TEST");
        dao.create(cppFactura);
        return cppFactura;
    }

    public CppFactura mergeCppFactura(CppFactura cppFactura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeCppFactura(String idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<CppFactura> getCppFactura() {
        return dao.findAll();
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public CppFacturaCntComprobantePojo guardaCppFacturaCntComprobantePojo(CppFacturaCntComprobantePojo cppFacturaCntComprobantePojo) {
        CppProveedorCliente cppProveedorCliente = cppProveedorClienteService.getCppProveedorCliente(Long.parseLong(cppFacturaCntComprobantePojo.getIdProveedorCliente()));
        CppFactura cppFactura = cppFacturaCntComprobantePojo.getCppFactura();
        cppFactura.setCppProveedorCliente(cppProveedorCliente);        
        if (cppFactura.getIdFactura() != null) {
            cppFactura = persistCppFactura(cppFactura);
            cppFacturaCntComprobantePojo.setCppFactura(cppFactura);
        }
        return cppFacturaCntComprobantePojo;
    }

  

}
