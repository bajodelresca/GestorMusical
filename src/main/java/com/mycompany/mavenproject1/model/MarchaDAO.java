/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.model;

import com.mycompany.mavenproject1.Utils.ConnectionUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author espin
 */
public class MarchaDAO extends Marcha {

    final String buscarID = "SELECT * FROM Marcha WHERE ID=" + ID;
    final String actualizar = "UPDATE Marcha SET Nombre = ?, Autor = ?, Añoañadida = ? WHERE ID = ?";
    final String insertar = "INSERT INTO Marcha (Nombre,Autor,ID,Añoañadida) VALUES(?,?,NULL,?)";
    final String borrar = "DELETE FROM Marcha WHERE ID=?";
    static String seleccionar = "SELECT * FROM Marcha";

    private boolean persist;

    public MarchaDAO() {
        super();
        persist = false;
    }

    public MarchaDAO(String Nombre, String Autor, int ID, int añoañadida) {
        super(Nombre, Autor, ID, añoañadida);
        persist = false;
    }

    public MarchaDAO(String Nombre, String Autor, int añoañadida) {
        super(Nombre, Autor, -1, añoañadida);
        persist = false;
    }

    public MarchaDAO(Marcha i) {
        Nombre = i.Nombre;
        Autor = i.Autor;
        ID = i.ID;
        añoañadida = i.añoañadida;
    }

    public MarchaDAO(int ID) {
        super();

        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(buscarID);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    this.ID = ID;
                    this.Nombre = rs.getString("Nombre");
                    this.Autor = rs.getString("Autor");
                    this.añoañadida = rs.getInt("Añoañadida");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(MarchaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void persist() {
        persist = true;
    }

    public void detatch() {
        persist = false;
    }

    @Override
    public void setNombre(String Nombre) {
        super.setNombre(Nombre);
        if (persist) {
            save();
        }
    }

    @Override
    public void setAutor(String Autor) {
        super.setAutor(Autor);
        if (persist) {
            save();
        }
    }

    @Override
    public void setAñoañadida(int añoañadida) {
        super.setAñoañadida(añoañadida);
        if (persist) {
            save();
        }
    }

    public int save() {
        int result = -1;
        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();

            if (this.ID > 0) {

                PreparedStatement ps = csql.prepareStatement(actualizar);
                ps.setString(1, Nombre);
                ps.setString(2, Autor);
                ps.setInt(3, añoañadida);
                ps.setInt(4, ID);
                result = ps.executeUpdate();
            } else {

                PreparedStatement ps = csql.prepareStatement(insertar, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, Nombre);
                ps.setString(2, Autor);
                ps.setInt(3, añoañadida);
                result = ps.executeUpdate();
                try ( ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        result = generatedKeys.getInt(1);
                    }
                }
                this.ID = result;

            }
        } catch (SQLException ex) {
            Logger.getLogger(MarchaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static List<Marcha> selectAll() {
        return selectAll("");
    }

    public static List<Marcha> selectAll(String pattern) {
        List<Marcha> result = new ArrayList<>();

        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();

            if (pattern.length() > 0) {
                seleccionar += " WHERE Nombre LIKE ?";
            }
            PreparedStatement ps = conn.prepareStatement(seleccionar);

            if (pattern.length() > 0) {
                ps.setString(1, pattern + "%");
            }

            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Marcha n = new Marcha();
                    n.setNombre(rs.getString("Nombre"));
                    n.setAutor(rs.getString("Autor"));
                    n.setID(rs.getInt("ID"));
                    n.setAñoañadida(rs.getInt("Añoañadida"));
                    result.add(n);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(MarchaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

     public void remove (Marcha marcha) {        
       PreparedStatement ps=null;
        try{
            java.sql.Connection conn = ConnectionUtil.getConnection();
            ps=conn.prepareStatement(borrar);
            ps.setInt(1,marcha.getID());
           
            if(ps.executeUpdate()==0) {
                throw new SQLException("No se Ha insertado correctamente");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarchaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps !=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MarchaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
