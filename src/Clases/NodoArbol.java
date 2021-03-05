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
public class NodoArbol {
    private int idNodo;
    private String interior;
    private NodoArbol derecha;
    private NodoArbol izquierda;
    private boolean anulable;
    
    private ArrayList<NodoArbol> primeros;
    private ArrayList<NodoArbol> ultimos;
    private ArrayList<NodoArbol> siguientes;

    // Nodo de las hojas
    public NodoArbol(int idNodo, String interior, HashMap<Integer, ArrayList<NodoArbol>> hojas) {
        this.idNodo = idNodo;
        this.interior = interior;
        this.derecha = null;
        this.izquierda = null;
        this.anulable = false;
        
        this.primeros = new ArrayList<>();
        this.ultimos = new ArrayList<>();
        this.siguientes = new ArrayList<>();
        this.primeros.add(this);
        this.ultimos.add(this);
        this.siguientes = new ArrayList<>();
        
        hojas.put(idNodo, new ArrayList<>()); //Agrego la hoja con un array
        
    }

    // Nodo que no sea una hoja
    public NodoArbol(String interior, NodoArbol derecha, NodoArbol izquierda, HashMap<Integer, ArrayList<NodoArbol>> hojas) {
        this.idNodo = -1;
        this.interior = interior;
        this.derecha = derecha;
        this.izquierda = izquierda;
        
        this.primeros = new ArrayList<>();
        this.ultimos = new ArrayList<>();
        
        //Tenemos qu evaluar la anulabilidad
        // hojas ya deben estar con un ID <Id hoja, arraySiguientes> 
        evaluarAnulable(interior, hojas);
    }
    
    public void evaluarAnulable(String interior, HashMap<Integer, ArrayList<NodoArbol>> hojas){
        switch(interior){
            case ".":
            //Concateacion
                this.anulable = this.izquierda.isAnulable() && this.derecha.isAnulable();
                //Establecemos los primeros
                if (this.izquierda.isAnulable()) {
                    this.primeros.addAll(this.izquierda.getPrimeros());
                    this.primeros.addAll(this.derecha.getPrimeros());
                }else{
                    this.primeros.addAll(this.derecha.getPrimeros());
                }
                //Establecemos los ultimos
                if (this.derecha.isAnulable()) {
                    this.ultimos.addAll(this.izquierda.getUltimos());
                    this.ultimos.addAll(this.derecha.getUltimos());
                }else{
                    this.ultimos = this.derecha.getUltimos();
                }
                
                //establecemos los siguientes
            case "|":
            //Disyuncion
                this.anulable = this.izquierda.isAnulable() || this.derecha.isAnulable();
                //Establecemos los primeros
                this.primeros.addAll(this.izquierda.getPrimeros());
                this.primeros.addAll(this.derecha.getPrimeros());
                //Establecemos los ultimos
                this.ultimos.addAll(this.izquierda.getUltimos());
                this.ultimos.addAll(this.derecha.getUltimos());
                
                //establecemos los siguientes
                
            case "*":
            // cero o mas veces
                this.anulable = true;
                //Establecemos los primeros
                this.primeros = this.izquierda.getPrimeros();
                //Establecemos los ultimos
                this.ultimos = this.izquierda.getUltimos();
                
                //establecemos los siguientes
                
            case "+":
            // una o mas veces
                this.anulable = this.izquierda.isAnulable();
                //Establecemos los primeros
                this.primeros = this.izquierda.getPrimeros();
                //Establecemos los ultimos
                this.ultimos = this.izquierda.getUltimos();
                
                //establecemos los siguientes
                
            case "?":
            // cero o una vez
                this.anulable = true;
                //Establecemos los primeros
                this.primeros = this.izquierda.getPrimeros();
                //Establecemos los ultimos
                this.ultimos = this.izquierda.getUltimos();
                
                //establecemos los siguientes
        }
    }
    
    

    public int getIdNodo() {
        return idNodo;
    }

    public void setIdNodo(int idNodo) {
        this.idNodo = idNodo;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public NodoArbol getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoArbol derecha) {
        this.derecha = derecha;
    }

    public NodoArbol getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoArbol izquierda) {
        this.izquierda = izquierda;
    }

    public boolean isAnulable() {
        return anulable;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }

    public ArrayList<NodoArbol> getPrimeros() {
        return primeros;
    }

    public void setPrimeros(ArrayList<NodoArbol> primeros) {
        this.primeros = primeros;
    }

    public ArrayList<NodoArbol> getUltimos() {
        return ultimos;
    }

    public void setUltimos(ArrayList<NodoArbol> ultimos) {
        this.ultimos = ultimos;
    }

    public ArrayList<NodoArbol> getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(ArrayList<NodoArbol> siguientes) {
        this.siguientes = siguientes;
    }
    
    
    
    
    
}
