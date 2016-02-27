package com.bap.erp.servicios.impl.cpc;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.enums.EnumTipoMoneda;
import com.bap.erp.modelo.cpc.CpcItem;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import com.bap.erp.modelo.pojo.EntidadPojo;
import com.bap.erp.servicios.cpc.CpcItemService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CpcItemServiceImpl implements CpcItemService {

    IGenericDao<CpcItem> dao;

    @Autowired
    public void setDao(IGenericDao<CpcItem> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CpcItem.class);
    }

    public CpcItem persistCpcItem(CpcItem cpcItem) throws Exception {
        try {
            cpcItem.setIdItem(null);
            cpcItem.setUsuarioAlta("TEST");
            cpcItem.setFechaAlta(new Date());
            dao.create(cpcItem);
            return cpcItem;
        } catch (Exception e) {
            throw e;
        }

    }

    public List<CpcItem> getCpcItem() throws Exception {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<EntidadArbolPojo> getCpcItemArbol() throws Exception {
        try {

            List<EntidadArbolPojo> listaSucursalFinal = new ArrayList<EntidadArbolPojo>();
            List<CpcItem> listaItem = getCpcItem();
            EntidadArbolPojo entidadArbolPojo;
            for (CpcItem cpcItem : listaItem) {
                entidadArbolPojo = new EntidadArbolPojo();
                entidadArbolPojo.setIdEntidadPojo(cpcItem.getIdItem());
                entidadArbolPojo.setDescripcion(cpcItem.getDescripcion());
                entidadArbolPojo.setMascara(cpcItem.getParTipoItem().getDescripcion());
                entidadArbolPojo.setTipo(cpcItem.getParTipoItem().getCodigo());
                entidadArbolPojo.setChildren(new ArrayList<EntidadPojo>());
                listaSucursalFinal.add(entidadArbolPojo);
            }
            return listaSucursalFinal;
        } catch (Exception e) {
            throw e;
        }
    }

    public CpcItem mergeCpcItem(CpcItem cpcItem) throws Exception {
        try {
            cpcItem.setFechaAlta(new Date());
            cpcItem.setUsuarioAlta("SYS");
            cpcItem.setFechaModificacion(new Date());
            cpcItem.setUsuarioModificacion("TEST");
            dao.update(cpcItem);
            return cpcItem;
        } catch (Exception e) {
            throw e;
        }
    }

    public CpcItem cpcItemPorIdItem(Long idItem) throws Exception {
        try {
            return dao.findOne(idItem);
        } catch (Exception e) {
            throw e;
        }

    }

    public BigDecimal calculaMontoDolares(BigDecimal monto, BigDecimal montoDolar, String tipoMoneda) throws Exception {        
        if (tipoMoneda.equals(EnumTipoMoneda.DOLARES.getCodigo())) {
            return monto.divide(montoDolar, 5, BigDecimal.ROUND_HALF_UP);
        } else {
            return monto.multiply(montoDolar);
        }
    }

//    public List<CpcItem> getCpcContratoItem(Long idItem) throws Exception {
//        try {
//            List<CpcItem> lista = find(""
//                    + "select a.cpcItem "
//                    + "from CpcContratoItem a "
//                    + "where a.cpcContrato.idItem = "
//                    + idItem + " and "
//                    + "a.fechaBaja is null "
//                    + "order by a.fechaAlta ASC");                         
//            if (!lista.isEmpty()) {
//                return lista;
//            }
//            return Collections.EMPTY_LIST;
//        } catch (Exception e) {
//            throw e;
//        }
//    }
    public List<CpcItem> getCpcItemByIdContrato(Long idContrato) throws Exception {
        try {
            List<CpcItem> lista = dao.find(""
                    + "select a.cpcItem "
                    + "from CpcContratoItem a "
                    + "where a.cpcContrato.idContrato = "
                    + idContrato + " and "
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

    public List<EntidadArbolPojo> getCpcItemArbolPorCpcContrato(Long idContrato) throws Exception {
        try {
            List<EntidadArbolPojo> listaItemFinal = new ArrayList<EntidadArbolPojo>();
            List<CpcItem> listaItem = getCpcItemByIdContrato(idContrato);
            EntidadArbolPojo entidadArbolPojo;
            for (CpcItem cpcItem1 : listaItem) {
                entidadArbolPojo = new EntidadArbolPojo();
                entidadArbolPojo.setIdEntidadPojo(cpcItem1.getIdItem());
                entidadArbolPojo.setDescripcion(cpcItem1.getDescripcion());
                entidadArbolPojo.setMascara("");
                entidadArbolPojo.setTipo("ITM");
                entidadArbolPojo.setChildren(new ArrayList<EntidadPojo>());
                listaItemFinal.add(entidadArbolPojo);
            }
            return listaItemFinal;
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean verificaSiElCodigoExiste(String codigo) throws Exception {
        try {
            List<CpcItem> lista = dao.find(""
                    + "select j "
                    + "from CpcItem j "
                    + "where j.fechaBaja is null "
                    + "and j.codigo = '" + codigo + "'");
            return !lista.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcItem> getCpcItemList() throws Exception {
        try {
            List<CpcItem> lista = dao.find(""
                    + "select j "
                    + "from CpcItem j "
                    + "where j.fechaBaja is null ");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }
}
