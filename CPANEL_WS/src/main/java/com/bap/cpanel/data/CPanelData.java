package com.bap.cpanel.data;

import com.bap.cpanel.modelo.adm.AdmModulo;
import com.bap.cpanel.modelo.adm.AdmPerfil;
import com.bap.cpanel.modelo.adm.AdmRol;
import com.bap.cpanel.modelo.cli.CliEmpresa;
import com.bap.cpanel.servicios.adm.AdmModuloService;
import com.bap.cpanel.servicios.adm.AdmPerfilService;
import com.bap.cpanel.servicios.adm.AdmRolService;
import com.bap.cpanel.servicios.cli.CliEmpresaService;
import static com.bap.erp.commons.managers.ConfigManager.getValue;
import static com.bap.erp.commons.managers.ConfigManager.loadConfig;
import com.bap.erp.commons.utils.JsonUtils;
import com.bap.erp.commons.utils.ObjectUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class CPanelData {
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CPanelData.class);
    
    static{
        loadConfig("CliEmpresa.properties");
//        loadConfig("AdmRol.properties");
//          loadConfig("AdmPermisos.properties");
//        loadConfig("AdmModulos.properties");
//          loadConfig("AdmPerfil.properties");
        
    }   

    public static void main(String args[]) throws Exception {
        
        ApplicationContext context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
        
        CliEmpresaService cliEmpresaService = (CliEmpresaService) context.getBean("cliEmpresaServiceImpl");
//        AdmRolService admRolService = (AdmRolService) context.getBean("admRolServiceImpl");
//          AdmPermisoService admPermisoService = (AdmPermisoService) context.getBean("admPermisoServiceImpl");
//        AdmModuloService admModuloService = (AdmModuloService) context.getBean("admModuloServiceImpl");
          
        
        CliEmpresa cliEmpresa=JsonUtils.getObject(getValue("bap"), CliEmpresa.class);  
//        AdmRol admRol=JsonUtils.getObject(getValue("Rol1"), AdmRol.class);
//        AdmPermiso admPermiso=JsonUtils.getObject(getValue("permiso8"), AdmPermiso.class);
//        AdmModulo admModulo=JsonUtils.getObject(getValue("modulo4"), AdmModulo.class);
        
        cliEmpresaService.persistCliEmpresa(cliEmpresa);
//        admRolService.persistAdmRol(admRol);
//          admPermisoService.persistAdmPermiso(admPermiso);
//        admModuloService.persistAdmModulo(admModulo);

        ObjectUtils.printObjectState(cliEmpresa);
//        ObjectUtils.printObjectState(admRol);
//          ObjectUtils.printObjectState(admPermiso);
//        ObjectUtils.printObjectState(admModulo);
          
          
//          AdmPerfilService admPerfilService = (AdmPerfilService)context.getBean("admPerfilServiceImpl");
//          AdmPerfil admPerfil=JsonUtils.getObject(getValue("sucursalPredeterminada"), AdmPerfil.class);
//          admPerfilService.persistAdmPerfil(admPerfil);
//          
//          admPerfil=JsonUtils.getObject(getValue("dosificacionPredeterminada"), AdmPerfil.class);
//          admPerfilService.persistAdmPerfil(admPerfil);
    }

}