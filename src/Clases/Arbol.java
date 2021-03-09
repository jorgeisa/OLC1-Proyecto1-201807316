/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
/**
 *
 * @author Isaac
 */
public class Arbol {
   private NodoArbol raiz;
   private String nombre;
   private String graphArbol;
   private HashMap<Integer, ArrayList<NodoArbol>> siguientes;
   
   private HashMap<String, Estado> estados;
   private ArrayList<String> terminales;
   private int contadorEstados;
   private Stack pilaEstados;

    public Arbol(NodoArbol raiz, String nombre, HashMap<Integer, ArrayList<NodoArbol>> siguientes, ArrayList<String> terminales) {
        this.raiz = raiz;
        this.nombre = nombre;
        this.graphArbol = "";
        this.siguientes = siguientes;
        this.estados = new HashMap<>();
        this.terminales =  terminales;
        this.contadorEstados = 0;
        this.pilaEstados = new Stack();
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

    public HashMap<String, Estado> getEstados() {
        return estados;
    }

    public void setEstados(HashMap<String, Estado> estados) {
        this.estados = estados;
    }

    public ArrayList<String> getTerminales() {
        return terminales;
    }

    public void setTerminales(ArrayList<String> terminales) {
        this.terminales = terminales;
    }

    public int getContadorEstados() {
        return contadorEstados;
    }

    public void setContadorEstados(int contadorEstados) {
        this.contadorEstados = contadorEstados;
    }

    public Stack getPilaEstados() {
        return pilaEstados;
    }

    public void setPilaEstados(Stack pilaEstados) {
        this.pilaEstados = pilaEstados;
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
    
    // METODOS PARA GRAFICAR
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
    
    public String realizarGraficaTransiciones(){
        return this.realizarTransiciones();
    }
    
    private String realizarTransiciones(){
        String graphTransiciones = "";
        graphTransiciones += "digraph G {\n" +
                             "\n" +
                             "node [shape=record];\n" +
                             "rankdir = \"TB\";\n"+
                             "b [label = \n\"";
        
        // COLUMNA DE ESTADOS
        String columnaEstados = "|{Estados|";
        int contador = 0;
        for (Map.Entry<String, Estado> estado : this.estados.entrySet()) {
            String key = estado.getKey();
            Estado val = estado.getValue();
            if (contador != this.estados.size()-1) {
                columnaEstados += val.getNombre()+"-["+key+"]"+"|";
            }else{
                columnaEstados += val.getNombre()+"-["+key+"]"+"}|";
            }
            contador++;
        }
        
        contador = 0;
        String columnasTerminales = "";
        // COLUMNA DE TERMINALES
        for (String terminal : this.terminales) {
            columnasTerminales += terminal.equals("\\n")?"{\\" + terminal + "|":"{" + terminal + "|";
            
            int contadorEstados = 0;
            for (Map.Entry<String, Estado> estado : estados.entrySet()) {
                String key = estado.getKey();
                Estado val = estado.getValue();
                ArrayList<Transicion> transiciones = val.getListaTransiciones();
                for (Transicion transicion : transiciones) {
                    if (transicion.getNombreTransicion().equals(terminal)) {
                        String nombreEstado = transicion.getEstado().getNombre();
                        
                        if (contadorEstados != this.estados.size()-1) {
                            columnasTerminales += nombreEstado+"|";
                        }else{
                            columnasTerminales += nombreEstado+"}|";
                        }
                    }
                }
                contadorEstados++;
            }
            contador++;
        }
        columnasTerminales += "\"];\n" +
                              "}";
        
        graphTransiciones += columnaEstados + columnasTerminales;
        return graphTransiciones;
    }
    
    public String realizarGraficaAFD(){
        return this.realizarAfd();
    }
    
    private String realizarAfd(){
        String graphAfd ="";
        String nodosAceptacion = "";
        String estadosUniones = "";
        int cuentaEstados = 0;
        
        
        for (Map.Entry<String, Estado> Estado : this.estados.entrySet()) {
            String key = Estado.getKey();
            Estado val = Estado.getValue();
            
            // Establecer estados de aceptacion
            if (val.isEstadoAceptacion()) {
                if (cuentaEstados != this.estados.size()-1) {
                    nodosAceptacion+=val.getNombre()+",";
                }else{
                    nodosAceptacion+=val.getNombre()+";";
                }
            }
            
            for (Transicion transicion : val.getListaTransiciones()) {
                if (!transicion.getEstado().getNombre().equals("SINV")) {
                    
                    estadosUniones += val.getNombre() +"->"+ transicion.getEstado().getNombre();
                    if (transicion.getNombreTransicion().equals("\\n")) {
                        estadosUniones += " [label = \"\\"+ transicion.getNombreTransicion() +"\" ];\n";
                    }else if (transicion.getNombreTransicion().equals(" ")) {
                        estadosUniones += " [label = \"\\\" \\\"\" ];\n";
                    }else{
                        estadosUniones += " [label = \""+ transicion.getNombreTransicion() +"\" ];\n";
                    }
                }
            }
            
            cuentaEstados++;
        }
        
        graphAfd +="digraph G{\n"+
                 "node [shape = doublecircle]; " + nodosAceptacion + "\n"+
                 "node [shape = circle];\n" +
                 "rankdir=LR;\n"+
                 estadosUniones+
                 "}";
                 
        return graphAfd;
    }
    
    // METODOS PARA GENERAR DATOS (TRANSICIONES, ESTADOS)
    public void generarEstados(){
        this.generandoPrimerEstado(); // El primer estado se genhera por la primero de la Raiz
        this.generarDemasEstados();
        this.imprimirTransiciones();
    }
    
    private void generandoPrimerEstado(){
        //Estados iniciales
        String primerosNodos = "";
        boolean estado=false;
        int idAceptacion = this.raiz.getDerecha().getIdNodo();
        for (int i = 0; i < this.raiz.getPrimeros().size(); i++) {
            if (i != this.raiz.getPrimeros().size()-1) {
                primerosNodos += this.raiz.getPrimeros().get(i).getIdNodo()+",";
            }else{
                primerosNodos += this.raiz.getPrimeros().get(i).getIdNodo();
            }
            
            //Comparacion para ver si es un estado de aceptacion
            int idNodoActual = this.raiz.getPrimeros().get(i).getIdNodo();
            if ( idNodoActual == idAceptacion) {
                estado = true;
            }
        }
        
        Estado estadoUno = new Estado("S"+this.contadorEstados, primerosNodos, this.raiz.getPrimeros(), estado);
        this.contadorEstados++;
        this.estados.put(primerosNodos, estadoUno);
        pilaEstados.add(estadoUno);
    }
    
    private void generarDemasEstados(){
        int idNodoAceptacion = this.raiz.getDerecha().getIdNodo();
        while(!pilaEstados.isEmpty()){
            // Al remover pila.remove(0)
            // Al hacer un add se debe de realizar pila.add(object);
            Estado estadoActual = (Estado)pilaEstados.get(0);
            ArrayList<NodoArbol>  listaNodos = estadoActual.getListaNodos();
            System.out.println("Estado De Pila: "+estadoActual.getNombre());
            
            // Para cada uno de los terminales
            for (int i = 0; i < terminales.size(); i++) {
                String terminalActual = terminales.get(i);
                ArrayList<NodoArbol> nodosTemporales = new ArrayList<>();
                // Evaluamos para cada uno de los nodos del estado
                for (int j = 0; j < listaNodos.size(); j++) {
                    String interiorNodo = listaNodos.get(j).getInterior();
                    // Preguntamos si el interior del nodo es igual al terminal
                    // Si es asi, entonces hay una transicion con ese terminal
                    if (terminalActual.equals(interiorNodo)) {
                        int idNodoActual = listaNodos.get(j).getIdNodo();
                        ArrayList<NodoArbol> nodosSiguientesActuales = this.siguientes.get(idNodoActual);
                        //Verificar si un nodo de la lista de siguientes
                        //No exista en el temporal
                        for (NodoArbol nodoSiguiente : nodosSiguientesActuales) {
                            if (!nodosTemporales.contains(nodoSiguiente)) {
                                nodosTemporales.add(nodoSiguiente);
                            }
                        }
                    }
                }
                
                boolean estadoAceptacion = false;
                // Hago la llave de mi hashmap
                String nodosEstadoNuevo = "";
                for (int z = 0; z< nodosTemporales.size(); z++) {
                    if (z != nodosTemporales.size()-1) {
                        nodosEstadoNuevo += nodosTemporales.get(z).getIdNodo()+",";
                    }else{
                        nodosEstadoNuevo += nodosTemporales.get(z).getIdNodo();
                    }
                    
                    int idNodoActual = nodosTemporales.get(z).getIdNodo();
                    if (idNodoActual == idNodoAceptacion) {
                        estadoAceptacion = true;
                    }
                }
                System.out.println("--------------------NODOS TEMPORALES----------------");
                System.out.println(nodosEstadoNuevo);
                
                // Evaluo si es un estado valido o no lo es
                if (!nodosEstadoNuevo.equals("")) {
                    // Evaluo si el estado ya existe o es uno nuevo con la llave realizada
                    if (!estados.containsKey(nodosEstadoNuevo)) {

                        Estado estadoNuevo = new Estado("S"+this.contadorEstados, nodosEstadoNuevo, nodosTemporales, estadoAceptacion);
                        Transicion nuevaTransicion = new Transicion(terminalActual, estadoNuevo);
                        estados.put(nodosEstadoNuevo, estadoNuevo);
                        estados.get(estadoActual.getLlave()).getListaTransiciones().add(nuevaTransicion);

                        pilaEstados.add(estadoNuevo);
                        System.out.println("Se agrega nuevo estado como nueva transicion al estado actual");
                        this.contadorEstados++;
                    }else{
                        Estado estadoExistente = estados.get(nodosEstadoNuevo);
                        Transicion nuevaTransicion = new Transicion(terminalActual, estadoExistente);
                        estados.get(estadoActual.getLlave()).getListaTransiciones().add(nuevaTransicion);
                        System.out.println("Se agrega el mismo estado como nueva transicion");
                    } 
                }else{
                    Estado estadoNoExistente = new Estado("SINV", "SINV", nodosTemporales, false);
                    Transicion nuevaTransicion = new Transicion(terminalActual, estadoNoExistente);
                    estados.get(estadoActual.getLlave()).getListaTransiciones().add(nuevaTransicion);
                    System.out.println("Se agrega al estado actual una transicion con estado invalido");
                }
            }
            pilaEstados.remove(0);
        }
    }
    
    // METODOS DE IMPRESION DE DATOS
    private void imprimirTransiciones(){
        System.out.println("--------------TRANSICIIONES--------------------");
        for (Map.Entry<String, Estado> estado : estados.entrySet()) {
            String key = estado.getKey();
            Estado val = estado.getValue();
            System.out.println("\n*****Estado Actual: "+val.getNombre()+", Llave Asociada: "+key+", Aceptacion: "+val.isEstadoAceptacion()+"*****");
            System.out.println("\nTransiciones Del Estado: ");
            for (Transicion transicion : val.getListaTransiciones()) {
                System.out.println("\tNombre (terminal) Transicion: "+transicion.getNombreTransicion());
                System.out.println("\tNombre Estado: "+transicion.getEstado().getNombre());
            }
        }
        System.out.println("-----------------------------------------------");
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
