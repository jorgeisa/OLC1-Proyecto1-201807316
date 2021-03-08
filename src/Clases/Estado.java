/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Isaac
 */
public class Estado {
    private String nombre;
    private ArrayList<String> listaNodos;
    private ArrayList<Transicion> listaTransiciones;

    public Estado(String nombre, ArrayList<String> listaNodos, ArrayList<Transicion> listaTransiciones) {
        this.nombre = nombre;
        this.listaNodos = listaNodos;
        this.listaTransiciones = listaTransiciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getListaNodos() {
        return listaNodos;
    }

    public void setListaNodos(ArrayList<String> listaNodos) {
        this.listaNodos = listaNodos;
    }

    public ArrayList<Transicion> getListaTransiciones() {
        return listaTransiciones;
    }

    public void setListaTransiciones(ArrayList<Transicion> listaTransiciones) {
        this.listaTransiciones = listaTransiciones;
    }
}
