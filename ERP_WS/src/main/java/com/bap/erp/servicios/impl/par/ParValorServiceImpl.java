package com.bap.erp.servicios.impl.par;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.enums.EnumCaracteristicaEspecial;
import com.bap.erp.modelo.par.ParActividadEconomica;
import com.bap.erp.modelo.par.ParBanco;
import com.bap.erp.modelo.par.ParCaracteristicaEspecial;
import com.bap.erp.modelo.par.ParDepartamento;
import com.bap.erp.modelo.par.ParEstado;
import com.bap.erp.modelo.par.ParEstadoFactura;
import com.bap.erp.modelo.par.ParEstadoPago;
import com.bap.erp.modelo.par.ParEstadoProceso;
import com.bap.erp.modelo.par.ParFormaDePago;
import com.bap.erp.modelo.par.ParLocalizacion;
import com.bap.erp.modelo.par.ParMes;
import com.bap.erp.modelo.par.ParModalidadFacturacion;
import com.bap.erp.modelo.par.ParModalidadTransaccion;
import com.bap.erp.modelo.par.ParMunicipio;
import com.bap.erp.modelo.par.ParPeriodo;
import com.bap.erp.modelo.par.ParRecurrencia;
import com.bap.erp.modelo.par.ParTipoAlicuota;
import com.bap.erp.modelo.par.ParTipoAplicacionRetencion;
import com.bap.erp.modelo.par.ParCliente;
import com.bap.erp.modelo.par.ParCondicionPension;
import com.bap.erp.modelo.par.ParEstadoCivil;
import com.bap.erp.modelo.par.ParEstadoLiquidacion;
import com.bap.erp.modelo.par.ParEstadoPeriodoGestion;
import com.bap.erp.modelo.par.ParGenero;
import com.bap.erp.modelo.par.ParPorcentajeAntiguedad;
import com.bap.erp.modelo.par.ParSeccion;
import com.bap.erp.modelo.par.ParTipoAplicacionDescuentoCriterioDeIngreso;
import com.bap.erp.modelo.par.ParTipoBonoAntiguedad;
import com.bap.erp.modelo.par.ParTipoCompra;
import com.bap.erp.modelo.par.ParTipoContacto;
import com.bap.erp.modelo.par.ParTipoContratoEmpleado;
import com.bap.erp.modelo.par.ParTipoDescuento;
import com.bap.erp.modelo.par.ParTipoDocumento;
import com.bap.erp.modelo.par.ParTipoDocumentoMercantil;
import com.bap.erp.modelo.par.ParTipoMoneda;
import com.bap.erp.modelo.par.ParTipoProveedorCliente;
import com.bap.erp.modelo.par.ParTipoRegistro;
import com.bap.erp.modelo.par.ParTipoRetencion;
import com.bap.erp.modelo.par.ParTipoTransaccion;
import com.bap.erp.modelo.par.ParTipoDocumentoPago;
import com.bap.erp.modelo.par.ParTipoEmpleado;
import com.bap.erp.modelo.par.ParTipoHito;
import com.bap.erp.modelo.par.ParTipoItem;
import com.bap.erp.modelo.par.ParTipoModulo;
import com.bap.erp.modelo.par.ParTipoPago;
import com.bap.erp.modelo.par.ParValor;
import com.bap.erp.modelo.pojo.EntidadPojo;
import com.bap.erp.servicios.par.ParValorService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParValorServiceImpl implements ParValorService {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ParValorServiceImpl.class);

    IGenericDao<ParValor> dao;
    IGenericDao<ParActividadEconomica> parActividadEconomica;
    IGenericDao<ParBanco> parBanco;
    IGenericDao<ParCaracteristicaEspecial> parCaracteristicaEspecial;
    IGenericDao<ParDepartamento> parDepartamento;
    IGenericDao<ParEstado> parEstado;
    IGenericDao<ParEstadoFactura> parEstadoFactura;
    IGenericDao<ParEstadoPago> parEstadoPago;
    IGenericDao<ParEstadoProceso> parEstadoProceso;
    IGenericDao<ParFormaDePago> parFormaDePago;
    IGenericDao<ParLocalizacion> parLocalizacion;
    IGenericDao<ParMes> parMes;
    IGenericDao<ParModalidadFacturacion> parModalidadFacturacion;
    IGenericDao<ParModalidadTransaccion> parModalidadTransaccion;
    IGenericDao<ParMunicipio> parMunicipio;
    IGenericDao<ParPeriodo> parPeriodo;
    IGenericDao<ParRecurrencia> parRecurrencia;
    IGenericDao<ParTipoContacto> parTipoContacto;
    IGenericDao<ParTipoDocumento> parTipoDocumento;
    IGenericDao<ParTipoDocumentoMercantil> parTipoDocumentoMercantil;
    IGenericDao<ParTipoDocumentoPago> parTipoDocumentoPago;
    IGenericDao<ParTipoMoneda> parTipoMoneda;
    IGenericDao<ParTipoPago> parTipoPago;
    IGenericDao<ParTipoProveedorCliente> parTipoProveedorCliente;
    IGenericDao<ParTipoRegistro> parTipoRegistro;
    IGenericDao<ParTipoRetencion> parTipoRetencion;
    IGenericDao<ParTipoTransaccion> parTipoTransaccion;
    IGenericDao<ParTipoItem> parTipoItem;
    IGenericDao<ParTipoCompra> parTipoCompra;
    IGenericDao<ParTipoHito> parTipoHito;
    IGenericDao<ParTipoAlicuota> parTipoAlicuota;
    IGenericDao<ParTipoAplicacionRetencion> parTipoAplicacionRetencion;
    IGenericDao<ParCliente> parCliente;
    IGenericDao<ParTipoModulo> parTipoModulo;
//    Modulo RRHH
    IGenericDao<ParEstadoCivil> parEstadoCivil;
    IGenericDao<ParCondicionPension> parCondicionPension;
    IGenericDao<ParTipoEmpleado> parTipoEmpleado;
    IGenericDao<ParGenero> parGenero;
    IGenericDao<ParSeccion> parSeccion;
    IGenericDao<ParTipoContratoEmpleado> parTipoContratoEmpleado;
    IGenericDao<ParPorcentajeAntiguedad> parPorcentajeAntiguedad;
    IGenericDao<ParTipoBonoAntiguedad> parTipoBonoAntiguedad;
    IGenericDao<ParEstadoLiquidacion> parEstadoLiquidacion;
    IGenericDao<ParTipoAplicacionDescuentoCriterioDeIngreso> parTipoAplicacionDescuentoCriterioDeIngreso;
    IGenericDao<ParEstadoPeriodoGestion> parPeriodoGestion;
    
    
    

    @Autowired
    public void setDao(IGenericDao<ParValor> daoToSet) {
        dao = daoToSet;
        dao.setClazz(ParValor.class);
    }

    @Autowired
    public void setParActividadEconomica(IGenericDao<ParActividadEconomica> parActividadEconomicaToSet) {
        parActividadEconomica = parActividadEconomicaToSet;
        parActividadEconomica.setClazz(ParActividadEconomica.class);
    }

    @Autowired
    public void setParBanco(IGenericDao<ParBanco> parBancoToSet) {
        parBanco = parBancoToSet;
        parBanco.setClazz(ParBanco.class);
    }

    @Autowired
    public void setParCaracteristicaEspecial(IGenericDao<ParCaracteristicaEspecial> parCaracteristicaEspecialToSet) {
        parCaracteristicaEspecial = parCaracteristicaEspecialToSet;
        parCaracteristicaEspecial.setClazz(ParCaracteristicaEspecial.class);
    }

    @Autowired
    public void setParDepartamento(IGenericDao<ParDepartamento> parDepartamentoToSet) {
        parDepartamento = parDepartamentoToSet;
        parDepartamento.setClazz(ParDepartamento.class);
    }

    @Autowired
    public void setParEstado(IGenericDao<ParEstado> parEstadoToSet) {
        parEstado = parEstadoToSet;
        parEstado.setClazz(ParEstado.class);
    }

    @Autowired
    public void setParEstadoFactura(IGenericDao<ParEstadoFactura> parEstadoFacturaToSet) {
        parEstadoFactura = parEstadoFacturaToSet;
        parEstadoFactura.setClazz(ParEstadoFactura.class);
    }

    @Autowired
    public void setParEstadoPago(IGenericDao<ParEstadoPago> parEstadoPagoToSet) {
        parEstadoPago = parEstadoPagoToSet;
        parEstadoPago.setClazz(ParEstadoPago.class);
    }

    @Autowired
    public void setParEstadoProceso(IGenericDao<ParEstadoProceso> parEstadoProcesoToSet) {
        parEstadoProceso = parEstadoProcesoToSet;
        parEstadoProceso.setClazz(ParEstadoProceso.class);
    }

    @Autowired
    public void setParFormaDePago(IGenericDao<ParFormaDePago> parFormaDePagoToSet) {
        parFormaDePago = parFormaDePagoToSet;
        parFormaDePago.setClazz(ParFormaDePago.class);
    }

    @Autowired
    public void setParLocalizacion(IGenericDao<ParLocalizacion> parLocalizacionToSet) {
        parLocalizacion = parLocalizacionToSet;
        parLocalizacion.setClazz(ParLocalizacion.class);
    }

    @Autowired
    public void setParMes(IGenericDao<ParMes> parMesToSet) {
        parMes = parMesToSet;
        parMes.setClazz(ParMes.class);
    }

    @Autowired
    public void setParModalidadFacturacion(IGenericDao<ParModalidadFacturacion> parModalidadFacturacionToSet) {
        parModalidadFacturacion = parModalidadFacturacionToSet;
        parModalidadFacturacion.setClazz(ParModalidadFacturacion.class);
    }

    @Autowired
    public void setParModalidadTransaccion(IGenericDao<ParModalidadTransaccion> parModalidadTransaccionToSet) {
        parModalidadTransaccion = parModalidadTransaccionToSet;
        parModalidadTransaccion.setClazz(ParModalidadTransaccion.class);
    }

    @Autowired
    public void setParMunicipio(IGenericDao<ParMunicipio> parMunicipioToSet) {
        parMunicipio = parMunicipioToSet;
        parMunicipio.setClazz(ParMunicipio.class);
    }

    @Autowired
    public void setParPeriodo(IGenericDao<ParPeriodo> parPeriodoToSet) {
        parPeriodo = parPeriodoToSet;
        parPeriodo.setClazz(ParPeriodo.class);
    }

    @Autowired
    public void setParRecurrencia(IGenericDao<ParRecurrencia> parRecurrenciaToSet) {
        parRecurrencia = parRecurrenciaToSet;
        parRecurrencia.setClazz(ParRecurrencia.class);
    }

    @Autowired
    public void setParTipoContacto(IGenericDao<ParTipoContacto> parTipoContactoToSet) {
        parTipoContacto = parTipoContactoToSet;
        parTipoContacto.setClazz(ParTipoContacto.class);
    }

    @Autowired
    public void setParTipoDocumento(IGenericDao<ParTipoDocumento> parTipoDocumentoToSet) {
        parTipoDocumento = parTipoDocumentoToSet;
        parTipoDocumento.setClazz(ParTipoDocumento.class);
    }

    @Autowired
    public void setParTipoDocumentoMercantil(IGenericDao<ParTipoDocumentoMercantil> parTipoDocumentoMercantilToSet) {
        parTipoDocumentoMercantil = parTipoDocumentoMercantilToSet;
        parTipoDocumentoMercantil.setClazz(ParTipoDocumentoMercantil.class);
    }

    @Autowired
    public void setParTipoDocumentoPago(IGenericDao<ParTipoDocumentoPago> parTipoDocumentoPagoToSet) {
        parTipoDocumentoPago = parTipoDocumentoPagoToSet;
        parTipoDocumentoPago.setClazz(ParTipoDocumentoPago.class);
    }

    @Autowired
    public void setParTipoMoneda(IGenericDao<ParTipoMoneda> parTipoMonedaToSet) {
        parTipoMoneda = parTipoMonedaToSet;
        parTipoMoneda.setClazz(ParTipoMoneda.class);
    }

    @Autowired
    public void setParTipoPago(IGenericDao<ParTipoPago> parTipoPagoToSet) {
        parTipoPago = parTipoPagoToSet;
        parTipoPago.setClazz(ParTipoPago.class);
    }

    @Autowired
    public void setParTipoProveedorCliente(IGenericDao<ParTipoProveedorCliente> parTipoProveedorClienteToSet) {
        parTipoProveedorCliente = parTipoProveedorClienteToSet;
        parTipoProveedorCliente.setClazz(ParTipoProveedorCliente.class);
    }

    @Autowired
    public void setParTipoRegistro(IGenericDao<ParTipoRegistro> parTipoRegistroToSet) {
        parTipoRegistro = parTipoRegistroToSet;
        parTipoRegistro.setClazz(ParTipoRegistro.class);
    }

    @Autowired
    public void setParTipoRetencion(IGenericDao<ParTipoRetencion> parTipoRetencionToSet) {
        parTipoRetencion = parTipoRetencionToSet;
        parTipoRetencion.setClazz(ParTipoRetencion.class);
    }

    @Autowired
    public void setParTipoItem(IGenericDao<ParTipoItem> parTipoItemToSet) {
        parTipoItem = parTipoItemToSet;
        parTipoItem.setClazz(ParTipoItem.class);
    }

    @Autowired
    public void setParTipoTransaccion(IGenericDao<ParTipoTransaccion> parTipoTransaccionToSet) {
        parTipoTransaccion = parTipoTransaccionToSet;
        parTipoTransaccion.setClazz(ParTipoTransaccion.class);
    }

    @Autowired
    public void setParTipoCompra(IGenericDao<ParTipoCompra> parTipoCompraToSet) {
        parTipoCompra = parTipoCompraToSet;
        parTipoCompra.setClazz(ParTipoCompra.class);
    }

    @Autowired
    public void setParTipoHito(IGenericDao<ParTipoHito> parTipoHitoToSet) {
        parTipoHito = parTipoHitoToSet;
        parTipoHito.setClazz(ParTipoHito.class);
    }

    @Autowired
    public void setParTipoAlicuota(IGenericDao<ParTipoAlicuota> parTipoAlicuotaToSet) {
        parTipoAlicuota = parTipoAlicuotaToSet;
        parTipoAlicuota.setClazz(ParTipoAlicuota.class);
    }

    @Autowired
    public void setParTipoAplicacionRetencion(IGenericDao<ParTipoAplicacionRetencion> parTipoAplicacionRetencionToSet) {
        parTipoAplicacionRetencion = parTipoAplicacionRetencionToSet;
        parTipoAplicacionRetencion.setClazz(ParTipoAplicacionRetencion.class);
    }

    @Autowired
    public void setParTipoCliente(IGenericDao<ParCliente> parClienteToSet) {
        parCliente = parClienteToSet;
        parCliente.setClazz(ParCliente.class);
    }

    @Autowired
    public void setParTipoModulo(IGenericDao<ParTipoModulo> parTipoModuloToSet) {
        parTipoModulo = parTipoModuloToSet;
        parTipoModulo.setClazz(ParTipoModulo.class);
    }

    @Autowired
    public void setParEstadoCivil(IGenericDao<ParEstadoCivil> parEstadoCivilToSet) {
        parEstadoCivil = parEstadoCivilToSet;
        parEstadoCivil.setClazz(ParEstadoCivil.class);
    }

    @Autowired
    public void setParCondicionPension(IGenericDao<ParCondicionPension> parCondicionPensionToSet) {
        parCondicionPension = parCondicionPensionToSet;
        parCondicionPension.setClazz(ParCondicionPension.class);
    }

    @Autowired
    public void setParTipoEmpleado(IGenericDao<ParTipoEmpleado> parTipoEmpleadoToSet) {
        parTipoEmpleado = parTipoEmpleadoToSet;
        parTipoEmpleado.setClazz(ParTipoEmpleado.class);
    }

    @Autowired
    public void setParGenero(IGenericDao<ParGenero> parGeneroToSet) {
        parGenero = parGeneroToSet;
        parGenero.setClazz(ParGenero.class);
    }

    @Autowired
    public void setParSeccion(IGenericDao<ParSeccion> parSeccionToSet) {
        parSeccion = parSeccionToSet;
        parSeccion.setClazz(ParSeccion.class);
    }

    @Autowired
    public void setParTipoContratoEmpleado(IGenericDao<ParTipoContratoEmpleado> parTipoContratoEmpleadoToSet) {
        parTipoContratoEmpleado = parTipoContratoEmpleadoToSet;
        parTipoContratoEmpleado.setClazz(ParTipoContratoEmpleado.class);
    }

    @Autowired
    public void setParPorcentajeAntiguedad(IGenericDao<ParPorcentajeAntiguedad> parPorcentajeAntiguedadToSet) {
        parPorcentajeAntiguedad = parPorcentajeAntiguedadToSet;
        parPorcentajeAntiguedad.setClazz(ParPorcentajeAntiguedad.class);
    }

    @Autowired
    public void setParTipoBonoAntiguedad(IGenericDao<ParTipoBonoAntiguedad> parTipoBonoAntiguedadToSet) {
        parTipoBonoAntiguedad = parTipoBonoAntiguedadToSet;
        parTipoBonoAntiguedad.setClazz(ParTipoBonoAntiguedad.class);
    }

    @Autowired
    public void setEstadoLiquidacion(IGenericDao<ParEstadoLiquidacion> parEstadoLiquidacionToSet) {
        parEstadoLiquidacion = parEstadoLiquidacionToSet;
        parEstadoLiquidacion.setClazz(ParEstadoLiquidacion.class);
    }
    
    @Autowired
    public void setTipoAplicacionDescuentoCriterioDeIngreso(IGenericDao<ParTipoAplicacionDescuentoCriterioDeIngreso> parTipoAplicacionDescuentoCriterioDeIngresoToSet) {
        parTipoAplicacionDescuentoCriterioDeIngreso = parTipoAplicacionDescuentoCriterioDeIngresoToSet;
        parTipoAplicacionDescuentoCriterioDeIngreso.setClazz(ParTipoAplicacionDescuentoCriterioDeIngreso.class);
    }
    
    @Autowired
    public void setParEstadoPeriodoGestion(IGenericDao<ParEstadoPeriodoGestion> parEstadoPeriodoGestionToSet) {
        parPeriodoGestion = parEstadoPeriodoGestionToSet;
        parPeriodoGestion.setClazz(ParEstadoPeriodoGestion.class);
    }

    public ParValor persistParValor(ParValor parValor) {

        parValor.setIdParValor(null);
        parValor.setFechaAlta(new Date());
        parValor.setUsuarioAlta("GUS");

        dao.create(parValor);
        return parValor;
    }

    public List<ParValor> getParValor() {
        return dao.findAll();
    }

    public Object find(Class clazz, String id) {
        return dao.findOne(clazz, id);
    }

    public List<ParActividadEconomica> getListParActividadEconomica() throws Exception {
        try {
            return parActividadEconomica.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParBanco> getListParBanco() throws Exception {
        try {
            return parBanco.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParCaracteristicaEspecial> getListParCaracteristicaEspecial() throws Exception {
        try {
            return parCaracteristicaEspecial.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParDepartamento> getListParDepartamento() throws Exception {
        try {
            return parDepartamento.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParEstado> getListParEstado() throws Exception {
        try {
            return parEstado.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParEstadoFactura> getListParEstadoFactura() throws Exception {
        try {
            return parEstadoFactura.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParEstadoPago> getListParEstadoPago() throws Exception {
        try {
            return parEstadoPago.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParEstadoProceso> getListParEstadoProceso() throws Exception {
        try {
            return parEstadoProceso.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParFormaDePago> getListParFormaDePago() throws Exception {
        try {
            return parFormaDePago.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParLocalizacion> getListParLocalizacion() throws Exception {
        try {
            return parLocalizacion.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParMes> getListParMes() throws Exception {
        try {
            return parMes.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParModalidadFacturacion> getListParModalidadFacturacion() throws Exception {
        try {
            List<ParModalidadFacturacion> lista = parModalidadFacturacion.find(""
                    + "select j "
                    + "from ParModalidadFacturacion j "
                    + "where j.codigo <> 'NIN'");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParModalidadTransaccion> getListParModalidadTransaccion() throws Exception {
        try {
            return parModalidadTransaccion.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParMunicipio> getListParMunicipio() throws Exception {
        try {
            return parMunicipio.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParMunicipio> getListParMunicipioByDepartamento(String codigoDepartamento) throws Exception {
        try {
            return parMunicipio.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParPeriodo> getListParPeriodo() throws Exception {
        try {
            return parPeriodo.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParRecurrencia> getListParRecurrencia() throws Exception {
        try {
            return parRecurrencia.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoContacto> getListParTipoContacto() throws Exception {
        try {
            return parTipoContacto.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoDocumento> getListParTipoDocumento() throws Exception {
        try {
            return parTipoDocumento.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoDocumentoMercantil> getListParTipoDocumentoMercantil() throws Exception {
        try {
            return parTipoDocumentoMercantil.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoDocumentoPago> getListParTipoDocumentoPago() throws Exception {
        try {
            return parTipoDocumentoPago.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoMoneda> getListParTipoMoneda() throws Exception {
        try {
            return parTipoMoneda.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoPago> getListParTipoPago() throws Exception {
        try {
            return parTipoPago.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoProveedorCliente> getListParTipoProveedorCliente() throws Exception {
        try {
            return parTipoProveedorCliente.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoRegistro> getListParTipoRegistro() throws Exception {
        try {
            return parTipoRegistro.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoRetencion> getListParTipoRetencion() throws Exception {
        try {
            return parTipoRetencion.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoTransaccion> getListParTipoTransaccion() throws Exception {
        try {
            return parTipoTransaccion.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoItem> getListParTipoItem() throws Exception {
        try {
            return parTipoItem.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<EntidadPojo> getMunicipiosPojoPorDepartamento(String codigoDepartamento) throws Exception {
        try {
            List<ParMunicipio> listaMunicipios = new ArrayList<ParMunicipio>();
            List<EntidadPojo> listaEntidadPojo = new ArrayList<EntidadPojo>();
            EntidadPojo entidadPojo;
            listaMunicipios = getListParMunicipio();
            for (ParMunicipio lista : listaMunicipios) {
                if (lista.getGrupo().equals(codigoDepartamento)) {
                    entidadPojo = new EntidadPojo();
                    entidadPojo.setDescripcion(lista.getDescripcion());
                    entidadPojo.setMascara(lista.getCodigo());
                    entidadPojo.setTipo("MUN");
                    listaEntidadPojo.add(entidadPojo);
                }
            }
            if (!listaEntidadPojo.isEmpty()) {
                return listaEntidadPojo;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParValor> getListMunicipiosByDepartamento(String codigoDepartamento) throws Exception {
        try {
            List<ParValor> lista = dao.find(""
                    + "select a "
                    + "from ParValor a "
                    + "where a.fechaBaja is null "
                    + "and a.grupo = '" + codigoDepartamento + "' "
                    + "order by  a.fechaAlta ASC");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParMunicipio> getListaMunicipiosByCodigoDepartamento(String codigoDepartamento) throws Exception {
        try {
            List<ParValor> listaMunicipios = new ArrayList<ParValor>();
            List<ParMunicipio> listaMunicipiofinal = new ArrayList<ParMunicipio>();
            listaMunicipios = getListMunicipiosByDepartamento(codigoDepartamento);
            ParMunicipio parMunicipio;
            for (ParValor lista : listaMunicipios) {
                parMunicipio = new ParMunicipio();
                parMunicipio.setCodigo(lista.getCodigo());
                parMunicipio.setDescripcion(lista.getDescripcion());
                parMunicipio.setGrupo(lista.getGrupo());
                listaMunicipiofinal.add(parMunicipio);
            }
            if (!listaMunicipiofinal.isEmpty()) {
                return listaMunicipiofinal;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public ParValor findByCodigo(String codigo) throws Exception {
        try {
            List<ParValor> listParValor = dao.find("select j "
                    + "from ParValor j "
                    + "where j.fechaBaja is null "
                    + "and j.codigo = '" + codigo + "'");
            if (!listParValor.isEmpty()) {
                return listParValor.get(0);
            }
            return new ParValor();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParCaracteristicaEspecial> getListParCaracteristicaEspecialParafacturacion() throws Exception {
        try {
            List<ParCaracteristicaEspecial> listaCaracteristicasEspeciales = parCaracteristicaEspecial.find(""
                    + "select j "
                    + "from ParCaracteristicaEspecial j "
                    + "where j.codigo <> '" + EnumCaracteristicaEspecial.NOTA_DE_DEBITO_CREDITO.getCodigo() + "'");
            if (!listaCaracteristicasEspeciales.isEmpty()) {
                return listaCaracteristicasEspeciales;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoCompra> getListParTipoCompra() throws Exception {
        try {
            return parTipoCompra.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoTransaccion> getTipoTransaccionPorModulo(String modulo) throws Exception {
        try {
            List<ParTipoTransaccion> listaTipoTransaccion = parTipoTransaccion.find(""
                    + "select j "
                    + "from ParTipoTransaccion j "
                    + "where j.grupo = '" + modulo + "'");
            if (!listaTipoTransaccion.isEmpty()) {
                return listaTipoTransaccion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoHito> getListTipoHito() throws Exception {
        try {
            return parTipoHito.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public void actualizaDatosAlicuotas(List<ParTipoAlicuota> listaAlicuotas) throws Exception {
        try {
            ParValor alicuota;
            for (ParTipoAlicuota listaAlicuota : listaAlicuotas) {
                alicuota = new ParValor();
                alicuota = findByCodigo(listaAlicuota.getCodigo());
                alicuota.setValor(listaAlicuota.getValor());
                mergeParValor(alicuota);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public ParValor mergeParValor(ParValor parValor) throws Exception {
        try {
            parValor.setFechaModificacion(new Date());
            parValor.setUsuarioModificacion("test");
            dao.update(parValor);
            return parValor;
        } catch (Exception e) {
            throw e;
        }
    }

    public ParTipoAlicuota findParTipoAlicuotaByCodigo(String codigo) throws Exception {
        try {
            ParTipoAlicuota parTipoAlicuotaObjeto = parTipoAlicuota.findOne(codigo);
            return parTipoAlicuotaObjeto;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoAplicacionRetencion> getListParTipoAplicacionRetencion() throws Exception {
        try {
            return parTipoAplicacionRetencion.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParCliente> getListParCliente() throws Exception {
        try {
            return parCliente.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoAlicuota> getListParTipoAlicuota() throws Exception {
        try {
            return parTipoAlicuota.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoModulo> getListParTipoModulo() throws Exception {
        try {
            return parTipoModulo.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoDocumentoMercantil> getParTipoDocumentoMercantilByGrupo(String grupo) throws Exception {
        try {
            List<ParTipoDocumentoMercantil> lista = parTipoDocumentoMercantil.find(""
                    + "select j "
                    + "from ParTipoDocumentoMercantil j "
                    + "where (j.grupo = '" + grupo + "' "
                    + "or j.grupo = 'AMB') ");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public ParEstadoPago findParEstadoPagoByCodigo(String codigo) throws Exception {
        try {
            ParEstadoPago parEstadoPagoObjeto = parEstadoPago.findOne(codigo);
            return parEstadoPagoObjeto;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoTransaccion> getListParTipoTransaccionFactura() throws Exception {
        try {
            List<ParTipoTransaccion> listaParTipoTransaccionFactura = parTipoTransaccion.find(""
                    + "select j "
                    + "from ParTipoTransaccion j "
                    + "where j.grupo = 'CPP' "
                    + "and j.codigo <> '2'");
            if (!listaParTipoTransaccionFactura.isEmpty()) {
                return listaParTipoTransaccionFactura;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParTipoTransaccion> getListParTipoTransaccionRetencion() throws Exception {
        try {
            List<ParTipoTransaccion> listaParTipoTransaccionRetencion = parTipoTransaccion.find(""
                    + "select j "
                    + "from ParTipoTransaccion j "
                    + "where j.grupo = 'CPP' "
                    + "and j.codigo <> '1'");
            if (!listaParTipoTransaccionRetencion.isEmpty()) {
                return listaParTipoTransaccionRetencion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public ParTipoModulo findParTipoModuloByCodigo(String codigo) throws Exception {
        try {
            ParTipoModulo parTipoModuloObjeto = parTipoModulo.findOne(codigo);
            return parTipoModuloObjeto;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ParEstadoCivil> getParEstadoCivil() {
        return parEstadoCivil.findAll();
    }

    public List<ParCondicionPension> getParCondicionPension() {
        return parCondicionPension.findAll();
    }

    public List<ParTipoEmpleado> getParTipoEmpleado() {
        return parTipoEmpleado.findAll();
    }

    public List<ParGenero> getParGenero() {
        return parGenero.findAll();
    }

    public List<ParSeccion> getParSeccion() {
        return parSeccion.findAll();
    }

    public ParEstado findParEstadoByCodigo(String codigo) throws Exception {
        try {
            ParEstado parEstadoObjeto = parEstado.findOne(codigo);
            return parEstadoObjeto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ParTipoContratoEmpleado> getParTipoContratoEmpleado() throws Exception {
        try {
            return parTipoContratoEmpleado.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean verificaExistenciaDeDatosAlicuota() throws Exception {
        try {
            Boolean sw = true;
            List<ParTipoAlicuota> listaParTipoAlicuota = parTipoAlicuota.findAll();
            for (ParTipoAlicuota alicuota : listaParTipoAlicuota) {
                System.out.println("VALOR ALI:" + alicuota.getValor());
                if (alicuota.getValor() == null || alicuota.getValor().isEmpty()) {
                    sw = false;
                }
            }
            return sw;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ParPorcentajeAntiguedad getParPorcentajeAntiguedadSegunAnio(int anio) throws Exception {
        try {
            log.info("el anio es::: " + anio);
            if (anio == 0) {
                return obtieneParPorcentajeAntiguedadPorCodigo("1");
            } else {
                List<ParPorcentajeAntiguedad> lista = parPorcentajeAntiguedad.find(""
                        + "select j "
                        + "from ParPorcentajeAntiguedad j "
                        + "where cast(j.desde as int) < " + anio + " "
                        + "and cast(j.hasta as int) >= " + anio + "");
                log.info("lista-----" + lista.get(0).getPorcentaje());
                return lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public ParPorcentajeAntiguedad obtieneParPorcentajeAntiguedadPorCodigo(String codigo) throws Exception {
        try {
            ParPorcentajeAntiguedad porcentajeAntiguedad = parPorcentajeAntiguedad.findOne(codigo);
            return porcentajeAntiguedad;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ParTipoBonoAntiguedad obtieneParTipoBonoAntiguedadPorCodigo(String codigo) throws Exception {
        try {
            ParTipoBonoAntiguedad bonoAntiguedad = parTipoBonoAntiguedad.findOne(codigo);
            return bonoAntiguedad;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ParEstadoLiquidacion> getListParEstadoLiquidacion() throws Exception {
        try {
            return parEstadoLiquidacion.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ParEstadoLiquidacion obtieneParEstadoLiquidacion(String codigo) throws Exception {
        try {
            ParEstadoLiquidacion estadoLiquidacion = parEstadoLiquidacion.findOne(codigo);
            return estadoLiquidacion;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ParTipoAplicacionDescuentoCriterioDeIngreso obtieneTipoAplicacionDescuentoPorCodigo(String codigo) throws Exception {
        try {
            ParTipoAplicacionDescuentoCriterioDeIngreso parTipoAplicacionDescuentoCriterioDeIngresoObjeto = parTipoAplicacionDescuentoCriterioDeIngreso.findOne(codigo);
            return parTipoAplicacionDescuentoCriterioDeIngresoObjeto;
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    @Override
    public ParEstadoPeriodoGestion obtieneParEstadoPeriodoGestion(String codigo) throws Exception {
        try {
            ParEstadoPeriodoGestion estadoPeriodoGestion = parPeriodoGestion.findOne(codigo);
            return estadoPeriodoGestion;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ParGenero obtieneParGeneroPorCodigo(String codigo) throws Exception {
        try {
            ParGenero parGeneroObjeto = parGenero.findOne(codigo);
            return parGeneroObjeto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ParEstadoFactura obtieneParEstadoFacturaPorCodigo(String codigo) throws Exception {
        try {
            ParEstadoFactura parEstadoFacturaObjeto = parEstadoFactura.findOne(codigo);
            return parEstadoFacturaObjeto;
        } catch (Exception e) {
            throw e;
        }
    }
}
