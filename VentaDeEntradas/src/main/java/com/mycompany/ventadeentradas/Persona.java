package com.mycompany.ventadeentradas;
import java.util.*;





/**
 *
 * @author Camilo
 */
public class Persona {
    private String rut;
    private String nombre;
    private int dia;
    private int mes;
    private int anio;
    private String contrasenia;      //como la hago inaccesible?
    private int presupuesto;
    private ArrayList<String> listaMusical;     //lista con los generos musicales favoritos de la persona

    
    public Persona(String rut, String nombre, int dia, int mes, int anio, String contrasenia, int presupuesto) {
        this.rut = rut;
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.contrasenia = contrasenia;
        this.presupuesto = presupuesto;
        listaMusical = new ArrayList();
        
    }
    
    
    public Persona(String nombre, int dia, int mes, int anio, String contrasenia, int presupuesto, ArrayList lista) {
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.contrasenia = contrasenia;
        this.presupuesto = presupuesto;
        listaMusical = new ArrayList();
        listaMusical.addAll(lista);
    }
    
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }
}
