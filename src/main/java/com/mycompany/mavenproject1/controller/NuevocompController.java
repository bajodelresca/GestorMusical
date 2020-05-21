/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.model.Componente;
import com.mycompany.mavenproject1.model.ComponenteDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author espin
 */
public class NuevocompController implements Initializable {

    @FXML
    private TextField Nombre;
    @FXML
    private TextField Instrumento;
    @FXML
    private TextField fechainscripcion;

    private ComponentesController parent;
    private Object params;
    private Stage ncompStage;

    public void setStage(Stage ncompStage) {
        this.ncompStage = ncompStage;
    }

    public void setParent(ComponentesController p) {
        parent = p;
    }

    public void setParams(Object p) {
        this.ncompStage = ncompStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void add() {
        String Nombre = this.Nombre.getText();
        String Instrumento = this.Instrumento.getText();
        String fechainscripcion = this.fechainscripcion.getText();
       
        

        if (Nombre.trim().length() > 0 && Instrumento.trim().length() > 0 && Instrumento.trim().length() > 0) {
            Componente c = new Componente();
                  
            

            c.setNombre(Nombre);
            c.setInstrumento(Instrumento);
             int fecha = Integer.parseInt(fechainscripcion);
            c.setFechainscripcion(fecha);
            ComponenteDAO newComponente = new ComponenteDAO(c);
            newComponente.save();

            List<Componente> miscomponentes = ComponenteDAO.selectAll();
            parent.data.addAll(miscomponentes);

            if (this.ncompStage != null) {
                this.ncompStage.close();
            }
        } else {if(parent!=null){
            
                parent.showWarning("Aviso","Debe rellenar todos los campos","Debe rellenar todos los campos");


            }
        }
    }
     
                
    

    @FXML
    public void atras() {
        if (this.ncompStage != null) {
            this.ncompStage.close();

        }
    }

}
