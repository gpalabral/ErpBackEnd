package com.bap.erp.servicios.impl.cpc;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.commons.utils.ObjectUtils;
import com.bap.erp.enums.EnumModalidadFacturacion;
import com.bap.erp.modelo.cpc.CpcDosificacion;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import com.bap.erp.modelo.pojo.EntidadPojo;
import com.bap.erp.servicios.cpc.CpcDosificacionService;
import com.bap.erp.servicios.ErpFacturaService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CpcDosificacionServiceImpl implements CpcDosificacionService {

    IGenericDao<CpcDosificacion> dao;

    @Autowired
    public ErpFacturaService erpFacturaService;

    @Autowired
    public void setDao(IGenericDao<CpcDosificacion> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CpcDosificacion.class);
    }

    public CpcDosificacion persistCpcDosificacion(CpcDosificacion cpcDosificacion) throws Exception {
        try {
            if (cpcDosificacion.getCpcActividadEconomica() != null && cpcDosificacion.getCpcActividadEconomica().getIdActividadEconomica() == null) {
                cpcDosificacion.setCpcActividadEconomica(null);
            }
            cpcDosificacion.setIdDosificacion(null);
            cpcDosificacion.setUsuarioAlta("TEST");
            cpcDosificacion.setFechaAlta(new Date());
            dao.create(cpcDosificacion);
            return cpcDosificacion;
        } catch (Exception e) {
            throw e;
        }
    }

    public CpcDosificacion mergeCpcDosificacion(CpcDosificacion cpcDosificacion) throws Exception {
        try {

            cpcDosificacion.setFechaAlta(new Date());
            cpcDosificacion.setUsuarioAlta("TEST");
            cpcDosificacion.setFechaModificacion(new Date());
            cpcDosificacion.setUsuarioModificacion("TEST");
            dao.update(cpcDosificacion);
            return cpcDosificacion;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcDosificacion> getCpcDosificacion() throws Exception {
        try {
            List<CpcDosificacion> listaCpcDosificacion = dao.find(""
                    + "select b "
                    + "from CpcDosificacion b "
                    + "where b.fechaBaja is null "
                    + "order by b.fechaAlta ASC");
            if (!listaCpcDosificacion.isEmpty()) {
                return listaCpcDosificacion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<EntidadArbolPojo> getCpcDosificacionArbol() throws Exception {
        try {
            List<EntidadArbolPojo> listaDosificacionFinal = new ArrayList<EntidadArbolPojo>();
            List<CpcDosificacion> listaDosificacion = getCpcDosificacion();
            EntidadArbolPojo entidadArbolPojo;
            for (CpcDosificacion cpcDosificacion : listaDosificacion) {
                entidadArbolPojo = new EntidadArbolPojo();
                entidadArbolPojo.setIdEntidadPojo(cpcDosificacion.getIdDosificacion());
                entidadArbolPojo.setDescripcion(cpcDosificacion.getLlaveDosificacion());
                entidadArbolPojo.setMascara("");
                entidadArbolPojo.setTipo("DOS");
                entidadArbolPojo.setChildren(new ArrayList<EntidadPojo>());
                listaDosificacionFinal.add(entidadArbolPojo);
            }
            return listaDosificacionFinal;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcDosificacion> listaCpcDosificacionesPorEstadoyCaracEsp(Long idSucursal, String parEstadoProceso, String parCaracteristicaEspecial) throws Exception {

        try {
            Long numeroFactura;
            List<CpcDosificacion> listafinal = new ArrayList<CpcDosificacion>();
            List<CpcDosificacion> listaCpcDosificacion = dao.find(""
                    + "select b "
                    + "from CpcDosificacion b "
                    + "where b.cpcSucursal.idSucursal = " + idSucursal + " "
                    + "and b.parEstadoProceso.codigo = '" + parEstadoProceso + "' "
                    + "and b.parCaracteristicaEspecial.codigo = '" + parCaracteristicaEspecial + "' "
                    + "and b.fechaBaja is null "
                    + "order by b.fechaAlta ASC");
            if (!listaCpcDosificacion.isEmpty()) {
                for (CpcDosificacion cpcDosificacion1 : listaCpcDosificacion) {
                    numeroFactura = erpFacturaService.generaNumeroFactura(cpcDosificacion1.getIdDosificacion());
                    cpcDosificacion1.setNumeroFacturaActual(numeroFactura);
                    listafinal.add(cpcDosificacion1);
                }
                return listafinal;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<EntidadArbolPojo> getCpcDosificacionArbolPorSucursal(Long idSucursal) throws Exception {
        try {
            List<EntidadArbolPojo> listaDosificacionFinal = new ArrayList<EntidadArbolPojo>();
            List<CpcDosificacion> listaDosificacion = listaCpcDosificacionPorIdSucursal(idSucursal);
            EntidadArbolPojo entidadArbolPojo;
            for (CpcDosificacion cpcDosificacion : listaDosificacion) {
                entidadArbolPojo = new EntidadArbolPojo();
                entidadArbolPojo.setIdEntidadPojo(cpcDosificacion.getIdDosificacion());
                entidadArbolPojo.setDescripcion(cpcDosificacion.getNumeroFacturaInicial().toString());
                entidadArbolPojo.setMascara(cpcDosificacion.getNumeroAutorizacion().toString());
                entidadArbolPojo.setTipo(cpcDosificacion.getParEstadoProceso().getDescripcion());
                entidadArbolPojo.setChildren(new ArrayList<EntidadPojo>());
                listaDosificacionFinal.add(entidadArbolPojo);
            }
            return listaDosificacionFinal;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcDosificacion> listaCpcDosificacionPorIdSucursal(Long idSucursal) throws Exception {
        try {
            List<CpcDosificacion> lista = dao.find(""
                    + "select j "
                    + "from CpcDosificacion j "
                    + "where j.fechaBaja is null "
                    + "and j.cpcSucursal.idSucursal = " + idSucursal + " "
                    + "order by j.parEstadoProceso.codigo ASC");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public CpcDosificacion getCpcDosificacionById(Long idDosificacion) throws Exception {
        try {
            return dao.findOne(idDosificacion);
        } catch (Exception e) {
            throw e;
        }
    }

//    public CpcDosificacion getCpcDosificacionByIdSucursal(Long idSucursal) throws Exception {
//        try {
//            List<CpcDosificacion> list = find(""
//                    + "select a "
//                    + "from CpcDosificacion a "
//                    + "where a.fechaBaja is null "
//                    + "and a.cpcSucursal.idSucursal = " + idSucursal + " "
//                    + "and a.preEstablecido = true");
//            if (!list.isEmpty()) {
//                return list.get(0);
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//        return new CpcDosificacion();
//    }
//    public CpcDosificacion getCpcDosificacionPreEstablecida() throws Exception {
//        try {
//            List<CpcDosificacion> lista = find(""
//                    + "select j "
//                    + "from CpcDosificacion j "
//                    + "where j.fechaBaja is null and "
//                    + "j.cpcSucursal.idSucursal in ("
//                    + "select o.idSucursal "
//                    + "from CpcSucursal o "
//                    + "where o.fechaBaja is null "
//                    + "and o.preEstablecido = true)");
//            if (!lista.isEmpty()) {
//                return lista.get(0);
//            }
//            return new CpcDosificacion();
//        } catch (Exception e) {
//            throw e;
//        }
//    }
    public List<CpcDosificacion> getListaCpcDosificacionByIdDosificacion(Long idSucursal) throws Exception {
        try {
            List<CpcDosificacion> list = dao.find(""
                    + "select h "
                    + "from CpcDosificacion h "
                    + "where h.fechaBaja is null "
                    + "and h.cpcSucursal.idSucursal = " + idSucursal + " "
                    + "order by h.fechaAlta");
            if (!list.isEmpty()) {
                return list;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

//    public Boolean getVerificaExistenciaPreEstablecidoParaDosificacionesByIdSucursal(Long idSucursal) throws Exception {
//        try {
//            List<CpcDosificacion> list = find(""
//                    + "select h "
//                    + "from CpcDosificacion h "
//                    + "where h.fechaBaja is null "
//                    + "and h.cpcSucursal.idSucursal = " + idSucursal + " "
//                    + "and h.preEstablecido=true "
//                    + "order by h.fechaAlta");
//                return !list.isEmpty();
//        } catch (Exception e) {
//            throw e;
//        }
//    }
    public CpcDosificacion getCpcDosificacionesByIdSucursalModalidadFacEstadoProceso(Long idSucursal, String parModalidadFacturacion, String parEstadoProceso) throws Exception {
        try {
            List<CpcDosificacion> lista = dao.find(""
                    + "select a "
                    + "from CpcDosificacion a "
                    + "where a.fechaBaja is null "
                    + "and a.cpcSucursal.idSucursal = " + idSucursal + " "
                    + "and a.parEstadoProceso.codigo = '" + parEstadoProceso + "' "
                    + "and a.parModalidadFacturacion.codigo = '" + parModalidadFacturacion + "'");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return new CpcDosificacion();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcDosificacion> getListaCpcDosificacionByIdSucursalAndCodigoDocMercantil(Long idSucursal, String codigoDocMercantil) throws Exception {
        try {
            List<CpcDosificacion> list = dao.find(""
                    + "select h "
                    + "from CpcDosificacion h "
                    + "where h.fechaBaja is null "
                    + "and h.cpcSucursal.idSucursal = " + idSucursal + " "
                    + "and h.parTipoDocumentoMercantil.codigo = '" + codigoDocMercantil + "' "
                    + "order by h.fechaAlta");
            if (!list.isEmpty()) {
                return list;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcDosificacion> listaDosificacionesVencidas() throws Exception {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
            String fechaActualString = formato.format(new Date());
            List<CpcDosificacion> listaDosificaciones = dao.find(""
                    + "select j "
                    + "from CpcDosificacion j "
                    + "where j.fechaBaja is null "
                    + "and j.parModalidadFacturacion.codigo = '" + EnumModalidadFacturacion.MANUAL.getCodigo() + "' "
                    + "and j.fechaLimiteEmision < '" + fechaActualString + "' ");
            if (!listaDosificaciones.isEmpty()) {
                return listaDosificaciones;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcDosificacion> getCpcDosificacionesPorIdContratoIdSucurEstProcCaracEspModFact(Long idSucursal, Long idContrato, String parEstadoProceso, String parCaracteristicaEspecial, String parModalidadFacturacion) throws Exception {
        try {
            List<CpcDosificacion> listaFinal = new ArrayList<CpcDosificacion>();
            List<CpcDosificacion> lista = dao.find(""
                    + "select j "
                    + "from CpcDosificacion j "
                    + "where j.fechaBaja is null "
                    + "and j.cpcSucursal.idSucursal = " + idSucursal + " "
                    + "and j.parEstadoProceso.codigo = '" + parEstadoProceso + "' "
                    + "and j.parModalidadFacturacion.codigo = '" + parModalidadFacturacion + "' "
                    + "and j.parCaracteristicaEspecial.codigo = '" + parCaracteristicaEspecial + "' "
                    + "and j.cpcActividadEconomica.idActividadEconomica in "
                    + "(select o.cpcActividadEconomica.idActividadEconomica "
                    + "from CpcContratoActividadEconomica o "
                    + "where o.fechaBaja is null "
                    + "and o.cpcContrato.idContrato = '" + idContrato + "') "
                    + "order by j.cpcActividadEconomica.idActividadEconomica asc");
            if (!lista.isEmpty()) {
                for (CpcDosificacion cpcDosificacion : lista) {
                    cpcDosificacion.setNumeroFacturaActual(erpFacturaService.generaNumeroFactura(cpcDosificacion.getIdDosificacion()));
                    listaFinal.add(cpcDosificacion);
                }
                return listaFinal;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public String obtieneConcatenaModalidadFacturacionPorActividadEconomica(Long idActividadEconomica) throws Exception {
        System.out.println("ENTRO M ETODO::::");
        try {
            String cadenaModalidadFacturacion = "";
            List<CpcDosificacion> listaDosificaciones = dao.find(""
                    + "select j "
                    + "from CpcDosificacion j "
                    + "where j.fechaBaja is null "
                    + "and j.cpcActividadEconomica.idActividadEconomica=" + idActividadEconomica);
            if (!listaDosificaciones.isEmpty()) {
                for (CpcDosificacion dosificacion : listaDosificaciones) {
                    System.out.println("ENTRO FOR:"+dosificacion.getParModalidadFacturacion().getDescripcion());
                    System.out.println("STRING:"+cadenaModalidadFacturacion);
                    if (!"".equals(cadenaModalidadFacturacion)) {
                        cadenaModalidadFacturacion = cadenaModalidadFacturacion + "," + dosificacion.getParModalidadFacturacion().getDescripcion();
                    } else {
                        cadenaModalidadFacturacion = dosificacion.getParModalidadFacturacion().getDescripcion();
                    }
                }
            }
            return cadenaModalidadFacturacion;
        } catch (Exception e) {
            throw e;
        }
    }

}
