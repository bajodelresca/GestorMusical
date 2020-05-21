/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.model;



/**
 *
 * @author espin
 */
public class Componente {
    protected String Nombre;
    protected String Instrumento;
    protected int ID;
    protected int fechainscripcion;
    

    public Componente(String Nombre, String Instrumento, int ID,int fechainscripcion) {
        this.Nombre = Nombre;
        this.Instrumento = Instrumento;
        this.ID = ID;
        this.fechainscripcion = fechainscripcion;
    }

    public Componente() {
       this ("","",-1,-1);
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getInstrumento() {
        return Instrumento;
    }

    public void setInstrumento(String Instrumento) {
        this.Instrumento = Instrumento;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getFechainscripcion() {
        return fechainscripcion;
    }

    public void setFechainscripcion(int fechainscripcion) {
        this.fechainscripcion = fechainscripcion;
    }

    @Override
    public String toString() {
        return "Componente{" + "Nombre=" + Nombre + ", Instrumento=" + Instrumento + ", ID=" + ID + ", fechainscripcion=" + fechainscripcion + '}';
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
        final Componente other = (Componente) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }

   
    
    
    
    
}
