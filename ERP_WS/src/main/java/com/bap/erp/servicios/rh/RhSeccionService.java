package com.bap.erp.servicios.rh;

import com.bap.erp.modelo.rh.RhSeccion;
import java.util.List;

    public interface RhSeccionService {

    //Autor:Henrry Guzman    
    List<RhSeccion> listaRhSeccionPorIdDepartamento(Long idDepartamento) throws Exception;

}
