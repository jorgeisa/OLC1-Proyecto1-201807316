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
public class Arbol {
   private NodoArbol raiz;
   private String nombre;
   private String graphArbol;
   private HashMap<Integer, ArrayList<NodoArbol>> siguientes;
   
   private HashMap<String, ArrayList<Estado>> estados;

    public Arbol(NodoArbol raiz, String nombre, HashMap<Integer, ArrayList<NodoArbol>> siguientes) {
        this.raiz = raiz;
        this.nombre = nombre;
        this.graphArbol = "";
        this.siguientes = siguientes;
        this.estados = new HashMap<>();
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGraphArbol() {
        return graphArbol;
    }

    public void setGraphArbol(String graphArbol) {
        this.graphArbol = graphArbol;
    }

    public HashMap<Integer, ArrayList<NodoArbol>> getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(HashMap<Integer, ArrayList<NodoArbol>> siguientes) {
        this.siguientes = siguientes;
    }

    public HashMap<String, ArrayList<Estado>> getEstados() {
        return estados;
    }

    public void setEstados(HashMap<String, ArrayList<Estado>> estados) {
        this.estados = estados;
    }
    
    public String realizarGrafica(){
        String grafica = "";
        grafica += "digraph G{\n"
                + "node[shape=\"record\"];\n";
        grafica += realizarNodos(this.raiz);
        grafica += realizarUniones(this.raiz);
        grafica += realizarSiguientes();
        grafica += "}";
        return grafica;
    }
    
    private String realizarNodos(NodoArbol temp){
        String grafica = "";
        if(temp != null){
            // Evaluamos si es anulable o no para graficar A o N
            String anulable = temp.isAnulable()?"A":"N";
            
            // Identificamos si es hoja o no para graficar id 0 o id hoja
            int idNodo = temp.isEvaluarHoja()?temp.getIdNodo():0;
            
            String primerosS = "";
            String ultimosS = "";
            
            // Concatenamos los primeros
            for (int i = 0; i < temp.getPrimeros().size(); i++) {
                primerosS += (i==temp.getPrimeros().size()-1)?temp.getPrimeros().get(i).getIdNodo()+"":temp.getPrimeros().get(i).getIdNodo()+",";
            }
            
            // Concatenamos los ultimos
            for (int i = 0; i < temp.getUltimos().size(); i++) {
                ultimosS += (i==temp.getUltimos().size()-1)?temp.getUltimos().get(i).getIdNodo()+"":temp.getUltimos().get(i).getIdNodo()+",";
            }
            
            // Evaluamos si no viene un caracter especial
            if("|".equals(temp.getInterior()) || "\\n".equals(temp.getInterior())){
                grafica += "nodo"+temp.getIdNodo()+"[label=\"{"+anulable+"|"+primerosS+"}|\\"+temp.getInterior()+"\\n|{{"+idNodo+"}|{"+ultimosS+"}}\"];\n";
            }else{
                grafica += "nodo"+temp.getIdNodo()+"[label=\"{"+anulable+"|"+primerosS+"}|"+temp.getInterior()+"\\n|{{"+idNodo+"}|{"+ultimosS+"}}\"];\n";
            }
            grafica += realizarNodos(temp.getIzquierda());
            grafica += realizarNodos(temp.getDerecha());
        }
        return grafica;
    }
    
    private String realizarUniones(NodoArbol temp){
        String grafica = "";
        if(temp != null){
            if(temp.getDerecha() != null){
                grafica += "nodo"+temp.getIdNodo()+"->nodo"+temp.getDerecha().getIdNodo()+";\n";
            }
            if(temp.getIzquierda()!= null){
                grafica += "nodo"+temp.getIdNodo()+"->nodo"+temp.getIzquierda().getIdNodo()+";\n";
            }
            grafica += realizarUniones(temp.getIzquierda());
            grafica += realizarUniones(temp.getDerecha());
        }
        return grafica;
    }
    
    private String realizarSiguientes(){
        String graphLlaves = "{Nodo|";
        String graphValores = "{{Siguientes}|";
        String retorno = "\n\nsubgraph F{\n" +
                         "node[shape=\"record\"];\n";
        //"node1" [label = "{Nodo|1|2|3|4|5}|{{Nodo}|{Nodo}|{Nodo}|{Nodo}|{Nodo}|{Nodo}}"];
        
        int contador = 0;
        
        for (Map.Entry<Integer, ArrayList<NodoArbol>> nodoFollows : siguientes.entrySet()) {
            int key = nodoFollows.getKey();
            if (contador != siguientes.size()-1){
                graphLlaves += key + "|";
                graphValores += "{";
            }else{
                graphLlaves += key + "}";
            }
            
            ArrayList<NodoArbol> arrayListNode = nodoFollows.getValue();
            
            for (int i = 0; i < arrayListNode.size(); i++) {
                if (i != arrayListNode.size()-1) {
                    graphValores += arrayListNode.get(i).getIdNodo() + ",";
                }else{
                    graphValores += arrayListNode.get(i).getIdNodo()+"}";
                }
            }
            
            if (contador != siguientes.size()-1){
                graphValores += "|";
            }else{
                graphValores += "}";
            }
            contador++;
        }
        retorno += "nodo8000[label=\""+graphLlaves+"|"+graphValores+"\"];";
        retorno += "}";
        return retorno;
    }
    
    private void generarEstados(){
        
    }
    
    
    
    public void recorrerArbol(){
        recorrerArbol(this.raiz);
    }
    
    private void recorrerArbol(NodoArbol temp){
        if(temp != null){
            System.out.println(temp.getInterior());
            System.out.println(temp.getIdNodo());
            recorrerArbol(temp.getIzquierda());
            recorrerArbol(temp.getDerecha());
            
        }
    }
}
