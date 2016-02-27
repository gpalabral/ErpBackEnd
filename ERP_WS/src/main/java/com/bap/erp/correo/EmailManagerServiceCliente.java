package com.bap.erp.correo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class EmailManagerServiceCliente {

    public static void main(String[] args) {
        BeanFactory factory = new XmlBeanFactory(new FileSystemResource("//media//henrry//267bd361-373b-4d96-9197-f3fbffb09c25//HENRRY//BAP//SERVIDOR ANGULAR//ERP//ERP_WS//src//main//webapp//WEB-INF//applicationContext.xml"));

        EmailManagerService emailManagerService = (EmailManagerService) factory.getBean("emailManagerService");

//        EmailManagerService tramiteService = (EmailManagerService) context.getBean("tramiteService");
        System.out.println("=====ENVIANDO CORREOS======");
    }
}
