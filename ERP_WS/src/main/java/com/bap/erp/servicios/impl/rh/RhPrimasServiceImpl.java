package com.bap.erp.servicios.impl.rh;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.utils.ImportUtils;
import com.bap.erp.modelo.rh.RhEmpleado;
import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import com.bap.erp.modelo.rh.RhPeriodoGestion;
import com.bap.erp.modelo.rh.RhPrimas;
import com.bap.erp.modelo.rh.RhVariacion;
import com.bap.erp.servicios.rh.RhEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhEmpleadoService;
import com.bap.erp.servicios.rh.RhPeriodoGestionService;
import com.bap.erp.servicios.rh.RhPrimasService;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RhPrimasServiceImpl implements RhPrimasService {

    IGenericDao<RhPrimas> dao;
    
    @Autowired
    RhEmpleadoService rhEmpleadoService;

    @Autowired
    public void setDao(IGenericDao<RhPrimas> daoToSet) {
        dao = daoToSet;
        dao.setClazz(RhPrimas.class);
    }

    @Autowired
    RhEmpleadoCargoService rhEmpleadoCargoService;

    @Autowired
    RhPeriodoGestionService rhPeriodoGestionService;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhPrimasServiceImpl.class);

    @Override
    public boolean existePrimasPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {
            log.info("idPeriodoGestion......" + idPeriodoGestion);
            List<RhPrimas> lista = dao.find(""
                    + "select h "
                    + "from RhPrimas h "
                    + "where h.fechaBaja is null "
                    + "and h.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            return !lista.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void registrarRhPrimasPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {
            List<RhEmpleadoCargo> listaEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoVigente(idPeriodoGestion);
            RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.encuentraRhPeriodoGestionPorId(idPeriodoGestion);
            RhPrimas rhPrimas;
            for (RhEmpleadoCargo empleadoCargo : listaEmpleadoCargo) {
                rhPrimas=rhPrimasFactory(empleadoCargo, rhPeriodoGestion);
                persistRhPrimas(rhPrimas);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public RhPrimas rhPrimasFactory(RhEmpleadoCargo empleadoCargo, RhPeriodoGestion rhPeriodoGestion) {
        RhPrimas rhPrimas = new RhPrimas();
        rhPrimas.setMontoPrima(BigDecimal.ZERO);
        rhPrimas.setRhEmpleadoCargo(empleadoCargo);
        rhPrimas.setRhPeriodoGestion(rhPeriodoGestion);
        return rhPrimas;
    }

    @Override
    public RhPrimas persistRhPrimas(RhPrimas rhPrimas) throws Exception {
        try {
            rhPrimas.setIdPrimas(null);
            dao.create(rhPrimas);
            return rhPrimas;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RhPrimas> listaRhPrimasPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {
            
             List<RhEmpleado> empleados=rhEmpleadoService.listaRhEmpleadosNuevosSinPrima(idPeriodoGestion);
            
            if(empleados.size()>0)
            {   log.info("Nuevos empleados sin Primas en el periodo:: "+empleados.size());
                RhPrimas rhPrimas=null;
                RhPeriodoGestion rhPeriodoGestion=rhPeriodoGestionService.getRhPeriodoGestionById(idPeriodoGestion);
                RhEmpleadoCargo empleadoCargo=null;
                for (RhEmpleado emp : empleados) {
                    empleadoCargo=rhEmpleadoCargoService.obtieneEmpleadoCargoPorIdEmpleado(emp.getIdEmpleado());
                    rhPrimas=rhPrimasFactory(empleadoCargo, rhPeriodoGestion);
                    persistRhPrimas(rhPrimas);
                }
            }
            
            
            List<RhPrimas> lista = dao.find(""
                    + "select j "
                    + "from RhPrimas j "
                    + "where j.fechaBaja is null "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhPrimas obtieneRhPrimasPorIdEmpleadoCargo(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            List<RhPrimas> lista = dao.find(""
                    + "select j "
                    + "from RhPrimas j "
                    + "where j.fechaBaja is null "
                    + "and j.rhEmpleadoCargo.idEmpleadoCargo = " + idEmpleadoCargo + " "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void importaPrimasExcel(InputStream fileInputStream, Long idPeriodoGestion) throws Exception {
        try {
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int startingRow = 1;
            int endingRow = ImportUtils.lookForRowWithValue(sheet, "");
            sheet = workbook.getSheetAt(0);
            String codigoEmpleado = "";
            BigDecimal montoPrima = new BigDecimal(BigInteger.ZERO);
            RhEmpleadoCargo rhEmpleadoCargo;
            RhPrimas rhPrimas = new RhPrimas();
            while (startingRow <= endingRow) {
                Row row = sheet.getRow(startingRow);
                Iterator<Cell> cellIterator = row.cellIterator();
                rhEmpleadoCargo = new RhEmpleadoCargo();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.println("ENTRO NUMERIC:" + cell.getColumnIndex());
                            if (cell.getColumnIndex() == 0) {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                codigoEmpleado = cell.getStringCellValue();
                                rhEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoPorCodigo(codigoEmpleado);
                                rhPrimas = obtieneRhPrimasPorIdEmpleadoCargo(rhEmpleadoCargo.getIdEmpleadoCargo(), idPeriodoGestion);
                            }
                            if (cell.getColumnIndex() == 6) {
                                montoPrima = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhPrimas.setMontoPrima(montoPrima);
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.println("ENTRO STRING:" + cell.getColumnIndex());
                            if (cell.getColumnIndex() == 0)//Subtotal
                            {
//                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                codigoEmpleado = cell.getStringCellValue();
                                System.out.println("CODIGO EMPLEADO:" + codigoEmpleado);
                                rhEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoPorCodigo(codigoEmpleado);
                                rhPrimas = obtieneRhPrimasPorIdEmpleadoCargo(rhEmpleadoCargo.getIdEmpleadoCargo(), idPeriodoGestion);
                            }
                            break;
                    }

                }
                mergeRhPrimas(rhPrimas);
                startingRow++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
        }
    }

    @Override
    public RhPrimas mergeRhPrimas(RhPrimas rhPrimas) throws Exception {
        try {
            rhPrimas.setFechaAlta(new Date());
            rhPrimas.setUsuarioAlta("SYS");
            rhPrimas.setFechaModificacion(new Date());
            rhPrimas.setUsuarioModificacion("SYS");
            dao.update(rhPrimas);
            return rhPrimas;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public List<RhPrimas> modificaListaRhPrimas(List<RhPrimas> listaRhPrimas) throws Exception {
        try {
            for (RhPrimas rhPrimas : listaRhPrimas) {
                mergeRhPrimas(rhPrimas);
            }
            return listaRhPrimas;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public void modificaCamposEmpleadoCargoParaPrimas(RhEmpleadoCargo rhEmpleadoCargoNuevo, Long idPeriodoGestion) throws Exception {
        try {
            RhPrimas primas = obtieneRhPrimasPorIdEmpleadoCargo(rhEmpleadoCargoNuevo.getIdAntecesor(), idPeriodoGestion);
            primas.setRhEmpleadoCargo(rhEmpleadoCargoNuevo);
            mergeRhPrimas(primas);
        } catch (Exception e) {
            throw e;
        }
    }

}
