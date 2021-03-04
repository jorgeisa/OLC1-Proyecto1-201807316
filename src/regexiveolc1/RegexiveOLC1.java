/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexiveolc1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import Clases.Enum;
import Clases.Excepcion;
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
        //Excepcion exc = new Excepcion(Enum.LEXICO, "Error Lexico", 0, 0);
        //System.out.println(exc.getTipo().ordinal());
        interpretar("Entrada3M.txt");
    }
    
    private static void interpretar(String path) {
        Analizador.Sintactico parse;
        try {
            parse = new Analizador.Sintactico(new Analizador.Lexico(new BufferedReader(new FileReader(path))));
            parse.parse();        
        } catch (Exception ex) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println("Causa: "+ex.getCause());
        } 
    }
}
