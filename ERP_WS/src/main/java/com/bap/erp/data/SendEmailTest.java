package com.bap.erp.data;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;


@Component("sendEmailTest")
public class SendEmailTest {
    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;
 
    public JavaMailSender getMailSender() {
        return mailSender;
    }
 
    @Autowired
    @Required
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
 
    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }
 
    @Autowired
    @Required
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
 
    public static void main(String[] args) {
        // Inicializamos el contexto de Spring
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "applicationContext.xml" });
        SendEmailTest app = (SendEmailTest) context.getBean("sendEmailTest");
        // LLamamos al método que prueba el envío del correo
        app.testEmail();
    }
 
    public void testEmail() {
        final User user = new User();
        user.setEmailAddress("loquesea@gmail.com");
        user.setUsername("loquesea");
 
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                message.setTo(user.getEmailAddress());
                message.setFrom("loquesea@gmail.com"); 
                message.setSubject("Prueba velocity");
                message.addAttachment("perfil.png", new ClassPathResource("perfil.png"));
                
                Map model = new HashMap();
                model.put("user", user);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "email-template.vm", model);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }
} 