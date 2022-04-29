/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio1;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author usuario
 */
public class Principal
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        File fichero = new File("futbolistas.dat");
        List<Futbolista> futbolistas = leerFichero(fichero);
        DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder builder = dBFactory.newDocumentBuilder();
            Document doc = builder.newDocument();
            doc.setXmlVersion("1.0");
            
            //Nodo Raiz
            Element elementFutbolistas=doc.createElement("futbolistas");
            doc.appendChild(elementFutbolistas);
            
            for(Futbolista futbolista: futbolistas){
                aniadirFutbolistas(doc, futbolista, elementFutbolistas);
            }
            
            transformarDoc(doc);
            
            leerXML();

        } catch (ParserConfigurationException e)
        {
            System.out.println(e.toString());
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } catch (TransformerConfigurationException ex)
        {
           System.out.println(ex.toString());
        } catch (TransformerException ex)
        {
            System.out.println(ex.toString());
        }
    }

    private static void leerXML() throws IOException
    {
        //Comprobación
        Files.readAllLines(Paths.get("futbolistas.xml"))
                .stream()
                .forEach(System.out::println);
    }

    private static void transformarDoc(Document doc) throws IllegalArgumentException, TransformerConfigurationException, IOException, TransformerException, TransformerFactoryConfigurationError
    {
        //Transformación
        Source source = new DOMSource(doc);
        Result result = new StreamResult(Files.newBufferedWriter(Paths.get("futbolistas.xml")));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }

    private static void aniadirFutbolistas(Document doc, Futbolista futbolista, Element elementFutbolistas) throws DOMException
    {
        //Elemento futbolista
        Element elementFutbolista = doc.createElement("futbolista");
        //Atributo
        elementFutbolista.setAttribute("equipo", futbolista.getEquipo());
        //Elementos de futbolista
        Element elementNum = doc.createElement("num");
        elementNum.appendChild(doc.createTextNode(String.valueOf(futbolista.getNumero())));
        elementFutbolista.appendChild(elementNum);
        
        Element elementAlias = doc.createElement("alias");
        elementAlias.appendChild(doc.createTextNode(futbolista.getAlias()));
        elementFutbolista.appendChild(elementAlias);
        
        Element elementPosicion = doc.createElement("posicion");
        elementPosicion.appendChild(doc.createTextNode(String.valueOf(futbolista.getPosicion())));
        elementFutbolista.appendChild(elementPosicion);
        
        Element elementAltura = doc.createElement("altura");
        elementAltura.appendChild(doc.createTextNode(String.valueOf(futbolista.getAltura())));
        elementFutbolista.appendChild(elementAltura);
        
        //Añadir futbolista al nodo raiz -> futbolistas
        elementFutbolistas.appendChild(elementFutbolista);
    }
    
    public static List<Futbolista> leerFichero(File fichero)
    {
        FileInputStream fis = null;
        DataInputStream lector = null;
        List<Futbolista> futbolistas = new ArrayList();
        try
        {
            fis = new FileInputStream(fichero);
            lector = new DataInputStream(fis);
            while (true)
            {
                Futbolista futbolista = new Futbolista();
                
                futbolista.setNumero(lector.readShort());
                futbolista.setAlias(lector.readUTF());
                futbolista.setPosicion(lector.readByte());
                futbolista.setAltura(lector.readFloat());
                futbolista.setEquipo(lector.readUTF());
                futbolistas.add(futbolista);
            }
        } catch(EOFException eofx){
            
        }
        catch (FileNotFoundException ex)
        {
            System.out.println(ex.toString());
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                lector.close();
                fis.close();
            } catch (IOException ex)
            {
                System.out.println(ex.toString());
            }
        }
        return futbolistas;
    }
}
