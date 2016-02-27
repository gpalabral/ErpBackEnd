package com.bap.erp.servicios.impl;

import com.bap.erp.commons.utils.ObjectUtils;
import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.utils.DateUtils;
import com.bap.erp.commons.utils.NumberUtils;
import static com.bap.erp.commons.utils.NumberUtils.redondeaBigDecimal;
import com.bap.erp.enums.EnumEstadoFactura;
import com.bap.erp.enums.EnumEstadoPago;
import com.bap.erp.enums.EnumModalidadTransaccion;
import com.bap.erp.enums.EnumModulo;
import com.bap.erp.enums.EnumTipoDocumentoMercantil;
import com.bap.erp.enums.EnumTipoHito;
import com.bap.erp.enums.EnumTipoMoneda;
import com.bap.erp.enums.EnumTipoProveedorCliente;
import com.bap.erp.enums.EnumTipoTransaccion;
import com.bap.erp.modelo.ban.DocumentoPago;
import com.bap.erp.modelo.cpc.CpcDosificacion;
import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.modelo.ErpDetalleFactura;
import com.bap.erp.modelo.cpc.CpcPago;
import com.bap.erp.modelo.cpc.CpcPagoContrato;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.cpp.CppRetencion;
import com.bap.erp.modelo.par.ParEstadoFactura;
import com.bap.erp.modelo.par.ParEstadoPago;
import com.bap.erp.modelo.par.ParTipoDocumentoMercantil;
import com.bap.erp.modelo.par.ParTipoModulo;
import com.bap.erp.modelo.par.ParTipoTransaccion;
import com.bap.erp.modelo.pojo.CpcConciliacionContablePojo;
import com.bap.erp.modelo.pojo.CpcFacturaEmitidaCpcDetalleFacturaPojo;
import com.bap.erp.modelo.pojo.CpcFacturaEmitidaPojo;
import com.bap.erp.modelo.pojo.CpcLibroDeBancarizacionPorVentasPojo;
import com.bap.erp.modelo.pojo.CppLibroDeComprasPojo;
import com.bap.erp.modelo.pojo.CpcLibroDeVentasHuaweiPojo;
import com.bap.erp.modelo.pojo.CpcLibroDeVentasPojo;
import com.bap.erp.modelo.pojo.ErpFacturaRetencionPojo;
import com.bap.erp.modelo.pojo.ErpFacturasBancariasPojo;
import com.bap.erp.modelo.pojo.ErpFacturasDatosPojo;
import com.bap.erp.servicios.cpc.CpcDosificacionService;
import com.bap.erp.servicios.ErpFacturaService;
import com.bap.erp.servicios.ErpDetalleFacturaService;
import com.bap.erp.servicios.ErpNotaCreditoDebitoService;
import com.bap.erp.servicios.cpc.CpcPagoContratoService;
import com.bap.erp.servicios.cpc.CpcPagoService;
import com.bap.erp.servicios.cpp.CppRetencionService;
import com.bap.erp.servicios.par.ParValorService;
import com.bap.erp.ws.ImportWS;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.osbosoftware.facturas.CodigoControl7;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ErpFacturaServiceImpl implements ErpFacturaService {

    private BigDecimal CIEN = new BigDecimal(100L);
    private BigDecimal TRECE = new BigDecimal(13L);
//    private BigDecimal CATORCE_NOVENTA_Y_CUATRO = new BigDecimal(14.94f);

    IGenericDao<ErpFactura> dao;

    @Autowired
    private ErpDetalleFacturaService cpcDetalleFacturaService;

    @Autowired
    private CpcDosificacionService cpcDosificacionService;

    @Autowired
    private ParValorService parValorService;

    @Autowired
    private CpcPagoContratoService cpcPagoContratoService;

    @Autowired
    private CpcPagoService cpcPagoService;

    @Autowired
    private ErpNotaCreditoDebitoService erpNotaCreditoDebitoService;

    @Autowired
    private CppRetencionService cppRetencionService;

//    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ImportWS.class);
    @Autowired
    public void setDao(IGenericDao<ErpFactura> daoToSet) {
        dao = daoToSet;
        dao.setClazz(ErpFactura.class);
    }

    public ErpFactura persistCpcFacturaEmitida(ErpFactura cpcFacturaEmitida) throws Exception {
        try {
            cpcFacturaEmitida.setIdFactura(null);
//            cpcFacturaEmitida.setUsuarioAlta("TEST");
//            cpcFacturaEmitida.setFechaAlta(new Date());
            dao.create(cpcFacturaEmitida);
            return cpcFacturaEmitida;
        } catch (Exception e) {
            throw e;
        }
    }

    public ErpFactura getCpcFacturaEmitidaById(Long idFacturaEmitida) throws Exception {
        try {
            List<ErpFactura> lista = dao.find("select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and j.idFactura = " + idFacturaEmitida + "");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return new ErpFactura();
//            return dao.findOne(idFacturaEmitida);
        } catch (Exception e) {
            throw e;
        }
    }

    public ErpFactura mergeCpcFacturaEmitida(ErpFactura cpcFacturaEmitida) {
        cpcFacturaEmitida.setFechaAlta(new Date());
        cpcFacturaEmitida.setUsuarioAlta("SYS");
        cpcFacturaEmitida.setFechaModificacion(new Date());
        cpcFacturaEmitida.setUsuarioModificacion("TEST");
//        ObjectUtils.printObjectState(cpcFacturaEmitida);
        dao.update(cpcFacturaEmitida);
        return cpcFacturaEmitida;
    }

    public List<ErpFactura> getCpcFacturaEmitidaList() throws Exception {
        try {
            List<ErpFactura> lista = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "order by j.fechaAlta asc");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public String genereCodigoDeControl(Long nit, String numeroFactura, String nroAutorizacion, Date fechaFactura, BigDecimal monto, String llaveDosificacion) throws Exception {
//        System.out.println("antes");
        CodigoControl7 codigoControl = new CodigoControl7();
        try {
//            System.out.println("despues");
            String codigoDeControl;
            codigoControl.setNitci(nit.toString());
            codigoControl.setNumeroFactura(Long.parseLong(numeroFactura));
            codigoControl.setNumeroAutorizacion(nroAutorizacion);
            codigoControl.setFechaTransaccion(fechaFactura);
            BigDecimal montoF = redondeaBigDecimal(monto, 0);
            int montoNuevo = montoF.intValue();
            codigoControl.setMonto(montoNuevo);
            codigoControl.setLlaveDosificacion(llaveDosificacion);
            codigoDeControl = codigoControl.obtener();
            return codigoDeControl;

        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public ErpFactura persistCpcFacturaEmitidaCpcDetalleFacturaPojo(CpcFacturaEmitidaCpcDetalleFacturaPojo cpcFacturaEmitidaCpcDetalleFacturaPojo) throws Exception {
        try {
//            ParEstadoFactura parEstadoFactura = (ParEstadoFactura) parValorService.find(ParEstadoFactura.class, EnumEstadoFactura.VALIDA.getCodigo());
            
            ParEstadoFactura parEstadoFactura = parValorService.obtieneParEstadoFacturaPorCodigo(EnumEstadoFactura.VALIDA.getCodigo());

            ParTipoDocumentoMercantil parTipoDocumentoMercantil = (ParTipoDocumentoMercantil) parValorService.find(ParTipoDocumentoMercantil.class, EnumTipoDocumentoMercantil.FACTURA.getCodigo());
            ErpFactura cpcFacturaEmitida = cpcFacturaEmitidaCpcDetalleFacturaPojo.getCpcFacturaEmitida();
            ParEstadoPago parEstadoPagoFactura;
            if (cpcFacturaEmitida.getParTipoModulo().getCodigo().equals(EnumModulo.CUENTAS_POR_PAGAR.getCodigo())) {
                parEstadoPagoFactura = (ParEstadoPago) parValorService.find(ParEstadoPago.class, EnumEstadoPago.FACTURADO.getCodigo());
            } else {
                parEstadoPagoFactura = (ParEstadoPago) parValorService.find(ParEstadoPago.class, EnumEstadoPago.PENDIENTE.getCodigo());
            }
            cpcFacturaEmitida.setParEstadoFactura(parEstadoFactura);
            cpcFacturaEmitida.setParEstadoPago(parEstadoPagoFactura);
            if (cpcFacturaEmitida.getParTipoDocumentoMercantil() == null) {
                cpcFacturaEmitida.setParTipoDocumentoMercantil(parTipoDocumentoMercantil);
            }
            if (cpcFacturaEmitida.getNumeroFactura() == null) {
                cpcFacturaEmitida.setNroFacturaInterno("0");
            }
            if (cpcFacturaEmitida.getCuentaBancaria() != null && cpcFacturaEmitida.getCuentaBancaria().getIdCuentaBancaria() == 0) {
                cpcFacturaEmitida.setCuentaBancaria(null);
            }
            if (cpcFacturaEmitida.getParTipoDocumentoMercantil().getCodigo().equals(EnumTipoDocumentoMercantil.POLIZA_DE_IMPORTACION.getCodigo())) {
                cpcFacturaEmitida.setIvaPrimeraMoneda(cpcFacturaEmitida.getIvaPrimeraMoneda());
            } else {
                cpcFacturaEmitida.setIvaPrimeraMoneda((cpcFacturaEmitida.getMontoPrimeraMoneda().multiply(new BigDecimal("0.13"))).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            cpcFacturaEmitida.setIvaSegundaMoneda((cpcFacturaEmitida.getMontoSegundaMoneda().multiply(new BigDecimal("0.13"))).setScale(2, BigDecimal.ROUND_HALF_UP));
            //ObjectUtils.printObjectState(cpcFacturaEmitida);
            cpcFacturaEmitida = persistCpcFacturaEmitida(cpcFacturaEmitida);
            List<ErpDetalleFactura> lista = cpcFacturaEmitidaCpcDetalleFacturaPojo.getListaCpcDetalleFactura();
            if (lista != null) {
                for (ErpDetalleFactura cpcDetalleFactura : lista) {
                    cpcDetalleFactura.setErpFactura(cpcFacturaEmitida);
                    if (cpcDetalleFactura.getCpcItem() != null && cpcDetalleFactura.getCpcItem().getIdItem().equals(0L)) {
                        cpcDetalleFactura.setCpcItem(null);
                    }
                    cpcDetalleFacturaService.persistCpcDetalleFactura(cpcDetalleFactura);
                }
            }
            if (cpcFacturaEmitida.getCpcPagoContrato() != null) {
                CpcPagoContrato cpcPagoContrato = cpcPagoContratoService.getCpcPagoContratoById(cpcFacturaEmitida.getCpcPagoContrato().getIdPagoContrato());
                //EN CASO DE TOMAR EN CUENTA LOS DESCUENTOS EL METODO SUMA EL MONTO TOTAL CON EL DESCUENTO PARA IGUALAR AL MONTO PROGRAMADO
                cpcPagoContrato.setMontoFacturado(cpcPagoContrato.getMontoFacturado().add(cpcFacturaEmitida.getMontoPrimeraMoneda().add(cpcFacturaEmitida.getTotalDescuentoPrimeraMoneda())));
                ParEstadoPago parEstadoPago = (ParEstadoPago) parValorService.find(ParEstadoPago.class, EnumEstadoPago.FACTURADO.getCodigo());
                if (cpcPagoContrato.getCpcContrato().getParTipoMoneda().getCodigo().equals(EnumTipoMoneda.DOLARES.getCodigo())) {
                    cpcPagoContrato.setMontoFacturadoSegMoneda(cpcPagoContrato.getMontoFacturadoSegMoneda().add(cpcFacturaEmitida.getMontoSegundaMoneda().add(cpcFacturaEmitida.getTotalDescuentoSegundaMoneda())));
                }
                if (cpcPagoContrato.getCpcContrato().getParTipoMoneda().getCodigo().equals(EnumTipoMoneda.BOLIVIANOS.getCodigo()) && (cpcPagoContrato.getMontoFacturado().compareTo(cpcPagoContrato.getMontoProgramado()) == 0)) {
                    cpcPagoContrato.setPorcentajeFacturado((cpcPagoContrato.getMontoFacturado().multiply(new BigDecimal("100"))).divide(cpcPagoContrato.getMontoProgramado(), 5, RoundingMode.HALF_UP));
                    cpcPagoContrato.setParEstadoPago(parEstadoPago);
                }
                if (cpcPagoContrato.getCpcContrato().getParTipoMoneda().getCodigo().equals(EnumTipoMoneda.DOLARES.getCodigo()) && (cpcPagoContrato.getMontoFacturadoSegMoneda().compareTo(cpcPagoContrato.getMontoProgramadoSegMoneda()) == 0)) {
                    cpcPagoContrato.setPorcentajeFacturado((cpcPagoContrato.getMontoFacturadoSegMoneda().multiply(new BigDecimal("100"))).divide(cpcPagoContrato.getMontoProgramadoSegMoneda(), 5, RoundingMode.HALF_UP));
                    cpcPagoContrato.setParEstadoPago(parEstadoPago);
                }
                cpcPagoContratoService.mergeCpcPagoContrato(cpcPagoContrato);
            }
            return cpcFacturaEmitida;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<ErpFactura> getCpcFacturaEmitidaProveedorClienteDocumentoPago(CppProveedorCliente cppProveedorCliente, DocumentoPago documentoPago) throws Exception {
        try {
            List<ErpFactura> listaConceptos = dao.find(""
                    + "select distinct a.cpcFacturaEmitida "
                    + "from CppProveedorCliente a "
                    + "where a.cpcFacturaEmitida.fechaBaja is null "
                    + "and a.cppProveedorCliente.idProveedorCliente = " + cppProveedorCliente.getIdProveedorCliente() + " "
                    + "and a.cpcFacturaEmitida.idFacturaEmitida = " + documentoPago.getIdDocumentoPago() + "");
            if (!listaConceptos.isEmpty()) {
                return listaConceptos;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ErpFactura> getListCpcFacturaEmitida() throws Exception {
        try {
            List<ErpFactura> listaFacturas = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and (j.parEstadoPago.codigo = '" + EnumEstadoPago.FACTURADO.getCodigo() + "' "
                    + "or j.parEstadoPago.codigo = '" + EnumEstadoPago.NO_BANCARIZADO.getCodigo() + "' "
                    + "or j.parEstadoPago.codigo = '" + EnumEstadoPago.PENDIENTE.getCodigo() + "') "
                    + "and j.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_COBRAR.getCodigo() + "' "
                    + "");
            if (!listaFacturas.isEmpty()) {
                return listaFacturas;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ErpFactura> getCpcFacturaEmitidaListporMesyAnio() throws Exception {
        try {
            List<ErpFactura> lista = dao.find(""
                    + "select b "
                    + "from ErpFactura b "
                    + "where b.fechaBaja is null "
                    + "and j.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_COBRAR.getCodigo() + "' "
                    + "and (b.montoPrimeraMoneda >= 50000 ) "
                    + "order by b.fechaAlta asc");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcFacturaEmitidaPojo> getCpcFacturaEmitidaPorMesyAnio(int month, int year) throws Exception {
        try {
            List<CpcFacturaEmitidaPojo> listaFacturaEmitida = new ArrayList<CpcFacturaEmitidaPojo>();
            CpcFacturaEmitidaPojo cpcFacturaEmitidaPojo;
//            ErpFactura j;
//            j.getParEstadoPago().getCodigo();
            String consultaMes1 = "";
            String consultaMes2 = "";
            if (month != 0) {
                consultaMes1 = "and MONTH(a.fechaFactura) = " + month + " ";
                consultaMes2 = "and MONTH(n.fechaFactura) = " + month + " ";
            }
            List<ErpFactura> lista1 = dao.find(""
                    + "select a "
                    + "from ErpFactura a "
                    + "where a.fechaBaja is null "
                    + consultaMes1
                    + "and YEAR(a.fechaFactura) = " + year + " "
                    + "and a.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_COBRAR.getCodigo() + "' "
                    + "and a.parEstadoPago.codigo = '" + EnumEstadoPago.PENDIENTE.getCodigo() + "' "
                    + "order by a.fechaAlta asc");
            List<ErpFactura> lista2 = dao.find(""
                    + "select n "
                    + "from ErpFactura n "
                    + "where n.fechaBaja is null "
                    + consultaMes2
                    + "and YEAR(n.fechaFactura) = " + year + " "
                    + "and n.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_COBRAR.getCodigo() + "' "
                    + "and n.parEstadoPago.codigo = '" + EnumEstadoPago.PENDIENTE.getCodigo() + "' "
                    + "and n.cpcPagoContrato.cpcContrato.idContrato in "
                    + "(select y.idContrato "
                    + "from CpcContrato y "
                    + "where y.fechaBaja is null) "
                    + "and n.idFactura not in "
                    + "(select a.idFactura "
                    + "from ErpFactura a "
                    + "where a.fechaBaja is null "
                    + consultaMes1
                    + "and YEAR(a.fechaFactura) = " + year + ") "
                    + "order by n.fechaAlta asc ");
            List<ErpFactura> listaFinal = new ArrayList<ErpFactura>();
            listaFinal.addAll(lista1);
            listaFinal.addAll(lista2);
            if (!listaFinal.isEmpty()) {
                for (ErpFactura cpcFacturaEmitida : listaFinal) {
                    cpcFacturaEmitidaPojo = new CpcFacturaEmitidaPojo();
                    cpcFacturaEmitidaPojo.setCpcFacturaEmitida(cpcFacturaEmitida);
                    if (cpcFacturaEmitida.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                        cpcFacturaEmitidaPojo.setNombre(cpcFacturaEmitida.getCppProveedorCliente().getRazonSocial());
                    } else {
                        cpcFacturaEmitidaPojo.setNombre(cpcFacturaEmitida.getCppProveedorCliente().getPrimerApellido() + " " + cpcFacturaEmitida.getCppProveedorCliente().getSegundoApellido() + " " + cpcFacturaEmitida.getCppProveedorCliente().getNombre());
                    }
                    if (cpcFacturaEmitida.getCpcPagoContrato() != null) {
                        cpcFacturaEmitidaPojo.setNroContrato(cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getNroContrato());
                        cpcFacturaEmitidaPojo.setNroContratoCliente(cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getNroContratoCliente());
                    } else {
                        cpcFacturaEmitidaPojo.setNroContrato("0");
                        cpcFacturaEmitidaPojo.setNroContratoCliente("0");
                    }
                    cpcFacturaEmitidaPojo.setSaldoPrimeraMoneda(cpcPagoService.saldoPagosPorFactura(cpcFacturaEmitida, "montoPagadoPrimeraMoneda"));
                    cpcFacturaEmitidaPojo.setSaldoSegundaMoneda(cpcPagoService.saldoPagosPorFactura(cpcFacturaEmitida, "montoPagadoSegundaMoneda"));
                    listaFacturaEmitida.add(cpcFacturaEmitidaPojo);
                }
                return listaFacturaEmitida;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public Long generaNumeroFactura(Long idDosificacion) throws Exception {
        try {
            System.out.println("idDosificacion:::" + idDosificacion);
            CpcDosificacion cpcDosificacion = cpcDosificacionService.getCpcDosificacionById(idDosificacion);
            List<ErpFactura> lista = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and j.cpcDosificacion.idDosificacion = " + idDosificacion + " "
                    + "and j.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_COBRAR.getCodigo() + "' "
                    + "order by j.numeroFactura desc");
            if (!lista.isEmpty()) {
                return lista.get(0).getNumeroFactura() + 1L;
            }
            return cpcDosificacion.getNumeroFacturaInicial();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ErpFactura> getLibroDeVentas(int month, int year, String modulo) throws Exception {
        try {
            List<ErpFactura> lista = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and MONTH(j.fechaFactura) = " + month + " "
                    + "and YEAR(j.fechaFactura) = " + year + " "
                    + "and j.parTipoModulo.codigo = '" + modulo + "' "
                    + "order by j.fechaFactura asc");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public CpcFacturaEmitidaPojo getFacturaEmitidaNombreConcatenadoById(Long idFacturaEmitida) throws Exception {
        ErpFactura cpcFacturaEmitida = getCpcFacturaEmitidaById(idFacturaEmitida);
        CpcFacturaEmitidaPojo cpcFacturaEmitidaPojo = new CpcFacturaEmitidaPojo();
        cpcFacturaEmitidaPojo.setCpcFacturaEmitida(cpcFacturaEmitida);
        if (cpcFacturaEmitida.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
            cpcFacturaEmitidaPojo.setNombre(cpcFacturaEmitida.getCppProveedorCliente().getRazonSocial());
        } else {
            cpcFacturaEmitidaPojo.setNombre(cpcFacturaEmitida.getCppProveedorCliente().getPrimerApellido() + " " + cpcFacturaEmitida.getCppProveedorCliente().getSegundoApellido() + " " + cpcFacturaEmitida.getCppProveedorCliente().getNombre());
        }
        return cpcFacturaEmitidaPojo;
    }

    public ErpFactura guardaFacturaEmitida(ErpFactura cpcFacturaEmitida) throws Exception {
        try {
            ParEstadoFactura parEstadoFactura = (ParEstadoFactura) parValorService.find(ParEstadoFactura.class, "V");
            cpcFacturaEmitida.setParEstadoFactura(parEstadoFactura);
            cpcFacturaEmitida = persistCpcFacturaEmitida(cpcFacturaEmitida);
            return cpcFacturaEmitida;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ErpFactura> getListaCpcFacturaEmitidaById(Long idPagoContrato) throws Exception {
        try {
            List<ErpFactura> lista = dao.find(""
                    + "select a "
                    + "from ErpFactura a "
                    + "where a.fechaBaja is null "
                    + "and a.cpcPagoContrato.idPagoContrato = " + idPagoContrato + "");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean getVerificaFacturaParaDosificacionesByIdDosificacion(Long idDosificacion) throws Exception {
        try {
            List<ErpFactura> list = dao.find(""
                    + "select c "
                    + "from CpcDosificacion c "
                    + "where c.fechaBaja is null and "
                    + "c.idDosificacion in ("
                    + "select a.cpcDosificacion.idDosificacion "
                    + "from ErpFactura a "
                    + "where a.fechaBaja is null "
                    + "and a.cpcDosificacion.idDosificacion = " + idDosificacion + ")");
            return !list.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ErpFactura> getXMLByIdFacturaEmitida(Long idFacturaEmitida) throws Exception {
        try {
            List<ErpFactura> lista = dao.find(""
                    + "select a "
                    + "from ErpFactura a "
                    + "where a.fechaBaja is null "
                    + "and a.idFactura = " + idFacturaEmitida + "");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ErpFactura> getListadoCpcFacturaEmitida() throws Exception {
        try {
            List<ErpFactura> lista = dao.find(""
                    + "select a "
                    + "from ErpFactura a "
                    + "where a.fechaBaja is null "
                    + "and cpcPagoContrato.idPagoContrato in ("
                    + "select c.idPagoContrato "
                    + "from CpcPagoContrato c "
                    + "where c.fechaBaja is null "
                    + "and c.montoFacturado >= 50000 ) "
                    + "order by a.fechaAlta asc");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcLibroDeVentasPojo> getLibroDeVentasPojo(int month, int year) throws Exception {
        try {
            List<CpcLibroDeVentasPojo> listaLibroDeVentasPojo = new ArrayList<CpcLibroDeVentasPojo>();
            List<ErpFactura> listaFacturaEmitida = getLibroDeVentas(month, year, EnumModulo.CUENTAS_POR_COBRAR.getCodigo());
            CpcLibroDeVentasPojo cpcLibroDeVentasPojo;
            int numero = 1;
            for (ErpFactura listaFacturaEmitida1 : listaFacturaEmitida) {
                cpcLibroDeVentasPojo = new CpcLibroDeVentasPojo();
                cpcLibroDeVentasPojo.setImporteIceIehdTasas(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                cpcLibroDeVentasPojo.setVentasGravadasAtasaCero(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                if (listaFacturaEmitida1.getParEstadoFactura().getCodigo().equals(EnumEstadoFactura.ANULADA.getCodigo())) {
                    cpcLibroDeVentasPojo.setNit(0L);
                    cpcLibroDeVentasPojo.setNombreRazonSocial("ANULADA");
                    cpcLibroDeVentasPojo.setNumeroAutorizacion(0L);
                    cpcLibroDeVentasPojo.setImporteTotal(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                    cpcLibroDeVentasPojo.setExportacionesYoperacionesExentas(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                    cpcLibroDeVentasPojo.setSubtotal(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                    cpcLibroDeVentasPojo.setDescuentoBonificacionYrebajas(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                    cpcLibroDeVentasPojo.setImporteBaseDebitoFiscal(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                    cpcLibroDeVentasPojo.setDebitoFiscal(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                    cpcLibroDeVentasPojo.setCodigoControl("0");
                } else {
                    cpcLibroDeVentasPojo.setNit(listaFacturaEmitida1.getCppProveedorCliente().getNit());
                    if (listaFacturaEmitida1.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                        cpcLibroDeVentasPojo.setNombreRazonSocial(listaFacturaEmitida1.getCppProveedorCliente().getRazonSocial());
                    } else {
                        cpcLibroDeVentasPojo.setNombreRazonSocial(listaFacturaEmitida1.getCppProveedorCliente().getPrimerApellido() + " " + listaFacturaEmitida1.getCppProveedorCliente().getSegundoApellido() + " " + listaFacturaEmitida1.getCppProveedorCliente().getNombre());
                    }
                    cpcLibroDeVentasPojo.setImporteTotal((listaFacturaEmitida1.getMontoPrimeraMoneda().add(listaFacturaEmitida1.getTotalDescuentoPrimeraMoneda())).setScale(2, BigDecimal.ROUND_HALF_UP));
                    if (listaFacturaEmitida1.getParTipoTransaccion().getCodigo().equals(EnumTipoTransaccion.EXPORTACIONES.getCodigo())) {
                        cpcLibroDeVentasPojo.setExportacionesYoperacionesExentas((listaFacturaEmitida1.getMontoPrimeraMoneda()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    } else {
                        cpcLibroDeVentasPojo.setExportacionesYoperacionesExentas(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                    cpcLibroDeVentasPojo.setSubtotal(((listaFacturaEmitida1.getMontoPrimeraMoneda().add(listaFacturaEmitida1.getTotalDescuentoPrimeraMoneda())).subtract(cpcLibroDeVentasPojo.getExportacionesYoperacionesExentas()).subtract(cpcLibroDeVentasPojo.getVentasGravadasAtasaCero())).setScale(2, BigDecimal.ROUND_HALF_UP));
                    cpcLibroDeVentasPojo.setDescuentoBonificacionYrebajas(listaFacturaEmitida1.getTotalDescuentoPrimeraMoneda().setScale(2, BigDecimal.ROUND_HALF_UP));
                    cpcLibroDeVentasPojo.setImporteBaseDebitoFiscal((cpcLibroDeVentasPojo.getSubtotal().subtract(cpcLibroDeVentasPojo.getDescuentoBonificacionYrebajas())).setScale(2, BigDecimal.ROUND_HALF_UP));
                    cpcLibroDeVentasPojo.setDebitoFiscal((cpcLibroDeVentasPojo.getImporteBaseDebitoFiscal().multiply(new BigDecimal("0.13"))).setScale(2, BigDecimal.ROUND_HALF_UP));
                    if (listaFacturaEmitida1.getCpcDosificacion() != null) {
                        cpcLibroDeVentasPojo.setNumeroAutorizacion(listaFacturaEmitida1.getCpcDosificacion().getNumeroAutorizacion());
                    } else {
                        cpcLibroDeVentasPojo.setNumeroAutorizacion(0L);
                    }
                    if (listaFacturaEmitida1.getCodigoControl().equals("")) {
                        cpcLibroDeVentasPojo.setCodigoControl("0");
                    } else {
                        cpcLibroDeVentasPojo.setCodigoControl(listaFacturaEmitida1.getCodigoControl());
                    }

//                    System.out.println("otra::: "+cpcLibroDeVentasPojo.getImporteTotal());                    
                }
                cpcLibroDeVentasPojo.setNumero(numero);
                cpcLibroDeVentasPojo.setFechaFactura(listaFacturaEmitida1.getFechaFactura());
                if (listaFacturaEmitida1.getNumeroFactura() != null) {
                    cpcLibroDeVentasPojo.setNumeroDeFactura(listaFacturaEmitida1.getNumeroFactura().intValue());
                } else {
                    cpcLibroDeVentasPojo.setNumeroDeFactura(0);
                }
                cpcLibroDeVentasPojo.setEstado(listaFacturaEmitida1.getParEstadoFactura().getDescripcion());
                listaLibroDeVentasPojo.add(cpcLibroDeVentasPojo);
                numero++;
            }

            if (!listaLibroDeVentasPojo.isEmpty()) {
                return listaLibroDeVentasPojo;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal getSumaFacturaEmitidasByIdPagoContrato(Long idPagoContrato) throws Exception {
        try {
            List<ErpFactura> list = dao.find(""
                    + "select a "
                    + "from ErpFactura a "
                    + "where a.fechaBaja is null "
                    + "and a.cpcPagoContrato.idPagoContrato is not null "
                    + "and a.cpcPagoContrato.idPagoContrato = " + idPagoContrato + " "
                    + "order by a.fechaAlta asc");
            BigDecimal montoTotal = new BigDecimal("0");
            for (ErpFactura listaFacturaEmitida : list) {
                montoTotal = montoTotal.add(listaFacturaEmitida.getMontoPrimeraMoneda());
            }
            return montoTotal;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcFacturaEmitidaPojo> getCpcNotaDebitoCreditoByMesYAnio(int mes, int anio, String codigoDocMercantil, String estadoPago, String codigoEstadoFactura) throws Exception {
        try {
            List<CpcFacturaEmitidaPojo> listaCpcFacturaEmitidaPojo = new ArrayList<CpcFacturaEmitidaPojo>();
            CpcFacturaEmitidaPojo cpcFacturaEmitidaPojo;
            List<ErpFactura> lista = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and MONTH(j.fechaFactura) = " + mes + " "
                    + "and YEAR(j.fechaFactura) = " + anio + " "
                    + "and j.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_COBRAR.getCodigo() + "' "
                    + "and j.parTipoDocumentoMercantil.codigo = '" + codigoDocMercantil + "' "
                    + "and j.parEstadoPago.codigo = '" + estadoPago + "' "
                    + "and j.parEstadoFactura.codigo = '" + codigoEstadoFactura + "' "
                    + "order by j.fechaAlta asc");
            if (!lista.isEmpty()) {
                for (ErpFactura cpcFacturaEmitida : lista) {
                    if (cpcFacturaEmitida.getMontoPrimeraMoneda().compareTo(erpNotaCreditoDebitoService.montoTotalCreditoDebitoPorFactura(cpcFacturaEmitida.getIdFactura(), EnumTipoMoneda.BOLIVIANOS.getCodigo())) == 1 || cpcFacturaEmitida.getMontoSegundaMoneda().compareTo(erpNotaCreditoDebitoService.montoTotalCreditoDebitoPorFactura(cpcFacturaEmitida.getIdFactura(), EnumTipoMoneda.DOLARES.getCodigo())) == 1) {
                        cpcFacturaEmitidaPojo = new CpcFacturaEmitidaPojo();
                        cpcFacturaEmitidaPojo.setCpcFacturaEmitida(cpcFacturaEmitida);
                        if (cpcFacturaEmitida.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                            cpcFacturaEmitidaPojo.setNombre(cpcFacturaEmitida.getCppProveedorCliente().getRazonSocial());
                        } else {
                            cpcFacturaEmitidaPojo.setNombre(cpcFacturaEmitida.getCppProveedorCliente().getPrimerApellido() + " " + cpcFacturaEmitida.getCppProveedorCliente().getSegundoApellido() + " " + cpcFacturaEmitida.getCppProveedorCliente().getNombre());
                        }
                        if (cpcFacturaEmitida.getCpcPagoContrato() != null) {
                            cpcFacturaEmitidaPojo.setNroContrato(cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getNroContrato());
                            cpcFacturaEmitidaPojo.setNroContratoCliente(cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getNroContratoCliente());
                        } else {
                            cpcFacturaEmitidaPojo.setNroContrato("0");
                            cpcFacturaEmitidaPojo.setNroContratoCliente("0");
                        }
                        cpcFacturaEmitidaPojo.setSaldoPrimeraMoneda(cpcPagoService.saldoPagosPorFactura(cpcFacturaEmitida, "montoPagadoPrimeraMoneda"));
                        cpcFacturaEmitidaPojo.setSaldoSegundaMoneda(cpcPagoService.saldoPagosPorFactura(cpcFacturaEmitida, "montoPagadoSegundaMoneda"));
                        listaCpcFacturaEmitidaPojo.add(cpcFacturaEmitidaPojo);
                    }
                }
                return listaCpcFacturaEmitidaPojo;
            }
            return new ArrayList<CpcFacturaEmitidaPojo>();
        } catch (Exception e) {
            throw e;
        }
    }

    public String getGeneraXML(Long idFacturaEmitida) throws Exception {
        try {
            ErpFactura erpFactura = getCpcFacturaEmitidaById(idFacturaEmitida);

            List<ErpDetalleFactura> listCpcDetalleFactura = cpcDetalleFacturaService.getCpcDetalleFacturaByFacturaEmitida(erpFactura);
            ErpFactura cpcFacturaEmitida = getCpcFacturaEmitidaById(idFacturaEmitida);
            if (cpcFacturaEmitida == null) {
                return null;
            }

            Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
            Namespace xsd = Namespace.getNamespace("xsd", "http://www.w3.org/2001/XMLSchema");
            Namespace soap = Namespace.getNamespace("soap", "http://schemas.xmlsoap.org/soap/envelope/");
            Namespace fact = Namespace.getNamespace("urn:impuestos-gob-bo:newton:facturacionelectronicaservice:data:v1");

            //Inicio del jDomDoc nuemro uno
            // create the jdom
            Document jdomDoc = new Document();
            // create root element
            Element rootElement = new Element("Envelope", soap);
            rootElement.addNamespaceDeclaration(xsi);
            rootElement.addNamespaceDeclaration(xsd);

            jdomDoc.setRootElement(rootElement);

            // add child
            // fin child
            Element header = new Element("Header", soap);
//        header.addContent("This is child 1");

            // add child 1
            Element datosCabecera = new Element("DatosCabecera");
            datosCabecera.setNamespace(fact);

            //datosCabecera.addNamespaceDeclaration(fact);
            String nm = null;
            Element NIT = new Element("NIT", nm);
            ObjectUtils.printObjectState(cpcFacturaEmitida, ":::::::::::::::::::cpcFacturaEmitida.getCppProveedorCliente():::::::::::::::::::::");
            NIT.setText(String.valueOf(cpcFacturaEmitida.getCppProveedorCliente().getNit()));
            NIT.removeNamespaceDeclaration(fact);

            datosCabecera.addContent(NIT);

            Element nombreUsuario = new Element("nombreUsuario");
            if (cpcFacturaEmitida.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                nombreUsuario.setText(String.valueOf(cpcFacturaEmitida.getCppProveedorCliente().getRazonSocial()));
            } else {
                nombreUsuario.setText(String.valueOf(cpcFacturaEmitida.getCppProveedorCliente().getNombre()) + " " + String.valueOf(cpcFacturaEmitida.getCppProveedorCliente().getPrimerApellido()) + " " + String.valueOf(cpcFacturaEmitida.getCppProveedorCliente().getSegundoApellido()));
            }
            datosCabecera.addContent(nombreUsuario);

            header.addContent(datosCabecera);
            // fin child 1

            Element body = new Element("Body", soap);
//        body.addContent("This is child 2");

            // add child 2
            Element facturarRequest = new Element("facturarRequest", "urn:impuestos-gob-bo:newton:facturacionelectronicaservice:data:v1");

            Element numeroTransacciones = new Element("numeroTransacciones");
            numeroTransacciones.setText("1");
            facturarRequest.addContent(numeroTransacciones);

            Element transacciones = new Element("transacciones");

            for (ErpDetalleFactura cpcDetalleFacturatoObjeto : listCpcDetalleFactura) {

                Element Facturas = new Element("Facturas", fact);

//                Element idDetalleFactura = new Element("idDetalleFactura");
//                idDetalleFactura.setText(cpcDetalleFacturatoObjeto.getIdDetalleFactura() != null ? cpcDetalleFacturatoObjeto.getIdDetalleFactura().toString() : "*");
//                Facturas.addContent(idDetalleFactura);
                Element codigoSucursal = new Element("codigoSucursal");

                codigoSucursal.setText(String.valueOf(cpcDetalleFacturatoObjeto.getErpFactura().getCpcDosificacion().getCpcSucursal().getNumeroSucursal()));
                Facturas.addContent(codigoSucursal);

                Element codTipoFactura = new Element("codTipoFactura");
                codTipoFactura.setText(String.valueOf(cpcDetalleFacturatoObjeto.getErpFactura().getParModalidadTransaccion().getDescripcion()));
                Facturas.addContent(codTipoFactura);

                Element nombreComprador = new Element("nombreComprador");
                String nombreCliente = null;

                //Facturas con contrato
                if (cpcDetalleFacturatoObjeto.getErpFactura().getCpcPagoContrato() != null) {
                    if (cpcDetalleFacturatoObjeto.getErpFactura().getCpcPagoContrato().getCpcContrato().getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                        nombreCliente = cpcDetalleFacturatoObjeto.getErpFactura().getCpcPagoContrato().getCpcContrato().getCppProveedorCliente().getRazonSocial();
                    } else {
                        nombreCliente = cpcDetalleFacturatoObjeto.getErpFactura().getCpcPagoContrato().getCpcContrato().getCppProveedorCliente().getPrimerApellido() + " " + cpcDetalleFacturatoObjeto.getErpFactura().getCppProveedorCliente().getSegundoApellido() + " " + cpcDetalleFacturatoObjeto.getErpFactura().getCppProveedorCliente().getNombre();
                    }
                } else if (cpcDetalleFacturatoObjeto.getErpFactura().getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                    nombreCliente = cpcDetalleFacturatoObjeto.getErpFactura().getCppProveedorCliente().getRazonSocial();
                } else {
                    nombreCliente = cpcDetalleFacturatoObjeto.getErpFactura().getCppProveedorCliente().getPrimerApellido() + " " + cpcDetalleFacturatoObjeto.getErpFactura().getCppProveedorCliente().getSegundoApellido() + " " + cpcDetalleFacturatoObjeto.getErpFactura().getCppProveedorCliente().getNombre();
                }

                nombreComprador.setText(nombreCliente);
                Facturas.addContent(nombreComprador);

                Element identificadorComprador = new Element("identificadorComprador");
                if (cpcDetalleFacturatoObjeto.getErpFactura().getCpcPagoContrato() != null) {//Con contrato
                    identificadorComprador.setText(String.valueOf(cpcDetalleFacturatoObjeto.getErpFactura().getCpcPagoContrato().getCpcContrato().getCppProveedorCliente().getNit()));
                } else {
                    identificadorComprador.setText(cpcDetalleFacturatoObjeto.getErpFactura().getCppProveedorCliente().getNumeroDocumento());
                }

                Facturas.addContent(identificadorComprador);

                Element debitoFiscal = new Element("debitoFiscal");
//                Float nroDebitoFiscal = (cpcDetalleFacturatoObjeto.getErpFactura().getMontoPrimeraMoneda() * 10) / 100;
                BigDecimal nroDebitoFiscal = (cpcDetalleFacturatoObjeto.getErpFactura().getMontoPrimeraMoneda().multiply(new BigDecimal("10"))).divide(new BigDecimal("100"), 5, RoundingMode.HALF_UP);
                debitoFiscal.setText(String.valueOf(nroDebitoFiscal));
                Facturas.addContent(debitoFiscal);

                Element importeNeto = new Element("importeNeto");
                importeNeto.setText("0");
                Facturas.addContent(importeNeto);

                Element importeTotal = new Element("importeTotal");
                importeTotal.setText("0");
                Facturas.addContent(importeTotal);

                Element importeICE = new Element("importeICE");
                importeICE.setText("0");
                Facturas.addContent(importeICE);

                Element importeExento = new Element("importeExento");
                importeExento.setText("0");
                Facturas.addContent(importeExento);

                Element descuentoTotal = new Element("descuentoTotal");
                descuentoTotal.setText("0");
                Facturas.addContent(descuentoTotal);

                Element detalle = new Element("detalles");
                detalle.setText(cpcDetalleFacturatoObjeto.getDetalleFactura());
                Facturas.addContent(detalle);

                transacciones.addContent(Facturas);
            }
            facturarRequest.addContent(transacciones);
            body.addContent(facturarRequest);
        // fin child 2

            // add attribute
            rootElement.addContent(header);
            rootElement.addContent(body);

            // Output as XML
            // create XMLOutputter
            XMLOutputter xml = new XMLOutputter();
            // we want to format the xml. This is used only for demonstration.
            // pretty formatting adds extra spaces and is generally not required.
            xml.setFormat(Format.getPrettyFormat());
            System.out.println(xml.outputString(jdomDoc));
            String outputString = xml.outputString(jdomDoc);
            outputString = outputString.replaceAll(" xmlns=\"\"", "");

//            InputStream stream = new ByteArrayInputStream(xml.outputString(jdomDoc).getBytes("UTF-8"));
            //InputStream stream = new ByteArrayInputStream(outputString.getBytes("UTF-8"));
//            SAXBuilder builder = new SAXBuilder();
//            Document anotherDocument = builder.build(stream);
//            System.out.println("anotherDocument:: " + anotherDocument);
//            //Inicio del jDomDoc nuemro dos
//            // create the jdom2
//            Document jdomDoc2 = new Document();
//            // create root element
//            Element rootElement2 = new Element("Envelope", soap);
//            rootElement2.addNamespaceDeclaration(xsi);
//            rootElement2.addNamespaceDeclaration(xsd);
//
//            jdomDoc2.setRootElement(rootElement2);
//
//            Element body2 = new Element("Body", soap);
//
//            // add child 2
//            Element facturarResponse2 = new Element("facturarResponse", "urn:impuestos-gob-bo:newton:facturacionelectronicaservice:messages:v1");
//
//            Element eTicket = new Element("eTicket");
//            eTicket.setText("String");
//            facturarResponse2.addContent(eTicket);
//
//            Element errores2 = new Element("errores");
//
//            Element fallo2 = new Element("fallo", "urn:impuestos-gob-bo:newton:facturacionelectronicaservice:data:v1");
//
//            Element codigo2 = new Element("codigo");
//            codigo2.setText("unsignedInt");
//            fallo2.addContent(codigo2);
//
//            Element descripcion2 = new Element("descripcion");
//            descripcion2.setText("String");
//            fallo2.addContent(descripcion2);
//
//            errores2.addContent(fallo2);
//
//            facturarResponse2.addContent(errores2);
//
//            body2.addContent(facturarResponse2);
//        // fin child 2
//
//            // add attribute
//            rootElement2.addContent(body2);
//
//            // Output as XML
//            // create XMLOutputter
//            XMLOutputter xml2 = new XMLOutputter();
//            // we want to format the xml. This is used only for demonstration.
//            // pretty formatting adds extra spaces and is generally not required.
//            xml2.setFormat(Format.getPrettyFormat());
//            System.out.println(xml2.outputString(jdomDoc2));            
            return outputString;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ErpFactura> getCpcFacturaEmitidaByIdPagoContratoAndEstado(Long idPagoContrato, String estadoPago) throws Exception {
        try {
            List<ErpFactura> lista = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and j.cpcPagoContrato.idPagoContrato = " + idPagoContrato + " "
                    + "and j.parEstadoPago.codigo = '" + estadoPago + "' "
                    + "order by j.fechaAlta");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcLibroDeBancarizacionPorVentasPojo> getLibroDeBancarizacionPorVentas(int month, int year) throws Exception {
        try {
            List<CpcLibroDeBancarizacionPorVentasPojo> lista = new ArrayList<CpcLibroDeBancarizacionPorVentasPojo>();
            List<CpcPago> listaPagos = cpcPagoService.listaPagosOrdenados(month, year, EnumModulo.CUENTAS_POR_COBRAR.getCodigo());
            CpcLibroDeBancarizacionPorVentasPojo cpcLibroDeBancarizacionPorVentasPojo;
            for (CpcPago cpcPago : listaPagos) {
                cpcLibroDeBancarizacionPorVentasPojo = new CpcLibroDeBancarizacionPorVentasPojo();
                if (cpcPago.getErpFactura().getParModalidadTransaccion().getCodigo().equals(EnumModalidadTransaccion.CONTADO.getCodigo())) {
                    cpcLibroDeBancarizacionPorVentasPojo.setModalidadDeTransaccion(1);
                } else {
                    cpcLibroDeBancarizacionPorVentasPojo.setModalidadDeTransaccion(2);
                }
                cpcLibroDeBancarizacionPorVentasPojo.setFechaFactura(cpcPago.getErpFactura().getFechaFactura());
                if (cpcPago.getErpFactura().getParTipoTransaccion().getCodigo().equals(EnumTipoTransaccion.VENTAS.getCodigo())) {
                    cpcLibroDeBancarizacionPorVentasPojo.setTipoTransaccion(1);
                } else if (cpcPago.getErpFactura().getParTipoTransaccion().getCodigo().equals(EnumTipoTransaccion.EXPORTACIONES.getCodigo())) {
                    cpcLibroDeBancarizacionPorVentasPojo.setTipoTransaccion(2);
                } else {
                    cpcLibroDeBancarizacionPorVentasPojo.setTipoTransaccion(3);
                }
                cpcLibroDeBancarizacionPorVentasPojo.setNumeroFactura(cpcPago.getErpFactura().getNumeroFactura().toString());
                cpcLibroDeBancarizacionPorVentasPojo.setMontoFactura(cpcPago.getErpFactura().getMontoPrimeraMoneda());
                cpcLibroDeBancarizacionPorVentasPojo.setNroAutorizacion(cpcPago.getErpFactura().getCpcDosificacion().getNumeroAutorizacion());
                if (!cpcPago.getErpFactura().getCppProveedorCliente().getNit().toString().equals("")) {
                    cpcLibroDeBancarizacionPorVentasPojo.setNitCiCliente(cpcPago.getErpFactura().getCppProveedorCliente().getNit().toString());
                } else if (!cpcPago.getErpFactura().getCppProveedorCliente().getNumeroDocumento().equals("")) {
                    cpcLibroDeBancarizacionPorVentasPojo.setNitCiCliente(cpcPago.getErpFactura().getCppProveedorCliente().getNumeroDocumento());
                } else {
                    cpcLibroDeBancarizacionPorVentasPojo.setNitCiCliente("0");
                }
                if (cpcPago.getErpFactura().getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                    cpcLibroDeBancarizacionPorVentasPojo.setRazonSocialNombreCliente(cpcPago.getErpFactura().getCppProveedorCliente().getRazonSocial());
                } else {
                    cpcLibroDeBancarizacionPorVentasPojo.setRazonSocialNombreCliente(cpcPago.getErpFactura().getCppProveedorCliente().getNombre() + " " + cpcPago.getErpFactura().getCppProveedorCliente().getPrimerApellido() + " " + cpcPago.getErpFactura().getCppProveedorCliente().getSegundoApellido());
                }
                cpcLibroDeBancarizacionPorVentasPojo.setNroCuentaDocumentoPago(cpcPago.getDocumentoPago().getNroCtaEntidadEmisora());
                cpcLibroDeBancarizacionPorVentasPojo.setMontoPagadoDocPago(cpcPago.getMontoPagadoPrimeraMoneda());
                cpcLibroDeBancarizacionPorVentasPojo.setMontoAcumulado(cpcPago.getMontoAcumuladoPrimeraMoneda());
                cpcLibroDeBancarizacionPorVentasPojo.setNitEntidadFinanciera(Integer.parseInt(cpcPago.getDocumentoPago().getParBanco().getNit()));
                cpcLibroDeBancarizacionPorVentasPojo.setNroDocumentoPago(cpcPago.getDocumentoPago().getNroDocumento().toString());
                cpcLibroDeBancarizacionPorVentasPojo.setTipoDeDocumentoDePago(Integer.parseInt(cpcPago.getDocumentoPago().getParTipoDocumentoPago().getCodigo()));
                cpcLibroDeBancarizacionPorVentasPojo.setFechaDelDocumentoDePago(cpcPago.getDocumentoPago().getFechaDocumentoPago());
                lista.add(cpcLibroDeBancarizacionPorVentasPojo);
            }
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    public void persistFacturasNoUtilizadas() throws Exception {
        try {
            ErpFactura cpcFacturaEmitida;
            List<CpcDosificacion> listaDosificaciones = cpcDosificacionService.listaDosificacionesVencidas();
//            CppProveedorCliente cppProveedorCliente = cppProveedorClienteService.
            Long siguienteNumFactura;
            if (!listaDosificaciones.isEmpty()) {
                for (CpcDosificacion cpcDosificacion : listaDosificaciones) {
                    siguienteNumFactura = generaNumeroFactura(cpcDosificacion.getIdDosificacion());
                    while (siguienteNumFactura <= cpcDosificacion.getNumeroFacturaFinal()) {
                        cpcFacturaEmitida = new ErpFactura();
                        cpcFacturaEmitida.setCodigoControl("");
                        cpcFacturaEmitida.setConcepto("");
                        cpcFacturaEmitida.setCpcDosificacion(cpcDosificacion);
                        cpcFacturaEmitida.setCpcPagoContrato(null);
                        cpcFacturaEmitida.setCppProveedorCliente(null);
                        siguienteNumFactura++;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcFacturaEmitidaPojo> getCpcFacturaEmitidaPorCliente(Long idCliente) throws Exception {
        try {
            List<CpcFacturaEmitidaPojo> listaFacturaEmitida = new ArrayList<CpcFacturaEmitidaPojo>();
            CpcFacturaEmitidaPojo cpcFacturaEmitidaPojo;
            List<ErpFactura> listaFinal = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and j.parEstadoPago.codigo = '" + EnumEstadoPago.PENDIENTE.getCodigo() + "' "
                    + "and j.parEstadoFactura.codigo = '" + EnumEstadoFactura.VALIDA.getCodigo() + "' "
                    + "and j.cppProveedorCliente.idProveedorCliente = '" + idCliente + "' "
                    + "order by j.fechaAlta asc");
            System.out.println("las facturas son::: " + listaFinal.size());
            if (!listaFinal.isEmpty()) {
                for (ErpFactura cpcFacturaEmitida : listaFinal) {
                    cpcFacturaEmitidaPojo = new CpcFacturaEmitidaPojo();
                    cpcFacturaEmitidaPojo.setCpcFacturaEmitida(cpcFacturaEmitida);
                    if (cpcFacturaEmitida.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                        cpcFacturaEmitidaPojo.setNombre(cpcFacturaEmitida.getCppProveedorCliente().getRazonSocial());
                    } else {
                        cpcFacturaEmitidaPojo.setNombre(cpcFacturaEmitida.getCppProveedorCliente().getPrimerApellido() + " " + cpcFacturaEmitida.getCppProveedorCliente().getSegundoApellido() + " " + cpcFacturaEmitida.getCppProveedorCliente().getNombre());
                    }
                    if (cpcFacturaEmitida.getCpcPagoContrato() != null) {
                        cpcFacturaEmitidaPojo.setNroContrato(cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getNroContrato());
                        cpcFacturaEmitidaPojo.setNroContratoCliente(cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getNroContratoCliente());
                    } else {
                        cpcFacturaEmitidaPojo.setNroContrato("0");
                        cpcFacturaEmitidaPojo.setNroContratoCliente("0");
                    }
                    cpcFacturaEmitidaPojo.setSaldoPrimeraMoneda(cpcPagoService.saldoPagosPorFactura(cpcFacturaEmitida, "montoPagadoPrimeraMoneda"));
                    cpcFacturaEmitidaPojo.setSaldoSegundaMoneda(cpcPagoService.saldoPagosPorFactura(cpcFacturaEmitida, "montoPagadoSegundaMoneda"));
                    listaFacturaEmitida.add(cpcFacturaEmitidaPojo);
                }
                return listaFacturaEmitida;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcConciliacionContablePojo> getReferenciacionNotaDebitoFiscal(List<CpcConciliacionContablePojo> listaDebitoFiscal, List<CpcConciliacionContablePojo> listaIngresos) throws Exception {
        try {
            List<CpcConciliacionContablePojo> listaConciliacionContablePojo = new ArrayList<CpcConciliacionContablePojo>();
            CpcConciliacionContablePojo cpcConciliacionContablePojo;
            String consulta;
            if (listaDebitoFiscal.isEmpty()) {
                consulta = "and (j.referenciadoIngresos is null or j.referenciadoIngresos = 'N') ";
            } else {
                consulta = "and (j.referenciado is null or j.referenciado = 'N') ";
            }
            List<ErpFactura> lista = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and (j.parEstadoFactura.codigo = '" + EnumEstadoFactura.VALIDA.getCodigo() + "' "
                    + "or j.parEstadoFactura.codigo = '" + EnumEstadoFactura.ANULADA.getCodigo() + "') "
                    + "and j.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_COBRAR.getCodigo() + "' "
                    + consulta
                    + "order by j.numeroFactura asc");
            if (!lista.isEmpty()) {
                String[] entryItemVector;
                String entryItem = "";
                for (ErpFactura cpcFacturaEmitida : lista) {
                    cpcConciliacionContablePojo = new CpcConciliacionContablePojo();
                    cpcConciliacionContablePojo.setFechaFactura(cpcFacturaEmitida.getFechaFactura());
                    cpcConciliacionContablePojo.setIdFacturaEmitida(cpcFacturaEmitida.getIdFactura());
                    cpcConciliacionContablePojo.setNroFacturaInterno(cpcFacturaEmitida.getNroFacturaInterno());
                    cpcConciliacionContablePojo.setConciliado("N");
                    if (listaDebitoFiscal.isEmpty()) {
                        cpcConciliacionContablePojo.setDebitoFiscal(((cpcFacturaEmitida.getMontoPrimeraMoneda().multiply(new BigDecimal("0.87")).setScale(2, BigDecimal.ROUND_HALF_UP))));
                    } else {
                        cpcConciliacionContablePojo.setDebitoFiscal(((cpcFacturaEmitida.getMontoPrimeraMoneda().multiply(new BigDecimal("0.13")).setScale(2, BigDecimal.ROUND_HALF_UP))));
                    }
                    cpcConciliacionContablePojo.setNumeroDeFactura(cpcFacturaEmitida.getNumeroFactura());
                    cpcConciliacionContablePojo.setNroContrato(cpcFacturaEmitida.getNroContrato());
                    if (cpcFacturaEmitida.getReferenciado() != null && cpcFacturaEmitida.getReferenciado().equals("M")) {
                        cpcConciliacionContablePojo.setConciliado(cpcFacturaEmitida.getReferenciado());
                        cpcConciliacionContablePojo.setBatchNameDebitoFiscal(cpcFacturaEmitida.getBatchNameDebitoFiscal());
                        cpcConciliacionContablePojo.setBatchNameIngresos(cpcFacturaEmitida.getBatchNameIngresos());
                    }
                    Boolean esAnulado = false;
                    if (!listaDebitoFiscal.isEmpty()) {
                        for (CpcConciliacionContablePojo cpcConciliacionContableExcel : listaDebitoFiscal) {
                            if (cpcConciliacionContableExcel.getEntryItem().startsWith("BLV")) {
                                entryItemVector = cpcConciliacionContableExcel.getEntryItem().split("-");
                                entryItem = entryItemVector[0];
                                esAnulado = entryItemVector.length == 0;
                                if (cpcFacturaEmitida.getNroFacturaInterno() != null && cpcFacturaEmitida.getNroFacturaInterno().equals(entryItem)) {
//                                    if ((cpcConciliacionContableExcel.getEnterCurrency().equals("USD") && (((cpcFacturaEmitida.getMontoSegundaMoneda().multiply(new BigDecimal("0.13"))).setScale(2, BigDecimal.ROUND_HALF_UP)).compareTo(new BigDecimal(cpcConciliacionContableExcel.getEnterCredits())) == 0)) || (cpcConciliacionContableExcel.getEnterCurrency().equals("BOB") && (cpcFacturaEmitida.getMontoPrimeraMoneda().multiply(new BigDecimal("0.13")).setScale(2, BigDecimal.ROUND_HALF_UP)).compareTo(new BigDecimal(cpcConciliacionContableExcel.getCredits())) == 0) || (esAnulado && cpcFacturaEmitida.getParEstadoFactura().getCodigo().equals(EnumEstadoFactura.ANULADA.getCodigo()))) {
                                    if ((((cpcFacturaEmitida.getMontoPrimeraMoneda().multiply(new BigDecimal("0.13"))).setScale(2, BigDecimal.ROUND_HALF_UP)).compareTo(new BigDecimal(cpcConciliacionContableExcel.getCredits())) == 0)
                                            || (esAnulado && cpcFacturaEmitida.getParEstadoFactura().getCodigo().equals(EnumEstadoFactura.ANULADA.getCodigo()))) {
                                        cpcConciliacionContablePojo.setBatchNameDebitoFiscal(cpcConciliacionContableExcel.getBatchNameDebitoFiscal());
                                        cpcConciliacionContablePojo.setConciliado("A");
//                                    cpcConciliacionContablePojo.setCredits(cpcConciliacionContableExcel.getCredits());
                                        cpcConciliacionContablePojo.setEntryItem(cpcConciliacionContableExcel.getEntryItem());
                                        cpcConciliacionContablePojo.setCuentaContable(cpcConciliacionContableExcel.getCuentaContable());
                                        cpcConciliacionContablePojo.setCredits(cpcConciliacionContableExcel.getCredits());
                                        cpcConciliacionContablePojo.setSalesContractNo(cpcConciliacionContableExcel.getSalesContractNo());
                                        cpcConciliacionContablePojo.setGlDate(cpcConciliacionContableExcel.getGlDate());
                                        cpcConciliacionContablePojo.setNumero(cpcConciliacionContableExcel.getNumero());
//                                        cpcFacturaEmitida.setReferenciado("A");
//                                        cpcFacturaEmitida.setBatchNameDebitoFiscal(cpcConciliacionContableExcel.getBatchNameDebitoFiscal());
//                                        cpcFacturaEmitida.setCuentaContable(cpcConciliacionContableExcel.getCuentaContable());
//                                        mergeCpcFacturaEmitida(cpcFacturaEmitida);
                                    }
                                }
                            }
                        }
                    }
                    if (!listaIngresos.isEmpty()) {
                        for (CpcConciliacionContablePojo cpcConciliacionContableExcel : listaIngresos) {
                            if ((cpcFacturaEmitida.getCpcPagoContrato() != null
                                    && cpcConciliacionContableExcel.getSalesContractNo() != null
                                    && cpcConciliacionContableExcel.getSalesContractNo().equals(cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getNroContrato()))
                                    || (cpcFacturaEmitida.getCpcPagoContrato() == null
                                    && cpcConciliacionContableExcel.getSalesContractNo() != null
                                    && cpcConciliacionContableExcel.getSalesContractNo().equals(cpcFacturaEmitida.getNroContrato()))) {
//                                if ((cpcConciliacionContableExcel.getEnterCurrency().equals("USD")
//                                        && (((cpcFacturaEmitida.getMontoSegundaMoneda().multiply(new BigDecimal("0.87"))).setScale(2, BigDecimal.ROUND_HALF_UP)).compareTo(new BigDecimal(cpcConciliacionContableExcel.getEnterCredits())) == 0))
//                                        || (cpcConciliacionContableExcel.getEnterCurrency().equals("BOB")
//                                        && cpcFacturaEmitida.getMontoPrimeraMoneda().multiply(new BigDecimal("0.87")).setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(cpcConciliacionContableExcel.getCredits())) == 0)
//                                        || (esAnulado && cpcFacturaEmitida.getParEstadoFactura().getCodigo().equals(EnumEstadoFactura.ANULADA.getCodigo()))) {
                                if ((((cpcFacturaEmitida.getMontoPrimeraMoneda().multiply(new BigDecimal("0.87"))).setScale(2, BigDecimal.ROUND_HALF_UP)).compareTo(new BigDecimal(cpcConciliacionContableExcel.getCredits())) == 0)
                                        || (esAnulado && cpcFacturaEmitida.getParEstadoFactura().getCodigo().equals(EnumEstadoFactura.ANULADA.getCodigo()))) {
                                    cpcConciliacionContablePojo.setBatchNameIngresos(cpcConciliacionContableExcel.getBatchNameDebitoFiscal());
                                    cpcConciliacionContablePojo.setConciliado("A");
//                                cpcConciliacionContablePojo.setCredits(cpcConciliacionContableExcel.getCredits());
                                    cpcConciliacionContablePojo.setEntryItem(cpcConciliacionContableExcel.getEntryItem());
                                    cpcConciliacionContablePojo.setGlDate(cpcConciliacionContableExcel.getGlDate());
                                    cpcConciliacionContablePojo.setNumero(cpcConciliacionContableExcel.getNumero());
                                    cpcConciliacionContablePojo.setCuentaContable(cpcConciliacionContableExcel.getCuentaContable());
                                    cpcConciliacionContablePojo.setCredits(cpcConciliacionContableExcel.getCredits());
                                    cpcConciliacionContablePojo.setSalesContractNo(cpcConciliacionContableExcel.getSalesContractNo());
//                                    cpcFacturaEmitida.setReferenciado("A");
//                                    cpcFacturaEmitida.setBatchNameIngresos(cpcConciliacionContableExcel.getBatchNameDebitoFiscal());
//                                    cpcFacturaEmitida.setCuentaContable(cpcConciliacionContableExcel.getCuentaContable());
//                                    mergeCpcFacturaEmitida(cpcFacturaEmitida);
                                }
                            }
                        }
                    }
                    listaConciliacionContablePojo.add(cpcConciliacionContablePojo);
                }
            }
            if (!listaConciliacionContablePojo.isEmpty()) {
                return listaConciliacionContablePojo;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }
//    public List<CpcConciliacionContablePojo> getReferenciacionNotaDebitoFiscal(List<CpcConciliacionContablePojo> listaDebitoFiscal, List<CpcConciliacionContablePojo> listaIngresos) throws Exception {
//        try {
//            List<CpcConciliacionContablePojo> listaConciliacionContablePojo = new ArrayList<CpcConciliacionContablePojo>();
//            CpcConciliacionContablePojo cpcConciliacionContablePojo;
//            List<ErpFactura> lista = dao.find(""
//                    + "select j "
//                    + "from ErpFactura j "
//                    + "where j.fechaBaja is null "
//                    + "and (j.parEstadoFactura.codigo = '" + EnumEstadoFactura.VALIDA.getCodigo() + "' "
//                    + "or j.parEstadoFactura.codigo = '" + EnumEstadoFactura.ANULADA.getCodigo() + "') "
//                    + "order by j.numeroFactura asc");
//            if (!lista.isEmpty()) {
//                String[] entryItemVector;
//                String entryItem = "";
//                for (ErpFactura cpcFacturaEmitida : lista) {
//                    cpcConciliacionContablePojo = new CpcConciliacionContablePojo();
//                    cpcConciliacionContablePojo.setFechaFactura(cpcFacturaEmitida.getFechaFactura());
//                    cpcConciliacionContablePojo.setIdFacturaEmitida(cpcFacturaEmitida.getIdFactura());
//                    cpcConciliacionContablePojo.setNroFacturaInterno(cpcFacturaEmitida.getNroFacturaInterno());
//                    cpcConciliacionContablePojo.setConciliado("N");
//                    cpcConciliacionContablePojo.setDebitoFiscal((cpcFacturaEmitida.getMontoPrimeraMoneda().multiply(new BigDecimal("0.13").setScale(2, BigDecimal.ROUND_HALF_UP))));
//                    cpcConciliacionContablePojo.setNumeroDeFactura(cpcFacturaEmitida.getNumeroFactura());
//                    if (cpcFacturaEmitida.getReferenciado() != null && cpcFacturaEmitida.getReferenciado().equals("M")) {
//                        cpcConciliacionContablePojo.setConciliado(cpcFacturaEmitida.getReferenciado());
//                        cpcConciliacionContablePojo.setBatchNameDebitoFiscal(cpcFacturaEmitida.getBatchNameDebitoFiscal());
//                        cpcConciliacionContablePojo.setBatchNameIngresos(cpcFacturaEmitida.getBatchNameIngresos());
//                    }
//                    Boolean esAnulado = false;
//                    if (!listaDebitoFiscal.isEmpty()) {
//                        for (CpcConciliacionContablePojo cpcConciliacionContableExcel : listaDebitoFiscal) {
//                            if (cpcConciliacionContableExcel.getEntryName().startsWith("BLV")) {
//                                entryItemVector = cpcConciliacionContableExcel.getEntryName().split("-");
//                                entryItem = entryItemVector[0];
//                                esAnulado = entryItemVector.length == 0;
//                                if (cpcFacturaEmitida.getNroFacturaInterno() != null && cpcFacturaEmitida.getNroFacturaInterno().equals(entryItem)) {
//                                    if ((cpcConciliacionContableExcel.getEnterCurrency().equals("USD") && (((cpcFacturaEmitida.getMontoSegundaMoneda().multiply(new BigDecimal("0.13"))).setScale(2, BigDecimal.ROUND_HALF_UP)).compareTo(new BigDecimal(cpcConciliacionContableExcel.getEnterCredits())) == 0)) || (cpcConciliacionContableExcel.getEnterCurrency().equals("BOB") && cpcFacturaEmitida.getMontoPrimeraMoneda().multiply(new BigDecimal("0.13")).setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(cpcConciliacionContableExcel.getCredits())) == 0) || (esAnulado && cpcFacturaEmitida.getParEstadoFactura().getCodigo().equals(EnumEstadoFactura.ANULADA.getCodigo()))) {
//                                        cpcConciliacionContablePojo.setBatchNameDebitoFiscal(cpcConciliacionContableExcel.getBatchNameDebitoFiscal());
//                                        cpcConciliacionContablePojo.setConciliado("A");
////                                    cpcConciliacionContablePojo.setCredits(cpcConciliacionContableExcel.getCredits());
//                                        cpcConciliacionContablePojo.setEntryName(cpcConciliacionContableExcel.getEntryName());
//                                        cpcConciliacionContablePojo.setGlDate(cpcConciliacionContableExcel.getGlDate());
//                                        cpcConciliacionContablePojo.setNumero(cpcConciliacionContableExcel.getNumero());
//                                        cpcFacturaEmitida.setReferenciado("A");
//                                        cpcFacturaEmitida.setBatchNameDebitoFiscal(cpcConciliacionContableExcel.getBatchNameDebitoFiscal());
//                                        mergeCpcFacturaEmitida(cpcFacturaEmitida);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    if (!listaIngresos.isEmpty()) {
//                        for (CpcConciliacionContablePojo cpcConciliacionContableExcel : listaIngresos) {
//                            if ((cpcFacturaEmitida.getCpcPagoContrato() != null
//                                    && cpcConciliacionContableExcel.getSalesContractNo() != null
//                                    && cpcConciliacionContableExcel.getSalesContractNo().equals(cpcFacturaEmitida.getCpcPagoContrato().getCpcContrato().getNroContrato()))
//                                    || (cpcFacturaEmitida.getCpcPagoContrato() == null
//                                    && cpcConciliacionContableExcel.getSalesContractNo() != null
//                                    && cpcConciliacionContableExcel.getSalesContractNo().equals(cpcFacturaEmitida.getNroContrato()))) {
//                                if ((cpcConciliacionContableExcel.getEnterCurrency().equals("USD")
//                                        && (((cpcFacturaEmitida.getMontoSegundaMoneda().multiply(new BigDecimal("0.87"))).setScale(2, BigDecimal.ROUND_HALF_UP)).compareTo(new BigDecimal(cpcConciliacionContableExcel.getEnterCredits())) == 0))
//                                        || (cpcConciliacionContableExcel.getEnterCurrency().equals("BOB")
//                                        && cpcFacturaEmitida.getMontoPrimeraMoneda().multiply(new BigDecimal("0.87")).setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(cpcConciliacionContableExcel.getCredits())) == 0)
//                                        || (esAnulado && cpcFacturaEmitida.getParEstadoFactura().getCodigo().equals(EnumEstadoFactura.ANULADA.getCodigo()))) {
//                                    cpcConciliacionContablePojo.setBatchNameIngresos(cpcConciliacionContableExcel.getBatchNameDebitoFiscal());
//                                    cpcConciliacionContablePojo.setConciliado("A");
////                                cpcConciliacionContablePojo.setCredits(cpcConciliacionContableExcel.getCredits());
//                                    cpcConciliacionContablePojo.setEntryName(cpcConciliacionContableExcel.getEntryName());
//                                    cpcConciliacionContablePojo.setGlDate(cpcConciliacionContableExcel.getGlDate());
//                                    cpcConciliacionContablePojo.setNumero(cpcConciliacionContableExcel.getNumero());
//                                    cpcFacturaEmitida.setReferenciado("A");
//                                    cpcFacturaEmitida.setBatchNameIngresos(cpcConciliacionContableExcel.getBatchNameDebitoFiscal());
//                                    cpcFacturaEmitida.setCuentaContable(cpcConciliacionContableExcel.getCuentaContable());
//                                    mergeCpcFacturaEmitida(cpcFacturaEmitida);
//                                }
//                            }
//                        }
//                    }
//                    listaConciliacionContablePojo.add(cpcConciliacionContablePojo);
//                }
//            }
//            if (!listaConciliacionContablePojo.isEmpty()) {
//                return listaConciliacionContablePojo;
//            }
//            return Collections.EMPTY_LIST;
//        } catch (Exception e) {
//            throw e;
//        }
//    }

    public List<CpcLibroDeVentasHuaweiPojo> getLibroDeVentasHuawei(Long idProveedorCliente, int month, int year) throws Exception {
        try {
            List<ErpFactura> listaFactura = getFacturasOrdenadasPorClienteContratoFecha(idProveedorCliente, month, year);
            List<CpcLibroDeVentasHuaweiPojo> lista = new ArrayList<CpcLibroDeVentasHuaweiPojo>();
            CpcLibroDeVentasHuaweiPojo cpcLibroDeVentasHuaweiPojo;
            for (ErpFactura listaFactura1 : listaFactura) {
                cpcLibroDeVentasHuaweiPojo = new CpcLibroDeVentasHuaweiPojo();
                if (listaFactura1.getParEstadoFactura().getCodigo().equals(EnumEstadoFactura.ANULADA.getCodigo())) {
                    cpcLibroDeVentasHuaweiPojo.setFechaAceptacionFactura("");
                    cpcLibroDeVentasHuaweiPojo.setNitCliente(0L);
                    cpcLibroDeVentasHuaweiPojo.setNombreCliente("ANULADA");
                    cpcLibroDeVentasHuaweiPojo.setNumeroContrato("");
                    cpcLibroDeVentasHuaweiPojo.setNumeroContratoCliente("");
                    cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoDolares(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoBolivianos(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setTipoCambioContrato(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoUSD(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoBOB(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setAnticipo(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setEntrega(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setFacturacionPorPac(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setFacturacionPorFac(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setTipoFactura("Nula");
                    cpcLibroDeVentasHuaweiPojo.setMontoFacturaDolares(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setMontoFacturaBolivianos(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setMontoFacturaUSD(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setMontoFacturaBOB(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setIngresoFacturado(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setIngresoPorExportacion(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setIngresoPorVentasAlExteriorSinFacturar(BigDecimal.ZERO);//no calculado                 
                    cpcLibroDeVentasHuaweiPojo.setIngresoNeto(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setPorcentajeFacturacion(BigDecimal.ZERO); //no calculado 
                    cpcLibroDeVentasHuaweiPojo.setDiasDeRetraso(0);
                } else {
                    cpcLibroDeVentasHuaweiPojo.setFechaAceptacionFactura(DateUtils.dateFormatReporte(listaFactura1.getFechaAceptacion()));
                    cpcLibroDeVentasHuaweiPojo.setNitCliente(listaFactura1.getCppProveedorCliente().getNit());
                    if (listaFactura1.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                        cpcLibroDeVentasHuaweiPojo.setNombreCliente(listaFactura1.getCppProveedorCliente().getRazonSocial());
                    } else {
                        cpcLibroDeVentasHuaweiPojo.setNombreCliente(listaFactura1.getCppProveedorCliente().getPrimerApellido() + " " + listaFactura1.getCppProveedorCliente().getSegundoApellido() + " " + listaFactura1.getCppProveedorCliente().getNombre());
                    }
                    if (listaFactura1.getCpcPagoContrato() != null) {
                        cpcLibroDeVentasHuaweiPojo.setNumeroContrato(listaFactura1.getCpcPagoContrato().getCpcContrato().getNroContrato());
                        cpcLibroDeVentasHuaweiPojo.setNumeroContratoCliente(listaFactura1.getCpcPagoContrato().getCpcContrato().getNroContratoCliente());
                        cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoDolares(listaFactura1.getCpcPagoContrato().getCpcContrato().getMontoSegundaMoneda());
                        cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoBolivianos(listaFactura1.getCpcPagoContrato().getCpcContrato().getMontoPrimeraMoneda());
                        cpcLibroDeVentasHuaweiPojo.setTipoCambioContrato(listaFactura1.getCpcPagoContrato().getCpcContrato().getTipoCambio());
                        cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoUSD(listaFactura1.getCpcPagoContrato().getCpcContrato().getMontoSegundaMoneda());
                        cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoBOB(listaFactura1.getCpcPagoContrato().getCpcContrato().getMontoPrimeraMoneda());
                        List<CpcPagoContrato> listaPagosPorContrato = cpcPagoContratoService.getCpcPagoContratoByIdContrato(listaFactura1.getCpcPagoContrato().getCpcContrato().getIdContrato());
                        for (CpcPagoContrato listaPagosPorContrato1 : listaPagosPorContrato) {
                            if (listaPagosPorContrato1.getParTipoHito().getCodigo().equals(EnumTipoHito.ANTICIPO.getCodigo())) {
                                cpcLibroDeVentasHuaweiPojo.setAnticipo(listaPagosPorContrato1.getPorcentajeProgramado());
                            } else if (listaPagosPorContrato1.getParTipoHito().getCodigo().equals(EnumTipoHito.ENTREGA.getCodigo())) {
                                cpcLibroDeVentasHuaweiPojo.setEntrega(listaPagosPorContrato1.getPorcentajeProgramado());
                            } else if (listaPagosPorContrato1.getParTipoHito().getCodigo().equals(EnumTipoHito.ACEPTACION_PARCIAL.getCodigo())) {
                                cpcLibroDeVentasHuaweiPojo.setFacturacionPorPac(listaPagosPorContrato1.getPorcentajeProgramado());
                            } else if (listaPagosPorContrato1.getParTipoHito().getCodigo().equals(EnumTipoHito.ACEPTACION_FINAL.getCodigo())) {
                                cpcLibroDeVentasHuaweiPojo.setFacturacionPorFac(listaPagosPorContrato1.getPorcentajeProgramado());
                            }
                        }
                        if (listaFactura1.getCpcPagoContrato().getCpcContrato().getMontoPrimeraMoneda().compareTo(BigDecimal.ZERO) == 0) {
                            System.out.println("montoSegundamoneda factura:::: " + listaFactura1.getMontoSegundaMoneda());
                            System.out.println("otro factura:::: " + listaFactura1.getCpcPagoContrato().getCpcContrato().getMontoSegundaMoneda());
                            cpcLibroDeVentasHuaweiPojo.setPorcentajeFacturacion(((listaFactura1.getMontoSegundaMoneda().multiply(new BigDecimal("100"))).divide(listaFactura1.getCpcPagoContrato().getCpcContrato().getMontoSegundaMoneda(), 2, RoundingMode.HALF_UP)));
                        } else {
                            cpcLibroDeVentasHuaweiPojo.setPorcentajeFacturacion(((listaFactura1.getMontoPrimeraMoneda().multiply(new BigDecimal("100"))).divide(listaFactura1.getCpcPagoContrato().getCpcContrato().getMontoPrimeraMoneda(), 2, RoundingMode.HALF_UP)));
                        }
                    } else {
                        cpcLibroDeVentasHuaweiPojo.setNumeroContrato(listaFactura1.getNroContrato());
                        cpcLibroDeVentasHuaweiPojo.setNumeroContratoCliente("");
                        cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoDolares(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoBolivianos(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setTipoCambioContrato(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoUSD(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoBOB(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setAnticipo(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setEntrega(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setFacturacionPorPac(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setFacturacionPorFac(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setPorcentajeFacturacion(BigDecimal.ZERO);
                    }
                    if (listaFactura1.getParTipoTransaccion().getCodigo().equals(EnumTipoTransaccion.EXPORTACIONES.getCodigo())) {
                        cpcLibroDeVentasHuaweiPojo.setTipoFactura("Export");
                        cpcLibroDeVentasHuaweiPojo.setIva(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setIt(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setIngresoFacturado(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setIngresoPorExportacion(listaFactura1.getMontoSegundaMoneda());
                    } else {
                        cpcLibroDeVentasHuaweiPojo.setTipoFactura("Local");
                        cpcLibroDeVentasHuaweiPojo.setIva(listaFactura1.getMontoPrimeraMoneda().multiply(new BigDecimal("0.13").setScale(2, BigDecimal.ROUND_HALF_UP)));
                        cpcLibroDeVentasHuaweiPojo.setIt(listaFactura1.getMontoPrimeraMoneda().multiply(new BigDecimal("0.03").setScale(2, BigDecimal.ROUND_HALF_UP)));
                        cpcLibroDeVentasHuaweiPojo.setIngresoFacturado(listaFactura1.getMontoPrimeraMoneda());
                        cpcLibroDeVentasHuaweiPojo.setIngresoPorExportacion(BigDecimal.ZERO);
                    }
                    cpcLibroDeVentasHuaweiPojo.setMontoFacturaDolares(listaFactura1.getMontoSegundaMoneda());
                    cpcLibroDeVentasHuaweiPojo.setMontoFacturaBolivianos(listaFactura1.getMontoPrimeraMoneda());
                    cpcLibroDeVentasHuaweiPojo.setMontoFacturaUSD(listaFactura1.getMontoSegundaMoneda());
                    cpcLibroDeVentasHuaweiPojo.setMontoFacturaBOB(listaFactura1.getMontoPrimeraMoneda());
                    cpcLibroDeVentasHuaweiPojo.setIngresoPorVentasAlExteriorSinFacturar(BigDecimal.ZERO);//no calculado                 
                    cpcLibroDeVentasHuaweiPojo.setIngresoNeto((listaFactura1.getMontoPrimeraMoneda().subtract(cpcLibroDeVentasHuaweiPojo.getIva())).setScale(2, BigDecimal.ROUND_HALF_UP));

                    cpcLibroDeVentasHuaweiPojo.setRevenueAccrued(listaFactura1.getMontoPrimeraMoneda().multiply(new BigDecimal("0.87").setScale(2, BigDecimal.ROUND_HALF_UP))); //consultar que pasa con facturas de exportacion
                    if (listaFactura1.getFechaAceptacion() != null) {
                        cpcLibroDeVentasHuaweiPojo.setDiasDeRetraso(DateUtils.diferenciaDiasReporte(listaFactura1.getFechaAceptacion(), listaFactura1.getFechaFactura()));//Calcular la diferencia entre la fecha de Aceptacion y la fecha de la Factura                
                    } else {
                        cpcLibroDeVentasHuaweiPojo.setDiasDeRetraso(0);
                    }

                }
                cpcLibroDeVentasHuaweiPojo.setEstadoFacturacion("");//Estado de la factura POD,FAC,PAC 
                cpcLibroDeVentasHuaweiPojo.setNoCompensable(BigDecimal.ZERO);// 87% de la factura de aquellas que estan en PAC o FAC                
                cpcLibroDeVentasHuaweiPojo.setNumeroFactura(listaFactura1.getNumeroFactura());
                cpcLibroDeVentasHuaweiPojo.setFechaEmisionFactura(DateUtils.dateFormatReporte(listaFactura1.getFechaFactura()));
                cpcLibroDeVentasHuaweiPojo.setMesDeFacturacion(DateUtils.dateFormatReporteMes(listaFactura1.getFechaAceptacion()));
                cpcLibroDeVentasHuaweiPojo.setNumeroFacturaInterno(listaFactura1.getNroFacturaInterno());
                cpcLibroDeVentasHuaweiPojo.setBatchNameDebitoFiscal(listaFactura1.getBatchNameDebitoFiscal());
                cpcLibroDeVentasHuaweiPojo.setBatchNameIngresos(listaFactura1.getBatchNameIngresos());
                cpcLibroDeVentasHuaweiPojo.setCuentaContable(listaFactura1.getCuentaContable());
                cpcLibroDeVentasHuaweiPojo.setGestionFactura(listaFactura1.getFechaFactura().getYear() + 1900);
                lista.add(cpcLibroDeVentasHuaweiPojo);
            }
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcLibroDeVentasHuaweiPojo> getReporteControlDeIngresos(Date fechaInicial, Date fechaFinal) throws Exception {
        try {
            System.out.println("fecha Inicial-- " + fechaInicial);
            System.out.println("fecha Final---- " + fechaFinal);
            List<ErpFactura> listaFactura = getFacturasOrdenadasPorClienteYRangoDeFechas(fechaInicial, fechaFinal);
            List<CpcLibroDeVentasHuaweiPojo> lista = new ArrayList<CpcLibroDeVentasHuaweiPojo>();
            CpcLibroDeVentasHuaweiPojo cpcLibroDeVentasHuaweiPojo;
            for (ErpFactura listaFactura1 : listaFactura) {
                cpcLibroDeVentasHuaweiPojo = new CpcLibroDeVentasHuaweiPojo();
                if (listaFactura1.getParEstadoFactura().getCodigo().equals(EnumEstadoFactura.ANULADA.getCodigo())) {
                    cpcLibroDeVentasHuaweiPojo.setFechaAceptacionFactura("");
                    cpcLibroDeVentasHuaweiPojo.setNitCliente(0L);
                    cpcLibroDeVentasHuaweiPojo.setNombreCliente("ANULADA");
                    cpcLibroDeVentasHuaweiPojo.setNumeroContrato("");
                    cpcLibroDeVentasHuaweiPojo.setNumeroContratoCliente("");
                    cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoDolares(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoBolivianos(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setTipoCambioContrato(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoUSD(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoBOB(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setAnticipo(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setEntrega(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setFacturacionPorPac(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setFacturacionPorFac(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setTipoFactura("Nula");
                    cpcLibroDeVentasHuaweiPojo.setMontoFacturaDolares(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setMontoFacturaBolivianos(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setMontoFacturaUSD(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setMontoFacturaBOB(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setIngresoFacturado(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setIngresoPorExportacion(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setIngresoPorVentasAlExteriorSinFacturar(BigDecimal.ZERO);//no calculado                 
                    cpcLibroDeVentasHuaweiPojo.setIngresoNeto(BigDecimal.ZERO);
                    cpcLibroDeVentasHuaweiPojo.setPorcentajeFacturacion(BigDecimal.ZERO); //no calculado 
                    cpcLibroDeVentasHuaweiPojo.setDiasDeRetraso(0);
                } else {
                    cpcLibroDeVentasHuaweiPojo.setFechaAceptacionFactura(DateUtils.dateFormatReporte(listaFactura1.getFechaAceptacion()));
                    cpcLibroDeVentasHuaweiPojo.setNitCliente(listaFactura1.getCppProveedorCliente().getNit());
                    if (listaFactura1.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                        cpcLibroDeVentasHuaweiPojo.setNombreCliente(listaFactura1.getCppProveedorCliente().getRazonSocial());
                    } else {
                        cpcLibroDeVentasHuaweiPojo.setNombreCliente(listaFactura1.getCppProveedorCliente().getPrimerApellido() + " " + listaFactura1.getCppProveedorCliente().getSegundoApellido() + " " + listaFactura1.getCppProveedorCliente().getNombre());
                    }
                    if (listaFactura1.getCpcPagoContrato() != null) {
                        cpcLibroDeVentasHuaweiPojo.setNumeroContrato(listaFactura1.getCpcPagoContrato().getCpcContrato().getNroContrato());
                        cpcLibroDeVentasHuaweiPojo.setNumeroContratoCliente(listaFactura1.getCpcPagoContrato().getCpcContrato().getNroContratoCliente());
                        cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoDolares(listaFactura1.getCpcPagoContrato().getCpcContrato().getMontoSegundaMoneda());
                        cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoBolivianos(listaFactura1.getCpcPagoContrato().getCpcContrato().getMontoPrimeraMoneda());
                        cpcLibroDeVentasHuaweiPojo.setTipoCambioContrato(listaFactura1.getCpcPagoContrato().getCpcContrato().getTipoCambio());
                        cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoUSD(listaFactura1.getCpcPagoContrato().getCpcContrato().getMontoSegundaMoneda());
                        cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoBOB(listaFactura1.getCpcPagoContrato().getCpcContrato().getMontoPrimeraMoneda());
                        List<CpcPagoContrato> listaPagosPorContrato = cpcPagoContratoService.getCpcPagoContratoByIdContrato(listaFactura1.getCpcPagoContrato().getCpcContrato().getIdContrato());
                        for (CpcPagoContrato listaPagosPorContrato1 : listaPagosPorContrato) {
                            if (listaPagosPorContrato1.getParTipoHito().getCodigo().equals(EnumTipoHito.ANTICIPO.getCodigo())) {
                                cpcLibroDeVentasHuaweiPojo.setAnticipo(listaPagosPorContrato1.getPorcentajeProgramado());
                            } else if (listaPagosPorContrato1.getParTipoHito().getCodigo().equals(EnumTipoHito.ENTREGA.getCodigo())) {
                                cpcLibroDeVentasHuaweiPojo.setEntrega(listaPagosPorContrato1.getPorcentajeProgramado());
                            } else if (listaPagosPorContrato1.getParTipoHito().getCodigo().equals(EnumTipoHito.ACEPTACION_PARCIAL.getCodigo())) {
                                cpcLibroDeVentasHuaweiPojo.setFacturacionPorPac(listaPagosPorContrato1.getPorcentajeProgramado());
                            } else if (listaPagosPorContrato1.getParTipoHito().getCodigo().equals(EnumTipoHito.ACEPTACION_FINAL.getCodigo())) {
                                cpcLibroDeVentasHuaweiPojo.setFacturacionPorFac(listaPagosPorContrato1.getPorcentajeProgramado());
                            }
                        }
                        if (listaFactura1.getCpcPagoContrato().getCpcContrato().getMontoPrimeraMoneda().compareTo(BigDecimal.ZERO) == 0) {
                            cpcLibroDeVentasHuaweiPojo.setPorcentajeFacturacion(((listaFactura1.getMontoSegundaMoneda().multiply(new BigDecimal("100"))).divide(listaFactura1.getCpcPagoContrato().getCpcContrato().getMontoSegundaMoneda(), 2, RoundingMode.HALF_UP)));
                        } else {
                            cpcLibroDeVentasHuaweiPojo.setPorcentajeFacturacion(((listaFactura1.getMontoPrimeraMoneda().multiply(new BigDecimal("100"))).divide(listaFactura1.getCpcPagoContrato().getCpcContrato().getMontoPrimeraMoneda(), 2, RoundingMode.HALF_UP)));
                        }
                    } else {
                        cpcLibroDeVentasHuaweiPojo.setNumeroContrato(listaFactura1.getNroContrato());
                        cpcLibroDeVentasHuaweiPojo.setNumeroContratoCliente("");
                        cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoDolares(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoBolivianos(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setTipoCambioContrato(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoUSD(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setMontoTotalContratoBOB(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setAnticipo(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setEntrega(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setFacturacionPorPac(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setFacturacionPorFac(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setPorcentajeFacturacion(BigDecimal.ZERO);
                    }
                    if (listaFactura1.getParTipoTransaccion().getCodigo().equals(EnumTipoTransaccion.EXPORTACIONES.getCodigo())) {
                        cpcLibroDeVentasHuaweiPojo.setTipoFactura("Export");
                        cpcLibroDeVentasHuaweiPojo.setIva(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setIt(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setIngresoFacturado(BigDecimal.ZERO);
                        cpcLibroDeVentasHuaweiPojo.setIngresoPorExportacion(listaFactura1.getMontoSegundaMoneda());
                    } else {
                        cpcLibroDeVentasHuaweiPojo.setTipoFactura("Local");
                        cpcLibroDeVentasHuaweiPojo.setIva(listaFactura1.getMontoPrimeraMoneda().multiply(new BigDecimal("0.13").setScale(2, BigDecimal.ROUND_HALF_UP)));
                        cpcLibroDeVentasHuaweiPojo.setIt(listaFactura1.getMontoPrimeraMoneda().multiply(new BigDecimal("0.03").setScale(2, BigDecimal.ROUND_HALF_UP)));
                        cpcLibroDeVentasHuaweiPojo.setIngresoFacturado(listaFactura1.getMontoPrimeraMoneda());
                        cpcLibroDeVentasHuaweiPojo.setIngresoPorExportacion(BigDecimal.ZERO);
                    }
                    cpcLibroDeVentasHuaweiPojo.setMontoFacturaDolares(listaFactura1.getMontoSegundaMoneda());
                    cpcLibroDeVentasHuaweiPojo.setMontoFacturaBolivianos(listaFactura1.getMontoPrimeraMoneda());
                    cpcLibroDeVentasHuaweiPojo.setMontoFacturaUSD(listaFactura1.getMontoSegundaMoneda());
                    cpcLibroDeVentasHuaweiPojo.setMontoFacturaBOB(listaFactura1.getMontoPrimeraMoneda());
                    cpcLibroDeVentasHuaweiPojo.setIngresoPorVentasAlExteriorSinFacturar(BigDecimal.ZERO);//no calculado                 
                    cpcLibroDeVentasHuaweiPojo.setIngresoNeto((listaFactura1.getMontoPrimeraMoneda().subtract(cpcLibroDeVentasHuaweiPojo.getIva())).setScale(2, BigDecimal.ROUND_HALF_UP));

                    cpcLibroDeVentasHuaweiPojo.setRevenueAccrued(listaFactura1.getMontoPrimeraMoneda().multiply(new BigDecimal("0.87").setScale(2, BigDecimal.ROUND_HALF_UP))); //consultar que pasa con facturas de exportacion
                    if (listaFactura1.getFechaAceptacion() != null) {
                        cpcLibroDeVentasHuaweiPojo.setDiasDeRetraso(DateUtils.diferenciaDiasReporte(listaFactura1.getFechaAceptacion(), listaFactura1.getFechaFactura()));//Calcular la diferencia entre la fecha de Aceptacion y la fecha de la Factura                
                    } else {
                        cpcLibroDeVentasHuaweiPojo.setDiasDeRetraso(0);
                    }

                }
                cpcLibroDeVentasHuaweiPojo.setEstadoFacturacion("");//Estado de la factura POD,FAC,PAC 
                cpcLibroDeVentasHuaweiPojo.setNoCompensable(BigDecimal.ZERO);// 87% de la factura de aquellas que estan en PAC o FAC                
                cpcLibroDeVentasHuaweiPojo.setNumeroFactura(listaFactura1.getNumeroFactura());
                cpcLibroDeVentasHuaweiPojo.setFechaEmisionFactura(DateUtils.dateFormatReporte(listaFactura1.getFechaFactura()));
                cpcLibroDeVentasHuaweiPojo.setMesDeFacturacion(DateUtils.dateFormatReporteMes(listaFactura1.getFechaAceptacion()));
                cpcLibroDeVentasHuaweiPojo.setNumeroFacturaInterno(listaFactura1.getNroFacturaInterno());
                cpcLibroDeVentasHuaweiPojo.setBatchNameDebitoFiscal(listaFactura1.getBatchNameDebitoFiscal());
                cpcLibroDeVentasHuaweiPojo.setBatchNameIngresos(listaFactura1.getBatchNameIngresos());
                cpcLibroDeVentasHuaweiPojo.setCuentaContable(listaFactura1.getCuentaContable());
                cpcLibroDeVentasHuaweiPojo.setGestionFactura(listaFactura1.getFechaFactura().getYear() + 1900);
                lista.add(cpcLibroDeVentasHuaweiPojo);
            }
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ErpFactura> getFacturasOrdenadasPorClienteContratoFecha(Long idProveedorCliente, int month, int year) throws Exception {
        try {
            String consulta = "";
            if (idProveedorCliente > 0L) {
                consulta = consulta + "and j.cppProveedorCliente.idProveedorCliente = " + idProveedorCliente + " ";
            }
            if (month > 0) {
                consulta = consulta + "and MONTH(j.fechaFactura) = " + month + " ";
            }
            if (year > 0) {
                consulta = consulta + "and YEAR(j.fechaFactura) = " + year + " ";
            }
            List<ErpFactura> lista = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and j.cpcPagoContrato <> null "
                    + "and j.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_COBRAR.getCodigo() + "' "
                    + consulta
                    + "order by j.cppProveedorCliente.idProveedorCliente, j.cpcPagoContrato.cpcContrato.idContrato asc"
            );
            List<ErpFactura> listaDos = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and j.cpcPagoContrato is null "
                    + "and j.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_COBRAR.getCodigo() + "' "
                    + consulta
                    + "order by j.cppProveedorCliente.idProveedorCliente asc"
            );
            lista.addAll(listaDos);
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ErpFactura> getFacturasOrdenadasPorClienteYRangoDeFechas(Date fechaInicial, Date fechaFinal) throws Exception {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
            String fechaInicialConsulta = formato.format(fechaInicial);
            String fechaFinalConsulta = formato.format(fechaFinal.getTime() + 86400000L);
            List<ErpFactura> lista = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and j.cpcPagoContrato <> null "
                    + "and j.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_COBRAR.getCodigo() + "' "
                    + "and j.fechaFactura >= '" + fechaInicialConsulta + "' "
                    + "and j.fechaFactura <= '" + fechaFinalConsulta + "' "
                    + "order by j.cppProveedorCliente.idProveedorCliente, j.cpcPagoContrato.cpcContrato.idContrato asc"
            );
            List<ErpFactura> listaDos = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and j.cpcPagoContrato is null "
                    + "and j.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_COBRAR.getCodigo() + "' "
                    + "and j.fechaFactura >= '" + fechaInicialConsulta + "' "
                    + "and j.fechaFactura <= '" + fechaFinalConsulta + "' "
                    + "order by j.cppProveedorCliente.idProveedorCliente asc"
            );
            lista.addAll(listaDos);
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Integer> getGestionesFacturadas(String modulo) throws Exception {
        try {
            List<Integer> gestiones = new ArrayList<Integer>();
            List<ErpFactura> lista = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and j.parTipoModulo.codigo = '" + modulo + "' "
                    + "order by j.fechaFactura asc ");
            if (!lista.isEmpty()) {
                int gestion;
                int gestionAnterior = 0;
                for (ErpFactura lista1 : lista) {
                    gestion = lista1.getFechaFactura().getYear() + 1900;
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

    public void persistFacturasReferenciadas(List<CpcConciliacionContablePojo> listaConciliacionContable) throws Exception {
        try {
            ErpFactura erpFactura;
            for (CpcConciliacionContablePojo listaConciliacionContable1 : listaConciliacionContable) {
                if (listaConciliacionContable1.getConciliado().equals("A")) {
                    erpFactura = dao.findOne(listaConciliacionContable1.getIdFacturaEmitida());
                    erpFactura.setReferenciado("A");
                    erpFactura.setBatchNameDebitoFiscal(listaConciliacionContable1.getBatchNameDebitoFiscal());
                    erpFactura.setNroContrato(listaConciliacionContable1.getSalesContractNo());
                    erpFactura.setCuentaContable(listaConciliacionContable1.getCuentaContable());
                    mergeCpcFacturaEmitida(erpFactura);
                } else if (listaConciliacionContable1.getConciliado().equals("M")) {
                    erpFactura = dao.findOne(listaConciliacionContable1.getIdFacturaEmitida());
                    erpFactura.setReferenciado("M");
                    erpFactura.setBatchNameDebitoFiscal(listaConciliacionContable1.getBatchNameDebitoFiscal());
                    erpFactura.setNroContrato(listaConciliacionContable1.getSalesContractNo());
                    erpFactura.setCuentaContable(listaConciliacionContable1.getCuentaContable());
                    mergeCpcFacturaEmitida(erpFactura);
                } else if (listaConciliacionContable1.getConciliado().equals("N")) {
                    erpFactura = dao.findOne(listaConciliacionContable1.getIdFacturaEmitida());
                    if (erpFactura.getReferenciado() != null && !erpFactura.getReferenciado().equals("N")) {
                        erpFactura.setReferenciado("N");
                        erpFactura.setBatchNameDebitoFiscal("");
                        erpFactura.setBatchNameIngresos("");
                        erpFactura.setNroContrato("");
                        erpFactura.setCuentaContable("");
                        mergeCpcFacturaEmitida(erpFactura);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean getVerdaderoSiElContratoEsModificable(Long idContrato) throws Exception {
        try {
            List<ErpFactura> lista = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and j.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_COBRAR.getCodigo() + "' "
                    + "and j.cpcPagoContrato.cpcContrato.idContrato = " + idContrato + " ");
            return lista.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcConciliacionContablePojo> seleccionaDatosDeExcel(InputStream fileInputStream) throws Exception {
        System.out.println("VALOR ARCHIVO:");
        System.out.println("VALOR ARCHIVO UNO:" + fileInputStream);
        try {
            List<CpcConciliacionContablePojo> listaConciliacionDebitoFiscal = new ArrayList<CpcConciliacionContablePojo>();
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            //Get first/desired sheet from the workbook
            //XSSFSheet sheet = workbook.getSheetAt(0);
            Sheet sheet = workbook.getSheetAt(0);
            int contador = 1;
            int startingRow = 1;
            int endingRow = lookForRowWithValue(sheet, "");
            sheet = workbook.getSheetAt(0);
            CpcConciliacionContablePojo cpcConciliacionContablePojo = null;
            while (startingRow <= endingRow) {
                Row row = sheet.getRow(startingRow);
//                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                Iterator<Cell> cellIterator = row.cellIterator();
                cpcConciliacionContablePojo = new CpcConciliacionContablePojo();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    //System.out.println("Index:: "+cell.getRowIndex()+" Tyoe::: "+cell.getCellType());
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            if (cell.getColumnIndex() == 4)//BatchName
                            {
                                System.out.print("BatchName::: " + cell.getStringCellValue() + "\t");
                                cpcConciliacionContablePojo.setBatchNameDebitoFiscal(cell.getStringCellValue());
                            }
                            if (cell.getColumnIndex() == 10)//Entry Name
                            {
                                System.out.print("Entry Item::: " + cell.getStringCellValue() + "\t");
                                cpcConciliacionContablePojo.setEntryItem(cell.getStringCellValue());
                            }
                            if (cell.getColumnIndex() == 13)//Enter Currency
                            {
                                System.out.print("Enter Currency::: " + cell.getStringCellValue() + "\t");
                                cpcConciliacionContablePojo.setEnterCurrency(cell.getStringCellValue());
                            }
                            if (cell.getColumnIndex() == 18)//Sales Contract No
                            {
                                System.out.print("Sales Contract::: " + cell.getStringCellValue() + "\t");
                                cpcConciliacionContablePojo.setSalesContractNo(cell.getStringCellValue());
                            }

                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            if (cell.getColumnIndex() == 6)//Account Code
                            {

                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                System.out.print("Account Code::: " + cell.getStringCellValue() + "\t");
                                cpcConciliacionContablePojo.setCuentaContable(cell.getStringCellValue());
                            }
                            if (cell.getColumnIndex() == 12)//Credits
                            {
                                System.out.print("Credits::: " + cell.getNumericCellValue() + "\t");
                                cpcConciliacionContablePojo.setCredits(Double.toString(cell.getNumericCellValue()));
                            }
                            if (cell.getColumnIndex() == 15)//Enter Credits
                            {
                                System.out.print("Enter Credits::: " + cell.getNumericCellValue() + "\t");
                                cpcConciliacionContablePojo.setEnterCredits(Double.toString(cell.getNumericCellValue()));
                            }
                            if (cell.getColumnIndex() == 18)//Sales Contract No
                            {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                System.out.print("Sales Contract::: " + cell.getStringCellValue() + "\t");
                                cpcConciliacionContablePojo.setSalesContractNo(cell.getStringCellValue());
                            }
                            break;
                    }
                }
                cpcConciliacionContablePojo.setNumero(contador);
                contador++;
                listaConciliacionDebitoFiscal.add(cpcConciliacionContablePojo);
                startingRow++;
            }
            return listaConciliacionDebitoFiscal;
//            listaConciliacion = erpFacturaService.getReferenciacionNotaDebitoFiscal(listaConciliacion);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InvalidFormatException ex) {
            Logger.getLogger(ImportWS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ImportWS.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // release resources, if any
//            log.info("LIST::: " + listaConciliacionDebitoFiscal);
        }
        return Collections.EMPTY_LIST;
    }

    public int lookForRowWithValue(Sheet sheet, String term) {       //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        boolean found = false;
        Cell cell = null;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                cell = cellIterator.next();
                if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().equals(term)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        if (cell != null) {
            return cell.getRowIndex();
        } else {
            return -1;
        }

    }

    public List<ErpFactura> getFacturasPorProveedorCliente(CppProveedorCliente cppProveedorCliente) throws Exception {
        try {
            List<ErpFactura> listaErpFactura = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and j.cppProveedorCliente.idProveedorCliente = " + cppProveedorCliente.getIdProveedorCliente() + " ");
            if (!listaErpFactura.isEmpty()) {
                return listaErpFactura;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CppLibroDeComprasPojo> getLibroDeComprasPojo(int mes, int anio) throws Exception {
        try {
            List<CppLibroDeComprasPojo> listaLibroDeComprasPojo = new ArrayList<CppLibroDeComprasPojo>();
            List<ErpFactura> listaFacturaEmitida = getLibroDeVentas(mes, anio, EnumModulo.CUENTAS_POR_PAGAR.getCodigo());
            CppLibroDeComprasPojo cppLibroDeComprasPojo;
            int numero = 1;
            for (ErpFactura listaFacturaEmitida1 : listaFacturaEmitida) {
                cppLibroDeComprasPojo = new CppLibroDeComprasPojo();
                if (listaFacturaEmitida1.getParEstadoFactura().getCodigo().equals(EnumEstadoFactura.ANULADA.getCodigo())) {
                    cppLibroDeComprasPojo.setNitProveedor(0L);
                    cppLibroDeComprasPojo.setNombreRazonSocial("ANULADA");
                    cppLibroDeComprasPojo.setNumeroAutorizacion(0L);
                    cppLibroDeComprasPojo.setImporteTotal(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                    cppLibroDeComprasPojo.setImporteNoSujetoACreditoFiscal(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                    cppLibroDeComprasPojo.setSubtotal(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                    cppLibroDeComprasPojo.setDescuentoBonificacionYrebajas(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                    cppLibroDeComprasPojo.setImporteBaseCreditoFiscal(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                    cppLibroDeComprasPojo.setCreditoFiscal(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                    cppLibroDeComprasPojo.setCodigoControl("0");
                } else {
                    cppLibroDeComprasPojo.setNitProveedor(listaFacturaEmitida1.getCppProveedorCliente().getNit());
                    if (listaFacturaEmitida1.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                        cppLibroDeComprasPojo.setNombreRazonSocial(listaFacturaEmitida1.getCppProveedorCliente().getRazonSocial());
                    } else {
                        cppLibroDeComprasPojo.setNombreRazonSocial(listaFacturaEmitida1.getCppProveedorCliente().getPrimerApellido() + " " + listaFacturaEmitida1.getCppProveedorCliente().getSegundoApellido() + " " + listaFacturaEmitida1.getCppProveedorCliente().getNombre());
                    }
                    cppLibroDeComprasPojo.setImporteTotal(listaFacturaEmitida1.getMontoPrimeraMoneda().setScale(2, BigDecimal.ROUND_HALF_UP));
                    cppLibroDeComprasPojo.setImporteNoSujetoACreditoFiscal((listaFacturaEmitida1.getIcePrimeraMoneda().add(listaFacturaEmitida1.getExcentoPrimeraMoneda())).setScale(2, BigDecimal.ROUND_HALF_UP));
                    cppLibroDeComprasPojo.setSubtotal((cppLibroDeComprasPojo.getImporteTotal().subtract(cppLibroDeComprasPojo.getImporteNoSujetoACreditoFiscal())).setScale(2, BigDecimal.ROUND_HALF_UP));
                    cppLibroDeComprasPojo.setDescuentoBonificacionYrebajas(listaFacturaEmitida1.getTotalDescuentoPrimeraMoneda().setScale(2, BigDecimal.ROUND_HALF_UP));
                    if (listaFacturaEmitida1.getParTipoDocumentoMercantil().getCodigo().equals(EnumTipoDocumentoMercantil.POLIZA_DE_IMPORTACION.getCodigo())) {
                        cppLibroDeComprasPojo.setImporteBaseCreditoFiscal(NumberUtils.redondeaBigDecimal(((listaFacturaEmitida1.getIvaPrimeraMoneda().multiply(CIEN)).divide(TRECE, 5, RoundingMode.HALF_UP)), 2));
//                        cppLibroDeComprasPojo.setImporteBaseCreditoFiscal(NumberUtils.redondeaBigDecimal(((listaFacturaEmitida1.getIvaPrimeraMoneda().multiply(CIEN)).divide(CATORCE_NOVENTA_Y_CUATRO, 5, RoundingMode.HALF_UP)), 2));
                    } else {
                        cppLibroDeComprasPojo.setImporteBaseCreditoFiscal((cppLibroDeComprasPojo.getSubtotal().subtract(cppLibroDeComprasPojo.getDescuentoBonificacionYrebajas())).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                    cppLibroDeComprasPojo.setCreditoFiscal(listaFacturaEmitida1.getIvaPrimeraMoneda());
                    if (listaFacturaEmitida1.getCodigoControl().equals("")) {
                        cppLibroDeComprasPojo.setCodigoControl("0");
                    } else {
                        cppLibroDeComprasPojo.setCodigoControl(listaFacturaEmitida1.getCodigoControl());
                    }
                    if (listaFacturaEmitida1.getParTipoCompra() != null) {
                        cppLibroDeComprasPojo.setTipoDeCompra(listaFacturaEmitida1.getParTipoCompra().getCodigo());
                    } else {
                        cppLibroDeComprasPojo.setTipoDeCompra("");
                    }
                    cppLibroDeComprasPojo.setNumeroAutorizacion(Long.parseLong(listaFacturaEmitida1.getNumeroAutorizacion()));
                }

                cppLibroDeComprasPojo.setNumero(numero);
                cppLibroDeComprasPojo.setFechaFacturaODui(listaFacturaEmitida1.getFechaFactura());
                cppLibroDeComprasPojo.setNumeroDeFactura(listaFacturaEmitida1.getNumeroFactura());
                cppLibroDeComprasPojo.setNumeroDeDui(listaFacturaEmitida1.getNumeroDui());
                listaLibroDeComprasPojo.add(cppLibroDeComprasPojo);
                numero++;
            }

            if (!listaLibroDeComprasPojo.isEmpty()) {
                return listaLibroDeComprasPojo;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ErpFacturaRetencionPojo> getFacturaRetencionByIdProveedorCliente(Long idProveedorCliente) throws Exception {
        try {
            List<ErpFacturaRetencionPojo> listaFacturaRetencion = new ArrayList<ErpFacturaRetencionPojo>();
            ErpFacturaRetencionPojo erpFacturaRetencionPojo;
            List<ErpFactura> listaFactura = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and j.cppProveedorCliente.idProveedorCliente = '" + idProveedorCliente + "' "
                    + "and j.parTipoModulo.codigo = 'CPP' "
                    + "and j.parEstadoPago.codigo = 'FACT' "
                    + "and j.parEstadoFactura.codigo = 'V' ");
            List<CppRetencion> listaRetencion = cppRetencionService.getRetencionesByIdProveedorCliente(idProveedorCliente);
            if (!listaFactura.isEmpty()) {
                for (ErpFactura listaFactura1 : listaFactura) {
                    erpFacturaRetencionPojo = new ErpFacturaRetencionPojo();
                    erpFacturaRetencionPojo.setIdFacturaRetencion(listaFactura1.getIdFactura());
                    erpFacturaRetencionPojo.setFechaRegistro(listaFactura1.getFechaFactura());
                    erpFacturaRetencionPojo.setNroContrato(listaFactura1.getNroContrato());
                    erpFacturaRetencionPojo.setNroFacturaRetencion(listaFactura1.getNumeroFactura().toString());
                    erpFacturaRetencionPojo.setNumeroAutorizacion(listaFactura1.getNumeroAutorizacion());
                    erpFacturaRetencionPojo.setModalidadTransaccion(listaFactura1.getParModalidadTransaccion().getDescripcion());
                    erpFacturaRetencionPojo.setTipoTransaccion(listaFactura1.getParTipoTransaccion().getDescripcion());
                    erpFacturaRetencionPojo.setMontoEmitido(listaFactura1.getMontoPrimeraMoneda());
                    erpFacturaRetencionPojo.setMontoABancarizar(montoABancarizarPorFacturaRetencion(listaFactura1.getIdFactura(), EnumTipoDocumentoMercantil.FACTURA.getCodigo()));
                    erpFacturaRetencionPojo.setMontoBancarizado(erpFacturaRetencionPojo.getMontoEmitido().subtract(erpFacturaRetencionPojo.getMontoABancarizar()));
                    erpFacturaRetencionPojo.setTipoDocumentoMercantil(listaFactura1.getParTipoDocumentoMercantil().getCodigo());
                    listaFacturaRetencion.add(erpFacturaRetencionPojo);
                }
            }
            if (!listaRetencion.isEmpty()) {
                for (CppRetencion cppRetencion : listaRetencion) {
                    erpFacturaRetencionPojo = new ErpFacturaRetencionPojo();
                    erpFacturaRetencionPojo.setIdFacturaRetencion(cppRetencion.getIdRetencion());
                    erpFacturaRetencionPojo.setFechaRegistro(cppRetencion.getFechaRegistro());
                    erpFacturaRetencionPojo.setNroContrato(cppRetencion.getNroContrato());
                    erpFacturaRetencionPojo.setNroFacturaRetencion(cppRetencion.getNroRetencionInterno());
                    erpFacturaRetencionPojo.setNumeroAutorizacion("0");
                    erpFacturaRetencionPojo.setModalidadTransaccion(cppRetencion.getParModalidadTransaccion().getDescripcion());
                    erpFacturaRetencionPojo.setTipoTransaccion(cppRetencion.getParTipoTransaccion().getDescripcion());
                    erpFacturaRetencionPojo.setMontoEmitido(cppRetencion.getMontoPrimeraMoneda());
                    erpFacturaRetencionPojo.setMontoABancarizar(montoABancarizarPorFacturaRetencion(cppRetencion.getIdRetencion(), EnumTipoDocumentoMercantil.RETENCION.getCodigo()));
                    erpFacturaRetencionPojo.setMontoBancarizado(erpFacturaRetencionPojo.getMontoEmitido().subtract(erpFacturaRetencionPojo.getMontoABancarizar()));
                    erpFacturaRetencionPojo.setTipoDocumentoMercantil(cppRetencion.getParTipoDocumentoMercantil().getCodigo());
                    listaFacturaRetencion.add(erpFacturaRetencionPojo);
                }
            }
            if (!listaFacturaRetencion.isEmpty()) {
                return listaFacturaRetencion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal montoABancarizarPorFacturaRetencion(Long idFacturaRetencion, String tipoFacturaRetencion) throws Exception {
        try {
            BigDecimal montoABancarizar = BigDecimal.ZERO;
            BigDecimal montoAcumulado = cpcPagoService.montoPagadoAcumuladoFacturaRetencion(idFacturaRetencion, tipoFacturaRetencion);
            if (tipoFacturaRetencion.equals(EnumTipoDocumentoMercantil.FACTURA.getCodigo())) {
                ErpFactura erpFactura = getCpcFacturaEmitidaById(idFacturaRetencion);
                montoABancarizar = erpFactura.getMontoPrimeraMoneda().subtract(montoAcumulado);
            } else {
                CppRetencion cppRetencion = cppRetencionService.findCppRetencionById(idFacturaRetencion);
                montoABancarizar = cppRetencion.getMontoPrimeraMoneda().subtract(montoAcumulado);
            }
            return montoABancarizar;
        } catch (Exception e) {
            throw e;
        }
    }

    public void noBancarizaFacturaRetencion(Long idFacturaRetencion, String tipoFacturaRetencion) throws Exception {
        try {
            ParEstadoPago parEstadoPago = parValorService.findParEstadoPagoByCodigo(EnumEstadoPago.NO_BANCARIZADO.getCodigo());
            if (tipoFacturaRetencion.equals(EnumTipoDocumentoMercantil.FACTURA.getCodigo())) {
                ErpFactura erpFactura = dao.findOne(idFacturaRetencion);
                erpFactura.setParEstadoPago(parEstadoPago);
                mergeCpcFacturaEmitida(erpFactura);
            } else {
                CppRetencion cppRetencion = cppRetencionService.findCppRetencionById(idFacturaRetencion);
                cppRetencion.setParEstadoPago(parEstadoPago);
                cppRetencionService.mergeCppRetencion(cppRetencion);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcLibroDeBancarizacionPorVentasPojo> getLibroDeBancarizacionPorCompras(int mes, int anio) throws Exception {
        try {
            List<CpcLibroDeBancarizacionPorVentasPojo> lista = new ArrayList<CpcLibroDeBancarizacionPorVentasPojo>();
            List<CpcPago> listaPagos = cpcPagoService.listaPagosOrdenados(mes, anio, EnumModulo.CUENTAS_POR_PAGAR.getCodigo());
            CpcLibroDeBancarizacionPorVentasPojo cpcLibroDeBancarizacionPorVentasPojo;
            for (CpcPago cpcPago : listaPagos) {
                cpcLibroDeBancarizacionPorVentasPojo = new CpcLibroDeBancarizacionPorVentasPojo();
                if (cpcPago.getErpFactura() != null) {
                    if (cpcPago.getErpFactura().getParModalidadTransaccion().getCodigo().equals(EnumModalidadTransaccion.CONTADO.getCodigo())) {
                        cpcLibroDeBancarizacionPorVentasPojo.setModalidadDeTransaccion(1);
                    } else {
                        cpcLibroDeBancarizacionPorVentasPojo.setModalidadDeTransaccion(2);
                    }
                    cpcLibroDeBancarizacionPorVentasPojo.setFechaFactura(cpcPago.getErpFactura().getFechaFactura());
                    cpcLibroDeBancarizacionPorVentasPojo.setTipoTransaccion(Integer.parseInt(cpcPago.getErpFactura().getParTipoTransaccion().getCodigo()));
                    if (!cpcPago.getErpFactura().getCppProveedorCliente().getNit().toString().equals("")) {
                        cpcLibroDeBancarizacionPorVentasPojo.setNitCiCliente(cpcPago.getErpFactura().getCppProveedorCliente().getNit().toString());
                    } else if (!cpcPago.getErpFactura().getCppProveedorCliente().getNumeroDocumento().equals("")) {
                        cpcLibroDeBancarizacionPorVentasPojo.setNitCiCliente(cpcPago.getErpFactura().getCppProveedorCliente().getNumeroDocumento());
                    } else {
                        cpcLibroDeBancarizacionPorVentasPojo.setNitCiCliente("0");
                    }
                    if (cpcPago.getErpFactura().getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                        cpcLibroDeBancarizacionPorVentasPojo.setRazonSocialNombreCliente(cpcPago.getErpFactura().getCppProveedorCliente().getRazonSocial());
                    } else {
                        cpcLibroDeBancarizacionPorVentasPojo.setRazonSocialNombreCliente(cpcPago.getErpFactura().getCppProveedorCliente().getNombre() + " " + cpcPago.getErpFactura().getCppProveedorCliente().getPrimerApellido() + " " + cpcPago.getErpFactura().getCppProveedorCliente().getSegundoApellido());
                    }
                    if (cpcPago.getErpFactura().getParTipoDocumentoMercantil().getCodigo().equals(EnumTipoDocumentoMercantil.FACTURA.getCodigo())) {
                        cpcLibroDeBancarizacionPorVentasPojo.setNumeroFactura(cpcPago.getErpFactura().getNumeroFactura().toString());
                    } else {
                        cpcLibroDeBancarizacionPorVentasPojo.setNumeroFactura(cpcPago.getErpFactura().getNumeroDui());
                    }
                    cpcLibroDeBancarizacionPorVentasPojo.setMontoFactura(cpcPago.getErpFactura().getMontoPrimeraMoneda());
                    cpcLibroDeBancarizacionPorVentasPojo.setNroAutorizacion(Long.parseLong(cpcPago.getErpFactura().getNumeroAutorizacion()));
                } else {
                    if (cpcPago.getCppRetencion().getParModalidadTransaccion().getCodigo().equals(EnumModalidadTransaccion.CONTADO.getCodigo())) {
                        cpcLibroDeBancarizacionPorVentasPojo.setModalidadDeTransaccion(1);
                    } else {
                        cpcLibroDeBancarizacionPorVentasPojo.setModalidadDeTransaccion(2);
                    }
                    cpcLibroDeBancarizacionPorVentasPojo.setFechaFactura(cpcPago.getCppRetencion().getFechaRegistro());
                    cpcLibroDeBancarizacionPorVentasPojo.setTipoTransaccion(Integer.parseInt(cpcPago.getCppRetencion().getParTipoTransaccion().getCodigo()));
                    if (!cpcPago.getCppRetencion().getCppProveedorCliente().getNit().toString().equals("")) {
                        cpcLibroDeBancarizacionPorVentasPojo.setNitCiCliente(cpcPago.getCppRetencion().getCppProveedorCliente().getNit().toString());
                    } else if (!cpcPago.getErpFactura().getCppProveedorCliente().getNumeroDocumento().equals("")) {
                        cpcLibroDeBancarizacionPorVentasPojo.setNitCiCliente(cpcPago.getCppRetencion().getCppProveedorCliente().getNumeroDocumento());
                    } else {
                        cpcLibroDeBancarizacionPorVentasPojo.setNitCiCliente("0");
                    }
                    if (cpcPago.getCppRetencion().getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                        cpcLibroDeBancarizacionPorVentasPojo.setRazonSocialNombreCliente(cpcPago.getCppRetencion().getCppProveedorCliente().getRazonSocial());
                    } else {
                        cpcLibroDeBancarizacionPorVentasPojo.setRazonSocialNombreCliente(cpcPago.getCppRetencion().getCppProveedorCliente().getNombre() + " " + cpcPago.getCppRetencion().getCppProveedorCliente().getPrimerApellido() + " " + cpcPago.getCppRetencion().getCppProveedorCliente().getSegundoApellido());
                    }
                    cpcLibroDeBancarizacionPorVentasPojo.setNumeroFactura(cpcPago.getCppRetencion().getNumeroRetencion().toString());
                    cpcLibroDeBancarizacionPorVentasPojo.setMontoFactura(cpcPago.getCppRetencion().getMontoPrimeraMoneda());
                    cpcLibroDeBancarizacionPorVentasPojo.setNroAutorizacion(4L);
                }
                cpcLibroDeBancarizacionPorVentasPojo.setNroCuentaDocumentoPago(cpcPago.getDocumentoPago().getNroCtaEntidadEmisora());
                cpcLibroDeBancarizacionPorVentasPojo.setMontoPagadoDocPago(cpcPago.getMontoPagadoPrimeraMoneda());
                cpcLibroDeBancarizacionPorVentasPojo.setMontoAcumulado(cpcPago.getMontoAcumuladoPrimeraMoneda());
                cpcLibroDeBancarizacionPorVentasPojo.setNitEntidadFinanciera(Integer.parseInt(cpcPago.getDocumentoPago().getParBanco().getNit()));
                cpcLibroDeBancarizacionPorVentasPojo.setNroDocumentoPago(cpcPago.getDocumentoPago().getNroDocumento().toString());
                cpcLibroDeBancarizacionPorVentasPojo.setTipoDeDocumentoDePago(Integer.parseInt(cpcPago.getDocumentoPago().getParTipoDocumentoPago().getCodigo()));
                cpcLibroDeBancarizacionPorVentasPojo.setFechaDelDocumentoDePago(cpcPago.getDocumentoPago().getFechaDocumentoPago());
                lista.add(cpcLibroDeBancarizacionPorVentasPojo);
            }
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Integer> getGestionesDeFacturasRetencionesBancarizadas() throws Exception {
        try {
            List<Integer> gestiones = new ArrayList<Integer>();
            List<Integer> gestionesRetencion = new ArrayList<Integer>();
            List<Integer> gestionesUnion = new ArrayList<Integer>();
            List<ErpFactura> lista = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and j.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_PAGAR.getCodigo() + "' "
                    + "and j.parEstadoPago.codigo = '" + EnumEstadoPago.BANCARIZADO.getCodigo() + "' "
                    + "order by j.fechaFactura asc ");
            List<CppRetencion> listaRetencion = cppRetencionService.listaRetencionesPorEstadoPago(EnumEstadoPago.BANCARIZADO.getCodigo());
            int gestion;
            int gestionAnterior = 0;
            if (!lista.isEmpty()) {
                for (ErpFactura lista1 : lista) {
                    gestion = lista1.getFechaFactura().getYear() + 1900;
                    if (gestionAnterior < gestion) {
                        gestiones.add(gestion);
                        gestionAnterior = gestion;
                    }
                }
            }
            if (!listaRetencion.isEmpty()) {
                gestionAnterior = 0;
                for (CppRetencion lista1 : listaRetencion) {
                    gestion = lista1.getFechaRegistro().getYear() + 1900;
                    if (gestionAnterior < gestion) {
                        gestionesRetencion.add(gestion);
                        gestionAnterior = gestion;
                    }
                }
            }

            return gestiones;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ErpFacturasBancariasPojo> importaExcelFacturasBancarias(InputStream fileInputStream) throws Exception {
        System.out.println("ENTRO WS::");
        try {
            System.out.println("ENTRO XLS:" + fileInputStream);
            List<ErpFacturasBancariasPojo> listaFacturasBancarias = new ArrayList<ErpFacturasBancariasPojo>();
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            //Get first/desired sheet from the workbook
            //XSSFSheet sheet = workbook.getSheetAt(0);
            Date dteFecha;
            Sheet sheet = workbook.getSheetAt(0);
            int contador = 1;
            int startingRow = 13;
            int endingRow = lookForRowWithValue(sheet, "");
            sheet = workbook.getSheetAt(0);
            ErpFacturasBancariasPojo erpFacturasBancariasPojo;
            while (startingRow <= endingRow) {
                Row row = sheet.getRow(startingRow);
//                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                Iterator<Cell> cellIterator = row.cellIterator();
                erpFacturasBancariasPojo = new ErpFacturasBancariasPojo();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    //System.out.println("Index:: "+cell.getRowIndex()+" Tyoe::: "+cell.getCellType());
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.out.println("STRING:" + cell.getColumnIndex());
                            if (cell.getColumnIndex() == 6)//Fecha 
                            {
                                dteFecha = new Date(cell.getStringCellValue());
                                erpFacturasBancariasPojo.setFechaFactura(dteFecha);
                            }
                            if (cell.getColumnIndex() == 7)//Numero Autorizacion
                            {
                                erpFacturasBancariasPojo.setNumeroAutorizacion(cell.getStringCellValue());
                            }
                            if (cell.getColumnIndex() == 16)//Codigo
                            {
                                erpFacturasBancariasPojo.setCodigo(cell.getStringCellValue());
                            }

                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.println("NUMERIC:" + cell.getColumnIndex());
                            if (cell.getColumnIndex() == 3)//Numero Factura
                            {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                erpFacturasBancariasPojo.setNumeroFactura(Long.parseLong(cell.getStringCellValue()));
                            }
                            if (cell.getColumnIndex() == 13)//Numero de Autorizacion
                            {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                erpFacturasBancariasPojo.setNumeroAutorizacion(cell.getStringCellValue());
                            }
                            if (cell.getColumnIndex() == 18)//Importe
                            {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                erpFacturasBancariasPojo.setImporte(new BigDecimal(cell.getStringCellValue()));
                            }

                            break;
                    }
                }
                listaFacturasBancarias.add(erpFacturasBancariasPojo);
                startingRow++;
            }
            return listaFacturasBancarias;

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InvalidFormatException ex) {
            Logger.getLogger(ImportWS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ImportWS.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // release resources, if any
//            log.info("LIST::: " + listaConciliacionDebitoFiscal);
        }
        return Collections.EMPTY_LIST;
    }

    public void guardaFacturasBancarias(ErpFacturasDatosPojo erpFacturasDatosPojo) throws Exception {
        try {
            for (ErpFacturasBancariasPojo erpFacturasBancariasPojo : erpFacturasDatosPojo.getListaFacturasBancarias()) {
                System.out.println("CODIGO:" + erpFacturasBancariasPojo.getCodigo());

                ErpFactura erpFactura = new ErpFactura();

                erpFactura.setMontoPrimeraMoneda(erpFacturasBancariasPojo.getImporte());
                erpFactura.setNumeroFactura(erpFacturasBancariasPojo.getNumeroFactura());
                erpFactura.setFechaFactura(erpFacturasBancariasPojo.getFechaFactura());
                erpFactura.setNumeroAutorizacion(erpFacturasBancariasPojo.getNumeroAutorizacion());
                erpFactura.setCodigoControl(erpFacturasBancariasPojo.getCodigo());

                ParTipoDocumentoMercantil parTipoDocumentoMercantil = new ParTipoDocumentoMercantil();
                parTipoDocumentoMercantil.setCodigo("FACT");
                erpFactura.setParTipoDocumentoMercantil(parTipoDocumentoMercantil);

                ParTipoTransaccion parTipoTransaccion = new ParTipoTransaccion();
                parTipoTransaccion.setCodigo("1");
                erpFactura.setParTipoTransaccion(parTipoTransaccion);
//                erpFactura.setParTipoTransaccion(erpFacturasDatosPojo.getParTipoTransaccion());
//                erpFactura.setParModalidadTransaccion(erpFacturasDatosPojo.getParModalidadTransaccion());

                ParEstadoFactura parEstadoFactura = new ParEstadoFactura();
                parEstadoFactura.setCodigo("VIG");
                erpFactura.setParEstadoFactura(parEstadoFactura);

                CppProveedorCliente cliente = new CppProveedorCliente();
                cliente.setIdProveedorCliente(57L);
                erpFactura.setCppProveedorCliente(cliente);

                erpFactura.setConcepto("");
                erpFactura.setGastosSeguro(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                erpFactura.setGastosTransporte(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                erpFactura.setGlosa("");
                erpFactura.setIdCbteContable(0L);
                erpFactura.setIncoterm("");
                erpFactura.setMontoSegundaMoneda(BigDecimal.ZERO.setScale(5, BigDecimal.ROUND_HALF_UP));
                erpFactura.setOtrosGastos(BigDecimal.ZERO.setScale(5, BigDecimal.ROUND_HALF_UP));
                erpFactura.setPuertoDestino("");
                erpFactura.setSeguroInternacional(BigDecimal.ZERO.setScale(5, BigDecimal.ROUND_HALF_UP));
                erpFactura.setTipoCambioFactura(BigDecimal.ZERO.setScale(5, BigDecimal.ROUND_HALF_UP));
                erpFactura.setTotalDescuentoPrimeraMoneda(BigDecimal.ZERO.setScale(5, BigDecimal.ROUND_HALF_UP));
                erpFactura.setTotalDescuentoSegundaMoneda(BigDecimal.ZERO.setScale(5, BigDecimal.ROUND_HALF_UP));
                erpFactura.setTotalFob(BigDecimal.ZERO.setScale(5, BigDecimal.ROUND_HALF_UP));
                erpFactura.setTransporteInternacional(BigDecimal.ZERO.setScale(5, BigDecimal.ROUND_HALF_UP));
                erpFactura.setValorBruto(BigDecimal.ZERO.setScale(5, BigDecimal.ROUND_HALF_UP));
                erpFactura.setCpcDosificacion(null);
                erpFactura.setCpcPagoContrato(null);
                erpFactura.setIcePrimeraMoneda(BigDecimal.ZERO.setScale(5, BigDecimal.ROUND_HALF_UP));
                erpFactura.setIceSegundaMoneda(BigDecimal.ZERO.setScale(5, BigDecimal.ROUND_HALF_UP));
                erpFactura.setNroFacturaInterno("");
                erpFactura.setCuentaBancaria(null);
                erpFactura.setNroContrato("");
                erpFactura.setNumeroDui("0");
                erpFactura.setExcentoPrimeraMoneda(BigDecimal.ZERO.setScale(5, BigDecimal.ROUND_HALF_UP));
                erpFactura.setExcentoSegundaMoneda(BigDecimal.ZERO.setScale(5, BigDecimal.ROUND_HALF_UP));
                erpFactura.setIvaPrimeraMoneda(BigDecimal.ZERO.setScale(5, BigDecimal.ROUND_HALF_UP));
                erpFactura.setIvaSegundaMoneda(BigDecimal.ZERO.setScale(5, BigDecimal.ROUND_HALF_UP));

                ParTipoModulo parTipoModulo = new ParTipoModulo();
                parTipoModulo.setCodigo("CPP");
                erpFactura.setParTipoModulo(parTipoModulo);

                guardaFacturaEmitida(erpFactura);

            }
        } catch (Exception e) {
            throw e;
        }

    }

    public List<CpcConciliacionContablePojo> getReferenciacionNotaCreditoFiscal(List<CpcConciliacionContablePojo> listaCreditoFiscal) throws Exception {
        try {
            List<CpcConciliacionContablePojo> listaConciliacionContablePojo = new ArrayList<CpcConciliacionContablePojo>();
            CpcConciliacionContablePojo cpcConciliacionContablePojo;
            List<ErpFactura> lista = dao.find(""
                    + "select j "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and (j.parEstadoFactura.codigo = '" + EnumEstadoFactura.VALIDA.getCodigo() + "' "
                    + "or j.parEstadoFactura.codigo = '" + EnumEstadoFactura.ANULADA.getCodigo() + "') "
                    + "and j.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_PAGAR.getCodigo() + "' "
                    + "and (j.referenciadoIngresos is null or j.referenciadoIngresos = 'N') "
                    + "order by j.numeroFactura asc");
            if (!lista.isEmpty()) {
                String[] entryItemVector;
                String entryItem = "";
                for (ErpFactura cpcFacturaEmitida : lista) {
                    cpcConciliacionContablePojo = new CpcConciliacionContablePojo();
                    cpcConciliacionContablePojo.setFechaFactura(cpcFacturaEmitida.getFechaFactura());
                    cpcConciliacionContablePojo.setIdFacturaEmitida(cpcFacturaEmitida.getIdFactura());
                    cpcConciliacionContablePojo.setNroFacturaInterno(cpcFacturaEmitida.getNroFacturaInterno());
                    cpcConciliacionContablePojo.setConciliado("N");
                    cpcConciliacionContablePojo.setDebitoFiscal(cpcFacturaEmitida.getIvaPrimeraMoneda());
                    cpcConciliacionContablePojo.setNumeroDeFactura(cpcFacturaEmitida.getNumeroFactura());
                    cpcConciliacionContablePojo.setNroContrato(cpcFacturaEmitida.getNroContrato());
                    if (cpcFacturaEmitida.getReferenciado() != null && cpcFacturaEmitida.getReferenciado().equals("M")) {
                        cpcConciliacionContablePojo.setConciliado(cpcFacturaEmitida.getReferenciado());
                        cpcConciliacionContablePojo.setBatchNameDebitoFiscal(cpcFacturaEmitida.getBatchNameDebitoFiscal());
                        cpcConciliacionContablePojo.setBatchNameIngresos(cpcFacturaEmitida.getBatchNameIngresos());
                    }
                    Boolean esAnulado = false;
                    if (!listaCreditoFiscal.isEmpty()) {
                        for (CpcConciliacionContablePojo cpcConciliacionContableExcel : listaCreditoFiscal) {
                            if (cpcConciliacionContableExcel.getEntryItem().startsWith("BLV")) {
                                entryItemVector = cpcConciliacionContableExcel.getEntryItem().split("-");
                                entryItem = entryItemVector[0];
                                esAnulado = entryItemVector.length == 0;
                                if (cpcFacturaEmitida.getNroFacturaInterno() != null && cpcFacturaEmitida.getNroFacturaInterno().equals(entryItem)) {
                                    if ((cpcFacturaEmitida.getIvaPrimeraMoneda().compareTo(new BigDecimal(cpcConciliacionContableExcel.getCredits())) == 0)
                                            || (esAnulado && cpcFacturaEmitida.getParEstadoFactura().getCodigo().equals(EnumEstadoFactura.ANULADA.getCodigo()))) {
                                        cpcConciliacionContablePojo.setBatchNameDebitoFiscal(cpcConciliacionContableExcel.getBatchNameDebitoFiscal());
                                        cpcConciliacionContablePojo.setConciliado("A");
                                        cpcConciliacionContablePojo.setEntryItem(cpcConciliacionContableExcel.getEntryItem());
                                        cpcConciliacionContablePojo.setCuentaContable(cpcConciliacionContableExcel.getCuentaContable());
                                        cpcConciliacionContablePojo.setCredits(cpcConciliacionContableExcel.getCredits());
                                        cpcConciliacionContablePojo.setSalesContractNo(cpcConciliacionContableExcel.getSalesContractNo());
                                        cpcConciliacionContablePojo.setGlDate(cpcConciliacionContableExcel.getGlDate());
                                        cpcConciliacionContablePojo.setNumero(cpcConciliacionContableExcel.getNumero());
                                    }
                                }
                            }
                        }
                    }
                    listaConciliacionContablePojo.add(cpcConciliacionContablePojo);
                }
            }
            if (!listaConciliacionContablePojo.isEmpty()) {
                return listaConciliacionContablePojo;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

}
