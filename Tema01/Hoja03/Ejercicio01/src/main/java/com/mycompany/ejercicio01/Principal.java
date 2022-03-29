/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio01;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce la ruta del fichero: ");
        String ruta = teclado.nextLine();
        File fichero = new File(ruta);
        System.out.println("Introduce el texto que deseas añadir: ");
        String texto = teclado.nextLine();
        String txtMayusculas = texto.toUpperCase();
        String txtMinusculas = texto.toLowerCase();
        String textoFinal= "";
        for (int i = 0; i < texto.length(); i++)
        {
            if(texto.charAt(i) == txtMayusculas.charAt(i)){
                textoFinal = textoFinal + Character.toLowerCase(texto.charAt(i));
                
            }else if(texto.charAt(i) == txtMinusculas.charAt(i)){
                textoFinal = textoFinal + Character.toUpperCase(texto.charAt(i));
                
            }else{
                textoFinal = textoFinal + texto.charAt(i);
            }
        }
    
        try
        {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(fichero));
            escritor.write(textoFinal);
            escritor.close();
            System.out.println(textoFinal);
        }
        catch (FileNotFoundException fn)
        {
            System.err.println("No se encuentra el fichero");
        }
        catch (IOException io)
        {
            System.err.println("Error de E/S");
        }
    }
    
}
