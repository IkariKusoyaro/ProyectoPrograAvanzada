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
        eventoAux.setAnio(Integer.parseInt(usuario.readLine()));
        
        
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
    
    public Evento buscarEvento(ArrayList<Evento> listaEventos, String nombreEvento){
        for(int i = 0; i < listaEventos.size();i++){
            if(listaEventos.get(i).getNombreEvento().equals(nombreEvento)){
                return listaEventos.get(i);
            }
        }
        return (null);
    }
    
    
    public boolean modificarEvento(ArrayList<Evento> listaEventos) throws IOException{
        BufferedReader usuario = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el Nombre del Evento a Modificar");
        Menu menu = new Menu();
        Evento aux = menu.buscarEvento(listaEventos, usuario.readLine());
        if(aux != (null)){
            Scanner sn = new Scanner(System.in);
            boolean salir = false;
            int opcion; //Guardaremos la opcion del usuario
 
            while (!salir) {
 
                System.out.println("1. Modificar Nombre");
                System.out.println("2. Modificar Fecha");
                System.out.println("3. Salir");
 
                try {
 
                    System.out.println("Selecciona una opcion");
                    opcion = sn.nextInt();
 
                    switch (opcion) {
                        case 1:
                            System.out.println("Ingrese El Nuevo Nombre Del Evento");
                            aux.setNombreEvento(usuario.readLine());
                        case 2:
                            System.out.println("Ingrese El Nuevo Dia Del Evento");
                            aux.setDia(Integer.parseInt(usuario.readLine()));
                            System.out.println("Ingrese El Nuevo Mes Del Evento");
                            aux.setMes(Integer.parseInt(usuario.readLine()));
                            System.out.println("Ingrese El Nuevo Anio Del Evento");
                            aux.setAnio(Integer.parseInt(usuario.readLine()));
                        case 3:
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
            
            return true;
        }
        return false;
    }
    
    public boolean modificarEvento(HashMap<Integer,Evento> mapaEventos) throws IOException{
        BufferedReader usuario = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese la Id del Evento a Modificar");
        Evento aux = mapaEventos.get(Integer.parseInt(usuario.readLine()));
        if(aux != (null)){
            Scanner sn = new Scanner(System.in);
            boolean salir = false;
            int opcion; //Guardaremos la opcion del usuario
 
            while (!salir) {
 
                System.out.println("1. Modificar Nombre");
                System.out.println("2. Modificar Fecha");
                System.out.println("3. Salir");
 
                try {
 
                    System.out.println("Selecciona una opcion");
                    opcion = sn.nextInt();
 
                    switch (opcion) {
                        case 1:
                            System.out.println("Ingrese El Nuevo Nombre Del Evento");
                            aux.setNombreEvento(usuario.readLine());
                        case 2:
                            System.out.println("Ingrese El Nuevo Dia Del Evento");
                            aux.setDia(Integer.parseInt(usuario.readLine()));
                            System.out.println("Ingrese El Nuevo Mes Del Evento");
                            aux.setMes(Integer.parseInt(usuario.readLine()));
                            System.out.println("Ingrese El Nuevo Anio Del Evento");
                            aux.setAnio(Integer.parseInt(usuario.readLine()));
                        case 3:
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
            
            return true;
        }
        return false;
    }
    
    public boolean modificarEntrada(ArrayList<Evento> listaEventos) throws IOException{
        BufferedReader usuario = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese la Id de la entrada a modificar");
        int id = Integer.parseInt(usuario.readLine());
        Entrada aux = null;
        for(int i = 0;i < listaEventos.size(); i++ ){
            aux = listaEventos.get(i).buscarEntrada(id);
            if(aux != null) break;
        }
        if(aux != null){
            System.out.println("Ingrese el Nuevo Rut de la Persona a la que esta Asignada la Entrada");
            aux.setRutPersona(usuario.readLine());
            return true;
        }
        return false;
    }
    
    public boolean eliminarEvento(ArrayList<Evento> listaEventos, HashMap<Integer,Evento> mapaEventos) throws IOException{
        BufferedReader usuario = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese la Id del evento a Eliminar");
        int id = Integer.parseInt(usuario.readLine());
        Evento aux = mapaEventos.get(id);
        if(aux != null){
            boolean flag = listaEventos.remove(aux);
            if (flag != true) return false;
            mapaEventos.remove(id);
        }
        return false;
    }
    
    public boolean eliminarEntrada(ArrayList<Evento> listaEventos,HashMap<Integer,Evento> mapaEventos) throws IOException{
        BufferedReader usuario = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese la Id de la entrada a eliminar");   //la entrada no se elimina 
        int id = Integer.parseInt(usuario.readLine());                  //sino que se modifica el rut asociado
        Entrada aux = null;
        for(int i = 0;i < listaEventos.size(); i++ ){
            aux = listaEventos.get(i).buscarEntrada(id);
            if(aux != null) break;
        }
        if(aux != null){
            aux.setRutPersona("0");
            return true;
        }
        return false;
    }
    public void generarReporte(ArrayList<Evento> listaEventos) throws IOException {
        FileWriter fichero = new FileWriter ("C:/Users/rodri/Desktop/reportes/reportes.txt");
        for (int i=0; i<listaEventos.size(); i++){
            fichero.write("Evento: " + (listaEventos.get(i)).getNombreEvento() + " - "  + "Año: " + (listaEventos.get(i)).getAnio() + " - " + "Día: " + (listaEventos.get(i)).getDia() + " - " + "Mes: " + (listaEventos.get(i)).getMes() + "\n" ); 

        }
        fichero.close();
    }
}
