package com.bap.erp.servicios.rh;

import com.bap.erp.modelo.rh.RhCargo;
import java.util.List;

public interface RhCargoService {

    public RhCargo persistRhCargo(RhCargo rhCargo) throws Exception;

    RhCargo mergeRhCargo(RhCargo rhCargo) throws Exception;

    public void removeRhCargo(Long idCargo) throws Exception;

    List<RhCargo> listaRhCargo() throws Exception;
    
    Boolean verificaExistenciaCodigoRhCargo(String codigo) throws Exception;

}
