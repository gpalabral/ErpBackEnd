package com.bap.erp.managers;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.*;
import org.apache.log4j.Logger;

public class ReportManager {

    private static org.apache.log4j.Logger log = Logger.getLogger(ReportManager.class);
    private final String REPORT_PATH = "/WEB-INF/reportes/";
    private transient Map<String, Object> reportParam;
    private String formato;


    public void exportPDF(HttpServletRequest request,List coleccion, final String reportName) {

        InputStream stream = null;
        JRExporter exporter = new JRPdfExporter();
        JasperPrint jasperPrint = null;

        try {

            if (reportParam == null) {
                throw new Exception("Parametros del reporte no iniciados......");
            }

            String path = REPORT_PATH + reportName + ".jasper";
            stream = this.getClass().getResourceAsStream(path);

            //String realPath = request.getResource(REPORT_PATH).getPath();
            String realPath = request.getSession().getServletContext().getRealPath(REPORT_PATH) + File.separatorChar;
            log.info("REAL PATH::: "+realPath);
            
            reportParam.put("REPORT_PATH", realPath);

            reportParam.put("formato", formato);

            log.debug("PARAMETROS ENVIADOS:::: " + reportParam);

            if (stream == null) {
                throw new Exception("Reporte no encontrado:::::::::::::::: " + reportName);
            }

            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(coleccion);

            jasperPrint = JasperFillManager.fillReport(stream, reportParam, ds);

//            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
//            response.setContentType("application/pdf");
//            response.setHeader("content-type", "application/pdf");
//            response.setHeader("Transfer-Encoding", "chunked");
//            response.setHeader("Content-Disposition", "inline;filename=Report.pdf");

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());

            exporter.exportReport();
            stream.close();

//            context.renderResponse();
//            context.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stream = null;
        exporter = null;
        jasperPrint = null;
    }


    public void setReportParam(Map<String, Object> reportParam) {
        this.reportParam = reportParam;
    }

    public Map<String, Object> getReportParam() {
        return reportParam;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getFormato() {
        return formato;
    }

}
