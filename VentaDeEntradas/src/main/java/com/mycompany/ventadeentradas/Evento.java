package com.mycompany.ventadeentradas;

import java.util.*;




/**
 *
 * @author Camilo
 */
public class Evento {
    private int eventId; //identificador numerico del evento
    private String nombreEvento; //nombre del evento
    private int dia;
    private int mes;
    private int anio;
    private HashMap<Integer,Entrada> mapaEntradas;  //Mapa de entradas por id
    private ArrayList<Entrada> listaEntradas;

    //Contructor por parametros
    public Evento(int eventId, String nombreEvento, int dia, int mes, int anio) {
        this.eventId = eventId;
        this.nombreEvento = nombreEvento;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        mapaEntradas = new HashMap();
        listaEntradas = new ArrayList();
    }
    
    //Constructor desde String
    public Evento(CSV archivo, String linea){
        eventId = Integer.parseInt(archivo.get_csvField(linea, 0));
        nombreEvento = archivo.get_csvField(linea, 1);
        dia = Integer.parseInt(archivo.get_csvField(linea, 2));
        mes = Integer.parseInt(archivo.get_csvField(linea, 3));
        anio = Integer.parseInt(archivo.get_csvField(linea, 4));
        mapaEntradas = new HashMap();
        listaEntradas = new ArrayList();
    }
    
    //constructor sin parametros
    public Evento(){
        mapaEntradas = new HashMap();
        listaEntradas = new ArrayList();
    }
    
    
    //******
    
    
    //constructor con cantidad de entradas fija
    public Evento(int numEntradas, int numSerieActual,int idEvento){
        mapaEntradas = new HashMap();
        listaEntradas = new ArrayList();
        for(int i = 0; i < numEntradas;i++){
            Entrada entradaAux = new Entrada(numSerieActual,(i+1),"0",1,idEvento);
            mapaEntradas.put(numSerieActual, entradaAux);
            listaEntradas.add(entradaAux);
            numSerieActual++;
        }
    }
    
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }
    
    //metodos
    public void addEntrada(Entrada aAgregar){
        mapaEntradas.put(aAgregar.getNumSerie(), aAgregar);
        listaEntradas.add(aAgregar);
    }
    
    
    
    
    //Mostrar Todas Las Entradas del evento
    public void mostrarEntradas(){   
        System.out.println("");
        System.out.println("EVENTO " + nombreEvento);
        for(int i = 0;i < listaEntradas.size();i++){
            System.out.println("Entrada " + (i+1));
            listaEntradas.get(i).mostrarEntrada(nombreEvento);
            
        }
    }
    
    //mostrar Evento
    public void mostrarEvento(){
        System.out.println("ID Evento: "+ eventId);
        System.out.println("Nombre Evento: "+ nombreEvento);
        System.out.println("Fecha Evento: "+ dia + "/" + mes + "/" + anio);
        
    }
    
    //buscar Entrada en HashMap
    public Entrada buscarEntrada(int idEntrada){
        if(mapaEntradas.containsKey(idEntrada)){
            return mapaEntradas.get(idEntrada);
        }
        else{
            return null;
        }
    }
    
    
    
    
    //Mostrar Todas las Entradas de un evento especifico
    //codigo
}
