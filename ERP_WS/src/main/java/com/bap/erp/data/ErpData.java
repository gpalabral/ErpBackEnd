package com.bap.erp.data;

import static com.bap.erp.commons.managers.ConfigManager.getValue;
import static com.bap.erp.commons.managers.ConfigManager.loadConfig;
import com.bap.erp.commons.utils.JsonUtils;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.servicios.cpp.CppProveedorClienteService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ErpData {
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ErpData.class);
    
    static{
          loadConfig("CppProveedorCliente.properties"); 
    }   

    public static void main(String args[]) throws Exception {
        
        ApplicationContext context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
          
          CppProveedorClienteService cppProveedorClienteService = (CppProveedorClienteService)context.getBean("cppProveedorClienteServiceImpl");
          CppProveedorCliente cppProveedorCliente=JsonUtils.getObject(getValue("proveedorCliente"), CppProveedorCliente.class);
          cppProveedorClienteService.persistCppProveedorCliente(cppProveedorCliente);
          
          cppProveedorCliente=JsonUtils.getObject(getValue("dosificacionPredeterminada"), CppProveedorCliente.class);
          cppProveedorClienteService.persistCppProveedorCliente(cppProveedorCliente);
    }

}
