package com.bap.erp.ws.clients;

import com.bap.erp.commons.entities.mappings.CalculosTributarios;
import com.bap.erp.commons.entities.mappings.Comprobante;
import com.bap.erp.commons.utils.JsonUtils;
import static com.bap.erp.commons.utils.JsonUtils.jsonToObject;
import com.bap.erp.modelo.pojo.CntCuentaPlanCuentasPojo;
import com.bap.erp.modelo.pojo.CntTipoCambioPojo;
import com.bap.erp.servicios.cpc.CpcDosificacionService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import net.sf.jasperreports.web.util.WebResource;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ErpContabilidadClient {

    @Resource(name = "wsReferences")
    private Properties wsReferences;

    private String erpContabilidadServer;

    @Autowired
    private CpcDosificacionService cpcDosificacionesService;

    @PostConstruct
    public void init() {
        erpContabilidadServer = wsReferences.getProperty("ws.erp.contabilidad");
    }

    /**
     * Este metodo se conecta al WS de Contabilidad para obtener el tipo de
     * cambio de una fecha en especifico.
     *
     * @param fecha DD/MM/YYYY
     *
     */
    public CntTipoCambioPojo getTipoCambio(String fecha) {

        Client client = ClientBuilder.newClient().register(JacksonFeature.class);
        String wsServer = erpContabilidadServer + "/erpws/contabilidad/tipoCambio?fecha=" + fecha;
//        WebResource webResource = client.resource(wsServer);
//        ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
//        if (response.getStatus() != 200) {
//            throw new RuntimeException("Failed : HTTP error code: " + response.getStatus());
//        }
//        String stringResponse = response.getEntity(String.class);
        CntTipoCambioPojo cntTipoCambioPojo = null;
//        try {
//            cntTipoCambioPojo = (CntTipoCambioPojo) jsonToObject(stringResponse, CntTipoCambioPojo.class);
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return cntTipoCambioPojo;
    }

    public CntCuentaPlanCuentasPojo getCntCuentaPlanCuentasPojoByIdEntidad(String idEntidad) {        
//        Client client = Client.create();
//        String wsServer = erpContabilidadServer + "/erpws/contabilidad/getById/" + idEntidad + "";
//        WebResource webResource = client.resource(wsServer);
//        ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
//        if (response.getStatus() != 200) {
//            throw new RuntimeException("Failed : HTTP error code: " + response.getStatus());
//        }
//        String stringResponse = response.getEntity(String.class);
//
        CntCuentaPlanCuentasPojo cntCuentaPlanCuentasPojo = null;
//        CntTipoCambioPojo cntTipoCambioPojo = null;
//        try {
//
//            cntCuentaPlanCuentasPojo = (CntCuentaPlanCuentasPojo) jsonToObject(stringResponse, CntCuentaPlanCuentasPojo.class);
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return cntCuentaPlanCuentasPojo;
    }

    public CalculosTributarios getCalculosTributarios(CalculosTributarios calculosTributarios) {
//        Client client = Client.create();
//        String wsServer = erpContabilidadServer + "/erpws/contabilidad/getComprobante";
//        WebResource webResource = client.resource(wsServer);
//        try {
//            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, JsonUtils.objectToJson(calculosTributarios));
//            if (response.getStatus() != 200) {
//                throw new RuntimeException("Failed : HTTP error code: " + response.getStatus());
//            }
//            String stringResponse = response.getEntity(String.class);
//            calculosTributarios = (CalculosTributarios) JsonUtils.jsonToObject(stringResponse, CalculosTributarios.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return calculosTributarios;
    }

    public Comprobante getComprobante(Comprobante comprobante) {
//        Client client = Client.create();
//        String wsServer = erpContabilidadServer + "/erpws/contabilidad/saveComprobante";
//        WebResource webResource = client.resource(wsServer);
//        try {
//            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, JsonUtils.objectToJson(comprobante));
//            if (response.getStatus() != 200) {
//                throw new RuntimeException("Failed : HTTP error code: " + response.getStatus());
//            }
//            String stringResponse = response.getEntity(String.class);
//            comprobante = (Comprobante) JsonUtils.jsonToObject(stringResponse, Comprobante.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return comprobante;
    }

    public Long getGeneraNumeroFactura(Long idDosificacion) {
//        Client client = Client.create();
//        String wsServer = erpContabilidadServer + "/erpws/contabilidad/getNumeroFactura/" + idDosificacion + "";
//        WebResource webResource = client.resource(wsServer);
        Long numeroFactura = 0L;
//        try {
//            ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
//            if (response.getStatus() != 200) {
//                throw new RuntimeException("Failed : HTTP error code: " + response.getStatus());
//            }
//            String stringResponse = response.getEntity(String.class);
//            numeroFactura = (Long) jsonToObject(stringResponse, Long.class);
//            if (numeroFactura == 0) {
//                try {
//                    numeroFactura = cpcDosificacionesService.getCpcDosificacionById(idDosificacion).getNumeroFacturaInicial();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return numeroFactura;
    }

}
