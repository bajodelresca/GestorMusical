/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.model.Marcha;
import com.mycompany.mavenproject1.model.MarchaDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author espin
 */
public class NuevamarController implements Initializable{
    @FXML
    private TextField Nombre;
    @FXML
    private TextField Autor;
    @FXML
    private TextField anoinscripcion;

    private RepertorioController parent;
    private Object params;
    private Stage nmarStage;

    public void setStage(Stage ncompStage) {
        this.nmarStage = ncompStage;
    }

    public void setParent(RepertorioController p) {
        parent = p;
    }

    public void setParams(Object p) {
        this.nmarStage = nmarStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void add() {
        String Nombre = this.Nombre.getText();
        String Autor = this.Autor.getText();
        String anoinscripcion = this.anoinscripcion.getText();
       
        

        if (Nombre.trim().length() > 0 && Autor.trim().length() > 0 && anoinscripcion.trim().length() > 0) {
            Marcha c = new Marcha();
                  
            

            c.setNombre(Nombre);
            c.setAutor(Autor);
             int fecha = Integer.parseInt(anoinscripcion);
            c.setAñoañadida(fecha);
            MarchaDAO newComponente = new MarchaDAO(c);
            int newId=newComponente.save();
            
           
            
            c.setID(newId);
            
           
            

            
            parent.datos.add(c);

            if (this.nmarStage != null) {
                this.nmarStage.close();
            }
        } else {if(parent!=null){
            
                parent.showWarning("Aviso","Debe rellenar todos los campos","Debe rellenar todos los campos");


            }
        }
    }
     
                
    

    @FXML
    public void atras() {
        if (this.nmarStage != null) {
            this.nmarStage.close();

        }
    }
    
}
