package com.mycompany.ejercicio01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class GestorFichero {

    private File fichero;
    private List<Futbolista> listaFutbolistas;

    public GestorFichero(File fichero)
    {
        this.fichero = fichero;
        listaFutbolistas = listarFutbolistas();
    }
    
    public Boolean anidirFutbolista(int id, String alias, String codigo, int puesto, float altura){
        boolean aniadido = false;
        ObjectOutputStream escritor = null;
        try
        {
            if(fichero.exists()){
                escritor = new ObjectOutputStreamSinCabeceras(new FileOutputStream(fichero, true));
            }else{
                escritor = new ObjectOutputStream(new FileOutputStream(fichero, true));
            }
            Futbolista futbolista = new Futbolista(id, alias, codigo, puesto, altura);
            escritor.writeObject(futbolista);
        
            aniadido = true;
            actualizarLista();
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                escritor.close();
            } catch (IOException ex)
            {
                System.out.println(ex.toString());
            }
        }
        return aniadido;
    }
    
    public List<Futbolista> listarFutbolistas()
    {
        List<Futbolista> futbolistas = new ArrayList();
        ObjectInputStream lector = null;
        try
        {
            lector = new ObjectInputStream(new FileInputStream(fichero));
            while (true)
            {
                Futbolista futbolista = (Futbolista) lector.readObject();
                futbolistas.add(futbolista);
            }
        } catch (EOFException eo)
        {
            System.out.println(eo.toString());
        } catch (IOException ex)
        {
            System.err.println(ex.toString());
        } catch (ClassNotFoundException ex)
        {
            System.err.println("Error al convertir la clase");
        } finally
        {
            if (lector != null)
                try {
                    lector.close();
            } catch (IOException ex) {
                    System.out.println(ex.toString());
            }
          
        }
        return futbolistas;
    }
    
    public List<Futbolista> listarFutbolistasEquipo(String codigo){
        List<Futbolista> futbolistasCodigo = new ArrayList();
        for(Futbolista futbolista: listaFutbolistas){
            if(futbolista.getCodigo().equalsIgnoreCase(codigo)){
                futbolistasCodigo.add(futbolista);
            }
        }
        return futbolistasCodigo;
    }
    
    public Futbolista buscarFutbolista(int id){
        Futbolista futbolista = new Futbolista();
        for(Futbolista f: listaFutbolistas){
            if(f.getIdentificador() == id){
                futbolista = f;
            }
        }
        return futbolista;
    }
    
    public Boolean mofificarEquipo(Futbolista futbolista, String codigoNuevo){
        boolean equipoModificado = false;
        File ficheroAux = new File("auxiliarEquipo.dat");
        ObjectOutputStream escritor = null;
        try
        {
            if(ficheroAux.exists()){
                escritor = new ObjectOutputStreamSinCabeceras(new FileOutputStream(ficheroAux, true));
            }else{
                escritor = new ObjectOutputStream(new FileOutputStream(ficheroAux, true));
            }
           for(Futbolista f: listaFutbolistas){
            if(f.getIdentificador() == futbolista.getIdentificador()){
                f.setCodigo(codigoNuevo);
                equipoModificado = true;
            }
            escritor.writeObject(f);
        }
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                escritor.close();
            } catch (IOException ex)
            {
                System.out.println(ex.toString());
            }
        }
        ficheroAux.renameTo(fichero);
        return equipoModificado;
    }
    
    public Boolean modificarDatos(Futbolista futbolista, int idNuevo, String aliasNuevo, String codigoNuevo, int puestoNuevo, float alturanNueva){
        boolean datosModificados = false;
        ObjectOutputStream escritor = null;
        File ficheroAux = new File("auxiliarDatos.dat");
        try
        {
            if(ficheroAux.exists()){
                escritor = new ObjectOutputStreamSinCabeceras(new FileOutputStream(ficheroAux, true));
            }else{
                escritor = new ObjectOutputStream(new FileOutputStream(ficheroAux, true));
            }
            for (Futbolista f : listaFutbolistas)
            {
                if (f.getIdentificador() == futbolista.getIdentificador())
                {
                    f.setIdentificador(idNuevo);
                    f.setAlias(aliasNuevo);
                    f.setCodigo(codigoNuevo);
                    f.setPuesto(puestoNuevo);
                    f.setAltura(alturanNueva);
                }
                escritor.writeObject(f);
            }
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                escritor.close();
            } catch (IOException ex)
            {
                System.out.println(ex.toString());
            }
        }
        ficheroAux.renameTo(fichero);
        return datosModificados;
    }
    
    public Boolean eliminarFutbolista(int id ){
        boolean eliminado = false;
        ObjectOutputStream escritor = null;
        File ficheroAux = new File("auxiliarEliminar.dat");
        try
        {
            if(ficheroAux.exists()){
                escritor = new ObjectOutputStreamSinCabeceras(new FileOutputStream(ficheroAux, true));
            }else{
                escritor = new ObjectOutputStream(new FileOutputStream(ficheroAux, true));
            }
            for (Futbolista f : listaFutbolistas)
            {
                if (f.getIdentificador() != id)
                {
                 escritor.writeObject(f);
                }
                eliminado = true;
            }
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } finally
        {
            try
            {
                escritor.close();
            } catch (IOException ex)
            {
                System.out.println(ex.toString());
            }
        }
        ficheroAux.renameTo(fichero);
        actualizarLista();
        return eliminado;
    }
    
    public Boolean comprobarId(int id)
    {
        boolean existe = false;
        List<Futbolista> futbolistas = listarFutbolistas();
        for (Futbolista futbolista : futbolistas)
        {
            if (futbolista.getIdentificador() == id)
            {
                existe = true;
            }
        }
        return existe;
    }

    public Puesto definirPuesto(int numero){
        Puesto puesto = Puesto.PORTERO;
        switch(numero){
            case 1:
                puesto = Puesto.PORTERO;
                break;
            case 2:
                puesto = Puesto.DEFENSA;
                break;
            case 3:
                puesto = Puesto.CENTROCAMPISTA;
                break;
            case 4:
                puesto = Puesto.DELANTERO;
                break;
        }
        return puesto;
    }
    
    public int numeroPuesto(Puesto p){
       int puesto = 0;
       if(p == Puesto.PORTERO){
           puesto = 1;
       }else if(p == Puesto.DEFENSA){
           puesto = 2;
       }else if(p == Puesto.CENTROCAMPISTA){
           puesto = 3;
       }else{
           puesto = 4;
       }
        return puesto;
    } 
    
    public void actualizarLista(){
        this.listaFutbolistas = listarFutbolistas();
    }
}
