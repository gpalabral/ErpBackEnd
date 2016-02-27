package com.bap.erp.servicios.impl.cpp;

import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.commons.utils.ObjectUtils;
import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.enums.EnumEstado;
import com.bap.erp.enums.EnumEstadoFactura;
import com.bap.erp.enums.EnumEstadoPago;
import com.bap.erp.enums.EnumModulo;
import com.bap.erp.enums.EnumTipoProveedorCliente;
import com.bap.erp.enums.EnumTipoRegistro;
import com.bap.erp.modelo.ban.CuentaBancaria;
import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.modelo.cpc.CpcContrato;
import com.bap.erp.modelo.cpp.CppConcepto;
import com.bap.erp.modelo.cpp.CppFormaPagoCobro;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.par.ParEstado;
import com.bap.erp.modelo.par.ParTipoDocumento;
import com.bap.erp.modelo.pojo.CppFormaPagoCobroCuentasBancariasPojo;
import com.bap.erp.modelo.pojo.CppProveedorClienteBusquedaPojo;
import com.bap.erp.modelo.pojo.CppProveedorClientePojo;
import com.bap.erp.modelo.pojo.EntidadArbolPojo;
import com.bap.erp.modelo.pojo.EntidadPojo;
import com.bap.erp.modelo.pojo.ErpProveedorClientePojo;
import com.bap.erp.servicios.ErpFacturaService;
import com.bap.erp.servicios.ban.CuentaBancariaService;
import com.bap.erp.servicios.cpc.CpcContratoService;
import com.bap.erp.servicios.cpp.CppConceptoService;
import com.bap.erp.servicios.cpp.CppContactoService;
import com.bap.erp.servicios.cpp.CppFormaPagoCobroService;
import com.bap.erp.servicios.cpp.CppProveedorClienteConceptoService;
import com.bap.erp.servicios.cpp.CppProveedorClienteService;
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
public class CppProveedorClienteServiceImpl extends AbstractJpaDAO<CppProveedorCliente> implements CppProveedorClienteService {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CppProveedorClienteServiceImpl.class);

    IGenericDao<CppProveedorCliente> dao;
    IGenericDao<ErpFactura> daoFactura;
    IGenericDao<CpcContrato> daoContrato;

    @Autowired
    public CppContactoService cppContactoService;
    @Autowired
    public CppFormaPagoCobroService cppFormaPagoCobroService;
    @Autowired
    public ParValorService parValorService;
    @Autowired
    public CppConceptoService cppConceptoService;
    @Autowired
    public CppProveedorClienteConceptoService cppProveedorClienteConceptoService;
    @Autowired
    public CuentaBancariaService cuentaBancariaService;
    @Autowired
    public ErpFacturaService erpFacturaService;
    @Autowired
    public CpcContratoService cpcContratoService;

    @Autowired
    public void setDao(IGenericDao<CppProveedorCliente> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CppProveedorCliente.class);
    }

    public CppProveedorCliente persistCppProveedorCliente(CppProveedorCliente cppProveedorCliente) throws Exception {
        try {
            cppProveedorCliente.setParEstado((ParEstado) parValorService.find(ParEstado.class, "VIG"));
            cppProveedorCliente.setIdProveedorCliente(null);
            cppProveedorCliente.setFechaAlta(new Date());
            cppProveedorCliente.setUsuarioAlta("TEST");
            dao.create(cppProveedorCliente);
            return cppProveedorCliente;
        } catch (Exception e) {
            throw e;
        }
    }

    public CppProveedorCliente mergeCppProveedorCliente(CppProveedorCliente cppProveedorCliente) throws Exception {
        try {
            cppProveedorCliente.setFechaAlta(new Date());
            cppProveedorCliente.setUsuarioAlta("SYS");
            cppProveedorCliente.setFechaModificacion(new Date());
            cppProveedorCliente.setUsuarioModificacion("TEST");
            dao.update(cppProveedorCliente);
            return cppProveedorCliente;
        } catch (Exception e) {
            throw e;
        }
    }

    public void removeCppProveedorCliente(Long idProveedorCliente) throws Exception {
        try {
            System.out.println("idProveedor" + idProveedorCliente);
            CppProveedorCliente proveedorRemoved = dao.findOne(idProveedorCliente);
            System.out.println("idProveedor---------" + proveedorRemoved);
            proveedorRemoved.setFechaBaja(new Date());
            proveedorRemoved.setUsuarioBaja("SYS");
            dao.update(proveedorRemoved);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CppProveedorCliente> getCppProveedorCliente() {
        return dao.findAll();
    }

    public List<CppProveedorCliente> getListaCppProveedorClientePorTipoRegistro(String tipoRegistro) {
        List<CppProveedorCliente> resultList = find(""
                + "select a "
                + "from CppProveedorCliente a "
                + "where a.fechaBaja is null "
                + "and a.parTipoRegistro.codigo = '" + tipoRegistro + "'"
                + "order by fechaAlta asc");
        return resultList;
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public CppProveedorCliente guardaProveedorClienteContactoFormaPagoCobro(CppProveedorClientePojo cppProveedorClientePojo) throws Exception {
        CppProveedorCliente cppProveedorCliente = new CppProveedorCliente();

        try {
            cppProveedorCliente = cppProveedorClientePojo.getCppProveedorCliente();
            if (cppProveedorCliente.getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                ParTipoDocumento parTipoDocumento = (ParTipoDocumento) parValorService.find(ParTipoDocumento.class, "CI");
                cppProveedorCliente.setParTipoDocumento(parTipoDocumento);
            }
            cppProveedorCliente = persistCppProveedorCliente(cppProveedorCliente);
//            if (cppProveedorClientePojo.getListaCppContacto() != null) {
//                for (CppContacto cppContacto : cppProveedorClientePojo.getListaCppContacto()) {
//                    cppContacto.setCppProveedorCliente(cppProveedorCliente);
//                    cppContactoService.persistCppContacto(cppContacto);
//                }
//            }
//            if (cppProveedorClientePojo.getCppProveedorCliente().getIdProveedorCliente() != null && cppProveedorClientePojo.getCppFormaPagoCobro() != null && cppProveedorClientePojo.getCppFormaPagoCobro().getParFormaDePago() != null && cppProveedorClientePojo.getCppFormaPagoCobro().getParFormaDePago().getCodigo() != null && !cppProveedorClientePojo.getCppFormaPagoCobro().getParFormaDePago().getCodigo().equals("")) {
//                CppFormaPagoCobro cppFormaPagoCobro = cppProveedorClientePojo.getCppFormaPagoCobro();
//                cppFormaPagoCobro.setCppProveedorCliente(cppProveedorCliente);
//                cppFormaPagoCobroService.persistCppFormaPagoCobro(cppFormaPagoCobro);
//            }
//            List<EntidadArbolPojo> listaConceptos = cppProveedorClientePojo.getListaCppGruposConceptos();
//            CppProveedorClienteConcepto cppProveedorClienteConcepto;
//            if (listaConceptos != null) {
//                for (EntidadArbolPojo entidadArbolPojo : listaConceptos) {
//                    for (EntidadPojo concepto : entidadArbolPojo.getChildren()) {
//                        cppProveedorClienteConcepto = new CppProveedorClienteConcepto();
//                        cppProveedorClienteConcepto.setCppProveedorCliente(cppProveedorCliente);
//                        cppProveedorClienteConcepto.setCppConcepto(cppConceptoService.getCppConcepto(concepto.getIdEntidadPojo()));
//                        cppProveedorClienteConceptoService.persistCppProveedorClienteConcepto(cppProveedorClienteConcepto);
//                    }
//                }
//            }
        } catch (Exception e) {
            throw e;
        }
        return cppProveedorCliente;
    }

    public CppProveedorCliente getCppProveedorCliente(Long idCppProveedorCliente) {
        return dao.findOne(idCppProveedorCliente);
    }

    public List<CppProveedorCliente> getListaProveedoresNoAsignadosPorIdConcepto(Long idConcepto) {
        List<CppProveedorCliente> list = find(""
                + "select j "
                + "from CppProveedorCliente j "
                + "where j.fechaBaja is null "
                + "and j.idProveedorCliente not in ("
                + "select o.cppProveedorCliente.idProveedorCliente "
                + "from CppProveedorClienteConcepto o "
                + "where o.fechaBaja is null "
                + "and o.cppConcepto.idConcepto = " + idConcepto + ")");
        if (!list.isEmpty()) {
            return list;
        }
        return Collections.EMPTY_LIST;
    }

    public List<CppProveedorCliente> listaProveedoresConConceptosAsignados() {
        List<CppProveedorCliente> lista = find(""
                + "select a.cppProveedorCliente "
                + "from CppProveedorClienteConcepto a "
                + "where a.fechaBaja is null "
                + "group by a.cppProveedorCliente "
                + "order by a.fechaAlta asc");
        if (!lista.isEmpty()) {
            return lista;
        }
        return Collections.EMPTY_LIST;
    }

    public List<EntidadArbolPojo> getProveedorClienteTree(String tipoRegistro) {
        List<CppProveedorCliente> lista = getListaCppProveedorClientePorTipoRegistro(tipoRegistro);
        List<EntidadArbolPojo> listaEntidadArbolPojo = new ArrayList<EntidadArbolPojo>();
        EntidadArbolPojo entidadArbolPojo = new EntidadArbolPojo();
        String descripcion = "";
        for (CppProveedorCliente cppProveedorCliente : lista) {
            entidadArbolPojo = new EntidadArbolPojo();
            entidadArbolPojo.setIdEntidadPojo(cppProveedorCliente.getIdProveedorCliente());
            if (cppProveedorCliente.getParTipoProveedorCliente().getCodigo().equals("JUR")) {
                descripcion = cppProveedorCliente.getRazonSocial();
            } else {
                descripcion = cppProveedorCliente.getNombre() + " " + cppProveedorCliente.getPrimerApellido() + " " + cppProveedorCliente.getSegundoApellido();
            }
            entidadArbolPojo.setDescripcion(descripcion);
            entidadArbolPojo.setMascara("");
            entidadArbolPojo.setTipo(cppProveedorCliente.getParTipoRegistro().getCodigo());
            entidadArbolPojo.setChildren(Collections.EMPTY_LIST);
            listaEntidadArbolPojo.add(entidadArbolPojo);
        }
        if (!listaEntidadArbolPojo.isEmpty()) {
            return listaEntidadArbolPojo;
        }
        return Collections.EMPTY_LIST;

    }

    public List<CppProveedorCliente> getProveedorClientePorIdConcepto(Long idConcepto, String asignado) throws Exception {
        List<CppProveedorCliente> listaProveedores = new ArrayList<CppProveedorCliente>();
        String consulta = "";
        try {
            if (asignado.equals("S")) {
                consulta = " in ";
            } else {
                consulta = " not in ";
            }
            listaProveedores = find(""
                    + "select j "
                    + "from CppProveedorCliente j "
                    + "where j.fechaBaja is null and "
                    + "j.parTipoRegistro.codigo != '" + EnumTipoRegistro.CLIENTE.getCodigo() + "' and "
                    + "j.idProveedorCliente " + consulta + " "
                    + "(select o.cppProveedorCliente.idProveedorCliente "
                    + "from CppProveedorClienteConcepto o "
                    + "where o.fechaBaja is null "
                    + "and o.cppConcepto.idConcepto = " + idConcepto + ") "
                    + "order by j.razonSocial asc");
        } catch (Exception e) {
            throw e;
        }
        return listaProveedores;
    }

    public List<EntidadArbolPojo> getProveedorClienteTree(List<CppProveedorCliente> listaProveedores) throws Exception {
        try {

            List<EntidadArbolPojo> listaProveedorFinal = new ArrayList<EntidadArbolPojo>();
            EntidadArbolPojo entidadArbolPojo;
            for (CppProveedorCliente cppProveedorCliente : listaProveedores) {
                entidadArbolPojo = new EntidadArbolPojo();
                entidadArbolPojo.setIdEntidadPojo(cppProveedorCliente.getIdProveedorCliente());
                if (cppProveedorCliente.getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.NATURAL.getCodigo())) {
                    entidadArbolPojo.setDescripcion(cppProveedorCliente.getNombre() + " " + cppProveedorCliente.getPrimerApellido() + " " + cppProveedorCliente.getSegundoApellido());
                } else {
                    entidadArbolPojo.setDescripcion(cppProveedorCliente.getRazonSocial());
                }
                entidadArbolPojo.setMascara(cppProveedorCliente.getParTipoProveedorCliente().getDescripcion());
                entidadArbolPojo.setChildren(new ArrayList<EntidadPojo>());
                listaProveedorFinal.add(entidadArbolPojo);
            }
            return listaProveedorFinal;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public CppProveedorCliente modificaProveedorClienteContactoFormaPagoCobroConceptos(CppProveedorClientePojo cppProveedorClientePojo) throws Exception {
        CppProveedorCliente cppProveedorCliente;
        try {
            cppProveedorCliente = mergeCppProveedorCliente(cppProveedorClientePojo.getCppProveedorCliente());
//            for (CppContacto cppContacto : cppProveedorClientePojo.getListaCppContacto()) {
//                if (cppContacto.getIdContacto() != null) {
//                    cppContactoService.mergeCppContacto(cppContacto);
//                } else {
//                    cppContacto.setCppProveedorCliente(cppProveedorCliente);
//                    cppContactoService.persistCppContacto(cppContacto);
//                }
//            }
            CppFormaPagoCobro cppFormaPagoCobro = cppProveedorClientePojo.getCppFormaPagoCobro();
            if (cppProveedorClientePojo.getCppProveedorCliente().getIdProveedorCliente() != null && cppFormaPagoCobro != null && cppFormaPagoCobro.getParFormaDePago() != null && cppFormaPagoCobro.getParFormaDePago().getCodigo() != null && !cppFormaPagoCobro.getParFormaDePago().getCodigo().equals("")) {
                if (cppFormaPagoCobro.getFechaAlta() == null) {
                    cppFormaPagoCobroService.persistCppFormaPagoCobro(cppFormaPagoCobro);
                } else {
                    cppFormaPagoCobroService.mergeCppFormaPagoCobro(cppFormaPagoCobro);
                }
            }
            List<EntidadArbolPojo> listaConceptos = cppProveedorClientePojo.getListaCppGruposConceptos();
            if (listaConceptos != null) {
                List<CppConcepto> listaConceptosFinal = cppConceptoService.getConceptosByEntidadArbolPojo(listaConceptos);
                cppProveedorClienteConceptoService.removeConceptosNoAsignados(listaConceptosFinal, cppProveedorCliente);
            }
        } catch (Exception e) {
            throw e;
        }
        return cppProveedorCliente;
    }

    public List<CppProveedorCliente> listaCpcProveedorClientePorTipoRegistro(String tipoReg) throws Exception {
        try {
            List<CppProveedorCliente> lista = find(""
                    + "select a "
                    + "from CppProveedorCliente a "
                    + "where a.fechaBaja is null "
                    + "and a.parTipoRegistro.codigo = '" + tipoReg + "' or a.parTipoRegistro.codigo = 'AMB'"
                    + "order by a.primerApellido ASC, a.razonSocial ASC");
            if (!lista.isEmpty()) {
                return lista;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CppProveedorClienteBusquedaPojo> getCpcProveedorClienteBusquedaPojo(String tipoRegistro) throws Exception {
        try {
            List<CppProveedorClienteBusquedaPojo> listaProveedorClienteFinal = new ArrayList<CppProveedorClienteBusquedaPojo>();
            List<CppProveedorCliente> listaProveedorCliente = listaCpcProveedorClientePorTipoRegistro(tipoRegistro);
            CppProveedorClienteBusquedaPojo cppProveedorClienteBusquedaPojo;
            for (CppProveedorCliente cppProveedorCliente : listaProveedorCliente) {
                cppProveedorClienteBusquedaPojo = new CppProveedorClienteBusquedaPojo();
                if (cppProveedorCliente.getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.NATURAL.getCodigo())) {
                    cppProveedorClienteBusquedaPojo.setNombre(cppProveedorCliente.getPrimerApellido() + " " + cppProveedorCliente.getSegundoApellido() + " " + cppProveedorCliente.getNombre());
                }
                if (cppProveedorCliente.getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                    cppProveedorClienteBusquedaPojo.setNombre(cppProveedorCliente.getRazonSocial());
                }//Agregando datos al pojo
                cppProveedorClienteBusquedaPojo.setIdProveedorCliente(cppProveedorCliente.getIdProveedorCliente());
                cppProveedorClienteBusquedaPojo.setTipoRegistro(cppProveedorCliente.getParTipoRegistro().getCodigo());
                cppProveedorClienteBusquedaPojo.setTipoProveedor(cppProveedorCliente.getParTipoProveedorCliente().getCodigo());
                //Add pojo a la lista
                listaProveedorClienteFinal.add(cppProveedorClienteBusquedaPojo);
            }
            return listaProveedorClienteFinal;
        } catch (Exception e) {
            throw e;
        }
    }

    public String getNombreCliente(CppProveedorCliente cppProveedorCliente) {

        String cliente = null;
        if (cppProveedorCliente.getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
            cliente = cppProveedorCliente.getRazonSocial();
        }
        if (cppProveedorCliente.getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.NATURAL.getCodigo())) {
            cliente = cppProveedorCliente.getPrimerApellido() + " " + cppProveedorCliente.getSegundoApellido() + " " + cppProveedorCliente.getNombre();
        }
        System.out.println("cliente::: " + cliente);
        return cliente;
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public CppFormaPagoCobroCuentasBancariasPojo persistCppFormaPagoCobroCuentasBancariasPojo(CppFormaPagoCobroCuentasBancariasPojo cppFormaPagoCobroCuentasBancariasPojo) throws Exception {
        try {
            CppFormaPagoCobro cppFormaPagoCobro = cppFormaPagoCobroCuentasBancariasPojo.getCppFormaPagoCobro();
            cppFormaPagoCobroService.persistCppFormaPagoCobro(cppFormaPagoCobro);
            List<CuentaBancaria> listaCuentaBancaria = cppFormaPagoCobroCuentasBancariasPojo.getListaCuentasBancarias();
            for (CuentaBancaria cuentaBancaria : listaCuentaBancaria) {
                if (!cuentaBancaria.getNumeroCuenta().equals("")) {
                    cuentaBancariaService.persistCuentaBancaria(cuentaBancaria);
                }
            }
            return cppFormaPagoCobroCuentasBancariasPojo;
        } catch (Exception e) {
            throw e;
        }
    }

    public CppFormaPagoCobroCuentasBancariasPojo getCppFormaPagoCobroCuentasBancariasPojoByIdProveedorCliente(Long idProveedorCliente) throws Exception {
        try {
            CppFormaPagoCobroCuentasBancariasPojo cppFormaPagoCobroCuentasBancariasPojo = new CppFormaPagoCobroCuentasBancariasPojo();
            cppFormaPagoCobroCuentasBancariasPojo.setCppFormaPagoCobro(cppFormaPagoCobroService.getCppFormaPagoCobroByIdProveedorCliente(idProveedorCliente));
            cppFormaPagoCobroCuentasBancariasPojo.setListaCuentasBancarias(cuentaBancariaService.getCuentaBancariaByIdProveedorCliente(idProveedorCliente));
            ObjectUtils.printObjectState(cppFormaPagoCobroCuentasBancariasPojo);
            return cppFormaPagoCobroCuentasBancariasPojo;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CppProveedorClienteBusquedaPojo> getCppProveedorClienteParaBancarizacion(String tipoRegistro) throws Exception {
        try {

            List<CppProveedorCliente> listaProveedorCliente = dao.find(""
                    + "select distinct j.cppProveedorCliente "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and j.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_COBRAR.getCodigo() + "' "
                    + "and j.parEstadoPago.codigo = '" + EnumEstadoPago.PENDIENTE.getCodigo() + "' "
                    + "and j.parEstadoFactura.codigo = '" + EnumEstadoFactura.VALIDA.getCodigo() + "'");
            if (!listaProveedorCliente.isEmpty()) {
                List<CppProveedorClienteBusquedaPojo> listaProveedorClientePojo = new ArrayList<CppProveedorClienteBusquedaPojo>();
                CppProveedorClienteBusquedaPojo cppProveedorClienteBusquedaPojo;
                for (CppProveedorCliente listaProveedorCliente1 : listaProveedorCliente) {

                    if (listaProveedorCliente1.getParTipoRegistro().getCodigo().equals(tipoRegistro) || listaProveedorCliente1.getParTipoRegistro().getCodigo().equals(EnumTipoRegistro.AMBOS.getCodigo())) {
                        cppProveedorClienteBusquedaPojo = new CppProveedorClienteBusquedaPojo();
                        cppProveedorClienteBusquedaPojo.setIdProveedorCliente(listaProveedorCliente1.getIdProveedorCliente());
                        if (listaProveedorCliente1.getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                            cppProveedorClienteBusquedaPojo.setNombre(listaProveedorCliente1.getRazonSocial());
                        } else {
                            cppProveedorClienteBusquedaPojo.setNombre(listaProveedorCliente1.getPrimerApellido() + " " + listaProveedorCliente1.getSegundoApellido() + " " + listaProveedorCliente1.getNombre());
                        }
                        cppProveedorClienteBusquedaPojo.setTipoRegistro(listaProveedorCliente1.getParTipoRegistro().getCodigo());
                        cppProveedorClienteBusquedaPojo.setTipoProveedor(listaProveedorCliente1.getParTipoProveedorCliente().getCodigo());
                        //Add pojo a la lista
                        listaProveedorClientePojo.add(cppProveedorClienteBusquedaPojo);
                    }

                }
                return listaProveedorClientePojo;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public CppProveedorCliente getRegistroAnulado() throws Exception {
        try {
            List<CppProveedorCliente> lista = dao.find(""
                    + "select j "
                    + "from CppProveedorCliente j "
                    + "where j.nit = 0 "
                    + "and j.fechaBaja is null "
                    + "and j.razonSocial = 'ANULADO'");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return new CppProveedorCliente();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ErpProveedorClientePojo> getListProveedorCliente() throws Exception {
        try {
            List<ErpProveedorClientePojo> listaProveedorClientePojo = new ArrayList<ErpProveedorClientePojo>();
            ErpProveedorClientePojo erpProveedorClientePojo;
            List<CppProveedorCliente> lista = dao.find(""
                    + "select j "
                    + "from CppProveedorCliente j "
                    + "where j.fechaBaja is null "
                    + "and j.parEstado.codigo = '" + EnumEstado.VIGENTE.getCodigo() + "' ");
            if (!lista.isEmpty()) {
                for (CppProveedorCliente lista1 : lista) {
                    erpProveedorClientePojo = new ErpProveedorClientePojo();
                    erpProveedorClientePojo.setIdProveedorCliente(lista1.getIdProveedorCliente());
                    if (lista1.getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.NATURAL.getCodigo())) {
                        erpProveedorClientePojo.setNombreCompleto(lista1.getPrimerApellido() + " " + lista1.getSegundoApellido() + " " + lista1.getNombre());
                        if (lista1.getNit() == 0L) {
                            erpProveedorClientePojo.setNitCi(lista1.getNumeroDocumento());
                        } else {
                            erpProveedorClientePojo.setNitCi(lista1.getNit().toString());
                        }
                    } else {
                        erpProveedorClientePojo.setNombreCompleto(lista1.getRazonSocial());
                        erpProveedorClientePojo.setNitCi(lista1.getNit().toString());
                    }
                    listaProveedorClientePojo.add(erpProveedorClientePojo);
                }
                return listaProveedorClientePojo;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean verificaSiProveedorClienteNoEstaAsociado(Long idProveedorCliente) throws Exception {
        try {
            System.out.println("idProveedor----" + idProveedorCliente);
            CppProveedorCliente proveedorClienteAEliminar = dao.findOne(idProveedorCliente);
            List<ErpFactura> listaFactura = erpFacturaService.getFacturasPorProveedorCliente(proveedorClienteAEliminar);
            if (listaFactura.isEmpty()) {
                List<CpcContrato> listaContrato = cpcContratoService.getCpcContratoByCppProveedorCliente(proveedorClienteAEliminar);
                return listaContrato.isEmpty();
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ErpProveedorClientePojo> getCppProveedorClienteParaBancarizacionCpp() throws Exception {
        try {
            List<CppProveedorCliente> listaProveedorCliente = dao.find(""
                    + "select distinct j.cppProveedorCliente "
                    + "from ErpFactura j "
                    + "where j.fechaBaja is null "
                    + "and j.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_PAGAR.getCodigo() + "' "
                    + "and j.parEstadoPago.codigo = '"+EnumEstadoPago.FACTURADO.getCodigo()+"'");
            List<CppProveedorCliente> listaProveedorClienteRetencion = dao.find(""
                    + "select distinct o.cppProveedorCliente "
                    + "from CppRetencion o "
                    + "where o.fechaBaja is null "
                    + "and o.parEstadoPago.codigo = '"+EnumEstadoPago.PENDIENTE.getCodigo()+"' "
                    + "and o.cppProveedorCliente.idProveedorCliente not in "
                    + "(select n.cppProveedorCliente.idProveedorCliente "
                    + "from ErpFactura n "
                    + "where n.fechaBaja is null "
                    + "and n.parTipoModulo.codigo = '" + EnumModulo.CUENTAS_POR_PAGAR.getCodigo() + "' "
                    + "and n.parEstadoPago.codigo = '"+EnumEstadoPago.FACTURADO.getCodigo()+"')");
            listaProveedorCliente.addAll(listaProveedorClienteRetencion);
            if (!listaProveedorCliente.isEmpty()) {
                List<ErpProveedorClientePojo> listaProveedorClientePojo = new ArrayList<ErpProveedorClientePojo>();
                ErpProveedorClientePojo cppProveedorClienteBusquedaPojo;
                for (CppProveedorCliente listaProveedorCliente1 : listaProveedorCliente) {
                    cppProveedorClienteBusquedaPojo = new ErpProveedorClientePojo();
                    cppProveedorClienteBusquedaPojo.setIdProveedorCliente(listaProveedorCliente1.getIdProveedorCliente());
                    if (listaProveedorCliente1.getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                        cppProveedorClienteBusquedaPojo.setNombreCompleto(listaProveedorCliente1.getRazonSocial());
                    } else {
                        cppProveedorClienteBusquedaPojo.setNombreCompleto(listaProveedorCliente1.getPrimerApellido() + " " + listaProveedorCliente1.getSegundoApellido() + " " + listaProveedorCliente1.getNombre());
                    }
                    //Add pojo a la lista
                    listaProveedorClientePojo.add(cppProveedorClienteBusquedaPojo);
                }
                return listaProveedorClientePojo;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

}
