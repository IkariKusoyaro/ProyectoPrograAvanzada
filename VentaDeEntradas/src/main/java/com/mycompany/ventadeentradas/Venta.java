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
public interface Venta {


    public boolean verificarDescuento(int dia, int mes);

    public int generarDescuento(int precio);
}
