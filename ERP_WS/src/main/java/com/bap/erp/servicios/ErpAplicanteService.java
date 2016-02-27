package com.bap.erp.servicios;

import com.bap.erp.modelo.ErpAplicante;
import com.bap.erp.modelo.pojo.ErpAplicantePojo;
import java.util.List;


public interface ErpAplicanteService {
    
    List<ErpAplicantePojo> listErpAplicanteByIdDepartamento(Long idDepartamento)throws Exception;
           
}
