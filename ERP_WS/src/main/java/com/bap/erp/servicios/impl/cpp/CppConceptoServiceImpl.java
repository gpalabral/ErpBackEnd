/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.servicios.impl.cpp;

import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.modelo.cpc.CpcDosificacion;
import com.bap.erp.modelo.cpp.CppConcepto;
import com.bap.erp.modelo.cpp.CppGrupo;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.cpp.CppProveedorClienteConcepto;
import com.bap.erp.modelo.pojo.CppConceptoProveedoresPojo;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import com.bap.erp.modelo.pojo.EntidadPojo;
import com.bap.erp.servicios.cpp.CppConceptoService;
import com.bap.erp.servicios.cpp.CppGrupoService;
import com.bap.erp.servicios.cpp.CppProveedorClienteConceptoService;
import com.bap.erp.servicios.cpp.CppProveedorClienteService;
import java.util.ArrayList;
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
public class CppConceptoServiceImpl extends AbstractJpaDAO<CppConcepto> implements CppConceptoService {

    IGenericDao<CppConcepto> dao;

    @Autowired
    public CppGrupoService cppGrupoService;
    @Autowired
    public CppProveedorClienteService cppProveedorClienteService;
    @Autowired
    public CppProveedorClienteConceptoService cppProveedorClienteConceptoService;

    @Autowired
    public void setDao(IGenericDao<CppConcepto> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CppConcepto.class);
    }

    public CppConcepto persistCppConcepto(CppConcepto cppConcepto) {
        cppConcepto.setIdConcepto(null);
        cppConcepto.setFechaAlta(new Date());
        cppConcepto.setUsuarioAlta("TEST");

        dao.create(cppConcepto);
        return cppConcepto;
    }

    public CppConcepto mergeCppConcepto(CppConcepto cppConcepto) throws Exception {
        try {
            cppConcepto.setFechaModificacion(new Date());
            cppConcepto.setUsuarioModificacion("TEST");
            dao.update(cppConcepto);
        } catch (Exception e) {
            throw e;
        }
        return cppConcepto;
    }

    public void removeCppConcepto(Long idConcepto) throws Exception {
        try {
            CppConcepto cppConcepto = getCppConcepto(idConcepto);
            cppConcepto.setFechaBaja(new Date());
            cppConcepto.setUsuarioBaja("TEST");
            dao.update(cppConcepto);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CppConcepto> getListCppConcepto() {
        return dao.findAll();
    }

    public CppConcepto getCppConcepto(Long idCppConcepto) {
        return dao.findOne(idCppConcepto);
    }

    public List<CppConcepto> getListaCppConceptoOrdenadosPorGrupo() {
        List<CppConcepto> listaConceptos = find(""
                + "select j "
                + "from CppConcepto j "
                + "where j.fechaBaja is null "
                + "and j.cppGrupo.fechaBaja is null "
                + "order by j.cppGrupo.idGrupo asc");
        return listaConceptos;
    }

    public List<CppConcepto> getListaCppConceptoByGrupo(CppGrupo cppGrupo) {
        List<CppConcepto> listaConceptos = find(""
                + "select j "
                + "from CppConcepto j "
                + "where j.fechaBaja is null "
                + "and j.cppGrupo.idGrupo = " + cppGrupo.getIdGrupo() + "");
        if (!listaConceptos.isEmpty()) {
            return listaConceptos;
        }
        return listaConceptos;
    }

    public List<EntidadPojo> getListaEntidadPojoByGrupo(CppGrupo cppGrupo) {
        List<CppConcepto> listaConceptos = getListaCppConceptoByGrupo(cppGrupo);
        List<EntidadPojo> listaEntidadPojo = new ArrayList<EntidadPojo>();
        EntidadPojo entidadPojo;
        for (CppConcepto cppConcepto : listaConceptos) {
            entidadPojo = new EntidadPojo();
            entidadPojo.setDescripcion(cppConcepto.getDescripcion());
            entidadPojo.setIdEntidadPojo(cppConcepto.getIdConcepto());
            entidadPojo.setMascara("");
            entidadPojo.setTipo("CON");
            listaEntidadPojo.add(entidadPojo);
        }
        if (!listaEntidadPojo.isEmpty()) {
            return listaEntidadPojo;
        }
        return Collections.EMPTY_LIST;
    }

    public List<EntidadArbolPojo> getListaEntidadArbolPojoPorProveedor(CppProveedorCliente cppProveedorCliente) {
        List<EntidadArbolPojo> listaEntidadArbolPojo = new ArrayList<EntidadArbolPojo>();
        List<CppGrupo> listaGrupos = cppGrupoService.getListaGruposPorProveedorCliente(cppProveedorCliente);
        EntidadArbolPojo entidadArbolPojo;
        List<EntidadPojo> listaConcepto;
        for (CppGrupo cppGrupo : listaGrupos) {
            entidadArbolPojo = new EntidadArbolPojo();
            entidadArbolPojo.setIdEntidadPojo(cppGrupo.getIdGrupo());
            entidadArbolPojo.setDescripcion(cppGrupo.getNombre());
            entidadArbolPojo.setMascara("");
            entidadArbolPojo.setTipo("GRU");
            listaConcepto = getEntidadPojoPorListaConceptos(getListaConceptosPorGrupoyProveedor(cppGrupo, cppProveedorCliente));
            if (!listaConcepto.isEmpty()) {
                entidadArbolPojo.setChildren(listaConcepto);
            }
            listaEntidadArbolPojo.add(listaEntidadArbolPojo.size(), entidadArbolPojo);
        }
        if (!listaEntidadArbolPojo.isEmpty()) {
            return listaEntidadArbolPojo;
        }
        return Collections.EMPTY_LIST;
    }

    public List<EntidadPojo> getListaEntidadPojoByGrupoAndProveedorCliente(CppGrupo cppGrupo, CppProveedorCliente cppProveedorCliente) {
        List<CppConcepto> listaConceptos = getListaCppConceptoByGrupo(cppGrupo);
        List<EntidadPojo> listaEntidadPojo = new ArrayList<EntidadPojo>();
        EntidadPojo entidadPojo;
        for (CppConcepto cppConcepto : listaConceptos) {
            entidadPojo = new EntidadPojo();
            entidadPojo.setDescripcion(cppConcepto.getDescripcion());
            entidadPojo.setIdEntidadPojo(cppConcepto.getIdConcepto());
            entidadPojo.setMascara("");
            entidadPojo.setTipo("CON");
            listaEntidadPojo.add(entidadPojo);
        }
        if (!listaEntidadPojo.isEmpty()) {
            return listaEntidadPojo;
        }
        return Collections.EMPTY_LIST;
    }

    public List<CppConcepto> getListaConceptosPorGrupoyProveedor(CppGrupo cppGrupo, CppProveedorCliente cppProveedorCliente) {
        List<CppConcepto> listaConceptos = find(""
                + "select distinct j.cppConcepto "
                + "from CppProveedorClienteConcepto j "
                + "where j.fechaBaja is null "
                + "and j.cppProveedorCliente.idProveedorCliente = " + cppProveedorCliente.getIdProveedorCliente() + " "
                + "and j.cppConcepto.cppGrupo.idGrupo = " + cppGrupo.getIdGrupo() + "");
        if (!listaConceptos.isEmpty()) {
            return listaConceptos;
        }
        return Collections.EMPTY_LIST;
    }

    public List<EntidadPojo> getEntidadPojoPorListaConceptos(List<CppConcepto> listaConceptos) {
        List<EntidadPojo> listaEntidadPojo = new ArrayList<EntidadPojo>();
        EntidadPojo entidadPojo;
        for (CppConcepto cppConcepto : listaConceptos) {
            entidadPojo = new EntidadPojo();
            entidadPojo.setDescripcion(cppConcepto.getDescripcion());
            entidadPojo.setIdEntidadPojo(cppConcepto.getIdConcepto());
            entidadPojo.setMascara("");
            entidadPojo.setTipo("CON");
            listaEntidadPojo.add(entidadPojo);
        }
        if (!listaEntidadPojo.isEmpty()) {
            return listaEntidadPojo;
        }
        return Collections.EMPTY_LIST;
    }

    public List<CppConcepto> getCppConceptoNoAsignadosPorGrupoYProveedor(CppGrupo cppGrupo, CppProveedorCliente cppProveedorCliente) {
        List<CppConcepto> listaConceptos = find(""
                + "select j "
                + "from CppConcepto j "
                + "where j.fechaBaja is null "
                + "and j.cppGrupo.fechaBaja is null "
                + "and j.cppGrupo.idGrupo = " + cppGrupo.getIdGrupo() + " "
                + "and j.idConcepto not in "
                + "(select o.cppConcepto.idConcepto "
                + "from CppProveedorClienteConcepto o "
                + "where o.fechaBaja is null "
                + "and o.cppProveedorCliente.idProveedorCliente = " + cppProveedorCliente.getIdProveedorCliente() + ") "
                + "order by j.cppGrupo.idGrupo asc");
        if (!listaConceptos.isEmpty()) {
            return listaConceptos;
        }
        return Collections.EMPTY_LIST;
    }

    public List<EntidadArbolPojo> getGruposConceptosNoAsignadosAProveedor(CppProveedorCliente cppProveedorCliente) {
        List<EntidadArbolPojo> listaArbol = new ArrayList<EntidadArbolPojo>();
        EntidadArbolPojo gruposArbol;
        List<CppGrupo> listaGrupos = cppGrupoService.getListaGrupos();
        List<CppConcepto> listaConceptos;
        for (CppGrupo cppGrupo : listaGrupos) {
            listaConceptos = getCppConceptoNoAsignadosPorGrupoYProveedor(cppGrupo, cppProveedorCliente);
            if (!listaConceptos.isEmpty()) {
                gruposArbol = new EntidadArbolPojo();
                gruposArbol.setIdEntidadPojo(cppGrupo.getIdGrupo());
                gruposArbol.setDescripcion(cppGrupo.getNombre());
                gruposArbol.setMascara(cppGrupo.getParRecurrencia().getDescripcion());
                gruposArbol.setTipo("GRU");
                gruposArbol.setChildren(getEntidadPojoPorListaConceptos(listaConceptos));
                listaArbol.add(gruposArbol);
            }
        }
        return listaArbol;
    }

    public List<CppConcepto> getListaCppConceptoByProveedor(CppProveedorCliente cppProveedorCliente) {
        List<CppConcepto> list = find(""
                + "select a.cppConcepto "
                + "from CppProveedorClienteConcepto a "
                + "where a.fechaBaja is null "
                + "and a.cppProveedorCliente.idProveedorCliente = " + cppProveedorCliente.getIdProveedorCliente() + "");
        if (!list.isEmpty()) {
            return list;
        }
        return list;
    }

    public List<EntidadArbolPojo> getGruposConceptosAsignadosAProveedor(CppProveedorCliente cppProveedorCliente) {
        List<EntidadArbolPojo> listaArbol = new ArrayList<EntidadArbolPojo>();
        EntidadArbolPojo gruposArbol;
        List<CppGrupo> listaGrupos = cppGrupoService.getListaGrupos();
        List<CppConcepto> listaConceptos;
        for (CppGrupo cppGrupo : listaGrupos) {
            listaConceptos = getCppConceptoNoAsignadosPorGrupoYProveedor(cppGrupo, cppProveedorCliente);
            if (!listaConceptos.isEmpty()) {
                gruposArbol = new EntidadArbolPojo();
                gruposArbol.setIdEntidadPojo(cppGrupo.getIdGrupo());
                gruposArbol.setDescripcion(cppGrupo.getNombre());
                gruposArbol.setMascara("");
                gruposArbol.setTipo("GRU");
                gruposArbol.setChildren(getEntidadPojoPorListaConceptos(listaConceptos));
                listaArbol.add(gruposArbol);
            }
        }
        return listaArbol;
    }

    public void deleteCppConcepto(Long idConcepto) throws Exception {
        try {
            CppConcepto cppConcepto = getCppConcepto(idConcepto);
            dao.delete(cppConcepto);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcDosificacion> getListCpcDosificaciones() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    public List<CppConcepto> getConceptosByEntidadArbolPojo(List<EntidadArbolPojo> entidadArbolPojoList) throws Exception {
        List<CppConcepto> conceptoList = new ArrayList<CppConcepto>();
        CppConcepto cppConcepto;
        try {
            for (EntidadArbolPojo entidadArbolPojo : entidadArbolPojoList) {
                for (EntidadPojo entidadPojo : entidadArbolPojo.getChildren()) {
                    cppConcepto = getCppConcepto(entidadPojo.getIdEntidadPojo());
                    conceptoList.add(cppConcepto);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return conceptoList;
    }

    public CppConceptoProveedoresPojo persistCppConceptoProveedorPojo(CppConceptoProveedoresPojo cppConceptoProveedoresPojo) throws Exception {
        try {
            CppConcepto cppConcepto = cppConceptoProveedoresPojo.getCppConcepto();
            persistCppConcepto(cppConcepto);
            List<EntidadArbolPojo> listaProveedores = cppConceptoProveedoresPojo.getListaProveedoresClientes();
            CppProveedorCliente cppProveedorCliente;
            CppProveedorClienteConcepto cppProveedorClienteConcepto;
            if (listaProveedores != null) {
                for (EntidadArbolPojo entidadArbolPojo : listaProveedores) {
                    cppProveedorClienteConcepto = new CppProveedorClienteConcepto();
                    cppProveedorCliente = cppProveedorClienteService.getCppProveedorCliente(entidadArbolPojo.getIdEntidadPojo());
                    cppProveedorClienteConcepto.setCppConcepto(cppConcepto);
                    cppProveedorClienteConcepto.setCppProveedorCliente(cppProveedorCliente);
                    cppProveedorClienteConceptoService.persistCppProveedorClienteConcepto(cppProveedorClienteConcepto);
                }
            }
            return cppConceptoProveedoresPojo;
        } catch (Exception e) {
            throw e;
        }
    }

    public CppConceptoProveedoresPojo mergeCppConceptoProveedorPojo(CppConceptoProveedoresPojo cppConceptoProveedoresPojo) throws Exception {
        try {
            CppConcepto cppConcepto = cppConceptoProveedoresPojo.getCppConcepto();
            CppProveedorClienteConcepto cppProveedorClienteConcepto;
            mergeCppConcepto(cppConcepto);
            List<EntidadArbolPojo> listaArbol = cppConceptoProveedoresPojo.getListaProveedoresClientes();
            CppProveedorCliente cppProveedorCliente;
            for (EntidadArbolPojo entidadArbolPojo : listaArbol) {
                cppProveedorCliente = cppProveedorClienteService.getCppProveedorCliente(entidadArbolPojo.getIdEntidadPojo());                                
//                if(){
//                }
            }
            return cppConceptoProveedoresPojo;
        } catch (Exception e) {
            throw e;
        }
    }
}
