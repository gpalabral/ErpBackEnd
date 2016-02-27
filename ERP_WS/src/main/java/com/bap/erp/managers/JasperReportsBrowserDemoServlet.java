/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.erp.managers;

import com.bap.erp.servicios.ErpFacturaService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class JasperReportsBrowserDemoServlet extends HttpServlet {
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(JasperReportsBrowserDemoServlet.class);
    
    @Autowired
    private ErpFacturaService erpFacturaService;
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        log.info("erpFacturaService::: "+erpFacturaService);
        
        ServletOutputStream servletOutputStream = response.getOutputStream();
        
        //File reportFile = new File(getServletConfig().getServletContext().getRealPath("/WEB-INF/reports/factura.jasper"));
        
        InputStream stream=getServletConfig().getServletContext().getResourceAsStream("/WEB-INF/reports/factura.jasper");
        
        String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/images") + File.separatorChar;
        
        log.info("realPath::: "+realPath);
        
        byte[] pdfFile = null;
        
        Map<String, Object> reportParam=new HashMap<String,Object>(); 
        
        //File reportFile = new File(getServletConfig().getServletContext().getRealPath("/WEB-INF/reports/factura.jasper"));
        
        try {
            
            /**********PARAMETROS*********/
            reportParam.put("NIT_CLIENTE", "1002329022");
            reportParam.put("NRO_FACTURA", "1");
            reportParam.put("NRO_AUTORIZACION", "6004002578983");
            reportParam.put("LITERAL", "Diez mil 00/100 Bolivianos");
            reportParam.put("CODIGO_CONTROL", "56-C0-90-00");
            reportParam.put("FECHA_LIMITE", "01/01/2016");
            reportParam.put("IMAGES_PATH", realPath);
            
            
            /*********DETAILS********/
            List<String> coleccion=new ArrayList<String>();
            coleccion.add("AAAA");
            coleccion.add("BBBB");
            coleccion.add("CCCCC");
            coleccion.add("DDDDD");
            
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(coleccion);
            
            
            /************FILLING************/
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, reportParam, ds);
            
            
            /*************EXPORT**************/
            pdfFile=JasperExportManager.exportReportToPdf(jasperPrint);
            
            
//            pdfFile = JasperRunManager.runReportToPdf(reportFile.getPath(),
//                    new HashMap(), new JREmptyDataSource());

            response.setContentType("application/pdf");
            response.setContentLength(pdfFile.length);

            servletOutputStream.write(pdfFile, 0, pdfFile.length);
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (JRException e) {
            // display stack trace in the browser
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            response.setContentType("text/plain");
            response.getOutputStream().print(stringWriter.toString());
        }
    }    
}
