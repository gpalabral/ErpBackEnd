package com.bap.cpanel.servicios.impl.adm;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.cpanel.modelo.adm.AdmPermiso;
import com.bap.cpanel.modelo.pojo.EntidadArbolPojo;
import com.bap.cpanel.modelo.pojo.EntidadPojo;
import com.bap.cpanel.servicios.adm.AdmPermisoService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmPermisoServiceImpl /*extends AbstractJpaDAO<AdmPermiso>*/ implements AdmPermisoService {

    IGenericDao<AdmPermiso> dao;

    @Autowired
    public void setDao(IGenericDao<AdmPermiso> daoToSet) throws Exception {
        try {
            dao = daoToSet;
            dao.setClazz(AdmPermiso.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public AdmPermiso persistAdmPermiso(AdmPermiso admPermiso) throws Exception {
        try {
            System.out.println("test");
            admPermiso.setIdPermiso(null);
            admPermiso.setUsuarioAlta("TEST");
            admPermiso.setFechaAlta(new Date());
            dao.create(admPermiso);
            return admPermiso;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<AdmPermiso> getAdmPermiso() throws Exception {
        try {
            List<AdmPermiso> listaAdmPermiso = dao.find(""
                    + "select b "
                    + "from AdmPermiso b "
                    + "where b.fechaBaja is null "
                    + "order by fechaAlta ASC");
            if (!listaAdmPermiso.isEmpty()) {
                return listaAdmPermiso;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<AdmPermiso> getAdmPermiso(Long idRol) throws Exception {
        try {
            List<AdmPermiso> lista = dao.find(""
                    + "select b "
                    + "from AdmPermiso b "
                    + "where b.fechaBaja is null "
                    + "and b.admRol.idRol = " + idRol + " "
                    + "order by fechaAlta ASC");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<AdmPermiso> getAdmModuloPermiso(Long idModulo) throws Exception {
        try {
            List<AdmPermiso> lista = dao.find(""
                    + "select a "
                    + "from AdmPermiso a "
                    + "where a.admModulo.idModulo = "
                    + idModulo + " and "
                    + "a.fechaBaja is null "
                    + "order by a.fechaAlta ASC");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<AdmPermiso> getAdmUsuarioConRol(Long idUsuario, Long idModulo) throws Exception {
        try {
            List<AdmPermiso> listaAdmUsuarioRol = dao.find(""
                    + "select j "
                    + "from AdmPermiso j "
                    + "where j.fechaBaja is null "
                    + "and j.admRol.idRol in (select a.admRol "
                    + "from AdmUsuarioRol a "
                    + "where a.fechaBaja is null and "
                    + "a.admUsuario.idUsuario = "
                    + idUsuario + ") "
                    + "and j.admModulo.idModulo in (select c "
                    + "from AdmModulo c "
                    + "where c.fechaBaja is null and "
                    + "c.idModulo = "
                    + idModulo + ") "
                    + "order by j.fechaAlta ASC)");
            if (!listaAdmUsuarioRol.isEmpty()) {
                return listaAdmUsuarioRol;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    //   crear entidad pojo Rol Usuario
    public List<EntidadPojo> getListaEntidadPojoByPermiso(Long idUsuario, Long idModulo) throws Exception {
        try {
            List<AdmPermiso> listaPermiso = getAdmUsuarioConRol(idUsuario, idModulo);
            List<EntidadPojo> listaEntidadPojo = new ArrayList<EntidadPojo>();
            EntidadPojo entidadPojo;
            for (AdmPermiso listaPer : listaPermiso) {
                entidadPojo = new EntidadPojo();
                entidadPojo.setIdEntidadPojo(listaPer.getIdPermiso());
                entidadPojo.setDescripcion(listaPer.getDetalle());
                entidadPojo.setMascara("");
                entidadPojo.setTipo("PER");
                listaEntidadPojo.add(entidadPojo);
            }
            if (!listaEntidadPojo.isEmpty()) {
                return listaEntidadPojo;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    //   crear entidad Arbolpojo Rol Usuario
    public List<EntidadArbolPojo> getListaEntidadArbolPojo(Long idUsuario, Long idModulo) throws Exception {
        try {
            List<EntidadArbolPojo> listaEntidadArbolPojo = new ArrayList<EntidadArbolPojo>();
            EntidadArbolPojo entidadArbolPojo;
            List<AdmPermiso> listaPadres = getAdmUsuarioConRol(idUsuario, idModulo);
            List<EntidadPojo> listaAdmPermiso;
            for (AdmPermiso admPermiso : listaPadres) {
                entidadArbolPojo = new EntidadArbolPojo();
                entidadArbolPojo.setIdEntidadArbolPojo(admPermiso.getIdPadre());
                entidadArbolPojo.setDescripcion(admPermiso.getDetalle());
                entidadArbolPojo.setMascara("");
                entidadArbolPojo.setTipo("PAD");
                listaAdmPermiso = getListaEntidadPojoByPermiso(idUsuario, admPermiso.getAdmModulo().getIdModulo());
                if (!listaAdmPermiso.isEmpty()) {
                    entidadArbolPojo.setChildren(listaAdmPermiso);
                }
                listaEntidadArbolPojo.add(entidadArbolPojo);
            }
            if (!listaEntidadArbolPojo.isEmpty()) {
                return listaEntidadArbolPojo;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<String> listAdmPermisosByAdmModulo(Long idUsuario, Long idModulo) throws Exception {
        List<String> permisos = new ArrayList<String>();
        try {
            List<AdmPermiso> listaAdmPermiso = dao.find("select p "
                    + "from AdmPermiso p, AdmUsuarioRol ur "
                    + "where p.admRol.idRol=ur.admRol.idRol "
                    + "and ur.admUsuario.idUsuario=" + idUsuario + " and p.admModulo.idModulo=" + idModulo);

            for (AdmPermiso permiso : listaAdmPermiso) {
                permisos.add(permiso.getCodigo());
            }

            return permisos;
        } catch (Exception e) {
            throw e;
        }

    }
}
