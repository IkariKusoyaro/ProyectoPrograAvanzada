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
    
    /**
     * agrega una entrada al evento
     * @param listaEventos lista que almacena los eventos 
     * @param mapaEventos mapa que almacena los eventos con clave id del evento
     * @throws IOException Error de I/O
     */
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
    
    /**
     * agrega un evento
     * @param numSerieActual Numero de serie actual del evento
     * @param listaEventos lista que almacena los eventos 
     * @param mapaEventos mapa que almacena los eventos con clave id del evento
     * @throws IOException Error de I/O
     */
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
        System.out.println("Cual es el precio de las entradas del evento");
        eventoAux.setPrecio(Integer.parseInt(usuario.readLine()));
        
        
        mapaEventos.put(eventoAux.getEventId(),eventoAux);
        listaEventos.add(eventoAux);
    }
    
    /**
     * muestra las entradas del evento
     * @param listaEventos lista que almacena los eventos 
     */
    public void mostrarEntradas(ArrayList<Evento> listaEventos){
        for(int i = 0; i < listaEventos.size();i++){
            listaEventos.get(i).mostrarEntradas();
        }
    }
    
    /**
     * muestra por pantalla la lista de eventos
     * @param listaEventos lista que almacena los eventos 
     */
    public void mostrarEventos(ArrayList<Evento> listaEventos){
        for(int i = 0;i < listaEventos.size();i++){
            System.out.println("Evento "+ (i+1));
            listaEventos.get(i).mostrarEvento();
            System.out.println("");
        }
    }
    
    /**
     * busca un evento por su nombre
     * @param listaEventos lista que almacena los eventos 
     * @param nombreEvento Nombre del evento 
     * @return retorna la lista de eventos o null cuando no encuentra el evento
     */
    public Evento buscarEvento(ArrayList<Evento> listaEventos, String nombreEvento){
        for(int i = 0; i < listaEventos.size();i++){
            if(listaEventos.get(i).getNombreEvento().equals(nombreEvento)){
                return listaEventos.get(i);
            }
        }
        return (null);
    }
    
    /**
     * modifica un evento ya existente de la lista
     * @param listaEventos lista que almacena los eventos 
     * @return retorna true si fue modificado o false si no fue modificado
     * @throws IOException Error de I/O
     */
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
                            break;
                        case 2:
                            System.out.println("Ingrese El Nuevo Dia Del Evento");
                            aux.setDia(Integer.parseInt(usuario.readLine()));
                            System.out.println("Ingrese El Nuevo Mes Del Evento");
                            aux.setMes(Integer.parseInt(usuario.readLine()));
                            System.out.println("Ingrese El Nuevo Anio Del Evento");
                            aux.setAnio(Integer.parseInt(usuario.readLine()));
                            break;
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
    
    /**
     * modifica un evento ya existente en el hashmap
     * @param mapaEventos mapa que almacena los eventos con clave id del evento
     * @return retorna true si fue modificado o false si no fue modificado
     * @throws IOException Error de I/O
     */
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
                            break;
                        case 2:
                            System.out.println("Ingrese El Nuevo Dia Del Evento");
                            aux.setDia(Integer.parseInt(usuario.readLine()));
                            System.out.println("Ingrese El Nuevo Mes Del Evento");
                            aux.setMes(Integer.parseInt(usuario.readLine()));
                            System.out.println("Ingrese El Nuevo Anio Del Evento");
                            aux.setAnio(Integer.parseInt(usuario.readLine()));
                            break;
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
    
    /**
     * modifica una entrada
     * @param listaEventos lista que almacena los eventos 
     * @return retorna la entrada modificada
     * @throws IOException Error de I/O
     */
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
    
    /**
     * elimina un evento
     * @param listaEventos lista que almacena los eventos 
     * @param mapaEventos mapa que almacena los eventos con clave id del evento
     * @return si se elimino el evento retorna true , y si no se elimino el evento retorna false
     * @throws IOException Error de I/O
     */
    public boolean eliminarEvento(ArrayList<Evento> listaEventos, HashMap<Integer,Evento> mapaEventos) throws IOException{
        BufferedReader usuario = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese la Id del evento a Eliminar");
        int id = Integer.parseInt(usuario.readLine());
        Evento aux = mapaEventos.get(id);
        if(aux != null){
            boolean flag = listaEventos.remove(aux);
            if (flag != true) return false;
            mapaEventos.remove(id);
            return true;
        }
        return false;
    }
    
    /**
     * elimina una entrada de un evento
     * @param listaEventos lista que almacena los eventos 
     * @param mapaEventos mapa que almacena los eventos con clave id del evento
     * @returnsi se elimino la entrada retorna true , y si no se elimino el evento retorna false
     * @throws IOException Error de I/O
     */
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

    /**
     * genera un reporte en formato .txt
     * @param listaEventos lista que almacena los eventos 
     * @throws IOException Error de I/O
     */
    public void generarReporte(ArrayList<Evento> listaEventos) throws IOException {
        try (FileWriter fichero = new FileWriter ("./reportes.txt")) {
            for (int i=0; i<listaEventos.size(); i++){
                fichero.write("Evento: " + (listaEventos.get(i)).getNombreEvento() + " - "  + "Anio: " + (listaEventos.get(i)).getAnio() + " - " + "Dia: " + (listaEventos.get(i)).getDia() + " - " + "Mes: " + (listaEventos.get(i)).getMes() + "\n" );
                
            }
        }
    }
    
    //buscar las personas que estan de cumpleaños el dia del evento y muestra una lista con ellas
    public void PersonasBeneficiadas(ArrayList<Evento> listaEventos, HashMap<String,Cliente> mapaClientes) throws IOException{
        BufferedReader usuario = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el evento del cual desea buscar Beneficiarios");
        String nombreEvento = usuario.readLine();
        Evento aux = null;
        
        //busca el evento
        for(int i = 0;i < listaEventos.size();i++ ){
            if(listaEventos.get(i).getNombreEvento().equals(nombreEvento)){
                aux = listaEventos.get(i);
                break;
            }
        }
        if(aux == null){
            System.out.println("No existe el evento");
            return;
        }
        
        //busca si en el evento hay entradas no nulas
        ArrayList<Entrada> listaEntradas = new ArrayList();
        if(aux.entradasNoNulas() != null){
            boolean flag = listaEntradas.addAll(aux.entradasNoNulas());
        }else{
            System.out.println("no hay personas beneficiadas");
            return;
        }
        
        //busca a las personas de la lista de entradas en el mapa de clientes
        int contador = 0;
        for (int i = 0;i < listaEntradas.size(); i++) {
            Cliente clienteAux = mapaClientes.get(listaEntradas.get(i).getRutPersona());
            if(clienteAux.verificarDescuento(aux.getDia(),aux.getMes())){
                contador++;
                clienteAux.mostrarPersona();
            }
        }
        if(contador == 0)System.out.println("No hay personas beneficiadas");;
        
    }
    
    
    

    /**
     * opcion para comprar una entrada para un evento
     * @param listaEventos lista que almacena los eventos 
     * @param user El usuario de el cliente quiere comprar una entrada
     * @throws IOException Error de I/O
     */
    public void comprarEntrada(ArrayList<Evento> listaEventos, Cliente user) throws IOException{
        int precioFinal = 0;
        BufferedReader usuario = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el evento del cual desea comprar la entrada");
        String nombreEvento = usuario.readLine();
        Evento aux = null;
        
        //busca el evento
        for(int i = 0;i < listaEventos.size();i++ ){
            if(listaEventos.get(i).getNombreEvento().equals(nombreEvento)){
                aux = listaEventos.get(i);
                break;
            }
        }
        if(aux == null){
            System.out.println("No existe el evento");
            return;
        }
        
        //Revisa que tenga entradas vacias
        if(aux.buscarEntradaVacia() == (null))return;
        
        //calcula el descuento de la entrada
        if(user.verificarDescuento(aux.getDia(),aux.getAnio())){
            precioFinal = (aux.getPrecio()) - (user.generarDescuento(aux.getPrecio()));
        }
        else precioFinal = aux.getPrecio();
        System.out.println("El precio de la entrada es: " + precioFinal);
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        
        
        //pregunta si se desea confirmar la comprar
        while (!salir) {
            System.out.println("1. Comprar");
            System.out.println("2. Salir");
            try {
                System.out.println("Selecciona una opcion");
                opcion = sn.nextInt();
                switch (opcion) {
                    case 1:
                        aux.buscarEntradaVacia().setRutPersona(user.getRut());
                        System.out.println("Venta Exitosa");
                        return;
                    case 2:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 2");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }
    
}
