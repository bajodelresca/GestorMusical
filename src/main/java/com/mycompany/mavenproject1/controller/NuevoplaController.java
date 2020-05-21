/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.model.Planensayo;
import com.mycompany.mavenproject1.model.PlanensayoDAO;
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
public class NuevoplaController implements Initializable{
    @FXML
    private TextField Nombrec;
    @FXML
    private TextField Nombrem;
    @FXML
    private TextField IDc;
    @FXML
    private TextField IDm;

    private PlanensayoController parent;
    private Object params;
    private Stage nplanStage;

    public void setStage(Stage ncompStage) {
        this.nplanStage = ncompStage;
    }

    public void setParent(PlanensayoController p) {
        parent = p;
    }

    public void setParams(Object p) {
        this.nplanStage = nplanStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void add() {
        String Nombrec = this.Nombrec.getText();
        String Nombrem = this.Nombrem.getText();
        String IDc = this.IDc.getText();
        String IDm = this.IDm.getText();
       
        

        if (Nombrec.trim().length() > 0 && Nombrem.trim().length() > 0 && IDc.trim().length() > 0 && IDm.trim().length() > 0) {
            Planensayo p = new Planensayo();
                  
            

            p.setNombrecomponen(Nombrec);
            p.setNombremarcha(Nombrem);
             int idc = Integer.parseInt(IDc);
             int idm = Integer.parseInt(IDm);
            p.setIDcomponente(idc);
            p.setIDmarcha(idm);
            PlanensayoDAO newPlan = new PlanensayoDAO(p);
            newPlan.save();

            List<Planensayo> miscomponentes = PlanensayoDAO.selectAll();
            parent.data.addAll(miscomponentes);

            if (this.nplanStage != null) {
                this.nplanStage.close();
            }
        } else {if(parent!=null){
            
                parent.showWarning("Aviso","Debe rellenar todos los campos","Debe rellenar todos los campos");


            }
        }
    }
     
                
    

    @FXML
    public void atras() {
        if (this.nplanStage != null) {
            this.nplanStage.close();

        }
    }
    
}
    

