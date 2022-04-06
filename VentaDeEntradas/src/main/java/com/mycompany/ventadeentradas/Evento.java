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
    private int año;
    private HashMap<Integer,Entrada> mapaEntradas;  //Mapa de entradas por id

    //Contructor por parametros
    public Evento(int eventId, String nombreEvento, int dia, int mes, int año) {
        this.eventId = eventId;
        this.nombreEvento = nombreEvento;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        mapaEntradas = new HashMap();
    }
    
    //constructor sin parametros
    public Evento(){
        mapaEntradas = new HashMap();
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

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
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
    }
    
}
