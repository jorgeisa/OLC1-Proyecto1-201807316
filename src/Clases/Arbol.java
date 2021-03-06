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
public class Arbol {
   private NodoArbol raiz;
   private String nombre;
   private String graphArbol;

    public Arbol(NodoArbol raiz, String nombre) {
        this.raiz = raiz;
        this.nombre = nombre;
        this.graphArbol = "";
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
    
    public String realizarGrafica(){
        String grafica = "";
        int contador = 0;
        grafica += "digraph G{\n"
                + "node[shape=\"record\"];\n";
        grafica += realizarNodos(this.raiz);
        grafica += realizarUniones(this.raiz);
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
