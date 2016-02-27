package com.bap.erp.modelo.pojo;

import com.bap.erp.modelo.ban.DocumentoPago;
import java.util.List;

public class DocumentoPagoFacturasPojo {

    private DocumentoPago documentoPago;
    private List<CpcPagoPojo> listaPagoPojo;

    public DocumentoPago getDocumentoPago() {
        return documentoPago;
    }

    public void setDocumentoPago(DocumentoPago documentoPago) {
        this.documentoPago = documentoPago;
    }

    public List<CpcPagoPojo> getListaPagoPojo() {
        return listaPagoPojo;
    }

    public void setListaPagoPojo(List<CpcPagoPojo> listaPagoPojo) {
        this.listaPagoPojo = listaPagoPojo;
    }
        
}
