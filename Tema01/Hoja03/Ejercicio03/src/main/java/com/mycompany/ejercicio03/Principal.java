/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
        // TODO code application logic here
        int opcion = 0;
        do
        {
            Scanner teclado = new Scanner(System.in);
            File fichero = new File("personas.txt");
            System.out.println("1 - Añadir persona " + "\n"
                    + "2 - Buscar persona" + "\n"
                    + "3 - Buscar nombre" + "\n"
                    + "4 - Apellidos comienzan por" + "\n"
                    + "5 - Eliminar persona" + "\n"
                    + "0 - Salir");

            System.out.println("Elige una de las opciones: ");
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion)
            {
                case 1:
                    System.out.println("Introduce el nombre: ");
                    String nombre = teclado.nextLine();

                    System.out.println("Introduce los apellidos: ");
                    String apellidos = teclado.nextLine();
                    
                    aniadirPersona(fichero, nombre, apellidos);
                    break;
                    
                case 2:
                    System.out.println("Introduce el nombre: ");
                    String nombreBuscar = teclado.nextLine();

                    System.out.println("Introduce los apellidos: ");
                    String apellidosBuscar = teclado.nextLine();
                    
                    boolean personaEncontrada = buscarPersona(fichero, nombreBuscar, apellidosBuscar);
                    if(personaEncontrada){
                        System.out.println("Se ha encontrado ha "+ nombreBuscar + " " + apellidosBuscar +" en el fichero");
                    }else{
                        System.out.println("No se ha encontrado ha " + nombreBuscar + " " + apellidosBuscar + " en el fichero");
                    }
                    break;

                case 3:
                    System.out.println("Introduce el nombre: ");
                    String nombreBuscar2 = teclado.nextLine();
                    List<String> personas = buscarPorNombre(fichero, nombreBuscar2);
                    if (personas.isEmpty())
                    {
                        System.out.println("No se ha encontrado ha ninguna persona llamada " + nombreBuscar2 + " en el fichero");
                    } else
                    {
                        System.out.println("Personas encontradas con ese nombre: ");
                        for (String p: personas)
                        {
                            System.out.println(p);
                        }
                    }
                    break;
                    
                case 4:
                    
                    break;
                case 5:
                    System.out.println("Introduce el nombre: ");
                    String nombreEliminar = teclado.nextLine();

                    System.out.println("Introduce los apellidos: ");
                    String apellidosEliminar = teclado.nextLine();
                    boolean personaEliminada = eliminarPersona(fichero,nombreEliminar, apellidosEliminar);
                    if(personaEliminada){
                        System.out.println("Se ha eliminado ha " + nombreEliminar + " " + apellidosEliminar);
                    }else{
                        System.out.println("Error al eliminar");
                    }
                    break;
                default:
                    if (opcion != 0)
                    {
                        System.out.println("Opción incorrecta");
                    }
                    break;
            }
        } while (opcion != 0);
    }

    public static void aniadirPersona(File fichero, String nombre, String apellidos)
    {
        String persona = apellidos.toUpperCase() + ", " + nombre.toUpperCase();
        try
        {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(fichero, true));
            escritor.write(persona);
            escritor.newLine();
            escritor.close();
        } catch (FileNotFoundException fn)
        {
            System.err.println("No se encuentra el fichero");
        } catch (IOException io)
        {
            System.err.println("Error de E/S");
        }
    }
    
    public static boolean buscarPersona(File fichero, String nombreBuscar, String apellidosBuscar){
        boolean encontrado = false;
        
        String personaBuscar = apellidosBuscar.toUpperCase() + ", " + nombreBuscar.toUpperCase();
        String textoFichero = " ";
        try
        {
            BufferedReader lector;
            lector = new BufferedReader(new FileReader(fichero));
            String linea;
            while ((linea = lector.readLine()) != null)
            {
                textoFichero = textoFichero + linea;
            }
            lector.close();

            if (textoFichero.contains(personaBuscar))
            {
                encontrado = true; 
            }
        } catch (FileNotFoundException ex)
        {
           System.out.println(ex.toString());
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }
        return encontrado;
    }

    public static List<String> buscarPorNombre(File fichero, String nombreBuscar)
    {
        
        String textoFichero = " ";
        List<String> personas = new ArrayList();
        try
        {
            BufferedReader lector;
            lector = new BufferedReader(new FileReader(fichero));
            String linea;
            while ((linea = lector.readLine()) != null)
            {
                if (linea.contains(nombreBuscar.toUpperCase()))
                {
                    personas.add(linea);
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
        return personas;
    }
    
    public static void apellidosComienzanPor(File fichero, String iniciales){
       
    }
    
    public static boolean eliminarPersona(File fichero, String nombre, String apellidos){
        boolean personaEliminada = false;
        boolean personaEncontrada = buscarPersona(fichero, nombre, apellidos);
        List<String> personas = new ArrayList();
        if (personaEncontrada)
        {
            String personaEliminar = apellidos.toUpperCase() + ", " + nombre.toUpperCase();
            try
            {
                BufferedReader lector;
                lector = new BufferedReader(new FileReader(fichero));
                String linea;
                while ((linea = lector.readLine()) != null)
                {
                    if(!linea.equals(personaEliminar)){
                        personas.add(linea);
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
            String nombreFichero = fichero.getName();
            fichero.delete();
            File ficheroNuevo = new File(nombreFichero);
            try
            {
                BufferedWriter escritor = new BufferedWriter(new FileWriter(fichero, true));
                for(String p: personas){
                 escritor.write(p);
                 escritor.newLine();   
                }
                escritor.close();
            } catch (FileNotFoundException fn)
            {
                System.err.println("No se encuentra el fichero");
            } catch (IOException io)
            {
                System.err.println("Error de E/S");
            }
            personaEliminada = true;
        }
        return personaEliminada;
    }
}
