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
    private int precio;     //precio de la entrada

    //Contructor por parametros
    public Evento(int eventId, String nombreEvento, int dia, int mes, int anio, int precio) {
        this.eventId = eventId;
        this.nombreEvento = nombreEvento;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.precio = precio;
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
        precio = Integer.parseInt(archivo.get_csvField(linea, 5));
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

    /**
     * crea un evento y le asigna sus datos
     * @param numEntradas numero de entradas del evento
     * @param numSerieActual Numero de serie del evento
     * @param idEvento Numero de indentificacion del evento
     */
    public Evento(int numEntradas, int numSerieActual,int idEvento){
        mapaEntradas = new HashMap();
        listaEntradas = new ArrayList();
        for(int i = 0; i < numEntradas;i++){
            Entrada entradaAux = new Entrada(numSerieActual,(i+1),"0",idEvento);
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    
    
    //metodos

     /**
     * Añade una entrada al mapa de entradas
     * @param Entradas a agregar
     * @return 
     */
    public void addEntrada(Entrada aAgregar){
        mapaEntradas.put(aAgregar.getNumSerie(), aAgregar);
        listaEntradas.add(aAgregar);
    }
    
    
    
    
    //Mostrar Todas Las Entradas del evento
     /**
     * Muestra todas las entradas del evento
     * @param idEntrada Numero de identificacion de una entrada
     */
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
        System.out.println("ID Evento: "+ eventId + " Precio Evento: " + precio);
        System.out.println("Nombre Evento: "+ nombreEvento);
        System.out.println("Fecha Evento: "+ dia + "/" + mes + "/" + anio);
        
    }
    
    
    //sobrecarga de metodo
    
    /**
     * Buscar Entrada en HashMap por Id de la entrada
     * @param idEntrada Numero de identificacion de una entrada
     * @return Retona una Entrada
     */
    public Entrada buscarEntrada(int idEntrada){
        if(mapaEntradas.containsKey(idEntrada)){
            return mapaEntradas.get(idEntrada);
        }
        else{
            return null;
        }
    }
    
    
    /**
     * Buscar Entrada en ArrayList por rut
     * @param rutPersona Rut de la persona que se desea buscar
     * @return Retorna una Entrada 
     */
    public Entrada buscarEntrada(String rutPersona){
        for(int i = 0;i < listaEntradas.size();i++){
            if(listaEntradas.get(i).getRutPersona().equals(rutPersona)){
                return listaEntradas.get(i);
            }
        }
        return (null);
    }
    
    /**
     * Buscar Entradas sin dueño
     * @param la lista de entradas listaEntradas
     * @return Retorna las entradas vacias
     */
    public Entrada buscarEntradaVacia(){
        String rutAux = "0";
        for(int i = 0; i < listaEntradas.size();i++){
            if(listaEntradas.get(i).getRutPersona().equals(rutAux))return listaEntradas.get(i);
        }
        return null;
    }
    
    /**
     * Busca las entradas validas
     * @param arraylist de entradas
     * @return retorna retorna una entrada no nula, si no existe retorna null
     */
    public ArrayList<Entrada> entradasNoNulas(){
        ArrayList<Entrada> noNulas = new ArrayList(); 
        for(int i = 0; i < listaEntradas.size();i++){
            if(!(listaEntradas.get(i).getRutPersona().equals("0"))){
                noNulas.add(listaEntradas.get(i));
            }
        }
        if(!noNulas.isEmpty()) return noNulas;
        return null;
    }
    
    //Mostrar Todas las Entradas de un evento especifico
    //codigo
}
