/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server.client;


import java.util.Date;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author henrry
 */
public class PruebaXML {

    public static void main(String args[]) {
 
        Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        Namespace xsd = Namespace.getNamespace("xsd", "http://www.w3.org/2001/XMLSchema");
        Namespace soap = Namespace.getNamespace("soap", "http://schemas.xmlsoap.org/soap/envelope");
        
        // create the jdom
        Document jdomDoc2 = new Document();
        // create root element
        Element rootElement2 = new Element("Envelope", soap);
        rootElement2.addNamespaceDeclaration(xsi);
        rootElement2.addNamespaceDeclaration(xsd);

        jdomDoc2.setRootElement(rootElement2);

        Element body2 = new Element("Body", soap);
        body2.addNamespaceDeclaration(xsi);
        body2.addNamespaceDeclaration(xsd);


        // add child 2
        Element facturarResponse2 = new Element("facturarResponse", "urn:impuestos-gob-bo:newton:facturacionelectronicaservice:data:v1");
        
        Element eTicket = new Element("eTicket");
        eTicket.setText("String");
        facturarResponse2.addContent(eTicket);

        Element errores2 = new Element("errores");

        Element fallo2 = new Element("fallo");

        Element codigo2 = new Element("codigo");
        codigo2.setText("unsignedInt");
        fallo2.addContent(codigo2);

        Element descripcion2 = new Element("descripcion");
        descripcion2.setText("String");
        fallo2.addContent(descripcion2);

        errores2.addContent(fallo2);

        facturarResponse2.addContent(errores2);

        body2.addContent(facturarResponse2);
        // fin child 2

        // add attribute
        rootElement2.addContent(body2);

            // Output as XML
        // create XMLOutputter
        XMLOutputter xml2 = new XMLOutputter();
            // we want to format the xml. This is used only for demonstration.
        // pretty formatting adds extra spaces and is generally not required.
        xml2.setFormat(Format.getPrettyFormat());
        System.out.println(xml2.outputString(jdomDoc2));

    }

}
