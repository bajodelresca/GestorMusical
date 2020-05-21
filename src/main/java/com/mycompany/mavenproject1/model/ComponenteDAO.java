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
    final String buscarID="SELECT * FROM Componente WHERE ID="+ID;
    final String insertar="INSERT INTO Componente (Nombre,Instrumento,ID,Fechainscripcion) VALUES(?,?,NULL,?)";
    final String actualizar="UPDATE componente SET Nombre = ?, Instrumento = ?, Fechainscripcion = ? WHERE ID = ?";
    final String borrar= "DELETE FROM componente WHERE ID=?";    
    static String seleccionar="SELECT * FROM Componente";
    
    
   
     private boolean persist;

    public ComponenteDAO() {
        super();
        persist=false;
    }
    public ComponenteDAO(String Nombre, String Instrumento, int ID,int fechainscripcion){
        super(Nombre,Instrumento,ID,fechainscripcion);
        persist=false;
    }
    public ComponenteDAO(String Nombre, String Instrumento,int fechainscripcion){
        super(Nombre,Instrumento,-1,fechainscripcion);
        persist=false;
    }
    
    public ComponenteDAO(Componente i){
        Nombre=i.Nombre;
        Instrumento=i.Instrumento;
        ID=i.ID;
        fechainscripcion=i.fechainscripcion;
    }
    
        
    public ComponenteDAO(int ID){
        super();

        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();            
            PreparedStatement ps=conn.prepareStatement(buscarID);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    this.ID=ID;
                    this.Nombre=rs.getString("Nombre");
                    this.Instrumento=rs.getString("Instrumento");
                    this.fechainscripcion=rs.getInt("Fechainscripcion");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void persist(){
        persist=true;
    }
    public void detatch(){
        persist=false;
    }

    @Override
    public void setNombre(String Nombre) {
        super.setNombre(Nombre); 
        if(persist){
            save();
        }
    }

    @Override
    public void setInstrumento(String Instrumento) {
        super.setInstrumento(Instrumento);
        if(persist){
            save();
        }
    }
    
    @Override
    public void setFechainscripcion(int fechainscripcion) {
        super.setFechainscripcion(fechainscripcion);
        if(persist){
            save();
        }
    }

    public int save(){
        int result=-1;
        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            
            if(this.ID>0){
                
                
                PreparedStatement ps = csql.prepareStatement(actualizar);
                ps.setString(1, Nombre);
                ps.setString(2, Instrumento);
                ps.setInt(3, fechainscripcion);
                ps.setInt(4, ID);
                result = ps.executeUpdate();
            }else{
                
                
                PreparedStatement ps = csql.prepareStatement(insertar, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, Nombre);
                ps.setString(2, Instrumento);
                ps.setInt(3, fechainscripcion);
                result = ps.executeUpdate();
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        result = generatedKeys.getInt(1);  
                    }
                }
                this.ID = result;
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
     public static List<Componente> selectAll(){
           return selectAll("");
     }
    
    public static List<Componente> selectAll(String pattern){
        List<Componente> result=new ArrayList<>();
        
        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            
           
            if(pattern.length()>0){
                seleccionar+=" WHERE Nombre LIKE ?";
            }
            PreparedStatement ps=conn.prepareStatement(seleccionar);
            
            if(pattern.length()>0){
                ps.setString(1, pattern+"%");
            }
            
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Componente n = new Componente();                    
                    n.setNombre(rs.getString("Nombre"));
                    n.setInstrumento(rs.getString("Instrumento"));
                    n.setID(rs.getInt("ID"));
                    n.setFechainscripcion(rs.getInt("Fechainscripcion"));
                    result.add(n);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return result;
    }
    
      public void remove (Componente componente) {        
       PreparedStatement ps=null;
        try{
            java.sql.Connection conn = ConnectionUtil.getConnection();
            ps=conn.prepareStatement(borrar);
            ps.setInt(1,componente.getID());
           
            if(ps.executeUpdate()==0) {
                throw new SQLException("No se Ha insertado correctamente");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps !=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
    

