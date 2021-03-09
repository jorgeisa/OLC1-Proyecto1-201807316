/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Isaac
 */
public class Transicion {
    private String nombreTransicion;
    private Estado estado;

    public Transicion(String nombreTransicion, Estado estado) {
        this.nombreTransicion = nombreTransicion;
        this.estado = estado;
    }

    public String getNombreTransicion() {
        return nombreTransicion;
    }

    public void setNombreTransicion(String nombreTransicion) {
        this.nombreTransicion = nombreTransicion;
    }
    
    public Estado getEstado() {
        return estado;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
