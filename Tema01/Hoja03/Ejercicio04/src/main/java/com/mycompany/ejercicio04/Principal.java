/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        File fichero = new File("alumnos.txt");
//        List<String> alumnos = new ArrayList();
        try
        {
            BufferedReader lector = new BufferedReader(new FileReader(fichero));
            String linea;
            while ((linea = lector.readLine()) != null)
            {
                String[] partes = linea.split("-");
                String persona = partes[0];
                String edad = partes[1];
                int edadPersona = Integer.parseInt(edad);
//                int posicionGuion = linea.indexOf("-");
//                String persona = linea.substring(0, posicionGuion);
//                String edad = linea.substring(posicionGuion+1, linea.length());
//                int edadPersona = Integer.parseInt(edad);
                if(edadPersona>=20){
                    System.out.println(persona + " tiene " + edadPersona + " años");
                }
            }
            lector.close();
        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.toString());
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }

    }
    
}
