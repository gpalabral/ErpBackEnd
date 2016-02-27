package com.bap.erp.servicios.impl.cpc;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.enums.EnumTipoDocumentoMercantil;
import com.bap.erp.enums.EnumTipoMoneda;
import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.modelo.cpc.CpcPago;
import com.bap.erp.servicios.cpc.CpcPagoService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CpcPagoServiceImpl implements CpcPagoService {

    IGenericDao<CpcPago> dao;

//        @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public void setDao(IGenericDao<CpcPago> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CpcPago.class);
    }

    public CpcPago persistCpcPago(CpcPago cpcPago) throws Exception {
        try {
            cpcPago.setIdPago(null);
            cpcPago.setUsuarioAlta("TEST");
            cpcPago.setFechaAlta(new Date());
            dao.create(cpcPago);
            return cpcPago;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CpcPago> getCpcPago() throws Exception {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public CpcPago getCpcPagoByIdPago(Long idPago) throws Exception {
        try {
            return dao.findOne(idPago);
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal saldoPagosPorFactura(ErpFactura cpcFacturaEmitida, String montoPagado) throws Exception {
        try {
            BigDecimal saldo = cpcFacturaEmitida.getMontoPrimeraMoneda();
            BigDecimal saldoSegundaMoneda = cpcFacturaEmitida.getMontoSegundaMoneda();
            String consulta = ""
                    //                    + "select sum(j." + montoPagado + ") as suma "
                    + "select j "
                    + "from CpcPago j "
                    + "where j.fechaBaja is null "
                    + "and j.erpFactura.idFactura = " + cpcFacturaEmitida.getIdFactura() + " ";
            List<CpcPago> listaPagos = dao.find(consulta);
            if (!listaPagos.isEmpty()) {
                for (CpcPago listaPago : listaPagos) {
                    if (montoPagado.equals("montoPagadoPrimeraMoneda")) {
                        saldo = saldo.subtract(listaPago.getMontoPagadoPrimeraMoneda());
                    } else {
                        saldoSegundaMoneda = saldoSegundaMoneda.subtract(listaPago.getMontoPagadoSegundaMoneda());
                    }
                }
            } else if (montoPagado.equals("montoPagadoPrimeraMoneda")) {
                saldo = cpcFacturaEmitida.getMontoPrimeraMoneda();
            } else {
                saldoSegundaMoneda = cpcFacturaEmitida.getMontoSegundaMoneda();
            }
            if (montoPagado.equals("montoPagadoPrimeraMoneda")) {
                return saldo;
            }
            return saldoSegundaMoneda;
        } catch (Exception e) {
            throw e;
        }

    }

    public List<CpcPago> listaPagosOrdenados(int month, int year, String modulo) throws Exception {
        try {            
            List<CpcPago> lista = dao.find("select j "
                    + "from CpcPago j "
                    + "where j.fechaBaja is null "
                    + "and j.parTipoModulo.codigo = '"+modulo+"' "
                    + "and MONTH(j.documentoPago.fechaDocumentoPago) = " + month + " "
                    + "and YEAR(j.documentoPago.fechaDocumentoPago) = " + year + " "
                    + "order by j.documentoPago.idDocumentoPago, j.documentoPago.fechaDocumentoPago asc");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal montoPagadoAcumulado(Long idFactura, String tipoMoneda) throws Exception {
        try {
            BigDecimal acumulado = new BigDecimal(BigInteger.ZERO);
            List<CpcPago> lista = dao.find(""
                    + "select j "
                    + "from CpcPago j "
                    + "where j.fechaBaja is null "
                    + "and j.erpFactura.idFactura = " + idFactura + "");
            if (!lista.isEmpty()) {
                for (CpcPago lista1 : lista) {
                    if (tipoMoneda.equals(EnumTipoMoneda.BOLIVIANOS.getCodigo())) {
                        acumulado = acumulado.add(lista1.getMontoPagadoPrimeraMoneda());
                    } else {
                        acumulado = acumulado.add(lista1.getMontoPagadoSegundaMoneda());
                    }   
                }                
                return acumulado;
            }
            return BigDecimal.ZERO;
        } catch (Exception e) {
            throw e;
        }
    }

    public BigDecimal montoPagadoAcumuladoFacturaRetencion(Long idFacturaRetencion, String tipoFacturaRetencion) throws Exception {
        try {
            BigDecimal acumulado = new BigDecimal(BigInteger.ZERO);
            String consulta = "";
            if(tipoFacturaRetencion.equals(EnumTipoDocumentoMercantil.FACTURA.getCodigo())){
                consulta = "and j.erpFactura.idFactura = "+idFacturaRetencion+" ";
            }else{
                consulta = "and j.cppRetencion.idRetencion = "+idFacturaRetencion+" ";
            }
            List<CpcPago> lista = dao.find(""
                    + "select j "
                    + "from CpcPago j "
                    + "where j.fechaBaja is null "
                    + consulta);
            for (CpcPago lista1 : lista) {
                acumulado = acumulado.add(lista1.getMontoPagadoPrimeraMoneda());
            }
            return acumulado;
        } catch (Exception e) {
            throw e;
        }
    }

}
