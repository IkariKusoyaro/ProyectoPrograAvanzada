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
public class Cliente extends Persona implements Venta {
    private int dia;
    private int mes;
    private int anio;
    private int presupuesto;

    public Cliente(String rut, String nombre, String contrasenia, int dia, int mes, int anio , int presupuesto) {
        super(rut, nombre, contrasenia);
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.presupuesto = presupuesto;
    }

    /**
     *
     * @param lineaPersona
     * @param archivoPersonas
     */
    public Cliente(String lineaPersona, CSV archivoPersonas) {
        super(archivoPersonas.get_csvField(lineaPersona,1),archivoPersonas.get_csvField(lineaPersona,2),archivoPersonas.get_csvField(lineaPersona,6));
        dia = Integer.parseInt(archivoPersonas.get_csvField(lineaPersona,3));
        mes = Integer.parseInt(archivoPersonas.get_csvField(lineaPersona,4));
        anio = Integer.parseInt(archivoPersonas.get_csvField(lineaPersona,5));
        presupuesto = Integer.parseInt(archivoPersonas.get_csvField(lineaPersona,7));
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

    public int getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }
    
    

    @Override
    public int generarDescuento(int precio) {
        int descuento = 0;
        if(this.dia == dia && this.mes == mes){
            descuento = (precio*15)/100;
        }
        return descuento;
    }

    @Override
    public boolean verificarDescuento(int dia, int mes) {
        return (this.dia == dia && this.mes == mes);
    }

    @Override
    public void mostrarPersona() {
        System.out.println("Nombre: " + getNombre() + " - Rut: "+ getRut());
        System.out.println("Fecha de Naciemento: "+ dia + "/" + mes + "/" + anio);
        System.out.println("Prespuesto: " + presupuesto);
    }
    
}
