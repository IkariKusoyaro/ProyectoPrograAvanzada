/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ventadeentradas;

import java.io.*;
import java.util.*;

/**
 *
 * @author Camilo
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException, InvalidDataTypeException, DatoExisteException {
        
        int numSerieActual = 1;
       
        ControladorColecciones listas = new ControladorColecciones();

        numSerieActual = listas.agregarEventosCSV(numSerieActual); 
        
        listas.importarPersonasCSV();
        
        
        
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
 
        while (!salir) {
 
            System.out.println("1. Agregar Evento");
            System.out.println("2. Agregar Entrada");
            System.out.println("3. Lista de Eventos");
            System.out.println("4. Lista de Entradas");
            System.out.println("5. Generar Informe");
            System.out.println("6. Editar Entrada");
            System.out.println("7. Editar Evento");
            System.out.println("8. Borrar Entrada");
            System.out.println("9. Borrar Evento");
            System.out.println("10. Vender Entrada");
            System.out.println("11. Mostrar clientes con Decuentos");
            System.out.println("12. Salir");
 
            try {
 
                System.out.println("Selecciona una opcion");
                opcion = sn.nextInt();
 
                switch (opcion) {
                    case 1:
                        //Agregar Evento
                        listas.agregarEvento(numSerieActual);
                        break;
                    case 2:
                        //Agregar Entrada (De las disponibles)
                        listas.agregarEntrada();
                        break;
                    case 3:
                        //Mostrar Eventos 
                        listas.mostrarEventos();
                        break;
                    case 4:
                        //MostrarEntradas de todos los eventos
                        listas.mostrarEntradas();
                        break;
                    case 5:
                        //
                        listas.generarReporte();
                        break;
                    case 6:
                        //
                        listas.modificarEntrada();
                        break;
                    case 7:
                        //
                        listas.modificarEvento();
                        break;
                    case 8:
                        //
                        listas.eliminarEntrada();
                        break;
                    case 9:
                        //
                        listas.eliminarEvento();
                        break;
                    case 10:
                        Cliente aux = new Cliente("12345678-9","Jesucristo de Nazaret","Diosito12",25,12,0,100000);
                        listas.comprarEntrada(aux);
                        break;
                    case 11:
                        listas.PersonasBeneficiadas();
                        break;
                    case 12:
                        salir = true;
                        break;
                        
                    default:
                        System.out.println("Solo números entre 1 y 12");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
        
        
        
        /*ArrayList<Entrada> a = new ArrayList();
        entradaAgregar = new Entrada(1,2,"20",3,4);
        a.add(entradaAgregar);
        if(a.contains(entradaAgregar))System.out.println("a");
        */
    }
}
