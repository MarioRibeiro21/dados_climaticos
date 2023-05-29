/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dadoclimatico.dadoclimatico.model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Mario
 */
public class AdapterExportXml implements IAdapterExport {

    @Override
    public void escrever(List<DadoClima> dadosClima) throws IOException {
        String valor = "Hello";
        String arquivo = "dados_climaticos.xml";

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        DocumentBuilder dc = null;
        try {
            dc = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AdapterExportXml.class.getName()).log(Level.SEVERE, null, ex);
        }

        
         Document d = dc.newDocument();

        //elemento raiz do XML
        Element raiz = d.createElement("Posts");
        d.appendChild(raiz);

        for (DadoClima dado : dadosClima) {
           
        //elemento post
        Element post = d.createElement("post");
        raiz.appendChild(post);

        //definindo o atributo do post
        Attr attr = d.createAttribute("id");
        long currentTimeStamp = System.currentTimeMillis();
        attr.setValue("_" + currentTimeStamp);
        post.setAttributeNode(attr);
        
            //definindo valor da postagem na tag text
            Element temperatura = d.createElement("Temperatura");
            temperatura.appendChild(d.createTextNode(Float.toString(dado.getTemperatura())));
            post.appendChild(temperatura);
            Element umidade = d.createElement("Umidade");
            umidade.appendChild(d.createTextNode(Float.toString(dado.getUmidade())));
            post.appendChild(umidade);
            Element pressao = d.createElement("Pressao");
            pressao.appendChild(d.createTextNode(Float.toString(dado.getPressao())));
            post.appendChild(pressao);

            //construção do XML
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = null;
            try {
                t = tf.newTransformer();
            } catch (TransformerConfigurationException ex) {
                Logger.getLogger(AdapterExportXml.class.getName()).log(Level.SEVERE, null, ex);
            }
            DOMSource domSource = new DOMSource(d);
            StreamResult streamResult = new StreamResult(new File(arquivo));

            try {
                //juntar o conteudo ao arquivo criado
                t.transform(domSource, streamResult);
            } catch (TransformerException ex) {
                Logger.getLogger(AdapterExportXml.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
