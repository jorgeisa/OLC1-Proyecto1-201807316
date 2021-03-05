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
public class Afnd {
    private String nombreAfnd;
    private String AfndGraph;

    public Afnd(String nombreAfnd, String AfndGraph) {
        this.nombreAfnd = nombreAfnd;
        this.AfndGraph = AfndGraph;
    }

    public String getNombreAfnd() {
        return nombreAfnd;
    }

    public void setNombreAfnd(String nombreAfnd) {
        this.nombreAfnd = nombreAfnd;
    }

    public String getAfndGraph() {
        return AfndGraph;
    }

    public void setAfndGraph(String AfndGraph) {
        this.AfndGraph = AfndGraph;
    }
}
