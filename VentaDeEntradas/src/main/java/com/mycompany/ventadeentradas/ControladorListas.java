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
public class ControladorListas {
    
    private HashMap<Integer,Evento> mapaEventos;   
    private ArrayList<Evento> listaEventos;
    private HashMap<String,Cliente> mapaClientes;
    private HashMap<String,Administrador> mapaAdmin;
    private Menu menu;
    
    
    public ControladorListas(){
        mapaEventos = new HashMap();
        listaEventos = new ArrayList();
        mapaClientes = new HashMap();
        mapaAdmin = new HashMap();
        menu = new Menu();
    }
    
    
    public int agregarEventosCSV(int numSerieActual) throws FileNotFoundException, IOException{
        Evento eventoAux;
     
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
                eventoAux.setPrecio(Integer.parseInt(archivoEventos.get_csvField(lineaEventos, 5)));
            }
            else{
                eventoAux = new Evento(archivoEntradas,lineaEventos);
                mapaEventos.put(eventoAux.getEventId(), eventoAux);
                listaEventos.add(eventoAux);
            }
            
            lineaEventos = archivoEventos.nextLine();
        }
        
        archivoEventos.close();
        archivoEntradas.close();
        return numSerieActual;
    }
    
    public void importarPersonasCSV() throws FileNotFoundException, IOException{
    //importar personas
        Cliente clienteAgregar;
        Administrador adminAgregar;
        CSV archivoPersonas = new CSV("Personas");
        
        //linea personas
        String lineaPersona = archivoPersonas.firstLine();
        lineaPersona = archivoPersonas.nextLine();  //Primera linea basura
        
        
        for(;lineaPersona != (null);){
            if(archivoPersonas.get_csvField(lineaPersona, 0).equals("1")){
                clienteAgregar = new Cliente(lineaPersona,archivoPersonas);
                mapaClientes.put(archivoPersonas.get_csvField(lineaPersona,1), clienteAgregar);
                
            }
            else{
                adminAgregar = new Administrador(lineaPersona,archivoPersonas);
                mapaAdmin.put(archivoPersonas.get_csvField(lineaPersona, 1), adminAgregar);
            }
            lineaPersona = archivoPersonas.nextLine();
        }
        archivoPersonas.close();
    }
    
    
    
    
    public void agregarEvento(int numSerieActual) throws IOException{
        menu.agregarEvento(numSerieActual, listaEventos, mapaEventos);
    }
    
    public void agregarEntrada() throws IOException{
        menu.agregarEntrada(listaEventos, mapaEventos);
    }
    
    public void mostrarEventos(){
        menu.mostrarEventos(listaEventos);
    }
    
    public void mostrarEntradas(){
        menu.mostrarEntradas(listaEventos);
    }
    
    public void generarReporte() throws IOException{
        menu.generarReporte(listaEventos);
    }
    
    public void modificarEntrada() throws IOException{
        menu.modificarEntrada(listaEventos);
    }
    
    public void modificarEvento() throws IOException{
        menu.modificarEvento(listaEventos);
    }
    
    public void eliminarEntrada() throws IOException{
        menu.eliminarEntrada(listaEventos,mapaEventos);
    }
    
    public void eliminarEvento() throws IOException{
        menu.eliminarEvento(listaEventos,mapaEventos);
    }
    
    public void comprarEntrada(Cliente aux) throws IOException{
        menu.comprarEntrada(listaEventos, aux);
    }
    
    public void PersonasBeneficiadas() throws IOException{
        menu.PersonasBeneficiadas(listaEventos, mapaClientes);
    }
}


