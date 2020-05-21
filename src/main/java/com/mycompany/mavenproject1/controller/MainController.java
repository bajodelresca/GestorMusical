/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author espin
 */
public class MainController implements Initializable {
     private PrimaryController parent;
    private Object params;
    private Stage myStage;
public void setStage(Stage myStage){
        this.myStage=myStage;
    }
    public void setParent(PrimaryController p){
        params=p;
    }
    public void setParams(Object p){
        this.myStage=myStage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    public void openComponente(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("componentes.fxml"));
            Parent modale = fxmlLoader.load();
            Stage modaleStage=new Stage();
            modaleStage.setTitle("Componentes");
            modaleStage.initModality(Modality.APPLICATION_MODAL);
            modaleStage.initOwner(myStage);

            Scene modalScene=new Scene(modale);
            modaleStage.setScene(modalScene);
            
            ComponentesController componentescontroller=fxmlLoader.getController();
            if(componentescontroller!=null){
                componentescontroller.setStage(modaleStage);
                componentescontroller.setParent(this);
                componentescontroller.setParams(null);
            }
            
            modaleStage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void openRepertorio(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("repertorio.fxml"));
            Parent modale = fxmlLoader.load();
            Stage modaleStage=new Stage();
            modaleStage.setTitle("Repertorio");
            modaleStage.initModality(Modality.APPLICATION_MODAL);
            modaleStage.initOwner(myStage);

            Scene modalScene=new Scene(modale);
            modaleStage.setScene(modalScene);
            
            RepertorioController repertoriocontroller=fxmlLoader.getController();
            if(repertoriocontroller!=null){
                repertoriocontroller.setStage(modaleStage);
                repertoriocontroller.setParent(this);
                repertoriocontroller.setParams(null);
            }
            
            modaleStage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void openPlanensayo(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("planensayo.fxml"));
            Parent modale = fxmlLoader.load();
            Stage modaleStage=new Stage();
            modaleStage.setTitle("Plan de ensayo");
            modaleStage.initModality(Modality.APPLICATION_MODAL);
            modaleStage.initOwner(myStage);

            Scene modalScene=new Scene(modale);
            modaleStage.setScene(modalScene);
            
            PlanensayoController planensayoController=fxmlLoader.getController();
            if(planensayoController!=null){
                planensayoController.setStage(modaleStage);
                planensayoController.setParent(this);
                planensayoController.setParams(null);
            }
            
            modaleStage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void salir(){
        if(this.myStage != null){
            this.myStage.close();
            App.rootstage.close();
        }
    }
}
