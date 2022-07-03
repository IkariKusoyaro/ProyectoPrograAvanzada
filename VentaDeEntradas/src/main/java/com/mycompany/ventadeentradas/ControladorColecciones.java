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
public class ControladorColecciones {
    
    private HashMap<Integer,Evento> mapaEventos;   
    private ArrayList<Evento> listaEventos;
    private HashMap<String,Cliente> mapaClientes;
    private HashMap<String,Administrador> mapaAdmin;
    
    
    public ControladorColecciones(){
        mapaEventos = new HashMap();
        listaEventos = new ArrayList();
        mapaClientes = new HashMap();
        mapaAdmin = new HashMap();
    }
    
    /**
     * Funcion que importa todos lo eventos de un archivo csv
     * @param numSerieActual
     * @return numero de serie resultante de ingresar las entradas
     * @throws FileNotFoundException
     * @throws IOException
     */
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
    
    /**
     * funcion que importa todos los usuarios y super-usuarios de un csv
     * @throws FileNotFoundException
     * @throws IOException
     */
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
    
    
    
    /**
     * agrega un evento
     * @param numSerieActual Numero de serie actual del evento
     * @throws IOException Error de I/O
     * @throws com.mycompany.ventadeentradas.InvalidDataTypeException Error de Tipo de Dato Incorrecto
     * @throws com.mycompany.ventadeentradas.DatoExisteException Dato Existente en Coleccion 
     */
    
    public void agregarEvento(int numSerieActual) throws InvalidDataTypeException, DatoExisteException, IOException{
        BufferedReader usuario = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Cuantas Entradas Tiene el evento");
        
        int numEntradas;
        try{
            numEntradas = Integer.parseInt(usuario.readLine());
        }
        catch(NumberFormatException a){
            throw new InvalidDataTypeException();
        }
        
        Evento eventoAux = new Evento(numEntradas, numSerieActual,(listaEventos.size()+1));
        numSerieActual = numSerieActual + numEntradas;
        System.out.println("Ingresa El Nombre del Evento ");
        
        String nombreEvento = usuario.readLine();
        if(buscarEvento(listaEventos, nombreEvento) != null) throw new DatoExisteException();
        eventoAux.setNombreEvento(nombreEvento);
        eventoAux.setEventId((listaEventos.size()+1));
        //exception nombre de evento ya existia
        
        System.out.println("Ingrese el dia del evento ");
        int dia;
        try{
            dia = Integer.parseInt(usuario.readLine());
        }
        catch(NumberFormatException a){
            throw new InvalidDataTypeException();
        }
        eventoAux.setDia(dia);
        
        System.out.println("Ingrese el Mes del evento ");
        int mes;
        try{
            mes = Integer.parseInt(usuario.readLine());
        }
        catch(NumberFormatException a){
            throw new InvalidDataTypeException();
        }
        eventoAux.setMes(mes);
        
        System.out.println("Ingrese el Año del evento ");
        int anio;
        try{
            anio = Integer.parseInt(usuario.readLine());
        }
        catch(NumberFormatException a){
            throw new InvalidDataTypeException();
        }
        eventoAux.setAnio(anio);
        
        System.out.println("Cual es el precio de las entradas del evento");
        int precio;
        try{
            precio = Integer.parseInt(usuario.readLine());
        }
        catch(NumberFormatException a){
            throw new InvalidDataTypeException();
        }
        eventoAux.setPrecio(precio);
        
        
        mapaEventos.put(eventoAux.getEventId(),eventoAux);
        listaEventos.add(eventoAux);
    }
    
    /**
     * agrega una entrada al evento 
     * @throws IOException Error de I/O
     */
    public void agregarEntrada() throws IOException{
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
     * muestra por pantalla la lista de eventos 
     */
    public void mostrarEventos(){
        for(int i = 0;i < listaEventos.size();i++){
            System.out.println("Evento "+ (i+1));
            listaEventos.get(i).mostrarEvento();
            System.out.println("");
        }
    }
    
    public void mostrarEntradas(){
        for(int i = 0; i < listaEventos.size();i++){
            listaEventos.get(i).mostrarEntradas();
        }
    }
    
    /**
     * genera un reporte en formato .txt
     * @throws IOException Error de I/O
     */
    public void generarReporte() throws IOException{
        try (FileWriter fichero = new FileWriter ("./reporte.txt")) {
            for (int i=0; i<listaEventos.size(); i++){
                fichero.write("Id Evento - Nombre Evento - Dia - Mes - Año - Precio\n");
                fichero.write(((listaEventos.get(i)).getEventId()) + " - " + (listaEventos.get(i)).getNombreEvento() + " - " + (listaEventos.get(i)).getDia() + " - " + (listaEventos.get(i)).getMes() + " - " + (listaEventos.get(i)).getAnio() + " - " + (listaEventos.get(i)).getPrecio() +"\n" );
                fichero.write("\nId Entrada - Numero Asiento - Rut Persona - Evento\n");
                fichero.write((listaEventos.get(i)).generarReporteEntradas());
                fichero.write("\n");
                fichero.write("\n");
            }
            
            fichero.close();
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
    
    /**
     * modifica una entrada 
     * @return retorna la entrada modificada
     * @throws IOException Error de I/O
     */
    public boolean modificarEntrada() throws IOException{
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
     * modifica un evento ya existente de la lista 
     * @return retorna true si fue modificado o false si no fue modificado
     * @throws IOException Error de I/O
     */
    public boolean modificarEventoNombre() throws IOException{
        BufferedReader usuario = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el Nombre del Evento a Modificar");
        Evento aux = buscarEvento(listaEventos, usuario.readLine());
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
     * @return retorna true si fue modificado o false si no fue modificado
     * @throws IOException Error de I/O
     */
    public boolean modificarEventoId() throws IOException{
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
     * Funcion que hace de menu para modificar Evento
     * @throws IOException
     */
    public void modificarEvento() throws IOException{
        Scanner sn = new Scanner(System.in);
        int opcion; //Guardaremos la opcion del usuario
        
        
 
        System.out.println("1. Modificar Evento Por Id");
        System.out.println("2. Modificar Evento Por Nombre");
        System.out.println("3. Salir");
 
        try {
 
            System.out.println("Selecciona una opcion");
            opcion = sn.nextInt();
 
            switch (opcion) {
                case 1:
                    modificarEventoId();
                    break;
                    
                case 2:
                    modificarEventoNombre();
                    break;
                    
                case 3:
                    break;
            
                default:
                    System.out.println("Solo números entre 1 y 3");
            }
        } catch (InputMismatchException e) {
            System.out.println("Debes insertar un número");
            sn.next();
        }
        
    }
    
    
    /**
     * elimina una entrada de un evento 
     * @return si se elimino la entrada retorna true , y si no se elimino el evento retorna false
     * @throws IOException Error de I/O
     */
    public boolean eliminarEntrada() throws IOException{
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
     * elimina un evento 
     * @return si se elimino el evento retorna true , y si no se elimino el evento retorna false
     * @throws IOException Error de I/O
     */
    public boolean eliminarEvento() throws IOException{
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
     * opcion para comprar una entrada para un evento 
     * @param user El usuario de el cliente quiere comprar una entrada
     * @throws IOException Error de I/O
     */
    public void comprarEntrada(Cliente user) throws IOException{
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
    
    
    
    /**
     * metodo para buscar las personas que estan de cumpleaños el dia del evento 
     * @throws IOException
     */
    public void PersonasBeneficiadas() throws IOException{
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
        if(contador == 0)System.out.println("No hay personas beneficiadas");
    }
    
    //metodo para seleccionar objeto por criterio
    
    
}


