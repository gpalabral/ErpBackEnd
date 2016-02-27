package com.bap.erp.servicios.impl.cpp;

import com.bap.erp.commons.dao.IGenericDao;
import com.bap.erp.modelo.cpp.CppEventoConcepto;
import com.bap.erp.servicios.cpp.CppEventoConceptoService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CppEventoConceptoServiceImpl implements CppEventoConceptoService {
    
    IGenericDao<CppEventoConcepto> dao;
    
   @Autowired
   public void setDao( IGenericDao<CppEventoConcepto> daoToSet ){
      dao = daoToSet;
      dao.setClazz(CppEventoConcepto.class );
   }

    public CppEventoConcepto persistCppEventoConcepto(CppEventoConcepto cppEventoConcepto) {
        
        cppEventoConcepto.setIdEventoConcepto(null);
        cppEventoConcepto.setFechaAlta(new Date());
        cppEventoConcepto.setUsuarioAlta("TEST");
        
        dao.create(cppEventoConcepto);
        return cppEventoConcepto;
    }

    public CppEventoConcepto mergeCppEventoConcepto(CppEventoConcepto cppEventoConcepto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeCppEventoConcepto(String idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<CppEventoConcepto> getCppEventoConcepto() {
        return dao.findAll();
    }

    
    
}
