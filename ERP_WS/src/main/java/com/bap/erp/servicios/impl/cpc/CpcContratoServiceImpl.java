package com.bap.erp.servicios.impl.cpc;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.commons.utils.ObjectUtils;
import com.bap.erp.enums.EnumEstadoPago;
import com.bap.erp.enums.EnumTipoMoneda;
import com.bap.erp.enums.EnumTipoProveedorCliente;
import com.bap.erp.modelo.cpc.CpcActividadEconomica;
import com.bap.erp.modelo.cpc.CpcContrato;
import com.bap.erp.modelo.cpc.CpcContratoActividadEconomica;
import com.bap.erp.modelo.cpc.CpcContratoItem;
import com.bap.erp.modelo.cpc.CpcPagoContrato;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.par.ParEstadoPago;
import com.bap.erp.modelo.pojo.CpcContratoPojo;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import com.bap.erp.modelo.pojo.EntidadPojo;
import com.bap.erp.servicios.cpc.CpcContratoActividadEconomicaService;
import com.bap.erp.servicios.cpc.CpcContratoService;
import com.bap.erp.servicios.cpc.CpcContratoItemService;
import com.bap.erp.servicios.cpc.CpcPagoContratoService;
import com.bap.erp.servicios.par.ParValorService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CpcContratoServiceImpl implements CpcContratoService {

    IGenericDao<CpcContrato> dao;

    @Autowired
    public CpcContratoItemService cpcContratoItemService;

    @Autowired
    public CpcPagoContratoService cpcPagoContratoService;

    @Autowired
    public ParValorService parValorService;

    @Autowired
    public CpcContratoActividadEconomicaService cpcContratoActividadEconomicaService;

    @Autowired
    public void setDao(IGenericDao<CpcContrato> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CpcContrato.class);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public CpcContrato persistCpcContrato(CpcContrato cpcContrato) throws Exception {
        try {
            cpcContrato.setIdContrato(null);
            cpcContrato.setUsuarioAlta("TEST");
            cpcContrato.setFechaAlta(new Date());
            dao.create(cpcContrato);
            return cpcContrato;
        } catch (Exception e) {
            throw e;
        }

    }

    public List<CpcContrato> getCpcContrato() throws Exception {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcContrato> listaCpcContratoByFecha() throws Exception {
        try {
            List<CpcContrato> listaCpcContrato = dao.find(""
                    + "select b "
                    + "from CpcContrato b "
                    + "where b.fechaBaja is null "
                    + "order by fechaAlta ASC");
            if (!listaCpcContrato.isEmpty()) {
                return listaCpcContrato;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcContrato> listaCpcContratoByEstadoPago() throws Exception {
        try {
            List<CpcContrato> listaCpcContrato = dao.find(""
                    + "select a "
                    + "from CpcContrato a "
                    + "where a.fechaBaja is null "
                    + "and a.idContrato in ("
                    + "select c.cpcContrato.idContrato "
                    + "from CpcPagoContrato c "
                    + "where c.fechaBaja is null "
                    + "and (c.parEstadoPago = '" + EnumEstadoPago.PENDIENTE.getCodigo() + "' "
                    + "or c.parEstadoPago = '" + EnumEstadoPago.MORA.getCodigo() + "')) "
                    + "order by a.fechaAlta ASC");
            if (!listaCpcContrato.isEmpty()) {
                return listaCpcContrato;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public CpcContrato mergeCpcContrato(CpcContrato cpcContrato) throws Exception {
        try {
            cpcContrato.setFechaModificacion(new Date());
            cpcContrato.setUsuarioModificacion("TEST");
            cpcContrato.setFechaAlta(new Date());
            cpcContrato.setUsuarioAlta("TEST");
            dao.update(cpcContrato);
            return cpcContrato;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcContrato> listaContrato() throws Exception {
        try {
            List<CpcContrato> listaCpcContrato = dao.find(""
                    + "select a.cpcContrato "
                    + "from CppProveedorCliente a "
                    + "where a.fechaBaja is null "
                    + "group by a.cpcContrato "
                    + "order by a.fechaAlta ASC");
            if (!listaCpcContrato.isEmpty()) {
                return listaCpcContrato;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public CpcContrato getCpcContratoById(Long idContrato) throws Exception {
        try {
            List<CpcContrato> lista = dao.find(""
                    + "select j "
                    + "from CpcContrato j "
                    + "where j.idContrato = " + idContrato + " "
                    + "and j.fechaBaja is null");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return new CpcContrato();
//            return dao.findOne(idContrato);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<EntidadArbolPojo> getCpcContratoArbol() throws Exception {
        try {
            List<EntidadArbolPojo> listaDosificacionFinal = new ArrayList<EntidadArbolPojo>();
            List<CpcContrato> listaCpcContrato = getCpcContrato();
            EntidadArbolPojo entidadArbolPojo;
            String descripcion = "";
            for (CpcContrato cpcContrato : listaCpcContrato) {
                entidadArbolPojo = new EntidadArbolPojo();
                entidadArbolPojo.setIdEntidadPojo(cpcContrato.getIdContrato());
                if (cpcContrato.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.NATURAL.getCodigo())) {
                    descripcion = cpcContrato.getCppProveedorCliente().getNombre() + " " + cpcContrato.getCppProveedorCliente().getPrimerApellido() + " " + cpcContrato.getCppProveedorCliente().getSegundoApellido();
                } else {
                    if (cpcContrato.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                        descripcion = cpcContrato.getCppProveedorCliente().getRazonSocial();
                    }
                }
                entidadArbolPojo.setDescripcion(descripcion);
                entidadArbolPojo.setMascara(cpcContrato.getCppProveedorCliente().getNumeroDocumento());
                entidadArbolPojo.setTipo("CON");
                entidadArbolPojo.setChildren(new ArrayList<EntidadPojo>());
                listaDosificacionFinal.add(entidadArbolPojo);
            }
            return listaDosificacionFinal;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<EntidadArbolPojo> getCpcContratoArbolFiltro() throws Exception {
        try {
            List<EntidadArbolPojo> listaDosificacionFinal = new ArrayList<EntidadArbolPojo>();
            List<CpcContrato> listaCpcContrato = getCpcContrato();
            EntidadArbolPojo entidadArbolPojo;
            String descripcion = "";
            for (CpcContrato cpcContrato : listaCpcContrato) {
                entidadArbolPojo = new EntidadArbolPojo();
                entidadArbolPojo.setIdEntidadPojo(cpcContrato.getIdContrato());
                if (cpcContrato.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.NATURAL.getCodigo())) {
                    descripcion = cpcContrato.getCppProveedorCliente().getNombre() + " " + cpcContrato.getCppProveedorCliente().getPrimerApellido() + " " + cpcContrato.getCppProveedorCliente().getSegundoApellido();
                } else {
                    if (cpcContrato.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                        descripcion = cpcContrato.getCppProveedorCliente().getRazonSocial();
                    }
                }
                entidadArbolPojo.setDescripcion(descripcion);
                entidadArbolPojo.setMascara(cpcContrato.getNroContrato());
                entidadArbolPojo.setTipo(cpcContrato.getNroContratoCliente());
                entidadArbolPojo.setChildren(new ArrayList<EntidadPojo>());
                listaDosificacionFinal.add(entidadArbolPojo);
            }
            return listaDosificacionFinal;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void guardaContratoPojo(CpcContratoPojo cpcContratoPojo) throws Exception {
        try {
            CpcContrato contrato = cpcContratoPojo.getCpcContrato();
            if (contrato.getIdContrato() != null) {
                contrato = eliminaContrato(contrato);
                mergeCpcContrato(contrato);
            } else {
                contrato = persistCpcContrato(cpcContratoPojo.getCpcContrato());
            }
            ParEstadoPago parEstadoPago = (ParEstadoPago) parValorService.find(ParEstadoPago.class, "PEND");
            for (CpcContratoItem cpcContratoItem : cpcContratoPojo.getListaCpcContratoItem()) {
                cpcContratoItem.setCpcContrato(contrato);
                cpcContratoItem.setCpcItem(cpcContratoItem.getCpcItem());
                cpcContratoItemService.persistCpcContratoItem(cpcContratoItem);
            }
            for (CpcPagoContrato cpcPagoContrato : cpcContratoPojo.getListaCpcPagoContrato()) {
                cpcPagoContrato.setCpcContrato(contrato);
                cpcPagoContrato.setParEstadoPago(parEstadoPago);
                cpcPagoContratoService.persistCpcPagoContrato(cpcPagoContrato);
            }
            if (!cpcContratoPojo.getListaCpcActividadEconomica().isEmpty()) {
                CpcContratoActividadEconomica cpcContratoActividadEconomica;
                for (CpcActividadEconomica cpcActividadEconomica : cpcContratoPojo.getListaCpcActividadEconomica()) {
                    cpcContratoActividadEconomica = new CpcContratoActividadEconomica();
                    cpcContratoActividadEconomica.setCpcContrato(contrato);
                    cpcContratoActividadEconomica.setCpcActividadEconomica(cpcActividadEconomica);
                    cpcContratoActividadEconomicaService.persistCpcContratoActividadEconomica(cpcContratoActividadEconomica);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public CpcContrato eliminaContrato(CpcContrato cpcContrato) throws Exception {
        try {
            List<CpcContratoActividadEconomica> listaActividadEconomica = cpcContratoActividadEconomicaService.getCpcContratoActividadEconomicaByIdContrato(cpcContrato.getIdContrato());
            for (CpcContratoActividadEconomica listaActividadEconomica1 : listaActividadEconomica) {
                cpcContratoActividadEconomicaService.deleteCpcContratoActividadEconomica(listaActividadEconomica1);
            }
            List<CpcContratoItem> listaContratoItem = cpcContratoItemService.getCpcContratoItemByIdContrato(cpcContrato.getIdContrato());
            for (CpcContratoItem listaContratoItem1 : listaContratoItem) {
                cpcContratoItemService.deleteCpcContratoItem(listaContratoItem1);
            }
            List<CpcPagoContrato> listaPagoContrato = cpcPagoContratoService.getCpcPagoContratoByIdContrato(cpcContrato.getIdContrato());
            for (CpcPagoContrato listaPagoContrato1 : listaPagoContrato) {
                cpcPagoContratoService.deleteCpcPagoContrato(listaPagoContrato1);
            }
            return cpcContrato;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcContrato> getCpcContratoByFecha(int month) throws Exception {
        try {
            List<CpcContrato> listaCpcContrato = dao.find(""
                    + "select a "
                    + "from CpcContrato a "
                    + "where a.fechaBaja is null "
                    + "and MONTH(a.fechaContrato) = " + month + " "
                    + "order by a.fechaAlta ASC");
            if (!listaCpcContrato.isEmpty()) {
                return listaCpcContrato;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<EntidadArbolPojo> getCpcContratoProveedorClienteArbol() throws Exception {
        try {
            List<EntidadArbolPojo> listaFinal = new ArrayList<EntidadArbolPojo>();
            List<CpcContrato> listaCpcContrato = listaCpcContratoByEstadoPago();
            EntidadArbolPojo entidadArbolPojo;
            String descripcion = "";
            for (CpcContrato cpcContrato : listaCpcContrato) {
                entidadArbolPojo = new EntidadArbolPojo();
                entidadArbolPojo.setIdEntidadPojo(cpcContrato.getIdContrato());
                entidadArbolPojo.setDescripcion(cpcContrato.getNroContrato());
                entidadArbolPojo.setMascara(cpcContrato.getNroContratoCliente());
                if (cpcContrato.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.NATURAL.getCodigo())) {
                    descripcion = cpcContrato.getCppProveedorCliente().getNombre() + " " + cpcContrato.getCppProveedorCliente().getPrimerApellido() + " " + cpcContrato.getCppProveedorCliente().getSegundoApellido();
                } else {
                    if (cpcContrato.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                        descripcion = cpcContrato.getCppProveedorCliente().getRazonSocial();
                    }
                }
                entidadArbolPojo.setTipo(descripcion);
                entidadArbolPojo.setChildren(new ArrayList<EntidadPojo>());
                listaFinal.add(entidadArbolPojo);
            }
            return listaFinal;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcContrato> getCpcContratoItemByTipoItem(String parTipoItem) throws Exception {
        try {
            List<CpcContrato> listaCpcContratoItem = dao.find(""
                    + "select b.cpcContrato "
                    + "from CpcContratoItem b "
                    + "where b.cpcItem.parTipoItem.codigo ='" + parTipoItem + "'"
                    + "and b.fechaBaja is null "
                    + "order by b.fechaAlta ASC");
            if (!listaCpcContratoItem.isEmpty()) {
                return listaCpcContratoItem;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void deleteCpcContrato(CpcContrato cpcContrato) throws Exception {
        try {
            dao.deleteById(cpcContrato.getIdContrato());
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcContrato> getCpcContratoByCppProveedorCliente(CppProveedorCliente cppProveedorCliente) throws Exception {
        try {
            List<CpcContrato> listaContratos = dao.find(""
                    + "select j "
                    + "from CpcContrato j "
                    + "where j.fechaBaja is null "
                    + "and j.cppProveedorCliente.idProveedorCliente = " + cppProveedorCliente.getIdProveedorCliente() + "");
            if(!listaContratos.isEmpty()){
                return listaContratos;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

}
