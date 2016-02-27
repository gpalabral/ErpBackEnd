package com.bap.erp.servicios.impl.rh;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.commons.utils.ImportUtils;
import com.bap.erp.commons.utils.NumberUtils;
import com.bap.erp.modelo.pojo.rh.RhCriterioDeIngresoPojo;
import com.bap.erp.modelo.pojo.rh.RhEmpleadoCriterioDeIngresoPojo;
import com.bap.erp.modelo.rh.RhCriterioDeIngreso;
import com.bap.erp.modelo.rh.RhCriterioDeIngresoEmpleadoCargo;
import com.bap.erp.modelo.rh.RhDescuentoEmpleadoCargo;
import com.bap.erp.modelo.rh.RhEmpleado;
import com.bap.erp.modelo.rh.RhEmpleadoCargo;
import com.bap.erp.modelo.rh.RhPeriodoGestion;
import com.bap.erp.servicios.rh.RhCriterioDeIngresoEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhCriterioDeIngresoService;
import com.bap.erp.servicios.rh.RhEmpleadoCargoService;
import com.bap.erp.servicios.rh.RhEmpleadoService;
import com.bap.erp.servicios.rh.RhPeriodoGestionService;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RhCriterioDeIngresoEmpleadoCargoServiceImpl implements RhCriterioDeIngresoEmpleadoCargoService {

    IGenericDao<RhCriterioDeIngresoEmpleadoCargo> dao;

    @Autowired
    public RhCriterioDeIngresoService rhCriterioDeIngresoService;

    @Autowired
    public RhEmpleadoCargoService rhEmpleadoCargoService;

    @Autowired
    public RhPeriodoGestionService rhPeriodoGestionService;

    @Autowired
    public RhEmpleadoService rhEmpleadoService;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RhCriterioDeIngresoEmpleadoCargoServiceImpl.class);

    @Autowired
    public void setDao(IGenericDao<RhCriterioDeIngresoEmpleadoCargo> daoToSet) {
        dao = daoToSet;
        dao.setClazz(RhCriterioDeIngresoEmpleadoCargo.class);
    }

    @Override
    public RhCriterioDeIngresoEmpleadoCargo persistRhCriterioDeIngresoEmpleadoCargo(RhCriterioDeIngresoEmpleadoCargo rhCriterioDeIngresoEmpleadoCargo) throws Exception {
        try {
            rhCriterioDeIngresoEmpleadoCargo.setIdCriterioDeIngresoEmpleadoCargo(null);
            dao.create(rhCriterioDeIngresoEmpleadoCargo);
            return rhCriterioDeIngresoEmpleadoCargo;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean existeCriterioDeIngresoEmpleadoCargoPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {
            List<RhCriterioDeIngresoEmpleadoCargo> lista = dao.find(""
                    + "select j "
                    + "from RhCriterioDeIngresoEmpleadoCargo j "
                    + "where j.fechaBaja is null "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            return !lista.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void registrarCriterioDeIngresoPorPeriodo(Long idPeriodoGestion) throws Exception {
        try {
            List<RhCriterioDeIngreso> listaCriterioDeIngreso = rhCriterioDeIngresoService.listaRhCriterioDeIngreso();
            List<RhEmpleadoCargo> listaEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoVigente(idPeriodoGestion);
            RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.encuentraRhPeriodoGestionPorId(idPeriodoGestion);
            RhCriterioDeIngresoEmpleadoCargo rhCriterioDeIngresoEmpleadoCargo;
            for (RhCriterioDeIngreso rhCriterioDeIngreso : listaCriterioDeIngreso) {
                for (RhEmpleadoCargo rhEmpleadoCargo : listaEmpleadoCargo) {
                    rhCriterioDeIngresoEmpleadoCargo = rhCriterioDeIngresoEmpleadoCargoFactory(rhEmpleadoCargo, rhPeriodoGestion, rhCriterioDeIngreso);
                    persistRhCriterioDeIngresoEmpleadoCargo(rhCriterioDeIngresoEmpleadoCargo);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public RhCriterioDeIngresoEmpleadoCargo rhCriterioDeIngresoEmpleadoCargoFactory(RhEmpleadoCargo empleadoCargo, RhPeriodoGestion rhPeriodoGestion, RhCriterioDeIngreso rhCriterioDeIngreso) {
        RhCriterioDeIngresoEmpleadoCargo rhCriterioDeIngresoEmpleadoCargo = new RhCriterioDeIngresoEmpleadoCargo();

        rhCriterioDeIngresoEmpleadoCargo.setMontoCriterioDeIngreso(BigDecimal.ZERO);
        rhCriterioDeIngresoEmpleadoCargo.setRhCriterioDeIngreso(rhCriterioDeIngreso);
        rhCriterioDeIngresoEmpleadoCargo.setRhEmpleadoCargo(empleadoCargo);
        rhCriterioDeIngresoEmpleadoCargo.setRhPeriodoGestion(rhPeriodoGestion);
        return rhCriterioDeIngresoEmpleadoCargo;
    }

    @Override
    public List<RhEmpleadoCriterioDeIngresoPojo> cargaEmpleadoCriterioDeIngreso(Long idPeriodoGestion) throws Exception {
        try {

            List<RhEmpleado> empleados = rhEmpleadoService.listaRhEmpleadosNuevosSinCriteriosIngreso(idPeriodoGestion);

            if (empleados.size() > 0) {
                log.info("Nuevos empleados sin Criterios de Ingreso en el periodo:: " + empleados.size());
                RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.getRhPeriodoGestionById(idPeriodoGestion);
                List<RhCriterioDeIngreso> listaCriterioDeIngreso = rhCriterioDeIngresoService.listaRhCriterioDeIngreso();

                RhEmpleadoCargo empleadoCargo = null;
                RhCriterioDeIngresoEmpleadoCargo rhCriterioDeIngresoEmpleadoCargo = null;

                for (RhEmpleado emp : empleados) {
                    empleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoPorIdEmpleado(emp.getIdEmpleado());
                    for (RhCriterioDeIngreso rhCriterioDeIngreso : listaCriterioDeIngreso) {
                        rhCriterioDeIngresoEmpleadoCargo = rhCriterioDeIngresoEmpleadoCargoFactory(empleadoCargo, rhPeriodoGestion, rhCriterioDeIngreso);
                        persistRhCriterioDeIngresoEmpleadoCargo(rhCriterioDeIngresoEmpleadoCargo);

                    }
                }
            }

            RhEmpleadoCriterioDeIngresoPojo rhEmpleadoCriterioDeIngresoPojo;
            List<RhEmpleadoCargo> listaEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoVigente(idPeriodoGestion);
            List<RhCriterioDeIngresoPojo> listaRhCriterioDeIngresoPojo;
            RhCriterioDeIngresoPojo rhCriterioDeIngresoPojo;
            List<RhEmpleadoCriterioDeIngresoPojo> listaEmpleadoCriterioDeIngresoPojo = new ArrayList<RhEmpleadoCriterioDeIngresoPojo>();
            List<RhCriterioDeIngresoEmpleadoCargo> listaCriterioDeIngresoEmpleadoCargo;
            for (RhEmpleadoCargo listaEmpleadoCargo1 : listaEmpleadoCargo) {
                rhEmpleadoCriterioDeIngresoPojo = new RhEmpleadoCriterioDeIngresoPojo();
                rhEmpleadoCriterioDeIngresoPojo.setIdEmpleadoCargo(listaEmpleadoCargo1.getIdEmpleadoCargo());
                rhEmpleadoCriterioDeIngresoPojo.setCodigo(listaEmpleadoCargo1.getRhEmpleado().getCodigo());
                rhEmpleadoCriterioDeIngresoPojo.setNombreCompleto(listaEmpleadoCargo1.getRhEmpleado().getNombreCompleto());
                rhEmpleadoCriterioDeIngresoPojo.setDepartamento(listaEmpleadoCargo1.getRhCargo().getErpDepartamento().getDescripcion());
                rhEmpleadoCriterioDeIngresoPojo.setCargo(listaEmpleadoCargo1.getRhCargo().getNombreCargo());
                rhEmpleadoCriterioDeIngresoPojo.setCarnetIdentidad(listaEmpleadoCargo1.getRhEmpleado().getNumeroDocumento());
                rhEmpleadoCriterioDeIngresoPojo.setNumeroItem(listaEmpleadoCargo1.getNumeroItem());

                listaCriterioDeIngresoEmpleadoCargo = obtieneCriterioDeIngresoEmpleadoCargo(listaEmpleadoCargo1.getRhEmpleado(), idPeriodoGestion);
                listaRhCriterioDeIngresoPojo = new ArrayList<RhCriterioDeIngresoPojo>();
                for (RhCriterioDeIngresoEmpleadoCargo listaDescuento : listaCriterioDeIngresoEmpleadoCargo) {
                    rhCriterioDeIngresoPojo = new RhCriterioDeIngresoPojo();
                    rhCriterioDeIngresoPojo.setIdCriterioDeIngreso(listaDescuento.getRhCriterioDeIngreso().getIdCriterioDeIngreso());
                    rhCriterioDeIngresoPojo.setDescripcion(listaDescuento.getRhCriterioDeIngreso().getDescripcion());
                    rhCriterioDeIngresoPojo.setMonto(listaDescuento.getMontoCriterioDeIngreso());
                    listaRhCriterioDeIngresoPojo.add(rhCriterioDeIngresoPojo);
                }
                rhEmpleadoCriterioDeIngresoPojo.setListaCriterioDeIngresoPojo(listaRhCriterioDeIngresoPojo);
                listaEmpleadoCriterioDeIngresoPojo.add(rhEmpleadoCriterioDeIngresoPojo);
            }
            return listaEmpleadoCriterioDeIngresoPojo;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RhCriterioDeIngresoEmpleadoCargo> obtieneCriterioDeIngresoEmpleadoCargo(RhEmpleado rhEmpleado, Long idPeriodoGestion) throws Exception {
        try {
            List<RhCriterioDeIngresoEmpleadoCargo> listaCriterioDeIngresoEmpleadoCargo = dao.find(""
                    + "select j "
                    + "from RhCriterioDeIngresoEmpleadoCargo j "
                    + "where j.fechaBaja is null "
                    + "and j.rhEmpleadoCargo.rhEmpleado.idEmpleado = '" + rhEmpleado.getIdEmpleado() + "' "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = '" + idPeriodoGestion + "' ");
            return listaCriterioDeIngresoEmpleadoCargo;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void modificaListaCriterioDeIngresoEmpleadoCargo(List<RhEmpleadoCriterioDeIngresoPojo> listaEmpleadoCriterioDeIngresoPojo, Long idPeriodoGestion) throws Exception {
        try {
            for (RhEmpleadoCriterioDeIngresoPojo listaEmpleadoCriterioDeIngresoPojo1 : listaEmpleadoCriterioDeIngresoPojo) {
                for (RhCriterioDeIngresoPojo rhCriterioDeIngresoPojo : listaEmpleadoCriterioDeIngresoPojo1.getListaCriterioDeIngresoPojo()) {
                    RhCriterioDeIngresoEmpleadoCargo rhCriterioDeIngresoEmpleadoCargo = obtieneCriterioDeIngresoEmpleadoCargo(listaEmpleadoCriterioDeIngresoPojo1.getIdEmpleadoCargo(), rhCriterioDeIngresoPojo.getIdCriterioDeIngreso(), idPeriodoGestion);
                    rhCriterioDeIngresoEmpleadoCargo.setMontoCriterioDeIngreso(rhCriterioDeIngresoPojo.getMonto());
                    mergeRhCriterioDeIngresoEmpleadoCargo(rhCriterioDeIngresoEmpleadoCargo);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhCriterioDeIngresoEmpleadoCargo obtieneCriterioDeIngresoEmpleadoCargo(Long idEmpleadoCargo, Long idCriterioDeIngreso, Long idPeriodoGestion) throws Exception {
        try {
            List<RhCriterioDeIngresoEmpleadoCargo> lista = dao.find(""
                    + "select j "
                    + "from RhCriterioDeIngresoEmpleadoCargo j "
                    + "where j.fechaBaja is null "
                    + "and j.rhEmpleadoCargo.idEmpleadoCargo = " + idEmpleadoCargo + " "
                    + "and j.rhCriterioDeIngreso.idCriterioDeIngreso = " + idCriterioDeIngreso + " "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return new RhCriterioDeIngresoEmpleadoCargo();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RhCriterioDeIngresoEmpleadoCargo mergeRhCriterioDeIngresoEmpleadoCargo(RhCriterioDeIngresoEmpleadoCargo rhCriterioDeIngresoEmpleadoCargo) throws Exception {
        try {
            rhCriterioDeIngresoEmpleadoCargo.setFechaAlta(new Date());
            rhCriterioDeIngresoEmpleadoCargo.setUsuarioAlta("SYS");
            rhCriterioDeIngresoEmpleadoCargo.setFechaModificacion(new Date());
            rhCriterioDeIngresoEmpleadoCargo.setUsuarioModificacion("SYS");
            dao.update(rhCriterioDeIngresoEmpleadoCargo);
            return rhCriterioDeIngresoEmpleadoCargo;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void importaValoresExcel(InputStream fileInputStream, Long idPeriodoGestion) throws Exception {
        try {
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int startingRow = 0;
            int endingRow = ImportUtils.lookForRowWithValue(sheet, "");
            int limiteCriterioDeIngresoTitulo = 6;
            int limiteCriterioDeIngresoEmpleado;
            sheet = workbook.getSheetAt(0);
            String codigoEmpleado;
            String codigoCriterioDeIngreso;
            BigDecimal monto;
            RhCriterioDeIngresoEmpleadoCargo rhCriterioDeIngresoEmpleadoCargo;
            Long idCriterioDeIngreso;
            RhEmpleadoCargo rhEmpleadoCargo = new RhEmpleadoCargo();
            RhCriterioDeIngreso rhCriterioDeIngreso;
            Map<Integer, Long> mapaCriterioDeIngreso = new HashMap<Integer, Long>();
            while (startingRow <= endingRow) {
                Row row = sheet.getRow(startingRow);
                Iterator<Cell> cellIterator = row.cellIterator();
                limiteCriterioDeIngresoEmpleado = 6;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (startingRow == 0) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                if (cell.getColumnIndex() == limiteCriterioDeIngresoTitulo) {
                                    codigoCriterioDeIngreso = cell.getStringCellValue();
                                    rhCriterioDeIngreso = rhCriterioDeIngresoService.obtieneCriterioDeIngresoPorDescripcion(codigoCriterioDeIngreso);
                                    mapaCriterioDeIngreso.put(limiteCriterioDeIngresoTitulo, rhCriterioDeIngreso.getIdCriterioDeIngreso());
                                    limiteCriterioDeIngresoTitulo++;
                                    log.info("el mapa es:::: " + mapaCriterioDeIngreso);
                                }
                                break;
                        }
                    } else {
                        log.info("STARTING ROW::: " + limiteCriterioDeIngresoEmpleado);
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                if (cell.getColumnIndex() == 0) {
                                    log.info("por Empleado");
                                    codigoEmpleado = cell.getStringCellValue();
                                    rhEmpleadoCargo = rhEmpleadoCargoService.obtieneEmpleadoCargoPorCodigo(codigoEmpleado);
                                }
                            case Cell.CELL_TYPE_NUMERIC:
                                log.info("por bonos" + cell.getColumnIndex());
                                if (cell.getColumnIndex() == limiteCriterioDeIngresoEmpleado) {
                                    monto = BigDecimal.valueOf(cell.getNumericCellValue());
                                    idCriterioDeIngreso = mapaCriterioDeIngreso.get(limiteCriterioDeIngresoEmpleado);
                                    rhCriterioDeIngresoEmpleadoCargo = encuentraCriterioDeIngresoEmpleadoCargo(rhEmpleadoCargo.getIdEmpleadoCargo(), idCriterioDeIngreso, idPeriodoGestion);
                                    rhCriterioDeIngresoEmpleadoCargo.setMontoCriterioDeIngreso(monto);
                                    mergeRhCriterioDeIngresoEmpleadoCargo(rhCriterioDeIngresoEmpleadoCargo);
                                    limiteCriterioDeIngresoEmpleado++;
                                }
                                break;
                        }
                    }
                }
                startingRow++;
            }
            log.info("mapa es:::" + mapaCriterioDeIngreso);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
        }

    }

    @Override
    public RhCriterioDeIngresoEmpleadoCargo encuentraCriterioDeIngresoEmpleadoCargo(Long idEmpleadoCargo, Long idCriterioDeIngreso, Long idPeriodoGestion) throws Exception {
        try {
            log.info("idEmpleadoCargo::::::: " + idEmpleadoCargo);
            log.info("idCriterioDeIngreso::: " + idCriterioDeIngreso);
            log.info("idPeriodoGestion::: " + idPeriodoGestion);
            List<RhCriterioDeIngresoEmpleadoCargo> lista = dao.find(""
                    + "select j "
                    + "from RhCriterioDeIngresoEmpleadoCargo j "
                    + "where j.fechaBaja is null "
                    + "and j.rhEmpleadoCargo.idEmpleadoCargo = " + idEmpleadoCargo + " "
                    + "and j.rhCriterioDeIngreso.idCriterioDeIngreso = " + idCriterioDeIngreso + " "
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
    public RhCriterioDeIngresoEmpleadoCargo encuentraBonoProduccion(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            List<RhCriterioDeIngresoEmpleadoCargo> lista = dao.find(""
                    + "select j "
                    + "from RhCriterioDeIngresoEmpleadoCargo j "
                    + "where j.fechaBaja is null "
                    + "and j.rhEmpleadoCargo.idEmpleadoCargo = " + idEmpleadoCargo + " "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + " "
                    + "and j.rhCriterioDeIngreso.codigo = 'BPRO'");
            if (!lista.isEmpty()) {
                return lista.get(0);
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public BigDecimal sumaOtrosBonos(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            BigDecimal totalOtrosBonos = new BigDecimal(BigInteger.ZERO);            
            List<RhCriterioDeIngresoEmpleadoCargo> lista = dao.find(""
                    + "select j "
                    + "from RhCriterioDeIngresoEmpleadoCargo j "
                    + "where j.fechaBaja is null "
                    + "AND j.rhCriterioDeIngreso.codigo <> 'BPRO'"
                    + "and j.rhEmpleadoCargo.idEmpleadoCargo = " + idEmpleadoCargo + " "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            if (!lista.isEmpty()) {
                for (RhCriterioDeIngresoEmpleadoCargo rhCriterioDeIngresoEmpleadoCargo : lista) {
                    totalOtrosBonos = totalOtrosBonos.add(rhCriterioDeIngresoEmpleadoCargo.getMontoCriterioDeIngreso());
                }
            }            
            return totalOtrosBonos;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void actualizaCriterioDeIngresoEmpleadoCargo(RhCriterioDeIngreso rhCriterioDeIngreso, Long idPeriodoGestion) throws Exception {
        try {
            RhPeriodoGestion rhPeriodoGestion = rhPeriodoGestionService.encuentraRhPeriodoGestionPorId(idPeriodoGestion);
            List<RhEmpleadoCargo> lista = rhEmpleadoCargoService.obtieneEmpleadoCargoVigente(idPeriodoGestion);
            RhCriterioDeIngresoEmpleadoCargo rhCriterioDeIngresoEmpleadoCargo;
            for (RhEmpleadoCargo lista1 : lista) {
                rhCriterioDeIngresoEmpleadoCargo = new RhCriterioDeIngresoEmpleadoCargo();
                rhCriterioDeIngresoEmpleadoCargo.setMontoCriterioDeIngreso(BigDecimal.ZERO);
                rhCriterioDeIngresoEmpleadoCargo.setRhCriterioDeIngreso(rhCriterioDeIngreso);
                rhCriterioDeIngresoEmpleadoCargo.setRhEmpleadoCargo(lista1);
                rhCriterioDeIngresoEmpleadoCargo.setRhPeriodoGestion(rhPeriodoGestion);
                persistRhCriterioDeIngresoEmpleadoCargo(rhCriterioDeIngresoEmpleadoCargo);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RhCriterioDeIngresoEmpleadoCargo> obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(Long idEmpleadoCargo, Long idPeriodoGestion) throws Exception {
        try {
            List<RhCriterioDeIngresoEmpleadoCargo> lista = dao.find(""
                    + "select j "
                    + "from RhCriterioDeIngresoEmpleadoCargo j "
                    + "where j.fechaBaja is null "
                    + "and j.rhEmpleadoCargo.idEmpleadoCargo = " + idEmpleadoCargo + " "
                    + "and j.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void modificaCamposEmpleadoCargoListaRhCriterioDeIngresoEmpleadoCargo(RhEmpleadoCargo rhEmpleadoCargoNuevo, Long idPeriodoGestion) throws Exception {
        try {
            List<RhCriterioDeIngresoEmpleadoCargo> listaRhCriterioDeIngresoEmpleadoCargo = obtieneRhVariacionPorIdEmpleadoCargoIdPeriodo(rhEmpleadoCargoNuevo.getIdAntecesor(), idPeriodoGestion);
            for (RhCriterioDeIngresoEmpleadoCargo criterioDeIngresoEmpleadoCargo : listaRhCriterioDeIngresoEmpleadoCargo) {
                criterioDeIngresoEmpleadoCargo.setRhEmpleadoCargo(rhEmpleadoCargoNuevo);
                mergeRhCriterioDeIngresoEmpleadoCargo(criterioDeIngresoEmpleadoCargo);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public List<RhCriterioDeIngresoEmpleadoCargo> obtieneCriterioDeIngresoEmpleadoCargoPorIdDescuentoAndIdPeriodoParaEliminar(Long idCriterioDeIngreso, Long idPeriodoGestion) throws Exception {
        try {
            List<RhCriterioDeIngresoEmpleadoCargo> lista = dao.find(""
                    + "select h "
                    + "from RhCriterioDeIngresoEmpleadoCargo h "
                    + "where h.fechaBaja is null "
                    + "and h.rhCriterioDeIngreso.idCriterioDeIngreso = " + idCriterioDeIngreso + " "
                    + "and h.rhPeriodoGestion.idPeriodoGestion = " + idPeriodoGestion + "");
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

}
