package com.bap.erp.ws;

import com.bap.erp.modelo.ErpDetalleFactura;
import com.bap.erp.modelo.pojo.CodigoControlExcelPojo;
import com.bap.erp.modelo.pojo.CpcConciliacionContablePojo;
import com.bap.erp.modelo.pojo.ErpFacturasBancariasPojo;
import com.bap.erp.servicios.ErpFacturaService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Component;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;

@Component
@Path("/import")
@Api(value = "import", description = "WS for importation")
public class ImportWS {
    
    @Autowired
    private ErpFacturaService erpFacturaService;
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ImportWS.class);

    @POST
    @Path("/upload/xls")
    @ApiOperation(value = "Importacion de Excel")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response xls(
            @FormDataParam("uploadFile") InputStream fileInputStream,
            @FormDataParam("uploadFile") FormDataContentDisposition fileDetail,
            @FormDataParam("tipoCambio") String tipoCambio) {
        
        log.info("File:: " + fileDetail.getFileName());
        BigDecimal tipoCambioF = new BigDecimal(tipoCambio);
        
        log.info("Tipo de Cambio::: " + tipoCambioF);
        
        List<ErpDetalleFactura> list = new ArrayList<ErpDetalleFactura>();
        try {

            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            //Get first/desired sheet from the workbook
            //XSSFSheet sheet = workbook.getSheetAt(0);
            Sheet sheet = workbook.getSheetAt(0);
            int startingRow = 2;
            int endingRow = lookForRowWithValue(sheet, "TOTAL VALUE");
            sheet = workbook.getSheetAt(0);
            ErpDetalleFactura cpcDetalleFactura = null;
            String descripcionMerge = null;
            while (startingRow <= endingRow) {
                
                Row row = sheet.getRow(startingRow);
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                Iterator<Cell> cellIterator = row.cellIterator();
                cpcDetalleFactura = new ErpDetalleFactura();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    //log.info("Index:: "+cell.getRowIndex()+" Tyoe::: "+cell.getCellType());
                    switch (cell.getCellType()) {
                        
                        case Cell.CELL_TYPE_NUMERIC:
                            if (cell.getColumnIndex() == 4)//Cantidad
                            {
                                log.info("Quantity:: " + cell.getNumericCellValue() + "\t");
                                cpcDetalleFactura.setCantidad(new Double(cell.getNumericCellValue()).intValue());
                            }
                            if (cell.getColumnIndex() == 6)//Precio Unitario
                            {
                                log.info("Precio Unitario 2da Moneda:: " + cell.getNumericCellValue() + "\t");
                                cpcDetalleFactura.setPrecioUnitarioSegundaMoneda(new BigDecimal(cell.getNumericCellValue()));
                                
                                log.info("Precio Unitario 1ra Moneda:: " + cell.getNumericCellValue() + "\t");
                                BigDecimal valBs = new BigDecimal(cell.getNumericCellValue());
                                cpcDetalleFactura.setPrecioUnitarioPrimeraMoneda(valBs.multiply(tipoCambioF));
                                
                            }
//                            if (cell.getColumnIndex() == 4) {
//                                log.info("Code como numero::: " + cell.getNumericCellValue() + "\t");
//                                cell.setCellType(Cell.CELL_TYPE_STRING);
//                                cpcDetalleFactura.setCodigo(cell.getStringCellValue());
//                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            if (cell.getColumnIndex() == 1)//Nandina
                            {
                                log.info("Nandina::: " + cell.getStringCellValue() + "\t");
                                descripcionMerge = cell.getStringCellValue();
                                if (!cell.getStringCellValue().contains("null")) {
                                    cpcDetalleFactura.setPartidaArancelaria(cell.getStringCellValue());
                                }
                                
                            }
                            if (cell.getColumnIndex() == 2)//Codigo
                            {
                                log.info("Codigo::: " + cell.getStringCellValue() + "\t");
                                cpcDetalleFactura.setCodigo(cell.getStringCellValue());
                            }
                            if (cell.getColumnIndex() == 3)//Detalle
                            {
                                log.info("Descripcion::: " + cell.getStringCellValue() + "\t");
                                descripcionMerge = cell.getStringCellValue();
                                if (!cell.getStringCellValue().contains("null")) {
                                    cpcDetalleFactura.setDetalleFactura(cell.getStringCellValue());
                                }
                            }
                            if (cell.getColumnIndex() == 5)//Unidad de Medida
                            {
                                log.info("Unidad de Medida::: " + cell.getStringCellValue() + "\t");
                                descripcionMerge = cell.getStringCellValue();
                                if (!cell.getStringCellValue().contains("null")) {
                                    cpcDetalleFactura.setUnidadMedida(cell.getStringCellValue());
                                }
                            }
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            if (cell.getColumnIndex() == 7)//Subtotal
                            {
                                CellValue cellValue = evaluator.evaluate(cell);
                                log.info("Subtotal 2da Moneda:: " + cellValue.getNumberValue() + "\t");
                                cpcDetalleFactura.setSubtotalSegundaMoneda(new BigDecimal(cellValue.getNumberValue()));
                                
                                log.info("Subtotal 1ra Moneda:: " + cellValue.getNumberValue() + "\t");
                                BigDecimal valBs = new BigDecimal(cellValue.getNumberValue());
                                cpcDetalleFactura.setSubtotalPrimeraMoneda(valBs.multiply(tipoCambioF));
                            }
                            break;
                        case Cell.CELL_TYPE_BLANK:
                            if (cell.getColumnIndex() == 1)//Descripcion MERGED AREA
                            {
                                cpcDetalleFactura.setDetalleFactura(descripcionMerge);
                            }
                            break;
                    }
                }
                log.info(startingRow + "::: " + cpcDetalleFactura.getCodigo());
                if (cpcDetalleFactura.getCantidad() != 0) {
                    cpcDetalleFactura.setDescuentoPrimeraMoneda(new BigDecimal(0L));
                    cpcDetalleFactura.setDescuentoSegundaMoneda(new BigDecimal(0L));
                    cpcDetalleFactura.setPorcentajeDescuento(new BigDecimal(0L));
                    list.add(cpcDetalleFactura);
                }
                startingRow++;
            }
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InvalidFormatException ex) {
            Logger.getLogger(ImportWS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ImportWS.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // release resources, if any
            log.info("LIST::: " + list);
        }
        return Response.ok(list).build();
        
    }
    
    public int lookForRowWithValue(Sheet sheet, String term) {       //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        boolean found = false;
        Cell cell = null;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                cell = cellIterator.next();
                if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().equals(term)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        if (cell != null) {
            return cell.getRowIndex();
        } else {
            return -1;
        }
        
    }
    
    @POST
    @Path("/codigoControl/xls")
    @ApiOperation(value = "Importacion de Excel para CodigoControl")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response codigoControlXLS(@FormDataParam("uploadFile") InputStream fileInputStream) {
        List<CodigoControlExcelPojo> list = new ArrayList<CodigoControlExcelPojo>();
        try {
            
            Long lgnNAut = null;
            Long lgnNFac = null;
            String srtNAut = null;
            String srtNFac = null;
            Long lgnNIT = null;
            String srtLlave = null;
//            Float fltMonto = null;
            BigDecimal fltMonto = null;
            Date dteFecha = new Date();
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int startingRow = 5;
            int endingRow = lookForRowWithValue(sheet, "TOTAL VALUE") - 1;
            sheet = workbook.getSheetAt(0);
            CodigoControlExcelPojo codigoControlExcelPojo = null;
            while (startingRow <= endingRow + 1) {
                Row row = sheet.getRow(startingRow);
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                Iterator<Cell> cellIterator = row.cellIterator();
                codigoControlExcelPojo = new CodigoControlExcelPojo();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            if (cell.getColumnIndex() == 2) {
                                lgnNAut = Long.parseLong(String.valueOf(new Double(cell.getNumericCellValue()).longValue()));
                                srtNAut = String.valueOf(new Double(cell.getNumericCellValue()).longValue());
                                codigoControlExcelPojo.setNroAutorizacion(lgnNAut);
                            }
                            if (cell.getColumnIndex() == 3) {
                                lgnNFac = Long.parseLong(String.valueOf(new Double(cell.getNumericCellValue()).longValue()));
                                srtNFac = String.valueOf(new Double(cell.getNumericCellValue()).longValue());
                                codigoControlExcelPojo.setNroFactura(lgnNFac);
                            }
                            if (cell.getColumnIndex() == 4) {
                                lgnNIT = Long.parseLong(String.valueOf(new Double(cell.getNumericCellValue()).longValue()));
                                codigoControlExcelPojo.setNit(lgnNIT);
                            }
                            if (cell.getColumnIndex() == 6) {
                                fltMonto = new BigDecimal(cell.getNumericCellValue());
                                codigoControlExcelPojo.setMonto(fltMonto);
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            if (cell.getColumnIndex() == 5) {
                                dteFecha = new Date(cell.getStringCellValue());
                                codigoControlExcelPojo.setFechaFactura(dteFecha);
                            }
                            if (cell.getColumnIndex() == 7) {
                                srtLlave = cell.getStringCellValue();
                                codigoControlExcelPojo.setLlaveDosificacion(srtLlave);
                            }
                            break;
                    }
                }
                String codCont = erpFacturaService.genereCodigoDeControl(lgnNIT, srtNFac, srtNAut, dteFecha, fltMonto, srtLlave);
                System.out.println(codCont + "\t");
                codigoControlExcelPojo.setCodigoControl(codCont);
                
                list.add(codigoControlExcelPojo);
                startingRow++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InvalidFormatException ex) {
            Logger.getLogger(ImportWS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ImportWS.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        List<String> listaCodigoControl = new ArrayList<String>();        
        for (CodigoControlExcelPojo x : list) {
            listaCodigoControl.add(x.getCodigoControl());
        }        
        return Response.ok(listaCodigoControl).build();
    }


    @POST
    @Path("/conciliacion/xls")
    @ApiOperation(value = "Importacion del mayor en Excel")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response conciliacionXls(
            @FormDataParam("uploadFile") InputStream fileInputStream,
            @FormDataParam("uploadFile") FormDataContentDisposition fileDetail) {
        try {
            List<CpcConciliacionContablePojo> listaConciliacion;
            List<CpcConciliacionContablePojo> listaConciliacionDebitoFiscal = erpFacturaService.seleccionaDatosDeExcel(fileInputStream);
            listaConciliacion = erpFacturaService.getReferenciacionNotaDebitoFiscal(listaConciliacionDebitoFiscal, Collections.EMPTY_LIST);
            return Response.ok(listaConciliacion).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
        
    }
    
    @POST
    @Path("/conciliacionIngresos/xls")
    @ApiOperation(value = "Importacion del mayor en Excel")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response conciliacionIngresosXls(
            @FormDataParam("uploadFileIngreso") InputStream fileInputStreamIngreso,
            @FormDataParam("uploadFileIngreso") FormDataContentDisposition fileDetailIngreso) {
        try {
            List<CpcConciliacionContablePojo> listaConciliacion;
            List<CpcConciliacionContablePojo> listaConciliacionIngresos = erpFacturaService.seleccionaDatosDeExcel(fileInputStreamIngreso);
            listaConciliacion = erpFacturaService.getReferenciacionNotaDebitoFiscal(Collections.EMPTY_LIST, listaConciliacionIngresos);
            return Response.ok(listaConciliacion).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
        
    }
    
    @POST
    @Path("/importaExcelFacturasBancariasXls/xls")
    @ApiOperation(value = "Importacion de Facturas Bancarias en Excel")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response importaExcelFacturasBancariasXls(
            @FormDataParam("uploadFileFacturasBancarias") InputStream fileInputStreamFacturasBancarias,
            @FormDataParam("uploadFileFacturasBancarias") FormDataContentDisposition fileDetailFacturasBancarias) {
        System.out.println("ENTROOOOOOO WS:");
        try {
            System.out.println("FILE:" + fileInputStreamFacturasBancarias);
            List<ErpFacturasBancariasPojo> listaFacturasBancarias = erpFacturaService.importaExcelFacturasBancarias(fileInputStreamFacturasBancarias);
            System.out.println("LISTA BANCAS:"+listaFacturasBancarias);
//            erpFacturaService.guardaFacturasBancarias(listaFacturasBancarias,erpFacturasDatosPojo);
            return Response.ok(listaFacturasBancarias).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
        
    }        

    @POST
    @Path("/conciliacionCreditoFiscal/xls")
    @ApiOperation(value = "Importacion del credito Fiscal en Excel")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response conciliacionCreditoFiscal(
            @FormDataParam("uploadFileIngreso") InputStream fileInputStream,
            @FormDataParam("uploadFileIngreso") FormDataContentDisposition fileDetail) {
        try {
            List<CpcConciliacionContablePojo> listaConciliacion = erpFacturaService.seleccionaDatosDeExcel(fileInputStream);            
            listaConciliacion = erpFacturaService.getReferenciacionNotaCreditoFiscal(listaConciliacion);
            return Response.ok(listaConciliacion).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }

    }
    
//    @POST
//    @Path("/importacionRRHH/xls")
//    @ApiOperation(value = "Importacion de ")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response importacionRRHH(
//            @FormDataParam("uploadFile") InputStream fileInputStream,
//            @FormDataParam("uploadFile") FormDataContentDisposition fileDetail,
//            @FormDataParam("tipoImportacion") String tipoImportacion) {
//        try {
////            if(tipoImportacion.equals("DES")){
////                List<
////            }
//            List<CpcConciliacionContablePojo> listaConciliacion = erpFacturaService.seleccionaDatosDeExcel(fileInputStream);            
////            listaConciliacion = erpFacturaService.getReferenciacionNotaCreditoFiscal(listaConciliacion);
//            return Response.ok(listaConciliacion).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(500).entity(e).build();
//        }
//    }
    

}
