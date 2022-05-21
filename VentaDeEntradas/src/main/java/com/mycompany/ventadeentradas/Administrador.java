/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ventadeentradas;

/**
 *
 * @author TioPanxo
 */
public class Administrador extends Persona{
    
    int dia;
    int mes;
    int anio;
    
    public Administrador(String rut, String nombre, String contrasenia, int dia, int mes, int anio) {
        super(rut, nombre, contrasenia);
        this.dia = dia; 
        this.mes = mes;
        this.anio = anio;
    }

    public Administrador(String lineaPersona, CSV archivoPersonas) {
        super(archivoPersonas.get_csvField(lineaPersona,1),archivoPersonas.get_csvField(lineaPersona,2),archivoPersonas.get_csvField(lineaPersona,6));
        dia = Integer.parseInt(archivoPersonas.get_csvField(lineaPersona,3));
        mes = Integer.parseInt(archivoPersonas.get_csvField(lineaPersona,4));
        anio = Integer.parseInt(archivoPersonas.get_csvField(lineaPersona,5));
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

    @Override
    public void mostrarPersona() {
        System.out.println("Nombre: " + getNombre() + " - Rut: "+ getRut());
        System.out.println("Fecha de Naciemento: "+ dia + "/" + mes + "/" + anio);
    }
    
    
}
