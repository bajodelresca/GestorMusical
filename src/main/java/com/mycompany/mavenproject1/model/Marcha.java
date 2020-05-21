/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.model;

import java.time.LocalDate;

/**
 *
 * @author espin
 */
public class Marcha {
    protected String Nombre;
    protected String Autor;
    protected int ID;
    protected int añoañadida;

    public Marcha(String Nombre, String Autor, int ID, int añoañadida) {
        this.Nombre = Nombre;
        this.Autor = Autor;
        this.ID = ID;
        this.añoañadida = añoañadida;
    }

    public Marcha() {
        this ("","",-1,-1);
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAñoañadida() {
        return añoañadida;
    }

    public void setAñoañadida(int añoañadida) {
        this.añoañadida = añoañadida;
    }

    @Override
    public String toString() {
        return "Marcha{" + "Nombre=" + Nombre + ", Autor=" + Autor + ", ID=" + ID + ", a\u00f1oa\u00f1adida=" + añoañadida + '}';
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Marcha other = (Marcha) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }

  
    
    
    
    
}
