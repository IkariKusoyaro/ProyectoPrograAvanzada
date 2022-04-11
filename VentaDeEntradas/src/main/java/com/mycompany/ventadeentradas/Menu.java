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
 * @author TioPanxo
 */
public class Menu {
    public Menu(){
        
    }
    
    public void agregarEntrada(ArrayList<Evento> listaEventos,HashMap<Integer,Evento> mapaEventos) throws IOException{
        System.out.println("Ingrese la ID del evento al que pertenece la Entrada a Agregar (Solo Entradas Numericas)");
        for(int i = 0;i < listaEventos.size();i++){
            System.out.println("");
            System.out.println("Evento "+ (i+1));
            listaEventos.get(i).mostrarEvento();
        }
        BufferedReader usuario = new BufferedReader(new InputStreamReader(System.in));
        int idEvento;
        idEvento = Integer.parseInt(usuario.readLine());
        Evento eventoAux = mapaEventos.get(idEvento);
        System.out.println("Ingrese la ID de la entrada que desea Ocupar (Solo Entradas Numericas)");
        eventoAux.mostrarEntradas();
        int idEntrada = Integer.parseInt(usuario.readLine());
        Entrada entradaAgregar = eventoAux.buscarEntrada(idEntrada);
        System.out.println("Ingrese su Rut");
        entradaAgregar.setRutPersona(usuario.readLine());
    }
    
    public void agregarEvento(int numSerieActual,ArrayList<Evento> listaEventos,HashMap<Integer,Evento> mapaEventos) throws IOException{
        BufferedReader usuario = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Cuantas Entradas Tiene el evento");
        int numEntradas = Integer.parseInt(usuario.readLine());
        Evento eventoAux = new Evento(numEntradas, numSerieActual,(listaEventos.size()+1));
        numSerieActual = numSerieActual + numEntradas;
        System.out.println("Ingresa El Nombre del Evento ");
        eventoAux.setNombreEvento(usuario.readLine());
        eventoAux.setEventId((listaEventos.size()+1));
        System.out.println("Ingrese el dia del evento ");
        eventoAux.setDia(Integer.parseInt(usuario.readLine()));
        System.out.println("Ingrese el Mes del evento ");
        eventoAux.setMes(Integer.parseInt(usuario.readLine()));
        System.out.println("Ingrese el Año del evento ");
        eventoAux.setAño(Integer.parseInt(usuario.readLine()));
        
        
        mapaEventos.put(eventoAux.getEventId(),eventoAux);
        listaEventos.add(eventoAux);
    }
    
    public void mostrarEntradas(ArrayList<Evento> listaEventos){
        for(int i = 0; i < listaEventos.size();i++){
            listaEventos.get(i).mostrarEntradas();
        }
    }
    
    public void mostrarEventos(ArrayList<Evento> listaEventos){
        for(int i = 0;i < listaEventos.size();i++){
            System.out.println("Evento "+ (i+1));
            listaEventos.get(i).mostrarEvento();
            System.out.println("");
        }
    }
    
}
