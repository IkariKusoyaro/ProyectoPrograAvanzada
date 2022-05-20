/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ventadeentradas;

import java.util.*;

/**
 *
 * @author TioPanxo
 */
public class Cliente extends Persona implements Venta {
    private int dia;
    private int mes;
    private int anio;
    private ArrayList<String> listaMusical;     //lista con los generos musicales favoritos de la persona
    private int presupuesto;

    public Cliente(String rut, String nombre, String contrasenia, int dia, int mes, int anio , ArrayList listaMusical, int presupuesto) {
        super(rut, nombre, contrasenia);
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.presupuesto = presupuesto;
        listaMusical = new ArrayList<String>();
    }
    

    @Override
    public int generarDescuento(int precio, float descuento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
