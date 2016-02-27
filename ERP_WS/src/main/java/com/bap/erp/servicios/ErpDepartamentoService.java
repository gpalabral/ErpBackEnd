package com.bap.erp.servicios;

import com.bap.erp.modelo.ErpDepartamento;
import java.util.List;


public interface ErpDepartamentoService {
    
    List<ErpDepartamento> listErpDepartamentoByEstado(String estado)throws Exception;
           
}
