package com.bap.erp.servicios.impl.cpp;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.modelo.cpp.CppConcepto;
import com.bap.erp.modelo.cpp.CppGrupo;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.pojo.Entidad;
import com.bap.erp.modelo.pojo.EntidadPojo;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import com.bap.erp.servicios.cpp.CppConceptoService;
import com.bap.erp.servicios.cpp.CppGrupoService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CppGrupoServiceImpl /*extends AbstractJpaDAO<CppGrupo>*/ implements CppGrupoService {
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CppGrupoServiceImpl.class);

    IGenericDao<CppGrupo> dao;

    @Autowired
    public CppConceptoService cppConceptoService;

    @Autowired
    public void setDao(IGenericDao<CppGrupo> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CppGrupo.class);
    }

    public CppGrupo persistCppGrupo(CppGrupo cppGrupo) {

        cppGrupo.setIdGrupo(null);
        //cppGrupo.setFechaAlta(new Date());
        //cppGrupo.setUsuarioAlta("TEST");
        log.info("Creando:::: "+cppGrupo);
        dao.create(cppGrupo);
        return cppGrupo;
    }

    public CppGrupo mergeCppGrupo(CppGrupo cppGrupo) {
        
        CppGrupo grupoFromDB=dao.findOne(cppGrupo.getIdGrupo());
        
        cppGrupo.setFechaAlta(grupoFromDB.getFechaAlta());
        cppGrupo.setUsuarioAlta(grupoFromDB.getUsuarioAlta());
        
        cppGrupo.setFechaModificacion(new Date());
        cppGrupo.setUsuarioModificacion("TEST");
        log.info("Modificando:: "+cppGrupo);
        dao.update(cppGrupo);
        return cppGrupo;
    }

    public void removeCppGrupo(Long idCppGrupo)throws Exception{
        try {           
            log.info("Eliminando::: "+idCppGrupo);
            dao.delete(getCppGrupo(idCppGrupo));
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CppGrupo> getCppGrupo() {
        return dao.findAll();
    }

    public CppGrupo getCppGrupo(Long id) {
        return dao.findOne(id);
    }   

    public List<CppGrupo> getListaGrupos() {
        List<CppGrupo> listaGrupos = dao.find(""
                + "select j "
                + "from CppGrupo j "
                + "where j.fechaBaja is null "
                + "order by fechaAlta ASC");
        if (!listaGrupos.isEmpty()) {
            return listaGrupos;
        }
        return Collections.EMPTY_LIST;
    }

    public List<Entidad> getListaGruposYConceptos() {
        List<Entidad> listaOrdenada = new ArrayList<Entidad>();
        List<CppConcepto> listaConceptos = cppConceptoService.getListaCppConceptoOrdenadosPorGrupo();
        Entidad entidadGrupo = new Entidad();
        Entidad entidadConcepto = new Entidad();
        entidadGrupo.setIdEntidad(0L);
        for (CppConcepto cppConcepto : listaConceptos) {
            entidadConcepto = new Entidad();
            if (!entidadGrupo.getIdEntidad().equals(cppConcepto.getCppGrupo().getIdGrupo())) {
                entidadGrupo = new Entidad();
                entidadGrupo.setIdEntidad(cppConcepto.getCppGrupo().getIdGrupo());
                entidadGrupo.setIdEntidadPadre(0L);
                entidadGrupo.setDescripcion(cppConcepto.getCppGrupo().getNombre());
                entidadGrupo.setNivel(1);
                entidadGrupo.setMascaraGenerada("");
                listaOrdenada.add(entidadGrupo);
            }
            entidadConcepto.setIdEntidad(cppConcepto.getIdConcepto());
            entidadConcepto.setIdEntidadPadre(cppConcepto.getCppGrupo().getIdGrupo());
            entidadConcepto.setDescripcion(cppConcepto.getDescripcion());
            entidadConcepto.setNivel(2);
            entidadConcepto.setMascaraGenerada("");
            listaOrdenada.add(entidadConcepto);
        }
        return listaOrdenada;
    }

    public List<EntidadArbolPojo> getListaEntidadArbolPojo() {
        List<EntidadArbolPojo> listaEntidadArbolPojo = new ArrayList<EntidadArbolPojo>();
        EntidadArbolPojo entidadArbolPojo = new EntidadArbolPojo();
        List<CppGrupo> listaGrupos = getListaGrupos();
        EntidadPojo grupo;
        List<EntidadPojo> listaConcepto;
        for (CppGrupo cppGrupo : listaGrupos) {
            entidadArbolPojo = new EntidadArbolPojo();
            entidadArbolPojo.setIdEntidadPojo(cppGrupo.getIdGrupo());
            entidadArbolPojo.setDescripcion(cppGrupo.getNombre());
            entidadArbolPojo.setMascara(cppGrupo.getParRecurrencia().getDescripcion());
            entidadArbolPojo.setTipo("GRU");
            listaConcepto = cppConceptoService.getListaEntidadPojoByGrupo(cppGrupo);
            if (!listaConcepto.isEmpty()) {
                entidadArbolPojo.setChildren(listaConcepto);
            }
            listaEntidadArbolPojo.add(entidadArbolPojo);
        }
        if (!listaEntidadArbolPojo.isEmpty()) {
            return listaEntidadArbolPojo;
        }
        return Collections.EMPTY_LIST;
    }

    public List<CppGrupo> getListaGruposPorProveedorCliente(CppProveedorCliente cppProveedorCliente) {
        List<CppGrupo> listaGrupos = dao.find(""
                + "select distinct j.cppConcepto.cppGrupo "
                + "from CppProveedorClienteConcepto j "
                + "where j.fechaBaja is null "
                + "and j.cppProveedorCliente.idProveedorCliente = " + cppProveedorCliente.getIdProveedorCliente() + "");
        if (!listaGrupos.isEmpty()) {
            return listaGrupos;
        }
        return Collections.EMPTY_LIST;
    }

    public List<EntidadArbolPojo> getGruposConConceptosAsignados() {
        List<EntidadArbolPojo> listaEntidadArbolPojo = new ArrayList<EntidadArbolPojo>();
        EntidadArbolPojo entidadArbolPojo = new EntidadArbolPojo();
        List<CppGrupo> listaGrupos = getListaGruposConConceptos();
        EntidadPojo grupo;
        List<EntidadPojo> listaConcepto;
        for (CppGrupo cppGrupo : listaGrupos) {
            entidadArbolPojo = new EntidadArbolPojo();
            entidadArbolPojo.setIdEntidadPojo(cppGrupo.getIdGrupo());
            entidadArbolPojo.setDescripcion(cppGrupo.getNombre());
            entidadArbolPojo.setMascara(cppGrupo.getParRecurrencia().getDescripcion());
            entidadArbolPojo.setTipo("GRU");
            listaConcepto = cppConceptoService.getListaEntidadPojoByGrupo(cppGrupo);
            if (!listaConcepto.isEmpty()) {
                entidadArbolPojo.setChildren(listaConcepto);
            }
            listaEntidadArbolPojo.add(entidadArbolPojo);
        }
        if (!listaEntidadArbolPojo.isEmpty()) {
            return listaEntidadArbolPojo;
        }
        return Collections.EMPTY_LIST;
    }

    public List<CppGrupo> getListaGruposConConceptos() {        
        List<CppGrupo> listaGrupos = dao.find(""
                + "select j "
                + "from CppGrupo j "
                + "where j.fechaBaja is null "
                + "and j.idGrupo in ("
                + "select o.cppGrupo.idGrupo "
                + "from CppConcepto o "
                + "where o.fechaBaja is null) "
                + "order by j.nombre asc");
        if(!listaGrupos.isEmpty()){
            return listaGrupos;
        }
        return Collections.EMPTY_LIST;
    }

    public void bajaCppGrupo(CppGrupo cppGrupo) throws Exception {
        try {
//            CppGrupo cppGrupo = getCppGrupo(idCppGrupo);
            cppGrupo.setFechaBaja(new Date());
            cppGrupo.setUsuarioBaja("TEST");
            dao.update(cppGrupo);            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
