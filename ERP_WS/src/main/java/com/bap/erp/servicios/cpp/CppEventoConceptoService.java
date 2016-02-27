package com.bap.erp.servicios.cpp;

import com.bap.erp.modelo.cpp.CppEventoConcepto;
import java.util.List;

public interface CppEventoConceptoService {
    
    CppEventoConcepto persistCppEventoConcepto(CppEventoConcepto cppGrupo);//CREATE

    CppEventoConcepto mergeCppEventoConcepto(CppEventoConcepto cppGrupo);//UPDATE
    
    void removeCppEventoConcepto(String idUsuario);//DELETE
    
    List<CppEventoConcepto> getCppEventoConcepto(); //RETRIEVE
    
}
