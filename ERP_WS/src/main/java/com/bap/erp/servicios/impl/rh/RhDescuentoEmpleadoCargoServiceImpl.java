package com.bap.erp.servicios.impl.rh;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.modelo.pojo.rh.RhDescuentoPojo;
import com.bap.erp.modelo.pojo.rh.RhEmpleadoDescuentoPojo;
import com.bap.erp.modelo.rh.RhDescuento;
import com.bap.erp.modelo.rh.RhDescuentoEmpleadoCargo;
import com.bap.erp.modelo.rh.RhEmpleado;
import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import com.bap.erp.modelo.rh.RhPeriodoGestion;
import com.bap.erp.servicios.rh.RhDescuentoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhDescuentoService;
import com.bap.erp.servicios.rh.RhEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhEmpleadoService;
import com.bap.erp.servicios.rh.RhPeriodoGestionService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RhDescuentoEmpleadoCargoServiceImpl implements RhDescuentoEmpleadoCargoService {

    IGenericDao<RhDescuentoEmpleadoCargo> dao;

    @Autowired
    public RhEmpleadoCargoService rhEmpleadoCargoService;
    @Autowired
    public RhDescuentoService rhDescuentoService;
    @Autowired
    public RhPeriodoGestionService rhPeriodoGestionService;
    @Autowired
    public RhEmpleadoService rhEmpleadoService;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhDescuentoEmpleadoCargoServiceImpl.class);

    @Autowired
    public void setDao(IGenericDao<RhDescuentoEmpleadoCargo> daoToSet) {
        dao = daoToSet;
        dao.setClazz(RhDescuentoEmpleadoCargo.class);
    }

    @Transactional
    public RhDescuentoEmpleadoCargo persistRhDescuentoEmpleadoCargo(RhDescuentoEmpleadoCargo rhDescuentoEmpleadoCargo) throws Exception {
        try {
            rhDescuentoEmpleadoCargo.setIdDescuentoEmpleadoCargo(null);
//            rhDescuentoEmpleadoCargo.setFechaAlta(new Date());
//            rhDescuentoEmpleadoCargo.setUsuarioAlta("SYS");
            dao.create(rhDescuentoEmpleadoCargo);
            return rhDescuentoEmpleadoCargo;
        } catch (Exception e) {
            throw e;
        }
    }

    
    @Override
    public void registraDescuentoEmpleadoCargoPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {
            RhDescuentoEmpleadoCargo rhDescuentoEmpleadoCargo;
            RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.encuentraRhPeriodoGestionPorId(idPeriodoGestion);
            if (!existeDescuentoPorPeriodo(idPeriodoGestion)) {
                List<RhEmpleadoCargo> listaEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoVigente(idPeriodoGestion);
                List<RhDescuento> listaDescuentos = rhDescuentoService.obtieneDescuentosVigentes();
                for (RhDescuento descuento : listaDescuentos) {
                    for (RhEmpleadoCargo empleadoCargo : listaEmpleadoCargo) {
                        rhDescuentoEmpleadoCargo = rhDescuentoEmpleadoCargoFactory(empleadoCargo, rhPeriodoGestion, descuento);
                        persistRhDescuentoEmpleadoCargo(rhDescuentoEmpleadoCargo);                        
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public RhDescuentoEmpleadoCargo rhDescuentoEmpleadoCargoFactory(RhEmpleadoCargo empleadoCargo, RhPeriodoGestion rhPeriodoGestion, RhDescuento descuento) {
        RhDescuentoEmpleadoCargo rhDescuentoEmpleadoCargo = new RhDescuentoEmpleadoCargo();
        rhDescuentoEmpleadoCargo.setRhDescuento(descuento);
        rhDescuentoEmpleadoCargo.setRhEmpleadoCargo(empleadoCargo);
        rhDescuentoEmpleadoCargo.setMontoDescuento(BigDecimal.ZERO);
        rhDescuentoEmpleadoCargo.setRhPeriodoGestion(rhPeriodoGestion);

        return rhDescuentoEmpleadoCargo;

    }

    @Override
    public Boolean existeDescuentoPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {
            List<RhDescuentoEmpleadoCargo> lista = dao.find(""
                    + "select j "
                    + "from RhDescuentoEmpleadoCargo j "
                    + "where j.fechaBaja is null "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + " ");
            return !lista.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RhEmpleadoDescuentoPojo> cargaEmpleadoDescuento(Long idPeriodoGestion) throws Exception {
        try {
            List<RhEmpleado> empleados = rhEmpleadoService.listaRhEmpleadosNuevosSinDescuentos(idPeriodoGestion);

            if (empleados.size() > 0) {
                log.info("Nuevos empleados sin Descuentos en el periodo:: " + empleados.size());
                RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.getRhPeriodoGestionById(idPeriodoGestion);
                List<RhDescuento> listaDescuentos = rhDescuentoService.obtieneDescuentosVigentes();

                RhEmpleadoCargo empleadoCargo = null;
                RhDescuentoEmpleadoCargo rhDescuentoEmpleadoCargo = null;

                for (RhEmpleado emp : empleados) {
                    empleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoPorIdEmpleado(emp.getIdEmpleado());
                    for (RhDescuento descuento : listaDescuentos) {
                        rhDescuentoEmpleadoCargo = rhDescuentoEmpleadoCargoFactory(empleadoCargo, rhPeriodoGestion, descuento);
                        persistRhDescuentoEmpleadoCargo(rhDescuentoEmpleadoCargo);

                    }
                }
            }

            RhEmpleadoDescuentoPojo rhEmpleadoDescuentoPojo;
            List<RhEmpleadoCargo> listaEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoVigente(idPeriodoGestion);
            List<RhDescuentoPojo> listaDescuentosPojo;
            RhDescuentoPojo rhDescuentoPojo;
            List<RhEmpleadoDescuentoPojo> listaEmpleadoDescuentoPojo = new ArrayList<RhEmpleadoDescuentoPojo>();
            List<RhDescuentoEmpleadoCargo> listaDescuentos;
            for (RhEmpleadoCargo listaEmpleadoCargo1 : listaEmpleadoCargo) {
                rhEmpleadoDescuentoPojo = new RhEmpleadoDescuentoPojo();
                rhEmpleadoDescuentoPojo.setIdEmpleadoCargo(listaEmpleadoCargo1.getIdEmpleadoCargo());
                rhEmpleadoDescuentoPojo.setCodigo(listaEmpleadoCargo1.getRhEmpleado().getCodigo());
                rhEmpleadoDescuentoPojo.setNombreCompleto(listaEmpleadoCargo1.getRhEmpleado().getNombreCompleto());
                rhEmpleadoDescuentoPojo.setDepartamento(listaEmpleadoCargo1.getRhCargo().getErpDepartamento().getDescripcion());
                rhEmpleadoDescuentoPojo.setCargo(listaEmpleadoCargo1.getRhCargo().getNombreCargo());
                rhEmpleadoDescuentoPojo.setCarnetIdentidad(listaEmpleadoCargo1.getRhEmpleado().getNumeroDocumento());
                rhEmpleadoDescuentoPojo.setNumeroItem(listaEmpleadoCargo1.getNumeroItem());
                listaDescuentos = obtieneDescuentosPorEmpleado(listaEmpleadoCargo1.getRhEmpleado(), idPeriodoGestion);
                listaDescuentosPojo = new ArrayList<RhDescuentoPojo>();
                for (RhDescuentoEmpleadoCargo listaDescuento : listaDescuentos) {
                    rhDescuentoPojo = new RhDescuentoPojo();
                    rhDescuentoPojo.setIdDescuento(listaDescuento.getRhDescuento().getIdDescuento());
                    rhDescuentoPojo.setDescripcion(listaDescuento.getRhDescuento().getDescripcion());
                    rhDescuentoPojo.setMonto(listaDescuento.getMontoDescuento());
                    listaDescuentosPojo.add(rhDescuentoPojo);
                }
                rhEmpleadoDescuentoPojo.setListaDescuentos(listaDescuentosPojo);
                listaEmpleadoDescuentoPojo.add(rhEmpleadoDescuentoPojo);
            }
            return listaEmpleadoDescuentoPojo;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<RhDescuentoEmpleadoCargo> obtieneDescuentosPorEmpleado(RhEmpleado rhEmpleado, Long idPeriodoGestion) throws Exception {
        try {
            List<RhDescuentoEmpleadoCargo> listaDescuentoEmpleadoCargo = dao.find(""
                    + "select j "
                    + "from RhDescuentoEmpleadoCargo j "
                    + "where j.fechaBaja is null "
                    + "and j.rhEmpleadoCargo.rhEmpleado.idEmpleado = '" + rhEmpleado.getIdEmpleado() + "' "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = '" + idPeriodoGestion + "' ");
            return listaDescuentoEmpleadoCargo;
        } catch (Exception e) {
            throw e;
        }
    }

    public void importaValoresExcel(InputStream fileInputStream, Long idPeriodoGestion) throws Exception {
        try {
            System.out.println("entra al metodo");
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int startingRow = 0;
            int endingRow = lookForRowWithValue(sheet, "");
            int limiteDescuentoTitulo = 6;
            int limiteDescuentoEmpleado;
            sheet = workbook.getSheetAt(0);
            String codigoEmpleado;
            String codigoDescuento;
            BigDecimal monto;
            RhEmpleado rhEmpleado;
            Long idDescuento;
            RhDescuento rhDescuento;
            Map<Integer, Long> mapaDescuento = new HashMap<Integer, Long>();
            while (startingRow <= endingRow) {
                Row row = sheet.getRow(startingRow);
                Iterator<Cell> cellIterator = row.cellIterator();
                rhEmpleado = new RhEmpleado();
                limiteDescuentoEmpleado = 6;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (startingRow == 0) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                if (cell.getColumnIndex() == limiteDescuentoTitulo) {
                                    codigoDescuento = cell.getStringCellValue();
                                    rhDescuento = rhDescuentoService.obtieneDescuentoPorDescripcion(codigoDescuento);
                                    mapaDescuento.put(limiteDescuentoTitulo, rhDescuento.getIdDescuento());
                                    limiteDescuentoTitulo++;
                                }
                                break;
                        }
                    } else {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                if (cell.getColumnIndex() == 0) {
                                    codigoEmpleado = cell.getStringCellValue();
                                    log.info("codigo Empleado:::: " + codigoEmpleado);
                                    rhEmpleado = rhEmpleadoService.obtieneEmpleadoPorCodigo(codigoEmpleado);
                                }
                            case Cell.CELL_TYPE_NUMERIC:
//                                log.info("la celda es:::::" + cell.getColumnIndex());
//                                if (cell.getColumnIndex() == 0) {
//                                    cell.setCellType(Cell.CELL_TYPE_STRING);
//                                    codigoEmpleado = cell.getStringCellValue();
//                                    log.info("codigo Empleado:::: " + codigoEmpleado);
//                                    rhEmpleado = rhEmpleadoService.obtieneEmpleadoPorCodigo(codigoEmpleado);
//                                }
                                if (cell.getColumnIndex() == limiteDescuentoEmpleado) {
                                    monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                    idDescuento = mapaDescuento.get(limiteDescuentoEmpleado);
                                    modificaEmpleadoCargoPorIdEmpleadoIdCargoMonto(rhEmpleado.getIdEmpleado(), idDescuento, monto, idPeriodoGestion);
                                    limiteDescuentoEmpleado++;
                                }
                                break;
                        }
                    }
                }
                startingRow++;
            }
            log.info("mapa es:::" + mapaDescuento);
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

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public void modificaEmpleadoCargoPorIdEmpleadoIdCargoMonto(Long idEmpleado, Long idDescuento, BigDecimal monto, Long idPeriodoGestion) throws Exception {
        try {
            RhDescuentoEmpleadoCargo rhDescuentoEmpleadoCargo;
            List<RhDescuentoEmpleadoCargo> lista = dao.find(""
                    + "select j "
                    + "from RhDescuentoEmpleadoCargo j "
                    + "where j.fechaBaja is null "
                    + "and j.rhEmpleadoCargo.rhEmpleado.idEmpleado = " + idEmpleado + " "
                    + "and j.rhDescuento.idDescuento = " + idDescuento + " "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            if (!lista.isEmpty()) {
                rhDescuentoEmpleadoCargo = lista.get(0);
                rhDescuentoEmpleadoCargo.setMontoDescuento(monto);
                mergeRhDescuentoEmpleadoCargo(rhDescuentoEmpleadoCargo);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhDescuentoEmpleadoCargo mergeRhDescuentoEmpleadoCargo(RhDescuentoEmpleadoCargo rhDescuentoEmpleadoCargo) throws Exception {
        try {
            rhDescuentoEmpleadoCargo.setUsuarioAlta("SYS");
            rhDescuentoEmpleadoCargo.setFechaAlta(new Date());
            rhDescuentoEmpleadoCargo.setUsuarioModificacion("SYS");
            rhDescuentoEmpleadoCargo.setFechaModificacion(new Date());
            dao.update(rhDescuentoEmpleadoCargo);
            return rhDescuentoEmpleadoCargo;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void modificaDescuentoEmpleadoCargo(List<RhEmpleadoDescuentoPojo> listaEmpleadoDescuentoPojo, Long idPeriodoGestion) throws Exception {
        try {
            RhEmpleado rhEmpleado;
            for (RhEmpleadoDescuentoPojo listaEmpleadoDescuentoPojo1 : listaEmpleadoDescuentoPojo) {
                rhEmpleado = rhEmpleadoService.obtieneEmpleadoPorIdEmpleadoCargo(listaEmpleadoDescuentoPojo1.getIdEmpleadoCargo());
                for (RhDescuentoPojo rhDescuento : listaEmpleadoDescuentoPojo1.getListaDescuentos()) {
                    modificaEmpleadoCargoPorIdEmpleadoIdCargoMonto(rhEmpleado.getIdEmpleado(), rhDescuento.getIdDescuento(), rhDescuento.getMonto(), idPeriodoGestion);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public BigDecimal otrosDescuentoPorEmpleadoYPeriodo(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal otrosDescuentos = new BigDecimal(BigInteger.ZERO);
            List<RhDescuentoEmpleadoCargo> lista = dao.find(""
                    + "select j "
                    + "from RhDescuentoEmpleadoCargo j "
                    + "where j.fechaBaja is null "
                    + "and j.rhEmpleadoCargo.idEmpleadoCargo = " + idEmpleadoCargo + " "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            if (!lista.isEmpty()) {
                for (RhDescuentoEmpleadoCargo lista1 : lista) {
                    otrosDescuentos = otrosDescuentos.add(lista1.getMontoDescuento());
                }
            }            
            return otrosDescuentos;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void actualizaDescuentoEmpleadoCargo(RhDescuento rhDescuento, Long idPeriodoGestion) throws Exception {
        try {
            RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.encuentraRhPeriodoGestionPorId(idPeriodoGestion);
            List<RhEmpleadoCargo> lista = rhEmpleadoCargoService.obtieneEmpleadoCargoVigente(idPeriodoGestion);
            RhDescuentoEmpleadoCargo rhDescuentoEmpleadoCargo;
            for (RhEmpleadoCargo lista1 : lista) {
                rhDescuentoEmpleadoCargo = new RhDescuentoEmpleadoCargo();
                rhDescuentoEmpleadoCargo.setMontoDescuento(BigDecimal.ZERO);
                rhDescuentoEmpleadoCargo.setRhDescuento(rhDescuento);
                rhDescuentoEmpleadoCargo.setRhEmpleadoCargo(lista1);
                rhDescuentoEmpleadoCargo.setRhPeriodoGestion(rhPeriodoGestion);
                persistRhDescuentoEmpleadoCargo(rhDescuentoEmpleadoCargo);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public List<RhDescuentoEmpleadoCargo> obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            List<RhDescuentoEmpleadoCargo> lista = dao.find(""
                    + "select h "
                    + "from RhDescuentoEmpleadoCargo h "
                    + "where h.fechaBaja is null "
                    + "and h.rhEmpleadoCargo.idEmpleadoCargo = " + idEmpleadoCargo + " "
                    + "and h.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public void modificaCamposEmpleadoCargoListaRhDescuentoEmpleadoCargo(RhEmpleadoCargo rhEmpleadoCargoNuevo, Long idPeriodoGestion) throws Exception {
        try {
            List<RhDescuentoEmpleadoCargo> listaRhDescuentoEmpleadoCargo = obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(rhEmpleadoCargoNuevo.getIdAntecesor(), idPeriodoGestion);
            for (RhDescuentoEmpleadoCargo descuentoEmpleadoCargo : listaRhDescuentoEmpleadoCargo) {
                descuentoEmpleadoCargo.setRhEmpleadoCargo(rhEmpleadoCargoNuevo);
                mergeRhDescuentoEmpleadoCargo(descuentoEmpleadoCargo);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public List<RhDescuentoEmpleadoCargo> obtieneDescuentosPorIdDescuentoAndIdPeriodo(Long idDescuento, Long idPeriodoGestion) throws Exception {
        try {
            List<RhDescuentoEmpleadoCargo> lista = dao.find(""
                    + "select h "
                    + "from RhDescuentoEmpleadoCargo h "
                    + "where h.fechaBaja is null "
                    + "and h.rhDescuento.idDescuento = " + idDescuento + " "
                    + "and h.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }
    
}
