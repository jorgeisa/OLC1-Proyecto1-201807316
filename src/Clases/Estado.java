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
    private String llave;
    private ArrayList<NodoArbol> listaNodos;
    private ArrayList<Transicion> listaTransiciones;

    public Estado(String nombre, String llave, ArrayList<NodoArbol> listaNodos) {
        this.nombre = nombre;
        this.listaNodos = listaNodos;
        this.listaTransiciones = new ArrayList<>();
        this.llave = llave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }
    
    

    public ArrayList<NodoArbol> getListaNodos() {
        return listaNodos;
    }

    public void setListaNodos(ArrayList<NodoArbol> listaNodos) {
        this.listaNodos = listaNodos;
    }

    

    public ArrayList<Transicion> getListaTransiciones() {
        return listaTransiciones;
    }

    public void setListaTransiciones(ArrayList<Transicion> listaTransiciones) {
        this.listaTransiciones = listaTransiciones;
    }

    
}
