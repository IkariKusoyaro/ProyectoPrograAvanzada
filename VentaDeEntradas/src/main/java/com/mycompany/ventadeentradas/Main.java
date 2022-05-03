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
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        int numSerieActual = 1;
        
        HashMap<Integer,Evento> mapaEventos;
        mapaEventos = new HashMap();
        
        ArrayList<Evento> listaEventos;
        listaEventos = new ArrayList();
        
        Evento eventoAux;
        
        
        //mapa de entradas a agregar al evento
        //HashMap<Integer,Entrada> mapaEntradas;
        
        
        
        //importar entradas
        Entrada entradaAgregar;
        CSV archivoEntradas = new CSV("Entradas");
        CSV archivoEventos = new CSV("Eventos");
        
        //linea Entradas
        String linea = archivoEntradas.firstLine();
        linea = archivoEntradas.nextLine();     //Primera Linea Basura
        
        //linea Eventos
        String lineaEventos = archivoEventos.firstLine();
        lineaEventos = archivoEventos.nextLine();       //Primera Linea Basura
        
        
        for(;linea != (null);){
            entradaAgregar = new Entrada(archivoEntradas,linea);
            
            if(mapaEventos.containsKey(entradaAgregar.getIdEvento())){
                eventoAux = mapaEventos.get(entradaAgregar.getIdEvento());
                eventoAux.addEntrada(entradaAgregar);
            }
            else{
                eventoAux = new Evento();
                eventoAux.addEntrada(entradaAgregar);
                mapaEventos.put(entradaAgregar.getIdEvento(), eventoAux);
                listaEventos.add(eventoAux);
            }
            //mapaEntradas.put(entradaAgregar.getNumSerie(), entradaAgregar);
            
            
            numSerieActual++;
            linea = archivoEntradas.nextLine();
        }
        
        for(;lineaEventos != (null);){
            if(mapaEventos.containsKey(Integer.parseInt(archivoEventos.get_csvField(lineaEventos, 0)))){
                eventoAux = mapaEventos.get(Integer.parseInt(archivoEventos.get_csvField(lineaEventos, 0)));
                eventoAux.setEventId(Integer.parseInt(archivoEventos.get_csvField(lineaEventos, 0)));
                eventoAux.setNombreEvento(archivoEventos.get_csvField(lineaEventos, 1));
                eventoAux.setDia(Integer.parseInt(archivoEventos.get_csvField(lineaEventos, 2)));
                eventoAux.setMes(Integer.parseInt(archivoEventos.get_csvField(lineaEventos, 3)));
                eventoAux.setAnio(Integer.parseInt(archivoEventos.get_csvField(lineaEventos, 4)));
            }
            else{
                eventoAux = new Evento(archivoEntradas,lineaEventos);
                mapaEventos.put(eventoAux.getEventId(), eventoAux);
                listaEventos.add(eventoAux);
            }
            
            lineaEventos = archivoEventos.nextLine();
        }
        
        Menu menu = new Menu();

        //Eliminar Elemento
        
        
        
        
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
            System.out.println("10. Salir");
 
            try {
 
                System.out.println("Selecciona una opcion");
                opcion = sn.nextInt();
 
                switch (opcion) {
                    case 1:
                        //Agregar Evento
                        menu.agregarEvento(numSerieActual, listaEventos, mapaEventos);
                        break;
                    case 2:
                        //Agregar Entrada (De las disponibles)
                        menu.agregarEntrada(listaEventos, mapaEventos);
                        break;
                    case 3:
                        //Mostrar Eventos 
                        menu.mostrarEventos(listaEventos);
                        break;
                    case 4:
                        //MostrarEntradas de todos los eventos
                        menu.mostrarEntradas(listaEventos);
                        break;
                    case 5:
                        //
                        menu.generarReporte(listaEventos);
                        break;
                    case 6:
                        //
                        menu.modificarEntrada(listaEventos);
                        break;
                    case 7:
                        //
                        menu.modificarEvento(listaEventos);
                        break;
                    case 8:
                        //
                        menu.eliminarEntrada(listaEventos,mapaEventos);
                        break;
                    case 9:
                        //
                        menu.eliminarEvento(listaEventos,mapaEventos);
                        break;

                    case 10:
                        salir = true;
                        break;
                        
                    default:
                        System.out.println("Solo números entre 1 y 3");
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
