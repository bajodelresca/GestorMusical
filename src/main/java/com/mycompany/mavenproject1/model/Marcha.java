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
    protected LocalDate añadida;

    public Marcha(String Nombre, String Autor, int ID, LocalDate añadida) {
        this.Nombre = Nombre;
        this.Autor = Autor;
        this.ID = ID;
        this.añadida = añadida;
    }

    public Marcha() {
        this ("","",-1,LocalDate.now());
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

    public LocalDate getAñadida() {
        return añadida;
    }

    public void setAñadida(LocalDate añadida) {
        this.añadida = añadida;
    }

    @Override
    public String toString() {
        return "Marcha{" + "Nombre=" + Nombre + ", Autor=" + Autor + ", ID=" + ID + ", a\u00f1adida=" + añadida + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
