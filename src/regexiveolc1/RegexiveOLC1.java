/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexiveolc1;

import Clases.Arbol;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import Clases.Enum;
import Clases.Excepcion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import static jdk.nashorn.internal.objects.NativeArray.map;
/**
 *
 * @author Isaac
 */
public class RegexiveOLC1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hola esto es regexive");
        
        //Interfaz pantallaInicial = new Interfaz();
        //pantallaInicial.setVisible(true);
        
        
        Stack pila = new Stack();
        String uno = "String1";
        pila.push(uno);
        pila.push("String2");
        pila.push("String3");
        pila.push("String4");
        pila.push("String5");
        pila.push("String6");
        pila.push("String7");
        pila.push("String8");
        pila.push("String9");
        
        
        System.out.println(pila.get(0));
        System.out.println(pila.lastElement());
        System.out.println(pila.capacity());
        while (!pila.empty()) {   
            if (pila.get(0).equals("String6")) {
                pila.add("Añadido");
            }
            System.out.println("**"+pila.remove(0));
        }
 
        pila.push("String1");
        pila.push("String2");
        pila.push("String3");
        pila.push("String4");
        pila.push("String5");
        pila.push("String6");
        pila.push("String7");
        pila.push("String8");
        pila.push("String9");
        System.out.println("");
        while (!pila.empty()) {            
            System.out.println("****"+pila.pop());
        }
        /*HashMap<Integer, ArrayList<String>> mapa = new HashMap<>();
        ArrayList<String> palabras = new ArrayList<>();
        ArrayList<String> palabras2 = new ArrayList<>();
        
        palabras.add("Hola");
        palabras.add("Como");
        palabras.add("Estas");
        palabras.add("?");
        
        palabras2.add("Hola2");
        palabras2.add("Como2");
        palabras2.add("Estas2");
        palabras2.add("?2");
        mapa.put(10, palabras);
        
        /*
        ArrayList<String> palabras = new ArrayList<>();
        palabras.add("Palabra1");
        palabras.add("Palabra2");
        palabras.add("Palabra3");
        palabras.add("Palabra4");
        
        for (String palabra : palabras) {
            System.out.println(palabra);
        } */
        //Excepcion exc = new Excepcion(Enum.LEXICO, "Error Lexico", 0, 0);
        //System.out.println(exc.getTipo().ordinal());
        //interpretar("entrada4D.olc");
    }
    
    private static void interpretar(String path) {
        Analizador.Sintactico parse;
        //Analizador.Lexico scanner;
        try {
            //scanner = new Analizador.Lexico(new BufferedReader(new FileReader(path))); //separado
            parse = new Analizador.Sintactico(new Analizador.Lexico(new BufferedReader(new FileReader(path))));//junto
            parse.parse(); 
            System.out.println("\n********************ARBOLES********************");
            for (Arbol Arbol : parse.Arboles) {
                System.out.println(Arbol.getNombre() + "\n");
                Arbol.recorrerArbol();
                System.out.println("\n\n\n");
                
                System.out.println(Arbol.realizarGrafica());
                System.out.println("\n\n******************************************");
            }
        } catch (Exception ex) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println("Causa: "+ex.getCause());
        } 
    }
}
