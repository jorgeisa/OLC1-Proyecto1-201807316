/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 *
 * @author Isaac
 */
public class Reportes {
    
    public String verificarExistenciaPath(String nombreCarpeta, boolean reporteError){
        //Realizar el archivo en la ruta por defecto
        String pathDefecto = new File(nombreCarpeta).getAbsolutePath();
        //Verificar si el path esta creado
        if (new File(pathDefecto).mkdirs()) {
            System.out.println("Se creo el directorio: " + pathDefecto);
            if (reporteError) {
                generarCssTabla();
            }
        }
        return pathDefecto;
    }
    
    // REPORTE DE ERRORES - HTML

    public void reporteErrores(ArrayList<Excepcion> erroresL, ArrayList<Excepcion> erroresS, String nombreArchivo){
        String pathDefecto = verificarExistenciaPath("ERRORES_201807316", true);
        
        //Quitarle el punto al nombre por defecto del archivo
        nombreArchivo = nombreArchivo.replace(".", "_") + ".html";
        System.out.println("La carpeta es: " + pathDefecto + " y el nombre sera "+ nombreArchivo);
        
        //Generar archivo y escribir en el html
        try {
            File archivoReportes = new File("ERRORES_201807316\\"+nombreArchivo);
            BufferedWriter salida = new BufferedWriter(new FileWriter(archivoReportes));
            String interiorHtml = generarString(erroresL, erroresS);
            salida.write(interiorHtml);
            salida.close();
        } catch (Exception e) {
            System.out.println("ERROR AL CREAR HTML DE REPORTE ERRORES");
        }
    }
    
    private String generarString(ArrayList<Excepcion> erroresL, ArrayList<Excepcion> erroresS){
        String interiorHtml = "";
        int contadorErrores = 0;
        interiorHtml += "<!DOCTYPE html>\n" +
                        "<html lang = \"en\">\n" +
                        "<head>\n" +
                        "<meta charset = \"UTF-8\">\n" +
                        "<title> Proyecto 1 OLC1 Regexive - 201807316 </title>\n" +
                        "<link rel = \"stylesheet\" href = \"tabla.css\">\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<div id= \"main-container\">\n" +
                        "<table>\n"+
                        "<thead>\n" +
                        "<tr>\n" +
                        "<th>No.</th><th>Tipo</th><th>Descripcion</th><th>Linea</th><th>Columna</th>\n" +
                        "</tr>\n" +
                        "</thead>\n";
        
        for (Excepcion errorLexico : erroresL) {
            contadorErrores++;
            interiorHtml += "<tr>\n";
            interiorHtml +=  "<td>"+contadorErrores+"</td>"+
                             "<td>"+ errorLexico.getTipo()+"</td>"+
                             "<td>"+errorLexico.getDescripcion()+"</td>"+
                             "<td>"+errorLexico.getFila()+"</td>"+
                             "<td>"+errorLexico.getColumna()+"</td>\n";
            interiorHtml += "</tr>\n";
        }
        
        for (Excepcion errorSintactico : erroresS) {
            contadorErrores++;
            interiorHtml += "<tr>\n";
            interiorHtml +=  "<td>"+contadorErrores+"</td>"+
                             "<td>"+ errorSintactico.getTipo()+"</td>"+
                             "<td>"+errorSintactico.getDescripcion()+"</td>"+
                             "<td>"+errorSintactico.getFila()+"</td>"+
                             "<td>"+errorSintactico.getColumna()+"</td>\n";
            interiorHtml += "</tr>\n";
        }
        
        interiorHtml += "</table>\n" +
                        "</div>\n" +
                        "</body>\n" +
                        "</html>";
        
        return interiorHtml;
    }
    
    private void generarCssTabla(){
        try {
            File archivoCss = new File("ERRORES_201807316\\tabla.css");
            BufferedWriter salida = new BufferedWriter(new FileWriter(archivoCss));
            String interiorCss = "/*\n" +
                                    "	Color fondo: #632432;\n" +
                                    "	Color header: 246355;\n" +
                                    "	Color borde: 0F362D;\n" +
                                    "	Color iluminado: 369681;\n" +
                                    "*/\n" +
                                    "body{\n" +
                                    "	background-color: #632432;\n" +
                                    "	font-family: Arial;\n" +
                                    "}\n" +
                                    "\n" +
                                    "#main-container{\n" +
                                    "	margin: 150px auto;\n" +
                                    "	width: 600px;\n" +
                                    "}\n" +
                                    "\n" +
                                    "table{\n" +
                                    "	background-color: white;\n" +
                                    "	text-align: left;\n" +
                                    "	border-collapse: collapse;\n" +
                                    "	width: 100%;\n" +
                                    "}\n" +
                                    "\n" +
                                    "th, td{\n" +
                                    "	padding: 20px;\n" +
                                    "}\n" +
                                    "\n" +
                                    "thead{\n" +
                                    "	background-color: #246355;\n" +
                                    "	border-bottom: solid 5px #0F362D;\n" +
                                    "	color: white;\n" +
                                    "}\n" +
                                    "\n" +
                                    "tr:nth-child(even){\n" +
                                    "	background-color: #ddd;\n" +
                                    "}\n" +
                                    "\n" +
                                    "tr:hover td{\n" +
                                    "	background-color: #369681;\n" +
                                    "	color: white;\n" +
                                    "}";
            salida.write(interiorCss);
            salida.close();
        } catch (Exception e) {
            System.out.println("Error al crear Css tabla");
        }
        
    }
    
    //REPORTES PARA TODOS LOS ARBOLES
    public void reportesArboles(ArrayList<Arbol> listaArboles){
        
        String pathArboles = this.verificarExistenciaPath("ARBOLES_201807316", false);
        String pathSiguientes = this.verificarExistenciaPath("SIGUIENTES_201807316", false);
        String pathTransiciones = this.verificarExistenciaPath("TRANSICIONES_201807316", false);
        String pathAFDs = this.verificarExistenciaPath("AFD_201807316", false);
        
        for (Arbol arbol : listaArboles) {
            arbol.generarEstados();
            this.reporteGraficaArbol(arbol, pathArboles);
            this.reporteGraficaSiguientes(arbol, pathSiguientes);
            this.reporteGraficaTransiciones(arbol, pathTransiciones);
            this.reporteGraficaAFD(arbol, pathAFDs);
        }
    }
    
    // REPORTE ARBOLES - PNG, TXT
    public void reporteGraficaArbol(Arbol arbol, String pathArboles){
        System.out.println("");
        System.out.println("Arbol"+arbol.getNombre());
            
        String nombreTxt = arbol.getNombre() + ".txt";
        String nombrePng = arbol.getNombre() + ".png";
        String graficaArbol = arbol.realizarGrafica();
            
            
        // Creamos el archivo .txt para cada arbol
        // Creamos el png con ese archivo txt
        try {
            String dirDot = pathArboles + "\\" + nombreTxt;
            String dirPng = pathArboles + "\\" + nombrePng;
                
            PrintWriter writer = new PrintWriter(dirDot, "UTF-8");
            writer.print(graficaArbol);
            writer.close();
                
            String cmd = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot " + "-Tpng " + "-o " + " " + dirPng + " " + dirDot;
            java.lang.Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
            System.out.println("Se creo el archivo de arbol: " + nombrePng + ", " + nombreTxt);
                
        } catch (Exception e) {
                System.out.println("Error al crear la imagen de Graphviz - Arbol :"+ nombrePng);
        }
            
        System.out.println("");
    }
    
    // REPORTE SIGIENTES - PNG, TXT
    public void reporteGraficaSiguientes(Arbol arbol, String pathSiguientes){
        System.out.println("");
        System.out.println("Siguientes -" + arbol.getNombre());
            
        String nombreTxt = arbol.getNombre() + ".txt";
        String nombrePng = arbol.getNombre() + ".png";
        String graficaSiguientes = arbol.realizarSiguientes();
            
            
        // Creamos el archivo .txt para cada arbol
        // Creamos el png con ese archivo txt
        try {
            String dirDot = pathSiguientes + "\\" + nombreTxt;
            String dirPng = pathSiguientes + "\\" + nombrePng;
                
            PrintWriter writer = new PrintWriter(dirDot, "UTF-8");
            writer.print(graficaSiguientes);
            writer.close();
                
            String cmd = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot " + "-Tpng " + "-o " + " " + dirPng + " " + dirDot;
            java.lang.Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
            System.out.println("Se creo el archivo de siguientes: " + nombrePng + ", " + nombreTxt);
                
        } catch (Exception e) {
                System.out.println("Error al crear la imagen de Graphviz - Siguientes: "+ nombrePng);
        }
            
        System.out.println("");
    }
    
    // REPORTE TRANSICIONES - PNG, TXT
    public void reporteGraficaTransiciones(Arbol arbol, String pathTransiciones){
        System.out.println("");
        System.out.println("Transiciones -" + arbol.getNombre());
            
        String nombreTxt = arbol.getNombre() + ".txt";
        String nombrePng = arbol.getNombre() + ".png";
        String graficaTransiciones = arbol.realizarGraficaTransiciones();
            
            
        // Creamos el archivo .txt para cada arbol
        // Creamos el png con ese archivo txt
        try {
            String dirDot = pathTransiciones + "\\" + nombreTxt;
            String dirPng = pathTransiciones + "\\" + nombrePng;
                
            PrintWriter writer = new PrintWriter(dirDot, "UTF-8");
            writer.print(graficaTransiciones);
            writer.close();
                
            String cmd = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot " + "-Tpng " + "-o " + " " + dirPng + " " + dirDot;
            java.lang.Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
            System.out.println("Se creo el archivo de Transiciones: " + nombrePng + ", " + nombreTxt);
                
        } catch (Exception e) {
                System.out.println("Error al crear la imagen de Graphviz - Transiciones: "+ nombrePng);
        }
            
        System.out.println("");
    }
    
    // REPORTE AFD - PNG, TXT
    public void reporteGraficaAFD(Arbol arbol, String pathAfd){
        System.out.println("");
        System.out.println("AFD -" + arbol.getNombre());
            
        String nombreTxt = arbol.getNombre() + ".txt";
        String nombrePng = arbol.getNombre() + ".png";
        String graficaAFD = arbol.realizarGraficaAFD();
            
            
        // Creamos el archivo .txt para cada arbol
        // Creamos el png con ese archivo txt
        try {
            String dirDot = pathAfd + "\\" + nombreTxt;
            String dirPng = pathAfd + "\\" + nombrePng;
                
            PrintWriter writer = new PrintWriter(dirDot, "UTF-8");
            writer.print(graficaAFD);
            writer.close();
                
            String cmd = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot " + "-Tpng " + "-o " + " " + dirPng + " " + dirDot;
            java.lang.Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
            System.out.println("Se creo el archivo de AFD: " + nombrePng + ", " + nombreTxt);
                
        } catch (Exception e) {
                System.out.println("Error al crear la imagen de Graphviz - AFD: "+ nombrePng);
        }
            
        System.out.println("");
    }
}
