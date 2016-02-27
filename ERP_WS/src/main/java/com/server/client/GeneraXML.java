/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server.client;

import java.util.Date;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author henrry
 */
public class GeneraXML {

    public static void main(String args[]) {
        GeneraXML generaXML = new GeneraXML();
        generaXML.generaXML();

    }

    public String generaXML() {

        Document docNuevo = new Document();

        Element nodoIdentificacionSolicitante = new Element("DatosFormulario");
        

        Element empresaRaiz = new Element("Empresa");

        Element etiquetaIdEmpresa = new Element("idEmpresa");
        etiquetaIdEmpresa.setText("1");
        empresaRaiz.addContent(etiquetaIdEmpresa);

        Element etiquetaNombreRepresentante = new Element("nombreRepresentante");
        etiquetaNombreRepresentante.setText("Jorge Flores");
        empresaRaiz.addContent(etiquetaNombreRepresentante);

        Element etiquetaNumeroDocumentoEmpresa = new Element("NumeroDocumento");
        etiquetaNumeroDocumentoEmpresa.setText("1234567");
        empresaRaiz.addContent(etiquetaNumeroDocumentoEmpresa);

        Element etiquetaLugarExpedicionEmpresa = new Element("lugarConstitucion");
        etiquetaLugarExpedicionEmpresa.setText("LP");
        empresaRaiz.addContent(etiquetaLugarExpedicionEmpresa);

        Element etiquetaFechaCreacion = new Element("fechaCreacion");
        etiquetaFechaCreacion.setText(new Date().toString());
        empresaRaiz.addContent(etiquetaFechaCreacion);

        Element personaRaiz = new Element("Persona");

        Element etiquetaIdPersona = new Element("idPersona");
        etiquetaIdPersona.setText("1");
        personaRaiz.addContent(etiquetaIdPersona);

        Element etiquetaNombres = new Element("nombres");
        etiquetaNombres.setText("Henrry Renan");
        personaRaiz.addContent(etiquetaNombres);

        Element etiquetaApellidoPaterno = new Element("apellidoPaterno");
        etiquetaApellidoPaterno.setText("Guzman");
        personaRaiz.addContent(etiquetaApellidoPaterno);

        Element etiquetaApellidoMaterno = new Element("apellidoMaterno");
        etiquetaApellidoMaterno.setText("Saramani");
        personaRaiz.addContent(etiquetaApellidoMaterno);

        Element etiquetaNumeroDocumento = new Element("numeroDocumento");
        etiquetaNumeroDocumento.setText("4898542");
        personaRaiz.addContent(etiquetaNumeroDocumento);

        Element etiquetaTipoDocumento = new Element("tipoDocumento");
        etiquetaTipoDocumento.setText("CI");
        personaRaiz.addContent(etiquetaTipoDocumento);

        Element etiquetaLugarExpedicionPersona = new Element("lugarExpedicion");
        etiquetaLugarExpedicionPersona.setText("LP");
        personaRaiz.addContent(etiquetaLugarExpedicionPersona);

        Element etiquetaGenero = new Element("genero");
        etiquetaGenero.setText("M");
        personaRaiz.addContent(etiquetaGenero);

//        Element datoscontactoRaiz = new Element("Datoscontacto");
//        for (DatosContacto datoscontactoObjeto : listaDatosDeContacto) {
//            Element etiquetaContacto = new Element("contacto");
//
//            Element etiquetaId = new Element("idContacto");
//            etiquetaId.setText(datoscontactoObjeto.getIdContacto() != null ? datoscontactoObjeto.getIdContacto().toString() : "*");
//            etiquetaContacto.addContent(etiquetaId);
//
//            Element etiquetaNotificar = new Element("notificar");
//            etiquetaNotificar.setText(datoscontactoObjeto.getNotificar() ? "1" : "0");
//            etiquetaContacto.addContent(etiquetaNotificar);
//
//            Element etiquetaTipoContacto = new Element("tipoContacto");
//            etiquetaTipoContacto.setText(datoscontactoObjeto.getTipoContacto().getCodigo());
//            etiquetaContacto.addContent(etiquetaTipoContacto);
//
//            Element etiquetaDato = new Element("dato");
//            etiquetaDato.setText(datoscontactoObjeto.getDato());
//            etiquetaContacto.addContent(etiquetaDato);
//
//            datoscontactoRaiz.addContent(etiquetaContacto);
//        }
        nodoIdentificacionSolicitante.addContent(empresaRaiz);
        nodoIdentificacionSolicitante.addContent(personaRaiz);
//        nodoIdentificacionSolicitante.addContent(datoscontactoRaiz);
        docNuevo.addContent(nodoIdentificacionSolicitante);
        
        Format format = Format.getPrettyFormat();
        XMLOutputter xmloutputter = new XMLOutputter(format);
        String docStr = xmloutputter.outputString(docNuevo);

//        this.salidaXML = docStr;
        System.out.println("XML Nuevo");
        System.out.println(docStr);

        return "";
    }

}
