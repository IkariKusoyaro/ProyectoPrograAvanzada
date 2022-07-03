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
public class InvalidDataTypeException extends Exception{
    
    public InvalidDataTypeException (){
        super("El tipo de dato ingresado no es valido");
    }
}
