package com.bap.erp.servicios.impl.rh;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.enums.EnumTipoEntidad;
import com.bap.erp.modelo.rh.RhEmpleado;
import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import com.bap.erp.modelo.rh.RhPeriodoGestion;
import com.bap.erp.modelo.rh.RhVariacion;
import com.bap.erp.servicios.rh.RhEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhEmpleadoService;
import com.bap.erp.servicios.rh.RhPeriodoGestionService;
import com.bap.erp.servicios.rh.RhVariacionService;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
public class RhVariacionServiceImpl implements RhVariacionService {

    @Autowired
    RhEmpleadoCargoService rhEmpleadoCargoService;

    @Autowired
    RhPeriodoGestionService rhPeriodoGestionService;

    @Autowired
    RhEmpleadoService rhEmpleadoService;

    IGenericDao<RhVariacion> dao;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhVariacionServiceImpl.class);

    @Autowired
    public void setDao(IGenericDao<RhVariacion> daoToSet) {
        dao = daoToSet;
        dao.setClazz(RhVariacion.class);
    }

    public RhVariacion persistRhVariacion(RhVariacion rhVariacion) throws Exception {
        try {
            rhVariacion.setIdVariacion(null);
//            rhVariacion.setUsuarioAlta("SYS");
//            rhVariacion.setFechaAlta(new Date());
            dao.create(rhVariacion);
            return rhVariacion;
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean existeVariacionesParaPeriodo(RhPeriodoGestion rhPeriodoGestion, String tipoParametro) throws Exception {
        try {
            if (tipoParametro.equals(EnumTipoEntidad.VARIACIONES.getCodigo())) {
                List<RhVariacion> lista = dao.find(""
                        + "select j "
                        + "from RhVariacion j "
                        + "where j.fechaBaja is null "
                        + "and j.rhPeriodoGestion.idPeriodoGestion = " + rhPeriodoGestion.getIdPeriodoGestion() + "");
                log.info("existeVariacionesParaPeriodo:: " + lista.isEmpty());
                return !lista.isEmpty();
            }
            return false;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean existeVariacionPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {
            List<RhVariacion> lista = dao.find(""
                    + "select j "
                    + "from RhVariacion j "
                    + "where j.fechaBaja is null "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            log.info("existeVariacionesParaPeriodo:: " + !lista.isEmpty());
            return !lista.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void registrarRhVariacionPorPeriodo(Long idPeriodoGestion) throws Exception {

        //Obtenemos los empleados vigentes en el periodo
        List<RhEmpleadoCargo> listaEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoVigente(idPeriodoGestion);
        RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.encuentraRhPeriodoGestionPorId(idPeriodoGestion);
        RhVariacion rhVariacion;

        //Creamos las variaciones del periodo / gestion por cada empleado
        RhEmpleado empleado = null;
        for (RhEmpleadoCargo empleadoCargo : listaEmpleadoCargo) {
            empleado = empleadoCargo.getRhEmpleado();
            log.info("********************************* empleadoCargo: " + empleadoCargo.getIdEmpleadoCargo() + " empleado: " + empleado.getIdEmpleado());
            
            rhVariacion=rhVariacionFactory(empleadoCargo, rhPeriodoGestion);
            
            persistRhVariacion(rhVariacion);
        }
    }
    
    public RhVariacion rhVariacionFactory(RhEmpleadoCargo empleadoCargo, RhPeriodoGestion rhPeriodoGestion)
    {   
            RhVariacion rhVariacion = new RhVariacion();
            RhEmpleado empleado = empleadoCargo.getRhEmpleado();
            log.info("********************************* empleadoCargo: " + empleadoCargo.getIdEmpleadoCargo() + " empleado: " + empleado.getIdEmpleado());
            

            rhVariacion.setRhPeriodoGestion(rhPeriodoGestion);
            rhVariacion.setRhEmpleadoCargo(empleadoCargo);

            rhVariacion.setDiasAjuste(BigDecimal.ZERO);
            rhVariacion.setDiasDeFalta(BigDecimal.ZERO);
            rhVariacion.setDiasDeMulta(BigDecimal.ZERO);
            rhVariacion.setDiasDomingo(BigDecimal.ZERO);
            rhVariacion.setDiasFeriado(BigDecimal.ZERO);
            rhVariacion.setDiasNoTrabajados(BigDecimal.ZERO);

            rhVariacion.setHorasAjuste(BigDecimal.ZERO);
            rhVariacion.setHorasDomingo(BigDecimal.ZERO);
            rhVariacion.setHorasExtras(BigDecimal.ZERO);
            rhVariacion.setHorasFeriado(BigDecimal.ZERO);
            rhVariacion.setHorasNocturnas(BigDecimal.ZERO);
            rhVariacion.setDiasDescanso(BigDecimal.ZERO);
            rhVariacion.setDiasDescansoTrabajados(BigDecimal.ZERO);
            rhVariacion.setItem(empleadoCargo.getNumeroItem());

            /**
             * INGRESO A LA EMPRESA EN EL PERIODO ACTUAL*
             */
            log.info("rhVariacion.getDiasTrabajados():: " + rhVariacion.getDiasTrabajados());
            log.info("empleado.getFechaIngreso()::: " + empleado.getFechaIngreso());
            log.info("empleado.getFechaRetiro()::: " + empleado.getFechaRetiro());

            if (rhVariacion.getDiasTrabajados() == null && empleado.getFechaIngreso() != null) {
                log.info("rhPeriodoGestion.getInicio().getTime() < empleado.getFechaIngreso().getTime() :: " + (rhPeriodoGestion.getInicio().getTime() < empleado.getFechaIngreso().getTime()));
                log.info("empleado.getFechaIngreso().getTime() < rhPeriodoGestion.getFin().getTime():: " + (empleado.getFechaIngreso().getTime() < rhPeriodoGestion.getFin().getTime()));

                if (rhPeriodoGestion.getInicio().getTime() < empleado.getFechaIngreso().getTime() && empleado.getFechaIngreso().getTime() < rhPeriodoGestion.getFin().getTime()) {
                    long diff = rhPeriodoGestion.getFin().getTime() - empleado.getFechaIngreso().getTime();
                    diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
                    log.info("INGRESO A LA EMPRESA EN EL PERIODO ACTUAL:: fechIngreso: " + empleado.getFechaIngreso() + " dias trabajados:: " + diff);
                    rhVariacion.setDiasTrabajados(new BigDecimal(diff));

                }
            }
            if (rhVariacion.getDiasTrabajados() == null && empleado.getFechaRetiro() != null) {
                /**
                 * *SALIDA DE LA EMPRESA EN EL PERIODO ACTUAL**
                 */
                if (rhPeriodoGestion.getInicio().getTime() < empleado.getFechaRetiro().getTime() && empleado.getFechaRetiro().getTime() < rhPeriodoGestion.getFin().getTime()) {
                    long diff = rhPeriodoGestion.getFin().getTime() - empleado.getFechaRetiro().getTime();
                    diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    log.info("SALIDA DE LA EMPRESA EN EL PERIODO ACTUAL:: fechasRetiro: " + empleado.getFechaRetiro() + " dias trabajados:: " + diff);
                    rhVariacion.setDiasTrabajados(new BigDecimal(diff));
                }
            }
            if (rhVariacion.getDiasTrabajados() == null) {
                rhVariacion.setDiasTrabajados(new BigDecimal("30"));
            }
            
            return rhVariacion;
        
    }

    @Override
    public List<RhVariacion> getRhVariacionPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {
            
            List<RhEmpleado> empleados=rhEmpleadoService.listaRhEmpleadosNuevosSinVariaciones(idPeriodoGestion);
            
            if(empleados.size()>0)
            {   log.info("Nuevos empleados sin Variaciones en el periodo:: "+empleados.size());
                RhVariacion rhVariacion=null;
                RhPeriodoGestion rhPeriodoGestion=rhPeriodoGestionService.getRhPeriodoGestionById(idPeriodoGestion);
                RhEmpleadoCargo empleadoCargo=null;
                for (RhEmpleado emp : empleados) {
                    empleadoCargo=rhEmpleadoCargoService.obtieneEmpleadoCargoPorIdEmpleado(emp.getIdEmpleado());
                    rhVariacion=rhVariacionFactory(empleadoCargo, rhPeriodoGestion);
                    persistRhVariacion(rhVariacion);
                }
            }
            
            List<RhVariacion> lista = dao.find(""
                    + "select j "
                    + "from RhVariacion j "
                    + "where j.fechaBaja is null "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + " ");
            
            
            
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void importaValoresExcel(InputStream fileInputStream, Long idPeriodoGestion) throws Exception {
        try {
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int startingRow = 1;
            int endingRow = lookForRowWithValue(sheet, "");
            sheet = workbook.getSheetAt(0);
            String codigoEmpleado = "";
            BigDecimal monto = new BigDecimal(BigInteger.ZERO);
            RhEmpleadoCargo rhEmpleadoCargo;
            RhVariacion rhVariacion = new RhVariacion();
            while (startingRow <= endingRow) {
                Row row = sheet.getRow(startingRow);
                Iterator<Cell> cellIterator = row.cellIterator();
                rhEmpleadoCargo = new RhEmpleadoCargo();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            if (cell.getColumnIndex() == 0) {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                codigoEmpleado = cell.getStringCellValue();
                                rhEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoPorCodigo(codigoEmpleado);
                                rhVariacion = obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(rhEmpleadoCargo.getIdEmpleadoCargo(), idPeriodoGestion);
                            }
                            if (cell.getColumnIndex() == 4) {
                                monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhVariacion.setDiasTrabajados(monto);
                            }
                            if (cell.getColumnIndex() == 5) {
                                monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhVariacion.setDiasNoTrabajados(monto);
                            }
                            if (cell.getColumnIndex() == 6) {
                                monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhVariacion.setDiasDeFalta(monto);
                            }
                            if (cell.getColumnIndex() == 7) {
                                monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhVariacion.setDiasDeMulta(monto);
                            }
                            if (cell.getColumnIndex() == 8) {
                                monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhVariacion.setDiasAjuste(monto);
                            }
                            if (cell.getColumnIndex() == 9) {
                                monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhVariacion.setDiasFeriado(monto);
                            }
                            if (cell.getColumnIndex() == 10) {
                                monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhVariacion.setDiasDomingo(monto);
                            }
                            if (cell.getColumnIndex() == 11) {
                                monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhVariacion.setHorasExtras(monto);
                            }
                            if (cell.getColumnIndex() == 12) {
                                monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhVariacion.setHorasNocturnas(monto);
                            }
                            if (cell.getColumnIndex() == 13) {
                                monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhVariacion.setHorasAjuste(monto);
                            }
                            if (cell.getColumnIndex() == 14) {
                                monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhVariacion.setHorasFeriado(monto);
                            }
                            if (cell.getColumnIndex() == 15) {
                                monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhVariacion.setHorasDomingo(monto);
                            }
                            if (cell.getColumnIndex() == 16) {
                                monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhVariacion.setDiasDescanso(monto);
                            }
                            if (cell.getColumnIndex() == 17) {
                                monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhVariacion.setDiasDescansoTrabajados(monto);
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.println("ENTRO STRING:" + cell.getColumnIndex());
                            if (cell.getColumnIndex() == 0) {
                                codigoEmpleado = cell.getStringCellValue();
                                rhEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoPorCodigo(codigoEmpleado);
                                rhVariacion = obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(rhEmpleadoCargo.getIdEmpleadoCargo(), idPeriodoGestion);
                            }

                            break;
                    }

                }
                mergeRhVariacion(rhVariacion);
                startingRow++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
        }
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

    @Override
    public RhVariacion obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            List<RhVariacion> listaVariacion = dao.find(""
                    + "select j "
                    + "from RhVariacion j "
                    + "where j.fechaBaja is null "
                    + "and j.rhEmpleadoCargo.idEmpleadoCargo = " + idEmpleadoCargo + " "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            if (!listaVariacion.isEmpty()) {
                return listaVariacion.get(0);
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhVariacion mergeRhVariacion(RhVariacion rhVariacion) throws Exception {
        try {
            rhVariacion.setUsuarioAlta("SYS");
            rhVariacion.setUsuarioModificacion("SYS");
            rhVariacion.setFechaAlta(new Date());
            rhVariacion.setFechaModificacion(new Date());
            rhVariacion = dao.update(rhVariacion);
            return rhVariacion;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public void modificaRhVariacion(List<RhVariacion> listaVariacion, Long idPeriodoGestion) throws Exception {
        try {
            for (RhVariacion listaVariacion1 : listaVariacion) {
                mergeRhVariacion(listaVariacion1);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void modificaCamposEmpleadoCargoParaVariacion(RhEmpleadoCargo rhEmpleadoCargoNuevo, Long idPeriodoGestion) throws Exception {
        try {
            RhVariacion variacion = obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(rhEmpleadoCargoNuevo.getIdAntecesor(), idPeriodoGestion);
            variacion.setRhEmpleadoCargo(rhEmpleadoCargoNuevo);
            mergeRhVariacion(variacion);
        } catch (Exception e) {
            throw e;
        }
    }

}
