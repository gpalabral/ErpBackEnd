package com.bap.erp.modelo.cpc;

import com.bap.erp.modelo.ErpDetalleFactura;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class CpcDetalleFacturaSerializer extends JsonSerializer<ErpDetalleFactura>{
    
    @Override
    public void serialize(ErpDetalleFactura value, JsonGenerator jgen, SerializerProvider provider) 
      throws IOException, JsonProcessingException {
        System.out.println(":::::::::::::::::serialize::::::::::::::::::::");
        jgen.writeStartObject();
        jgen.writeNumberField("Descuento Primera Moneda", value.getDescuentoPrimeraMoneda());
        jgen.writeEndObject();
    }
    
}
