package com.bap.erp.servicios.impl;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.enums.EnumEstado;
import com.bap.erp.modelo.ErpAplicante;
import com.bap.erp.modelo.pojo.ErpAplicantePojo;
import com.bap.erp.servicios.ErpAplicanteService;
import com.bap.erp.ws.ImportWS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErpAplicanteServiceImpl implements ErpAplicanteService {

    IGenericDao<ErpAplicante> dao;    

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ImportWS.class);
    
    
    @Autowired
    public void setDao(IGenericDao<ErpAplicante> daoToSet) {
        dao = daoToSet;
        dao.setClazz(ErpAplicante.class);
    }

    public List<ErpAplicantePojo> listErpAplicanteByIdDepartamento(Long idDepartamento) throws Exception {
        try {
            List<ErpAplicantePojo> listaErpAplicantePojo = new ArrayList<ErpAplicantePojo>();
            List<ErpAplicante> lista = dao.find(""
                    + "select j "
                    + "from ErpAplicante j "
                    + "where j.fechaBaja is null "
                    + "and j.parEstado.codigo = '"+EnumEstado.VIGENTE.getCodigo()+"' "
                    + "and j.erpDepartamento.idDepartamento = "+idDepartamento+" ");
            if(!lista.isEmpty()){
                ErpAplicantePojo erpAplicantePojo;
                for (ErpAplicante lista1 : lista) {
                    erpAplicantePojo = new ErpAplicantePojo();
                    erpAplicantePojo.setIdAplicante(lista1.getIdAplicante());
                    erpAplicantePojo.setNombreCompleto(lista1.getPrimerApellido()+ " "+lista1.getSegundoApellido()+" "+lista1.getNombre());
                    listaErpAplicantePojo.add(erpAplicantePojo);
                }
                return listaErpAplicantePojo;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }   
    
}
