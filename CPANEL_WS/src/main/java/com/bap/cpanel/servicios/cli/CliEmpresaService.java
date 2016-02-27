package com.bap.cpanel.servicios.cli;

import com.bap.cpanel.modelo.cli.CliEmpresa;
import java.util.List;

public interface CliEmpresaService {
    
    CliEmpresa getCliEmpresaById(Long idEmpresa);
    
    CliEmpresa persistCliEmpresa(CliEmpresa cliEmpresa) throws Exception;
    
    CliEmpresa mergeCliEmpresa(CliEmpresa cliEmpresa) throws Exception;
    
    List<CliEmpresa> getCliEmpresa() throws Exception;
    
}
