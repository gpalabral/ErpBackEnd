package com.bap.erp.servicios.impl.cpc;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.modelo.cpc.CpcSucursal;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import com.bap.erp.modelo.pojo.EntidadPojo;
import com.bap.erp.servicios.cpc.CpcSucursalService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CpcSucursalServiceImpl extends AbstractJpaDAO<CpcSucursal> implements CpcSucursalService {

    IGenericDao<CpcSucursal> dao;

    @Autowired
    public void setDao(IGenericDao<CpcSucursal> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CpcSucursal.class);
    }

    public CpcSucursal persistCpcSucursal(CpcSucursal cpcSucursal) {
        cpcSucursal.setIdSucursal(null);
        cpcSucursal.setUsuarioAlta("TEST");
        cpcSucursal.setFechaAlta(new Date());
        dao.create(cpcSucursal);
        return cpcSucursal;
    }

    public CpcSucursal mergeCpcSucursal(CpcSucursal cpcSucursal) throws Exception {
        try {
            cpcSucursal.setFechaAlta(new Date());
            cpcSucursal.setUsuarioAlta("TEST");
            cpcSucursal.setFechaModificacion(new Date());
            cpcSucursal.setUsuarioModificacion("TEST");
            dao.update(cpcSucursal);
            return cpcSucursal;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcSucursal> getCpcSucursal() throws Exception {
        try {

            List<CpcSucursal> listaCpcSucursal = find(""
                    + "select b "
                    + "from CpcSucursal b "
                    + "where b.fechaBaja is null "
                    + "order by b.idSucursal ASC");
//              + "order by b.fechaAlta ASC");
            if (!listaCpcSucursal.isEmpty()) {
                return listaCpcSucursal;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<EntidadArbolPojo> getCpcSucursalArbol() throws Exception {
        try {

            List<EntidadArbolPojo> listaSucursalFinal = new ArrayList<EntidadArbolPojo>();
            List<CpcSucursal> listaSucursales = getCpcSucursal();
            EntidadArbolPojo entidadArbolPojo;
            for (CpcSucursal cpcSucursalObject : listaSucursales) {
                entidadArbolPojo = new EntidadArbolPojo();
                entidadArbolPojo.setIdEntidadPojo(cpcSucursalObject.getIdSucursal());
                entidadArbolPojo.setDescripcion(cpcSucursalObject.getDescripcion());
                entidadArbolPojo.setMascara("");
                entidadArbolPojo.setTipo("SUC");
                entidadArbolPojo.setChildren(new ArrayList<EntidadPojo>());
                listaSucursalFinal.add(entidadArbolPojo);
            }
            return listaSucursalFinal;
        } catch (Exception e) {
            throw e;
        }
    }

    public Long generaNumeroSucursal() throws Exception {
        try {
            List<CpcSucursal> listaSucursales = find(""
                    + "select j "
                    + "from CpcSucursal j "
                    + "where j.fechaBaja is null "
                    + "order by j.numeroSucursal desc");
            if (listaSucursales.isEmpty()) {
                return 0L;
            }
            return listaSucursales.get(0).getNumeroSucursal() + 1;
        } catch (Exception e) {
            throw e;
        }

    }

    public CpcSucursal getCpcSucursalByIdSucursal(Long idSucursal) throws Exception {
        try {
            return dao.findOne(idSucursal);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcSucursal> getSucursalesFiltradoFactura(String caracteristicaEspecial, String estadoProceso) throws Exception {
        try {
            List<CpcSucursal> lista = find(""
                    + "select j "
                    + "from CpcSucursal j "
                    + "where j.fechaBaja is null "
                    + "and j.emiteFactura = true "
                    + "and j.idSucursal in (select o.cpcSucursal.idSucursal "
                    + "from CpcDosificacion o "
                    + "where o.fechaBaja is null "
                    + "and o.parCaracteristicaEspecial.codigo = '" + caracteristicaEspecial + "' "
                    + "and o.parEstadoProceso.codigo = '" + estadoProceso + "')");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public CpcSucursal sucursalPorNumeroSucursal(Long numero_sucursal) throws Exception {
        try {
            List<CpcSucursal> listaSucursales = find(""
                    + "select a "
                    + "from CpcSucursal a "
                    + "where a.fechaBaja is null "
                    + "and a.numeroSucursal =" + numero_sucursal + " "
                    + "order by a.numeroSucursal desc");
            if (!listaSucursales.isEmpty()) {
                return listaSucursales.get(0);
            }
            return new CpcSucursal();
        } catch (Exception e) {
            throw e;
        }

    }
    
//    public CpcSucursal getCpcDosificacionPreEstablecido() throws Exception {
//        try {
//            List<CpcSucursal> list = find(""
//                    + "select a "
//                    + "from CpcSucursal a "
//                    + "where a.fechaBaja is null "
//                    + "and a.preEstablecido = true");
//            if (!list.isEmpty()) {
//                return list.get(0);
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//        return new CpcSucursal();
//    }
    
}
