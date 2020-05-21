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
public class PlanensayoDAO extends Planensayo {

    final String buscarID = "SELECT * FROM planensayo WHERE ID=" + ID;
    final String actualizar = "UPDATE planensayo SET Nombrecomponen = ?, Nombremarcha = ?, IDcomponente = ?, IDmarcha = ? WHERE ID = ?";
    final String insertar = "INSERT INTO planensayo (Nombrecomponen,Nombremarcha,ID,IDcomponente,IDmarcha) VALUES(?,?,NULL,?,?)";
    final String borrar = "DELETE FROM planensayo WHERE ID=?";
    static String seleccionar = "SELECT * FROM planensayo";

    private boolean persist;

    public PlanensayoDAO() {
        super();
        persist = false;
    }

    public PlanensayoDAO(String Nombrecomponen, String Nombremarcha, int ID, int IDcomponente, int IDmarcha) {
        super(Nombrecomponen, Nombremarcha, ID, IDcomponente, IDmarcha);
        persist = false;
    }

    public PlanensayoDAO(String Nombrecomponen, String Nombremarcha,int IDcomponente, int IDmarcha) {
        super(Nombrecomponen, Nombremarcha,-1,IDcomponente,IDmarcha);
        persist = false;
    }

    public PlanensayoDAO(Planensayo i) {
        Nombrecomponen = i.Nombrecomponen;
        Nombremarcha = i.Nombremarcha;
        ID = i.ID;
        IDcomponente = i.IDcomponente;
        IDmarcha = i.IDmarcha;
        
    }
public PlanensayoDAO(int ID){
        super();

        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();            
            PreparedStatement ps=conn.prepareStatement(buscarID);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    this.Nombrecomponen = rs.getString("Nombrecomponen");
                    this.Nombremarcha = rs.getString("Nombremarcha");
                    this.ID = ID;
                    this.IDcomponente = rs.getInt("IDcomponente");
                    this.IDmarcha = rs.getInt("IDmarcha");
                    
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(PlanensayoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
                  

    public void persist() {
        persist = true;
    }

    public void detatch() {
        persist = false;
    }
    @Override
    public void setNombrecomponen(String Nombrecomponen) {
        super.setNombrecomponen(Nombrecomponen);
        if (persist) {
            save();
        }
    }

    @Override
    public void setNombremarcha(String Nombremarcha) {
        super.setNombremarcha(Nombremarcha);
        if (persist) {
            save();
        }
    }

    @Override
    public void setIDcomponente(int IDcomponente) {
        super.setIDcomponente(IDcomponente);
        if (persist) {
            save();
        }
    }

    @Override
    public void setIDmarcha(int IDmarcha) {
        super.setIDmarcha(IDmarcha);
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
                ps.setString(1, Nombrecomponen);
                ps.setString(2, Nombremarcha);
                ps.setInt(3, IDcomponente);
                ps.setInt(4, IDmarcha);
                ps.setInt(5, ID);
                result = ps.executeUpdate();
            } else {

                PreparedStatement ps = csql.prepareStatement(insertar, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, Nombrecomponen);
                ps.setString(2, Nombremarcha);                
                ps.setInt(3, IDcomponente);
                ps.setInt(4, IDmarcha);
                
                result = ps.executeUpdate();
                try ( ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        result = generatedKeys.getInt(1);
                    }
                }
                this.ID = result;

            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanensayoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static List<Planensayo> selectAll() {
        return selectAll("");
    }

    public static List<Planensayo> selectAll(String pattern) {
        List<Planensayo> result = new ArrayList<>();

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
                    Planensayo n = new Planensayo();

                    n.setID(rs.getInt("ID"));
                    n.setIDcomponente(rs.getInt("IDcomponente"));
                    n.setIDmarcha(rs.getInt("IDmarcha"));
                    n.setNombrecomponen(rs.getString("Nombrecomponen"));
                    n.setNombremarcha(rs.getString("Nombremarcha"));
                    result.add(n);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(PlanensayoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

     public void remove (Planensayo planensayo) {        
       PreparedStatement ps=null;
        try{
            java.sql.Connection conn = ConnectionUtil.getConnection();
            ps=conn.prepareStatement(borrar);
            ps.setInt(1,planensayo.getID());
           
            if(ps.executeUpdate()==0) {
                throw new SQLException("No se Ha insertado correctamente");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanensayoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps !=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PlanensayoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
