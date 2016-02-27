package com.bap.cpanel.servicios.impl.adm;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.cpanel.modelo.adm.AdmRol;
import com.bap.cpanel.modelo.adm.AdmUsuario;
import com.bap.cpanel.modelo.pojo.EntidadArbolPojo;
import com.bap.cpanel.modelo.pojo.EntidadPojo;
import com.bap.cpanel.servicios.adm.AdmRolService;
import com.bap.cpanel.servicios.adm.AdmUsuarioService;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdmRolServiceImpl extends AbstractJpaDAO<AdmRol> implements AdmRolService {

    IGenericDao<AdmRol> dao;

    @Autowired
    private AdmUsuarioService admUsuarioService;

    @Autowired
    public void setDao(IGenericDao<AdmRol> daoToSet) {
        dao = daoToSet;
        dao.setClazz(AdmRol.class);
    }

    public AdmRol persistAdmRol(AdmRol admRol) throws Exception {
        try {
            admRol.setIdRol(null);
            admRol.setUsuarioAlta("TEST");
            admRol.setFechaAlta(new Date());
            dao.create(admRol);
            return admRol;
        } catch (Exception e) {
            throw e;
        }

    }

    public List<AdmRol> getAdmRol() throws Exception {
        try {
            List<AdmRol> listaAdmRol = find(""
                    + "select a "
                    + "from AdmRol a "
                    + "where a.fechaBaja is null "
                    + "order by fechaAlta ASC");
            if (!listaAdmRol.isEmpty()) {
                return listaAdmRol;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<AdmRol> getAdmUsuarioRol(Long idUsuario) throws Exception {
        try {
            List<AdmRol> listaAdmUsuarioRol = find(""
                    + "select b.admRol "
                    + "from AdmUsuarioRol b "
                    + "where b.admUsuario.idUsuario = "
                    + idUsuario + " and "
                    + "b.fechaBaja is null "
                    + "order by b.fechaAlta ASC");
            if (!listaAdmUsuarioRol.isEmpty()) {
                return listaAdmUsuarioRol;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

//    crear entidad pojo Rol Usuario
    public List<EntidadPojo> getListaEntidadPojoByUsuario(AdmUsuario admUsuario) throws Exception {
        try {
            List<AdmRol> listaRol = getAdmUsuarioRol(admUsuario.getIdUsuario());
            List<EntidadPojo> listaEntidadPojo = new ArrayList<EntidadPojo>();
            EntidadPojo entidadPojo;
            for (AdmRol admRol : listaRol) {
                entidadPojo = new EntidadPojo();
                entidadPojo.setIdEntidadPojo(admRol.getIdRol());
                entidadPojo.setDescripcion(admRol.getCargo());
                entidadPojo.setMascara("");
                entidadPojo.setTipo("ROL");
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

    //crear entidad Arbolpojo Rol Usuario
    public List<EntidadArbolPojo> getListaEntidadArbolPojo() throws Exception {
        try {
            List<EntidadArbolPojo> listaEntidadArbolPojo = new ArrayList<EntidadArbolPojo>();
            EntidadArbolPojo entidadArbolPojo;
            List<AdmUsuario> listaAdmUsuario = admUsuarioService.getAdmUsuario();
            List<EntidadPojo> listaAdmRol;
            for (AdmUsuario admUsuario : listaAdmUsuario) {
                entidadArbolPojo = new EntidadArbolPojo();
                entidadArbolPojo.setIdEntidadArbolPojo(admUsuario.getIdUsuario());
                entidadArbolPojo.setDescripcion(admUsuario.getLogin());
                entidadArbolPojo.setMascara("");
                entidadArbolPojo.setTipo("USU");
                listaAdmRol = getListaEntidadPojoByUsuario(admUsuario);
                if (!listaAdmRol.isEmpty()) {
                    entidadArbolPojo.setChildren(listaAdmRol);
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

    public AdmRol getAdmRolById(Long idRol) throws Exception {
        try {
            return dao.findOne(idRol);
        } catch (Exception e) {
            throw e;
        }
    }

    public AdmRol getAdmRolByIdUrs(Long idUsuario) throws Exception {
        try {
            List<AdmRol> lista = find(""
                    + "select a.admRol "
                    + "from AdmUsuarioRol a "
                    + "where a.admUsuario.idUsuario = "
                    + idUsuario + " and "
                    + "a.fechaBaja is null "
                    + "order by a.fechaAlta ASC");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return new AdmRol();
        } catch (Exception e) {
            throw e;
        }
    }

    public EntidadArbolPojo getEntidadArbolPojo(Long idUsuario) throws Exception {
        try {
            List<EntidadArbolPojo> listaEntidadArbolPojo = new ArrayList<EntidadArbolPojo>();
            EntidadArbolPojo entidadArbolPojo;
            AdmUsuario listaAdmUsuario = admUsuarioService.getAdmUsuarioById(idUsuario);
            List<EntidadPojo> listaAdmRol;
            entidadArbolPojo = new EntidadArbolPojo();
            entidadArbolPojo.setIdEntidadArbolPojo(listaAdmUsuario.getIdUsuario());
            entidadArbolPojo.setDescripcion(listaAdmUsuario.getLogin());
            entidadArbolPojo.setMascara("");
            entidadArbolPojo.setTipo("USU");
            listaAdmRol = getListaEntidadPojoByUsuario(listaAdmUsuario);
            if (!listaAdmRol.isEmpty()) {
                entidadArbolPojo.setChildren(listaAdmRol);
            }
            listaEntidadArbolPojo.add(entidadArbolPojo);
            if (!listaEntidadArbolPojo.isEmpty()) {
                return listaEntidadArbolPojo.get(0);
            }
            return new EntidadArbolPojo();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<AdmRol> getListaAdmRolByUsuario(AdmUsuario admUsuario) throws Exception {
        try {
            List<AdmRol> list = find(""
                    + "select a.admRol "
                    + "from AdmUsuarioRol a "
                    + "where a.fechaBaja is null "
                    + "and a.admUsuario.idUsuario = " + admUsuario.getIdUsuario() + "");
            if (!list.isEmpty()) {
                return list;
            }
            return list;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<AdmRol> getAdmUsuarioSinRol(Long idUsuario) throws Exception {
        try {
            List<AdmRol> listaSinRol = find(""
                    + "select j "
                    + "from AdmRol j "
                    + "where j.fechaBaja is null "
                    + "and j.idRol not in (select a.admRol "
                    + "from AdmUsuarioRol a "
                    + "where a.admUsuario.idUsuario = "
                    + idUsuario + " and "
                    + "a.admRol.idRol = j.idRol) "
                    + "order by j.fechaAlta ASC");

            if (!listaSinRol.isEmpty()) {
                return listaSinRol;
            }
            return listaSinRol;
        } catch (Exception e) {
            throw e;
        }
    }

//    public List<AdmRol> getAdmRolByIdModulo(Long idModulo) throws Exception {
//        try {
//            List<AdmRol> listaAdmRol = find(""
//                    + "select b.admRol "
//                    + "from AdmRolModulo b "
//                    + "where b.admModulo.idModulo = " + idModulo + " "
//                    + "and b.fechaBaja is null "
//                    + "order by b.fechaAlta ASC");
//            if (!listaAdmRol.isEmpty()) {
//                return listaAdmRol;
//            }
//            return Collections.EMPTY_LIST;
//        } catch (Exception e) {
//            throw e;
//        }
//    }

    public AdmRol mergeAdmRol(AdmRol admRol) throws Exception {
        try {
            admRol.setFechaModificacion(new Date());
            admRol.setUsuarioModificacion("TEST");
            dao.update(admRol);
            return admRol;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean isNumeric(String cadena) throws Exception {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public List<AdmRol> getAdmUsuarioSinRolByIdModulo(Long idUsuario, String idModulo) throws Exception {
        try {
            String consulta = "";
            boolean verifica = isNumeric(idModulo);
            if (verifica) {
                consulta = " and j.idRol in (select c.admRol "
                        + "from AdmPermiso c "
                        + "where c.admModulo.idModulo = "
                        + Long.parseLong(idModulo) + " and "
                        + "c.admRol.idRol = j.idRol)";
            }
            List<AdmRol> listaSinRol = find(""
                    + "select j "
                    + "from AdmRol j "
                    + "where j.fechaBaja is null "
                    + "and j.idRol not in (select a.admRol "
                    + "from AdmUsuarioRol a "
                    + "where a.admUsuario.idUsuario = "
                    + idUsuario + " and "
                    + "a.admRol.idRol = j.idRol)"
                    + consulta + " "
                    + "order by j.fechaAlta ASC");
            if (!listaSinRol.isEmpty()) {
                return listaSinRol;
            }
            return listaSinRol;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<AdmRol> getAdmUsuarioConRolByIdModulo(Long idUsuario, String idModulo) throws Exception {
        try {
            String consulta = "";
            boolean verifica = isNumeric(idModulo);
            if (verifica) {
                consulta = " and j.idRol in (select c.admRol "
                        + "from AdmPermiso c "
                        + "where c.fechaBaja is null and "
                        + "c.admModulo.idModulo = "
                        + Long.parseLong(idModulo) + " and "
                        + "c.admRol.idRol = j.idRol)";
            }
            List<AdmRol> listaAdmUsuarioRol = find(""
                    + "select j "
                    + "from AdmRol j "
                    + "where j.fechaBaja is null "
                    + "and j.idRol in (select a.admRol "
                    + "from AdmUsuarioRol a "
                    + "where a.fechaBaja is null and "
                    + "a.admUsuario.idUsuario = "
                    + idUsuario + " and "
                    + "a.admRol.idRol = j.idRol)"
                    + consulta + " "
                    + "order by j.fechaAlta ASC");
            if (!listaAdmUsuarioRol.isEmpty()) {
                return listaAdmUsuarioRol;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }
}
