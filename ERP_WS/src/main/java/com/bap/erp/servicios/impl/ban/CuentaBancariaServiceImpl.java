package com.bap.erp.servicios.impl.ban;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.enums.EnumPropietarioCuenta;
import com.bap.erp.modelo.ban.CuentaBancaria;
import com.bap.erp.modelo.cpc.CpcDosificacion;
import com.bap.erp.modelo.pojo.CuentaBancariaPojo;
import com.bap.erp.servicios.ban.CuentaBancariaService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonathan
 */
@Service
public class CuentaBancariaServiceImpl implements CuentaBancariaService {

    IGenericDao<CuentaBancaria> dao;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CuentaBancariaServiceImpl.class);

    @Autowired
    public void setDao(IGenericDao<CuentaBancaria> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CuentaBancaria.class);
    }

    public CuentaBancaria persistCuentaBancaria(CuentaBancaria cuentaBancaria) throws Exception {
        try {
            cuentaBancaria.setIdCuentaBancaria(null);
            cuentaBancaria.setUsuarioAlta("TEST");
            cuentaBancaria.setFechaAlta(new Date());
            dao.create(cuentaBancaria);
            return cuentaBancaria;
        } catch (Exception e) {
            throw e;
        }
    }

    public CuentaBancaria getCuentaBancariaById(Long idCuentaBancaria) throws Exception {
        try {
            return dao.findOne(idCuentaBancaria);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CuentaBancaria> getCuentaBancariaByIdProveedorCliente(Long idProveedorCliente) throws Exception {
        try {
            String consulta;
            if (idProveedorCliente != null) {
                consulta = "and j.cppProveedorCliente.idProveedorCliente = " + idProveedorCliente + " ";
            } else {
                consulta = "and j.propietarioCuenta = '" + EnumPropietarioCuenta.EMPRESA.getCodigo() + "' ";
            }
            List<CuentaBancaria> listaCuentas = dao.find(""
                    + "select j "
                    + "from CuentaBancaria j "
                    + "where j.fechaBaja is null "
                    + consulta
                    + "order by j.fechaAlta ASC");
            if (!listaCuentas.isEmpty()) {
                return listaCuentas;
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CuentaBancariaPojo> getCuentaBancariaPojoByIdProveedorCliente(Long idProveedorCliente) throws Exception {
        try {
            List<CuentaBancaria> listaCuentasEmpresa = getCuentaBancariaByIdProveedorCliente(idProveedorCliente);
            List<CuentaBancariaPojo> listaCuentasEmpresaPojo = new ArrayList<CuentaBancariaPojo>();
            CuentaBancariaPojo cuentaBancariaPojo;
            for (CuentaBancaria listaCuentasEmpresa1 : listaCuentasEmpresa) {
                cuentaBancariaPojo = new CuentaBancariaPojo();
                cuentaBancariaPojo.setIdCuentaBancaria(listaCuentasEmpresa1.getIdCuentaBancaria());
                cuentaBancariaPojo.setDescripcion(listaCuentasEmpresa1.getParBanco().getDescripcion() + " - Cuenta Nro. " + listaCuentasEmpresa1.getNumeroCuenta());
                listaCuentasEmpresaPojo.add(cuentaBancariaPojo);
            }
            if (!listaCuentasEmpresaPojo.isEmpty()) {
                return listaCuentasEmpresaPojo;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CuentaBancariaPojo> getCuentaBancariaPojoByEmpresa(String propietarioCuenta) throws Exception {
        try {
            if (propietarioCuenta.equals("EMP")) {
                List<CuentaBancaria> listaCuentasEmpresa = getCuentaBancariaByIdProveedorCliente(null);
                List<CuentaBancariaPojo> listaCuentasEmpresaPojo = new ArrayList<CuentaBancariaPojo>();
                CuentaBancariaPojo cuentaBancariaPojo;
                for (CuentaBancaria listaCuentasEmpresa1 : listaCuentasEmpresa) {
                    cuentaBancariaPojo = new CuentaBancariaPojo();
                    cuentaBancariaPojo.setIdCuentaBancaria(listaCuentasEmpresa1.getIdCuentaBancaria());
                    cuentaBancariaPojo.setDescripcion(listaCuentasEmpresa1.getParBanco().getDescripcion() + " - Cuenta Nro. " + listaCuentasEmpresa1.getNumeroCuenta());
                    listaCuentasEmpresaPojo.add(cuentaBancariaPojo);
                }
                if (!listaCuentasEmpresaPojo.isEmpty()) {
                    return listaCuentasEmpresaPojo;
                }
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

}
