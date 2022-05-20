package com.mycompany.ventadeentradas;





/**
 *
 * @author Camilo
 */
abstract class Persona {
    private String rut;
    private String nombre;
    private String contrasenia;      //como la hago inaccesible?
    

    
    public Persona(String rut, String nombre, String contrasenia) {
        this.rut = rut;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
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
    
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    
    

}
