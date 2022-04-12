/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio01;

import java.io.File;
import java.util.List;
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
        File fichero = new File("futbolistas.dat"); 
        GestorFichero gestor = new GestorFichero(fichero);
        int opc;
        
        do{
            System.out.println("1 Añadir futbolista\n" +
                                "2 Listar futbolistas\n" + 
                                "3 Listar futbolistas de equipo\n" +
                                "4 Buscar futbolista\n" +
                                "5 Modificar equipo de futbolista\n" +
                                "6 Modificar datos de futbolista\n" +
                                "7 Eliminar futbolista\n" +
                                "0 Salir");
            System.out.println("Elige una de las opciones: ");
            opc = teclado.nextInt();
            
            switch(opc){
                case 1:
                        int id = pedirId(gestor);
                        System.out.println("Introduce el alias: ");
                        String alias = teclado.next();
                        String codigo = pedirCodigo();
                        int puesto = pedirPuesto();
                        System.out.println("Introduce la altura: ");
                        float altura = teclado.nextFloat();
                        boolean aniadido = gestor.anidirFutbolista(id, alias, codigo, puesto, altura);
                        if(aniadido){
                            System.out.println("Se ha añadido al futbolista correctamente");
                        }else{
                            System.out.println("Error al añadir");
                        }
                    break;
                case 2:
                    List<Futbolista> futbolistas = gestor.listarFutbolistas();
                    for(Futbolista futbolista: futbolistas){
                        System.out.println(futbolista.toString());
                    }
                    break;
                case 3:
                    String codigoEquipo = pedirCodigo();
                    List<Futbolista> futbolistasEquipo = gestor.listarFutbolistasEquipo(codigoEquipo);
                    for(Futbolista futbolista: futbolistasEquipo){
                        System.out.println(futbolista.toString());
                    }
                    break;
                case 4:
                    System.out.println("Introduce el id del futbolista");
                    int idFutbolista = teclado.nextInt();
                    boolean idExiste = gestor.comprobarId(idFutbolista);
                    if(idExiste){
                        Futbolista futbolista = gestor.buscarFutbolista(idFutbolista);
                        System.out.println(futbolista.toString());
                    }else{
                        System.out.println("No existe ningún futbolista con ese id");
                    }
                    break;
                case 5:
                    System.out.println("Introduce el di del futbolista: ");
                    int idFut = teclado.nextInt();
                    boolean existeIdentificador = gestor.comprobarId(idFut);
                    if(existeIdentificador){
                        Futbolista futbolista = gestor.buscarFutbolista(idFut);
                        System.out.println(futbolista.toString());
                        String codigoNuevo = pedirCodigo();
                        boolean equipoModificado = gestor.mofificarEquipo(futbolista, codigoNuevo);
                        if(equipoModificado){
                            System.out.println("Se ha modificado correctamente el equipo del jugador");
                        }else{
                            System.out.println("Error al modificar el equipo");
                        }
                    }else{
                        System.out.println("El futbolista que desea modificar no existe");
                    }
                    break;
                case 6:
                    System.out.println("Introduce el id del futbolista: ");
                    int idFu = teclado.nextInt();
                    boolean identExiste = gestor.comprobarId(idFu);
                    if (identExiste)
                    {
                        Futbolista futbolista = gestor.buscarFutbolista(idFu);
                        System.out.println(futbolista.toString());
                        int idNuevo = pedirId(gestor);
                        System.out.println("Introduce el alias: ");
                        String aliasNuevo = teclado.next();
                        String codigoNuevo = pedirCodigo();
                        int puestoNuevo = pedirPuesto();
                        System.out.println("Introduce la altura: ");
                        float alturaNueva = teclado.nextFloat();
                        boolean datosModificados = gestor.modificarDatos(futbolista, idNuevo, aliasNuevo, codigoNuevo, puestoNuevo, alturaNueva);
                        if (datosModificados)
                        {
                            System.out.println("Se han modificado correctamente los datos del jugador");
                        } else
                        {
                            System.out.println("Error al modificar los datos");
                        }
                    } else
                    {
                        System.out.println("El futbolista que desea modificar no existe");
                    }
                    break;
                case 7:
                    System.out.println("Introduce el id del futbolista: ");
                    int futId = teclado.nextInt();
                    boolean existefutId = gestor.comprobarId(futId);
                    if(existefutId){
                        boolean eliminado = gestor.eliminarFutbolista(futId);
                        if(eliminado){
                            System.out.println("Se ha eliminado al futbolista correctamente");
                        }else{
                            System.out.println("Error al eliminar al futbolista");
                        }
                    }else{
                        System.out.println("No se ha encontrado a ningún futbolista con ese id");
                    }
                    break;
                default:
                    if(opc!=0){
                        System.out.println("Opción incorrecta");
                    }
            }
        }while(opc!=0);
    }

    private static int pedirId(GestorFichero gestor){
        Scanner teclado = new Scanner(System.in);
        int id = 0;
        boolean existeId = false;
        do
        {
            System.out.println("Introduce el id: ");
            id = teclado.nextInt();
            existeId = gestor.comprobarId(id);
            if (existeId)
            {
                System.out.println("El id intrducido ya existe");
            }
        } while (existeId);
        
        return id;
    }
    private static String pedirCodigo()
    {
        Scanner teclado = new Scanner(System.in);
        String codigo = " ";
        do
        {
            System.out.println("Introduce el código del equipo (3 caracteres): ");
            codigo = teclado.next();
            if (codigo.length() != 3)
            {
                System.out.println("El formato del código no es correcto, debe de tener 3 caracteres");
            }
        } while (codigo.length() != 3);
        return codigo;
    }
    
    private static int pedirPuesto()
    {
        Scanner teclado = new Scanner(System.in);
        int puesto = 0;
        do
        {
            System.out.println("Introduce el puesto: " + "\n"
                    + "1 - PORTERO" + "\n"
                    + "2 - DEFENSA" + "\n"
                    + "3 - CENTROCAMPISTA" + "\n"
                    + "4 - DELANTERO");
            puesto = teclado.nextInt();
            if (puesto < 1 || puesto > 4)
            {
                System.out.println("Puesto incorrecto");
            }
        } while (puesto < 1 || puesto > 4);
        return puesto;
    }
}

