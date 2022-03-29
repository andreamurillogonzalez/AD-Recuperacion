/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        FileReader lector = null;
        try
        {
            // TODO code application logic here
            Scanner teclado = new Scanner(System.in);
            System.out.println("Introduce un caracter: ");
            char letra = teclado.next().charAt(0);
            File fichero = new File("texto.txt");
            lector = new FileReader(fichero);
            List<Integer> posiciones = new ArrayList();
            int i;
            int contador = 0;
            int posicion = 0;
            while ((i = lector.read()) != -1)
            {
                System.out.print((char) i);
                if(Character.isLetter((char) i)){
                    if((char) i == Character.toUpperCase(letra) || (char) i == Character.toLowerCase(letra)){
                        contador++;
                        posiciones.add(posicion);
                    }
                }else{
                    if((char) i == letra){
                        contador++;
                        posiciones.add(posicion);
                    }
                }
                posicion++;
            }
            System.out.println("El carácter aparece " + contador + " veces.");
            System.out.println("El carácter está en las posiciones: ");
            
            for(int p: posiciones){
                System.out.println(p);
            }
            System.out.println("- Suponiendo que el primer carácter del texto se encuentra en la posición 0 - ");
            lector.close();
        } catch (FileNotFoundException ex)
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
            } catch (IOException ex)
            {
                System.out.println(ex.toString());
            }
        }
    }

}
