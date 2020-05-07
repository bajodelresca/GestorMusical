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
public class Componente {
    protected String Nombre;
    protected String Instrumento;
    protected int ID;
    protected LocalDate inscripcion;
    

    public Componente(String Nombre, String Instrumento, int ID, LocalDate inscripcion) {
        this.Nombre = Nombre;
        this.Instrumento = Instrumento;
        this.ID = ID;
        this.inscripcion = inscripcion;
    }

    public Componente() {
       this ("","",-1,LocalDate.now());
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

    public LocalDate getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(LocalDate inscripcion) {
        this.inscripcion = inscripcion;
    }

    @Override
    public String toString() {
        return "Componente{" + "Nombre=" + Nombre + ", Instrumento=" + Instrumento + ", ID=" + ID + ", inscripcion=" + inscripcion + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
