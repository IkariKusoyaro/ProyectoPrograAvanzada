package com.mycompany.ventadeentradas;




/**
 *
 * @author Camilo
 */
public class Entrada {
    private int numSerie;
    private int numAsiento;
    private String rutPersona;  //persona a la que pertenece la entrada, si es 0
                                //la entrada no esta asignadaa
    private int idEvento;  //evento al que pertenece la entrada
    
    
    //constructor por parametros
    public Entrada(int numSerie, int numAsiento, String rutPersona, int idEvento){
        this.numSerie = numSerie;
        this.idEvento = idEvento;
        this.numAsiento = numAsiento;
        this.rutPersona = rutPersona;
    }


    //constructor por String
    public Entrada(CSV archivo, String linea){
        numSerie = Integer.parseInt(archivo.get_csvField(linea,0));
        idEvento = Integer.parseInt(archivo.get_csvField(linea, 3));
        numAsiento = Integer.parseInt(archivo.get_csvField(linea,1));
        rutPersona = archivo.get_csvField(linea, 2);
    }

    
    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(int numSerie) {
        this.numSerie = numSerie;
    }

    public int getNumAsiento() {
        return numAsiento;
    }

    public void setNumAsiento(int numAsiento) {
        this.numAsiento = numAsiento;
    }

    public String getRutPersona() {
        return rutPersona;
    }

    public void setRutPersona(String rutPersona) {
        this.rutPersona = rutPersona;
    }

    //metodos
     /**
     * Mostrar por pantalla las entradas
     * @param un string de evento
     */
    public void mostrarEntrada(String evento){
        System.out.println("Numero de Serie: "  + numSerie);
        System.out.println("Evento: " + evento + " - Asiento: "+ numAsiento);
        if("0".equals(rutPersona)){
            System.out.println("DISPONIBLE");
            
        }
        else{
            System.out.println("NO DISPONIBLE");
        }
        
    }
    
}
