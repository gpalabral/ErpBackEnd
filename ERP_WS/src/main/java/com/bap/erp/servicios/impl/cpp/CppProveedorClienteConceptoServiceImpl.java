package com.bap.erp.servicios.impl.cpp;

import com.bap.erp.commons.entities.AbstractJpaDAO;
import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.enums.EnumTipoProveedorCliente;
import com.bap.erp.modelo.cpp.CppConcepto;
import com.bap.erp.modelo.cpp.CppProveedorCliente;
import com.bap.erp.modelo.cpp.CppProveedorClienteConcepto;
import com.bap.erp.modelo.pojo.ProveedorGrupoConcepto;
import com.bap.erp.servicios.cpp.CppConceptoService;
import com.bap.erp.servicios.cpp.CppProveedorClienteConceptoService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CppProveedorClienteConceptoServiceImpl extends AbstractJpaDAO<CppProveedorClienteConcepto> implements CppProveedorClienteConceptoService {

    IGenericDao<CppProveedorClienteConcepto> dao;

    @Autowired
    public CppConceptoService cppConceptoService;

    @Autowired
    public void setDao(IGenericDao<CppProveedorClienteConcepto> daoToSet) {
        dao = daoToSet;
        dao.setClazz(CppProveedorClienteConcepto.class);
    }

    public CppProveedorClienteConcepto persistCppProveedorClienteConcepto(CppProveedorClienteConcepto cppProveedorClienteConcepto) throws Exception {
        try {
            cppProveedorClienteConcepto.setIdProveedorClienteConcepto(null);
            cppProveedorClienteConcepto.setUsuarioAlta("TEST");
            cppProveedorClienteConcepto.setFechaAlta(new Date());
            dao.create(cppProveedorClienteConcepto);
            return cppProveedorClienteConcepto;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CppProveedorClienteConcepto> getCppProveedorClienteConcepto() {
        return dao.findAll();
    }

    public List<CppProveedorClienteConcepto> listaCppProveedorClienteConceptoPorIdCppProveedorCliente(Long idCppProveedorCliente) {
        List<CppProveedorClienteConcepto> lista = find(""
                + "select j "
                + "from CppProveedorClienteConcepto j "
                + "where j.fechaBaja is null "
                + "and j.cppProveedorCliente.idProveedorCliente = " + idCppProveedorCliente + " "
                + "order by j.cppConcepto.cppGrupo.idGrupo");
        if (!lista.isEmpty()) {
            return lista;
        }
        return Collections.EMPTY_LIST;
    }

    public List<CppProveedorClienteConcepto> listaCppProveedorClienteConceptoPorIdConcepto(Long idConcepto) {
        List<CppProveedorClienteConcepto> list = find(""
                + "select j "
                + "from CppProveedorClienteConcepto j "
                + "where j.fechaBaja is null "
                + "and j.cppConcepto.idConcepto = " + idConcepto + " "
                + "order by j.fechaAlta asc");
        if (!list.isEmpty()) {
            return list;
        }
        return Collections.EMPTY_LIST;
    }

    public List<ProveedorGrupoConcepto> getProveedorGrupoConcepto() {
        List<ProveedorGrupoConcepto> listaProveedorGrupoConcepto = new ArrayList<ProveedorGrupoConcepto>();
        List<CppProveedorClienteConcepto> listaProveedor = getCppProveedorClienteConceptoOrdenados();
        ProveedorGrupoConcepto proveedorGrupoConcepto;
        for (CppProveedorClienteConcepto cppProveedorClienteConcepto : listaProveedor) {
            proveedorGrupoConcepto = new ProveedorGrupoConcepto();
            proveedorGrupoConcepto.getProveedorCliente().setIdEntidadPojo(cppProveedorClienteConcepto.getCppProveedorCliente().getIdProveedorCliente());
            if (cppProveedorClienteConcepto.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.NATURAL.getCodigo())) {
                proveedorGrupoConcepto.getProveedorCliente().setDescripcion(cppProveedorClienteConcepto.getCppProveedorCliente().getNombre() + " " + cppProveedorClienteConcepto.getCppProveedorCliente().getPrimerApellido() + " " + cppProveedorClienteConcepto.getCppProveedorCliente().getSegundoApellido());
            } else {
                proveedorGrupoConcepto.getProveedorCliente().setDescripcion(cppProveedorClienteConcepto.getCppProveedorCliente().getRazonSocial());
            }
            proveedorGrupoConcepto.getProveedorCliente().setTipo("PROV");

            proveedorGrupoConcepto.getGrupo().setIdEntidadPojo(cppProveedorClienteConcepto.getCppConcepto().getCppGrupo().getIdGrupo());
            proveedorGrupoConcepto.getGrupo().setDescripcion(cppProveedorClienteConcepto.getCppConcepto().getCppGrupo().getNombre());
            proveedorGrupoConcepto.getGrupo().setMascara(cppProveedorClienteConcepto.getCppConcepto().getCppGrupo().getParRecurrencia().getDescripcion());
            proveedorGrupoConcepto.getGrupo().setTipo("GRU");

            proveedorGrupoConcepto.getConcepto().setIdEntidadPojo(cppProveedorClienteConcepto.getCppConcepto().getIdConcepto());
            proveedorGrupoConcepto.getConcepto().setDescripcion(cppProveedorClienteConcepto.getCppConcepto().getDescripcion());
            proveedorGrupoConcepto.getConcepto().setTipo("CON");
            listaProveedorGrupoConcepto.add(proveedorGrupoConcepto);
        }
        return listaProveedorGrupoConcepto;
    }

    public List<CppProveedorClienteConcepto> getCppProveedorClienteConceptoOrdenados() {
        List<CppProveedorClienteConcepto> lista = find(""
                + "select j "
                + "from CppProveedorClienteConcepto j "
                + "where j.fechaBaja is null "
                + "order by j.cppProveedorCliente.nombre asc");

        if (!lista.isEmpty()) {
            return lista;
        }
        return Collections.EMPTY_LIST;
    }

    public CppProveedorClienteConcepto mergeProveedorClienteConcepto(CppProveedorClienteConcepto cppProveedorClienteConcepto) throws Exception {
        try {
            cppProveedorClienteConcepto.setFechaModificacion(new Date());
            cppProveedorClienteConcepto.setUsuarioModificacion("TEST");
            dao.update(cppProveedorClienteConcepto);
            return cppProveedorClienteConcepto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void removeConceptosNoAsignados(List<CppConcepto> listaConceptosAsignados, CppProveedorCliente cppProveedorCliente) throws Exception {
        try {
            List<CppConcepto> listaConceptosBD = cppConceptoService.getListaCppConceptoByProveedor(cppProveedorCliente);
            CppProveedorClienteConcepto cppProveedorClienteConcepto;
            for (CppConcepto cppConcepto : listaConceptosAsignados) {                
                
                if (!listaConceptosBD.contains(cppConcepto)) {  
                    
                    cppProveedorClienteConcepto = new CppProveedorClienteConcepto();
                    cppProveedorClienteConcepto.setCppConcepto(cppConcepto);
                    cppProveedorClienteConcepto.setCppProveedorCliente(cppProveedorCliente);
                    persistCppProveedorClienteConcepto(cppProveedorClienteConcepto);
                    
                }
                
            }
            for (CppConcepto cppConceptoBD : listaConceptosBD) {       
                
                if (!listaConceptosAsignados.contains(cppConceptoBD)) {                                        
                    removeProveedorClienteConcepto(getRegisterByIdCppConceptoAndIdCppProveedor(cppConceptoBD.getIdConcepto(), cppProveedorCliente.getIdProveedorCliente()).getIdProveedorClienteConcepto());
                }
                
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public void removeProveedorClienteConcepto(Long idProveedorClienteConcepto) throws Exception {
        try {
            CppProveedorClienteConcepto cppProveedorClienteConcepto = getCppProveedorClienteConcepto(idProveedorClienteConcepto);
            cppProveedorClienteConcepto.setFechaBaja(new Date());
            cppProveedorClienteConcepto.setUsuarioBaja("TEST");
            dao.update(cppProveedorClienteConcepto);
        } catch (Exception e) {
            throw e;
        }
    }

    public CppProveedorClienteConcepto getCppProveedorClienteConcepto(Long idProveedorClienteConcepto) throws Exception {
        try {
            CppProveedorClienteConcepto cppProveedorClienteConcepto = dao.findOne(idProveedorClienteConcepto);
            return cppProveedorClienteConcepto;
        } catch (Exception e) {
            throw e;
        }
    }

    public CppProveedorClienteConcepto getRegisterByIdCppConceptoAndIdCppProveedor(Long idConcepto, Long idProveedorCliente) throws Exception {
        try {
            List<CppProveedorClienteConcepto> list = find(""
                    + "select j "
                    + "from CppProveedorClienteConcepto j "
                    + "where j.fechaBaja is null "
                    + "and j.cppConcepto.idConcepto = " + idConcepto + " "
                    + "and j.cppProveedorCliente.idProveedorCliente = " + idProveedorCliente + "");
            if (!list.isEmpty()) {
                return list.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return new CppProveedorClienteConcepto();
    }

    public void removeProveedoresNoAsignados(List<CppProveedorCliente> listaProveedorClientes, CppConcepto cppConcepto) throws Exception {
        try {
            
            
//            List<CppProveedorCliente> listaProveedoreClienteBD = cppConceptoService.getListaCppConceptoByProveedor(cppProveedorCliente);
//            CppProveedorClienteConcepto cppProveedorClienteConcepto;
//            for (CppConcepto cppConcepto : listaConceptosAsignados) {                
//                if (!listaConceptosBD.contains(cppConcepto)) {                    
//                    cppProveedorClienteConcepto = new CppProveedorClienteConcepto();
//                    cppProveedorClienteConcepto.setCppConcepto(cppConcepto);
//                    cppProveedorClienteConcepto.setCppProveedorCliente(cppProveedorCliente);
//                    persistCppProveedorClienteConcepto(cppProveedorClienteConcepto);
//                }
//            }
//            for (CppConcepto cppConceptoBD : listaConceptosBD) {                
//                if (!listaConceptosAsignados.contains(cppConceptoBD)) {                                        
//                    removeProveedorClienteConcepto(getRegisterByIdCppConceptoAndIdCppProveedor(cppConceptoBD.getIdConcepto(), cppProveedorCliente.getIdProveedorCliente()).getIdProveedorClienteConcepto());
//                }
//            }

        } catch (Exception e) {
            throw e;
        }
    }

}
