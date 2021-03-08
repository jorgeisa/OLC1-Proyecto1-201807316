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
        
        Interfaz pantallaInicial = new Interfaz();
        pantallaInicial.setVisible(true);
        
        
        /*HashMap<Integer, ArrayList<String>> mapa = new HashMap<>();
        ArrayList<String> palabras = new ArrayList<>();
        palabras.add("Hola");
        palabras.add("Como");
        palabras.add("Estas");
        palabras.add("?");
        mapa.put(1, palabras);
        
        mapa.get(1).add("PRUEBA");
        System.out.println(mapa.get(2));
        System.out.println("La direccion es esta: "+mapa.get(mapa));
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
