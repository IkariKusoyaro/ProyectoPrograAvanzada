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
        
        HashMap<Integer,Evento> mapaEventos;
        mapaEventos = new HashMap();
        
        Evento eventoAux;
        
        
        //mapa de entradas a agregar al evento
        //HashMap<Integer,Entrada> mapaEntradas;
        
        
        
        //importar entradas
        Entrada entradaAgregar;
        CSV archivoEntradas = new CSV("Entradas");
        String linea = archivoEntradas.firstLine();
        linea = archivoEntradas.nextLine();     //Primera Linea Basura
        
        
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
            }
            //mapaEntradas.put(entradaAgregar.getNumSerie(), entradaAgregar);
            
            
            
            linea = archivoEntradas.nextLine();
        }
        
        
        
    }
}
