package com.bap.erp.servicios.impl.rh;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.utils.ImportUtils;
import com.bap.erp.commons.utils.ObjectUtils;
import com.bap.erp.modelo.rh.RhEmpleado;
import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import com.bap.erp.modelo.rh.RhPeriodoGestion;
import com.bap.erp.modelo.rh.RhPlanillaImpositiva;
import com.bap.erp.modelo.rh.RhRcIva;
import com.bap.erp.servicios.rh.RhEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhEmpleadoService;
import com.bap.erp.servicios.rh.RhPeriodoGestionService;
import com.bap.erp.servicios.rh.RhPlanillaImpositivaService;
import com.bap.erp.servicios.rh.RhRcIvaService;
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
public class RhRcIvaServiceImpl implements RhRcIvaService {

    private final BigDecimal CERO = new BigDecimal(0L);

    @Autowired
    RhEmpleadoService rhEmpleadoService;

    IGenericDao<RhRcIva> dao;

    @Autowired
    public void setDao(IGenericDao<RhRcIva> daoToSet) {
        dao = daoToSet;
        dao.setClazz(RhRcIva.class);
    }

    @Autowired
    RhEmpleadoCargoService rhEmpleadoCargoService;

    @Autowired
    RhPeriodoGestionService rhPeriodoGestionService;

    @Autowired
    RhPlanillaImpositivaService rhPlanillaImpositivaService;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhRcIvaServiceImpl.class);

    @Override
    public boolean existeRcIvaPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {
            log.info("idPeriodoGestion......" + idPeriodoGestion);
            List<RhRcIva> lista = dao.find(""
                    + "select j "
                    + "from RhRcIva j "
                    + "where j.fechaBaja is null "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            return !lista.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void registrarRhRcIvaPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {
            List<RhEmpleadoCargo> listaEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoVigente(idPeriodoGestion);
            RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.encuentraRhPeriodoGestionPorId(idPeriodoGestion);
            RhRcIva rhRcIva;
            for (RhEmpleadoCargo empleadoCargo : listaEmpleadoCargo) {
                rhRcIva = rhRcIvaFactory(empleadoCargo, rhPeriodoGestion);
                persistRhRcIva(rhRcIva);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public RhRcIva rhRcIvaFactory(RhEmpleadoCargo empleadoCargo, RhPeriodoGestion rhPeriodoGestion) throws Exception {
        try {
            RhRcIva rhRcIva = new RhRcIva();
            rhRcIva.setMonto(BigDecimal.ZERO);
            rhRcIva.setRhEmpleadoCargo(empleadoCargo);
            rhRcIva.setRhPeriodoGestion(rhPeriodoGestion);
            rhRcIva.setSaldoAcumulado(encuentraSaldoAnterior());
            return rhRcIva;
        } catch (Exception e) {
            throw e;
        }

    }

    public BigDecimal encuentraSaldoAnterior() throws Exception {
        //metodo para encontrar el saldo anterior
        return BigDecimal.ZERO;
    }

    @Override
    public RhRcIva persistRhRcIva(RhRcIva rhRcIva) throws Exception {
        try {
            rhRcIva.setIdRcIva(null);
            dao.create(rhRcIva);
            return rhRcIva;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RhRcIva> listaRhRcIvaPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {

            List<RhEmpleado> empleados = rhEmpleadoService.listaRhEmpleadosNuevosSinRcIva(idPeriodoGestion);

            if (empleados.size() > 0) {
                log.info("Nuevos empleados sin RcIva en el periodo:: " + empleados.size());
                RhRcIva rhRcIva;
                RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.getRhPeriodoGestionById(idPeriodoGestion);
                RhEmpleadoCargo empleadoCargo = null;
                for (RhEmpleado empleado : empleados) {
                    empleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoPorIdEmpleado(empleado.getIdEmpleado());
                    rhRcIva = rhRcIvaFactory(empleadoCargo, rhPeriodoGestion);
                    persistRhRcIva(rhRcIva);
                }
            }

            List<RhRcIva> lista = dao.find(""
                    + "select j "
                    + "from RhRcIva j "
                    + "where j.fechaBaja is null "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhRcIva obtieneRhRcIvaPorIdEmpleadoCargo(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            List<RhRcIva> lista = dao.find(""
                    + "select j "
                    + "from RhRcIva j "
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

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public void importaValoresExcel(InputStream fileInputStream, Long idPeriodoGestion) throws Exception {
        try {
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int startingRow = 1;
            int endingRow = ImportUtils.lookForRowWithValue(sheet, "");
            sheet = workbook.getSheetAt(0);
            String codigoEmpleado = "";
            BigDecimal monto = new BigDecimal(BigInteger.ZERO);
            BigDecimal saldoAcumulado = new BigDecimal(BigInteger.ZERO);
            RhEmpleadoCargo rhEmpleadoCargo;
            RhRcIva rhRcIva;
            while (startingRow <= endingRow) {
                Row row = sheet.getRow(startingRow);
                Iterator<Cell> cellIterator = row.cellIterator();
                rhEmpleadoCargo = new RhEmpleadoCargo();
                rhRcIva = new RhRcIva();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            if (cell.getColumnIndex() == 0) {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                codigoEmpleado = cell.getStringCellValue();
                                rhEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoPorCodigo(codigoEmpleado);
                                rhRcIva = obtieneRhRcIvaPorIdEmpleadoCargo(rhEmpleadoCargo.getIdEmpleadoCargo(), idPeriodoGestion);
                            }
                            if (cell.getColumnIndex() == 6) {
                                saldoAcumulado = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhRcIva.setSaldoAcumulado(saldoAcumulado);
                            }
                            if (cell.getColumnIndex() == 7) {
                                monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                rhRcIva.setMonto(monto);
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            if (cell.getColumnIndex() == 0) {
                                codigoEmpleado = cell.getStringCellValue();
                                rhEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoPorCodigo(codigoEmpleado);
                                rhRcIva = obtieneRhRcIvaPorIdEmpleadoCargo(rhEmpleadoCargo.getIdEmpleadoCargo(), idPeriodoGestion);
                            }
                            break;
                    }

                }
                mergeRhRcIva(rhRcIva);
                startingRow++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
        }
    }

    @Override
    public RhRcIva mergeRhRcIva(RhRcIva rhRcIva) throws Exception {
        try {
            rhRcIva.setFechaAlta(new Date());
            rhRcIva.setUsuarioAlta("SYS");
            rhRcIva.setFechaModificacion(new Date());
            rhRcIva.setUsuarioModificacion("SYS");
            dao.update(rhRcIva);
            return rhRcIva;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public List<RhRcIva> modificaListaRhRcIva(List<RhRcIva> listaRhRcIva) throws Exception {
        try {
            for (RhRcIva rcIva : listaRhRcIva) {
                ObjectUtils.printObjectState(rcIva, "========================="+rcIva.getIdRcIva()+"==========================");
                mergeRhRcIva(rcIva);
            }
            return listaRhRcIva;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void modificaCamposEmpleadoCargoParaRcIva(RhEmpleadoCargo rhEmpleadoCargoNuevo, Long idPeriodoGestion) throws Exception {
        try {
            RhRcIva iva = obtieneRhRcIvaPorIdEmpleadoCargo(rhEmpleadoCargoNuevo.getIdAntecesor(), idPeriodoGestion);
            iva.setRhEmpleadoCargo(rhEmpleadoCargoNuevo);
            mergeRhRcIva(iva);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public void actualizaSaldoRcIva(Long idPeriodoGestion) throws Exception {
        try {
            RhRcIva rhRcIva;
            RhPeriodoGestion rhPeriodoGestionAnterior = rhPeriodoGestionService.obtieneRegistroPorPeriodoGestionAnterior(idPeriodoGestion);
            if(rhPeriodoGestionAnterior != null){
                List<RhPlanillaImpositiva> listaPlanillaImpositiva = rhPlanillaImpositivaService.generaPlanillaImpositiva(rhPeriodoGestionAnterior.getIdPeriodoGestion());
                for (RhPlanillaImpositiva rhPlanillaImpositiva : listaPlanillaImpositiva) {
                    if (rhPlanillaImpositiva.getSaldoPeriodoSiguiente().compareTo(CERO) == 1) {
                        rhRcIva = obtieneRhRcIvaPorIdEmpleadoCargo(rhPlanillaImpositiva.getRhEmpleadoCargo().getIdEmpleadoCargo(), idPeriodoGestion);
                        rhRcIva.setSaldoAcumulado(rhPlanillaImpositiva.getSaldoPeriodoSiguiente());
                        mergeRhRcIva(rhRcIva);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }    
    
    
}
