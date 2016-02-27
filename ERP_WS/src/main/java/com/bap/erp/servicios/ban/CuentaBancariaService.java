package com.bap.erp.servicios.ban;

import com.bap.erp.modelo.ban.CuentaBancaria;
import com.bap.erp.modelo.pojo.CuentaBancariaPojo;
import java.util.List;

public interface CuentaBancariaService {

    CuentaBancaria persistCuentaBancaria(CuentaBancaria cuentaBancaria) throws Exception;   

    CuentaBancaria getCuentaBancariaById(Long idCuentaBancaria) throws Exception;    
    
    List<CuentaBancaria> getCuentaBancariaByIdProveedorCliente(Long idProveedorCliente)throws Exception;
        
    List<CuentaBancariaPojo> getCuentaBancariaPojoByIdProveedorCliente(Long idProveedorCliente)throws Exception;
    
    List<CuentaBancariaPojo> getCuentaBancariaPojoByEmpresa(String propietarioCuenta)throws Exception;
}
