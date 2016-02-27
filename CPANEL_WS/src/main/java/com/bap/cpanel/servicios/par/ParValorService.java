package com.bap.cpanel.servicios.par;

import com.bap.cpanel.modelo.par.ParDepartamento;
import com.bap.cpanel.modelo.par.ParEstadoUsuario;
import com.bap.cpanel.modelo.par.ParTipoDocumento;
import com.bap.cpanel.modelo.par.ParEstadoPermiso;
import com.bap.cpanel.modelo.par.ParTipo;
import com.bap.cpanel.modelo.par.ParTipoMoneda;
import com.bap.cpanel.modelo.par.ParValor;
import java.util.List;

public interface ParValorService {
    
    ParValor persistParValor(ParValor parValor);

    List<ParValor> getParValor();

    Object find(Class clazz, String id);

    List<ParEstadoUsuario> getListParEstadoUsuario() throws Exception;
    
    List<ParTipoDocumento> getListParTipoDocumento() throws Exception;
    
    List<ParEstadoPermiso> getListParEstadoPermiso() throws Exception;
    
    List<ParTipoMoneda> getListParTipoMoneda() throws Exception;
    
    List<ParTipo> getListParTipo() throws Exception;
    
    List<ParDepartamento> getListParDepartamento() throws Exception;
    
}
