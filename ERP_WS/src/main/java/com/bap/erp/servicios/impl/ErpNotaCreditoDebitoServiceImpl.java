package com.bap.erp.servicios.impl;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.enums.EnumEstadoFactura;
import com.bap.erp.enums.EnumTipoMoneda;
import com.bap.erp.modelo.ErpDetalleFactura;
import com.bap.erp.modelo.ErpNotaCreditoDebito;
import com.bap.erp.modelo.cpc.CpcDosificacion;
import com.bap.erp.modelo.par.ParEstadoFactura;
import com.bap.erp.modelo.par.ParTipoMoneda;
import com.bap.erp.modelo.pojo.ErpNotaCreditoDebitoCpcDetalleFacturaPojo;
import com.bap.erp.servicios.ErpDetalleFacturaService;
import com.bap.erp.servicios.ErpNotaCreditoDebitoService;
import com.bap.erp.servicios.cpc.CpcDosificacionService;
import com.bap.erp.servicios.par.ParValorService;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErpNotaCreditoDebitoServiceImpl implements ErpNotaCreditoDebitoService {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ErpNotaCreditoDebitoServiceImpl.class);

    IGenericDao<ErpNotaCreditoDebito> dao;

    @Autowired
    private ParValorService parValorService;

    @Autowired
    private ErpDetalleFacturaService erpDetalleFacturaService;

    @Autowired
    private CpcDosificacionService cpcDosificacionService;

    @Autowired
    public void setDao(IGenericDao<ErpNotaCreditoDebito> daoToSet) {
        dao = daoToSet;
        dao.setClazz(ErpNotaCreditoDebito.class);
    }

    public ErpNotaCreditoDebito persistErpNotaCreditoDebito(ErpNotaCreditoDebito erpNotaCreditoDebito) throws Exception {
        try {
            erpNotaCreditoDebito.setIdNotaCreditoDebito(null);
            erpNotaCreditoDebito.setUsuarioAlta("TEST");
            erpNotaCreditoDebito.setFechaAlta(new Date());
            dao.create(erpNotaCreditoDebito);
            return erpNotaCreditoDebito;
        } catch (Exception e) {
            throw e;
        }
    }

    public ErpNotaCreditoDebito persistErpNotaCreditoDebitoCpcDetalleFacturaPojo(ErpNotaCreditoDebitoCpcDetalleFacturaPojo erpNotaCreditoDebitoCpcDetalleFacturaPojo) throws Exception {
        try {
            ParEstadoFactura parEstadoFactura = (ParEstadoFactura) parValorService.find(ParEstadoFactura.class, EnumEstadoFactura.VALIDA.getCodigo());
            ErpNotaCreditoDebito erpNotaCreditoDebito = erpNotaCreditoDebitoCpcDetalleFacturaPojo.getErpNotaCreditoDebito();
            List<ErpDetalleFactura> listaDetalleFactura = erpNotaCreditoDebitoCpcDetalleFacturaPojo.getListaCpcDetalleFactura();
            erpNotaCreditoDebito.setParEstadoFactura(parEstadoFactura);
            erpNotaCreditoDebito = persistErpNotaCreditoDebito(erpNotaCreditoDebito);
            for (ErpDetalleFactura erpDetalleFactura : listaDetalleFactura) {
                erpDetalleFactura.setErpNotaCreditoDebito(erpNotaCreditoDebito);
                erpDetalleFactura.setErpFactura(null);
                erpDetalleFacturaService.persistCpcDetalleFactura(erpDetalleFactura);
            }
            return erpNotaCreditoDebito;
        } catch (Exception e) {
            throw e;
        }
    }

    public ErpNotaCreditoDebito getErpNotaCreditoDebitoById(Long idNotaCreditoDebito) throws Exception {
        try {
            List<ErpNotaCreditoDebito> lista = dao.find("select j "
                    + "from ErpNotaCreditoDebito j "
                    + "where j.fechaBaja is null "
                    + "and j.idNotaCreditoDebito = " + idNotaCreditoDebito + "");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return new ErpNotaCreditoDebito();
        } catch (Exception e) {
            throw e;
        }
    }

    public ErpNotaCreditoDebitoCpcDetalleFacturaPojo getErpNotaCreditoDebito(Long idNotaCreditoDebito) throws Exception {

        log.info("idNotaCreditoDebito:: " + idNotaCreditoDebito);

        ErpNotaCreditoDebitoCpcDetalleFacturaPojo erpNotaCreditoDebitoCpcDetalleFacturaPojo = new ErpNotaCreditoDebitoCpcDetalleFacturaPojo();

        //Buscamos la Nota de Credito Debito
//        ErpNotaCreditoDebito erpNotaCreditoDebito = getErpNotaCreditoDebitoById(idNotaCreditoDebito);
        ErpNotaCreditoDebito erpNotaCreditoDebito = dao.findOne(idNotaCreditoDebito);
        log.info("erpNotaCreditoDebito:: " + erpNotaCreditoDebito);
        erpNotaCreditoDebitoCpcDetalleFacturaPojo.setErpNotaCreditoDebito(erpNotaCreditoDebito);

        //Cargamos su detalle (Nota Credito Debito)
        List<ErpDetalleFactura> lista = erpDetalleFacturaService.getCpcDetalleFacturaByNotaCreditoDebito(erpNotaCreditoDebito);
        erpNotaCreditoDebitoCpcDetalleFacturaPojo.setListaCpcDetalleFactura(lista);

        return erpNotaCreditoDebitoCpcDetalleFacturaPojo;

    }

    public Long generaNumeroNotaCreditoDebito(Long idDosificacion) throws Exception {
        try {
            CpcDosificacion cpcDosificacion = cpcDosificacionService.getCpcDosificacionById(idDosificacion);
            List<ErpNotaCreditoDebito> lista = dao.find(""
                    + "select j "
                    + "from ErpNotaCreditoDebito j "
                    + "where j.fechaBaja is null "
                    + "and j.cpcDosificacion.idDosificacion = " + idDosificacion + " "
                    + "order by j.numeroNotaCreditoDebito desc");
            if (!lista.isEmpty()) {
                return lista.get(0).getNumeroNotaCreditoDebito() + 1L;
            }
            return cpcDosificacion.getNumeroFacturaInicial();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ErpNotaCreditoDebito> getAllErpNotaCreditoDebitoByIdFatura(Long idFactura) throws Exception {
        try {
            List<ErpNotaCreditoDebito> lista = dao.find(""
                    + "select j "
                    + "from ErpNotaCreditoDebito j "
                    + "where j.fechaBaja is null "
                    + "and j.erpFactura.idFactura = " + idFactura + " ");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal montoTotalCreditoDebitoPorFactura(Long idFactura, String tipoMoneda) throws Exception {
        try {
            System.out.println("entra a monto total");
            BigDecimal total = BigDecimal.ZERO;
            List<ErpNotaCreditoDebito> lista = getAllErpNotaCreditoDebitoByIdFatura(idFactura);
            if (!lista.isEmpty()) {
                for (ErpNotaCreditoDebito lista1 : lista) {
                    if (tipoMoneda.equals(EnumTipoMoneda.BOLIVIANOS.getCodigo())) {
                        total = total.add(lista1.getImporteTotalPrimeraMoneda());
                        System.out.println("total Bolivianos::::"+total);
                    }else{
                        total = total.add(lista1.getImporteTotalSegundaMoneda());
                        System.out.println("total Dolares::::"+total);
                    }
                }                
            }
            return total;
        } catch (Exception e) {
            throw e;
        }
    }

}
