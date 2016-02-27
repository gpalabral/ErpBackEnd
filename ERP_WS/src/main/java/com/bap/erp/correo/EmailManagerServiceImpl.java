package com.bap.erp.correo;

import com.bap.erp.enums.EnumTipoProveedorCliente;
import com.bap.erp.modelo.ErpFactura;
import com.bap.erp.servicios.ErpFacturaService;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;
import javax.mail.internet.MimeMessage;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Service
public class EmailManagerServiceImpl implements EmailManagerService {

    private static Logger log = Logger.getLogger(EmailManagerServiceImpl.class);

    @Autowired
    private JavaMailSenderImpl mailSender;
    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private ErpFacturaService erpFacturaService;

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    @Override
    public void enviarCorreoPrueba(Long idFacturaEmitida) {
        try {
            
            
            ErpFactura facturaEmitida = erpFacturaService.getCpcFacturaEmitidaById(idFacturaEmitida);
            log.info("facturaEmitida::: "+facturaEmitida.getIdFactura());
            
            String xmlString = erpFacturaService.getGeneraXML(facturaEmitida.getIdFactura());
            
            InputStream adjunto = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
            
            
            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("userNameParam", userNameParam);
            String userNameParam = "";
            if (facturaEmitida.getCppProveedorCliente().getParTipoProveedorCliente().getCodigo().equals(EnumTipoProveedorCliente.JURIDICO.getCodigo())) {
                model.put("userNameParam", facturaEmitida.getCppProveedorCliente().getRazonSocial());
            }else{
                model.put("userNameParam", facturaEmitida.getCppProveedorCliente().getNombre());
            }
            model.put("variable", "Es otro valor");
            model.put("emailAddress", facturaEmitida.getCppProveedorCliente().getCorreoElectronico());
            model.put("template", "mail_template_html.vm");
            String to=facturaEmitida.getCppProveedorCliente().getCorreoElectronico();
            log.info("to::: "+to);
            
            String subject="Factura Electronica";
            SimpleAppMsgPreparator simpleAppMsgPreparator = new SimpleAppMsgPreparator(to, subject, model, new ByteArrayResource(IOUtils.toByteArray(adjunto)));
            this.mailSender.send(simpleAppMsgPreparator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class SimpleAppMsgPreparator implements MimeMessagePreparator {

        private String template;
        private String to;
        private String subject;
        private Map<String, Object> model;
        private boolean useHtml;
        private String body;
        private InputStreamSource attachment;

        public SimpleAppMsgPreparator(String to, String subject, Map<String, Object> model) {
            this.template = (String) model.get("template");
            this.subject = subject;
            this.model = model;
            this.to = to;
            this.useHtml = true;
        }

        public SimpleAppMsgPreparator(String to, String subject, Map<String, Object> model, InputStreamSource adjunto) {
            this.template = (String) model.get("template");
            this.subject = subject;
            this.model = model;
            this.to = to;
            this.useHtml = true;
            this.attachment = adjunto;
        }

        public SimpleAppMsgPreparator(String to, String subject, String body) {
            this.subject = subject;
            this.to = to;
            this.useHtml = true;
            this.body = body;
        }

        public void prepare(MimeMessage message) throws Exception {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, useHtml);
            messageHelper.addAttachment("facturaElectronica.xml", attachment,"application/xml");
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, model);
            this.body = text;
            messageHelper.setText(text, useHtml);
            messageHelper.setText(this.body, useHtml);
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

}
