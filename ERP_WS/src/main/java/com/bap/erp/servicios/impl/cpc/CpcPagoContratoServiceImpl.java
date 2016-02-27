/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.servicios.impl.cpc;

import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.commons.utils.DateUtils;
import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.enums.EnumColorView;
import com.bap.erp.enums.EnumEstadoPago;
import com.bap.erp.enums.EnumTipoProveedorCliente;
import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.modelo.cpc.CpcPagoContrato;
import com.bap.erp.modelo.par.ParEstadoPago;
import com.bap.erp.modelo.pojo.CpcPagoContratoPojo;
import com.bap.erp.servicios.ErpFacturaService;
import com.bap.erp.servicios.cpc.CpcPagoContratoService;
import com.bap.erp.servicios.par.ParValorService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Javier
 */
@Service
//public class CpcPagoContratoServiceImpl extends AbstractJpaDAO<CpcPagoContrato> implements CpcPagoContratoService {
public class CpcPagoContratoServiceImpl extends AbstractJpaDAO<CpcPagoContrato> implements CpcPagoContratoService {

    IGenericDao<CpcPagoContrato> dao;

    @Autowired
    public ErpFacturaService cpcFacturaEmitidaservice;

    @Autowired
    public ParValorService parValorService;

    @Autowired
    public void setDao(IGenericDao<CpcPagoContrato> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CpcPagoContrato.class);
    }

    public CpcPagoContrato persistCpcPagoContrato(CpcPagoContrato cpcPagoContrato) throws Exception {
        try {
            cpcPagoContrato.setIdPagoContrato(null);
            cpcPagoContrato.setFechaAlta(new Date());
            cpcPagoContrato.setUsuarioAlta("TEST");
            dao.create(cpcPagoContrato);
            return cpcPagoContrato;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcPagoContrato> getCpcPagoContrato() {
        return dao.findAll();
    }

    public List<CpcPagoContratoPojo> listaCpcPagoContratoByFechaProgramadaEstadoPago(String parEstadoPago) throws Exception {
        try {
            List<CpcPagoContratoPojo> lista = new ArrayList<CpcPagoContratoPojo>();
            CpcPagoContratoPojo cpcPagoContratoPojo;
            List<CpcPagoContrato> listaCpcPagoContrato = find(""
                    + "select b "
                    + "from CpcPagoContrato b "
                    + "where b.fechaBaja is null "
                    + "and b.parEstadoPago.codigo = '" + parEstadoPago + "' "
                    + "order by b.fechaProgramada, b.fechaAlta ASC");
            if (!listaCpcPagoContrato.isEmpty()) {
                for (CpcPagoContrato cpcPagoContrato : listaCpcPagoContrato) {
                    cpcPagoContratoPojo = new CpcPagoContratoPojo();
                    cpcPagoContratoPojo.setCpcPagoContrato(cpcPagoContrato);
                    if (cpcPagoContrato.getCpcContrato().getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                        cpcPagoContratoPojo.setNombre(cpcPagoContrato.getCpcContrato().getCppProveedorCliente().getRazonSocial());
                    } else {
                        cpcPagoContratoPojo.setNombre(cpcPagoContrato.getCpcContrato().getCppProveedorCliente().getPrimerApellido() + " " + cpcPagoContrato.getCpcContrato().getCppProveedorCliente().getSegundoApellido() + " " + cpcPagoContrato.getCpcContrato().getCppProveedorCliente().getNombre());
                    }
                    lista.add(cpcPagoContratoPojo);
                }
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public CpcPagoContrato getCpcPagoContratoById(Long idPagoContrato) throws Exception {
        try {
            return dao.findOne(idPagoContrato);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcPagoContrato> getCpcPagoContratoByIdContrato(Long idContrato) throws Exception {
        try {
            List<CpcPagoContrato> lista = find(""
                    + "select a "
                    + "from CpcPagoContrato a "
                    + "where a.fechaBaja is null "
                    + "and a.cpcContrato.idContrato = '" + idContrato + "' "
                    + "order by a.nroPago ASC");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcPagoContrato> getCpcPagoContratoIdContratoByEstadoPago(Long idContrato, String parEstadoPago) throws Exception {
        try {
            List<CpcPagoContrato> lista = find(""
                    + "select a "
                    + "from CpcPagoContrato a "
                    + "where a.cpcContrato.idContrato = " + idContrato + " "
                    + "and a.parEstadoPago.codigo = '" + parEstadoPago + "' "
                    + "and a.fechaBaja is null "
                    + "order by a.nroPago ASC");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public CpcPagoContrato mergeCpcPagoContrato(CpcPagoContrato cpcPagoContrato) throws Exception {
        try {

            cpcPagoContrato.setFechaAlta(new Date());
            cpcPagoContrato.setUsuarioAlta("TEST");
            cpcPagoContrato.setUsuarioModificacion("SYS");
            cpcPagoContrato.setFechaModificacion(new Date());
            dao.update(cpcPagoContrato);
            return cpcPagoContrato;
        } catch (Exception e) {
            throw e;
        }
    }

    public CpcPagoContratoPojo listaCpcPagoContrato(Long idPagoContrato) throws Exception {
        try {
            CpcPagoContratoPojo cpcPagoContratoPojo;
            CpcPagoContrato cpcPagoContrato = getCpcPagoContratoById(idPagoContrato);
            cpcPagoContratoPojo = new CpcPagoContratoPojo();
            cpcPagoContratoPojo.setCpcPagoContrato(cpcPagoContrato);
            if (cpcPagoContrato.getCpcContrato().getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                cpcPagoContratoPojo.setNombre(cpcPagoContrato.getCpcContrato().getCppProveedorCliente().getRazonSocial());
            } else {
                cpcPagoContratoPojo.setNombre(cpcPagoContrato.getCpcContrato().getCppProveedorCliente().getPrimerApellido() + " " + cpcPagoContrato.getCpcContrato().getCppProveedorCliente().getSegundoApellido() + " " + cpcPagoContrato.getCpcContrato().getCppProveedorCliente().getNombre());
            }
            return cpcPagoContratoPojo;
        } catch (Exception e) {
            throw e;
        }
    }

    public Long generaNroPago(Long idContrato) throws Exception {
        try {
            List<CpcPagoContrato> listaPagoContrato = find(""
                    + "select b "
                    + "from CpcPagoContrato b "
                    + "where b.fechaBaja is null "
                    + "and b.cpcContrato.idContrato = " + idContrato + " "
                    + "order by b.nroPago DESC");
            if (listaPagoContrato.isEmpty()) {
                return 1L;
            }
            return listaPagoContrato.get(0).getNroPago() + 1;
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean verificaTiempoDisponible(CpcPagoContrato cpcPagoContrato) throws Exception {
        try {
            CpcPagoContrato cpcPagoContratoBD = getCpcPagoContratoById(cpcPagoContrato.getIdPagoContrato());
            Date fechaBD = cpcPagoContratoBD.getFechaProgramada();
            Date fechaIntroducida = cpcPagoContrato.getFechaProgramada();
            Long resulta = fechaIntroducida.getTime() - fechaBD.getTime();
            Long dias = resulta / (1000 * 60 * 60 * 24);
            return dias <= 15L;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void removeCpcPagoContrato(Long idPagoContrato) throws Exception {
        try {
            CpcPagoContrato cpcPagoContrato = getCpcPagoContratoById(idPagoContrato);
            cpcPagoContrato.setFechaBaja(new Date());
            cpcPagoContrato.setUsuarioBaja("TEST");
            dao.update(cpcPagoContrato);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public CpcPagoContrato cambiaCpcPagoContrato(CpcPagoContrato cpcPagoContrato) throws Exception {
        try {
            removeCpcPagoContrato(cpcPagoContrato.getIdPagoContrato());
            cpcPagoContrato.setIdPagoContrato(null);
            persistCpcPagoContrato(cpcPagoContrato);
            return cpcPagoContrato;
        } catch (Exception e) {
            throw e;
        }
    }

    public void actualizaPagosContratoEnMora() throws Exception {
        try {
            Date fechaActual = new Date();
            ParEstadoPago parEstadoPago = (ParEstadoPago) parValorService.find(ParEstadoPago.class, EnumEstadoPago.MORA.getCodigo());
            List<CpcPagoContrato> listaPagosPendientes = find(""
                    + "select j "
                    + "from CpcPagoContrato j "
                    + "where j.fechaBaja is null "
                    + "and j.parEstadoPago.codigo = '" + EnumEstadoPago.PENDIENTE.getCodigo() + "'");
            if (!listaPagosPendientes.isEmpty()) {
                for (CpcPagoContrato cpcPagoContrato : listaPagosPendientes) {
                    if (fechaActual.after(fechaInicialMasDia(cpcPagoContrato.getFechaProgramada()))) {
                        cpcPagoContrato.setParEstadoPago(parEstadoPago);
                        mergeCpcPagoContrato(cpcPagoContrato);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    Date fechaInicialMasDia(Date fecha) throws Exception {
        try {
            System.out.println("el año:::" + fecha.getYear());
            System.out.println("el mes:::" + fecha.getMonth());
            System.out.println("el dia:::" + fecha.getDate());
            System.out.println("el dia2:::" + fecha.getDay());
            fecha = DateUtils.sumaDias(fecha, 1);
            fecha = new Date(fecha.getYear(), fecha.getMonth(), fecha.getDate());
            System.out.println("la fecha es:::" + fecha);
            return fecha;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcPagoContrato> getListaPagoContratoPendienteByDias(Long idContrato, Integer cantidadDias) throws Exception {
        try {
            Date fechaActual = new Date();
            Date fechaFinal = DateUtils.sumaDias(fechaActual, cantidadDias);
            SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
            String fechaActualString = formato.format(fechaActual);
            String fechaFinalString = formato.format(fechaFinal);
            System.out.println("fecha actual.- " + fechaActualString);
            System.out.println("fecha final.- " + fechaFinalString);
            List<CpcPagoContrato> listaPagosContrato = find(""
                    + "select a "
                    + "from CpcPagoContrato a "
                    + "where a.fechaBaja is null "
                    + "and a.cpcContrato.idContrato = " + idContrato + " "
                    + "and a.parEstadoPago.codigo = '" + EnumEstadoPago.PENDIENTE.getCodigo() + "'"
                    + "and a.fechaProgramada >= '" + fechaActualString + "' "
                    + "and a.fechaProgramada <= '" + fechaFinalString + "'");
            if (!listaPagosContrato.isEmpty()) {
                return listaPagosContrato;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcPagoContratoPojo> getPagoContratoPendientePojo(Long idContrato, Integer cantidadDias) throws Exception {
        try {
            String color = EnumColorView.COLOR_PAGOS_PENDIENTES_POR_FECHA_PROGRAMADA.getCodigo();
            List<CpcPagoContratoPojo> lista = new ArrayList<CpcPagoContratoPojo>();
            CpcPagoContratoPojo cpcPagoContratoPojo;
            List<CpcPagoContrato> cpcPagoContrato = getListaPagoContratoPend(idContrato, cantidadDias);
            cpcPagoContratoPojo = new CpcPagoContratoPojo();
            for (CpcPagoContrato ListacpcPagoContrato : cpcPagoContrato) {
                cpcPagoContratoPojo.setCpcPagoContrato(ListacpcPagoContrato);
                cpcPagoContratoPojo.setNombre(color);
                lista.add(cpcPagoContratoPojo);
            }
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    public CpcPagoContrato getCpcPagoContratoCpcFarturaEmitida(Long idPagoContrato, BigDecimal montoSum) throws Exception {
        try {
            List<CpcPagoContrato> lista = find(""
                    + "select a "
                    + "from CpcPagoContrato a "
                    + "where a.fechaBaja is null "
                    + "and a.idPagoContrato = " + idPagoContrato + " "
                    + "and a.montoProgramado >= " + montoSum + " "
                    + "order by a.fechaAlta ASC");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return new CpcPagoContrato();
        } catch (Exception e) {
            throw e;
        }
    }
//    public CpcPagoContrato getCpcPagoContratoCpcFarturaEmitida(Long idPagoContrato, Float montoSum) throws Exception {
//        try {
//            List<CpcPagoContrato> lista = find(""
//                    + "select a "
//                    + "from CpcPagoContrato a "
//                    + "where a.fechaBaja is null "
//                    + "and a.idPagoContrato = " + idPagoContrato + " "
//                    + "and a.montoProgramado >= " + montoSum + " "
//                    + "order by a.fechaAlta ASC");
//            if (!lista.isEmpty()) {
//                return lista.get(0);
//            }
//            return new CpcPagoContrato();
//        } catch (Exception e) {
//            throw e;
//        }
//    }

    public List<CpcPagoContrato> getListaPagoContratoPend(Long idContrato, Integer cantidadDias) throws Exception {
        try {
            Date fechaActual = new Date();
            Date fechaFinal = DateUtils.sumaDias(fechaActual, cantidadDias);
            SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
            String fechaActualString = formato.format(fechaActual);
            String fechaFinalString = formato.format(fechaFinal);
            List<CpcPagoContrato> listaPagosContrato = find(""
                    + "select a "
                    + "from CpcPagoContrato a "
                    + "where a.fechaBaja is null "
                    + "and a.cpcContrato.idContrato = " + idContrato + " "
                    + "and a.parEstadoPago.codigo = '" + EnumEstadoPago.PENDIENTE.getCodigo() + "'"
                    + "and a.fechaProgramada >= '" + fechaActualString + "' "
                    + "and a.fechaProgramada <= '" + fechaFinalString + "'");
//            Float montoSum;
            BigDecimal montoSum = BigDecimal.ZERO;
            List<CpcPagoContrato> lista2 = new ArrayList<CpcPagoContrato>();
            for (CpcPagoContrato cpcPagoContrato : listaPagosContrato) {
                montoSum = cpcFacturaEmitidaservice.getSumaFacturaEmitidasByIdPagoContrato(cpcPagoContrato.getIdPagoContrato());
                CpcPagoContrato cpcPagoContratoCpcFecEmi = getCpcPagoContratoCpcFarturaEmitida(cpcPagoContrato.getIdPagoContrato(), montoSum);
                System.out.println("montoTotal.- " + cpcPagoContratoCpcFecEmi.getMontoPagadoPrimeraMoneda());
                lista2.add(cpcPagoContratoCpcFecEmi);
            }
            if (!lista2.isEmpty()) {
                return lista2;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean verificaSiElPagoEsCompleto(Long idPagoContrato) throws Exception {
        try {
            List<ErpFactura> lista = cpcFacturaEmitidaservice.getCpcFacturaEmitidaByIdPagoContratoAndEstado(idPagoContrato, EnumEstadoPago.PENDIENTE.getCodigo());
            return lista.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcPagoContrato> getCobrosPorFacturarPorContrato(Long idContrato) throws Exception {
        try {
            List<CpcPagoContrato> lista = find(""
                    + "select j "
                    + "from CpcPagoContrato j "
                    + "where j.cpcContrato.idContrato = " + idContrato + " "
                    + "and (j.parEstadoPago.codigo = '" + EnumEstadoPago.PENDIENTE.getCodigo() + "' "
                    + "or j.parEstadoPago.codigo = '" + EnumEstadoPago.MORA.getCodigo() + "') "
                    + "and j.fechaBaja is null "
                    + "order by j.nroPago ASC");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void deleteCpcPagoContrato(CpcPagoContrato cpcPagoContrato) throws Exception {
        try {
            dao.deleteById(cpcPagoContrato.getIdPagoContrato());
        } catch (Exception e) {
            throw e;
        }
    }
}
