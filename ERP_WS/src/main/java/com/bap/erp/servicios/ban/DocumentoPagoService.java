package com.bap.erp.servicios.ban;

import com.bap.erp.modelo.ban.DocumentoPago;
import com.bap.erp.modelo.pojo.DocumentoPagoFacturasPojo;
import java.math.BigDecimal;
import java.util.List;

public interface DocumentoPagoService {

    DocumentoPago persistDocumentoPago(DocumentoPago documentoPago) throws Exception;

    DocumentoPago mergeDocumentoPago(DocumentoPago documentoPago) throws Exception;

    DocumentoPago getDocumentoPagoById(Long idDocumentoPago) throws Exception;

    List<DocumentoPago> getDocumentoPago() throws Exception;

    List<DocumentoPago> getListDocumentoPagoByIdFacturaEmitida(Long idFacturaEmitida) throws Exception;
    
    Long generaNumeroPago(Long idFaturaEmitida)throws Exception;
    
    BigDecimal generaMontoAcumulado(Long idFacturaEmitida) throws Exception;
    
    DocumentoPago guardaDocumentoPagoFacturasPojo (DocumentoPagoFacturasPojo documentoPagoFacturasPojo)throws Exception;
    
    DocumentoPago guardaDocumentoPagoFacturasRetencionesPojo (DocumentoPagoFacturasPojo documentoPagoFacturasPojo)throws Exception;
    
    List<Integer> getGestionesFacturadas(String modulo) throws Exception;
        
}
