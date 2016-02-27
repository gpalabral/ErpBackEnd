package com.bap.erp.servicios.impl.ban;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.commons.utils.ObjectUtils;
import com.bap.erp.enums.EnumEstadoPago;
import com.bap.erp.enums.EnumModulo;
import com.bap.erp.enums.EnumTipoDocumentoMercantil;
import com.bap.erp.enums.EnumTipoMoneda;
import com.bap.erp.modelo.ban.DocumentoPago;
import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.modelo.cpc.CpcPago;
import com.bap.erp.modelo.cpc.CpcPagoContrato;
import com.bap.erp.modelo.cpp.CppRetencion;
import com.bap.erp.modelo.par.ParEstadoPago;
import com.bap.erp.modelo.par.ParTipoModulo;
import com.bap.erp.modelo.pojo.CpcPagoPojo;
import com.bap.erp.modelo.pojo.DocumentoPagoFacturasPojo;
import com.bap.erp.servicios.ban.DocumentoPagoService;
import com.bap.erp.servicios.ErpFacturaService;
import com.bap.erp.servicios.cpc.CpcPagoContratoService;
import com.bap.erp.servicios.cpc.CpcPagoService;
import com.bap.erp.servicios.cpp.CppRetencionService;
import com.bap.erp.servicios.par.ParValorService;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class DocumentoPagoServiceImpl extends AbstractJpaDAO<DocumentoPago> implements DocumentoPagoService {

    IGenericDao<DocumentoPago> dao;

    @Autowired
    public ErpFacturaService erpFacturaService;
    @Autowired
    public CpcPagoService cpcPagoService;
    @Autowired
    private ParValorService parValorService;
    @Autowired
    public CpcPagoContratoService cpcPagoContratoService;
    @Autowired
    public CppRetencionService cppRetencionService;

    @Autowired
    public void setDao(IGenericDao<DocumentoPago> daoToSet) {
        dao = daoToSet;
        dao.setClazz(DocumentoPago.class);
    }

    public DocumentoPago persistDocumentoPago(DocumentoPago documentoPago) throws Exception {
        try {
            ObjectUtils.printObjectState(documentoPago);
            documentoPago.setIdDocumentoPago(null);
            documentoPago.setUsuarioAlta("TEST");
            documentoPago.setFechaAlta(new Date());
            dao.create(documentoPago);
            return documentoPago;
        } catch (Exception e) {
            throw e;
        }
    }

    public DocumentoPago mergeDocumentoPago(DocumentoPago documentoPago) throws Exception {
        try {
            documentoPago.setFechaModificacion(new Date());
            documentoPago.setUsuarioModificacion("TEST");
            dao.update(documentoPago);
            return documentoPago;
        } catch (Exception e) {
            throw e;
        }
    }

    public DocumentoPago getDocumentoPagoById(Long idDocumentoPago) throws Exception {
        try {
            return dao.findOne(idDocumentoPago);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<DocumentoPago> getDocumentoPago() throws Exception {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<DocumentoPago> getListDocumentoPagoByIdFacturaEmitida(Long idFacturaEmitida) throws Exception {
        try {
            List<DocumentoPago> lista = find(""
                    + "select a "
                    + "from DocumentoPago a "
                    + "where a.fechaBaja is null "
                    + "and a.cpcFacturaEmitida.idFacturaEmitida = " + idFacturaEmitida + " "
                    + "order by a.fechaAlta ASC");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public Long generaNumeroPago(Long idFaturaEmitida) throws Exception {
        try {
            List<DocumentoPago> lista = find(""
                    + "select j "
                    + "from DocumentoPago j "
                    + "where j.fechaBaja is null "
                    + "and j.cpcFacturaEmitida.idFacturaEmitida = " + idFaturaEmitida + " "
                    + "order by j.numeroPago desc");
            if (!lista.isEmpty()) {
                return lista.get(0).getNumeroPago() + 1L;
            }
            return 1L;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal generaMontoAcumulado(Long idFacturaEmitida) throws Exception {
        try {
            List<DocumentoPago> lista = getListDocumentoPagoByIdFacturaEmitida(idFacturaEmitida);
            BigDecimal montoAcumulado = new BigDecimal("0");
            for (DocumentoPago documentoPago : lista) {
                montoAcumulado = montoAcumulado.add(documentoPago.getMontoAcumulado());
            }
            return montoAcumulado;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public DocumentoPago guardaDocumentoPagoFacturasPojo(DocumentoPagoFacturasPojo documentoPagoFacturasPojo) throws Exception {
        try {
            CpcPagoContrato cpcPagoContrato;
            ParEstadoPago parEstadoPago = (ParEstadoPago) parValorService.find(ParEstadoPago.class, EnumEstadoPago.BANCARIZADO.getCodigo());
            DocumentoPago documentoPago = documentoPagoFacturasPojo.getDocumentoPago();
            documentoPago = persistDocumentoPago(documentoPago);
            ErpFactura cpcFacturaEmitida;
            CpcPago cpcPago;
            ParTipoModulo parTipoModulo = parValorService.findParTipoModuloByCodigo(EnumModulo.CUENTAS_POR_COBRAR.getCodigo());
            BigDecimal montoAcumuladoPrimeraMoneda = new BigDecimal(BigInteger.ZERO);
            BigDecimal montoAcumuladoSegundaMoneda = new BigDecimal(BigInteger.ZERO);
            for (CpcPagoPojo cpcPagoPojo : documentoPagoFacturasPojo.getListaPagoPojo()) {
                cpcFacturaEmitida = erpFacturaService.getCpcFacturaEmitidaById(cpcPagoPojo.getIdFacturaEmitida());
                cpcPago = new CpcPago();
                cpcPago.setParTipoModulo(parTipoModulo);
                cpcPago.setErpFactura(cpcFacturaEmitida);
                cpcPago.setDocumentoPago(documentoPago);
                cpcPago.setMontoPagadoPrimeraMoneda(cpcPagoPojo.getMontoPrimeraMoneda());
                cpcPago.setMontoPagadoSegundaMoneda(cpcPagoPojo.getMontoSegundaMoneda());
                montoAcumuladoPrimeraMoneda = montoAcumuladoPrimeraMoneda.add(cpcPagoPojo.getMontoPrimeraMoneda());
                montoAcumuladoSegundaMoneda = montoAcumuladoSegundaMoneda.add(cpcPagoPojo.getMontoSegundaMoneda());
                cpcPago.setMontoAcumuladoPrimeraMoneda(montoAcumuladoPrimeraMoneda);
                cpcPago.setMontoAcumuladoSegundaMoneda(montoAcumuladoSegundaMoneda);
                cpcPagoService.persistCpcPago(cpcPago);
                if (cpcFacturaEmitida.getCpcPagoContrato() != null) {
                    if (cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getParTipoMoneda().getCodigo().equals(EnumTipoMoneda.BOLIVIANOS.getCodigo())) {
                        if (cpcFacturaEmitida.getMontoPrimeraMoneda().compareTo(cpcPagoService.montoPagadoAcumulado(cpcFacturaEmitida.getIdFactura(), EnumTipoMoneda.BOLIVIANOS.getCodigo())) == 0) {
                            cpcFacturaEmitida.setParEstadoPago(parEstadoPago);
                            erpFacturaService.mergeCpcFacturaEmitida(cpcFacturaEmitida);
                        }
                    } else {
                        if (cpcFacturaEmitida.getMontoSegundaMoneda().compareTo(cpcPagoService.montoPagadoAcumulado(cpcFacturaEmitida.getIdFactura(), EnumTipoMoneda.DOLARES.getCodigo())) == 0) {
                            cpcFacturaEmitida.setParEstadoPago(parEstadoPago);
                            erpFacturaService.mergeCpcFacturaEmitida(cpcFacturaEmitida);
                        }
                    }
                    cpcPagoContrato = cpcPagoContratoService.getCpcPagoContratoById(cpcFacturaEmitida.getCpcPagoContrato().getIdPagoContrato());
                    cpcPagoContrato.setMontoPagadoPrimeraMoneda(cpcPagoContrato.getMontoPagadoPrimeraMoneda().add(cpcPagoPojo.getMontoPrimeraMoneda()));
                    if (cpcPagoContrato.getCpcContrato().getParTipoMoneda().getCodigo().equals(EnumTipoMoneda.DOLARES.getCodigo())) {
                        cpcPagoContrato.setMontoPagadoSegundaMoneda(cpcPagoContrato.getMontoPagadoSegundaMoneda().add(cpcPagoPojo.getMontoSegundaMoneda()));
                        if (cpcPagoContrato.getMontoPagadoSegundaMoneda().compareTo(cpcPagoContrato.getMontoProgramadoSegMoneda()) == 0) {
                            cpcPagoContrato.setParEstadoPago(parEstadoPago);
                        }
                    } else {
                        if (cpcPagoContrato.getMontoPagadoPrimeraMoneda().compareTo(cpcPagoContrato.getMontoProgramado()) == 0) {
                            cpcPagoContrato.setParEstadoPago(parEstadoPago);
                        }
                    }
                    cpcPagoContratoService.mergeCpcPagoContrato(cpcPagoContrato);
                } else {
                    if (cpcFacturaEmitida.getMontoPrimeraMoneda().compareTo(cpcPagoService.montoPagadoAcumulado(cpcFacturaEmitida.getIdFactura(), EnumTipoMoneda.BOLIVIANOS.getCodigo())) == 0) {
                        cpcFacturaEmitida.setParEstadoPago(parEstadoPago);
                        erpFacturaService.mergeCpcFacturaEmitida(cpcFacturaEmitida);
                    }
                }
            }
            return documentoPago;
        } catch (Exception e) {
            throw e;
        }
    }

    public DocumentoPago guardaDocumentoPagoFacturasRetencionesPojo(DocumentoPagoFacturasPojo documentoPagoFacturasPojo) throws Exception {
        try {
            DocumentoPago documentoPago = documentoPagoFacturasPojo.getDocumentoPago();
            documentoPago = persistDocumentoPago(documentoPago);
            ErpFactura erpFactura;
            CppRetencion cppRetencion;
            CpcPago cpcPago;
            ParTipoModulo parTipoModulo = parValorService.findParTipoModuloByCodigo(EnumModulo.CUENTAS_POR_PAGAR.getCodigo());
            ParEstadoPago parEstadoPago = parValorService.findParEstadoPagoByCodigo(EnumEstadoPago.BANCARIZADO.getCodigo());
            List<CpcPagoPojo> listaPagoPojo = documentoPagoFacturasPojo.getListaPagoPojo();
            BigDecimal montoAcumulado = BigDecimal.ZERO;
            for (CpcPagoPojo listaPagoPojo1 : listaPagoPojo) {
                cpcPago = new CpcPago();
                cpcPago.setParTipoModulo(parTipoModulo);
                cpcPago.setDocumentoPago(documentoPago);
                cpcPago.setMontoPagadoPrimeraMoneda(listaPagoPojo1.getMontoPrimeraMoneda());                
                cpcPago.setMontoAcumuladoPrimeraMoneda(montoAcumulado.add(cpcPago.getMontoPagadoPrimeraMoneda()));
                if (listaPagoPojo1.getTipoDocumentoMercantil().equals(EnumTipoDocumentoMercantil.FACTURA.getCodigo())) {
                    erpFactura = erpFacturaService.getCpcFacturaEmitidaById(listaPagoPojo1.getIdFacturaEmitida());
                    cpcPago.setErpFactura(erpFactura);
                    cpcPagoService.persistCpcPago(cpcPago);
                    if ((erpFactura.getMontoPrimeraMoneda().compareTo(cpcPagoService.montoPagadoAcumuladoFacturaRetencion(erpFactura.getIdFactura(), EnumTipoDocumentoMercantil.FACTURA.getCodigo()))) == 0) {
                        erpFactura.setParEstadoPago(parEstadoPago);
                        erpFacturaService.mergeCpcFacturaEmitida(erpFactura);
                    }
                } else {
                    cppRetencion = cppRetencionService.findCppRetencionById(listaPagoPojo1.getIdFacturaEmitida());
                    cpcPago.setCppRetencion(cppRetencion);
                    cpcPagoService.persistCpcPago(cpcPago);
                    if ((cppRetencion.getMontoPrimeraMoneda().compareTo(cpcPagoService.montoPagadoAcumuladoFacturaRetencion(cppRetencion.getIdRetencion(), EnumTipoDocumentoMercantil.RETENCION.getCodigo()))) == 0) {
                        cppRetencion.setParEstadoPago(parEstadoPago);
                        cppRetencionService.mergeCppRetencion(cppRetencion);
                    }
                }
            }
            return documentoPago;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Integer> getGestionesFacturadas(String modulo) throws Exception {
        try {
            List<Integer> gestiones = new ArrayList<Integer>();
            List<DocumentoPago> lista = dao.find(""
                    + "select j.documentoPago "
                    + "from CpcPago j "
                    + "where j.fechaBaja is null "
                    + "and j.parTipoModulo.codigo = '" + modulo + "' "
                    + "order by j.documentoPago.fechaDocumentoPago asc ");
            if (!lista.isEmpty()) {
                int gestion;
                int gestionAnterior = 0;
                for (DocumentoPago lista1 : lista) {
                    gestion = lista1.getFechaDocumentoPago().getYear() + 1900;
                    if (gestionAnterior < gestion) {
                        gestiones.add(gestion);
                        gestionAnterior = gestion;
                    }
                }
                return gestiones;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }
}
