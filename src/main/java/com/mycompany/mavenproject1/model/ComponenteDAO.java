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
public class ComponenteDAO extends Componente{
    public static List<Componente> selectAll(){
           return selectAll("");
     }
    
    public static List<Componente> selectAll(String pattern){
        List<Componente> result=new ArrayList<>();
        
        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q="SELECT * FROM componente";
           
            if(pattern.length()>0){
                q+=" WHERE Nombre LIKE ?";
            }
            PreparedStatement ps=conn.prepareStatement(q);
            
            if(pattern.length()>0){
                ps.setString(1, pattern+"%");
            }
            
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Componente n = new Componente();
                    n.setID(rs.getInt("ID"));
                    n.setNombre(rs.getString("Nombre"));
                    n.setInstrumento(rs.getString("Instrumento"));
                    result.add(n);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return result;
    }
    
    public int remove(){
        int result=-1;
        if (this.ID > 0) {
            
            try {
               java.sql.Connection csql = ConnectionUtil.getConnection();
                String q = "DELETE FROM componente WHERE ID=" + this.ID;

                PreparedStatement ps = csql.prepareStatement(q);
                result = ps.executeUpdate();
                if(result>0)
                    this.ID = -1;
                

            } catch (SQLException ex) {
                Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
    

