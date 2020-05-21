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
public class Planensayo {
    protected String Nombrecomponen;
    protected String Nombremarcha;
    protected int ID;
    protected int IDcomponente;
    protected int IDmarcha;

    public Planensayo(String Nombrecomponen, String Nombremarcha, int ID, int IDcomponente, int IDmarcha) {
        this.Nombrecomponen = Nombrecomponen;
        this.Nombremarcha = Nombremarcha;
        this.ID = ID;
        this.IDcomponente = IDcomponente;
        this.IDmarcha = IDmarcha;
    }

    public Planensayo() {
        this ("","",-1,-1,-1);
    }

    public String getNombrecomponen() {
        return Nombrecomponen;
    }

    public void setNombrecomponen(String Nombrecomponen) {
        this.Nombrecomponen = Nombrecomponen;
    }

    public String getNombremarcha() {
        return Nombremarcha;
    }

    public void setNombremarcha(String Nombremarcha) {
        this.Nombremarcha = Nombremarcha;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDcomponente() {
        return IDcomponente;
    }

    public void setIDcomponente(int IDcomponente) {
        this.IDcomponente = IDcomponente;
    }

    public int getIDmarcha() {
        return IDmarcha;
    }

    public void setIDmarcha(int IDmarcha) {
        this.IDmarcha = IDmarcha;
    }

    @Override
    public String toString() {
        return "Planensayo{" + "Nombrecomponen=" + Nombrecomponen + ", Nombremarcha=" + Nombremarcha + ", ID=" + ID + ", IDcomponente=" + IDcomponente + ", IDmarcha=" + IDmarcha + '}';
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
        final Planensayo other = (Planensayo) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }
    
    
    
    

    
}
