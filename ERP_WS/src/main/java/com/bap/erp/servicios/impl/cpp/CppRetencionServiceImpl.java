/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.servicios.impl.cpp;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.enums.EnumEstado;
import com.bap.erp.enums.EnumEstadoPago;
import com.bap.erp.modelo.cpp.CppRetencion;
import com.bap.erp.servicios.cpp.CppRetencionService;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jonas
 */
@Service
public class CppRetencionServiceImpl implements CppRetencionService {

    IGenericDao<CppRetencion> dao;  
    

    @Autowired
    public void setDao(IGenericDao<CppRetencion> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CppRetencion.class);
    }

    public CppRetencion persistCppRetencion(CppRetencion cppRetencion) throws Exception {
        try {
            cppRetencion.setIdRetencion(null);
//            cppRetencion.setFechaAlta(new Date());
//            cppRetencion.setUsuarioAlta("SYS");
            dao.create(cppRetencion);
            return cppRetencion;
        } catch (Exception e) {
            throw e;
        }
    }   

    public List<CppRetencion> getRetencionesByIdProveedorCliente(Long idProveedorCliente) throws Exception {
        try {
            List<CppRetencion> listaRetencion = dao.find(""
                    + "select j "
                    + "from CppRetencion j "
                    + "where j.fechaBaja is null "
                    + "and j.cppProveedorCliente.idProveedorCliente = '"+idProveedorCliente+"' "
                    + "and j.parEstadoPago.codigo = '"+EnumEstadoPago.PENDIENTE.getCodigo()+"' "
                    + "and j.parEstadoRetencion.codigo = '"+EnumEstado.VIGENTE.getCodigo()+"' ");
            if(!listaRetencion.isEmpty()){
                return listaRetencion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public CppRetencion findCppRetencionById(Long idRetencion) throws Exception {
        try {
            CppRetencion cppRetencion = dao.findOne(idRetencion);
            return cppRetencion;
        } catch (Exception e) {
            throw e;
        }
    }

    public CppRetencion mergeCppRetencion(CppRetencion cppRetencion) throws Exception {
        try {
            cppRetencion.setUsuarioAlta("SYS");
            cppRetencion.setFechaAlta(new Date());
            cppRetencion.setUsuarioModificacion("SYS");
            cppRetencion.setFechaModificacion(new Date());
            cppRetencion = dao.update(cppRetencion);
            return cppRetencion;    
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CppRetencion> listaRetencionesPorEstadoPago(String estadoPago) throws Exception {
        try {
            List<CppRetencion> listaRetencion = dao.find(""
                    + "select j "
                    + "from CppRetencion j "
                    + "where j.fechaBaja is null "
                    + "and j.parEstadoPago.codigo = '"+estadoPago+"' "
                    + "order by j.fechaRegistro asc ");
            if(!listaRetencion.isEmpty()){
                return listaRetencion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }
    
}
